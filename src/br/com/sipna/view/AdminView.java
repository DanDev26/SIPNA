package br.com.sipna.view;

import br.com.sipna.controller.AdminController;
import br.com.sipna.controller.BoletimController;
import br.com.sipna.dao.UsuarioDAO;
import br.com.sipna.model.Perfil;
import br.com.sipna.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminView extends BaseView {

    private AdminController controller = new AdminController();
    private BoletimController boletimController = new BoletimController();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public AdminView() {
        super("Admin", "Menu Admin", "Painel do Administrador", 
            new String[]{"Início", "Usuários", "Alunos e Escolas"});
        
        addMenuItemListeners();
    }
    
    private void addMenuItemListeners() {
        List<JButton> botoes = getMenuButtons();
        
        if (botoes.size() > 0) {
            JButton btnInicio = botoes.get(0);
            JButton btnUsuarios = botoes.get(1);
            JButton btnAlunos = botoes.get(2);
            
            btnInicio.addActionListener(e -> showDashboard());
            btnUsuarios.addActionListener(e -> showUserSubMenu());
            btnAlunos.addActionListener(e -> showAlunosListPanel());
        }
    }
    
    private void showUserSubMenu() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Usuários");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel subMenuPanel = new JPanel();
        subMenuPanel.setLayout(new BoxLayout(subMenuPanel, BoxLayout.X_AXIS));
        subMenuPanel.setBackground(BACKGROUND_WHITE);
        subMenuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnRegistrar = new JButton("Registrar Usuário");
        styleSubMenuButton(btnRegistrar);
        btnRegistrar.addActionListener(e -> showRegistroUsuarioPanel());
        
        JButton btnListar = new JButton("Listar Usuários");
        styleSubMenuButton(btnListar);
        btnListar.addActionListener(e -> showListarUsuariosPanel());

        subMenuPanel.add(btnRegistrar);
        subMenuPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        subMenuPanel.add(btnListar);
        
        mainContentPanel.add(subMenuPanel, BorderLayout.CENTER);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    
    private void styleSubMenuButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(PRIMARY_BLUE);
        button.setBackground(BACKGROUND_WHITE);
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_BLUE, 1));
        button.setFocusPainted(false);
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
        
        JLabel lblTitulo = new JLabel("REGISTRAR USUÁRIO", SwingConstants.CENTER);
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
        
        JLabel lblSenha = new JLabel("SENHA:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblSenha, gbc);

        JPasswordField txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtSenha, gbc);
        
        JLabel lblPermissao = new JLabel("PERMISSÃO:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblPermissao, gbc);

        JComboBox<Perfil> comboPermissao = new JComboBox<>(Perfil.values());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(comboPermissao, gbc);

        JButton btnCadastrar = new JButton("CADASTRAR USUÁRIO");
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
                String senha = new String(txtSenha.getPassword());
                Perfil perfil = (Perfil) comboPermissao.getSelectedItem();
                
                controller.adicionarUsuario(nome, senha, perfil);
                
                JOptionPane.showMessageDialog(this, "Usuário " + nome + " cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                txtNome.setText("");
                txtSenha.setText("");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        return panel;
    }

    private void showListarUsuariosPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel("Lista de Usuários");
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.setBackground(BACKGROUND_WHITE);
        listaPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        List<Usuario> usuarios = new UsuarioDAO().listarTodos();
        
        for (Usuario user : usuarios) {
            JPanel userItem = createUsuarioItemPanel(user);
            listaPanel.add(userItem);
            listaPanel.add(Box.createVerticalStrut(5));
        }

        mainContentPanel.add(new JScrollPane(listaPanel), BorderLayout.CENTER);
        
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
    
    private JPanel createUsuarioItemPanel(Usuario user) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setBackground(new Color(240, 240, 240));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel lblInfo = new JLabel("<html><b>Usuário:</b> " + user.getUsername() + " | <b>Perfil:</b> " + user.getPerfil() + "</html>");
        itemPanel.add(lblInfo);
        
        return itemPanel;
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
        List<Usuario> usuarios = new UsuarioDAO().listarTodos();
        mainContentPanel.add(alunosListView.createPanel(usuarios), BorderLayout.CENTER);
        
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }
}