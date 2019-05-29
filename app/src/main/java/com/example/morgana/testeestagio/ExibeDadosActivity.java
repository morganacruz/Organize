package com.example.morgana.testeestagio;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

public class ExibeDadosActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private String idExibe;
    private TextView nomeExibe;
    private TextView emailExibe;
    private TextView senhaExibe;
    private String idProjetoExibe;
    private List<String> idProjetosExibe;

    FirebaseUser user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_dados);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("usuario");

        ExibeDadosUsuario();
    }

    public void ExibeDadosUsuario(){

        nomeExibe = (TextView) findViewById(R.id.textExibeNome);
        emailExibe = (TextView) findViewById(R.id.textExibeEmail);
        senhaExibe = (TextView) findViewById(R.id.textExibeSenha);
         String nome = nomeExibe.getText().toString().trim();
         String senha = senhaExibe.getText().toString().trim();
         String email = emailExibe.getText().toString().trim();



        String valor = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = databaseReference.orderByKey().equalTo(valor);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                  //  List<Usuarios> list = new ArrayList<Usuarios>();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                      //  list.add(snapshot.getValue(Usuarios.class));
                        Usuarios usuarios = snapshot.getValue(Usuarios.class);
                        Log.i("ID DO User:", "" + usuarios.getId());

                            nomeExibe.setText(usuarios.getNome());
                            senhaExibe.setText(usuarios.getSenha());
                            emailExibe.setText(usuarios.getEmail());




                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Nenhum usuario adicionado!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemhome:
                Intent intentHome = new Intent(ExibeDadosActivity.this, InicialLogadoActivity.class);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
