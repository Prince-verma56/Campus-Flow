package com.campusflow.repository;

import com.campusflow.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<Course> {
}
