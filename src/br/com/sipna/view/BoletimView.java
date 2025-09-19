package br.com.sipna.view;

import br.com.sipna.model.Boletim;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

public class BoletimView {

    private static final DecimalFormat DF = new DecimalFormat("#.##");

    public JPanel createPanel(Boletim boletim) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblAluno = new JLabel("Aluno: " + boletim.getAluno());
        lblAluno.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblAluno);
        panel.add(Box.createVerticalStrut(10));

        for (Map.Entry<String, Double> entry : boletim.getFrequenciaPorMateria().entrySet()) {
            JPanel materiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            materiaPanel.setBackground(panel.getBackground());
            
            JLabel lblMateria = new JLabel(entry.getKey() + ":");
            lblMateria.setFont(new Font("Arial", Font.PLAIN, 12));
            
            JLabel lblFrequencia = new JLabel(DF.format(entry.getValue()) + "%");
            lblFrequencia.setFont(new Font("Arial", Font.BOLD, 12));
            
            materiaPanel.add(lblMateria);
            materiaPanel.add(Box.createHorizontalStrut(5));
            materiaPanel.add(lblFrequencia);
            panel.add(materiaPanel);
        }

        return panel;
    }
}