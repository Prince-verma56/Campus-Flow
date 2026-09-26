package com.campusflow.service;

import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;
import com.campusflow.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final DepartmentService departmentService;

    public FacultyService(FacultyRepository facultyRepository, DepartmentService departmentService) {
        this.facultyRepository = facultyRepository;
        this.departmentService = departmentService;
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found with id: " + id));
    }

    @Transactional
    public Faculty createFaculty(Faculty faculty) {
        if (faculty.getDepartment() == null || faculty.getDepartment().getId() == null) {
            throw new IllegalArgumentException("Department ID is required to create a faculty");
        }
        Department dept = departmentService.getDepartmentById(faculty.getDepartment().getId());
        faculty.setDepartment(dept);
        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long id, Faculty updatedFaculty) {
        Faculty existingFaculty = getFacultyById(id);
        existingFaculty.setName(updatedFaculty.getName());
        existingFaculty.setEmail(updatedFaculty.getEmail());
        existingFaculty.setEmployeeNumber(updatedFaculty.getEmployeeNumber());

        if (updatedFaculty.getDepartment() != null && updatedFaculty.getDepartment().getId() != null) {
            Department dept = departmentService.getDepartmentById(updatedFaculty.getDepartment().getId());
            existingFaculty.setDepartment(dept);
        }

        return facultyRepository.save(existingFaculty);
    }

    @Transactional
    public void deleteFaculty(Long id) {
        getFacultyById(id);
        facultyRepository.deleteById(id);
    }
}
