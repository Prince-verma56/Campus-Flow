package com.campusflow.controller;

import com.campusflow.dto.department.DepartmentRequest;
import com.campusflow.dto.department.DepartmentResponse;
import com.campusflow.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void shouldCreateDepartment() throws Exception {
        DepartmentResponse response = new DepartmentResponse(1L, "CSE", "Computer Science");
        when(departmentService.createDepartment(any(DepartmentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/departments")
                .contentType("application/json")
                .content("{\"code\": \"CSE\", \"name\": \"Computer Science\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CSE"));
    }
}
