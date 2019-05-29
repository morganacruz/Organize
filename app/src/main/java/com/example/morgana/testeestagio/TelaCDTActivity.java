package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.UUID;

public class TelaCDTActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtNome;
    private EditText edtSenha;
    private EditText edtConfSenha;
    private Button btnCadastrar;
    private Usuarios usuarios;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;
    private String idUsuarioCorrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cdt);
        edtEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtNome = (EditText) findViewById(R.id.edtCadNome);
        edtSenha = (EditText) findViewById(R.id.edtCadSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
               /* if(edtSenha.getText().toString().equals(edtConfSenha.getText().toString())){
                    //usuarios.setNome(edtNome.getText().toString());
                    //usuarios.setEmail(edtEmail.getText().toString());
                    //usuarios.setSenha(edtSenha.getText().toString());

                    cadastrarUsuario();
                }else {
                    Toast.makeText(TelaCDTActivity.this, "As senhas são diferentes", Toast.LENGTH_LONG).show();
                }*/

            }
        });
    }


    private void cadastrarUsuario(){
        mFirebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();
        idUsuarioCorrent = UUID.randomUUID().toString();
        mFirebaseAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    final String id = UUID.randomUUID().toString();


                    Toast.makeText(TelaCDTActivity.this, "Usuario cadastrado com Sucesso!",Toast.LENGTH_LONG).show();

                    final List<String> lista = null;

                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    Usuarios u = new  Usuarios(user.getUid(), edtNome.getText().toString().trim(), edtEmail.getText().toString().trim(), edtSenha.getText().toString().trim(), lista);

                    //FirebaseUser usuarioFire = task.getResult().getUser();
                    //usuarios.setId(id);
                    databaseReference.child(user.getUid()).setValue(u);
                    //usuarios.salvar();
                    Log.i("Estado_Activity","Tela Cadastro ::onCreate");
                    //Preferencias preferencias = new Preferencias(TelaCDTActivity.this);
                    //preferencias.salvarUsuarioPreferencias(id, usuarios.getNome());



                    Intent intentTelaPrinc = new Intent(TelaCDTActivity.this, InicialLogadoActivity.class);
                    startActivity(intentTelaPrinc);
                    Log.i("Estado_Activity","Tela passou pela intent ::onCreate");


                }else{
                    Toast.makeText(TelaCDTActivity.this, "Erro ao cadastrar!",Toast.LENGTH_LONG).show();

                }



            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
