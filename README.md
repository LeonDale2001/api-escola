# API Escola

Este projeto é uma API RESTful para gerenciamento de alunos, professores, turmas e inscrições, construída com Spring Boot e JPA.

## Configuração do Banco de Dados

O projeto utiliza **MySQL** como banco de dados. Para não expor usuário e senha no código, usamos variáveis de ambiente com um arquivo `.env`.

### 1. Criar o arquivo `.env`

Na raiz do projeto (`api-escola`), crie um arquivo chamado:

E adicione:

```env
DB_URL=jdbc:mysql://localhost:3306/api_escola?useSSL=false&serverTimezone=UTC
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```