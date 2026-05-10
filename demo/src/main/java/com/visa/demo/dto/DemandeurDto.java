package com.visa.demo.dto;

import java.time.LocalDate;

import com.visa.demo.models.Demandeur;


public class DemandeurDto extends Demandeur {
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dtn;
    private String profession;
    private String adressemada;
    private String tel;
    private String email;
    private NationaliteDTO nationalite;
    private SituationDeFamilleDTO situationdefamille;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDtn() {
        return dtn;
    }

    public void setDtn(LocalDate dtn) {
        this.dtn = dtn;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAdressemada() {
        return adressemada;
    }

    public void setAdressemada(String adressemada) {
        this.adressemada = adressemada;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
