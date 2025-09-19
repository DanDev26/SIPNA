package br.com.sipna.view;

import br.com.sipna.controller.ProfessorController;
import br.com.sipna.controller.BoletimController;
import br.com.sipna.dao.UsuarioDAO;
import br.com.sipna.model.Boletim;
import br.com.sipna.model.Materia;
import br.com.sipna.model.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorView extends BaseView {
    
    private ProfessorController controller = new ProfessorController();
    private BoletimController boletimController = new BoletimController();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String nomeProfessorLogado;
    
    public ProfessorView(String nomeProfessor) { 
        super("Professor", "Menu Professor", "Painel do Professor",
              new String[]{"Início", "Registrar Aula e Presença", "Relatório de Frequência"});
        
        this.nomeProfessorLogado = nomeProfessor;
        
        addMenuItemListeners();
    }
    
    private void addMenuItemListeners() {
        List<JButton> botoes = getMenuButtons();

        if (botoes.size() > 2) {
            JButton btnInicio = botoes.get(0);
            JButton btnRegistrar = botoes.get(1);
            JButton btnRelatorio = botoes.get(2);
            
            btnInicio.addActionListener(e -> showDashboard());
            btnRegistrar.addActionListener(e -> showRegistroAulaPanel());
            btnRelatorio.addActionListener(e -> showRelatorioFrequenciaPanel());
        }
    }

    protected void showDashboard() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Painel do Professor");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_WHITE);
        contentPanel.add(new JLabel("Bem-vindo ao Painel do Professor"));
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void showRegistroAulaPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Registrar Aula e Presença");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel registroAulaPanel = createRegistroAulaPanel();
        mainContentPanel.add(registroAulaPanel, BorderLayout.CENTER);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    
    private JPanel createRegistroAulaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("REGISTRO DE AULA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);
        
        JLabel lblMateria = new JLabel("MATÉRIA:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblMateria, gbc);
        
        JComboBox<Materia> comboMateria = new JComboBox<>(controller.getMaterias().toArray(new Materia[0]));
        comboMateria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Materia) {
                    setText(((Materia) value).getNome());
                }
                return this;
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(comboMateria, gbc);

        JPanel alunosPanel = new JPanel(new GridLayout(0, 2, 15, 5));
        alunosPanel.setBackground(BACKGROUND_WHITE);
        alunosPanel.setBorder(BorderFactory.createTitledBorder("Alunos"));

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (String aluno : controller.getAlunos()) {
            JLabel lblAluno = new JLabel(aluno + ":");
            JCheckBox cbPresente = new JCheckBox("PRESENTE");
            cbPresente.setSelected(true);
            
            alunosPanel.add(lblAluno);
            alunosPanel.add(cbPresente);
            checkBoxes.add(cbPresente);
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.weighty = 1.0;
        panel.add(alunosPanel, gbc);

        JButton btnRegistrarAula = new JButton("REGISTRAR AULA");
        btnRegistrarAula.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarAula.setForeground(new Color(59, 89, 182));
        btnRegistrarAula.setBackground(BACKGROUND_WHITE);
        btnRegistrarAula.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 182), 1));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(btnRegistrarAula, gbc);
        
        btnRegistrarAula.addActionListener(e -> {
            Materia materiaSelecionada = (Materia) comboMateria.getSelectedItem();
            List<String> presencas = new ArrayList<>();
            for (int i = 0; i < controller.getAlunos().size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    presencas.add(controller.getAlunos().get(i));
                }
            }
            controller.registrarFrequencia(nomeProfessorLogado, materiaSelecionada, presencas);
            JOptionPane.showMessageDialog(this, "Aula registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            showDashboard();
        });
        
        return panel;
    }

    private void showRelatorioFrequenciaPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Relatório de Frequência dos Alunos");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel relatorioPanel = new JPanel();
        relatorioPanel.setLayout(new BoxLayout(relatorioPanel, BoxLayout.Y_AXIS));
        relatorioPanel.setBackground(BACKGROUND_WHITE);
        relatorioPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblAlunosTitulo = new JLabel("Frequência por Aluno");
        lblAlunosTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblAlunosTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        relatorioPanel.add(lblAlunosTitulo);
        relatorioPanel.add(Box.createVerticalStrut(15));
        
        List<String> alunos = controller.getAlunos();
        for (String aluno : alunos) {
            Boletim boletim = boletimController.getBoletimPorNome(aluno);
            if (boletim != null) {
                BoletimView boletimView = new BoletimView();
                JPanel alunoBoletimPanel = boletimView.createPanel(boletim);
                
                alunoBoletimPanel.setBorder(BorderFactory.createTitledBorder(aluno));
                alunoBoletimPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alunoBoletimPanel.getPreferredSize().height));
                
                relatorioPanel.add(alunoBoletimPanel);
                relatorioPanel.add(Box.createVerticalStrut(20));
            }
        }
        
        mainContentPanel.add(new JScrollPane(relatorioPanel), BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
}