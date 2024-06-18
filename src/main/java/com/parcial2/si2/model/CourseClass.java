package com.parcial2.si2.model;

import jakarta.persistence.*;

@Entity
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private Grupo grupo;

    @Column(name = "horario_entrada", nullable = false)
    private String horarioEntrada;

    @Column(name = "horario_salida", nullable = false)
    private String horarioSalida;

    @Column(name = "dias", nullable = false)
    private String dias; // Guardar los días separados por comas

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    private Career career;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Grupo getGroup() {
        return grupo;
    }

    public void setGroup(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }
}