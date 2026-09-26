# 07 API Contract

## Base path
`/api/v1`

## Authentication
- POST `/auth/register`
- POST `/auth/login`
- GET `/auth/me`

## Students
- GET `/students`
- POST `/students`
- GET `/students/{id}`
- PUT `/students/{id}`
- DELETE `/students/{id}`

## Courses
- GET `/courses`
- POST `/courses`
- GET `/courses/{id}`
- PUT `/courses/{id}`

## Enrollment
- POST `/enrollments`
- GET `/students/{id}/enrollments`
- DELETE `/enrollments/{id}`

## Attendance
- POST `/attendance`
- GET `/students/{id}/attendance`
- GET `/courses/{id}/attendance`

## Assignments
- POST `/courses/{courseId}/assignments`
- GET `/courses/{courseId}/assignments`
- POST `/assignments/{id}/submissions`

## Grades
- POST `/submissions/{id}/grade`
- GET `/students/{id}/grades`

## API rules
- Use correct HTTP status codes.
- Use request/response DTOs.
- Validate all external input.
- Return stable error structures.
- Paginate collection endpoints.
- Do not leak passwords or internal secrets.
