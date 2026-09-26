# 05 Data Sources

## Primary source
PostgreSQL is the system of record.

## Development data
Use deterministic seed data only after the database model is stable.

## Generated data
Dummy users, courses, enrollments, attendance, assignments, and grades may be generated for local development.

## External data
No external production data is required for the MVP.

## Data policy
- Never use real student personal information.
- Never commit credentials.
- Do not copy private institutional data into the project.
- Use clearly fake names/emails in seed data.
