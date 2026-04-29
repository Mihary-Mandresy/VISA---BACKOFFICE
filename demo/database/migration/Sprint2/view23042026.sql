/*vue pour la liste des demande*/
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
                    tv.libelle as libelletypevisa
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

/*vue pour detail demande sans dossier*/

create or replace view v_demande_details_sans_dossier as(
    select 
            dmd.id , dmd.idtypedemande,dmd.idtypevisa,
            dmdr.id as iddemandeur,dmdr.nom as nomdemandeur, dmdr.prenom as prenomdemandeur, dmdr.dtn as dtndemandeur,dmdr.idnationalite,dmdr.idsituationdefamille,
            dmdr.profession,dmdr.adressemada,dmdr.email,dmdr.tel,
            p.id as idpassport,p.numero as numeropassport, p.datedelivrance as datedelivrancepassport, p.dateexpiration as dateexpirationpassport,
            vt.id as idvisatransformable,vt.reference as referencevt,vt.dateentreemada,vt.dateexpiration as dateexpirationvt,vt.lieuentree
    from demande dmd
    join demandeur dmdr on dmdr.id = dmd.iddemandeur
    join passport p on p.id = dmd.idpassport
    join visatransformable vt on vt.id = dmd.idvisatransformable
);
