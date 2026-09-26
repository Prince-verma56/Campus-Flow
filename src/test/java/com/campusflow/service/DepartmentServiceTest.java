package com.campusflow.service;

import com.campusflow.dto.department.DepartmentRequest;
import com.campusflow.dto.department.DepartmentResponse;
import com.campusflow.entity.Department;
import com.campusflow.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldCreateDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setCode("CSE");
        request.setName("Computer Science");

        Department savedDept = new Department(1L, "CSE", "Computer Science");
        when(departmentRepository.save(any(Department.class))).thenReturn(savedDept);

        DepartmentResponse response = departmentService.createDepartment(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getCode()).isEqualTo("CSE");
    }
}
