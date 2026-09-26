package com.campusflow.dto.enrollment;

import java.time.LocalDateTime;

public class EnrollmentResponse {
    private Long id;
    private LocalDateTime enrolledAt;
    private String status;
    private Long studentId;
    private Long courseId;

    public EnrollmentResponse() {}

    public EnrollmentResponse(Long id, LocalDateTime enrolledAt, String status, Long studentId, Long courseId) {
        this.id = id;
        this.enrolledAt = enrolledAt;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(LocalDateTime enrolledAt) { this.enrolledAt = enrolledAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
