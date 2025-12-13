## 🛒 Desafio Técnico – API de Produtos (Grupo Mateus)

Este projeto implementa uma API REST para gerenciamento de produtos, desenvolvida em Java 21 com Spring Boot, seguindo Arquitetura Hexagonal (Ports & Adapters), DAO + Factory Pattern, testes unitários completos, observabilidade basica como log na aplicação e boas práticas de design.

---

### 📌 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Arquitetura Hexagonal
- DAO Pattern
- Factory Pattern
- MapStruct
- Lombok
- H2 Database (em memória)
- JUnit 5
- Mockito
- MockMvc
- Maven
- SLF4J (para fazer observabilidade basica)
- YAML Configuration
- Postman Collection
- Swagger

---

### 📐 Arquitetura do Projeto

O projeto segue o modelo de Arquitetura Hexagonal, separando responsabilidades da seguinte forma:

```
src/main/java
├── application
│   ├── dto
│   └── mapper
│
├── core
│   ├── domain
│   ├── ports
│   ├── usecase
│   └── exception
│
├── infrastructure
│   ├── controller
│   ├── dao
│   │   ├── factory
│   │   └── impl
│   ├── handler
│   ├── exception
│   └── config
```


#### Core
- Domínio puro
- Não depende de Spring
- Contém entidades, ports, use cases e regras de negócio
- Contém observabilidade basica no serviço.

#### Application
- Camada de entrada e saída
- DTOs
- Mappers (MapStruct)

#### Infrastructure
- Frameworks e detalhes técnicos
- Controllers REST
- DAO implementations
- Factory de DAOs
- Configurações Spring
- Handler global de exceções

---

### 🗄️ Persistência de Dados

- Implementação baseada em DAO Pattern
- Criação dos DAOs via Factory Pattern
- Banco H2 em memória
- Implementação atual usando ConcurrentHashMap
- Preparado para troca futura por JDBC ou outro provider

---

### ▶️ Como Executar o Projeto

#### Pré-requisitos

- Java 21 ou superior
- Maven 3.9 ou superior

#### Executar a aplicação

```bash
> git clone https://github.com/gleniomontovani/desafio-grupo-mateus.git
> cd desafio-grupo-mateus
> ./mvnw clean install
> ./mvnw spring-boot:run
```

A aplicação estará disponível em:

[http://localhost:8080](http://localhost:8080)

### 📮 Endpoints Disponíveis

| Método | Endpoint       | Descrição     |
| ------ | -------------- | ------------- |
| POST   | /products      | Criar produto |
| GET    | /products      | Listar todos  |
| GET    | /products/{id} | Buscar por ID |
| PUT    | /products/{id} | Atualizar     |
| DELETE | /products/{id} | Remover       |


### ❌ Tratamento de Erros
Os erros são tratados globalmente via ControllerAdvice.

Exemplo de resposta de erro:

```json
{
"status": 404,
"message": "Product not found with id: 99"
}
```

### 📌 Como testar a aplicação:

Após fazer o clone do projeto para sua maquina e rodar a aplicação, você pode testar das seguintes formas:

#### 📬 Postman Collection
Uma collection do Postman está disponível na raiz do projeto com o nome:

```pgsql
desafio-grupo-mateus_collection.json
```

Importe no Postman para testar todos os endpoints.


#### 🎯 Swagger

Acesse o swagger pelo link abaixo e realizar os testes:

[API Swagger - Desafio Grupo Mateus](http://localhost:8080/swagger-ui.html)



### 👨‍💻 Autor

Glenio Montovani <br/>
Desenvolvedor Java | Arquitetura de Software


🔗 **Repositório:**

[GitHub](https://github.com/gleniomontovani)