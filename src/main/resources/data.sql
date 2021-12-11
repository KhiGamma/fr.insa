INSERT INTO Agence (CODE_AGENCE, NOM_AGENCE, ADRESSE, VILLE)
VALUES
    (10001, 'agence1', 'adresse agence 1', 'Paris'),
    (10002, 'agence2', 'adresse agence 2', 'Valenciennes'),
    (10003, 'agence3', 'adresse agence 3', 'Valenciennes');

INSERT INTO Client (ADRESSE, AGE, CODE_POSTAL, NOM, PRENOM, TELEPHONE, VILLE, AGENCE_CODE_AGENCE)
VALUES
    ('adresse client 1', 18, '59700', 'nom client 1', 'prenom client 1', '1234567890', 'Marly', 10001),
    ('adresse client 2', 25, '59300', 'nom client 2', 'prenom client 2', '1234567890', 'Valenciennes', 10001),
    ('adresse client 3', 30, '75000', 'nom client 3', 'prenom client 3', '1234567890', 'Paris', 10001),
    ('adresse client 4', 40, '63000', 'nom client 4', 'prenom client 4', '1234567890', 'Biarritz', 10002);