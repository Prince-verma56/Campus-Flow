package com.campusflow.repository;

import com.campusflow.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<Student> {
    // Spring Data JPA dynamically generates the implementation for all standard CRUD methods
    // (findAll, findById, save, deleteById, etc.) at runtime!
}
