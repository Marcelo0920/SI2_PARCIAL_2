package com.parcial2.si2.builder;

import com.parcial2.si2.model.Admin;

import java.time.LocalTime;

public class AdminBuilder {
    private Long id;
    private String name;
    private int age;
    private String email;
    private double effectiveHours;
    private String phoneNumber;
    private String userCode;
    private String password;
    private LocalTime horarioEntrada;
    private LocalTime horarioSalida;

    public AdminBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AdminBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AdminBuilder age(int age) {
        this.age = age;
        return this;
    }

    public AdminBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AdminBuilder effectiveHours(double effectiveHours) {
        this.effectiveHours = effectiveHours;
        return this;
    }

    public AdminBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AdminBuilder userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public AdminBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AdminBuilder horarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
        return this;
    }

    public AdminBuilder horarioSalida(LocalTime horarioSalida) {
        this.horarioSalida = horarioSalida;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setId(this.id);
        admin.setName(this.name);
        admin.setAge(this.age);
        admin.setEmail(this.email);
        admin.setEffectiveHours(this.effectiveHours);
        admin.setPhoneNumber(this.phoneNumber);
        admin.setUserCode(this.userCode);
        admin.setPassword(this.password);
        admin.setHorarioEntrada(this.horarioEntrada);
        admin.setHorarioSalida(this.horarioSalida);
        return admin;
    }
}
