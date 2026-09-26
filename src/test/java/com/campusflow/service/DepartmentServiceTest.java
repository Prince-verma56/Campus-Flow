package com.campusflow.service;

import com.campusflow.entity.Department;
import com.campusflow.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldCreateDepartment() {
        Department department = new Department(null, "CSE", "Computer Science");
        when(departmentRepository.save(any(Department.class))).thenReturn(new Department(1L, "CSE", "Computer Science"));

        Department saved = departmentService.createDepartment(department);

        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(saved.getCode()).isEqualTo("CSE");
    }

    @Test
    void shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentService.getDepartmentById(99L);
        });

        assertThat(exception.getMessage()).isEqualTo("Department not found with id: 99");
    }
}
