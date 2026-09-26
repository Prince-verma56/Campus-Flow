package com.campusflow.repository;

import com.campusflow.entity.Department;
import com.campusflow.entity.Student;
import org.junit.jupiter.api.BeforeEach;
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
class StudentRepositoryIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department(null, "TEST", "Test Dept");
        department = departmentRepository.save(department);
    }

    @Test
    void shouldSaveAndRetrieveStudent() {
        Student student = new Student(null, "Integration Test Student", "integration@example.com", department);
        Student savedStudent = studentRepository.save(student);

        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Integration Test Student");

        Optional<Student> retrievedStudent = studentRepository.findById(savedStudent.getId());
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getEmail()).isEqualTo("integration@example.com");
    }

    @Test
    void shouldUpdateStudent() {
        Student student = new Student(null, "Update Student", "update@example.com", department);
        Student savedStudent = studentRepository.save(student);

        savedStudent.setName("Updated Name");
        Student updatedStudent = studentRepository.save(savedStudent);

        assertThat(updatedStudent.getName()).isEqualTo("Updated Name");
        
        Optional<Student> retrievedStudent = studentRepository.findById(savedStudent.getId());
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getName()).isEqualTo("Updated Name");
    }

    @Test
    void shouldDeleteStudent() {
        Student student = new Student(null, "Delete Student", "delete@example.com", department);
        Student savedStudent = studentRepository.save(student);
        Long id = savedStudent.getId();

        studentRepository.deleteById(id);

        Optional<Student> retrievedStudent = studentRepository.findById(id);
        assertThat(retrievedStudent).isEmpty();
    }
}
