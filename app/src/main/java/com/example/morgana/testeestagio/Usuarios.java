package com.example.morgana.testeestagio;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Usuarios {
    private String id;
    private String nome;
    private String email;
    private String senha;
    //private String idProjeto;
    private  List<String> idProjetos;

   /* public String getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(String idProjeto) {
        this.idProjeto = idProjeto;
    }*/

    public List<String> getIdProjetos() {
        return idProjetos;
    }

    public void setIdProjetos(List<String> idProjetos) {
        this.idProjetos = idProjetos;
    }

    public Usuarios() {
    }
    public Usuarios(String id, String nome, String email, String senha, List idProjetos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
       // this.idProjeto = idProjeto;
        this.idProjetos = idProjetos;
    }
    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
