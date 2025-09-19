package br.com.sipna.model;

import java.util.List;

public class Aula {
    private Materia materia;
    private List<String> presencas;

    public Aula(Materia materia, List<String> presencas) {
        this.materia = materia;
        this.presencas = presencas;
    }

    public Materia getMateria() {
        return materia;
    }

    public List<String> getPresencas() {
        return presencas;
    }
}