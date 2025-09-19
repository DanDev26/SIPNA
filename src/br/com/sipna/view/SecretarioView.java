package br.com.sipna.view;

import br.com.sipna.controller.AdminController;
import br.com.sipna.controller.BoletimController;
import br.com.sipna.dao.UsuarioDAO;
import br.com.sipna.model.Boletim;
import br.com.sipna.model.Perfil;
import br.com.sipna.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SecretarioView extends BaseView {

    private AdminController controller = new AdminController();
    private BoletimController boletimController = new BoletimController();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public SecretarioView() {
        super("Secretário", "Menu Secretário", "Painel do Secretário",
              new String[]{"Início", "Adicionar Usuário", "Alunos e Escolas", "Relatórios"});

        addMenuItemListeners();
    }

    private void addMenuItemListeners() {
        List<JButton> botoes = getMenuButtons();

        if (botoes.size() > 0) {
            JButton btnInicio = botoes.get(0);
            JButton btnAdicionar = botoes.get(1);
            JButton btnAlunos = botoes.get(2);
            JButton btnRelatorios = botoes.get(3);

            btnInicio.addActionListener(e -> showDashboard());
            btnAdicionar.addActionListener(e -> showRegistroUsuarioPanel());
            btnAlunos.addActionListener(e -> showAlunosListPanel());
            btnRelatorios.addActionListener(e -> showRelatoriosPanel());
        }
    }

    private void showRegistroUsuarioPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Registrar Usuário");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel registroUsuarioPanel = createRegistroUsuarioPanel();
        mainContentPanel.add(registroUsuarioPanel, BorderLayout.CENTER);
        
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    
    private JPanel createRegistroUsuarioPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_WHITE);
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("CADASTRAR PROFESSOR OU ALUNO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);
        
        JLabel lblNome = new JLabel("NOME:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblNome, gbc);
        
        JTextField txtNome = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtNome, gbc);
        
        JLabel lblIdade = new JLabel("SENHA:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblIdade, gbc);
        
        JTextField txtIdade = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtIdade, gbc);

        JLabel lblPermissao = new JLabel("PERMISSÃO:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblPermissao, gbc);

        JCheckBox cbProfessor = new JCheckBox("PROFESSOR");
        JCheckBox cbAluno = new JCheckBox("ALUNO");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(cbProfessor);
        grupo.add(cbAluno);

        JPanel checkPanel = new JPanel();
        checkPanel.setBackground(BACKGROUND_WHITE);
        checkPanel.add(cbProfessor);
        checkPanel.add(new JLabel("|"));
        checkPanel.add(cbAluno);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(checkPanel, gbc);

        JButton btnCadastrar = new JButton("CADASTRAR USUARIO");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setForeground(new Color(59, 89, 182));
        btnCadastrar.setBackground(BACKGROUND_WHITE);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 182), 1));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(30, 10, 10, 10);
        panel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String senha = txtIdade.getText();
                
                Perfil perfil = null;
                if (cbProfessor.isSelected()) {
                    perfil = Perfil.PROFESSOR;
                } else if (cbAluno.isSelected()) {
                    perfil = Perfil.ALUNO;
                } else {
                    throw new IllegalArgumentException("Selecione um perfil (Professor ou Aluno).");
                }
                
                controller.adicionarUsuario(nome, senha, perfil);
                
                JOptionPane.showMessageDialog(this, "Usuário " + nome + " (" + perfil + ") cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                txtNome.setText("");
                txtIdade.setText("");
                grupo.clearSelection();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void showAlunosListPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Alunos e Escolas");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        AlunosListView alunosListView = new AlunosListView(mainContentPanel, boletimController);
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        mainContentPanel.add(alunosListView.createPanel(usuarios), BorderLayout.CENTER);
        
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    
    private void showRelatoriosPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Relatórios");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel relatorioPanel = new JPanel(new GridBagLayout());
        relatorioPanel.setBackground(BACKGROUND_WHITE);
        relatorioPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel lblRelatorioTitulo = new JLabel("Relatórios de Frequência Geral");
        lblRelatorioTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 20, 10);
        relatorioPanel.add(lblRelatorioTitulo, gbc);

        JPanel alunosRelatorioPanel = new JPanel();
        alunosRelatorioPanel.setLayout(new BoxLayout(alunosRelatorioPanel, BoxLayout.Y_AXIS));
        alunosRelatorioPanel.setBackground(BACKGROUND_WHITE);
        
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getPerfil() == Perfil.ALUNO) {
                Boletim boletim = boletimController.getBoletimPorNome(usuario.getUsername());
                BoletimView boletimView = new BoletimView();
                JPanel alunoBoletimPanel = boletimView.createPanel(boletim);
                
                alunoBoletimPanel.setBorder(BorderFactory.createTitledBorder(usuario.getUsername()));
                alunoBoletimPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alunoBoletimPanel.getPreferredSize().height));
                
                alunosRelatorioPanel.add(alunoBoletimPanel);
                alunosRelatorioPanel.add(Box.createVerticalStrut(20));
            }
        }
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        relatorioPanel.add(new JScrollPane(alunosRelatorioPanel), gbc);

        mainContentPanel.add(relatorioPanel, BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
}