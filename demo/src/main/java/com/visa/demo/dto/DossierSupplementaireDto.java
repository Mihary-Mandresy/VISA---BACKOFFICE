package com.visa.demo.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nojpa.bd.connexion.DbConnexe;
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
    public List<DossierSupplementaireDto> getDossiersNonVerifiesByIdDemande(Connection c, String iddemande, String idtypevisa) throws Exception {
        String query = """
                select * from
                (
                SELECT ds.id, ds.libelle, COALESCE(cds.exist, false) as exists
                FROM dossiersupplementaire ds
                LEFT JOIN checkdossiersupplementaire cds
                    ON cds.iddossiersupplementaire = ds.id
                    and cds.iddemande = ?
                where ds.idtypevisa = ?
                )as dnv where not dnv.exists
                        """;
        List<DossierSupplementaireDto> result = new ArrayList<>();
        Boolean isCloseable = false;
        if (c == null) {
            c = new DbConnexe().getConnection();
            isCloseable = true;
        }
        try (PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, iddemande);
            pstmt.setString(2, idtypevisa); 

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DossierSupplementaireDto dto = new DossierSupplementaireDto();
                    dto.setId(rs.getString("id"));
                    dto.setLibelle(rs.getString("libelle"));
                    dto.setExist(rs.getBoolean("exists"));
                    result.add(dto);
                }
            } catch (Exception e) {
                // TODO: handle exception
                if (isCloseable) {
                    c.close();
                }
                throw e;
            }
        } catch (Exception e) {
            // TODO: handle exception
            if (isCloseable) {
                c.close();
            }
            throw e;
        }

        return result;

    }
}
