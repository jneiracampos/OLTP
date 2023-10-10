ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT bares.ciudad, bebidas.nombre
FROM(
SELECT id as idBebedor, id_bar as idBar
FROM 
(SELECT bebedores.id
FROM bebedores, gustan, bebidas
WHERE (bebedores.id = id_bebedor and id_bebida = bebidas.id and grado_alcohol BETWEEN 15 and 25 and presupuesto = 'Alto')
GROUP BY bebedores.id
HAVING COUNT(*) > 2) 
INNER JOIN 
(SELECT id_bebedor, id_bar
FROM (SELECT * FROM bares INNER JOIN frecuentan 
ON id = id_bar WHERE presupuesto = 'Alto'))
ON id = id_bebedor), sirven, bebidas, bares
WHERE idBar = sirven.id_Bar and sirven.id_bebida = bebidas.id and idBar = bares.id
GROUP BY bares.ciudad, bebidas.nombre
ORDER BY bares.ciudad, bebidas.nombre;




