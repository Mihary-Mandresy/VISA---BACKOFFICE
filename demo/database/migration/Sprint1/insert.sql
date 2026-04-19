-- Active: 1773163328439@@127.0.0.1@5432@visa@public

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


SELECT * FROM dossierstandard;

SELECT * FROM dossiersupplementaire;

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
INSERT INTO situationdefamille VALUES
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'Celibataire'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'En Couple'),
('SITF' || lpad(nextval('seq_situationdefamille')::text, 6, '0'), 'C''est complique');

-- ============= Situatino de Famille =============
INSERT INTO nationalite VALUES
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Americain'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Africain'),
('NAT' || lpad(nextval('seq_nationalite')::text, 6, '0'), 'Europeen');


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

