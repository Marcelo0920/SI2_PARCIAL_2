package com.parcial2.si2.model;

import jakarta.persistence.*;

@Entity
public class Module{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private int moduleNumber;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(int moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public Faculty getFaculty_id() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}