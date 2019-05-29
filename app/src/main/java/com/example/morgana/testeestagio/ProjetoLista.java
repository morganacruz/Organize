package com.example.morgana.testeestagio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ProjetoLista extends ArrayAdapter<Projeto> {
    private Activity context;
    List<Projeto> projetosarray;

    public ProjetoLista(Activity context, List<Projeto> projetosarray){
        super(context, R.layout.layout_projeto_lista, projetosarray);
        this.context = context;
        this.projetosarray = projetosarray;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_projeto_lista, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.TextViewNome);


        Projeto projeto = projetosarray.get(position);
        textViewName.setText(projeto.getNome());


        return listViewItem;
    }

}
