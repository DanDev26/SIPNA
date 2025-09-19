DROP DATABASE IF EXISTS sipna_db;

CREATE DATABASE sipna_db;

USE sipna_db;

-- Tabela de usuários
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL
);

-- Tabela de disciplinas
CREATE TABLE disciplinas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela de aulas
CREATE TABLE aulas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disciplina_id INT NOT NULL,
    data_aula DATE NOT NULL,
    professor_id INT NOT NULL,
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id),
    FOREIGN KEY (professor_id) REFERENCES usuarios(id)
);

-- Tabela de frequência
CREATE TABLE frequencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    aula_id INT NOT NULL,
    presente BOOLEAN NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES usuarios(id),
    FOREIGN KEY (aula_id) REFERENCES aulas(id)
);



INSERT INTO usuarios (username, password, perfil) VALUES
('admin', '123', 'ADMIN'),
('secretario', '123', 'SECRETARIO'),
('prof', '123', 'PROFESSOR'),
('aluno1', '123', 'ALUNO'),
('aluno2', '123', 'ALUNO'),
('aluno3', '123', 'ALUNO'),
('aluno4', '123', 'ALUNO'),
('aluno5', '123', 'ALUNO');


INSERT INTO disciplinas (nome) VALUES
('Matemática'),
('Português'),
('História'),
('Geografia'),
('Eletiva');