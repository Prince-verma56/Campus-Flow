# 06 Data Ingestion Specification

## Purpose
Define how data enters CampusFlow.

## Inputs
- REST requests
- Thymeleaf forms
- Seed data
- Optional CSV import in a later phase

## Pipeline
```text
Input
 -> DTO validation
 -> authorization
 -> service business rules
 -> transaction
 -> repository
 -> database
```

## Validation
Reject malformed, missing, unauthorized, or contradictory input before persistence.

## Import feature
CSV import is optional and must not be added before the core CRUD and validation flows are understood.
