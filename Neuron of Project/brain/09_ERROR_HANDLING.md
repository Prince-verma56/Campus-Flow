# 09 Error Handling

## Goals
Consistent, readable, secure errors.

## Exception types
- ResourceNotFoundException
- ValidationException / validation errors
- UnauthorizedException
- ForbiddenException
- ConflictException
- Generic fallback exception

## Mechanism
Use centralized exception handling with `@RestControllerAdvice` for API errors.

## Error shape
```json
{
  "timestamp": "...",
  "status": 404,
  "code": "STUDENT_NOT_FOUND",
  "message": "Student was not found",
  "path": "/api/v1/students/123"
}
```

## Rules
Never expose stack traces or database internals to clients.
Log enough context server-side to debug safely.
