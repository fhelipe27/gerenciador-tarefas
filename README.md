# Gerenciador de Atividades App

Este é um projeto de um aplicativo de gerenciamento de atividades desenvolvido em Java com o framework Spring Boot. O projeto consiste em um sistema que permite o cadastro de tarefas e usuários, associando usuários a tarefas específicas.

## Documentação Swagger

### Para acessar a documentação da API com Swagger, acesse: http://localhost:8080/swagger-ui/index.html#/

## Configurações do Banco de Dados

As configurações do banco de dados estão definidas no arquivo `application.properties`. O projeto está configurado para usar o MySQL com as seguintes configurações:
- URL do banco de dados: jdbc:mysql://localhost:3306/gerenciador_tarefa
- Nome de usuário: root
- Senha: root
- Hibernate DDL Auto: update (o banco de dados será atualizado automaticamente com base nas entidades do projeto)

## Observações:

### Página HTML

Existe um arquivo HTML simples, `hello-world.html`, que exibe uma mensagem "Hello, World!" em um navegador da web quando acessado na raiz do aplicativo.

