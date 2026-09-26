# 24 AI IDE Handoff

## How an AI coding agent must work

Before every task:
1. Read `brain/00_MASTER_RULES.md`.
2. Read the relevant phase/task documents.
3. Inspect the current project files.
4. Identify the current phase.
5. State the intended changes.
6. Implement the smallest coherent change.
7. Run the relevant build/tests.
8. Report files changed and learning points.
9. Update brain documents only when a real decision or milestone changed.

## Never
- invent files that do not exist
- replace the architecture without an ADR
- add dependencies without justification
- rewrite working code unnecessarily
- claim tests passed without running them
- skip explanations
- jump to later phases because a later feature is easier

## Task response format
### Understanding
What is being built and why.

### Plan
Small steps.

### Implementation
Files changed and reasoning.

### Verification
Commands/tests and result.

### Learning
Concepts the learner should understand.

### Interview
3-5 likely questions about the feature.

### Next
The next microtask only.
