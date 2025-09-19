package br.com.sipna.view;

import br.com.sipna.controller.BoletimController;
import br.com.sipna.model.Boletim;
import br.com.sipna.model.Perfil;
import br.com.sipna.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class AlunosListView {

    private JPanel mainContentPanel;
    private BoletimController boletimController;

    public AlunosListView(JPanel mainContentPanel, BoletimController boletimController) {
        this.mainContentPanel = mainContentPanel;
        this.boletimController = boletimController;
    }

    public JPanel createPanel(List<Usuario> usuarios) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("Lista de Alunos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(15));
        
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.setBackground(new Color(240, 240, 240));

        List<Usuario> alunos = usuarios.stream()
            .filter(u -> u.getPerfil() == Perfil.ALUNO)
            .collect(Collectors.toList());

        if (alunos.isEmpty()) {
            JLabel lblNenhumAluno = new JLabel("Nenhum aluno cadastrado.");
            lblNenhumAluno.setAlignmentX(Component.CENTER_ALIGNMENT);
            listaPanel.add(lblNenhumAluno);
        } else {
            for (Usuario aluno : alunos) {
                JPanel alunoItem = createAlunoItemPanel(aluno);
                listaPanel.add(alunoItem);
                listaPanel.add(Box.createVerticalStrut(5));
            }
        }

        JScrollPane scrollPane = new JScrollPane(listaPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createAlunoItemPanel(Usuario aluno) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel lblInfo = new JLabel("<html><b>ALUNO:</b> " + aluno.getUsername() + "</html>");
        itemPanel.add(lblInfo);

        JButton btnBoletim = new JButton("BOLETIM");
        btnBoletim.setFont(new Font("Arial", Font.BOLD, 12));
        btnBoletim.setForeground(Color.WHITE);
        btnBoletim.setBackground(new Color(76, 175, 80));
        btnBoletim.setFocusPainted(false);
        
        btnBoletim.addActionListener(e -> {
            Boletim boletim = boletimController.getBoletimPorNome(aluno.getUsername());
            
            if (boletim != null && !boletim.getFrequenciaPorMateria().isEmpty()) {
                mainContentPanel.removeAll();
                mainContentPanel.setLayout(new BorderLayout());

                JPanel headerPanel = new JPanel(new BorderLayout());
                headerPanel.setBackground(new Color(240, 240, 240));
                headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
                JLabel lblHeaderTitle = new JLabel("Boletim do Aluno");
                lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
                headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
                mainContentPanel.add(headerPanel, BorderLayout.NORTH);

                BoletimView boletimView = new BoletimView();
                JPanel boletimPanel = boletimView.createPanel(boletim);
                
                mainContentPanel.add(boletimPanel, BorderLayout.CENTER);
                mainContentPanel.revalidate();
                mainContentPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Boletim não encontrado para o aluno: " + aluno.getUsername(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        itemPanel.add(btnBoletim);
        return itemPanel;
    }
}