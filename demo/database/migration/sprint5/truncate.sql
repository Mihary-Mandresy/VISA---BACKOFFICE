TRUNCATE TABLE historiqueetatdemande,
visa,
carteresident,
checkdossiersupplementaire,
checkdossierstandard,
demande,
visatransformable,
passport,
information,
demandeur,
etatdemande,
filepdf,
dossiersupplementaire,
dossierstandard,
nationalite,
situationdefamille,
typedemande,
typevisa;

ALTER SEQUENCE seq_historiqueetatdemande RESTART WITH 1;
ALTER SEQUENCE seq_visa RESTART WITH 1;
ALTER SEQUENCE seq_carteresident RESTART WITH 1;
ALTER SEQUENCE seq_checkdossiersupplementaire RESTART WITH 1;
ALTER SEQUENCE seq_checkdossierstandard RESTART WITH 1;
ALTER SEQUENCE seq_demande RESTART WITH 1;
ALTER SEQUENCE seq_visatransformable RESTART WITH 1;
ALTER SEQUENCE seq_passport RESTART WITH 1;
ALTER SEQUENCE seq_demandeur RESTART WITH 1;
ALTER SEQUENCE seq_etatdemande RESTART WITH 1;
ALTER SEQUENCE seq_dossiersupplementaire RESTART WITH 1;
ALTER SEQUENCE seq_dossierstandard RESTART WITH 1;
ALTER SEQUENCE seq_nationalite RESTART WITH 1;
ALTER SEQUENCE seq_situationdefamille RESTART WITH 1;
ALTER SEQUENCE seq_typedemande RESTART WITH 1;
ALTER SEQUENCE seq_typevisa RESTART WITH 1;
ALTER SEQUENCE seq_filepdf RESTART WITH 1;
ALTER SEQUENCE seq_information RESTART WITH 1;