# 04 Data Model

## Core entities

### User
- id
- name
- email
- passwordHash
- role
- status
- createdAt
- updatedAt

### Student
- id
- userId
- enrollmentNumber
- departmentId
- semester

### Faculty
- id
- userId
- employeeNumber
- departmentId

### Department
- id
- code
- name

### Course
- id
- code
- name
- credits
- semester
- departmentId
- facultyId

### Enrollment
- id
- studentId
- courseId
- enrolledAt
- status

### AttendanceRecord
- id
- enrollmentId
- date
- status

### Assignment
- id
- courseId
- title
- description
- dueAt
- createdBy

### Submission
- id
- assignmentId
- studentId
- submittedAt
- content/reference
- status

### Grade
- id
- submissionId
- score
- feedback
- gradedBy
- gradedAt

### Notification
- id
- recipientUserId
- type
- title
- message
- readAt
- createdAt

## Relationships
- Department 1:N Student
- Department 1:N Faculty
- Department 1:N Course
- Faculty 1:N Course
- Student N:M Course through Enrollment
- Course 1:N Assignment
- Assignment 1:N Submission
- Submission 1:1 Grade
- User 1:1 Student or Faculty profile

## Rules
Do not expose JPA entities directly from public APIs. Use DTOs.
