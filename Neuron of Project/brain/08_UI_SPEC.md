# 08 UI SPEC

## UI architecture
Spring Boot + Thymeleaf + Tailwind CSS + vanilla JavaScript + GSAP.

No React or Next.js.

## Visual direction
Premium academic technology platform:
- warm neutral background
- deep ink typography
- one restrained accent color
- editorial typography hierarchy
- generous whitespace
- subtle borders
- high-quality cards
- compact but clear data tables
- responsive sidebar/navigation
- polished states

## Main screens
1. Landing / sign-in
2. Login
3. Student dashboard
4. Faculty dashboard
5. Admin dashboard
6. Courses
7. Course detail
8. Attendance
9. Assignments
10. Grades
11. Notifications
12. Profile

## Motion
GSAP is used for:
- page entrance
- staggered dashboard cards
- navigation transitions
- modal/dialog transitions
- meaningful state changes

Do not animate every element.

## Component philosophy
Build reusable server-rendered components/fragments:
- navbar
- sidebar
- page header
- stat card
- data table
- badge
- modal
- toast
- empty state
- loading state
- error state

## Accessibility
Keyboard navigation, semantic HTML, visible focus states, sufficient contrast, useful labels.

## UI acceptance
A screen is not complete if it only works technically. It must have loading, empty, error, success, responsive, and interaction states where relevant.
