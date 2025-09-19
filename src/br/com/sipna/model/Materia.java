package br.com.sipna.model;

public class Materia {
    private String nome;

    public Materia(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    
    @Override
    public String toString() { return nome; }
}