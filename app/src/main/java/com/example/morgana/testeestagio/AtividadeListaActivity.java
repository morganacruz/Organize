package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AtividadeListaActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseTracks;
    DatabaseReference databaseitemat;
    List<Track> tracks;
    ListView listViewTracks;
    TextView nomeAt;
    TextView dataAt;
    TextView descAt;
    TextView statAt;
    TextView prioAt;
    Button inserirativid;
    Button excluiatv;
    String idatv;
    String idprojeto;
    String nomeAtv;
    String idAtividade;
    public static final String Ativi_id ="com.example.morgana.testeestagio.ativiid";
    public static final String Proj_id ="com.example.morgana.testeestagio.projid";
    public static final String Ativi_nome ="com.example.morgana.testeestagio.projid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_lista);
        Intent intent = getIntent();

        idatv = intent.getStringExtra(AtiviProjetoActivity.ProjetoAti_idati);
        idprojeto = intent.getStringExtra(AtiviProjetoActivity.ProjetoAti_id);
        nomeAtv = intent.getStringExtra(AtiviProjetoActivity.Ati_nomeAtv);

       databaseTracks =  FirebaseDatabase.getInstance().getReference("itemprojeto").child(idprojeto);



        //listViewTracks = (ListView) findViewById(R.id.listAtiviList);
        //nomeproj = (TextView) findViewById(R.id.textViewPro);
        tracks = new ArrayList<>();
        nomeAt = (TextView) findViewById(R.id.textnomeat);
        dataAt = (TextView) findViewById(R.id.textdataat);
        descAt = (TextView) findViewById(R.id.tdescat);
        statAt = (TextView) findViewById(R.id.statat);
        prioAt = (TextView) findViewById(R.id.prioat);

        //nomeAt.setText(intent.getStringExtra(AdicionarProjetoActivity.Projeto_Nome));
        inserirativid = (Button) findViewById(R.id.btnatualizaativi);
        inserirativid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent inttentAtualizaAti = new Intent(getBaseContext(), AtualizaAtividadeActivity.class);
                inttentAtualizaAti.putExtra(Ativi_id, idAtividade);
                inttentAtualizaAti.putExtra(Proj_id, idprojeto);
                inttentAtualizaAti.putExtra(Ativi_nome, nomeAtv);
                startActivity(inttentAtualizaAti);

            }
        });
        excluiatv = (Button) findViewById(R.id.btnexcluiativi);
        excluiatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("itemprojeto").child(idprojeto).child(idatv);

                drTracks.removeValue();
                Toast.makeText(getApplicationContext(), "Atividade Excluída", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AtividadeListaActivity.this, InicialLogadoActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        regatadadosatividade();
        /*databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // tracks.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Track track = postSnapshot.getValue(Track.class);
                        //nomeAt.setText(track.getTrackName());
                        //dataAt.setText(track.getTrackData());
                       // descAt.setText(track.getTrackDesc());
                       // statAt.setText(track.getTrackStatus());
                       // prioAt.setText(track.getTrackPriori());
                        regatadadosatividade(track.getId());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }
    public void regatadadosatividade(){


        // Faço a busca ordenando os ids do projeto e comparando ao idatividade do usuário
        Query query = databaseTracks.orderByKey().equalTo(idatv);
        //Query query = databaseTracks.child(idatividade);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snap : dataSnapshot.getChildren()){
                        Track track = snap.getValue(Track.class);
                        //String id = track.getId();

                            nomeAt.setText(track.getTrackName());
                            dataAt.setText(track.getTrackData());
                            descAt.setText(track.getTrackDesc());
                            statAt.setText(track.getTrackStatus());
                            prioAt.setText(track.getTrackPriori());
                            idAtividade = track.getId();
                           // listaitensatv(idAtividade);

                    }
                }/*else{
                    Toast.makeText(getApplicationContext(), "Nenhuma atividade adicionada!", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void listaitensatv(String itemid){


    }

}
