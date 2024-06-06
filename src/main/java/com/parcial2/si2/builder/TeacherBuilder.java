package com.parcial2.si2.builder;

import com.parcial2.si2.model.Career;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.model.Teacher;

import java.util.List;

public class TeacherBuilder {

    private Long id;
    private String name;
    private int age;
    private String email;
    private double effectiveHours;
    private String phoneNumber;
    private String userCode;
    private String password;
    private List<Long> classes;
    private Faculty faculty;
    private Career career;

    public TeacherBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TeacherBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TeacherBuilder age(int age) {
        this.age = age;
        return this;
    }

    public TeacherBuilder email(String email) {
        this.email = email;
        return this;
    }

    public TeacherBuilder effectiveHours(double effectiveHours) {
        this.effectiveHours = effectiveHours;
        return this;
    }

    public TeacherBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public TeacherBuilder userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public TeacherBuilder password(String password) {
        this.password = password;
        return this;
    }

    public TeacherBuilder classes(List<Long> classes) {
        this.classes = classes;
        return this;
    }

    public TeacherBuilder faculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public TeacherBuilder career(Career career) {
        this.career = career;
        return this;
    }

    public Teacher build() {
        Teacher teacher = new Teacher();
        teacher.setId(this.id);
        teacher.setName(this.name);
        teacher.setAge(this.age);
        teacher.setEmail(this.email);
        teacher.setEffectiveHours(this.effectiveHours);
        teacher.setPhoneNumber(this.phoneNumber);
        teacher.setUserCode(this.userCode);
        teacher.setPassword(this.password);
        teacher.setClasses(this.classes);
        teacher.setFaculty(this.faculty);
        teacher.setCareer(this.career);
        return teacher;
    }
}