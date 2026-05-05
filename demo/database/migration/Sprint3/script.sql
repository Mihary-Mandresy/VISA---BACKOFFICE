SELECT * FROM filepdf;

SELECT * FROM checkdossierstandard;

SELECT * FROM checkdossiersupplementaire;

SELECT ds.*
FROM dossierstandard ds
WHERE
    id NOT IN (
        SELECT iddossierstandard
        FROM checkdossierstandard
        WHERE
            iddemande = 'DMD000002'
            AND exist = TRUE
            and idfilepdf is not NULL
    );

SELECT ds.*
FROM dossiersupplementaire ds
WHERE
    ds.idtypevisa = 'TYPV000001'
    and ds.id not IN (
        SELECT iddossiersupplementaire
        FROM checkdossiersupplementaire
        WHERE
            iddemande = 'DMD000002'
            AND exist = TRUE
            and idfilepdf is not NULL
    );