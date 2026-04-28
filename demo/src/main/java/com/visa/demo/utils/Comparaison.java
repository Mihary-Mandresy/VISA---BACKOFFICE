package com.visa.demo.utils;

import com.visa.demo.models.Demandeur;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;

public class Comparaison {
    //retourne -1 si on n'a pas les memes instance et 1 dans le cas contraire
    public static int comparerDeuxInstances(Object obj1, Object obj2) {
        int result = 1;
        if (obj1 instanceof Passport passport1 && obj2 instanceof Passport passport2) {
            if (passport1.getId() != null && passport2.getId() != null) {
                if (!passport1.getId().equals(passport2.getId())) {
                    result = -1;
                }
            }
            if (passport1.getDatedelivrance() != null && passport2.getDatedelivrance() != null) {
                if (!passport1.getDatedelivrance().isEqual(passport2.getDatedelivrance())) {
                    result = -1;
                }
            }
            if (passport1.getDateexpiration() != null && passport2.getDateexpiration() != null) {
                if (!passport1.getDateexpiration().isEqual(passport2.getDateexpiration())) {
                    result = -1;
                }
            }
            if (passport1.getIddemandeur() != null && passport2.getIddemandeur() != null) {
                if (!passport1.getIddemandeur().equals(passport2.getIddemandeur())) {
                    result = -1;
                }
            }
        }
        if (obj1 instanceof Visatransformable vt1 && obj2 instanceof Visatransformable vt2) {
            if (vt1.getId() != null && vt2.getId() != null) {
                if (!vt1.getId().equals(vt2.getId())) {
                    result = -1;
                }
            }
            if (vt1.getDateentreemada() != null && vt2.getDateentreemada() != null) {
                if (!vt1.getDateentreemada().isEqual(vt2.getDateentreemada())) {
                    result = -1;
                }
            }
            if (vt1.getDateexpiration() != null && vt2.getDateexpiration() != null) {
                if (!vt1.getDateentreemada().isEqual(vt2.getDateentreemada())) {
                    result = -1;
                }
            }
            if (vt1.getIddemandeur() != null && vt2.getIddemandeur() != null) {
                if (!vt1.getIddemandeur().equals(vt2.getIddemandeur())) {
                    result = -1;
                }
            }
            if (vt1.getDateexpiration() != null && vt2.getDateexpiration() != null) {
                if (!vt1.getDateentreemada().isEqual(vt2.getDateentreemada())) {
                    result = -1;
                }
            }
            if (vt1.getIdpassport() != null && vt2.getIdpassport() != null) {
                if (!vt1.getIdpassport().equals(vt2.getIdpassport())) {
                    result = -1;
                }
            }
            if (vt1.getLieuentree() != null && vt2.getLieuentree() != null) {
                if (!vt1.getLieuentree().equals(vt2.getLieuentree())) {
                    result = -1;
                }
            }
        }
        if (obj1 instanceof Demandeur dmdr1 && obj2 instanceof Demandeur dmdr2) {
            if (dmdr1.getId() != null && dmdr2.getId() != null) {
                if (!dmdr1.getId().equals(dmdr2.getId())) {
                    result = -1;
                }
            }
            if (dmdr1.getNom() != null && dmdr2.getNom() != null) {
                if (!dmdr1.getNom().equals(dmdr2.getNom())) {
                    result = -1;
                }
            }
            if (dmdr1.getPrenom() != null && dmdr2.getPrenom() != null) {
                if (!dmdr1.getPrenom().equals(dmdr2.getPrenom())) {
                    result = -1;
                }
            }
            if (dmdr1.getProfession() != null && dmdr2.getProfession() != null) {
                if (!dmdr1.getProfession().equals(dmdr2.getProfession())) {
                    result = -1;
                }
            }
            if (dmdr1.getDtn() != null && dmdr2.getDtn() != null) {
                if (!dmdr1.getDtn().isEqual(dmdr2.getDtn())) {
                    result = -1;
                }
            }
            if (dmdr1.getIdnationalite() != null && dmdr2.getIdnationalite() != null) {
                if (!dmdr1.getIdnationalite().equals(dmdr2.getIdnationalite())) {
                    result = -1;
                }
            }
            if (dmdr1.getIdsituationdefamille() != null && dmdr2.getIdsituationdefamille() != null) {
                if (!dmdr1.getIdsituationdefamille().equals(dmdr2.getIdsituationdefamille())) {
                    result = -1;
                }
            }
            if (dmdr1.getProfession() != null && dmdr2.getProfession() != null) {
                if (!dmdr1.getProfession().equals(dmdr2.getProfession())) {
                    result = -1;
                }
            }
        }
        return result;
    }

}
