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


create or replace view v_liste_demande as(
    select   
                    dmd.id ,
                    dmd.datecreation as datecreation ,
                    dmd.idpassport as idpassport,
                    dmd.idvisatransformable as  idvisatransformable,
                    edmd.libelle as libelleetatdemande ,
                    tdmd.libelle as libelletypedemande ,
                    dmdr.nom as nomdemandeur,
                    dmdr.prenom as prenomdemandeur,
                    tv.libelle as libelletypevisa,
                    dmd.idetatdemande as idetatdemande
                from demande dmd
                join etatdemande edmd 
                on edmd.id = dmd.idetatdemande
                join typedemande tdmd
                on tdmd.id = dmd.idtypedemande
                join demandeur dmdr
                on dmdr.id = dmd.iddemandeur
                join typevisa tv 
                on tv.id = dmd.idtypevisa
);
