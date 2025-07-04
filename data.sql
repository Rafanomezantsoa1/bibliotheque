INSERT INTO genre (genre) VALUES 
('Roman'),
('Science-Fiction'),
('Bande Dessinée'),
('Fantasy'),
('Policier'),
('Historique'),
('Biographie'),
('Poésie');

-- Insertion des livres (version corrigée)
INSERT INTO livre (titre, auteur, date_publication, isbn, nb_total) VALUES
('Le Petit Prince', 'Antoine de Saint-Exupery', '1943-04-06', '9782070408504', 50),
('1984', 'George Orwell', '1949-06-08', '9782070368228', 30),
('Fondation', 'Isaac Asimov', '1951-05-01', '9782290032724', 25),
('Asterix le Gaulois', 'Rene Goscinny, Albert Uderzo', '1961-10-29', '9782864971729', 40),
('Le Seigneur des Anneaux', 'J.R.R. Tolkien', '1954-07-29', '9782266282362', 35),
('Le Crime de l''Orient-Express', 'Agatha Christie', '1934-01-01', '9782253005674', 20),
('Les Miserables', 'Victor Hugo', '1862-04-03', '9782253096703', 45),
('L''Etranger', 'Albert Camus', '1942-05-19', '9782070360024', 30),
('Dune', 'Frank Herbert', '1965-08-01', '9782253079621', 28),
('Persepolis', 'Marjane Satrapi', '2000-01-01', '9782844140583', 22);

-- Association des livres aux genres (version corrigée)
INSERT INTO genre_livre (id_genre, id_livre) VALUES
(1, 1),  -- Le Petit Prince - Roman
(3, 4),  -- Asterix le Gaulois - Bande Dessinee
(2, 2),  -- 1984 - Science-Fiction
(2, 3),  -- Fondation - Science-Fiction
(4, 5),  -- Le Seigneur des Anneaux - Fantasy
(5, 6),  -- Le Crime de l'Orient-Express - Policier
(1, 7),  -- Les Miserables - Roman
(6, 7),  -- Les Miserables - Historique
(1, 8),  -- L'Etranger - Roman
(2, 9),  -- Dune - Science-Fiction
(4, 9),  -- Dune - Fantasy
(3, 10), -- Persepolis - Bande Dessinee
(6, 10); -- Persepolis - Historique

INSERT INTO type_pret (type) VALUES 
('à domicile'),
('sur place');

INSERT INTO profil (profil) VALUES 
('étudiant'),
('enseignant'),
('personnel'),
('externe');

INSERT INTO norme_pret (id_livre, id_profil, nb_max, duree) VALUES 
(1, 1, 3, 14),  -- Livre 1, Profil 1: max 3 emprunts, durée 14 jours
(2, 1, 2, 7),   -- Livre 2, Profil 1: max 2 emprunts, durée 7 jours
(3, 2, 5, 21),  -- Livre 3, Profil 2: max 5 emprunts, durée 21 jours
(1, 2, 4, 14);  -- Livre 1, Profil 2: max 4 emprunts, durée 14 jours

INSERT INTO statut (etat) VALUES 
    ('actif'),
    ('inactif');

INSERT INTO adherent (nom, prenom, telephone, id_profil, id_statut, date_inscription, date_expiration, carte_numero)
VALUES
    ('Dupont', 'Jean', '0612345678', 1, 1, '2023-01-15', '2024-01-15', 'CARTE001'),
    ('Martin', 'Sophie', '0623456789', 2, 1, '2023-02-20', '2024-02-20', 'CARTE002'),
    ('Bernard', 'Pierre', '0634567890', 1, 2, '2023-03-10', '2024-03-10', 'CARTE003'), -- inactif
    ('Petit', 'Marie', '0645678901', 3, 1, '2023-04-05', '2024-04-05', 'CARTE004'),
    ('Moreau', 'Luc', '0656789012', 2, 2, '2023-05-12', '2024-05-12', 'CARTE005'), -- inactif
    ('Lefebvre', 'Isabelle', '0667890123', 1, 1, '2023-06-18', '2024-06-18', 'CARTE006'),
    ('Roux', 'Thomas', '0678901234', 4, 1, '2023-07-22', '2024-07-22', 'CARTE007'),
    ('Fournier', 'Camille', '0689012345', 2, 1, '2023-08-30', '2024-08-30', 'CARTE008'),
    ('Girard', 'Nicolas', '0690123456', 1, 2, '2023-09-14', '2024-09-14', 'CARTE009'), -- inactif
    ('Mercier', 'Valérie', '0601234567', 3, 1, '2023-10-25', '2024-10-25', 'CARTE010');