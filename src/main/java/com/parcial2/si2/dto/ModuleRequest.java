package com.parcial2.si2.dto;

public class ModuleRequest {

    private int moduleNumber;
    private Long facultyId;


    // Getters and Setters

    public int getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(int moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
