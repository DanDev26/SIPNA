package br.com.sipna.controller;

import br.com.sipna.model.Usuario;
import br.com.sipna.model.Perfil;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private List<Usuario> usuarios = new ArrayList<>();

    public LoginController() {
        // Usuários de teste
        usuarios.add(new Usuario("admin", "123", Perfil.ADMIN));
        usuarios.add(new Usuario("prof", "123", Perfil.PROFESSOR));
        usuarios.add(new Usuario("aluno", "123", Perfil.ALUNO));
        usuarios.add(new Usuario("secretario", "123", Perfil.SECRETARIO));
    }

    public Usuario autenticar(String username, String password) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}