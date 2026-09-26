package com.campusflow.repository;

import com.campusflow.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentRepository() {
        // Add some dummy data for initial testing
        save(new Student(null, "Alice Johnson", "alice@example.com"));
        save(new Student(null, "Bob Smith", "bob@example.com"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.getAndIncrement());
        }
        students.put(student.getId(), student);
        return student;
    }

    public void deleteById(Long id) {
        students.remove(id);
    }
}
