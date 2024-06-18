package com.parcial2.si2.dto;

public class AttendanceRequest {
    private Long classId;
    private boolean isPresent;

    // Getters and setters
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}