# 02 TRD: Technical Requirements

## Runtime
- Java 21
- Spring Boot 4.1.1
- Maven

## Backend
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Bean Validation
- Spring Security
- JWT
- Spring Boot Actuator
- OpenAPI/Swagger

## Database
PostgreSQL.

## UI
- Thymeleaf
- Tailwind CSS
- Vanilla JavaScript where possible
- GSAP for controlled motion
- Lucide icons

## Infrastructure
- Docker
- Docker Compose
- PostgreSQL container
- Redis container when Phase 10 begins

## Testing
- JUnit
- Mockito
- Spring Boot Test
- MockMvc
- Integration tests

## Build
Maven Wrapper is preferred so contributors do not need a globally installed Maven version.

## Configuration
Environment variables must hold secrets and environment-specific settings. Never commit passwords, JWT secrets, API keys, or production credentials.

## Coding conventions
- Constructor injection
- Small services
- Explicit DTOs
- RESTful naming
- No business logic in controllers
- No database access from controllers
- Transactions at service boundaries when needed
- Meaningful exceptions
- Consistent API error structure
