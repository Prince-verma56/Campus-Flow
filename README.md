# CampusFlow

Smart Campus Management System

## Overview
CampusFlow is a modern, intelligent campus management platform being developed to manage academics, track progress, and help students stay connected. It is built as a modular monolith.

## Technology Stack

### Current
- Java 21
- Spring Boot 4.1.1
- Maven
- Thymeleaf
- Tailwind CSS

### Planned
- PostgreSQL
- Docker

### Future
- Spring Security / JWT
- Redis
- GSAP (planned for current phase but not yet active)

## Architecture
CampusFlow follows a standard Spring Boot layered architecture (Controller → Service → Repository). It currently uses server-side rendered Thymeleaf views integrated with Tailwind CSS for a premium UI.

## Project Structure
- `src/`: Contains the main Java source code, static assets, and Thymeleaf HTML templates.
- `brain/`: Contains permanent project knowledge, design specifications, and architecture rules.
- `audits/`: Contains historical project progress, learning records, and phase audits.

## Development Setup

Requirements: Java 21, Node.js (for Tailwind CSS compilation).

1. Check Java version:
   ```bash
   java -version
   ```

2. Run initial clean and test using the Maven Wrapper:
   ```bash
   .\mvnw.cmd clean test
   ```

3. Start the application:
   ```bash
   .\mvnw.cmd spring-boot:run
   ```

4. Install frontend dependencies (for Tailwind UI development):
   ```bash
   npm install
   ```

5. Watch for CSS changes:
   ```bash
   npm run watch:css
   ```

## Live Development
To develop locally with hot-reloading for both Java and Tailwind CSS, use two terminal windows:

**Terminal 1 (Spring Boot):**
```bash
.\mvnw.cmd spring-boot:run
```

**Terminal 2 (Tailwind CSS):**
```bash
npm run watch:css
```

**Browser:**
Navigate to `http://localhost:8080/`

*Note: The generated `src/main/resources/static/css/app.css` is committed to version control. This means developers only needing to work on backend code do not need to install Node.js or run the Tailwind watcher; the application will run perfectly with the pre-compiled CSS.*

## Git Workflow
We use a standard Git workflow where `main` acts as the primary integration branch. Feature development should happen on branches and merge into `main` after review.

## Learning Approach
CampusFlow is an educational, learning-first project. It is being developed phase-by-phase with structured microtasks. After each microtask, an audit is generated to record progress and learning outcomes. It is not currently production-ready.
