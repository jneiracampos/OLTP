--Mostrar el indice de ocupacion de cada una de las bodegas
SELECT *
FROM A_BODEGA
WHERE idsucursal = 24;

SELECT * 
FROM A_ESTANTE
WHERE id_sucursal = 24;

--Mostrar los productos que cumplen con ciertas caracteristicas
SELECT * 
FROM A_PRODUCTO
WHERE id_categoria_producto = 41;


