package com.visa.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

		// DbConnexe dbConnexe = new DbConnexe();

		// List<DossierSupplementaire> allSupplementaires = new DossierSupplementaire().getAllByIdTypeVisa(dbConnexe.getConnection(), "TYPV000002");

		// for (DossierSupplementaire d : allSupplementaires) {
		// 	System.out.println(d.getId() + " " + d.getLibelle());
		// }
	} 

}
