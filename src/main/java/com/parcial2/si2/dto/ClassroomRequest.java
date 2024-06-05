package com.parcial2.si2.dto;

public class ClassroomRequest {

    private int classroomNumber;
    private Long moduleId;

    public ClassroomRequest() {
    }

    public ClassroomRequest(int classroomNumber, Long moduleId) {
        this.classroomNumber = classroomNumber;
        this.moduleId = moduleId;
    }

    // Getters and Setters

    public int getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(int classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}