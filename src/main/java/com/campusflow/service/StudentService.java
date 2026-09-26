package com.campusflow.service;

import com.campusflow.dto.student.StudentRequest;
import com.campusflow.dto.student.StudentResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Student;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {
        return StudentMapper.toResponse(getStudentEntityById(id));
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
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
            Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
            existingStudent.setDepartment(dept);
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
