package br.com.sipna.controller;

import br.com.sipna.model.Usuario;
import br.com.sipna.dao.UsuarioDAO;

public class LoginController {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String username, String password) {
        return usuarioDAO.autenticar(username, password);
    }
}