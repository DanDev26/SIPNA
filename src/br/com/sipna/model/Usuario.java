package br.com.sipna.model;

public class Usuario {
    private String username;
    private String password;
    private Perfil perfil;

    public Usuario(String username, String password, Perfil perfil) {
        this.username = username;
        this.password = password;
        this.perfil = perfil;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}