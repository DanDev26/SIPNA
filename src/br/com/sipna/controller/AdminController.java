package br.com.sipna.controller;

import br.com.sipna.dao.UsuarioDAO;
import br.com.sipna.model.Perfil;
import br.com.sipna.model.Usuario;

public class AdminController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void adicionarUsuario(String nome, String password, Perfil perfil) {
        if (nome == null || nome.trim().isEmpty() || password == null || password.trim().isEmpty() || perfil == null) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }
        Usuario novoUsuario = new Usuario(nome, password, perfil);
        usuarioDAO.adicionar(novoUsuario);
    }
}