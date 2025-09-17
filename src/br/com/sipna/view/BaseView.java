package br.com.sipna.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class BaseView extends JFrame {

    protected final Color PRIMARY_BLUE = new Color(30, 144, 255);
    protected final Color DARK_BLUE = new Color(25, 118, 210);
    protected final Color LIGHT_GRAY = new Color(245, 245, 245);
    protected final Color TEXT_WHITE = Color.WHITE;
    protected final Color BACKGROUND_WHITE = Color.WHITE; // <-- Linha adicionada

    protected JPanel mainContentPanel;
    protected JSplitPane splitPane;

    public BaseView(String title, String menuTitle, String mainPanelTitle, String[] menuItems) {
        setTitle("SIPNA - " + title);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel leftMenu = createLeftMenu(menuTitle, menuItems);
        
        mainContentPanel = createMainContentPanel(mainPanelTitle);
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftMenu, mainContentPanel);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(250);
        
        add(splitPane);
    }

    private JPanel createLeftMenu(String title, String[] menuItems) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BLUE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel lblTitle = new JLabel("SIPNA");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(5));
        
        JLabel lblSubTitle = new JLabel(title);
        lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubTitle.setForeground(new Color(220, 220, 220));
        lblSubTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblSubTitle);
        
        panel.add(Box.createVerticalStrut(30));

        for (String item : menuItems) {
            JButton button = createMenuItemButton(item);
            panel.add(button);
            panel.add(Box.createVerticalStrut(10));
        }

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton createMenuItemButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(TEXT_WHITE);
        button.setBackground(DARK_BLUE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(true);
                button.setBackground(PRIMARY_BLUE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(false);
                button.setBackground(DARK_BLUE);
            }
        });

        return button;
    }

    private JPanel createMainContentPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel lblHeaderTitle = new JLabel(title);
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);

        JLabel lblUserIcon = new JLabel("👤");
        lblUserIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        headerPanel.add(lblUserIcon, BorderLayout.EAST);
        
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_WHITE);
        contentPanel.add(new JLabel("Bem-vindo ao Painel do " + title));

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }
}