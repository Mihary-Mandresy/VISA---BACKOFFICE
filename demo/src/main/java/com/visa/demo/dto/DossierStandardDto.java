package com.visa.demo.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nojpa.bd.connexion.DbConnexe;
import com.nojpa.bd.entity.Entity;

public class DossierStandardDto extends Entity<DossierStandardDto> {
    private String id;
    private String libelle;
    private boolean exist;

    public DossierStandardDto() {
        setNomTable("v_verifications_dossiers_standards");
    }

    public DossierStandardDto(String id, String libelle, boolean exist) {
        this.id = id;
        this.libelle = libelle;
        this.exist = exist;
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

    public List<DossierStandardDto> getDossiersNonVerifiesByIdDemande(Connection c, String iddemande) throws Exception {
        String query = """
                select * from
                (
                SELECT ds.id, ds.libelle, COALESCE(cds.exist, false) as exists
                FROM dossierstandard ds
                LEFT JOIN checkdossierstandard cds
                    ON cds.iddossierstandard = ds.id
                    and cds.iddemande = ?
                )as dnv where not dnv.exists 
                        """;
        List<DossierStandardDto> result = new ArrayList<>();
        Boolean isCloseable = false;
        if (c == null) {
            c = new DbConnexe().getConnection();
            isCloseable = true;
        }
        try (PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, iddemande);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DossierStandardDto dto = new DossierStandardDto();
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
            }
        } catch (Exception e) {
            // TODO: handle exception
            if (isCloseable) {
                c.close();
            }
        }

        return result;

    }
}
