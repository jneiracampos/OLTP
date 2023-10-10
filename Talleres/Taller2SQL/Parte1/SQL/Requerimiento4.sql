SELECT ciudad, COUNT(*) as cantidadBebedores
FROM(
SELECT bebedores.ciudad, bebedores.id
FROM PARRANDEROS.bebedores, PARRANDEROS.gustan, PARRANDEROS.bebidas
WHERE (bebedores.id = id_bebedor and id_bebida = bebidas.id and grado_alcohol > 25 and presupuesto = 'Alto')
GROUP BY bebedores.id, bebedores.ciudad
HAVING COUNT(*) > 4)
GROUP BY ciudad;