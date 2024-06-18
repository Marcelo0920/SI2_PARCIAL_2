package com.parcial2.si2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private CourseClass clase;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "is_present")
    private Boolean isPresent;

    @Column(name = "licencia")
    private Boolean licencia;

    @Column(name = "detalle_licencia")
    private String detalleLicencia;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseClass getClase() {
        return clase;
    }

    public void setClase(CourseClass clase) {
        this.clase = clase;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public Boolean getLicencia() {
        return licencia;
    }

    public void setLicencia(Boolean licencia) {
        this.licencia = licencia;
    }

    public String getDetalleLicencia() {
        return detalleLicencia;
    }

    public void setDetalleLicencia(String detalleLicencia) {
        this.detalleLicencia = detalleLicencia;
    }
}