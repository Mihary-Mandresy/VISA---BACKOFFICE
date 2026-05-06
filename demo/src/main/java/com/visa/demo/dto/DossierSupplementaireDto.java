package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;


public class DossierSupplementaireDto extends Entity<DossierSupplementaireDto>{

    
        private String id;
        private String libelle;
        private boolean exist;

        
        public DossierSupplementaireDto() {
            setNomTable("v_verifications_dossiers_supplementaires");
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }
}
