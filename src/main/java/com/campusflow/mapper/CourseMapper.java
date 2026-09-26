package com.campusflow.mapper;

import com.campusflow.dto.course.CourseRequest;
import com.campusflow.dto.course.CourseResponse;
import com.campusflow.entity.Course;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;

public class CourseMapper {

    public static Course toEntity(CourseRequest request, Department department, Faculty faculty) {
        Course course = new Course();
        course.setCode(request.getCode());
        course.setName(request.getName());
        course.setCredits(request.getCredits());
        course.setSemester(request.getSemester());
        course.setDepartment(department);
        course.setFaculty(faculty);
        return course;
    }

    public static CourseResponse toResponse(Course course) {
        return new CourseResponse(
            course.getId(),
            course.getCode(),
            course.getName(),
            course.getCredits(),
            course.getSemester(),
            course.getDepartment() != null ? course.getDepartment().getId() : null,
            course.getFaculty() != null ? course.getFaculty().getId() : null
        );
    }
}
