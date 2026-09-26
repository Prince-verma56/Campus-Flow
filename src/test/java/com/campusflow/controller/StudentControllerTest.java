package com.campusflow.controller;

import com.campusflow.dto.student.StudentRequest;
import com.campusflow.dto.student.StudentResponse;
import com.campusflow.exception.GlobalExceptionHandler;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    void getStudentById_ShouldReturn200_WhenStudentExists() throws Exception {
        StudentResponse response = new StudentResponse(1L, "Test Student", "test@example.com", 1L);
        when(studentService.getStudentById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Student"));
    }

    @Test
    void getStudentById_ShouldReturn404_WhenStudentDoesNotExist() throws Exception {
        when(studentService.getStudentById(1L))
                .thenThrow(new ResourceNotFoundException("Student not found with id: 1"));

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("RESOURCE_NOT_FOUND"));
    }
}
