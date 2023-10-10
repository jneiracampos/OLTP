ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT bebedores.id, bebedores.nombre, bebedores.ciudad, bebedores.presupuesto, bares.presupuesto as presupuestoBar, COUNT(*) veces
FROM bebedores, frecuentan, bares
WHERE bebedores.id = id_bebedor and id_bar = bares.id and bebedores.presupuesto = 'Medio' and bebedores.id not in (SELECT
            bebedores.id
        FROM
            parranderos.bebedores, parranderos.frecuentan, parranderos.bares
        WHERE
             bebedores.id = id_bebedor
              AND id_bar = bares.id
              AND bebedores.presupuesto = 'Medio' 
        GROUP BY
            bares.presupuesto, bebedores.id
        HAVING bares.presupuesto = 'Bajo'
               OR bares.presupuesto = 'Medio')
GROUP BY bebedores.id, bebedores.nombre, bebedores.ciudad, bebedores.presupuesto, bares.presupuesto
ORDER BY bebedores.id;



 

