package com.visa.demo.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import com.nojpa.bd.entity.Entity;

public class Demande extends Entity<Demande> {

    private String idtypedemande;
    private String idvisatransformable;
    private String idpassport;
    private String iddemandeur;
    private String idtypevisa;
    private LocalDate datecreation;
    private String idetatdemande;

    private String idoriginal;

    public Demande() {
        setNomTable("demande");
        setSigle("DMD");
    }

    /**
     * 
     * @param c
     * @param etatCivil
     * @param passport
     * @param visa
     * @return
     * @throws Exception
     */
    public Demande save(Connection c, Demandeur demandeur, Passport passport, Visatransformable visa,
            List<String> dossiersStandard, List<String> dossiersSup, String idTypeDemande, String idTypeVisa,
            LocalDate date)
            throws Exception {
        try {
            c.setAutoCommit(false);

            // Insertion Detaitl
            demandeur.insert(c);

            passport.setIddemandeur(demandeur.getId());
            passport.insert(c);

            visa.setIddemandeur(demandeur.getId());
            visa.setIdpassport(passport.getId());
            visa.insert(c);

            // Insertion Demande
            Demande demande = new Demande();
            demande.setIddemandeur(demandeur.getId());
            demande.setIdpassport(passport.getId());
            demande.setIdvisatransformable(visa.getId());
            demande.setDatecreation(date);
            demande.setIdetatdemande("ETATDMD000001");

            // DANGER mila ovaina
            demande.setIdtypedemande(idTypeDemande);
            demande.setIdtypevisa(idTypeVisa);

            demande.insert(c);

            // Insertion dossier fournit

            for (String idStandard : dossiersStandard) {
                CheckDossierStandard checkdossier = new CheckDossierStandard();
                checkdossier.setIddemande(demande.getId());
                checkdossier.setIddossierstandard(idStandard);
                checkdossier.setExist(true);

                checkdossier.insert(c);
            }

            for (String idsupplementaire : dossiersSup) {
                CheckDossierSupplementaire checkdossier = new CheckDossierSupplementaire();
                checkdossier.setIddemande(demande.getId());
                checkdossier.setIddossiersupplementaire(idsupplementaire);
                checkdossier.setExist(true);

                checkdossier.insert(c);
            }

            c.commit();

            c.setAutoCommit(true);

            return demande;
        } catch (Exception e) {
            c.rollback();
            throw e;
        }
    }

    // =========== Verification (Mbola tsy vita tsara ) ===========

    public boolean isFullStandard(Connection c, List<String> idStandard) throws Exception {
        return new DossierStandard().findAll(c).stream()
                .allMatch(e -> idStandard.contains(e.getId()));
    }

    public boolean isFullSup(Connection c, List<String> idSup, String idStatut) throws Exception {
        return new DossierSupplementaire().select(c, String.format("idstatutvisa like \"%s\"", idStatut), null).stream()
                .allMatch(e -> idSup.contains(e.getId()));
    }

    // =========== Verification ===========

    public String getIdtypedemande() {
        return idtypedemande;
    }

    public void setIdtypedemande(String idtypedemande) {
        this.idtypedemande = idtypedemande;
    }

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        if (datecreation == null) {
            this.datecreation = LocalDate.now();
        } else {
            this.datecreation = datecreation;
        }
    }

    public String getIdvisatransformable() {
        return idvisatransformable;
    }

    public void setIdvisatransformable(String idvisatransformable) {
        this.idvisatransformable = idvisatransformable;
    }

    public String getIdtypevisa() {
        return idtypevisa;
    }

    public void setIdtypevisa(String idtypevisa) {
        this.idtypevisa = idtypevisa;
    }

    public String getIddemandeur() {
        return iddemandeur;
    }

    public void setIddemandeur(String iddemandeur) {
        this.iddemandeur = iddemandeur;
    }

    public String getIdoriginal() {
        return idoriginal;
    }

    public void setIdoriginal(String idoriginal) {
        this.idoriginal = idoriginal;
    }

    public String getIdetatdemande() {
        return idetatdemande;
    }

    public void setIdetatdemande(String idetatdemande) {
        this.idetatdemande = idetatdemande;
    }

}
