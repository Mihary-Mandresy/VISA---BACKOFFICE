package com.visa.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(DemoApplication.class, args);

		// DbConnexe dbConnexe = new DbConnexe();

		// Demandeur demandeur = new Demandeur("Deraman", null, "Etudiant", "Lot Baba", "0333333333", null, "SITF000001", "NAT000001");
		// Passport passport = new Passport("1234", LocalDate.of(2015, 5, 12), LocalDate.of(2027, 6, 25), null);
		// Visatransformable visatransformable = new Visatransformable("1234", LocalDate.of(2024, 12, 2), LocalDate.of(2027, 6, 25), null, null, "Ivato");

		// List<String> dossierStandars = List.of("DST000001", "DST000003", "DST000003");
		// List<String> dossierSupplementaire = List.of("DSU000001", "DSU000002");

		// String typeDemande = "TYPDMD000001", typeVisa = "TYPV000001";

		// Demande dmd = new Demande();
		
		// try {
		// 	dmd.save(dbConnexe.getConnection(), demandeur, passport, visatransformable, dossierStandars, dossierSupplementaire, typeDemande, typeVisa, null);
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
	}

}
