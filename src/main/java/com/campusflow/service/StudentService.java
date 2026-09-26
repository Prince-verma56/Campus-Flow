package com.campusflow.service;

import com.campusflow.entity.Student;
import com.campusflow.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // By default, operations are read-only to optimize database access
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

    @Transactional // Overrides class-level readOnly to allow database writes
    public Student createStudent(Student student) {
        // Business logic could go here (e.g., checking if email exists)
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        
        // Business logic to update only certain fields
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        
        // Because of @Transactional, Hibernate automatically tracks 'existingStudent'
        // and generates an UPDATE statement when the transaction commits.
        // Explicitly calling save() is good practice, though technically optional here.
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        // Business logic could go here (e.g., checking if student can be deleted)
        // First ensure it exists
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}
