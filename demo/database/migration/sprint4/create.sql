alter table demande ADD COLUMN qrcode BYTEA ;
ALTER TABLE historiqueetatdemande
ALTER COLUMN daty TYPE DATE
USING daty::DATE;