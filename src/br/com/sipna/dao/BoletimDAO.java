package br.com.sipna.dao;

import br.com.sipna.model.Boletim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoletimDAO {
    
    public Boletim getBoletimPorNome(String nomeAluno) {
        Boletim boletim = new Boletim(nomeAluno);
        
        String sql = "SELECT d.nome AS disciplina, " +
                     "COUNT(f.presente) AS total_aulas, " +
                     "SUM(CASE WHEN f.presente = TRUE THEN 1 ELSE 0 END) AS presencas " +
                     "FROM frequencia f " +
                     "JOIN aulas a ON f.aula_id = a.id " +
                     "JOIN disciplinas d ON a.disciplina_id = d.id " +
                     "JOIN usuarios u ON f.aluno_id = u.id " +
                     "WHERE u.username = ? " +
                     "GROUP BY d.nome";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nomeAluno);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String disciplina = rs.getString("disciplina");
                int totalAulas = rs.getInt("total_aulas");
                int presencas = rs.getInt("presencas");
                
                if (totalAulas > 0) {
                    double porcentagemFrequencia = (double) presencas / totalAulas * 100;
                    boletim.adicionarFrequencia(disciplina, porcentagemFrequencia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boletim;
    }
}