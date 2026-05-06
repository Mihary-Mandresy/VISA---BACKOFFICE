package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class DemandeFicheDto extends Entity<DemandeFicheDto> {

    private String id;
    private byte[] qrcode;

    private String nomdemandeur;
    private String prenomdemandeur;

    private String typevisa;
    private String typedemande;
    private String etatdemande;

    public DemandeFicheDto() {
        setNomTable("v_fiches_demandes");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

    public String getNomdemandeur() {
        return nomdemandeur;
    }

    public void setNomdemandeur(String nomdemandeur) {
        this.nomdemandeur = nomdemandeur;
    }

    public String getPrenomdemandeur() {
        return prenomdemandeur;
    }

    public void setPrenomdemandeur(String prenomdemandeur) {
        this.prenomdemandeur = prenomdemandeur;
    }

    public String getTypevisa() {
        return typevisa;
    }

    public void setTypevisa(String typevisa) {
        this.typevisa = typevisa;
    }

    public String getTypedemande() {
        return typedemande;
    }

    public void setTypedemande(String typedemande) {
        this.typedemande = typedemande;
    }

    public String getEtatdemande() {
        return etatdemande;
    }

    public void setEtatdemande(String etatdemande) {
        this.etatdemande = etatdemande;
    }

}
