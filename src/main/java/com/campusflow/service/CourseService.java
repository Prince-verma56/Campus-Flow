package com.campusflow.service;

import com.campusflow.entity.Course;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;
import com.campusflow.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentService departmentService;
    private final FacultyService facultyService;

    public CourseService(CourseRepository courseRepository, DepartmentService departmentService, FacultyService facultyService) {
        this.courseRepository = courseRepository;
        this.departmentService = departmentService;
        this.facultyService = facultyService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
    }

    @Transactional
    public Course createCourse(Course course) {
        if (course.getDepartment() == null || course.getDepartment().getId() == null) {
            throw new IllegalArgumentException("Department ID is required to create a course");
        }
        if (course.getFaculty() == null || course.getFaculty().getId() == null) {
            throw new IllegalArgumentException("Faculty ID is required to create a course");
        }
        Department dept = departmentService.getDepartmentById(course.getDepartment().getId());
        Faculty fac = facultyService.getFacultyById(course.getFaculty().getId());
        
        course.setDepartment(dept);
        course.setFaculty(fac);
        
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = getCourseById(id);
        existingCourse.setCode(updatedCourse.getCode());
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setCredits(updatedCourse.getCredits());
        existingCourse.setSemester(updatedCourse.getSemester());

        if (updatedCourse.getDepartment() != null && updatedCourse.getDepartment().getId() != null) {
            Department dept = departmentService.getDepartmentById(updatedCourse.getDepartment().getId());
            existingCourse.setDepartment(dept);
        }
        if (updatedCourse.getFaculty() != null && updatedCourse.getFaculty().getId() != null) {
            Faculty fac = facultyService.getFacultyById(updatedCourse.getFaculty().getId());
            existingCourse.setFaculty(fac);
        }

        return courseRepository.save(existingCourse);
    }

    @Transactional
    public void deleteCourse(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }
}
