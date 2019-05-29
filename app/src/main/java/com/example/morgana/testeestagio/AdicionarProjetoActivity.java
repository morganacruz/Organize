package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdicionarProjetoActivity extends AppCompatActivity {

    public static final String Projeto_Nome ="com.example.morgana.testeestagio.projetonome";
    public static final String Projeto_ID ="com.example.morgana.testeestagio.projetoid";



    DatabaseReference projeto, usuario;
    private FirebaseAuth firebaseAuth;

    FirebaseUser user;
    Button btnaddProjeto;
    EditText edtNomeP;
    ListView listViewProjetos;

    List<Projeto> projetinho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_projeto);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        usuario = FirebaseDatabase.getInstance().getReference("usuario");
        projeto = FirebaseDatabase.getInstance().getReference("projeto");

        edtNomeP = (EditText) findViewById(R.id.edtnomePro);
        listViewProjetos = (ListView) findViewById(R.id.listViewlistaProjetos);
        btnaddProjeto = (Button) findViewById(R.id.btnadpro);
        projetinho = new ArrayList<>();

        btnaddProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InserirProjeto();
            }
        });


        listViewProjetos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Projeto projetonovo = projetinho.get(position);
                Intent intent = new Intent(getApplicationContext(), AtiviProjetoActivity.class);
               // Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(Projeto_Nome, projetonovo.getNome());
                intent.putExtra(Projeto_ID, projetonovo.getId());

                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ResgataDadosDoProjeto();
    }

    private void InserirProjeto(){
        String id = UUID.randomUUID().toString();
        String name = edtNomeP.getText().toString().trim();


        if (TextUtils.isEmpty(name)){
            //e-mail vazio
            Toast.makeText(this, "Por favor insira o nome do projeto", Toast.LENGTH_SHORT).show();
            return;
        }

        id = projeto.push().getKey();

        Projeto projeto1 = new Projeto(name, id);
        projeto.child(id).setValue(projeto1);
        usuario.child(user.getUid()).child("idProjetos").setValue(id);
        Toast.makeText(this,"Projeto Adicionado !", Toast.LENGTH_LONG).show();

        ResgataDadosDoProjeto();
    }
   /* public void ResgataDadosUsuario(){
        String valor = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = usuario.orderByKey().equalTo(valor);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Usuarios u = snapshot.getValue(Usuarios.class);
                    ResgataDadosDoProjeto();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/


    public void ResgataDadosDoProjeto(){



        projeto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projetinho.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Projeto projetos = postSnapshot.getValue(Projeto.class);
                    projetinho.add(projetos);
                }
                ProjetoLista projetoadapter = new ProjetoLista(AdicionarProjetoActivity.this, projetinho);
                listViewProjetos.setAdapter(projetoadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



        listViewProjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Projeto projetono = projetinho.get(position);
                Intent intent = new Intent(getApplicationContext(), AtiviProjetoActivity.class);
              //  Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(Projeto_Nome, projetono.getNome());
                intent.putExtra(Projeto_ID, projetono.getId());

                startActivity(intent);
            }
        });

    }

}
