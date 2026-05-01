-- Active: 1773163328439@@127.0.0.1@5432@visa@public
CREATE TABLE filepdf(
   id VARCHAR(20) ,
   nom VARCHAR(100)  NOT NULL,
   contenue BYTEA NOT NULL,
   PRIMARY KEY(id)
);

-- dossier standard
ALTER Table checkdossierstandard 
ADD COLUMN idfilepdf VARCHAR(20);

ALTER Table checkdossierstandard
ADD CONSTRAINT fk_filepdf
Foreign Key (idfilepdf) REFERENCES filepdf(id);

ALTER TABLE checkdossierstandard
ADD CONSTRAINT uq_checkdossierstandard_idfilepdf
UNIQUE (idfilepdf);

-- dossier supplementaire

ALTER Table checkdossiersupplementaire
ADD COLUMN idfilepdf VARCHAR(20);

ALTER TABLE checkdossiersupplementaire
ADD constraint fk_filepdf
Foreign Key (idfilepdf) REFERENCES filepdf(id);

ALTER TABLE checkdossiersupplementaire
ADD CONSTRAINT uq_checkdossiersupplementaire_idfilepdf
UNIQUE (idfilepdf);
