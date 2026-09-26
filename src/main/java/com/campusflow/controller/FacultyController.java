package com.campusflow.controller;

import com.campusflow.dto.faculty.FacultyRequest;
import com.campusflow.dto.faculty.FacultyResponse;
import com.campusflow.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyResponse>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PostMapping
    public ResponseEntity<FacultyResponse> createFaculty(@Valid @RequestBody FacultyRequest request) {
        FacultyResponse createdFaculty = facultyService.createFaculty(request);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable Long id, @Valid @RequestBody FacultyRequest request) {
        FacultyResponse updatedFaculty = facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
