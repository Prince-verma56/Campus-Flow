package com.campusflow.repository.specification;

import com.campusflow.entity.Course;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {
    public static Specification<Course> getCourseQuery(String search, Long departmentId, Long facultyId, Integer semester) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.trim().isEmpty()) {
                String likePattern = "%" + search.trim().toLowerCase() + "%";
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), likePattern)
                ));
            }

            if (departmentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("department").get("id"), departmentId));
            }

            if (facultyId != null) {
                predicates.add(criteriaBuilder.equal(root.get("faculty").get("id"), facultyId));
            }

            if (semester != null) {
                predicates.add(criteriaBuilder.equal(root.get("semester"), semester));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
