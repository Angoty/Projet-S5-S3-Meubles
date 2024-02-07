CREATE OR REPLACE VIEW v_price_meuble AS
SELECT
    M.id_meuble,
    M.intitule AS meuble_intitule,
    M.id_style,
    M.volume,
    SUM(MT.unitPrice * MQM.qte) AS total_material_price
FROM
    Meuble M
JOIN
    MeubleQuantityMateriel MQM ON M.id_meuble = MQM.id_meuble
JOIN
    Materiel MT ON MQM.id_materiel = MT.id_materiel
GROUP BY
    M.id_meuble, M.intitule, M.id_style, M.volume, M.price;

--SELECT * FROM v_price_meuble

/* ====================================================== */

CREATE OR REPLACE VIEW v_meuble_qte_materiel AS
SELECT 
    MQM.id_meuble_qte_materiel,
    M.id_meuble,
    MT.id_materiel,
    MQM.qte
FROM 
    Meuble M
JOIN
    MeubleQuantityMateriel MQM ON M.id_meuble = MQM.id_meuble
JOIN
    Materiel MT ON MQM.id_materiel = MT.id_materiel
GROUP BY 
    MQM.id_meuble_qte_materiel, M.id_meuble, MT.id_materiel, MQM.qte;

--SELECT * FROM v_meuble_qte_materiel;

/* ====================================================== */
CREATE OR REPLACE VIEW v_stock_in AS
SELECT 
    id_materiel,
    SUM(quantity) as qte_in
FROM 
    MouvementStock
WHERE 
    type_mouvement = 'IN'
GROUP BY id_materiel;


/* ====================================================== */
CREATE OR REPLACE VIEW v_stock_out AS
SELECT 
    id_materiel,
    SUM(quantity) as qte_out
FROM 
    MouvementStock
WHERE 
    type_mouvement = 'OUT'
GROUP BY id_materiel;

/* ====================================================== */
CREATE OR REPLACE VIEW v_qte_materiel_stock AS
SELECT 
    v_stock_in.id_materiel,
    SUM(v_stock_in.qte_in - COALESCE(v_stock_out.qte_out, 0)) AS qte_stock
FROM 
    v_stock_in
LEFT JOIN 
    v_stock_out ON v_stock_in.id_materiel = v_stock_out.id_materiel
GROUP BY 
    v_stock_in.id_materiel;

--SELECT * FROM v_qte_materiel_stock;

/* ============================================== */

CREATE OR REPLACE VIEW v_meuble_worker AS
SELECT w.type_worker, m.id_meuble, SUM(m.number_worker * m.salary) as salaire_horaire
FROM Worker w
JOIN MeubleWorker m ON w.id_worker = m.id_worker
GROUP BY w.type_worker, m.id_meuble, m.number_worker , m.salary;

--SELECT * FROM v_meuble_worker;

/* ============================================== */

CREATE OR REPLACE VIEW v_prix_de_revient AS
SELECT v_meuble_worker.id_meuble, (v_price_meuble.total_material_price + v_meuble_worker.salaire_horaire) as prix_revient
FROM v_price_meuble 
JOIN v_meuble_worker ON v_price_meuble.id_meuble = v_meuble_worker.id_meuble
GROUP BY  v_meuble_worker.id_meuble, v_price_meuble.total_material_price, v_meuble_worker.salaire_horaire;


--SELECT * FROM v_prix_de_revient;

/* ============================================= */

CREATE OR REPLACE VIEW v_achat_par_genre AS
SELECT
    C.gender AS Genre,
    COUNT(A.id_meuble) AS NombreAchats
FROM
    Client C
JOIN
    AchatMeuble A ON C.id_client = A.id_client
GROUP BY
    C.gender;

/* ============================================= */

CREATE OR REPLACE VIEW v_achat_par_style AS
SELECT
    S.intitule AS style_intitule,
    COALESCE(SUM(AM.qte), 0) AS nombre_achats
FROM
    Style S
LEFT JOIN
    Meuble M ON S.id_style = M.id_style
LEFT JOIN
    AchatMeuble AM ON M.id_meuble = AM.id_meuble
GROUP BY
    S.intitule;


CREATE OR REPLACE VIEW v_total_achat AS
SELECT
    COUNT(a.id_meuble) AS NombreAchats
FROM
    AchatMeuble a
;