package br.com.sipna.dao;

import br.com.sipna.model.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT nome FROM disciplinas";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                materias.add(new Materia(rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public List<String> listarAlunos() {
        List<String> alunos = new ArrayList<>();
        String sql = "SELECT username FROM usuarios WHERE perfil = 'ALUNO'";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                alunos.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
    
    public void registrarFrequencia(String nomeProfessor, String nomeMateria, List<String> presencasAlunos) {
        int professorId = getUsuarioId(nomeProfessor);
        int disciplinaId = getDisciplinaId(nomeMateria);
        
        if (professorId == -1 || disciplinaId == -1) {
            System.err.println("Erro: Professor ou Matéria não encontrados.");
            return;
        }

        String sqlAula = "INSERT INTO aulas (disciplina_id, data_aula, professor_id) VALUES (?, NOW(), ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtAula = conn.prepareStatement(sqlAula, Statement.RETURN_GENERATED_KEYS)) {
            
            stmtAula.setInt(1, disciplinaId);
            stmtAula.setInt(2, professorId);
            stmtAula.executeUpdate();
            
            ResultSet rsKeys = stmtAula.getGeneratedKeys();
            if (rsKeys.next()) {
                int aulaId = rsKeys.getInt(1);
                
                String sqlFrequencia = "INSERT INTO frequencia (aluno_id, aula_id, presente) VALUES (?, ?, ?)";
                try (PreparedStatement stmtFrequencia = conn.prepareStatement(sqlFrequencia)) {
                    
                    List<Integer> todosAlunosIds = getAlunosIds();
                    
                    for (Integer alunoId : todosAlunosIds) {
                        String alunoUsername = getUsernameById(alunoId);
                        boolean presente = presencasAlunos.contains(alunoUsername);
                        
                        stmtFrequencia.setInt(1, alunoId);
                        stmtFrequencia.setInt(2, aulaId);
                        stmtFrequencia.setBoolean(3, presente);
                        stmtFrequencia.addBatch();
                    }
                    stmtFrequencia.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int getUsuarioId(String username) {
        String sql = "SELECT id FROM usuarios WHERE username = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    private String getUsernameById(int id) {
        String sql = "SELECT username FROM usuarios WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getDisciplinaId(String nome) {
        String sql = "SELECT id FROM disciplinas WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    private List<Integer> getAlunosIds() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM usuarios WHERE perfil = 'ALUNO'";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
}