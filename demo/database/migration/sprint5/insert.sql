
-- ============= Dossier Standard =============
INSERT INTO dossierstandard VALUES
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), '02 photos d''identite'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Notice de renseignement'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Demande adressee a Mr le Ministere de l''interieur et de la Decentralisation avec adresse e-mail et numero telephone portable'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Photocopie certifiee du visa en cours de validite'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Photocopie certifiee de la premiere page du passeport'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Photocopie certifiee de la carte resident en cours de validite'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Certificat de residence a Madagascar'),
('DST' || LPAD(nextval('seq_dossierstandard')::TEXT,6,'0'), 'Extrait de casier judiciaire moins de 3 moins');

-- ! ============= Statut Visa =============
INSERT INTO typevisa VALUES
('TYPV' || lpad(nextval('seq_typevisa')::TEXT, 6, '0'), 'Investisseur'),
('TYPV' || lpad(nextval('seq_typevisa')::TEXT, 6, '0'), 'Travailleur');

--  ============= dossier Supplementaire =============
INSERT INTO dossiersupplementaire(id, libelle, idtypevisa) VALUES 
('DSU' || lpad(nextval('seq_dossiersupplementaire')::text, 6, '0'), 'Autorisation emploi delivree a Madagascar par le Ministere de la Fonction publique', 'TYPV000001'),
('DSU' || lpad(nextval('seq_dossiersupplementaire')::text, 6, '0'), 'Attestation d''emploi delivre par l''employeur (Original)', 'TYPV000001'),

('DSU' || lpad(nextval('seq_dossiersupplementaire')::text, 6, '0'), 'Acte de naissace delivre moins de 6 mois (enfant) ou acte de mariage (livret de famille)', 'TYPV000002'),
('DSU' || lpad(nextval('seq_dossiersupplementaire')::text, 6, '0'), 'Justification de ressources pour les Hommes en regroupement familial', 'TYPV000002'),
('DSU' || lpad(nextval('seq_dossiersupplementaire')::text, 6, '0'), 'Autorisation emploi pour le Regroupement familial des travailleurs', 'TYPV000002');

-- ! ============= Nationalité =============
INSERT INTO nationalite VALUES
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Afghane'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Africaine du Sud'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Algérienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Allemande'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Américaine'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Anglaise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Angolaise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Argentine'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Australienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Autrichienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Belge'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Béninoise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Birmane'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Bolivienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Brésilienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Bulgare'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Burkinabè'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Burundaise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Cambodgienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Camerounaise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Canadienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Cap-Verdienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Centrafricaine'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Chilienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Chinoise'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Colombienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Comorienne'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Congolaise'),

-- ============= Situation de Famille =============
INSERT INTO situationdefamille VALUES
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Célibataire'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Marié(e)'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Divorcé(e)'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Veuf/Veuve'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'En couple'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Pacsé(e)'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Séparé(e)');


-- ! ============= Type Demande =============
INSERT INTO typedemande VALUES
('TYPDMD' || lpad(nextval('seq_typedemande')::text, 6, '0'), 'Nouveau Titre'),
('TYPDMD' || lpad(nextval('seq_typedemande')::text, 6, '0'), 'Duplicata'),
('TYPDMD' || lpad(nextval('seq_typedemande')::text, 6, '0'), 'Transfert');

-- ============= Etat Demande =============
INSERT INTO etatdemande VALUES
('ETATDMD' || lpad(nextval('seq_etatdemande')::text, 6, '0'), 'Demande Cree'),
('ETATDMD' || lpad(nextval('seq_etatdemande')::text, 6, '0'), 'Demande Scanne'),
('ETATDMD' || lpad(nextval('seq_etatdemande')::text, 6, '0'), 'Visa Cree');
('ETATDMD' || lpad(nextval('seq_etatdemande')::text, 6, '0'), 'Photo et signature terminees');

