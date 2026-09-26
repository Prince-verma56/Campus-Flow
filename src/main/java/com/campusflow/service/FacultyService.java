package com.campusflow.service;

import com.campusflow.dto.faculty.FacultyRequest;
import com.campusflow.dto.faculty.FacultyResponse;
import com.campusflow.entity.Department;
import com.campusflow.entity.Faculty;
import com.campusflow.exception.InvalidReferenceException;
import com.campusflow.exception.ResourceNotFoundException;
import com.campusflow.mapper.FacultyMapper;
import com.campusflow.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final DepartmentService departmentService;

    public FacultyService(FacultyRepository facultyRepository, DepartmentService departmentService) {
        this.facultyRepository = facultyRepository;
        this.departmentService = departmentService;
    }

    public Faculty getFacultyEntityById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id: " + id));
    }

    public List<FacultyResponse> getAllFaculty() {
        return facultyRepository.findAll().stream()
                .map(FacultyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FacultyResponse getFacultyById(Long id) {
        return FacultyMapper.toResponse(getFacultyEntityById(id));
    }

    @Transactional
    public FacultyResponse createFaculty(FacultyRequest request) {
        Department dept;
        try {
            dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidReferenceException("Cannot create Faculty. " + e.getMessage());
        }
        
        Faculty faculty = FacultyMapper.toEntity(request, dept);
        faculty = facultyRepository.save(faculty);
        return FacultyMapper.toResponse(faculty);
    }

    @Transactional
    public FacultyResponse updateFaculty(Long id, FacultyRequest request) {
        Faculty existingFaculty = getFacultyEntityById(id);
        
        existingFaculty.setName(request.getName());
        existingFaculty.setEmail(request.getEmail());
        existingFaculty.setEmployeeNumber(request.getEmployeeNumber());
        
        if (request.getDepartmentId() != null) {
            try {
                Department dept = departmentService.getDepartmentEntityById(request.getDepartmentId());
                existingFaculty.setDepartment(dept);
            } catch (ResourceNotFoundException e) {
                throw new InvalidReferenceException("Cannot update Faculty. " + e.getMessage());
            }
        }

        existingFaculty = facultyRepository.save(existingFaculty);
        return FacultyMapper.toResponse(existingFaculty);
    }

    @Transactional
    public void deleteFaculty(Long id) {
        getFacultyEntityById(id);
        facultyRepository.deleteById(id);
    }
}
