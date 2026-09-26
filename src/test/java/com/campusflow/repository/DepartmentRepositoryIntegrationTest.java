package com.campusflow.repository;

import com.campusflow.entity.Department;
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
class DepartmentRepositoryIntegrationTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldSaveAndRetrieveDepartment() {
        Department department = new Department(null, "PHYS", "Physics");
        Department saved = departmentRepository.save(department);

        assertThat(saved.getId()).isNotNull();

        Optional<Department> retrieved = departmentRepository.findById(saved.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getCode()).isEqualTo("PHYS");
    }
}
