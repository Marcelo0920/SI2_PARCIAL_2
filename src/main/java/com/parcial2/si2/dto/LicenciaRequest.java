package com.parcial2.si2.dto;

import java.util.Date;

public class LicenciaRequest {

    private Date date;
    private Long teacherId;

    public LicenciaRequest() {
    }

    public LicenciaRequest(Date date, Long teacherId) {
        this.date = date;
        this.teacherId = teacherId;
    }

    // Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}