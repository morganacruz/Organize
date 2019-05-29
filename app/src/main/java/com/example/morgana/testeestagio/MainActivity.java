package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private Usuarios usuarios;
    private TextView  btnCadastro;
    public static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnCadastro = (Button) findViewById(R.id.btnEntrar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("messages");

        edtEmail = (EditText) findViewById(R.id.txtEmail);
        edtSenha = (EditText) findViewById(R.id.txtSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
                    usuarios = new Usuarios();
                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());


                    mFirebaseAuth.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                abrirTelaPrincipal();
                                Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();


                            }else{
                                Toast.makeText(MainActivity.this, "Senha ou Email inválidos", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastro  = (TextView) findViewById(R.id.textViewCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaCDTActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Log.i("Estado_Activity","Tela 1 ::onCreate");



    }


    public void abrirTelaPrincipal(){
        Intent intentTelaPrinc = new Intent(this, InicialLogadoActivity.class);
        startActivity(intentTelaPrinc);
    }

    @Override
    protected void onStart() {
        super.onStart();
       /* FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);*/

    }







}
