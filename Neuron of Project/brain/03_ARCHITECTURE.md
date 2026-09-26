# 03 Architecture

## Architectural style
CampusFlow is a modular monolith using layered architecture.

```text
Browser
  |
  +--> Thymeleaf pages / static assets
  |
  +--> REST API
          |
          v
      Controller
          |
          v
        DTO
          |
          v
       Service
          |
          +--> Domain rules
          |
          v
      Repository
          |
          v
       JPA/Hibernate
          |
          v
      PostgreSQL
```

## Security flow

```text
Request
  |
  v
Spring Security filter chain
  |
  +--> validate JWT
  |
  v
Authentication
  |
  v
Authorization
  |
  v
Controller
```

## Package structure

```text
com.campusflow
├── CampusFlowApplication.java
├── config/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
│   ├── auth/
│   ├── student/
│   ├── faculty/
│   ├── course/
│   └── attendance/
├── mapper/
├── exception/
├── security/
├── notification/
└── util/
```

## Why layered architecture
Controllers deal with HTTP. Services own business rules. Repositories own persistence. DTOs control API boundaries. This separation makes the system easier to test and explain.

## Architecture boundary
Do not introduce a separate frontend repository unless the project requirements change. The learning target is Spring Boot end-to-end.
