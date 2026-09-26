package com.campusflow.service;

import com.campusflow.dto.student.StudentRequest;
import com.campusflow.dto.student.StudentResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Student;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldCreateStudent() {
        Department dept = new Department(1L, "CSE", "Computer Science");
        StudentRequest request = new StudentRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setDepartmentId(1L);
        
        when(departmentService.getDepartmentEntityById(1L)).thenReturn(dept);
        when(studentRepository.save(any(Student.class))).thenReturn(new Student(1L, "Alice", "alice@example.com", dept));

        StudentResponse response = studentService.createStudent(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Alice");
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(99L);
        });

        assertThat(exception.getMessage()).isEqualTo("Student not found with id: 99");
    }
}
