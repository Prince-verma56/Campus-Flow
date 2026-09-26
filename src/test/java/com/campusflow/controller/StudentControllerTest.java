package com.campusflow.controller;

import com.campusflow.model.Student;
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
        // Build MockMvc directly without Spring Boot Test Context to bypass autoconfiguration issues
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void getStudentById_ShouldReturn200_WhenStudentExists() throws Exception {
        Student mockStudent = new Student(1L, "Test Student", "test@example.com");
        when(studentService.getStudentById(1L)).thenReturn(mockStudent);

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Student"));
    }

    @Test
    void getStudentById_ShouldReturn404_WhenStudentDoesNotExist() throws Exception {
        when(studentService.getStudentById(1L))
                .thenThrow(new IllegalArgumentException("Student not found with id: 1"));

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Student not found with id: 1"));
    }
}
