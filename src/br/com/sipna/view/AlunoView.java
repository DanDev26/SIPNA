package br.com.sipna.view;

import br.com.sipna.controller.BoletimController;
import br.com.sipna.model.Boletim;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AlunoView extends BaseView {

    private String nomeAluno;
    private BoletimController boletimController = new BoletimController();

    public AlunoView(String nomeAluno) {
        super("Aluno", "Menu Aluno", "Painel do Aluno", new String[]{"Início", "Ver Boletim"});
        this.nomeAluno = nomeAluno;
        addMenuItemListeners();
    }

    private void addMenuItemListeners() {
        List<JButton> botoes = getMenuButtons();
        if (botoes.size() > 1) {
            JButton btnInicio = botoes.get(0);
            JButton btnBoletim = botoes.get(1);
            btnInicio.addActionListener(e -> showDashboard());
            btnBoletim.addActionListener(e -> showBoletimPanel());
        }
    }

    private void showBoletimPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Boletim do Aluno");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        Boletim boletim = boletimController.getBoletimPorNome(nomeAluno);
        BoletimView boletimView = new BoletimView();
        JPanel boletimPanel = boletimView.createPanel(boletim);
        
        mainContentPanel.add(boletimPanel, BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
}