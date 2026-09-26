package com.campusflow.service;

import com.campusflow.dto.department.DepartmentRequest;
import com.campusflow.dto.department.DepartmentResponse;
import com.campusflow.entity.Department;
import com.campusflow.mapper.DepartmentMapper;
import com.campusflow.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(Long id) {
        return DepartmentMapper.toResponse(getDepartmentEntityById(id));
    }

    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department department = DepartmentMapper.toEntity(request);
        department = departmentRepository.save(department);
        return DepartmentMapper.toResponse(department);
    }

    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department department = getDepartmentEntityById(id);
        department.setCode(request.getCode());
        department.setName(request.getName());
        department = departmentRepository.save(department);
        return DepartmentMapper.toResponse(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        getDepartmentEntityById(id);
        departmentRepository.deleteById(id);
    }
}
