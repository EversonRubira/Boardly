# Boardly – Backend

**Boardly** é um projeto pessoal desenvolvido com o objetivo de praticar a aplicação da Clean Architecture em uma API REST construída com Java 17, Spring Boot e MongoDB.  
O sistema permite o gerenciamento de projetos e tarefas, com autenticação via JWT e estrutura modular orientada a boas práticas.

---

## 🎯 Objetivos do projeto

- Aplicar Clean Architecture de forma prática
- Praticar autenticação JWT com Spring Security
- Trabalhar com MongoDB e documentos embutidos
- Desenvolver uma API REST organizada, testável e escalável
- Preparar base para frontend moderno (React + Tailwind)

---

## ⚙️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data MongoDB
- Maven
- IntelliJ IDEA
- HTTP Client da IDE (`API.http`)

---

## 🧱 Organização da arquitetura (Clean Architecture)

```
backend/
├── adapters/
│   ├── in/web/controller       # Controllers REST
│   ├── in/web/dto              # DTOs
│   ├── out/mongo/document      # Documentos MongoDB
│   ├── out/mapper              # Conversores
│   └── out/ProjectRepositoryAdapter.java
├── application/
│   ├── usecase                 # Interfaces dos casos de uso
│   └── service                 # Implementações
├── domain/
│   ├── model                   # Entidades puras
│   ├── common                  # Enums e utilitários
│   └── repository              # Ports (interfaces)
├── config/                     # Beans, JWT, segurança
└── resources/                  # application.properties etc.
```

---

## 🔐 Autenticação

- Endpoint de login: `POST /api/auth/login`
- JWT gerado e validado via `JwtAuthenticationFilter`
- Rotas protegidas exigem header:

```
Authorization: Bearer <seu_token_jwt>
```

---

## 📝 Funcionalidades implementadas

### Projetos (`/api/projects`)
- Criar, listar, buscar por ID, atualizar e deletar
- Campos: título, descrição, data de início/fim
- Lista de tarefas embutida no projeto

### Tarefas (`/api/tasks`)
- Criar tarefa vinculada a um projeto
- Atualizar status da tarefa (`To Do`, `Doing`, `Done`)
- Rotas protegidas por JWT

---

## 🧪 Como testar

1. Inicie a aplicação com:

```bash
cd backend
./mvnw spring-boot:run
```

2. Use o arquivo `API.http` na IDE para testar:
   - Login (`/api/auth/login`)
   - Criação de projetos
   - Criação e movimentação de tarefas

3. Copie o token JWT da resposta de login e use nas rotas protegidas:

```
Authorization: Bearer <token>
```

---

## 📌 Observações finais

Este projeto está em construção contínua.  
Foco principal: aprendizado, boas práticas e estrutura profissional de backend.  
Será evoluído com dashboards, frontend em React e deploy completo.

Repositório mantido por [Everson Rubira](https://github.com/EversonRubira).

