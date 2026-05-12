package com.visa.demo.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nojpa.bd.entity.Entity;

public class Demandeur extends Entity<Demandeur> {

    private String nom;
    private String prenom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dtn;
    private String profession;
    private String adressemada;
    private String tel;
    private String email;
    private String idsituationdefamille;
    private String idnationalite;
    private byte[] pdp = new byte[0];
    private byte[] signatures = new byte[0];

    public Demandeur(String nom, String prenom, LocalDate dtn, String profession, String adressemada, String tel, String email,
            String idsituationdefamille, String idnationalite) {
        this();

        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.profession = profession;
        this.adressemada = adressemada;
        this.tel = tel;
        this.email = email;
        this.idsituationdefamille = idsituationdefamille;
        this.idnationalite = idnationalite;
    }

    public Demandeur() {
        setNomTable("demandeur");
        setSigle("DMDR");
    }

    public String getNom() {
        return nom;
    }

    public void setPdp(byte[] pdp) {
        this.pdp = pdp;
    }
    public void setSignatures(byte[] signatures) {
        this.signatures = signatures;
    }

    public byte[] getPdp() {
        return pdp;
    }
    public byte[] getSignatures() {
        return signatures;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getIdsituationdefamille() {
        return idsituationdefamille;
    }

    public void setIdsituationdefamille(String idsituationdefamille) {
        this.idsituationdefamille = idsituationdefamille;
    }

    public String getIdnationalite() {
        return idnationalite;
    }

    public void setIdnationalite(String idnationalite) {
        this.idnationalite = idnationalite;
    }

    public String getAdressemada() {
        return adressemada;
    }

    public void setAdressemada(String adressemada) {
        this.adressemada = adressemada;
    }

    public LocalDate getDtn() {
        return dtn;
    }

    public void setDtn(LocalDate dtn) {
        this.dtn = dtn;
    }

    public void savePhotoAndSignature(Connection c) throws Exception {

        PreparedStatement stmt = null;

        try {

            String sql = """
                UPDATE demandeur
                SET
                    pdp = ?,
                    signatures = ?
                WHERE id = ?
            """;

            stmt = c.prepareStatement(sql);

            // photo
            stmt.setBytes(1, this.getPdp());

            // signature
            stmt.setBytes(2, this.getSignatures());

            // id demandeur
            stmt.setString(3, this.getId());

            stmt.executeUpdate();

        } catch (Exception e) {

            throw e;

        } finally {

            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
