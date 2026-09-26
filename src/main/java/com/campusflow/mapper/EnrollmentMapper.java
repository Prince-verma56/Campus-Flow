package com.campusflow.mapper;

import com.campusflow.dto.enrollment.EnrollmentRequest;
import com.campusflow.dto.enrollment.EnrollmentResponse;
import com.campusflow.entity.Course;
import com.campusflow.entity.Enrollment;
import com.campusflow.entity.Student;

public class EnrollmentMapper {

    public static Enrollment toEntity(EnrollmentRequest request, Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStatus(request.getStatus());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollment;
    }

    public static EnrollmentResponse toResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
            enrollment.getId(),
            enrollment.getEnrolledAt(),
            enrollment.getStatus(),
            enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
            enrollment.getCourse() != null ? enrollment.getCourse().getId() : null
        );
    }
}
