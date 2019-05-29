package com.example.morgana.testeestagio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaItenAtividade extends ArrayAdapter<Track> {
    private Activity context;
    List<Track> tracks;

    public ListaItenAtividade(Activity context, List<Track> tracks) {
        super(context, R.layout.layout_projeto_lista, tracks);
        this.context = context;
        this.tracks = tracks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_projeto_lista, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.TextViewNome);



        Track track = tracks.get(position);
        textViewName.setText(track.getTrackName());

        return listViewItem;
    }
}
