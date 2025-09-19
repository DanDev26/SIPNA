package br.com.sipna.controller;

import br.com.sipna.dao.BoletimDAO;
import br.com.sipna.model.Boletim;

public class BoletimController {
    private BoletimDAO boletimDAO = new BoletimDAO();
    
    public Boletim getBoletimPorNome(String nomeAluno) {
        return boletimDAO.getBoletimPorNome(nomeAluno);
    }
}