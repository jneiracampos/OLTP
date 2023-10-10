SELECT table_name, numcolumnas, numcolsnull, last_analyzed
FROM ((SELECT table_name, COUNT(column_name) AS numcolumnas
FROM ALL_TAB_COLUMNS
WHERE owner = 'PARRANDEROS' 
GROUP BY table_name) numcol 
NATURAL INNER JOIN 
(SELECT table_name, COUNT(column_name) AS numcolsnull
FROM ALL_TAB_COLUMNS
WHERE owner = 'PARRANDEROS' and nullable = 'Y' 
GROUP BY table_name) tablenumcolsnull) 
NATURAL INNER JOIN (
SELECT table_name, last_analyzed
FROM ALL_TABLES
WHERE owner = 'PARRANDEROS')
ORDER BY table_name;