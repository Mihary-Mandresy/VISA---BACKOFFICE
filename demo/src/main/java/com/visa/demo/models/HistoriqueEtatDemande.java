package com.visa.demo.models;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class HistoriqueEtatDemande extends Entity<HistoriqueEtatDemande> {
 
    private LocalDate daty;
    private String idetatdemande;
    private String iddemande;

    public HistoriqueEtatDemande() {
        setSigle("HISTED");
        setNomTable("historiqueetatdemande");
    }

    public LocalDate getDaty() {
        return daty;
    }

    public void setDaty(LocalDate daty) {
        this.daty = daty;
    }

    public String getIdetatdemande() {
        return idetatdemande;
    }

    public void setIdetatdemande(String idetatdemande) {
        this.idetatdemande = idetatdemande;
    }

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
    }

}
