package com.visa.demo.dto;

import com.visa.demo.models.Demandeur;


public class DemandeurDto extends Demandeur {
    private NationaliteDTO nationalite;
    private SituationDeFamilleDTO situationdefamille;

    

    public NationaliteDTO getNationalite() {
        return nationalite;
    }



    public void setNationalite(NationaliteDTO nationalite) {
        this.nationalite = nationalite;
    }



    public SituationDeFamilleDTO getSituationdefamille() {
        return situationdefamille;
    }



    public void setSituationdefamille(SituationDeFamilleDTO situationdefamille) {
        this.situationdefamille = situationdefamille;
    }



    public DemandeurDto copyToDemandeurDto(Demandeur demandeur) {
        DemandeurDto demandeurDto = new DemandeurDto();

        // Copie des propriétés de base héritées de Demandeur
        demandeurDto.setId(demandeur.getId());
        demandeurDto.setNom(demandeur.getNom());
        demandeurDto.setPrenom(demandeur.getPrenom());
        demandeurDto.setDtn(demandeur.getDtn());
        demandeurDto.setProfession(demandeur.getProfession());
        demandeurDto.setAdressemada(demandeur.getAdressemada());
        demandeurDto.setTel(demandeur.getTel());
        demandeurDto.setEmail(demandeur.getEmail());
        demandeurDto.setIdsituationdefamille(demandeur.getIdsituationdefamille());
        demandeurDto.setIdnationalite(demandeur.getIdnationalite());

        // Les objets Nationalite et SituationDeFamille restent à null si vous ne les
        // chargez pas,
        // ou vous pouvez les définir séparément si vous avez les objets complets
        demandeurDto.setNationalite(null);
        demandeurDto.setSituationdefamille(null);

        return demandeurDto;
    }
}
