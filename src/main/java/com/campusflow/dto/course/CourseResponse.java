package com.campusflow.dto.course;

public class CourseResponse {
    private Long id;
    private String code;
    private String name;
    private int credits;
    private String semester;
    private Long departmentId;
    private Long facultyId;

    public CourseResponse() {}

    public CourseResponse(Long id, String code, String name, int credits, String semester, Long departmentId, Long facultyId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.departmentId = departmentId;
        this.facultyId = facultyId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }
}
