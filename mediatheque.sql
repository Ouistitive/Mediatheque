CREATE TABLE Abonne (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nom VARCHAR(30),
	dateNaiss DATE
);

CREATE TABLE DVD (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	titre VARCHAR(50),
	adulte boolean,
	abonneId INTEGER,
	FOREIGN KEY (abonneId) REFERENCES Abonne(id)
);

INSERT INTO Abonne(nom, dateNaiss) VALUES
('Emma', STR_TO_DATE('12-07-2005', '%d-%m-%Y')),
('Noah', STR_TO_DATE('04-02-2000', '%d-%m-%Y')),
('Léa', STR_TO_DATE('23-11-2007', '%d-%m-%Y')),
('Ethan', STR_TO_DATE('8-05-2010', '%d-%m-%Y')),
('Charlotte', STR_TO_DATE('19-09-2002', '%d-%m-%Y'));

INSERT INTO DVD(titre, adulte, abonneId) VALUES
('Le Seigneur des anneaux', TRUE, null),
('Star Wars', FALSE, null),
('Harry Potter', FALSE, null),
('Forrest Gump', FALSE, null),
('Le Parrain', TRUE, null),
('Fight Club', TRUE, null),
('Le Silence des agneaux', TRUE, null),
('Pulp Fiction', TRUE, null),
('Les Évadés', FALSE, null),
('Titanic', FALSE, null);