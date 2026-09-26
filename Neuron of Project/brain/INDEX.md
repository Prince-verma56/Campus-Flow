# CampusFlow Brain Index

Read in this order:

1. `00_MASTER_RULES.md`
2. `01_PRD.md`
3. `02_TRD.md`
4. `03_ARCHITECTURE.md`
5. `04_DATA_MODEL.md`
6. `21_ROLES_PERMISSIONS.md`
7. `08_UI_SPEC.md`
8. `15_MICROTASKS.md`
9. `23_ENVIRONMENT_CONFIG.md`
10. `24_IT_HANDOFF.md`

Then consult the phase-specific documents as implementation reaches them.

## Phase map

| Phase | Main document(s) |
|---|---|
| 0 Java | 00, 15 |
| 1 Spring Boot/Maven | 02, 03, 15 |
| 2 REST | 03, 07 |
| 3 JPA/PostgreSQL | 04, 05 |
| 4 Relationships | 04 |
| 5 DTO/Validation | 07, 09 |
| 6 Errors | 09 |
| 7 Security | 10, 21 |
| 8 Campus modules | 01, 04, 07 |
| 9 Pagination/search | 07 |
| 10 Redis | 02, 22 |
| 11 Notifications | 11, 19 |
| 12 API docs | 07 |
| 13 Testing | 13 |
| 14 Production | 14, 22, 23 |
| 15 AI/CI/polish | 12, 20, 24 |
