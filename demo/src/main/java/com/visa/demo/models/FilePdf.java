package com.visa.demo.models;

import java.util.Base64;

import com.nojpa.bd.entity.Entity;

public class FilePdf extends Entity<FilePdf> {

    private String nom;
    private byte[] contenue;

    public FilePdf() {
        setNomTable("filepdf");
        setSigle("FPDF");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getContenue() {
        return contenue;
    }

    public void setContenue(byte[] contenue) {
        this.contenue = contenue;
    }


}
