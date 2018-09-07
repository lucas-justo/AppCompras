package org.neocities.dolphintech.applista2.model;

public class Anotacao {

    private int id;
    private String nome;
    private String anotacao;

    public Anotacao() {

    }

    public Anotacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.anotacao = anotacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    @Override
    public String toString() {
        return nome;
    }
}

