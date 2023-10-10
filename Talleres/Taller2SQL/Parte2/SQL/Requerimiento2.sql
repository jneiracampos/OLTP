SELECT table_name, column_name as nombrecolspk, data_type as tipodedato
FROM
(SELECT table_name, column_name
FROM ALL_CONS_COLUMNS
WHERE owner = 'PARRANDEROS' and constraint_name LIKE 'PK_%')pk
NATURAL INNER JOIN
(SELECT table_name, column_name, data_type
FROM ALL_TAB_COLUMNS
WHERE owner = 'PARRANDEROS') data
ORDER BY table_name ASC, column_name ASC; 
