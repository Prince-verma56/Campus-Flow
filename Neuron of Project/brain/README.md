# CampusFlow Brain

A persistent planning and learning specification for the **Smart Campus Management System**.

This folder is intentionally similar to a project "brain": it contains the product requirements, technical decisions, architecture, data model, API contract, UI rules, security rules, testing strategy, environment rules, microtasks, and AI handoff rules.

## Current stack

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Spring Security + JWT
- Thymeleaf
- Tailwind CSS
- GSAP
- Docker
- Redis later
- JUnit / Mockito
- OpenAPI / Swagger

Spring Boot 4.1.1 is the stable version used in this plan. Official Spring documentation currently lists Spring Boot 4.1.1 as stable and states that it requires at least Java 17. Maven 3.6.3+ is supported.

Official references:
- https://spring.io/projects/spring-boot/
- https://docs.spring.io/spring-boot/
- https://docs.spring.io/spring-boot/system-requirements.html

## Important architecture decision

We are **not using Next.js, React, Node.js, or Express**.

The first complete version is a Spring Boot web application:
- Thymeleaf renders HTML pages.
- Spring MVC handles HTTP requests.
- REST endpoints expose JSON APIs.
- Tailwind handles styling.
- GSAP handles carefully selected browser animations.
- PostgreSQL stores application data.

This is a deliberate learning constraint.

## How to initialize the actual project

### 1. Check Java

```bash
java -version
```

Use Java 21.

### 2. Open your blank folder

Example Windows PowerShell:

```powershell
mkdir campus-flow
cd campus-flow
```

### 3. Generate the project

Use Spring Initializr at:

https://start.spring.io/

Choose:

```text
Project: Maven
Language: Java
Spring Boot: 4.1.1
Group: com.campusflow
Artifact: campus-flow
Name: CampusFlow
Package name: com.campusflow
Packaging: Jar
Java: 21
```

Initial dependencies:

```text
Spring Web
Thymeleaf
Spring Data JPA
PostgreSQL Driver
Validation
Spring Boot DevTools
```

Do **not** add Security, Redis, JWT, or random libraries on day one. They are introduced in the correct phases.

Download the generated ZIP, extract its contents into the blank project folder, then open that folder in the IDE.

### 4. Run the application

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux:

```bash
./mvnw spring-boot:run
```

Or build:

```bash
.\mvnw.cmd clean package
```

Then run the JAR:

```bash
java -jar target/campus-flow-0.0.1-SNAPSHOT.jar
```

The exact generated JAR filename may vary.

### 5. Verify

Open:

```text
http://localhost:8080
```

At first, a simple page is enough. We will build the premium UI later.

## Initial project shape

After Spring Initializr:

```text
campus-flow/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/campusflow/
│   │   │   └── CampusFlowApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── brain/
```

We will **not** manually create every future folder on day one. Folders are added when their concepts become necessary.

## Learning philosophy

Do not ask the AI:

> "Build the entire CampusFlow application."

Instead ask:

> "We are on Phase 1, Microtask 3. Read the brain files, inspect the current code, teach me the concept, then implement only this microtask."

The AI should explain every unfamiliar Spring concept before or alongside implementation.

## First milestone

The first milestone is intentionally tiny:

```text
Spring Boot starts
        ↓
GET /
        ↓
Thymeleaf page
        ↓
GET /api/v1/health
        ↓
JSON response
```

Once that works, Phase 1 is real and we move forward.
