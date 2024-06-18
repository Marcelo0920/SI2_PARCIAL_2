package com.parcial2.si2.model;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sigla;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Career getCareer_id() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }
}