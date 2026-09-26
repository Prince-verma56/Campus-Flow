package com.campusflow.service;

import com.campusflow.model.Student;
import com.campusflow.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudentById_ShouldReturnStudent_WhenStudentExists() {
        // Arrange
        Student mockStudent = new Student(1L, "Test Student", "test@example.com");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(mockStudent));

        // Act
        Student result = studentService.getStudentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Student", result.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void getStudentById_ShouldThrowException_WhenStudentDoesNotExist() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.getStudentById(1L);
        });

        assertTrue(exception.getMessage().contains("Student not found"));
        verify(studentRepository, times(1)).findById(1L);
    }
}
