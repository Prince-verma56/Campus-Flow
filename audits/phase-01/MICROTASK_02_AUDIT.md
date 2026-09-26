# Microtask 02 Audit

## Status
COMPLETE

## Objective
Implement the first Spring MVC controller (`HomeController`) to understand the Spring Web MVC request lifecycle, routing annotations, and basic controller structure.

## Previous State
- Foundation project initialized with Spring Boot 4.1.1 and Maven.
- `pom.xml` configured with Web, Thymeleaf, Validation, and DevTools dependencies.
- No controllers or application logic existed.

## What We Added
- Created `HomeController` to handle the root URL (`GET /`).
- Used `@RestController` conceptually by combining `@Controller` and `@ResponseBody` to return a simple text string before introducing Thymeleaf templates.

## Files Created
- `src/main/java/com/campusflow/controller/HomeController.java`

## Files Modified
- None

## Concepts Learned
- **Spring MVC**: The framework built on the Servlet API used to build web applications.
- **Controller**: A class responsible for preparing a model Map with data and selecting a view name (or returning data directly).
- **Component Scanning**: How Spring automatically discovers annotated classes like `@Controller` at startup.

## Spring MVC Request Flow
Browser
→ HTTP Request (`GET /`)
→ **DispatcherServlet** (The front controller that receives all incoming requests)
→ **Controller Mapping** (Scans registered routes to find a match for `/`)
→ **HomeController** (The specific method mapped to `/` is executed)
→ Response (The returned string is written to the HTTP response body)

## Controller Explanation
- `package com.campusflow.controller;`: Defines the location of the class. Spring's component scan looks in sub-packages of the main application class.
- `@Controller`: A specialization of `@Component`. It marks the class as a web controller, allowing Spring to register it and its routing methods.
- `@GetMapping("/")`: A shortcut for `@RequestMapping(method = RequestMethod.GET, value = "/")`. It binds the HTTP GET request on `/` to the `home()` method.
- `@ResponseBody`: Tells Spring MVC not to render a template (like Thymeleaf), but to write the returned `String` directly into the HTTP response body.
- `public String home()`: The method executed when the route is hit.

## Express Comparison
- **Express Route**: `app.get('/', (req, res) => res.send('...'))`.
- **Spring Equivalent**: A method annotated with `@GetMapping("/")` inside a `@Controller` class.
- **Differences**: 
  - Express uses callbacks/middleware chains explicitly defined in a file. 
  - Spring uses declarative annotations. The `DispatcherServlet` acts as a hidden underlying router (similar to Express's core router) that delegates to your methods automatically based on annotations. You don't manually wire the route to the server instance.

## Commands Executed
- `.\mvnw.cmd spring-boot:run`

## Verification
- Application startup: SUCCESS
- `GET /` on `http://localhost:8080/`: SUCCESS
- The browser returned: "CampusFlow Spring MVC Controller is working!"

## Problems Encountered
- None. The background maven resolution took some time as expected for a new Spring Boot application, but execution succeeded.

## Fixes
- N/A

## What I Can Explain Without AI
- [To be filled by student]

## What I Still Need Practice With
- [To be filled by student]

## Interview Questions
1. What is the role of the `DispatcherServlet` in Spring Web MVC?
2. How does Spring Boot know about your `HomeController` without you manually instantiating it?
3. What is the difference between `@Controller` and `@RestController`?
4. What happens if you omit `@ResponseBody` on a `@Controller` method returning a String?
5. How would you map a POST request to a specific URL in Spring MVC?
6. Explain the HTTP request lifecycle from the browser hitting `GET /` to the response.
7. What is Component Scanning in Spring?
8. If your controller is in `com.external.controller` but your main app is in `com.campusflow`, will Spring find it by default? Why or why not?
9. How does dependency injection relate to Spring's management of `@Controller` classes?
10. Compare how routing works in Express.js versus Spring MVC.

## AI Contribution
- AI explained the core concepts of Spring MVC, DispatcherServlet, and the annotations.
- AI generated the `HomeController.java` implementation.
- AI ran the application and verified the endpoint response.
- AI generated the Audit document and the interview questions.
- User will manually verify the endpoint and answer the learning check questions.

## Next Microtask
Microtask 3: Thymeleaf Templates and Server-Side Rendering
