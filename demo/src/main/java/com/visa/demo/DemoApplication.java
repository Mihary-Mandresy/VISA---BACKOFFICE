package com.visa.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.HexFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DossierStandardDto;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;
import com.visa.demo.utils.NetworkUtils;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

		DbConnexe dbConnexe = new DbConnexe();
		Connection c = null;
		try {
			c = dbConnexe.getConnection();
			c.setAutoCommit(false);
			// Demande d = new Demande().findByid(c, "DMD000005");
			// String url ="http://"+NetworkUtils.getLocalIpAddress()+":5173/search";
			// d.genererQrCode(url);
			// String query = "update demande set qrcode=decode(?,'hex') where id=?";
			List<DossierStandardDto> nonVerifies = new DossierStandardDto().getDossiersNonVerifiesByIdDemande(c, "DMD000023");
			System.out.println("valiny : "+nonVerifies.size());
			// try(PreparedStatement ps =c.prepareStatement(query)){
			// ps.setString(1,HexFormat.of().formatHex(d.getQrcode()));
			// ps.setString(2,d.getId());
			// ps.executeUpdate();
			// c.commit();
			// System.out.println("eto ve");
			// }
			// d.update(c);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if (c != null) {
				c.setAutoCommit(true);
				c.close();
			}
		}
		// List<DossierSupplementaire> allSupplementaires = new
		// DossierSupplementaire().getAllByIdTypeVisa(dbConnexe.getConnection(),
		// "TYPV000002");

		// for (DossierSupplementaire d : allSupplementaires) {
		// System.out.println(d.getId() + " " + d.getLibelle());
		// }
	}

}
