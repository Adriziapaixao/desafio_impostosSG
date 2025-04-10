# 📚 Minha API

Bem-vindo à documentação da **Minha API**! Este projeto foi desenvolvido fornece uma API RESTful para
 gerenciar e calcular impostos no Brasil. A API deve permitir o registro de diferentes tipos de impostos,
como ICMS, ISS, IPI, entre outros, e realizar cálculos com base no tipo de imposto e no
valor base fornecido. Além disso, a API deve ser segura, utilizando Spring Security e JWT
(JSON Web Token) para autenticação e autorização.


---

## 🚀 Tecnologias Utilizadas

- **Java jdk 17**: 
- **Spring Boot**: 
- **Maven**: Gerenciador de dependências.
- **JUnit**: Padrão de Testes Unitários.
- **JWT (JSON Web Token)**: Autenticação segura.
- **PostgresSql**: Banco de dados relacional.

---

## **Configurações de Banco de Dados e Servidor**

Certifique-se de configurar as variáveis de ambiente antes de iniciar a aplicação. Abaixo estão as configurações padrão que você pode usar como exemplo:

```env
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/imposto}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```
# **Funcionalidades da API**

A API oferece diversas funcionalidades para o gerenciamento de tipos de impostos, cálculo de impostos e segurança. Confira os detalhes abaixo:

---

## **1. Gerenciamento de Tipos de Impostos**
- **Lista todos os tipos de impostos disponíveis**:
  
- **Cadastra novos tipos de impostos**:
  
- **Obtem detalhes de um tipo de imposto específico pelo ID**:
  
- **Exclue um tipo de imposto pelo ID**:
  

---

## **2. Cálculo de Impostos**
- **Calcula o valor do imposto**:
 
---

## **3. Segurança**
A API implementa segurança robusta utilizando **Spring Security** e **JWT**. Confira os detalhes:

- **Autenticação e autorização**:
    - Apenas usuários autenticados podem acessar os endpoints.

    - Apenas usuários com o papel de **ADMIN** podem acessar os endpoints de:
        - Criação de tipos de impostos.
        - Exclusão de tipos de impostos.
        - Cálculo de impostos.

---

:lock: **Nota de Segurança**  
Certifique-se de que as credenciais de autenticação e o token JWT sejam mantidos em segurança. Apenas usuários autorizados devem ter acesso às operações sensíveis.

:rocket: **Pronto para começar?** Explore as rotas disponíveis e aproveite as funcionalidades da API!
## 📖 Endpoints Disponíveis

### **1. Gerenciamento de Impostos**
- `GET`  `/users`: Obtém a lista de todos os usuários cadastrados.
- `POST`  `/tipos`: Cadastra um novo tipo de imposto (Acesso restrito ao papel ADMIN).
- `GET`  `/tipos/{id}`: Retorna os detalhes de um tipo de imposto específico pelo ID.
- `DELETE` `/tipos/{id}`: Exclui um tipo de imposto pelo ID (Acesso restrito ao papel ADMIN).
### **2. Gerenciamento de Usuários**
- `POST` `/user/user/register`: Registra novos usuários no sistema.
- `POST` `/user/user/login`: Autentica usuários e gera um token JWT.
### **3. Cálculo Imposto**
- `POST` `/calculo`: Calcula o valor do imposto com base no tipo de imposto e no valor base (Acesso restrito ao papel ADMIN).

#### Exemplos:

- Requisição de login:
POST /user/login
```json
  {
  "username": "usuario123",
  "password": "senhaSegura"
  }
  ```

- Resposta(200 - OK):
```json
  {
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
```

- Cálculo do Imposto:
POST /calculo
```json
{
"tipoImpostoId": 1,
"valorBase": 1000.0
}
```
Resposta (200):
```json
{
"tipoImposto": "ICMS",
"valorBase": 1000.0,
"rate": 18.0,
"valorImposto": 180.0
}
```
- Cadastrar Imposto:
  POST /tipos
```json
{
"name": "IPI",
"description": "Imposto sobre Produtos Industrializados",
"rate": 12.0
}
```
Resposta (201):
```json
{
"id": 3,
"name": "IPI",
"description": "Imposto sobre Produtos Industrializados",
"rate": 12.0
}
```

## **Testes com JUnit**

A API utiliza o **JUnit** para garantir a qualidade e o funcionamento correto das funcionalidades. 

---

### **Como Executar os Testes**

Siga os passos abaixo para rodar os testes com o JUnit:

. Certifique-se de que todas as dependências estão instaladas:

    mvn test


📦 Containers Utilizados

Este projeto utiliza containers Docker para facilitar a configuração e execução do ambiente. Abaixo estão os detalhes dos containers utilizados:

. **PostgreSQL**
- **Imagem**: `postgres:latest`
- **Porta**: `5432`
- **Descrição**: Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Configurações**:
    - `POSTGRES_USER`: `postgres`
    - `POSTGRES_PASSWORD`: `postgres`
    - `POSTGRES_DB`: `imposto`

. **Aplicação Java**
- **Imagem Base**: `openjdk:17-jdk-slim`
- **Porta**: `8080`
- **Descrição**: Container responsável por executar a aplicação Spring Boot.
- **Configurações**:
    - Maven 3.8.8 instalado no container.
    - Dependências gerenciadas pelo Maven.
    - Variáveis de ambiente configuradas para conexão com o banco de dados:
        - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://db:5432/imposto`
        - `SPRING_DATASOURCE_USERNAME`: `postgres`
        - `SPRING_DATASOURCE_PASSWORD`: `postgres`
        - `SPRING_JPA_HIBERNATE_DDL_AUTO`: `update`

### Como Executar os Containers

Certifique-se de que o Docker e o Docker Compose estão instalados na sua máquina. Para iniciar os containers, execute o seguinte comando:

```bash
docker-compose up -d
```

## **Contribuição** 🤝

Contribuições são super bem-vindas! 🎉

Se você deseja ajudar a melhorar este projeto, siga os passos abaixo:

### **Como Contribuir**
. **Faça um Fork do Repositório**  
Clique no botão "Fork" no canto superior direito desta página para criar uma cópia do repositório no seu GitHub.

. **Clone o Repositório**  
Clone o repositório para a sua máquina local:

- Com HTTP:
```bash  
   git clone https://github.com/Adriziapaixao/desafio_impostosSG.git
```
- Com SSH:
```bash  
   git clone git@github.com:Adriziapaixao/desafio_impostosSG.git
```

### Dicas para Contribuir

Certifique-se de que seu código segue os padrões do projeto.
Adicione testes para novas funcionalidades ou correções de bugs.
Atualize a documentação, se necessário.

### Código de Conduta
Ao contribuir, você concorda em seguir nosso Código de Conduta.