# Lista de Mercado (To-Do) — Spring Boot + Arquitetura Limpa

API REST simples para gerenciar uma lista de compras de mercado.

**Stack:** Java 21, Spring Boot 3.5, Spring Data JPA, Bean Validation, H2 (em memória).

## Estrutura

```
src/main/java/com/mercado/lista
├── domain/                      # Regras de negócio puras (sem Spring/JPA)
│   ├── model/ItemMercado.java
│   ├── enums/                   # Categoria, UnidadeMedida, StatusItem
│   ├── repository/              # Porta: ItemMercadoRepository (interface)
│   └── exception/               # ItemNaoEncontradoException, RegraNegocioException
├── application/                 # Casos de uso: um service por ação
│   ├── dto/ItemMercadoInput.java
│   └── service/
│       ├── CriarItemService.java
│       ├── ListarItensService.java
│       ├── BuscarItemPorIdService.java
│       ├── AtualizarItemService.java
│       ├── AlterarStatusItemService.java
│       └── DeletarItemService.java
├── infrastructure/persistence/  # Adaptador JPA
│   ├── entity/ItemMercadoEntity.java
│   ├── mapper/ItemMercadoEntityMapper.java
│   └── repository/              # ItemMercadoJpaRepository + ItemMercadoRepositoryImpl
└── presentation/                # Camada HTTP
    ├── controller/ItemMercadoController.java
    ├── request/                 # ItemMercadoRequest, AlterarStatusRequest
    ├── resource/                # ItemMercadoResource, ErroResource (respostas)
    └── handler/GlobalExceptionHandler.java
```

Regra de dependência: `presentation → application → domain ← infrastructure`.
O domínio não conhece nenhuma outra camada; a infraestrutura implementa a porta do domínio.

## Executar

```bash
mvn spring-boot:run
mvn test
```

Documentação interativa (Scalar): http://localhost:8080/scalar
Especificação OpenAPI (JSON): http://localhost:8080/v3/api-docs

Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:listamercado`, usuário `sa`).

## Endpoints

| Método | Rota                       | Ação                                           |
|--------|----------------------------|------------------------------------------------|
| POST   | `/api/itens`               | Criar item                                     |
| GET    | `/api/itens`               | Listar (filtros opcionais `status`, `categoria`) |
| GET    | `/api/itens/{id}`          | Buscar por id                                  |
| PUT    | `/api/itens/{id}`          | Atualizar item                                 |
| PATCH  | `/api/itens/{id}/status`   | Marcar como `COMPRADO` / `PENDENTE`            |
| DELETE | `/api/itens/{id}`          | Remover item                                   |

### Exemplos

```bash
curl -X POST localhost:8080/api/itens -H "Content-Type: application/json" \
  -d '{"nome":"Arroz","quantidade":5,"unidade":"KG","categoria":"MERCEARIA","observacao":"tipo 1"}'

curl "localhost:8080/api/itens?status=PENDENTE&categoria=MERCEARIA"

curl -X PATCH localhost:8080/api/itens/1/status -H "Content-Type: application/json" \
  -d '{"status":"COMPRADO"}'
```

### Enums

- **Categoria:** HORTIFRUTI, ACOUGUE, PADARIA, LATICINIOS, MERCEARIA, BEBIDAS, LIMPEZA, HIGIENE, OUTROS
- **UnidadeMedida:** UNIDADE, KG, GRAMA, LITRO, PACOTE, CAIXA, DUZIA
- **StatusItem:** PENDENTE, COMPRADO
