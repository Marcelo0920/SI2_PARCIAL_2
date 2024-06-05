package com.parcial2.si2.dto;

public class ClassRequest {

    private Long subjectId;
    private Long classroomId;
    private Long grupoId;

    public ClassRequest() {
    }

    public ClassRequest(Long subjectId, Long classroomId, Long grupoId) {
        this.subjectId = subjectId;
        this.classroomId = classroomId;
        this.grupoId = grupoId;
    }

    // Getters and Setters

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

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}