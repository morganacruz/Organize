package com.example.morgana.testeestagio;

public class Projeto {
    private String nome;
    private String id;

    public Projeto(){

    }

    public Projeto(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
