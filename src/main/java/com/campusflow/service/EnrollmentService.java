package com.campusflow.service;

import com.campusflow.dto.enrollment.EnrollmentRequest;
import com.campusflow.dto.enrollment.EnrollmentResponse;
import com.campusflow.entity.Course;
import com.campusflow.entity.Enrollment;
import com.campusflow.entity.Student;
import com.campusflow.exception.InvalidReferenceException;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.mapper.EnrollmentMapper;
import com.campusflow.repository.EnrollmentRepository;
import com.campusflow.repository.specification.EnrollmentSpecification;
import com.campusflow.dto.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Enrollment getEnrollmentEntityById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }

    public PageResponse<EnrollmentResponse> getAllEnrollments(Long studentId, Long courseId, String status, Pageable pageable) {
        Specification<Enrollment> spec = EnrollmentSpecification.getEnrollmentQuery(studentId, courseId, status);
        Page<Enrollment> pageResult = enrollmentRepository.findAll(spec, pageable);
        
        Page<EnrollmentResponse> responsePage = pageResult.map(EnrollmentMapper::toResponse);
        return new PageResponse<>(responsePage);
    }

    public EnrollmentResponse getEnrollmentById(Long id) {
        return EnrollmentMapper.toResponse(getEnrollmentEntityById(id));
    }

    @Transactional
    public EnrollmentResponse createEnrollment(EnrollmentRequest request) {
        Student student;
        Course course;
        try {
            student = studentService.getStudentEntityById(request.getStudentId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Enrollment. " + e.getMessage());
        }
        
        try {
            course = courseService.getCourseEntityById(request.getCourseId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Enrollment. " + e.getMessage());
        }
        
        Enrollment enrollment = EnrollmentMapper.toEntity(request, student, course);
        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toResponse(enrollment);
    }

    @Transactional
    public EnrollmentResponse updateEnrollment(Long id, EnrollmentRequest request) {
        Enrollment existingEnrollment = getEnrollmentEntityById(id);
        
        existingEnrollment.setStatus(request.getStatus());
        
        if (request.getStudentId() != null) {
            try {
                Student student = studentService.getStudentEntityById(request.getStudentId());
                existingEnrollment.setStudent(student);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Enrollment. " + e.getMessage());
            }
        }
        
        if (request.getCourseId() != null) {
            try {
                Course course = courseService.getCourseEntityById(request.getCourseId());
                existingEnrollment.setCourse(course);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Enrollment. " + e.getMessage());
            }
        }

        existingEnrollment = enrollmentRepository.save(existingEnrollment);
        return EnrollmentMapper.toResponse(existingEnrollment);
    }

    @Transactional
    public void deleteEnrollment(Long id) {
        getEnrollmentEntityById(id);
        enrollmentRepository.deleteById(id);
    }
}
