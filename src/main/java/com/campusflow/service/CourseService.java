package com.campusflow.service;

import com.campusflow.dto.course.CourseRequest;
import com.campusflow.dto.course.CourseResponse;
import com.campusflow.entity.Course;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;
import com.campusflow.exception.InvalidReferenceException;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.mapper.CourseMapper;
import com.campusflow.repository.CourseRepository;
import com.campusflow.repository.specification.CourseSpecification;
import com.campusflow.dto.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Course getCourseEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public PageResponse<CourseResponse> getAllCourses(String search, Long departmentId, Long facultyId, Integer semester, Pageable pageable) {
        Specification<Course> spec = CourseSpecification.getCourseQuery(search, departmentId, facultyId, semester);
        Page<Course> pageResult = courseRepository.findAll(spec, pageable);
        
        Page<CourseResponse> responsePage = pageResult.map(CourseMapper::toResponse);
        return new PageResponse<>(responsePage);
    }

    public CourseResponse getCourseById(Long id) {
        return CourseMapper.toResponse(getCourseEntityById(id));
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Department dept;
        Faculty faculty;
        try {
            dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Course. " + e.getMessage());
        }
        
        try {
            faculty = facultyService.getFacultyEntityById(request.getFacultyId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Course. " + e.getMessage());
        }
        
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
            try {
                Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
                existingCourse.setDepartment(dept);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Course. " + e.getMessage());
            }
        }
        
        if (request.getFacultyId() != null) {
            try {
                Faculty faculty = facultyService.getFacultyEntityById(request.getFacultyId());
                existingCourse.setFaculty(faculty);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Course. " + e.getMessage());
            }
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
