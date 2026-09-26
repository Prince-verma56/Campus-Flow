package com.campusflow.service;

import com.campusflow.dto.course.CourseRequest;
import com.campusflow.dto.course.CourseResponse;
import com.campusflow.entity.Course;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;
import com.campusflow.mapper.CourseMapper;
import com.campusflow.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Course getCourseEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        return CourseMapper.toResponse(getCourseEntityById(id));
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
        Faculty faculty = facultyService.getFacultyEntityById(request.getFacultyId());
        
        Course course = CourseMapper.toEntity(request, dept, faculty);
        course = courseRepository.save(course);
        return CourseMapper.toResponse(course);
    }

    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course existingCourse = getCourseEntityById(id);
        
        existingCourse.setCode(request.getCode());
        existingCourse.setName(request.getName());
        existingCourse.setCredits(request.getCredits());
        existingCourse.setSemester(request.getSemester());
        
        if (request.getDepartmentId() != null) {
            Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
            existingCourse.setDepartment(dept);
        }
        
        if (request.getFacultyId() != null) {
            Faculty faculty = facultyService.getFacultyEntityById(request.getFacultyId());
            existingCourse.setFaculty(faculty);
        }

        existingCourse = courseRepository.save(existingCourse);
        return CourseMapper.toResponse(existingCourse);
    }

    @Transactional
    public void deleteCourse(Long id) {
        getCourseEntityById(id);
        courseRepository.deleteById(id);
    }
}
