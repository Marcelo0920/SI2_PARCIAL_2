package com.parcial2.si2.dto;

import java.util.Date;

public class AsistenciaRequest {

    private Date date;
    private Long classId;
    private Long teacherId;

    public AsistenciaRequest() {
    }

    public AsistenciaRequest(Date date, Long classId, Long teacherId) {
        this.date = date;
        this.classId = classId;
        this.teacherId = teacherId;
    }

    // Getters y Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}