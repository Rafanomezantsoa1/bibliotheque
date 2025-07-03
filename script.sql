DROP DATABASE IF EXISTS bibliotheque;
CREATE DATABASE bibliotheque;
\c bibliotheque;

CREATE TABLE genre(
    id SERIAL PRIMARY KEY,
    genre VARCHAR(50) --roman, science, BD 
);

CREATE TABLE livre (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255),
    date_publication DATE,
    isbn VARCHAR(20) UNIQUE,
    nb_total INT NOT NULL --nb des exemplaires
);

CREATE TABLE genre_livre(
    id SERIAL PRIMARY KEY,
    id_genre INT REFERENCES genre(id),
    id_livre INT REFERENCES livre(id)
);

CREATE TABLE statut(
    id SERIAL PRIMARY KEY,
    etat VARCHAR(100) --actif ou inactif
);

CREATE TABLE profil(
    id SERIAL PRIMARY KEY,
    profil VARCHAR(50) --étudiant, enseignants
);

CREATE TABLE adherent (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    telephone VARCHAR(20),
    id_profil INT REFERENCES profil(id),
    id_statut INT REFERENCES statut(id),
    date_inscription DATE DEFAULT CURRENT_DATE,
    date_expiration DATE,
    carte_numero VARCHAR(50) UNIQUE
);

-- CREATE TABLE paiement(
--     id SERIAL PRIMARY KEY,
--     id_adherent INT REFERENCES adherent(id),
--     montant INT
-- );

-- CREATE TABLE montant_a_payer(
--     id SERIAL PRIMARY KEY,
--     montant INT,
--     id_profil INT REFERENCES profil(id)
-- );

-- CREATE TABLE historique_montant(
--     id SERIAL PRIMARY KEY,
--     montant INT,
--     date_update DATE
-- );

-- CREATE TABLE statut_pret(
--     id SERIAL PRIMARY KEY,
--     statur VARCHAR(50) --en cours, refusé, validé, annulé 
-- );

CREATE TABLE type_pret(
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) --à domicile , sur place 
);

CREATE TABLE norme_pret(
    id SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id),
    id_profil INT REFERENCES profil(id),
    nb_max INT NOT NULL,
    durée INT --nb de jours
);

CREATE TABLE pret (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    id_livre INT REFERENCES livre(id),
    date_pret TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_fin DATE,
    id_type INT REFERENCES type_pret(id)
);

CREATE TABLE prolongement_pret (
    id SERIAL PRIMARY KEY,
    duree_jour INT,
    id_pret REFERENCES pret(id)
);

CREATE TABLE etat_reservation (
    id SERIAL PRIMARY KEY,
    etat VARCHAR(20) -- en_attente, confirmée, annulée
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    id_livre INT REFERENCES livre(id),
    date_reservation DATE NOT NULL,
    id_etat_reservation INT REFERENCES etat_reservation(id),
    date_limite_recuperation DATE
);

CREATE TABLE penalite (
    id SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id),
    id_adherent INT REFERENCES adherent(id),
    date_début DATE NOT NULL,
    date_fin DATE NOT NULL
);

CREATE TABLE historique_pret (
    id SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id),
    id_adherent INT REFERENCES adherent(id),
    date_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bibliothécaire (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(20),
    identifiant INT --qui va me servir de mot de passe après
);
