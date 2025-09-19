package br.com.sipna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    // Configurações de conexão - ADAPTE COM AS SUAS CREDENCIAIS
    private static final String URL = "jdbc:mysql://localhost:3306/sipna_db";
    private static final String USER = "root";
    private static final String PASSWORD = "senha do usuário";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados.", e);
        }
    }
}
