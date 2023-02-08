# RH
Este projeto é um sistema de gerenciamento de funcionários, que visa não só fornecer uma solução para o cadastro e gestão de funcionários, mas também demonstrar meus conhecimentos em Java e Spring.

## Especificações

| Ferramenta  | Versão | Descrição                                                                                        |
|-------------|--------|--------------------------------------------------------------------------------------------------|
| Java        | 17     | Linguagem de programação OpenJDK                                                                 |
| Gradle      | 7.6    | Gerenciador de build e dependências, para facilitar a construção, teste e implantação do projeto |
| Spring Boot | 2.7.8  | Framework para criação de aplicações web de forma rápida e fácil                                 |
| Lombok      |        | Biblioteca que ajuda a reduzir o código repetitivo e aumentar a produtividade do desenvolvedor   |
| Security    |        | Biblioteca para gerenciar a segurança da aplicação                                               |
| Data JPA    |        | Biblioteca que facilita a busca e persistência de dados no banco de dados                        |
| H2 Database |        | Banco de dados em memória, para uso durante o desenvolvimento e testes                           |
| Actuator    |        | Ferramenta para monitorar e gerenciar a saúde, métricas e outros aspectos da aplicação           |
| Prometheus  |        | Sistema de monitoramento de métricas, para ajudar a controlar a performance e disponibilidade    |

## Pré-requisitos

Para instalar e executar esse projeto, você precisará dos seguintes pré-requisitos:

- Ter instalado na sua máquina o Java Development Kit (JDK) na versão 17 ou superior.
- Ter instalado na sua máquina o Gradle na versão 7.6 ou superior.
- Algum software para acessar APIs REST (Ex.: Postman)

## Instalação

Uma vez que você tenha os pré-requisitos instalados, siga os passos abaixo:

1. Clone ou faça o download do projeto em seu computador.  
    `git clone https://github.com/mauriciobelusso/rh.git`
2. Abra o terminal ou o prompt de comando e vá até o diretório do projeto.
3. Execute o comando gradle para baixar as dependências e compilar o projeto:  
    `gradle build`
4. Depois de compilado, digite o seguinte comando para executar o projeto:  
    `gradle bootRun`
5. Verifique se o projeto está rodando corretamente acessando a seguinte URL no seu navegador:   
    http://localhost:8080/rh/funcionarios.

Se tudo correr bem, você verá a página inicial do projeto.

## Uso

Com a aplicação iniciada, você poderá realizar as operações básicas de CRUD (Create, Read, Update e Delete) de funcionários.

Para testar as requisições de API, é recomendado o uso do POSTMAN. Seguem abaixo os passos para realizar as requisições:

Abra o POSTMAN

1. Selecione a requisição desejada (GET, POST, PUT, DELETE)
2. Insira a URL da API (Exemplo: http://localhost:8080/rh/funcionarios)
3. Selecione a aba "Authorization" e escolha a opção "Basic Auth"
4. Insira o nome de usuário e senha (o usuário é o "user" e a senha é gerada automaticamente e apresentada no console do server após iniciar)  
    ex: 
```
Using generated security password: c15ec4ae-24b9-48e8-b80a-5ace03152184

This generated password is for development use only. Your security configuration must be updated before running your application in production.
```
5. Clique em "Send"

### Criar um funcionário
Requisição HTTP: POST

URL: http://localhost:8080/rh/funcionarios

Corpo da requisição (em formato JSON):

```
{
    "nome": "Fulano",
    "sobrenome": "Ciclano",
    "email": "fulano.ciclano@email.com",
    "nis": "123456789"
}
```

### Listar todos os funcionários
Requisição HTTP: GET

URL: http://localhost:8080/rh/funcionarios

### Consultar um funcionário específico
Requisição HTTP: GET

URL: http://localhost:8080/rh/funcionarios/{id}

Onde {id} é o identificador único do funcionário que você deseja consultar.

### Atualizar um funcionário
Requisição HTTP: PUT

URL: http://localhost:8080/rh/funcionarios/{id}

Corpo da requisição (em formato JSON):
```
{
    "nome": "Beltrano",
    "sobrenome": "Ciclano",
    "email": "fulano.ciclano@email.com",
    "nis": "123456789"
}
```

Onde {id} é o identificador único do funcionário que você deseja atualizar.

### Excluir um funcionário
Requisição HTTP: DELETE

URL: http://localhost:8080/rh/funcionarios/{id}