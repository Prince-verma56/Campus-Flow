# 22 Observability

## Logging
Use structured, useful logs.

Log:
- application startup
- important business events
- authentication failures
- unexpected exceptions
- integration failures

Never log:
- passwords
- JWT tokens
- secrets
- sensitive personal data unnecessarily

## Health
Use Spring Boot Actuator later in the project.

## Metrics
Track useful application metrics when the core application is stable:
- request counts
- latency
- errors
- database health
- cache hit/miss when Redis is introduced
