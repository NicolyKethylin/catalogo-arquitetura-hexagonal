# Catálogo de Produtos

API REST para cadastro e consulta de produtos, desenvolvida com Java 21 e Spring
Boot. O projeto usa PostgreSQL para persistência e organiza o código seguindo
Arquitetura Hexagonal (Ports & Adapters).

## Tecnologias

- Java 21
- Spring Boot 4.1.1 e Spring MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven Wrapper

## Requisitos

- JDK 21
- PostgreSQL acessível pela aplicação

## Configuração

A configuração local do banco está em `src/main/resources/application.properties`.
Por padrão, a aplicação conecta ao PostgreSQL em `localhost:5432`, no banco
`postgres`, com o usuário `postgres`. Configure a senha sem adicioná-la ao
repositório, por exemplo, usando a variável de ambiente
`SPRING_DATASOURCE_PASSWORD`. As propriedades Spring também podem ser
substituídas com `SPRING_DATASOURCE_URL` e `SPRING_DATASOURCE_USERNAME`.

O Hibernate está configurado para atualizar o schema (`ddl-auto=update`) e
registrar SQL no log.

## Executar

No Windows, a partir da pasta do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

A API inicia por padrão em `http://localhost:8080`.

## API

### Cadastrar produto

`POST /produtos`

Corpo JSON:

```json
{
  "nome": "Teclado",
  "valor": 120.00
}
```

Retorna `201 Created` com o produto persistido. O nome deve estar preenchido e
o valor deve ser maior que zero; entradas inválidas retornam `400 Bad Request`.

### Listar produtos

`GET /produtos`

Retorna `200 OK` e uma lista de produtos. Quando não há produtos cadastrados,
retorna `404 Not Found`.

### Consultar produto por ID

`GET /produtos/{id}`

Retorna `200 OK` com o produto encontrado ou `404 Not Found` se o ID não existir.

Exemplo de resposta:

```json
{
  "idProduto": 1,
  "nome": "Teclado",
  "valor": 120.00
}
```

## Arquitetura

- `domain/model`: modelo de domínio e invariantes de produto, sem dependências
  de Spring ou JPA.
- `application/ports/in`: interfaces dos casos de uso oferecidos à aplicação.
- `application/ports/out`: interfaces para dependências externas, como
  persistência.
- `application/usecase`: implementação dos casos de uso de criação e consulta.
- `adapters/in/web`: controller REST, DTOs HTTP e tratamento de erros.
- `adapters/out/persistence`: adapter JPA, entidade de persistência e repositório
  Spring Data.
- `configuration`: configuração e wiring entre casos de uso e portas.
- `CatalogoApplication.java`: bootstrap do Spring Boot.

O controller depende das portas de entrada; os casos de uso usam a porta de
persistência; e o adapter JPA implementa essa porta. A entidade JPA permanece
separada do modelo de domínio.

## Testes

Para executar os testes:

```powershell
.\mvnw.cmd test
```

Os testes de casos de uso usam uma implementação em memória da porta de
persistência, sem exigir banco de dados ou contexto Spring.
