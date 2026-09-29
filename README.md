# Catálogo de Clientes

Microservicio independiente basado en Spring Boot y Java 21, con API REST, Spring Data JPA y PostgreSQL.

## Requisitos

- Java 17 o superior (el proyecto está configurado con Java 21).
- Docker Compose para iniciar PostgreSQL localmente.

## Inicio local

```sh
docker compose up -d postgres
./mvnw spring-boot:run
```

En Windows, usa `mvnw.cmd spring-boot:run`. La API queda disponible en `http://localhost:8081`.

La conexión usa estos valores por defecto, alineados con `compose.yaml`:

| Variable | Predeterminado |
| --- | --- |
| `SERVER_PORT` | `8081` |
| `DB_URL` | `jdbc:postgresql://localhost:5432/clientes` |
| `DB_USERNAME` | `clientes` |
| `DB_PASSWORD` | `clientes` |

Configura esas variables para apuntar a otro entorno. Hibernate actualiza el esquema local con `ddl-auto=update`.

## API de clientes

- `POST /api/clientes` crea un cliente (`nombre`, `email`).
- `GET /api/clientes` lista clientes.
- `GET /api/clientes/{id}` obtiene un cliente.
- `PUT /api/clientes/{id}` actualiza nombre y correo.
- `DELETE /api/clientes/{id}` elimina un cliente.
