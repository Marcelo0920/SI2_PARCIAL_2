package com.parcial2.si2.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private CourseClass clase;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseClass getClase() {
        return clase;
    }

    public void setClase(CourseClass clase) {
        this.clase = clase;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}