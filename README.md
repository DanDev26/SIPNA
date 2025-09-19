# SIPNA - Sistema de Integração para Processos e Notificações Acadêmicas

## Status do Projeto
✅ Concluído e Funcional

---

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Swing**: Biblioteca para a construção da interface gráfica (GUI).
- **MySQL**: Sistema de gerenciamento de banco de dados relacional (SGBD).
- **JDBC**: API de conexão Java com o banco de dados.
- **NetBeans**: Ambiente de desenvolvimento integrado (IDE).

---

## Time de Desenvolvedores
- José Danilo

---

## Objetivo do Software
O SIPNA é uma plataforma que integra e gerencia processos acadêmicos de uma instituição de ensino. Ele foi desenvolvido para organizar atividades escolares e notificações, proporcionando uma ferramenta robusta para administradores, professores e alunos. O sistema garante o acesso seguro a dados, permitindo a gestão de usuários e a visualização de informações relevantes, como a frequência dos alunos, de forma centralizada e eficiente.

---

## Funcionalidades do Sistema
O sistema oferece diferentes funcionalidades dependendo do perfil do usuário:

### Para todos os perfis:
- **Login e autenticação**: Acesso seguro ao sistema com base no perfil de usuário.

### Perfil Administrador (`admin`):
- **Cadastro de usuários**: Criação de novas contas para professores, alunos, e secretários.
- **Listagem de usuários**: Visualização de todos os usuários cadastrados no sistema.
- **Relatórios de alunos**: Acesso a relatórios de frequência de todos os alunos.

### Perfil Professor (`prof`):
- **Registro de aulas e frequência**: Marcação de presença dos alunos por disciplina.
- **Relatório de frequência**: Visualização da frequência dos alunos para acompanhar o desempenho da turma.

### Perfil Secretário (`secretario`):
- **Cadastro de usuários**: Ferramenta para adicionar novos professores e alunos.
- **Visualização de alunos e escolas**: Listagem de todos os alunos cadastrados no sistema.
- **Relatórios de alunos**: Acesso a relatórios de frequência de todos os alunos.

### Perfil Aluno (`aluno`):
- **Consulta de boletim**: Visualização do seu próprio relatório de frequência por disciplina.

---

## Como Executar o Projeto
1.  **Configure o Banco de Dados**:
    * No MySQL Workbench, execute o script SQL fornecido para criar o banco de dados `sipna_db` e popular as tabelas.
    * No arquivo `br.com.sipna.dao.ConnectionFactory.java`, ajuste a variável `PASSWORD` com a senha do seu usuário `root` do MySQL.
2.  **Adicione o Driver JDBC**:
    * Adicione o arquivo `mysql-connector-j-x.x.x.jar` às bibliotecas do seu projeto no NetBeans.
3.  **Execute a Aplicação**:
    * Execute o arquivo `LoginView.java`. O sistema está pronto para ser utilizado com os dados de teste fornecidos.
