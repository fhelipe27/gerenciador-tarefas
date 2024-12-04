# Gerenciador de Tarefas

## Descrição do Projeto

O **Gerenciador de Tarefas** é uma aplicação desenvolvida em **Java Spring Boot** com **MySQL** para gerenciar atividades acadêmicas em grupo. A aplicação permite criar, editar, marcar como concluídas ou removidas, e também inclui funcionalidades avançadas como conquistas baseadas no progresso das tarefas. A interface do usuário foi construída com **HTML**, **CSS**, **Bootstrap**, e integrações via **Thymeleaf** para conectar o front-end ao back-end.

## Funcionalidades

1. **Gerenciamento de Tarefas**
   - Criar, editar, remover e restaurar tarefas.
   - Marcar tarefas como concluídas ou removidas.
   - Listar tarefas ativas, concluídas e removidas.

2. **Sistema de Conquistas**
   - Pontuação e conquistas para o grupo com base nas tarefas realizadas.
   - Conquistas específicas para marcos como:
     - "Primeiro Passo": primeira tarefa criada.
     - "Missão Cumprida": primeira tarefa concluída.
     - "Em Alta Performance": 10 tarefas concluídas.
     - "Mestre das Tarefas": 100 tarefas concluídas.
     - "Descarte Inteligente": primeira tarefa removida.
     - "Limpeza Definitiva": todas as tarefas removidas permanentemente.
     - "Aprimorando": primeira tarefa editada.

3. **Autenticação e Cadastro de Usuário**
   - Sistema de login e registro de usuários.
   - Sistema de criptografia para garantir a integridade e segurança dos dados.

4. **Serviços Auxiliares**
   - Página de conquistas do grupo.
   - Serviço de envio de e-mails para notificações.

5. **Configuração do Status das Tarefas**
   - BLOQUEADA, EM_ANDAMENTO, EM_ANALISE, RESOLVIDA, PENDENTE, AGUARDANDO_APROVACAO, REABERTA e NOVA.

## Estrutura do Projeto

### Camadas Principais
1. **Modelos**
   - `Tarefa`: Representa as tarefas do sistema.
   - `Grupo`: Representa os grupos acadêmicos.
   - `Conquista`: Representa as conquistas disponíveis.
   - `GrupoConquista`: Relaciona grupos às conquistas.

2. **Repositórios**
   - `TarefaRepository`: Manipulação de tarefas no banco de dados.
   - `ConquistaRepository`: Manipulação de conquistas.
   - `GrupoConquistaRepository`: Manipulação da relação entre grupos e conquistas.

3. **Serviços**
   - Contêm a lógica de negócio, como salvar tarefas, gerenciar conquistas e editar status.

4. **Utilitários**
   - `SessionUtilityBean`: Gerencia mensagens de sessão no front-end.

### Front-End
- Páginas HTML:
  - conquistas.html
  - contato.html
  - criar-atividade.html
  - editar-atividade.html
  - index.html
  - lista-atividades.html
  - lista-atividades-concluidas.html
  - lista-atividades-removidas.html
  - login.html
  - register.html
  - servicos.html
  - sobre.html
- Estilizadas com **CSS** e **Bootstrap**.
- Conectadas ao back-end via **Thymeleaf**.

# Como Executar

## Clone o repositório:

```bash
git clone https://github.com/seu-usuario/gerenciador-tarefas.git
```

## Configure o banco de dados: 

1. Crie um banco de dados MySQL com o nome `gerenciador_tarefa`.
2. Atualize as credenciais no arquivo `application.properties`.
  
## Execute a aplicação:
```bash
./mvnw spring-boot:run
```
## Acesse no navegador:

[http://localhost:8080](http://localhost:8080)

## Tecnologias Utilizadas

- **Back-End:** Java, Spring Boot, Hibernate, MySQL.
- **Front-End:** HTML, CSS, Bootstrap, Thymeleaf.
- **Ferramentas:** Maven, Lombok.

## Contribuições

Contribuições são bem-vindas! Siga os passos:

1. Faça um fork do projeto.
2. Crie um branch para sua feature ou correção:

    ```bash
    git checkout -b minha-feature
    ```

3. Commit suas alterações:

    ```bash
    git commit -m "Adiciona nova feature"
    ```

4. Envie o branch:

    ```bash
    git push origin minha-feature
    ```

5. Abra um Pull Request.


