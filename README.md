# E-commerce API

API REST para um sistema de e-commerce, desenvolvida com Spring Boot. Permite o cadastro de usuários e a criação/consulta de pedidos, com produtos e tags já pré-cadastrados via seed do banco.

Repositório: [github.com/caioloreis/e-commerce](https://github.com/caioloreis/e-commerce)

## Tecnologias

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Data JPA**
- **Spring Web (MVC)**
- **PostgreSQL**
- **Maven** (com Maven Wrapper)
- **Docker Compose** (para subir o banco de dados localmente)

## Estrutura do projeto

```
src/main/java/devcaio/ecommerce
├── controller/     # Endpoints REST (OrderController, UserController)
├── dto/            # Objetos de transferência de dados (records)
├── entity/         # Entidades JPA mapeadas para o banco
├── exception/       # Exceções de domínio
├── repository/     # Interfaces Spring Data JPA
├── service/        # Regras de negócio (OrderService, UserService)
└── EcommerceApplication.java
```

### Modelo de domínio

- **UserEntity** — usuário, com endereço de cobrança associado (1:1)
- **BillingAddressEntity** — endereço de cobrança
- **ProductEntity** — produtos disponíveis para venda, com tags (N:N)
- **TagEntity** — categorias/etiquetas de produtos
- **OrderEntity** — pedidos, associados a um usuário
- **OrderItemEntity** / **OrderItemId** — itens de um pedido (chave composta produto + pedido)

## Como rodar o projeto

### Pré-requisitos

- JDK 21+
- Docker e Docker Compose (para o banco de dados)

### 1. Subir o banco de dados

```bash
cd docker
docker compose up -d
```

Isso sobe um PostgreSQL na porta `5432` com o banco `ecommercedb` já configurado (usuário `myuser`, senha `secret`).

### 2. Rodar a aplicação

Na raiz do projeto:

```bash
./mvnw spring-boot:run
```

*(no Windows, use `mvnw.cmd spring-boot:run`)*

A aplicação sobe por padrão em `http://localhost:8080`.

> O schema é atualizado automaticamente (`spring.jpa.hibernate.ddl-auto=update`) e o `data.sql` popula alguns produtos e tags de exemplo a cada start:
> - Produtos: Computer, Smartphone, Mouse
> - Tags: Eletronics, Home, Apple

## Endpoints disponíveis

### Usuários (`/users`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/users` | Cria um novo usuário com endereço de cobrança |
| `GET` | `/users/{userId}` | Busca um usuário pelo ID |
| `DELETE` | `/users/{userId}` | Remove um usuário pelo ID |

**Exemplo — `POST /users`:**
```json
{
  "fullName": "Maria Silva",
  "address": "Rua das Flores, 123",
  "number": "123",
  "complement": "Apto 45"
}
```

### Pedidos (`/orders`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/orders` | Cria um novo pedido para um usuário |
| `GET` | `/orders` | Lista pedidos, paginado |
| `GET` | `/orders/{orderId}` | Busca um pedido pelo ID, com itens e produtos |

**Exemplo — `POST /orders`:**
```json
{
  "userId": "41e84462-a025-4b2e-80fd-e4c372387d33",
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}
```

**Exemplo — `GET /orders?page=0&pageSize=10`:**
```json
{
  "data": [
    {
      "orderId": 1,
      "orderDate": "2026-09-13T20:29:13.525",
      "userId": "41e84462-a025-4b2e-80fd-e4c372387d33",
      "total": 9001.00
    }
  ],
  "pagination": {
    "page": 0,
    "pageSize": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

**Exemplo — `GET /orders/{orderId}`:**
```json
{
  "id": 1,
  "total": 9001.00,
  "orderDate": "2026-09-13T20:29:13.525",
  "userId": "41e84462-a025-4b2e-80fd-e4c372387d33",
  "itens": [
    {
      "salePrice": 4500.50,
      "quantity": 2,
      "product": {
        "productId": 1,
        "productName": "Computer",
        "tags": [{ "tagId": 1, "name": "Eletronics" }]
      }
    }
  ]
}
```

## Regras de negócio implementadas

- Um pedido não pode ser criado sem itens (`CreateOrderException`).
- Cada item precisa referenciar um produto existente; caso contrário, o pedido é rejeitado.
- O total do pedido é calculado automaticamente a partir do preço e quantidade de cada item.
- A criação do pedido é transacional (`@Transactional`): se algo falhar no meio do processo, nada é persistido.

## Testes

```bash
./mvnw test
```

## Roadmap

- [ ] Endpoints de CRUD completo para produtos e tags
- [ ] Testes automatizados de integração para os fluxos de pedido
- [ ] Documentação interativa da API (OpenAPI/Swagger)
- [ ] Autenticação/autorização

## Licença

Defina aqui a licença do projeto (ex: MIT), se aplicável.
