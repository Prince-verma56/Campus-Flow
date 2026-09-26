# 21 Roles and Permissions

| Resource | ADMIN | FACULTY | STUDENT |
|---|---|---|---|
| Users | manage | view limited | self |
| Departments | manage | view | view |
| Courses | manage | assigned | enrolled/view |
| Enrollment | manage | view assigned | own |
| Attendance | manage | mark assigned | own view |
| Assignments | manage | create/grade | submit |
| Grades | manage | grade assigned | own view |
| Notifications | system | own | own |
| Reports | full | course-level | own |

Authorization must be enforced server-side. UI hiding is not security.
