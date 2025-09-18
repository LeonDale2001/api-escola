# API Escola

Este projeto é uma API RESTful para gerenciamento de alunos, professores, turmas e inscrições, construída com Spring Boot e JPA.

## Configuração do Banco de Dados

O projeto utiliza **MySQL** como banco de dados. Para não expor usuário e senha no código, usamos variáveis de ambiente com um arquivo `.env`.

### 1. Criar o arquivo `.env`

Na raiz do projeto (`api-escola`), crie um arquivo chamado:

E adicione:

```env
DB_URL=jdbc:mysql://<HOST>:<PORT>/<NOME_DO_BANCO>?useSSL=false&serverTimezone=UTC
DB_USERNAME=<SEU_USUARIO>
DB_PASSWORD=<SUA_SENHA>

```