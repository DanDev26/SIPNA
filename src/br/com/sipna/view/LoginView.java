package br.com.sipna.view;

import br.com.sipna.controller.LoginController;
import br.com.sipna.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginView extends JFrame {
    private final LoginController controller = new LoginController();
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginView() {
        setTitle("SIPNA - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(59, 89, 182)); // Azul
        leftPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 0, 10, 0);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        gbcLeft.gridwidth = GridBagConstraints.REMAINDER;
        
        JLabel lblLogo = new JLabel("SIPNA", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 36));
        lblLogo.setForeground(Color.WHITE);
        gbcLeft.gridy = 0;
        leftPanel.add(lblLogo, gbcLeft);

        gbcLeft.insets = new Insets(20, 0, 5, 0);
        JLabel lblUsername = new JLabel("Usuário:", SwingConstants.LEFT);
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setForeground(Color.WHITE);
        gbcLeft.gridy = 1;
        leftPanel.add(lblUsername, gbcLeft);

        txtUsername = new JTextField(15);
        txtUsername.setBackground(new Color(59, 89, 182));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcLeft.gridy = 2;
        leftPanel.add(txtUsername, gbcLeft);

        gbcLeft.insets = new Insets(15, 0, 5, 0);
        JLabel lblPassword = new JLabel("Senha:", SwingConstants.LEFT);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setForeground(Color.WHITE);
        gbcLeft.gridy = 3;
        leftPanel.add(lblPassword, gbcLeft);

        txtPassword = new JPasswordField(15);
        txtPassword.setBackground(new Color(59, 89, 182));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcLeft.gridy = 4;
        leftPanel.add(txtPassword, gbcLeft);

        gbcLeft.insets = new Insets(30, 0, 10, 0);
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(59, 89, 182));
        btnLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnLogin.setFocusPainted(false);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 5;
        gbcLeft.gridwidth = 2;
        leftPanel.add(btnLogin, gbcLeft);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                Usuario user = controller.autenticar(username, password);

                if (user != null) {
                    abrirTelaPorPerfil(user);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(240, 240, 240));
        
        JLabel lblBigS = new JLabel("S", SwingConstants.CENTER);
        lblBigS.setFont(new Font("Arial", Font.BOLD, 150));
        lblBigS.setForeground(new Color(59, 89, 182));
        rightPanel.add(lblBigS);
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }
    
    private void abrirTelaPorPerfil(Usuario user) {
        SwingUtilities.invokeLater(() -> {
            switch (user.getPerfil()) {
                case ADMIN:
                    new AdminView().setVisible(true);
                    break;
                case PROFESSOR:
                    new ProfessorView(user.getUsername()).setVisible(true);
                    break;
                case ALUNO:
                    new AlunoView(user.getUsername()).setVisible(true);
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