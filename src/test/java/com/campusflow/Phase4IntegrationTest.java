package com.campusflow;

import com.campusflow.entity.*;
import com.campusflow.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class Phase4IntegrationTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void shouldPersistFullRelationalWorkflow() {
        // 1. Create Department
        Department dept = new Department(null, "CSE", "Computer Science");
        dept = departmentRepository.save(dept);
        assertThat(dept.getId()).isNotNull();

        // 2. Create Student linked to Department
        Student student = new Student(null, "John Doe", "john@example.com", dept);
        student = studentRepository.save(student);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getDepartment().getCode()).isEqualTo("CSE");

        // 3. Create Faculty linked to Department
        Faculty faculty = new Faculty(null, "Dr. Smith", "smith@example.com", "EMP123", dept);
        faculty = facultyRepository.save(faculty);
        assertThat(faculty.getId()).isNotNull();
        assertThat(faculty.getDepartment().getCode()).isEqualTo("CSE");

        // 4. Create Course linked to Department + Faculty
        Course course = new Course(null, "CS101", "Intro to CS", 3, "Fall 2026", dept, faculty);
        course = courseRepository.save(course);
        assertThat(course.getId()).isNotNull();
        assertThat(course.getFaculty().getEmployeeNumber()).isEqualTo("EMP123");

        // 5. Create Enrollment linked to Student + Course
        Enrollment enrollment = new Enrollment(null, null, "ENROLLED", student, course);
        enrollment = enrollmentRepository.save(enrollment);
        assertThat(enrollment.getId()).isNotNull();
        assertThat(enrollment.getStudent().getEmail()).isEqualTo("john@example.com");
        assertThat(enrollment.getCourse().getCode()).isEqualTo("CS101");
        
        // Verify GET from repository (simulating GET endpoints)
        Optional<Enrollment> retrieved = enrollmentRepository.findById(enrollment.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getEnrolledAt()).isNotNull();
    }
}
