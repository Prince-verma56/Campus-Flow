# 23 Environment Configuration

## Local variables
Example names only:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
REDIS_HOST
REDIS_PORT
```

## Profiles
- local
- test
- production

## Rules
- No real secrets in Git.
- Use `.env` or IDE environment configuration locally.
- Production secrets come from the deployment environment.
- Keep `application.properties` safe to commit.
