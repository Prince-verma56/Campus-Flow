package com.campusflow.service;

import com.campusflow.entity.Course;
import com.campusflow.entity.Enrollment;
import com.campusflow.entity.Student;
import com.campusflow.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found with id: " + id));
    }

    @Transactional
    public Enrollment createEnrollment(Enrollment enrollment) {
        if (enrollment.getStudent() == null || enrollment.getStudent().getId() == null) {
            throw new IllegalArgumentException("Student ID is required to create an enrollment");
        }
        if (enrollment.getCourse() == null || enrollment.getCourse().getId() == null) {
            throw new IllegalArgumentException("Course ID is required to create an enrollment");
        }

        Student student = studentService.getStudentById(enrollment.getStudent().getId());
        Course course = courseService.getCourseById(enrollment.getCourse().getId());
        
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        // Can also add business validation here, e.g., student already enrolled in this course

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        Enrollment existingEnrollment = getEnrollmentById(id);
        existingEnrollment.setStatus(updatedEnrollment.getStatus());

        if (updatedEnrollment.getStudent() != null && updatedEnrollment.getStudent().getId() != null) {
            Student student = studentService.getStudentById(updatedEnrollment.getStudent().getId());
            existingEnrollment.setStudent(student);
        }
        if (updatedEnrollment.getCourse() != null && updatedEnrollment.getCourse().getId() != null) {
            Course course = courseService.getCourseById(updatedEnrollment.getCourse().getId());
            existingEnrollment.setCourse(course);
        }

        return enrollmentRepository.save(existingEnrollment);
    }

    @Transactional
    public void deleteEnrollment(Long id) {
        getEnrollmentById(id);
        enrollmentRepository.deleteById(id);
    }
}
