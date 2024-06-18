package com.parcial2.si2.dto;

import com.parcial2.si2.model.Comunicado;

public class ComunicadoResponse {

    private Long id;
    private Long facultyId;
    private String titulo;
    private String imageUrl;

    // Constructores, getters y setters

    public ComunicadoResponse() {
        // Constructor vacío necesario para deserialización
    }

    public ComunicadoResponse(Long id, Long facultyId, String titulo, String imageUrl) {
        this.id = id;
        this.facultyId = facultyId;
        this.titulo = titulo;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
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

    // Conversion method from entity
    public static ComunicadoResponse fromEntity(Comunicado comunicadoEntity) {
        return new ComunicadoResponse(
                comunicadoEntity.getId(),
                comunicadoEntity.getFaculty().getId(),
                comunicadoEntity.getTitulo(),
                comunicadoEntity.getImageUrl()
        );
    }
}