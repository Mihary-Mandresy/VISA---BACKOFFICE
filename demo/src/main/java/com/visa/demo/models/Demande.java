package com.visa.demo.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nojpa.bd.entity.Entity;
import com.visa.demo.models.obj.DemandeObj;

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
    public Demande save(Connection c, String idoriginal,Demandeur demandeur, Passport passport, Visatransformable visa,
            List<String> dossiersStandard, List<String> dossiersSup, String idTypeDemande,
            String idTypeVisa,String etatdemande,
            LocalDate date)
            throws Exception {
        try {
            c.setAutoCommit(false);
            boolean demandeurExist = false;
            if(demandeur.getId() != null && !demandeur.getId().isEmpty()){
                demandeurExist =true;
            }
            // Insertion Detaitl
            if(!demandeurExist){
                demandeur.insert(c);
            }
            passport.setIddemandeur(demandeur.getId());
            if(visa.getId() == null){
                passport.insert(c);
            }
            visa.setIddemandeur(demandeur.getId());
            visa.setIdpassport(passport.getId());
            if(visa.getId() == null){
                visa.insert(c);
            }

            // Insertion Demande
            Demande demande = new Demande();
            demande.setIddemandeur(demandeur.getId());
            demande.setIdpassport(passport.getId());
            demande.setIdvisatransformable(visa.getId());
            demande.setDatecreation(date);
            demande.setIdetatdemande(etatdemande);
            demande.setIdoriginal(idoriginal);
            // DANGER mila ovaina
            demande.setIdtypedemande(idTypeDemande);
            demande.setIdtypevisa(idTypeVisa);

            demande.insert(c);

            // Insertion dossier fournit
            if(!demandeurExist){
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

    /**
     *
     * @param c
     * @param etatCivil
     * @param passport
     * @param visa
     * @return
     * @throws Exception
     */
    public Demande update(Connection c, String iddemande,Demandeur demandeur, Passport passport, Visatransformable visa,
            List<String> dossiersStandard, List<String> dossiersSup,List<String>dossiersStandardConcatIdChecks, List<String> dossiersSupConcatIdCheck, String idTypeDemande, String idTypeVisa,String idTypeVisaPrecedent,
            LocalDate date)
            throws Exception {
        try {
            c.setAutoCommit(false);

            // update demandeur
            demandeur.update(c);

            passport.setIddemandeur(demandeur.getId());
            passport.update(c);

            visa.setIddemandeur(demandeur.getId());
            visa.setIdpassport(passport.getId());
            visa.update(c);

            // update Demande
            Demande demande = new Demande();
            demande.setId(iddemande);
            demande.setIddemandeur(demandeur.getId());
            demande.setIdpassport(passport.getId());
            demande.setIdvisatransformable(visa.getId());
            demande.setDatecreation(date);
            demande.setIdetatdemande("ETATDMD000001");

            // DANGER mila ovaina
            demande.setIdtypedemande(idTypeDemande);
            demande.setIdtypevisa(idTypeVisa);

            demande.update(c);

            // update dossier fourni
            insertAfterCheck(c, new CheckDossierStandard(), iddemande, dossiersStandard,dossiersStandardConcatIdChecks);
            updateAfterCheck(c,new CheckDossierStandard(), iddemande, dossiersStandard,dossiersStandardConcatIdChecks,idTypeVisaPrecedent,idTypeVisa);

            insertAfterCheck(c, new CheckDossierSupplementaire(), iddemande, dossiersSup, dossiersSupConcatIdCheck);
            updateAfterCheck(c,new CheckDossierSupplementaire(), iddemande, dossiersSup, dossiersSupConcatIdCheck ,idTypeVisaPrecedent,idTypeVisa);
            c.commit();

            c.setAutoCommit(true);

            return demande;
        } catch (Exception e) {
            c.rollback();
            throw e;
        }
    }

    public Map<String, String> separateIdCheckDossierAndIdDossier(List<String> idDossierConcatidCheckDossiers)
            throws Exception {
        Map<String, String> result = new LinkedHashMap<String, String>();
        if (idDossierConcatidCheckDossiers == null || idDossierConcatidCheckDossiers.size() == 0) {
            throw new Exception("on ne peut pas extraire les id check car l'argument est vide ou null");
        }
        for (String idDossierConcatidCheck : idDossierConcatidCheckDossiers) {
            result.put(idDossierConcatidCheck.split("-")[0], idDossierConcatidCheck.split("-")[1]);
        }
        return result;
    }

    public void insertAfterCheck(Connection c, Object obj, String iddemande, List<String> iddossiers,
            List<String> idDossierConcatidCheckDossiers) throws Exception {
        if (obj == null) {
            throw new Exception("l'objet est requis");
        }
        if (obj instanceof CheckDossierStandard checkDossierStandard) {
            if (iddossiers != null && idDossierConcatidCheckDossiers != null) {
                Map<String, String> idChecksandIdDossiers = separateIdCheckDossierAndIdDossier(
                        idDossierConcatidCheckDossiers);
                List<String> idDossiersCheckes = new ArrayList<String>(idChecksandIdDossiers.values());
                for (String iddossier : iddossiers) {
                    if (!idDossiersCheckes.contains(iddossier)) {
                        checkDossierStandard.setIddemande(iddemande);
                        checkDossierStandard.setIddossierstandard(iddossier);
                        checkDossierStandard.setExist(true);
                        checkDossierStandard.insert(c);
                    }
                }
            }
        } else if (obj instanceof CheckDossierSupplementaire checkDossierSupplementaire) {
            if (iddossiers != null && idDossierConcatidCheckDossiers != null) {
                Map<String, String> idChecksandIdDossiers = separateIdCheckDossierAndIdDossier(
                        idDossierConcatidCheckDossiers);

                List<String> idDossiersCheckes = new ArrayList<String>(idChecksandIdDossiers.values());
                for (String iddossier : iddossiers) {
                    if (!idDossiersCheckes.contains(iddossier)) {
                        checkDossierSupplementaire.setIddemande(iddemande);
                        checkDossierSupplementaire.setIddossiersupplementaire(iddossier);
                        checkDossierSupplementaire.setExist(true);
                        checkDossierSupplementaire.insert(c);
                    }
                }
            }
        }
    }

    public void updateAfterCheck(Connection c, Object obj, String iddemande, List<String> iddossiers,
            List<String> idDossierConcatidCheckDossiers,String idTypeVisaPrecedent,String idTypeVisa) throws Exception {
        if (obj == null) {
            throw new Exception("l'objet est requis");
        }
        if (iddossiers != null && idDossierConcatidCheckDossiers != null) {
            Map<String, String> idChecksandIdDossiers = separateIdCheckDossierAndIdDossier(
                    idDossierConcatidCheckDossiers);
            List<String> idDossiersCheckes = new ArrayList<String>(idChecksandIdDossiers.values());
            List<String> idCheckDossiers = new ArrayList<String>(idChecksandIdDossiers.keySet());
            if (obj instanceof CheckDossierStandard checkDossierStandard) {
                for (int i = 0; i < idDossiersCheckes.size(); i++) {
                    if (!iddossiers.contains(idDossiersCheckes.get(i))) {
                        checkDossierStandard.setIddemande(iddemande);
                        checkDossierStandard.setIddossierstandard(idDossiersCheckes.get(i));
                        checkDossierStandard.setExist(false);
                        checkDossierStandard.setId(idCheckDossiers.get(i));
                        checkDossierStandard.update(c);
                    }
                }
            } else if (obj instanceof CheckDossierSupplementaire checkDossierSupplementaire) {
                List<CheckDossierSupplementaire> dossierSupplementairesVaovao = new ArrayList<>();
                for (int i = 0; i < idDossiersCheckes.size(); i++) {
                    if (!iddossiers.contains(idDossiersCheckes.get(i))) {
                        checkDossierSupplementaire.setIddemande(iddemande);
                        checkDossierSupplementaire.setIddossiersupplementaire(idDossiersCheckes.get(i));
                        checkDossierSupplementaire.setExist(false);
                        checkDossierSupplementaire.setId(idCheckDossiers.get(i));
                        dossierSupplementairesVaovao.add(checkDossierSupplementaire);
                    }
                }
                if(!idTypeVisaPrecedent.equals(idTypeVisa)){
                    for(int i = 0; i < idDossiersCheckes.size(); i++){
                        CheckDossierSupplementaire cds = new CheckDossierSupplementaire();
                        cds.setId(idCheckDossiers.get(i));
                        cds.delete(c);
                    }
                }
                else{
                    for(CheckDossierSupplementaire cds : dossierSupplementairesVaovao){
                        cds.update(c);
                    }
                }
                
            }
        }
    }

    public static DemandeObj getByIdDemandeur(String idDemandeur, Connection c) throws Exception{
        Demande d = new Demande();
        DemandeObj demandeObj = new DemandeObj();
        List<Demande> demandes = (List<Demande>) d.select(c, " iddemandeur = '"+idDemandeur+"' and idtypedemande='TYPDMD000001' order by datecreation desc", 1);
        if (demandes.size() == 0) {
            return null;
        }else{
            d = demandes.get(0);
            TypeDemande typeDemande = new TypeDemande();
            Visatransformable visatransformable = new Visatransformable();
            Passport passport = new Passport();
            Demandeur demandeur = new Demandeur();
            TypeVisa typeVisa= new TypeVisa();
            EtatDemande etatDemande = new EtatDemande();
            Demande original = d; 
            demandeObj.setId(d.getId());
            demandeObj.setTypeDemande(typeDemande.findByid(c, d.getIdtypedemande()));
            demandeObj.setVisatransformable(visatransformable.findByid(c, d.getIdvisatransformable())); 
            demandeObj.setPassport(passport.findByid(c, d.getIdpassport()));    
            demandeObj.setDemandeur(demandeur.findByid(c, d.getIddemandeur()));           
            demandeObj.setTypeVisa(typeVisa.findByid(c, d.getIdtypevisa()));           
            demandeObj.setDatecreation(d.getDatecreation());
            demandeObj.setEtatDemande(etatDemande.findByid(c, d.getIdetatdemande()));
            if (d.getIdoriginal() != null) {
                demandeObj.setOriginal(original.findByid(c, d.getIdoriginal()));
            }
        } 
        return demandeObj; 
    }
    public Demande getInstanceByDemandeObj(DemandeObj demandeObj){
        Demande demande = new Demande();
        if(demandeObj.getId() != null){
            demande.setId(demandeObj.getId());
        }
        if(demandeObj.getTypeDemande()!= null){
            demande.setIdtypedemande(demandeObj.getTypeDemande().getId());
        }
        if(demandeObj.getVisatransformable()!= null){
            demande.setIdvisatransformable(demandeObj.getVisatransformable().getId());
        }
        if(demandeObj.getPassport()!= null){
            demande.setIdpassport(demandeObj.getPassport().getId());
        }
        if(demandeObj.getDemandeur()!= null){
            demande.setIddemandeur(demandeObj.getDemandeur().getId());
        }
        if(demandeObj.getTypeVisa()!= null){
            demande.setIdtypevisa(demandeObj.getTypeVisa().getId());
        }
        if(demandeObj.getDatecreation()!= null){
            demande.setDatecreation(demandeObj.getDatecreation());
        }
        if(demandeObj.getEtatDemande()!= null){
            demande.setIdetatdemande(demandeObj.getEtatDemande().getId());
        }
        return demande;
    }
}
