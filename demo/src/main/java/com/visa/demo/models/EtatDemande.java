package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class EtatDemande  extends Entity<EtatDemande> {

    public EtatDemande() {
        setNomTable("etatdemande");
        setSigle("ETATDMD");
    }
}
