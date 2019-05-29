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

public class AtualizaAtividadeActivity extends AppCompatActivity {

    private Button btnatualizaativi;
    private TextView nomeAtivi;
    private EditText descativi;
    private EditText dataati;
    private Spinner statusativi;
    private Spinner prioriativi;
    private TextView nomeproj;

    String idatv;
    String idprojeto;
    String nomeAtv;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAtualizaAtv;
    public static final String ProjetoAti_idproj ="com.example.morgana.testeestagio.idprojatv";
    public static final String ProjetoAti_idati ="com.example.morgana.testeestagio.idatuaatv";
    public static final String NomeAti_nomeatv ="com.example.morgana.testeestagio.atprid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_atividade);

        Intent intent = getIntent();

        idatv = intent.getStringExtra(AtividadeListaActivity.Ativi_id);
        idprojeto = intent.getStringExtra(AtividadeListaActivity.Proj_id);
        nomeAtv = intent.getStringExtra(AtividadeListaActivity.Ativi_nome);

        databaseAtualizaAtv = FirebaseDatabase.getInstance().getReference("itensatividades").child(idatv);






        btnatualizaativi = (Button) findViewById(R.id.btnatuatv);
        nomeAtivi = (TextView) findViewById(R.id.textNomeAtv);
        descativi = (EditText) findViewById(R.id.edtdescativi2);
        dataati = (EditText) findViewById(R.id.edtdata2);
        statusativi = (Spinner) findViewById(R.id.sprstatusatv2);
        prioriativi = (Spinner) findViewById(R.id.spirprioriatv2);
        //nomeproj = (TextView) findViewById(R.id.textNomeProj);

       nomeAtivi.setText(intent.getStringExtra(AtividadeListaActivity.Ativi_nome));
        //nomeAtivi.setText(intent.getStringExtra(AtividadeListaActivity.Ativi_id));
        btnatualizaativi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inseriratualizacao();
            }
        });

    }

    public void inseriratualizacao(){
        String tNome = nomeAtv;
        String tdescricao = descativi.getText().toString().trim();
        String tdataatv = dataati.getText().toString().trim();
        String tstatus = statusativi.getSelectedItem().toString();
        String tprioridade = prioriativi.getSelectedItem().toString();

        if (!TextUtils.isEmpty(tNome)) {
            String id  = databaseAtualizaAtv.push().getKey();
            Track track = new Track(id, tNome, tdescricao, tdataatv, tstatus, tprioridade );
            databaseAtualizaAtv.child(id).setValue(track);
            Toast.makeText(this, "Atualização SALVA", Toast.LENGTH_LONG).show();
            Intent inttentListaAti = new Intent(AtualizaAtividadeActivity.this, InicialLogadoActivity.class);
           /* inttentListaAti.putExtra(ProjetoAti_idproj, idprojeto);
            inttentListaAti.putExtra(ProjetoAti_idati, idatv);
            inttentListaAti.putExtra(NomeAti_nomeatv, nomeAtv);*/

            startActivity(inttentListaAti);

        } else {
            Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
        }
    }
}
