# CampusFlow Brain: Master Rules

## Purpose
CampusFlow is a learning-first Smart Campus Management System built with Java + Spring Boot. The project is intentionally designed so the developer learns every architectural decision instead of accepting AI-generated code blindly.

## Non-negotiable stack
- Java 21 LTS
- Spring Boot 4.1.1
- Maven
- Spring MVC / REST
- Spring Data JPA + Hibernate
- PostgreSQL
- Spring Security + JWT
- Thymeleaf for server-rendered UI
- Tailwind CSS for styling
- GSAP for intentional motion and transitions
- Lucide icons or another lightweight icon set
- Redis, introduced only after the core system is understood
- Docker / Docker Compose
- JUnit + Mockito + Spring Boot Test
- OpenAPI / Swagger

Spring Boot 4.1.1 is the current stable line used by this project plan. Spring Boot 4.1.1 requires at least Java 17; Java 21 is selected for this project. Maven 3.6.3+ is supported. See the official Spring documentation referenced in README.md.

## Architecture rule
Do not introduce React, Next.js, Node.js, Express, or another frontend framework. The first complete application is a Spring Boot application with Thymeleaf UI and static CSS/JavaScript. This is deliberate: the goal is to understand how Java + Spring Boot can serve both the application and web interface.

## AI rules
1. AI is a pair programmer and teacher, not the owner of the project.
2. Before generating code, AI must identify the phase and task.
3. AI must explain why the code belongs in that layer.
4. AI must not invent dependencies, APIs, database columns, or files outside this brain.
5. AI must inspect existing files before changing them.
6. AI must preserve existing architecture unless a documented ADR changes it.
7. Prefer the smallest implementation that teaches the current concept.
8. Never add a library just because it makes the task easier.
9. Never silently change Spring Boot, Java, Maven, database, or security versions.
10. After implementation, AI must provide:
   - files changed
   - concepts learned
   - how to test
   - common failure modes
   - interview questions
11. If requirements are ambiguous, ask rather than hallucinate.
12. Do not mark a phase complete until its acceptance criteria pass.

## Learning rule
For every feature:
Concept -> Why -> Tiny example -> CampusFlow implementation -> Test -> Break/Fix -> Explain without AI.

## UI rule
The UI must feel like a deliberate premium product:
- strong visual hierarchy
- restrained color system
- excellent typography
- responsive layouts
- meaningful empty/loading/error states
- subtle GSAP transitions
- no random gradients, excessive glassmorphism, or animation everywhere
- no template-looking dashboard copied blindly
- animation must support hierarchy and feedback

## Scope rule
MVP first. Advanced features are layered later. Do not build microservices. CampusFlow starts as a modular monolith because that is easier to understand and is appropriate for the learning goal.

## Definition of done
A feature is complete only when:
- code compiles
- application starts
- relevant API/UI flow works
- validation/error paths are handled
- tests exist where appropriate
- README/brain is updated if architecture changed
- the learner can explain the implementation
