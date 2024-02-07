-- pg_dump -U utilisateur -d nom_de_la_base_de_donnees > nom_du_fichier.sql
-- pg_dump --create -U postgres -d gestion_meuble_v1 -f backup1.sql

CREATE DATABASE meuble_final;
\c meuble_final;

/* SEQUENCE ====================== */
CREATE SEQUENCE seq_materiel START WITH 1 increment BY 1;
CREATE SEQUENCE seq_style START WITH 1 increment BY 1;
CREATE SEQUENCE seq_style_materiel START WITH 1 increment BY 1;
CREATE SEQUENCE seq_meuble START WITH 1 increment BY 1;
CREATE SEQUENCE seq_volume START WITH 1 increment BY 1;
CREATE SEQUENCE seq_meuble_qte_materiel START WITH 1 increment BY 1;
CREATE SEQUENCE seq_stock START WITH 1 increment BY 1;
CREATE SEQUENCE seq_mouvement_stock START WITH 1 increment BY 1;
CREATE SEQUENCE seq_worker START WITH 1 increment BY 1;
CREATE SEQUENCE seq_meuble_worker START WITH 1 increment BY 1;
CREATE SEQUENCE seq_person START WITH 1 increment BY 1;
CREATE SEQUENCE seq_post START WITH 1 increment BY 1;
CREATE SEQUENCE seq_client START WITH 1 increment BY 1;
CREATE SEQUENCE seq_gender START WITH 1 increment BY 1;


/* TABLE ========================== */ 
CREATE TABLE Materiel (
    id_materiel VARCHAR(255) DEFAULT ('MAT' || LPAD(nextval('seq_materiel')::TEXT, 4, '0')),
    intitule VARCHAR(255) NOT NULL,
    unitPrice DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id_materiel)
);

CREATE TABLE Style (
    id_style VARCHAR(255) DEFAULT ('STL' || LPAD(nextval('seq_style')::TEXT, 4, '0')),
    intitule VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_style)
);

CREATE TABLE StyleMateriel (
    id_style VARCHAR(255) NOT NULL,
    id_materiel VARCHAR(255) NOT NULL,

    PRIMARY KEY (id_style, id_materiel),
    
    FOREIGN KEY (id_style) REFERENCES Style (id_style),
    FOREIGN KEY (id_materiel) REFERENCES Materiel (id_materiel)
);

CREATE TABLE Volume (
    id_volume VARCHAR(255) DEFAULT ('VOL' || LPAD(nextval('seq_volume')::TEXT, 4, '0')),
    intitule VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_volume)
);

CREATE TABLE Meuble (
    id_meuble VARCHAR(255) DEFAULT ('MEU' || LPAD(nextval('seq_meuble')::TEXT, 4, '0')),
    intitule VARCHAR(255) NOT NULL,
    id_style VARCHAR(255) NOT NULL,
    volume VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (id_meuble),

    FOREIGN KEY (id_style) REFERENCES Style (id_style)
);

CREATE TABLE MeubleQuantityMateriel (
    id_meuble_qte_materiel VARCHAR(255) DEFAULT ('MQM' || LPAD(nextval('seq_meuble_qte_materiel')::TEXT, 4, '0')),
    id_meuble VARCHAR(255) NOT NULL,
    id_volume VARCHAR(255) NOT NULL,
    id_materiel VARCHAR(255) NOT NULL,
    qte int NOT NULL,

    PRIMARY KEY (id_meuble_qte_materiel),

    FOREIGN KEY (id_meuble) REFERENCES Meuble (id_meuble),
    FOREIGN KEY (id_volume) REFERENCES Volume (id_volume),
    FOREIGN KEY (id_materiel) REFERENCES Materiel (id_materiel)
);


CREATE TABLE Stock (
    id_stock VARCHAR(255) DEFAULT ('STK' || LPAD(nextval('seq_stock')::TEXT, 4, '0')),
    id_materiel VARCHAR(255) NOT NULL,
    quantity_stock INT NOT NULL,

    PRIMARY KEY (id_stock),
    FOREIGN KEY (id_materiel) REFERENCES Materiel (id_materiel)
);

CREATE TABLE MouvementStock (
    id_mouvement VARCHAR(255) DEFAULT ('MVS' || LPAD(nextval('seq_mouvement_stock')::TEXT, 4, '0')),
    id_materiel VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    type_mouvement VARCHAR(255) NOT NULL,
    date_mouvement DATE NOT NULL,

    PRIMARY KEY(id_mouvement),
    FOREIGN KEY (id_materiel) REFERENCES Materiel (id_materiel)
);

CREATE TABLE Worker (
    id_worker VARCHAR(255) DEFAULT ('WK' || LPAD(nextval('seq_worker')::TEXT, 4, '0')),
    type_worker VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_worker)
); 

CREATE TABLE MeubleWorker (
    id_meuble_worker VARCHAR(255)  DEFAULT ('MW' || LPAD(nextval('seq_meuble_worker')::TEXT, 4, '0')),
    id_meuble VARCHAR(255) NOT NULL,
    id_worker VARCHAR(255) NOT NULL,
    id_volume VARCHAR(255) NOT NULL,
    number_worker INT NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (id_meuble) REFERENCES Meuble (id_meuble),
    FOREIGN KEY (id_worker) REFERENCES Worker (id_worker)
);

CREATE TABLE MeublePrixAchat (
    id_meuble VARCHAR(255) NOT NULL,
	id_volume VARCHAR(255) NOT NULL,
    prix_achat DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (id_meuble) REFERENCES Meuble (id_meuble)
);


CREATE TABLE Poste (
    id_poste VARCHAR(255) DEFAULT ('POS' || LPAD(nextval('seq_post')::TEXT, 4, '0')),
    type_poste VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_poste)
);

CREATE TABLE Person (
    id_person VARCHAR(255) DEFAULT ('PERS' || LPAD(nextval('seq_person')::TEXT, 4, '0')),
    id_poste VARCHAR(255) NOT NULL,
    name_person VARCHAR(255) NOT NULL,
    date_embauche DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,

    PRIMARY KEY (id_person),
    FOREIGN KEY (id_poste) REFERENCES Poste (id_poste)
);

CREATE TABLE MvtPoste (
    id_person VARCHAR(255) NOT NULL,
    id_new_post VARCHAR(255) NOT NULL,
    new_date DATE NOT NULL,
    new_salary DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (id_person) REFERENCES Person (id_person),
    FOREIGN KEY (id_new_post) REFERENCES Poste (id_poste)
);

CREATE TABLE ExperiencePoste (
    id SERIAL PRIMARY KEY,
    id_poste VARCHAR(255) NOT NULL,
    delai INT NOT NULL,

    FOREIGN KEY (id_poste) REFERENCES Poste (id_poste)
);

CREATE TABLE SalaryPoste (
    id_poste VARCHAR(255) NOT NULL,
    multiplicateur INT NOT NULL,

    FOREIGN KEY (id_poste) REFERENCES Poste (id_poste)
);

CREATE TABLE Gender(
    id_gender VARCHAR(255) DEFAULT ('GEND' || LPAD(nextval('seq_gender')::TEXT, 4, '0')) PRIMARY KEY,
    intitule VARCHAR(255) NOT NULL
);

CREATE TABLE Client (
    id_client VARCHAR(255) DEFAULT ('CL' || LPAD(nextval('seq_client')::TEXT, 4, '0')),
    gender VARCHAR(255) NOT NULL,
    name_client VARCHAR(255) NOT NULL,
    budget DECIMAL(10,2) NOT NULL,
    
    PRIMARY KEY (id_client)
);

CREATE TABLE AchatMeuble (
    id_client VARCHAR(255) NOT NULL,
    id_meuble VARCHAR (255) NOT NULL,
    qte INT NOT NULL,
    date_achat DATE NOT NULL,

    FOREIGN KEY (id_client) REFERENCES Client (id_client),
    FOREIGN KEY (id_meuble) REFERENCES Meuble (id_meuble)
);

INSERT INTO Gender(intitule) VALUES ('Homme');
INSERT INTO Gender(intitule) VALUES ('Femme');