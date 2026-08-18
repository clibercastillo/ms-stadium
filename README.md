# ms-stadium

Microservicio de gestión de canchas sintéticas de fútbol. Valida tokens JWT emitidos por `ms-auth`.

## Stack

- Java 17 · Spring Boot 4.1.0
- Spring Security (validación de JWT)
- Spring Data JPA + PostgreSQL
- Swagger / OpenAPI (springdoc 3.0.3)
- Gradle
- Docker

## Requisitos

- JDK 17
- Docker (Postgres compartido con `ms-auth`)
- `ms-auth` corriendo, para generar el token de login

## Levantar local

```bash
docker compose up -d
./gradlew bootRun
```

App disponible en `http://localhost:8081`
Swagger UI en `http://localhost:8081/swagger-ui.html`

## Endpoints principales

| Método | Ruta                      | Descripción              | Auth |
|--------|---------------------------|---------------------------|------|
| GET    | `/api/stadiums`           | Listar todas las canchas | No   |
| GET    | `/api/stadiums/{id}`      | Obtener cancha por id     | No   |
| GET    | `/api/stadiums/city/{city}`| Buscar canchas por ciudad| No   |
| POST   | `/api/stadiums`           | Registrar cancha          | Sí   |
| PUT    | `/api/stadiums/{id}`      | Actualizar cancha         | Sí   |
| DELETE | `/api/stadiums/{id}`      | Eliminar cancha           | Sí   |

Rutas protegidas requieren header:
```
Authorization: Bearer <token>
```
El token se obtiene desde `ms-auth` (`POST /api/auth/login`).

## Variables de entorno

| Variable                | Descripción                              |
|--------------------------|-------------------------------------------|
| `SPRING_DATASOURCE_URL`  | URL de conexión a PostgreSQL              |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la BD                      |
| `SPRING_DATASOURCE_PASSWORD` | Password de la BD                     |
| `JWT_SECRET`             | Clave secreta para validar JWT — debe ser idéntica a la de `ms-auth` |

## Docker

```bash
docker build -t ms-stadium:local .
docker run -p 8081:8081 ms-stadium:local
```

## CI/CD

El pipeline en `.github/workflows/ci.yml` compila el proyecto y publica la imagen en GitHub Container Registry (`ghcr.io`) en cada push a `main` o `develop`.
