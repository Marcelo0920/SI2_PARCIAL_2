package com.parcial2.si2.dto;

public class AnotacionFilterRequest {

    private Long teacherId;
    private Long courseClassId;

    public AnotacionFilterRequest() {
        // Constructor vacío necesario para deserialización
    }

    public AnotacionFilterRequest(Long teacherId, Long courseClassId) {
        this.teacherId = teacherId;
        this.courseClassId = courseClassId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(Long courseClassId) {
        this.courseClassId = courseClassId;
    }

    // Opcional: Puedes sobrescribir el método toString() para facilitar la depuración y la impresión de información.
    @Override
    public String toString() {
        return "AnotacionFilterRequest{" +
                "teacherId=" + teacherId +
                ", courseClassId=" + courseClassId +
                '}';
    }
}