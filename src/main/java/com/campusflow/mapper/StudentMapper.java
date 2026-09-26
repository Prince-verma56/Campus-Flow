package com.campusflow.mapper;

import com.campusflow.dto.student.StudentRequest;
import com.campusflow.dto.student.StudentResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequest request, Department department) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(department);
        return student;
    }

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
            student.getId(),
            student.getName(),
            student.getEmail(),
            student.getDepartment() != null ? student.getDepartment().getId() : null
        );
    }
}
