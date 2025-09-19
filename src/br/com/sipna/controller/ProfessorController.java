package br.com.sipna.controller;

import br.com.sipna.dao.ProfessorDAO;
import br.com.sipna.dao.UsuarioDAO;
import br.com.sipna.model.Materia;
import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Materia> getMaterias() {
        return professorDAO.listarMaterias();
    }

    public List<String> getAlunos() {
        List<String> alunos = new ArrayList<>();
        usuarioDAO.listarTodos().stream()
            .filter(u -> u.getPerfil().toString().equals("ALUNO"))
            .forEach(u -> alunos.add(u.getUsername()));
        return alunos;
    }

    public void registrarFrequencia(String nomeProfessor, Materia materia, List<String> presencas) {
        professorDAO.registrarFrequencia(nomeProfessor, materia.getNome(), presencas);
    }
}