/*vue suivi etat demande*/
drop view v_suivis_etats_demandes;
create or replace view v_suivis_etats_demandes as (
select 
hedmd.id,
dmd.id as iddemande,
hedmd.daty,
/*vue demandes details*/
create or replace view v_demandes_details as(
select 
dmd.id,
dmd.qrcode,
dmd.datecreation ,
dmdr.id as iddemandeur,
dmdr.nom as nomdemandeur,
dmdr.prenom as prenomdemandeur,
dmdr.dtn as dtndemandeur,
dmdr.profession ,
dmdr.adressemada,
dmdr.tel as telephone,
dmdr.email,

nat.id as idnationalite,
nat.libelle as libnationalite,

sitf.id as idsituationfamiliale,
sitf.libelle as libsituationfamiliale,

pp.id as idpassport,
pp.numero as numeropassport,
pp.datedelivrance as datedelivrancepassport,
pp.dateexpiration as dateexppassport,
dmd.idvisatransformable,
vt.reference as referencevt,
vt.dateentreemada,
vt.dateexpiration as  dateexpvisatransformable,
vt.lieuentree,

tpv.id as idtypevisa,
tpv.libelle as typevisa,
tdmd.id as idtypedemande,
tdmd.libelle as typedemande

from demande  dmd
join demandeur dmdr 
on dmdr.id = dmd.iddemandeur
join nationalite nat
on nat.id = dmdr.idnationalite
join  situationdefamille sitf
on sitf.id = dmdr.idsituationdefamille
join passport pp
on pp.id = dmd.idpassport
join visatransformable vt
on vt.id = dmd.idvisatransformable
join typevisa tpv
on tpv.id = dmd.idtypevisa
join typedemande tdmd
on tdmd.id = dmd.idtypedemande
join etatdemande edmd
on edmd.id = dmd.idetatdemande
);


/*vue suivi etat demande*/
create or replace view v_suivis_etats_demandes as (
select 
dmd.id,
hedmd.daty as datecreation,
edmd.id as idetat,
edmd.libelle
from demande dmd 
left join  historiqueetatdemande hedmd
on  hedmd.iddemande = dmd.id
left join etatdemande edmd
on edmd.id = hedmd.idetatdemande
);

/*v  dossier_standard*/
drop view if EXISTS v_verifications_dossiers_standards;
create or replace view v_verifications_dossiers_standards as(
    select 
    ds.id,
    dmd.id as iddemande,
    ds.libelle,
    cds.exist
    from checkdossierstandard cds
    join dossierstandard ds
    on ds.id =cds.iddossierstandard
    join demande dmd
    on dmd.id = cds.iddemande
);

/*v  dossier_supplementaire*/
drop view if exists v_verifications_dossiers_supplementaires;
create or replace view v_verifications_dossiers_supplementaires as(
    select 
    ds.id,
    dmd.id as iddemande,
    ds.libelle,
    cds.exist
    from checkdossiersupplementaire cds
    join dossiersupplementaire ds
    on ds.id =cds.iddossiersupplementaire
    join demande dmd
    on dmd.id = cds.iddemande
);

drop view if exists v_visatransformable;
create or replace view v_visatransformable as(
    select 
    id,
    reference,
    dateentreemada,
    dateexpiration,
    lieuentree
    from visatransformable
);
drop view if exists v_passport;
create or replace view v_passport as(
    select 
    id,
    numero,
    datedelivrance,
    dateexpiration
    from passport
);

drop view if exists v_demande_recherche;
create or replace view v_demande_recherche as(
    select 
    dmd.id,
    dmdr.nom ||' '|| dmdr.prenom as "nomDemandeur",
    p.numero as numeropassport,
    tpv.libelle as typevisa,
    edmd.libelle as etatdemande
    from demande dmd
    join demandeur dmdr
    on  dmd.iddemandeur= dmdr.id
    join passport p
    on p.id = dmd.idpassport
    join typevisa tpv
    on tpv.id = dmd.idtypevisa
    join etatdemande edmd
    on edmd.id = dmd.idetatdemande
);