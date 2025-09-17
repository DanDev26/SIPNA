package br.com.sipna.view;

import javax.swing.*;

public class AdminView extends BaseView {
    public AdminView() {
        super("Admin", "Menu Admin", "Painel do Administrador", 
            new String[]{"Dashboard", "Usuários", "Alunos e Escolas", "Relatórios"});
        
        // EVITAR REPETIÇAO DE CODIGO
        // JPanel adminContent = new JPanel();
        // this.mainContentPanel.removeAll();
        // this.mainContentPanel.add(adminContent, BorderLayout.CENTER);
        // this.mainContentPanel.revalidate();
        // this.mainContentPanel.repaint();
    }
}