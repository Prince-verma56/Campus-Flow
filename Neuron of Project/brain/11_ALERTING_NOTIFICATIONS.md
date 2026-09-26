# 11 Alerting and Notifications

## Notification types
- ASSIGNMENT_CREATED
- ASSIGNMENT_DUE_SOON
- GRADE_PUBLISHED
- ATTENDANCE_WARNING
- SYSTEM

## MVP
In-app notifications stored in PostgreSQL.

## Later
- email
- WebSocket push
- scheduled reminders

## Notification lifecycle
Event -> notification service -> persistence -> user inbox -> read/unread state.
