# 13 Testing

## Testing pyramid
- Unit tests: fast service/domain tests
- MVC tests: controller/API behavior
- Integration tests: database/security flows
- End-to-end tests: only for critical user journeys

## Minimum coverage targets
Do not chase an arbitrary percentage. Prioritize:
- authentication
- authorization
- student/course CRUD
- enrollment rules
- attendance rules
- assignment submission
- grading
- exception handling

## Learning goals
Understand the difference between:
- unit test
- integration test
- mock
- stub
- test slice
- full application test
