package com.parcial2.si2.dto;

import com.parcial2.si2.model.*;

public class ClassRequest {

    private Long id;
    private Long subjectId;
    private Long classroomId;
    private Long careerId;
    private Long grupoId;
    private String horarioEntrada;
    private String horarioSalida;
    private String dias;
    private String ubicacion;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
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

    // Conversion Methods

    public static ClassRequest fromEntity(CourseClass courseClassEntity) {
        ClassRequest dto = new ClassRequest();
        dto.setId(courseClassEntity.getId());
        dto.setSubjectId(courseClassEntity.getSubject().getId());
        dto.setClassroomId(courseClassEntity.getClassroom().getId());
        dto.setGrupoId(courseClassEntity.getGroup().getId());
        dto.setCareerId(courseClassEntity.getCareer().getId());
        dto.setHorarioEntrada(courseClassEntity.getHorarioEntrada());
        dto.setHorarioSalida(courseClassEntity.getHorarioSalida());
        dto.setDias(courseClassEntity.getDias());
        dto.setUbicacion(courseClassEntity.getUbicacion());
        return dto;
    }

    public CourseClass toEntity() {
        CourseClass courseClassEntity = new CourseClass();
        courseClassEntity.setId(this.getId());

        Subject subject = new Subject();
        subject.setId(this.getSubjectId());
        courseClassEntity.setSubject(subject);

        Classroom classroom = new Classroom();
        classroom.setId(this.getClassroomId());
        courseClassEntity.setClassroom(classroom);

        Grupo grupo = new Grupo();
        grupo.setId(this.getGrupoId());
        courseClassEntity.setGroup(grupo);

        Career career = new Career();
        career.setId(this.getCareerId());
        courseClassEntity.setCareer(career);

        courseClassEntity.setHorarioEntrada(this.getHorarioEntrada());
        courseClassEntity.setHorarioSalida(this.getHorarioSalida());
        courseClassEntity.setDias(this.getDias());
        courseClassEntity.setUbicacion(this.getUbicacion());
        return courseClassEntity;
    }
}
