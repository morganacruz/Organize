package com.example.morgana.testeestagio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InicialLogadoActivity extends AppCompatActivity {
    private Button addProjeto;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference projeto;
    ListView listaProjetos;

    List<Projeto> listprojetinho;
    public static final String Projeto_Nome ="com.example.morgana.testeestagio.projetonomeini";
    public static final String Projeto_ID ="com.example.morgana.testeestagio.projetoidini";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial_logado);

        projeto = FirebaseDatabase.getInstance().getReference("projeto");

       /* listaProjetos = (ListView) findViewById(R.id.listViewlistaProjetosTELAPrin);
            listprojetinho = new ArrayList<>();

        listaProjetos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Projeto tentprojeto = listprojetinho.get(position);
                Intent intent = new Intent(getApplicationContext(), AtiviProjetoActivity.class);
                // Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(Projeto_Nome, tentprojeto.getNome());
                intent.putExtra(Projeto_ID, tentprojeto.getId());

                startActivity(intent);
                return true;
            }
        });*/


        addProjeto = (Button) findViewById(R.id.btnaddproject);
        addProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniadd = new Intent(InicialLogadoActivity.this, AdicionarProjetoActivity.class);
                startActivity(iniadd);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_iniciallogado, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.itemdados:
                Intent inidados = new Intent(InicialLogadoActivity.this, ExibeDadosActivity.class);
                startActivity(inidados);
                break;
            case R.id.itemsair:
                AlertDialog.Builder builder = new AlertDialog.Builder(InicialLogadoActivity.this);
                builder.setMessage("Deseja sair ?");
                builder.setCancelable(true);
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();

                    }
                });
                builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // ExibeProjetos();


    }
   /* public void ExibeProjetos(){
        projeto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listprojetinho.clear();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Projeto projetos = postSnapshot.getValue(Projeto.class);
                        listprojetinho.add(projetos);
                    }
                    ProjetoLista projeto2adapter = new ProjetoLista(InicialLogadoActivity.this, listprojetinho);

                    listaProjetos.setAdapter(projeto2adapter);
                }else {
                    Toast.makeText(getApplicationContext(), "Nenhum projeto adicionado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        listaProjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Projeto projetono = listprojetinho.get(position);
                Intent intent = new Intent(getApplicationContext(), AtiviProjetoActivity.class);
                //  Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(Projeto_Nome, projetono.getNome());
                intent.putExtra(Projeto_ID, projetono.getId());

                startActivity(intent);
            }
        });




    }*/






















}
