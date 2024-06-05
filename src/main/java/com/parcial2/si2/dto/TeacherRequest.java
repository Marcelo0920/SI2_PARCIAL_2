package com.parcial2.si2.dto;

import java.util.List;

public class TeacherRequest {
    private String email;
    private String name;
    private String phoneNumber;
    private String userCode;
    private int age;
    private float effectiveHours;
    private String password;
    private Long facultyId;
    private Long careerId;
    private List<Long> classes;

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getEffectiveHours() {
        return effectiveHours;
    }

    public void setEffectiveHours(float effectiveHours) {
        this.effectiveHours = effectiveHours;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }

    public List<Long> getClasses() {
        return classes;
    }

    public void setClasses(List<Long> classes) {
        this.classes = classes;
    }
}