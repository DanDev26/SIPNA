package br.com.sipna.view;

import br.com.sipna.controller.LoginController;
import br.com.sipna.model.Usuario;
import br.com.sipna.model.Perfil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel lblMensagem;
    private LoginController loginController = new LoginController();

    private final Color PRIMARY_BLUE = new Color(30, 144, 255);
    private final Color TEXT_WHITE = Color.WHITE;
    private final Color BACKGROUND_WHITE = Color.WHITE;
    private final Color MESSAGE_RED = new Color(220, 20, 60);

    public LoginView() {
        setTitle("SIPNA - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // --- Lado Esquerdo (Azul - Login) ---
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(PRIMARY_BLUE);
        leftPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 0, 10, 0);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        gbcLeft.gridwidth = GridBagConstraints.REMAINDER;
        
        JLabel lblLogo = new JLabel("SIPNA", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 36));
        lblLogo.setForeground(TEXT_WHITE);
        gbcLeft.gridy = 0;
        leftPanel.add(lblLogo, gbcLeft);

        gbcLeft.insets = new Insets(20, 0, 5, 0);
        JLabel lblUsuario = new JLabel("Usuário", SwingConstants.LEFT);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setForeground(TEXT_WHITE);
        gbcLeft.gridy = 1;
        leftPanel.add(lblUsuario, gbcLeft);

        txtUsuario = new JTextField(15);
        txtUsuario.setBackground(PRIMARY_BLUE);
        txtUsuario.setForeground(TEXT_WHITE);
        txtUsuario.setCaretColor(TEXT_WHITE);
        txtUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, TEXT_WHITE));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcLeft.gridy = 2;
        leftPanel.add(txtUsuario, gbcLeft);

        gbcLeft.insets = new Insets(15, 0, 5, 0);
        JLabel lblSenha = new JLabel("Senha", SwingConstants.LEFT);
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSenha.setForeground(TEXT_WHITE);
        gbcLeft.gridy = 3;
        leftPanel.add(lblSenha, gbcLeft);

        txtSenha = new JPasswordField(15);
        txtSenha.setBackground(PRIMARY_BLUE);
        txtSenha.setForeground(TEXT_WHITE);
        txtSenha.setCaretColor(TEXT_WHITE);
        txtSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, TEXT_WHITE));
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcLeft.gridy = 4;
        leftPanel.add(txtSenha, gbcLeft);

        gbcLeft.insets = new Insets(30, 0, 10, 0);
        JButton btnEntrar = new JButton("Iniciar sessão");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setBackground(new Color(25, 118, 210));
        btnEntrar.setForeground(TEXT_WHITE);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setPreferredSize(new Dimension(txtUsuario.getPreferredSize().width, 40));
        gbcLeft.gridy = 5;
        leftPanel.add(btnEntrar, gbcLeft);

        lblMensagem = new JLabel("", SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 12));
        lblMensagem.setForeground(MESSAGE_RED);
        gbcLeft.gridy = 6;
        leftPanel.add(lblMensagem, gbcLeft);

        btnEntrar.addActionListener(e -> autenticar());
        txtSenha.addActionListener(e -> autenticar());

        // --- Lado Direito (Branco - Logo "S") ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(BACKGROUND_WHITE);

        JLabel lblBigS = new JLabel("S", SwingConstants.CENTER);
        lblBigS.setFont(new Font("Arial", Font.BOLD, 150));
        lblBigS.setForeground(PRIMARY_BLUE);
        rightPanel.add(lblBigS);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }

    private void autenticar() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());

        Usuario user = loginController.autenticar(usuario, senha);
        if (user != null) {
            abrirTelaPorPerfil(user.getPerfil());
            this.dispose();
        } else {
            lblMensagem.setText("Usuário ou senha inválidos!");
        }
    }

    private void abrirTelaPorPerfil(Perfil perfil) {
        SwingUtilities.invokeLater(() -> {
            switch (perfil) {
                case ADMIN:
                    new AdminView().setVisible(true);
                    break;
                case PROFESSOR:
                    new ProfessorView().setVisible(true);
                    break;
                case ALUNO:
                    new AlunoView().setVisible(true);
                    break;
                case SECRETARIO:
                    new SecretarioView().setVisible(true);
                    break;
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}