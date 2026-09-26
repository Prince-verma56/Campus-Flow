package com.campusflow.mapper;

import com.campusflow.dto.faculty.FacultyRequest;
import com.campusflow.dto.faculty.FacultyResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;

public class FacultyMapper {

    public static Faculty toEntity(FacultyRequest request, Department department) {
        Faculty faculty = new Faculty();
        faculty.setName(request.getName());
        faculty.setEmail(request.getEmail());
        faculty.setEmployeeNumber(request.getEmployeeNumber());
        faculty.setDepartment(department);
        return faculty;
    }

    public static FacultyResponse toResponse(Faculty faculty) {
        return new FacultyResponse(
            faculty.getId(),
            faculty.getName(),
            faculty.getEmail(),
            faculty.getEmployeeNumber(),
            faculty.getDepartment() != null ? faculty.getDepartment().getId() : null
        );
    }
}
