package com.parcial2.si2.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Admin extends Usuario {

    public Admin(){
        super();
        this.setRole(Role.ADMIN);
    }

    private LocalTime horarioEntrada;
    private LocalTime horarioSalida;

    // Getters and Setters

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalTime getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(LocalTime horarioSalida) {
        this.horarioSalida = horarioSalida;
    }
}