package com.parcial2.si2.model;

import jakarta.persistence.*;


import java.util.List;


@Entity
public class Teacher extends Usuario {

    public Teacher(){
        super();
        this.setRole(Role.TEACHER);
    }

    @ElementCollection
    @CollectionTable(name = "teacher_classes", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "class_id")
    private List<Long> classes;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    private Career career;


    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public List<Long> getClasses() {
        return classes;
    }

    public void setClasses(List<Long> classes) {
        this.classes = classes;
    }
}