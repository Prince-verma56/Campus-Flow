package com.campusflow.dto.faculty;

public class FacultyResponse {
    private Long id;
    private String name;
    private String email;
    private String employeeNumber;
    private Long departmentId;

    public FacultyResponse() {}

    public FacultyResponse(Long id, String name, String email, String employeeNumber, Long departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.employeeNumber = employeeNumber;
        this.departmentId = departmentId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
