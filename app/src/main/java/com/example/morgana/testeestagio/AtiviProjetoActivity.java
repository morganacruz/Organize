package com.example.morgana.testeestagio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AtiviProjetoActivity extends AppCompatActivity {

    Button btninserirativi;
    EditText nomeAtivi;
    EditText descativi;
    EditText dataati;
    Spinner  statusativi;
    Spinner prioriativi;
    TextView nomeproj;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseTracks;
    List<Track> tracks;
    ListView listViewTracks;
    String idprojeto;
    String nomeprojeto;
    public static final String ProjetoAti_Nome ="com.example.morgana.testeestagio.atprnome";
    public static final String ProjetoAti_id ="com.example.morgana.testeestagio.atprid";
    public static final String ProjetoAti_idati ="com.example.morgana.testeestagio.idaticri";
    public static final String Ati_nomeAtv ="com.example.morgana.testeestagio.nomeAtv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativi_projeto);

        Intent intent = getIntent();

       databaseTracks =  FirebaseDatabase.getInstance().getReference("itemprojeto").child(intent.getStringExtra(AdicionarProjetoActivity.Projeto_ID));



        btninserirativi = (Button) findViewById(R.id.btninseratv);
        nomeproj = (TextView) findViewById(R.id.textNomeProj);

        listViewTracks = (ListView) findViewById(R.id.listeste);

       tracks = new ArrayList<>();

        nomeproj.setText(intent.getStringExtra(AdicionarProjetoActivity.Projeto_Nome));

        nomeprojeto = intent.getStringExtra(AdicionarProjetoActivity.Projeto_Nome);
        idprojeto = intent.getStringExtra(AdicionarProjetoActivity.Projeto_ID);

        btninserirativi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdicionarAtividadeActivity.class);
                //  Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(ProjetoAti_Nome, nomeprojeto);
                intent.putExtra(ProjetoAti_id, idprojeto);
                startActivity(intent);
            }
        });
        listViewTracks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Track trackno = tracks.get(position);
                Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(ProjetoAti_id, idprojeto);
                intent.putExtra(ProjetoAti_idati, trackno.getId());
                intent.putExtra(Ati_nomeAtv, trackno.getTrackName());
               /* intent.putExtra(Projeto_ID, projetono.getId());*/

                startActivity(intent);
                return true;
            }
        });
    }

    public void listadadosativi(){
        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tracks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Track track = postSnapshot.getValue(Track.class);
                    tracks.add(track);
                }
                TrackList trackListAdapter = new TrackList(AtiviProjetoActivity.this, tracks);
                listViewTracks.setAdapter(trackListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listViewTracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Track trackno = tracks.get(position);
                Intent intent = new Intent(getApplicationContext(), AtividadeListaActivity.class);
                intent.putExtra(ProjetoAti_id, idprojeto);
                intent.putExtra(ProjetoAti_idati, trackno.getId());
                intent.putExtra(Ati_nomeAtv, trackno.getTrackName());
               /* intent.putExtra(Projeto_Nome, projetono.getNome());
                intent.putExtra(Projeto_ID, projetono.getId());*/

                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        listadadosativi();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_projeto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemexcluir:
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("projeto").child(idprojeto);

                dR.removeValue();


                DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("itemprojeto").child(idprojeto);

                drTracks.removeValue();
                Toast.makeText(getApplicationContext(), "Projeto Excluido", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AtiviProjetoActivity.this, InicialLogadoActivity.class);
                startActivity(intent);

            break;
        }
        return super.onOptionsItemSelected(item);
    }












}


