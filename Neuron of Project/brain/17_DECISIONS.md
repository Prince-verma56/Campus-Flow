# 17 Architecture Decision Records

## ADR-001: Modular monolith
Decision: Start as a modular monolith.
Reason: The learning objective is Spring Boot architecture and domain design, not distributed-systems complexity.

## ADR-002: Thymeleaf instead of React/Next.js
Decision: Use Thymeleaf for the initial web UI.
Reason: The project is intentionally focused on learning Java + Spring Boot end-to-end. Thymeleaf allows Spring Boot to render the application without introducing another application stack.

## ADR-003: PostgreSQL
Decision: PostgreSQL is the primary database.
Reason: Strong relational modeling fits students, courses, enrollments, attendance, assignments, and grades.

## ADR-004: Java 21
Decision: Java 21.
Reason: LTS release and comfortable baseline for learning modern Java while remaining broadly relevant.

## ADR-005: Maven
Decision: Maven.
Reason: Familiar, widely used in Spring Boot environments, and useful for understanding dependency management and build lifecycle.

## ADR-006: AI-assisted development
Decision: AI can generate examples and implementation drafts, but every feature requires human verification and learning notes.
