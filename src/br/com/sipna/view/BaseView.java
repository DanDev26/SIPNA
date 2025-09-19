package br.com.sipna.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseView extends JFrame {

    protected static final Color PRIMARY_BLUE = new Color(59, 89, 182);
    protected static final Color BACKGROUND_WHITE = new Color(240, 240, 240);
    protected JPanel mainContentPanel;
    protected JPanel sidebarPanel;

    private String windowTitle;
    private String menuTitle;
    private String contentTitle;
    private String[] menuItems;
    private List<JButton> menuButtons = new ArrayList<>();

    public BaseView(String windowTitle, String menuTitle, String contentTitle, String[] menuItems) {
        this.windowTitle = windowTitle;
        this.menuTitle = menuTitle;
        this.contentTitle = contentTitle;
        this.menuItems = menuItems;

        initializeUI();
    }
    
    private void initializeUI() {
        setTitle(windowTitle);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        createSidebar();
        createMainContentPanel();
        
        showDashboard();
    }

    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(PRIMARY_BLUE);
        sidebarPanel.setLayout(new BorderLayout());
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));

        JPanel menuHeaderPanel = new JPanel();
        menuHeaderPanel.setBackground(PRIMARY_BLUE);
        JLabel menuTitleLabel = new JLabel(menuTitle, SwingConstants.CENTER);
        menuTitleLabel.setForeground(Color.WHITE);
        menuTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuHeaderPanel.add(menuTitleLabel);
        sidebarPanel.add(menuHeaderPanel, BorderLayout.NORTH);

        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBackground(PRIMARY_BLUE);
        menuItemsPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(PRIMARY_BLUE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
            menuItemsPanel.add(button);
            menuItemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            menuButtons.add(button);
        }
        
        sidebarPanel.add(menuItemsPanel, BorderLayout.CENTER);
        
        add(sidebarPanel, BorderLayout.WEST);
    }

    private void createMainContentPanel() {
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());
        add(mainContentPanel, BorderLayout.CENTER);
    }
    
    protected void showDashboard() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblHeaderTitle = new JLabel(contentTitle);
        lblHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblHeaderTitle, BorderLayout.WEST);
        mainContentPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_WHITE);
        contentPanel.add(new JLabel("Bem-vindo(a) ao " + windowTitle));
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    protected List<JButton> getMenuButtons() {
        return menuButtons;
    }
}