package com.campusflow.controller;

import com.campusflow.entity.Department;
import com.campusflow.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
        Department res = new Department(1L, "MATH", "Mathematics");
        when(departmentService.createDepartment(any(Department.class))).thenReturn(res);

        String reqJson = "{\"code\":\"MATH\", \"name\":\"Mathematics\"}";

        mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("MATH"));
    }
    
    @Test
    void shouldReturn404WhenDepartmentNotFound() throws Exception {
        when(departmentService.getDepartmentById(99L))
            .thenThrow(new IllegalArgumentException("Department not found with id: 99"));

        mockMvc.perform(get("/api/v1/departments/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Department not found with id: 99"));
    }
}
