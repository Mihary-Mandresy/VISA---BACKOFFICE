package com.visa.demo.dto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nojpa.bd.connexion.DbConnexe;
import com.nojpa.bd.entity.Entity;

public class DemandeDetailDto extends Entity<DemandeDetailDto> {

    private byte[] qrcode;
    private LocalDate datecreation;

    // demandeur
    private String iddemandeur;

    private String nomdemandeur;
    private String prenomdemandeur;
    private LocalDate dtndemandeur;
    private String profession;
    private String adressemada;
    private String telephone;
    private String email;

    // nationalite
    private String idnationalite;
    private String libnationalite;

    // situation familiale
    private String idsituationfamiliale;
    private String libsituationfamiliale;

    // passport
    private String idpassport;
    private String numeropassport;
    private LocalDate datedelivrancepassport;
    private LocalDate dateexppassport;

    // visatransformable
    private String idvisatransformable;
    private String referencevt;
    private LocalDate dateentreemada;
    private LocalDate dateexpvisatransformable;
    private String lieuentree;

    private String idtypevisa;
    private String idtypedemande;

    private String typevisa;
    private String typedemande;

    public DemandeDetailDto() {
        setNomTable("v_demandes_details");
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }

    public String getIddemandeur() {
        return iddemandeur;
    }

    public void setIddemandeur(String iddemandeur) {
        this.iddemandeur = iddemandeur;
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

    public LocalDate getDtndemandeur() {
        return dtndemandeur;
    }

    public void setDtndemandeur(LocalDate dtndemandeur) {
        this.dtndemandeur = dtndemandeur;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdnationalite() {
        return idnationalite;
    }

    public void setIdnationalite(String idnationalite) {
        this.idnationalite = idnationalite;
    }

    public String getLibnationalite() {
        return libnationalite;
    }

    public void setLibnationalite(String libnationalite) {
        this.libnationalite = libnationalite;
    }

    public String getIdsituationfamiliale() {
        return idsituationfamiliale;
    }

    public void setIdsituationfamiliale(String idsituationfamiliale) {
        this.idsituationfamiliale = idsituationfamiliale;
    }

    public String getLibsituationfamiliale() {
        return libsituationfamiliale;
    }

    public void setLibsituationfamiliale(String libsituationfamiliale) {
        this.libsituationfamiliale = libsituationfamiliale;
    }

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public String getNumeropassport() {
        return numeropassport;
    }

    public void setNumeropassport(String numeropassport) {
        this.numeropassport = numeropassport;
    }

    public LocalDate getDatedelivrancepassport() {
        return datedelivrancepassport;
    }

    public void setDatedelivrancepassport(LocalDate datedelivrancepassport) {
        this.datedelivrancepassport = datedelivrancepassport;
    }

    public LocalDate getDateexpirationpassport() {
        return dateexppassport;
    }

    public void setDateexpirationpassport(LocalDate dateexppassport) {
        this.dateexppassport = dateexppassport;
    }

    public String getIdvisatransformable() {
        return idvisatransformable;
    }

    public void setIdvisatransformable(String idvisatransformable) {
        this.idvisatransformable = idvisatransformable;
    }

    public String getReferencevt() {
        return referencevt;
    }

    public void setReferencevt(String referencevt) {
        this.referencevt = referencevt;
    }

    public LocalDate getDateentreemada() {
        return dateentreemada;
    }

    public void setDateentreemada(LocalDate dateentreemada) {
        this.dateentreemada = dateentreemada;
    }

    public LocalDate getDateexpvisatransformable() {
        return dateexpvisatransformable;
    }

    public void setDateexpvisatransformable(LocalDate dateexpvisatransformable) {
        this.dateexpvisatransformable = dateexpvisatransformable;
    }

    public String getLieuentree() {
        return lieuentree;
    }

    public void setLieuentree(String lieuentree) {
        this.lieuentree = lieuentree;
    }

    public String getTypevisa() {
        return typevisa;
    }

    public String getIdtypevisa() {
        return idtypevisa;
    }

    public void setIdtypevisa(String idtypevisa) {
        this.idtypevisa = idtypevisa;
    }

    public void setTypevisa(String typevisa) {
        this.typevisa = typevisa;
    }

    public String getIdtypedemande() {
        return idtypedemande;
    }

    public void setIdtypedemande(String idtypedemande) {
        this.idtypedemande = idtypedemande;
    }

    public String getTypedemande() {
        return typedemande;
    }

    public void setTypedemande(String typedemande) {
        this.typedemande = typedemande;
    }

    public LocalDate getDateexppassport() {
        return dateexppassport;
    }

    public void setDateexppassport(LocalDate dateexppassport) {
        this.dateexppassport = dateexppassport;
    }
    public List<DemandeDetailDto> findAllByIddemandeur(Connection c, String iddemandeur) throws Exception {

    if (iddemandeur == null || iddemandeur.trim().isEmpty()) {
        throw new Exception("iddemandeur ne doit pas etre null ou vide");
    }

    List<DemandeDetailDto> results = new ArrayList<>();

    String query = "SELECT * FROM v_demandes_details WHERE iddemandeur = ?";

    boolean isClosable = false;

    PreparedStatement ps = null;
    ResultSet rs = null;

    if (c == null) {
        c = new DbConnexe().getConnection();
        isClosable = true;
    }

    try {

        ps = c.prepareStatement(query);
        ps.setString(1, iddemandeur);

        rs = ps.executeQuery();

        while (rs.next()) {

            DemandeDetailDto dto = createByResultSet(rs);

            results.add(dto);
        }

    } catch (Exception e) {

        throw new Exception("Erreur lors de la recuperation des demandes", e);

    } finally {

        if (rs != null) {
            rs.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (isClosable && c != null) {
            c.close();
        }
    }

    return results;
}
    public DemandeDetailDto createByResultSet(ResultSet rs) throws Exception {
        if (rs == null) {
            throw new Exception("le resultset ne doit pas etre null pour pouvoir construire un objet DemandeDetailDto");
        }

        DemandeDetailDto dto = new DemandeDetailDto();

        dto.setId(rs.getString("id"));
        // demandeur
        dto.setQrcode(rs.getBytes("qrcode"));
        dto.setIddemandeur(rs.getString("iddemandeur"));

        dto.setNomdemandeur(rs.getString("nomdemandeur"));
        dto.setPrenomdemandeur(rs.getString("prenomdemandeur"));

        Date dtnDemandeur = rs.getDate("dtndemandeur");
        if (dtnDemandeur != null) {
            dto.setDtndemandeur(dtnDemandeur.toLocalDate());
        }

        dto.setProfession(rs.getString("profession"));
        dto.setAdressemada(rs.getString("adressemada"));
        dto.setTelephone(rs.getString("telephone"));
        dto.setEmail(rs.getString("email"));

        // nationalite
        dto.setIdnationalite(rs.getString("idnationalite"));
        dto.setLibnationalite(rs.getString("libnationalite"));

        // situation familiale
        dto.setIdsituationfamiliale(rs.getString("idsituationfamiliale"));
        dto.setLibsituationfamiliale(rs.getString("libsituationfamiliale"));

        // passport
        dto.setIdpassport(rs.getString("idpassport"));
        dto.setNumeropassport(rs.getString("numeropassport"));

        Date dateDelivrancePassport = rs.getDate("datedelivrancepassport");
        if (dateDelivrancePassport != null) {
            dto.setDatedelivrancepassport(dateDelivrancePassport.toLocalDate());
        }

        Date dateExpPassport = rs.getDate("dateexppassport");
        if (dateExpPassport != null) {
            dto.setDateexppassport(dateExpPassport.toLocalDate());
        }

        // visa transformable
        dto.setIdvisatransformable(rs.getString("idvisatransformable"));
        dto.setReferencevt(rs.getString("referencevt"));

        Date dateEntreeMada = rs.getDate("dateentreemada");
        if (dateEntreeMada != null) {
            dto.setDateentreemada(dateEntreeMada.toLocalDate());
        }

        Date dateExpVisaTransformable = rs.getDate("dateexpvisatransformable");
        if (dateExpVisaTransformable != null) {
            dto.setDateexpvisatransformable(dateExpVisaTransformable.toLocalDate());
        }

        dto.setLieuentree(rs.getString("lieuentree"));
        dto.setIdtypevisa(rs.getString("idtypevisa"));
        dto.setIdtypedemande(rs.getString("idtypedemande"));

        dto.setTypevisa(rs.getString("typevisa"));
        dto.setTypedemande(rs.getString("typedemande"));

        return dto;
    }

}
