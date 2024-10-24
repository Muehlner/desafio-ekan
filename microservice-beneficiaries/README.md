# Microservice Beneficiaries

## Descrição
Este projeto é um microserviço desenvolvido em Java com Spring Boot, responsável pela gestão de beneficiários e seus documentos. A aplicação permite o cadastro, atualização, listagem e remoção de beneficiários e seus documentos associados. A segurança é garantida através do Spring Security, que implementa autenticação básica.

## Tecnologias Utilizadas
- **Java**: 17
- **Spring Boot**: 3.3.4
- **Spring Security**: 6.x
- **Spring Data JPA**: para a interação com o banco de dados
- **H2 Database**: banco de dados em memória para desenvolvimento e testes
- **Lombok**: para redução de código boilerplate
- **Swagger**: para documentação da API

## Estrutura do Projeto
- **src/main/java/br/com/ekan**: Pacote principal contendo as classes da aplicação.
    - **config**: Configurações de segurança e outros beans.
    - **controller**: Controladores REST para gerenciar as requisições.
    - **dto**: Objetos de transferência de dados.
    - **entity**: Entidades JPA representando as tabelas do banco de dados.
    - **repository**: Interfaces para interação com o banco de dados.
    - **service**: Lógica de negócios e manipulação dos dados.

## Instruções para Execução

### Pré-requisitos
- JDK 17 instalado.
- Maven 3.x instalado.

### Executando o Build
1. **Clone o repositório:**
   ```bash
    git clone https://github.com/Muehlner/desafio-ekan
    cd microservice-beneficiaries
    mvn clean install
    mvn spring-boot:run

### Acessando a Aplicação
- A aplicação será iniciada na porta 9090.
- Para acessar a documentação da API: http://localhost:9090/swagger-ui/index.html

### Testando a API
- Use ferramentas como Postman ou cURL para interagir com a API.
- Credenciais de acesso (para autenticação básica):
  - #### Username: user
  - #### Password: password