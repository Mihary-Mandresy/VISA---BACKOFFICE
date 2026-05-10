package com.visa.demo.dto;
import com.visa.demo.models.EtatDemande;

public class EtatDemandeDto {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String libelle;
    public String getLibelle() {
        return libelle;
    }
     public void copierDepuisEtatDemande(EtatDemande etatDemande) {
        if (etatDemande == null) {
            return;
        }

        this.id = etatDemande.getId();
        this.libelle = etatDemande.getLibelle();
    }

}

