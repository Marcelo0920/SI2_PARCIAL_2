package com.parcial2.si2.dto;

import com.parcial2.si2.model.Anotaciones;
import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.model.Teacher;

import java.time.LocalDate;

public class AnotacionRequest {

    private Long id; // Añadir campo id
    private String texto;
    private Long teacherId;
    private Long courseClassId;
    private LocalDate fecha; // Añadir campo fecha

    // Constructores, getters y setters

    public AnotacionRequest() {
        // Constructor vacío necesario para deserialización
    }

    public AnotacionRequest(Long id, String texto, Long teacherId, Long courseClassId, LocalDate fecha) {
        this.id = id;
        this.texto = texto;
        this.teacherId = teacherId;
        this.courseClassId = courseClassId;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // Conversion Methods
    public Anotaciones toEntity(Teacher teacher, CourseClass courseClass) {
        return new Anotaciones(this.texto, teacher, courseClass, LocalDate.now());
    }

    public static AnotacionRequest fromEntity(Anotaciones anotaciones) {
        return new AnotacionRequest(
                anotaciones.getId(), // Incluir id
                anotaciones.getTexto(),
                anotaciones.getTeacher().getId(),
                anotaciones.getCourseClass().getId(),
                anotaciones.getFecha() // Incluir fecha
        );
    }
}