package com.campusflow.repository;

import com.campusflow.entity.Student;
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

    @Test
    void shouldSaveAndRetrieveStudent() {
        // Arrange
        Student student = new Student(null, "Integration Test Student", "integration@example.com");

        // Act
        Student savedStudent = studentRepository.save(student);

        // Assert
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Integration Test Student");

        // Verify retrieval
        Optional<Student> retrievedStudent = studentRepository.findById(savedStudent.getId());
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getEmail()).isEqualTo("integration@example.com");
    }

    @Test
    void shouldUpdateStudent() {
        // Arrange
        Student student = new Student(null, "Update Student", "update@example.com");
        Student savedStudent = studentRepository.save(student);

        // Act
        savedStudent.setName("Updated Name");
        Student updatedStudent = studentRepository.save(savedStudent);

        // Assert
        assertThat(updatedStudent.getName()).isEqualTo("Updated Name");
        
        Optional<Student> retrievedStudent = studentRepository.findById(savedStudent.getId());
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getName()).isEqualTo("Updated Name");
    }

    @Test
    void shouldDeleteStudent() {
        // Arrange
        Student student = new Student(null, "Delete Student", "delete@example.com");
        Student savedStudent = studentRepository.save(student);
        Long id = savedStudent.getId();

        // Act
        studentRepository.deleteById(id);

        // Assert
        Optional<Student> retrievedStudent = studentRepository.findById(id);
        assertThat(retrievedStudent).isEmpty();
    }
}
