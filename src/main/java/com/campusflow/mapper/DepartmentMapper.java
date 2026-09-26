package com.campusflow.mapper;

import com.campusflow.dto.department.DepartmentRequest;
import com.campusflow.dto.department.DepartmentResponse;
import com.campusflow.entity.Department;

public class DepartmentMapper {
    public static Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setCode(request.getCode());
        department.setName(request.getName());
        return department;
    }

    public static DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
            department.getId(),
            department.getCode(),
            department.getName()
        );
    }
}
