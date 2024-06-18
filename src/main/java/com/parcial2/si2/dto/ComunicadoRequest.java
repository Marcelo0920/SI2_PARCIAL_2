package com.parcial2.si2.dto;

import com.parcial2.si2.model.Comunicado;

public class ComunicadoRequest {

    private Long facultyId;
    private String titulo;
    private String imageUrl;

    // Constructores, getters y setters

    public ComunicadoRequest() {
        // Constructor vacío necesario para deserialización
    }

    public ComunicadoRequest(Long facultyId, String titulo, String imageUrl) {
        this.facultyId = facultyId;
        this.titulo = titulo;
        this.imageUrl = imageUrl;
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

    // Conversion Methods

    public static ComunicadoRequest fromEntity(Comunicado comunicadoEntity) {
        ComunicadoRequest dto = new ComunicadoRequest();
        dto.setFacultyId(comunicadoEntity.getFaculty().getId());
        dto.setTitulo(comunicadoEntity.getTitulo());
        dto.setImageUrl(comunicadoEntity.getImageUrl());
        return dto;
    }

    public Comunicado toEntity() {
        Comunicado comunicadoEntity = new Comunicado();
        comunicadoEntity.setTitulo(this.getTitulo());
        comunicadoEntity.setImageUrl(this.getImageUrl());
        // No se establece el ID de la facultad directamente en el DTO,
        // se asume que se manejará internamente en el servicio o controlador.
        return comunicadoEntity;
    }
}