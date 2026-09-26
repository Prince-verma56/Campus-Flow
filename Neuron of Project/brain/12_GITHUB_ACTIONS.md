# 12 GitHub Actions

## CI pipeline
On pull request and push:
1. Checkout
2. Set up Java 21
3. Run Maven tests
4. Run build
5. Optionally run static checks

## Rules
A pull request should not be considered ready if tests fail.

## Future
- Docker image build
- security scanning
- deployment
