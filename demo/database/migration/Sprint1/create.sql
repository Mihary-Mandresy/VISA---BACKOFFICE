CREATE TABLE typevisa(
   id VARCHAR(20) ,
   libelle VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE typedemande(
   id VARCHAR(20) ,
   libelle VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE situationdefamille(
   id VARCHAR(20) ,
   libelle VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE nationalite(
   id VARCHAR(20) ,
   libelle VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE dossierstandard(
   id VARCHAR(50) ,
   libelle VARCHAR(200)  NOT NULL,
   obligatoire BOOLEAN default false,
   PRIMARY KEY(id)
);

CREATE TABLE dossiersupplementaire(
   id VARCHAR(50) ,
   libelle VARCHAR(200)  NOT NULL,
   obligatoire BOOLEAN default false,
   idtypevisa VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idtypevisa) REFERENCES typevisa(id)
);

CREATE TABLE etatdemande(
   id VARCHAR(20) ,
   libelle VARCHAR(30)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE demandeur(
   id VARCHAR(20) ,
   nom VARCHAR(100)  NOT NULL,
   prenom VARCHAR(100) ,
   profession VARCHAR(100) ,
   adressemada VARCHAR(200)  NOT NULL,
   tel VARCHAR(20)  NOT NULL,
   email VARCHAR(200) ,
   idsituationdefamille VARCHAR(20)  NOT NULL,
   idnationalite VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idsituationdefamille) REFERENCES situationdefamille(id),
   FOREIGN KEY(idnationalite) REFERENCES nationalite(id)
);

CREATE TABLE passport(
   id VARCHAR(20) ,
   numero VARCHAR(100)  NOT NULL,
   datedelivrance DATE NOT NULL,
   dateexpiration DATE NOT NULL,
   iddemandeur VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(iddemandeur) REFERENCES demandeur(id)
);

CREATE TABLE visatransformable(
   id VARCHAR(20) ,
   reference VARCHAR(100)  NOT NULL,
   dateentreemada DATE NOT NULL,
   dateexpiration DATE NOT NULL,
   lieuentree VARCHAR(100)  NOT NULL,
   idpassport VARCHAR(20)  NOT NULL,
   iddemandeur VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idpassport) REFERENCES passport(id),
   FOREIGN KEY(iddemandeur) REFERENCES demandeur(id)
);

CREATE TABLE demande(
   id VARCHAR(20) ,
   datecreation DATE NOT NULL default current_date,
   idoriginal VARCHAR(20) ,
   idpassport VARCHAR(20)  NOT NULL,
   idetatdemande VARCHAR(20)  NOT NULL,
   idtypedemande VARCHAR(20)  NOT NULL,
   idvisatransformable VARCHAR(20)  NOT NULL,
   iddemandeur VARCHAR(20)  NOT NULL,
   idtypevisa VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idoriginal) REFERENCES demande(id),
   FOREIGN KEY(idpassport) REFERENCES passport(id),
   FOREIGN KEY(idetatdemande) REFERENCES etatdemande(id),
   FOREIGN KEY(idtypedemande) REFERENCES typedemande(id),
   FOREIGN KEY(idvisatransformable) REFERENCES visatransformable(id),
   FOREIGN KEY(iddemandeur) REFERENCES demandeur(id),
   FOREIGN KEY(idtypevisa) REFERENCES typevisa(id)
);

CREATE TABLE checkdossierstandard(
   id VARCHAR(50) ,
   exist BOOLEAN default false,
   iddemande VARCHAR(20)  NOT NULL,
   iddossierstandard VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(iddemande) REFERENCES demande(id),
   FOREIGN KEY(iddossierstandard) REFERENCES dossierstandard(id)
);

CREATE TABLE checkdossiersupplementaire(
   id VARCHAR(50) ,
   exist BOOLEAN default false,
   iddemande VARCHAR(20)  NOT NULL,
   iddossiersupplementaire VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(iddemande) REFERENCES demande(id),
   FOREIGN KEY(iddossiersupplementaire) REFERENCES dossiersupplementaire(id)
);

CREATE TABLE carteresident(
   id VARCHAR(20) ,
   reference VARCHAR(100)  NOT NULL,
   datedebut DATE NOT NULL,
   dateexpiration DATE NOT NULL,
   idpassport VARCHAR(20)  NOT NULL,
   iddemande VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idpassport) REFERENCES passport(id),
   FOREIGN KEY(iddemande) REFERENCES demande(id)
);

CREATE TABLE visa(
   id VARCHAR(20) ,
   reference VARCHAR(100)  NOT NULL,
   datedebut DATE NOT NULL,
   dateexpiration DATE NOT NULL,
   idpassport VARCHAR(20)  NOT NULL,
   iddemande VARCHAR(20)  NOT NULL,
   idtypevisa VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idpassport) REFERENCES passport(id),
   FOREIGN KEY(iddemande) REFERENCES demande(id),
   FOREIGN KEY(idtypevisa) REFERENCES typevisa(id)
);

CREATE TABLE historiqueetatdemande(
   id VARCHAR(20) ,
   daty VARCHAR(50)  NOT NULL default current_date,
   idetatdemande VARCHAR(20)  NOT NULL,
   iddemande VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idetatdemande) REFERENCES etatdemande(id),
   FOREIGN KEY(iddemande) REFERENCES demande(id)
);
