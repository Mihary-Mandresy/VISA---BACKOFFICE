package com.visa.demo.dto;

import com.visa.demo.models.FilePdf;

public class DossierDemandeDTO {
    String libelle;
    String type; // standard ou supplementaire
    FilePdf filePdf;
     String tempFilePath;
    
    public String getTempFilePath() {
        return tempFilePath;
    }
     public void setTempFilePath(String tempFilePath) {
         this.tempFilePath = tempFilePath;
     }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public FilePdf getFilePdf() {
        return filePdf;
    }
    public void setFilePdf(FilePdf filePdf) {
        this.filePdf = filePdf;
    }
    
}
