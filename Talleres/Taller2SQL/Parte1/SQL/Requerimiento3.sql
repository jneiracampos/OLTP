ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT*
FROM(
    SELECT BARES.Id, BARES.Nombre, BARES.Presupuesto,CUENTABARES.CuentaIDS
    FROM BARES
        INNER JOIN (
            SELECT Id_Bar, COUNT(Id_Bar)CuentaIds
            FROM FRECUENTAN
            GROUP BY Id_Bar)CUENTABARES ON
    BARES.Id = CUENTABARES.Id_Bar
ORDER BY CUENTABARES.CuentaIDS DESC,BARES.Nombre DESC
    )
WHERE rownum <=10;