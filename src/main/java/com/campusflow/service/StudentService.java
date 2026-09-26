package com.campusflow.service;

import com.campusflow.dto.student.StudentRequest;
import com.campusflow.dto.student.StudentResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Student;
import com.campusflow.exception.InvalidReferenceException;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.repository.StudentRepository;
import com.campusflow.repository.specification.StudentSpecification;
import com.campusflow.dto.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentService departmentService;

    public StudentService(StudentRepository studentRepository, DepartmentService departmentService) {
        this.studentRepository = studentRepository;
        this.departmentService = departmentService;
    }

    public Student getStudentEntityById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public PageResponse<StudentResponse> getAllStudents(String search, Long departmentId, Pageable pageable) {
        Specification<Student> spec = StudentSpecification.getStudentsQuery(search, departmentId);
        Page<Student> pageResult = studentRepository.findAll(spec, pageable);
        
        Page<StudentResponse> responsePage = pageResult.map(StudentMapper::toResponse);
        return new PageResponse<>(responsePage);
    }

    public StudentResponse getStudentById(Long id) {
        return StudentMapper.toResponse(getStudentEntityById(id));
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        Department dept;
        try {
            dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Student. " + e.getMessage());
        }

        Student student = StudentMapper.toEntity(request, dept);
        student = studentRepository.save(student);
        return StudentMapper.toResponse(student);
    }

    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student existingStudent = getStudentEntityById(id);
        
        existingStudent.setName(request.getName());
        existingStudent.setEmail(request.getEmail());
        
        if (request.getDepartmentId() != null) {
            try {
                Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
                existingStudent.setDepartment(dept);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Student. " + e.getMessage());
            }
        }

        existingStudent = studentRepository.save(existingStudent);
        return StudentMapper.toResponse(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        getStudentEntityById(id);
        studentRepository.deleteById(id);
    }
}
