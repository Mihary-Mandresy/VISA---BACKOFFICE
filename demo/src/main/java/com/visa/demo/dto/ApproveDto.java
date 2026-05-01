package com.visa.demo.dto;

import java.time.LocalDate;

public class ApproveDto {

    private String iddemande;
    private LocalDate datedebut;
    private LocalDate datefin;

    public ApproveDto(String iddemande, LocalDate datedebut, LocalDate datefin) {
        this.iddemande = iddemande;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

}
