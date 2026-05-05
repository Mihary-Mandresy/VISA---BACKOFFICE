SELECT * FROM filepdf;

SELECT * FROM checkdossierstandard;
SELECT * FROM checkdossiersupplementaire;

SELECT ds.* FROM dossierstandard ds
JOIN (
   SELECT * FROM checkdossierstandard WHERE iddemande = 'DMD000002' AND (exist = FALSE OR idfilepdf is NULL)
) cd
on cd.iddossierstandard = ds.id;

SELECT ds.* FROM dossiersupplementaire ds
JOIN (
   SELECT * FROM checkdossiersupplementaire WHERE iddemande = 'DMD000002' AND (exist = FALSE OR idfilepdf is NULL)
) cd
on cd.iddossiersupplementaire = ds.id
WHERE ds.idtypevisa = 'TYPV000001';
