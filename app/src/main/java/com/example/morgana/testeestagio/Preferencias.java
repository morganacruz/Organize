package com.example.morgana.testeestagio;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private Context context;
    private SharedPreferences preferences;

    private String NomeArquivo = "testeestagio.Preferencias";
    private int Mode = 0;
    private SharedPreferences.Editor editor;

    private  final String chave_ID = "identificarUsuarioLogado";
    private final String chave_nome = "nomeusuarioLogado";

    public Preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NomeArquivo, Mode);
        editor = preferences.edit();

    }

    public void salvarUsuarioPreferencias(String identiUsuario, String nomeUsuario){
        editor.putString(chave_ID, identiUsuario);
        editor.putString(chave_nome, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(chave_ID, null);
    }
    public String getNome(){
        return preferences.getString(chave_nome, null);
    }







}
