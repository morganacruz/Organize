package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdicionarAtividadeActivity extends AppCompatActivity {
    Button btninserirativi;
    EditText nomeAtivi;
    EditText descativi;
    EditText dataati;
    Spinner statusativi;
    Spinner prioriativi;
    TextView nomeproj;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_atividade);

        Intent intent = getIntent();

        btninserirativi = (Button) findViewById(R.id.btninseratv);
        nomeAtivi = (EditText) findViewById(R.id.edtNomeativi);
        descativi = (EditText) findViewById(R.id.edtdescativi);
        dataati = (EditText) findViewById(R.id.edtdata);
        statusativi = (Spinner) findViewById(R.id.sprstatusatv);
        prioriativi = (Spinner) findViewById(R.id.spirprioriatv);
        nomeproj = (TextView) findViewById(R.id.textNomeProj);

        nomeproj.setText(intent.getStringExtra(AtiviProjetoActivity.ProjetoAti_Nome));

        databaseAtividade = FirebaseDatabase.getInstance().getReference("itemprojeto").child(intent.getStringExtra(AtiviProjetoActivity.ProjetoAti_id));

        btninserirativi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvarAtividade();
            }
        });
    }



    private void salvarAtividade(){
        String tNome = nomeAtivi.getText().toString().trim();
        String tdescricao = descativi.getText().toString().trim();
        String tdataatv = dataati.getText().toString().trim();
        String tstatus = statusativi.getSelectedItem().toString();
        String tprioridade = prioriativi.getSelectedItem().toString();

        if (!TextUtils.isEmpty(tNome)) {
            String id  = databaseAtividade.push().getKey();
            Track track = new Track(id, tNome, tdescricao, tdataatv, tstatus, tprioridade );
            databaseAtividade.child(id).setValue(track);
            Toast.makeText(this, "ATIVIDADE SALVA", Toast.LENGTH_LONG).show();
            Intent inttentListaAti = new Intent(this, AtividadeListaActivity.class);
            startActivity(inttentListaAti);

        } else {
            Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
        }

    }
}
