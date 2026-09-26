# 10 Security

## Authentication
JWT-based authentication.

## Passwords
Passwords are hashed with a strong password encoder. Plain-text passwords are never stored.

## Authorization
Roles:
- ADMIN
- FACULTY
- STUDENT

## Example permissions
ADMIN:
- manage users
- manage departments
- manage courses
- view system reports

FACULTY:
- manage assigned courses
- mark attendance
- create assignments
- grade submissions

STUDENT:
- view own profile
- view enrolled courses
- view own attendance
- submit assignments
- view own grades

## Security rules
- Deny by default where appropriate.
- Never trust role information from request bodies.
- Validate ownership in services.
- Keep JWT secrets in environment variables.
- Never log tokens or passwords.
