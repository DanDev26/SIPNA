package br.com.sipna.model;

import java.util.HashMap;
import java.util.Map;

public class Boletim {
    private String aluno;
    private Map<String, Double> frequenciaPorMateria;

    public Boletim(String aluno) {
        this.aluno = aluno;
        this.frequenciaPorMateria = new HashMap<>();
    }

    public String getAluno() { return aluno; }

    public Map<String, Double> getFrequenciaPorMateria() { return frequenciaPorMateria; }
    
    public void adicionarFrequencia(String materia, double frequencia) {
        frequenciaPorMateria.put(materia, frequencia);
    }
}