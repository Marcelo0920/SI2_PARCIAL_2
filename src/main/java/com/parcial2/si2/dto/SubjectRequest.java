package com.parcial2.si2.dto;

import com.parcial2.si2.model.Subject;

public class SubjectRequest {

    private Long id;
    private String name;
    private String sigla;
    private Long careerId;


    // Constructor por defecto
    public SubjectRequest() {
    }

    public SubjectRequest(Long id, String name, String sigla, Long careerId) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
        this.careerId = careerId;
    }

    // Getters y Setters

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

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }

    // Método de utilidad para convertir entidad a DTO
    public static SubjectRequest fromEntity(Subject subject) {
        return new SubjectRequest(
                subject.getId(),
                subject.getName(),
                subject.getSigla(),
                subject.getCareer_id() != null ? subject.getCareer_id().getId() : null
        );
    }

    // Método de utilidad para convertir DTO a entidad
    public Subject toEntity() {
        Subject subject = new Subject();
        subject.setId(this.id);
        subject.setName(this.name);
        subject.setSigla(this.sigla);
        // No asignamos Career aquí, se maneja en el servicio
        return subject;
    }
}