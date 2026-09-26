# 20 AI Intelligence Specification

AI is optional and comes after the core campus system.

## Candidate features
1. Attendance risk explanation
2. Assignment question generation
3. Study-plan suggestions
4. Course FAQ assistant

## Safety and architecture
AI output must never directly change grades, attendance, permissions, or academic records.

## Integration boundary
```text
Spring Boot
   |
   v
AI service/client
   |
   v
External model API
```

## Learning objective
Use AI as an integration feature after understanding REST clients, configuration, error handling, and rate limits.
