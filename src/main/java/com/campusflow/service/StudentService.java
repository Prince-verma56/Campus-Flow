package com.campusflow.service;

import com.campusflow.entity.Department;
import com.campusflow.entity.Student;
import com.campusflow.repository.StudentRepository;
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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    @Transactional
    public Student createStudent(Student student) {
        if (student.getDepartment() == null || student.getDepartment().getId() == null) {
            throw new IllegalArgumentException("Department ID is required to create a student");
        }
        Department dept = departmentService.getDepartmentById(student.getDepartment().getId());
        student.setDepartment(dept);
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        
        if (updatedStudent.getDepartment() != null && updatedStudent.getDepartment().getId() != null) {
            Department dept = departmentService.getDepartmentById(updatedStudent.getDepartment().getId());
            existingStudent.setDepartment(dept);
        }

        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}
