--information
CREATE TABLE information(
   id VARCHAR(20) ,
   pdp BYTEA  NOT NULL,
   "signature" BYTEA NOT NULL,
   iddemandeur VARCHAR(20),
   PRIMARY KEY(id)
);

ALTER Table information
ADD CONSTRAINT fk_demandeur
Foreign Key (iddemandeur) REFERENCES demandeur(id);


