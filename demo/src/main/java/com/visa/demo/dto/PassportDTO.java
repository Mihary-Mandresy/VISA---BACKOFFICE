package com.visa.demo.dto;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class PassportDTO extends Entity<PassportDTO>{
    private String id;
    private String numero;
    private LocalDate datedelivrance;
    private LocalDate dateexpiration;

    public PassportDTO() {
        setNomTable("v_passport");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDatedelivrance() {
        return datedelivrance;
    }

    public void setDatedelivrance(LocalDate datedelivrance) {
        this.datedelivrance = datedelivrance;
    }

    public LocalDate getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
    }
}

