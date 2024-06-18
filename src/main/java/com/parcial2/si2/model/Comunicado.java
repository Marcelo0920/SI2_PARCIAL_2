package com.parcial2.si2.model;

import jakarta.persistence.*;

@Entity
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "image_url")
    private String imageUrl;

    // Constructores, getters y setters

    public Comunicado() {
        // Constructor vacío necesario para JPA
    }

    public Comunicado(Faculty faculty, String titulo, String imageUrl) {
        this.faculty = faculty;
        this.titulo = titulo;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}