package com.parcial2.si2.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Anotaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "clase_id", referencedColumnName = "id")
    private CourseClass courseClass;

    @Column(nullable = false)
    private LocalDate fecha;

    // Constructores, getters y setters

    public Anotaciones() {
        // Constructor vacío necesario para JPA
    }

    public Anotaciones(String texto, Teacher teacher, CourseClass courseClass, LocalDate fecha) {
        this.texto = texto;
        this.teacher = teacher;
        this.courseClass = courseClass;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CourseClass getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(CourseClass courseClass) {
        this.courseClass = courseClass;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}