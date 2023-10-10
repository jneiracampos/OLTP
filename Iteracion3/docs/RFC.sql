--FC7 -- Fecha de mayor demanda
SELECT frecuencia, fecha 
FROM (SELECT COUNT(fecha) as frecuencia, fecha FROM A_FACTURA WHERE fecha BETWEEN '14/11/22' AND '14/01/23' GROUP BY fecha)
WHERE frecuencia = (SELECT MAX(COUNT(fecha)) FROM A_FACTURA WHERE fecha BETWEEN '14/11/22' AND '14/01/23' GROUP BY fecha);

--RFC 8 Encontrar los clientes frecuentes
SELECT frecuencia, idcliente as id_cliente__frecuente 
FROM (SELECT COUNT(idcliente) as frecuencia, idcliente FROM A_FACTURA WHERE fecha BETWEEN '14/11/22' AND '14/01/23' GROUP BY idcliente)
WHERE frecuencia >= 2;

--RFC 9 Productos que no tienen mucha demanda 
SELECT demanda, id_producto
FROM (SELECT COUNT(id_producto) as demanda, id_producto 
FROM A_ACUERDO_VENTA
GROUP BY id_producto)
WHERE demanda = (SELECT MIN(COUNT(id_producto)) FROM A_ACUERDO_VENTA GROUP BY id_producto);


