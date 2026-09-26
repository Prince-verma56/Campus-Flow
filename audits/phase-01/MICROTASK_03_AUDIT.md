# Microtask 03 Audit

## Objective
Introduce Thymeleaf templates, establish the premium presentation shell, and transition from raw string responses to server-side rendered HTML using reusable fragments.

## Previous State
- `HomeController` returned plain text using `@ResponseBody`.
- No HTML templates or fragments existed.

## What We Implemented
- Modified `HomeController` to remove `@ResponseBody` and return Thymeleaf view names.
- Created reusable UI fragments: `navbar`, `sidebar`, `page-header`, `footer`.
- Created presentation shell templates for all primary routes: `index.html` (for `/`), `dashboard.html`, `courses.html`, `attendance.html`, `assignments.html`, `grades.html`, `profile.html`.

## Thymeleaf Explanation
Thymeleaf is a modern server-side Java template engine. It takes HTML files containing special `th:` attributes, processes them (injecting data, replacing fragments), and generates a final standard HTML string that is sent directly to the browser. 

## Server-Side Rendering Flow
Browser
→ HTTP Request (`GET /`)
→ Spring MVC (DispatcherServlet)
→ `HomeController` (receives request, adds "title" to the Model, returns "index")
→ View Name ("index" is resolved to `src/main/resources/templates/index.html`)
→ Thymeleaf (processes the template, replacing fragments and variables)
→ HTML (Fully built HTML document is generated)
→ Browser (Receives and displays the HTML immediately)

## @Controller vs @ResponseBody
- **Before:** `@ResponseBody` told Spring to take the exact String returned by the method and write it directly into the HTTP response.
- **After:** Removing `@ResponseBody` tells Spring that the returned String is a *View Name*. Spring intercepts it, finds the matching `.html` template in the `templates/` directory, and hands it to Thymeleaf for rendering.

## Templates
- `index.html`
- `dashboard.html`
- `courses.html`
- `attendance.html`
- `assignments.html`
- `grades.html`
- `profile.html`

## Fragments
- `fragments/navbar.html`: Reusable top navigation bar.
- `fragments/sidebar.html`: Reusable side navigation menu.
- `fragments/page-header.html`: Reusable page title area, demonstrating passing parameters to fragments (`th:fragment="header(title)"`).
- `fragments/footer.html`: Reusable bottom footer.

## Page Routes
- `GET /`
- `GET /dashboard`
- `GET /courses`
- `GET /attendance`
- `GET /assignments`
- `GET /grades`
- `GET /profile`

## React/Next.js Comparison
- **React (CSR):** Sends an empty HTML file + JavaScript. The browser builds the DOM elements locally.
- **Next.js (SSR/SSG):** A Node server runs React to build the initial HTML, then sends it. The browser still needs to download a JavaScript bundle to "hydrate" the page and make it interactive.
- **Thymeleaf (Traditional SSR):** The Spring Boot server builds the complete HTML. The browser downloads and displays it. The resulting page doesn't require a massive JavaScript framework to run.
- **Fragments vs React Components:** `th:replace="~{fragments/navbar :: navbar}"` is conceptually similar to `<Navbar />`, allowing developers to keep code DRY.

## Files Created
- `src/main/resources/templates/fragments/navbar.html`
- `src/main/resources/templates/fragments/sidebar.html`
- `src/main/resources/templates/fragments/page-header.html`
- `src/main/resources/templates/fragments/footer.html`
- `src/main/resources/templates/index.html`
- `src/main/resources/templates/dashboard.html`
- `src/main/resources/templates/courses.html`
- `src/main/resources/templates/attendance.html`
- `src/main/resources/templates/assignments.html`
- `src/main/resources/templates/grades.html`
- `src/main/resources/templates/profile.html`

## Files Modified
- `src/main/java/com/campusflow/controller/HomeController.java`

## Dependencies Used
- `spring-boot-starter-thymeleaf` (Already added in Microtask 1)

## Commands Executed
- (Application was already running with `spring-boot-devtools`, which automatically hot-reloaded the template and controller changes)
- HTTP GET requests to `localhost:8080` for verification.

## Verification Results
- All endpoints (`/`, `/dashboard`, `/courses`, etc.) returned the correct fully rendered HTML.
- Fragments (navbar, sidebar, etc.) were correctly injected.
- Dynamic data (`${title}`) passed from the Controller rendered successfully in the HTML.

## Problems Encountered
- None.

## Fixes
- N/A

## Concepts Learned
- Server-Side Rendering (SSR)
- Thymeleaf Templates
- Thymeleaf Fragments
- Model Data Binding

## What I Can Explain Without AI
- [To be filled by student]

## What I Need More Practice With
- [To be filled by student]

## Interview Questions
1. What does the term Server-Side Rendering (SSR) mean in the context of Spring Boot?
2. What happens if a `@Controller` method returns the string `"index"` without the `@ResponseBody` annotation?
3. Where does Spring Boot look for Thymeleaf templates by default?
4. How do you pass data from a Spring Controller to a Thymeleaf template?
5. What is the purpose of Thymeleaf fragments?
6. Compare how a reusable navigation bar is implemented in React versus Thymeleaf.
7. How does the `th:text` attribute work?
8. Explain the request flow from the moment the browser requests `/dashboard` to the moment it receives HTML.
9. What is the benefit of SSR over a pure Single Page Application (SPA) built with plain React?
10. How does a Spring `@Controller` decide whether to return JSON/text or a rendered HTML page?

## AI Contribution
- AI explained Thymeleaf, SSR, and the conceptual shift from `@ResponseBody`.
- AI generated the initial premium HTML templates and reusable fragments.
- AI updated the `HomeController` to use the Spring `Model` and return view names.
- AI verified the routes using internal HTTP requests.
- User will manually verify the endpoints in their browser and answer learning checks.

## Next Microtask
Microtask 4: Premium UI (Tailwind & GSAP)
