package com.campusflow.service;

import com.campusflow.model.Student;
import com.campusflow.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor Injection (Dependency Injection)
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        // Business logic could go here (e.g., checking if email exists)
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        
        // Business logic to update only certain fields
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        // Business logic could go here (e.g., checking if student can be deleted)
        // First ensure it exists
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}
