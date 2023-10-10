SELECT table_name, column_name, data_type, NVL(constraint_name, 'No tiene') as nombrerestriccion, nullable as permitenulos
FROM
((SELECT table_name, column_name, data_type
FROM ALL_TAB_COLS
WHERE owner = 'PARRANDEROS')
NATURAL LEFT OUTER JOIN
(SELECT table_name, column_name, constraint_name
FROM ALL_CONS_COLUMNS
WHERE owner = 'PARRANDEROS'))
NATURAL INNER JOIN
(SELECT table_name, column_name, nullable
FROM ALL_TAB_COLUMNS
WHERE owner = 'PARRANDEROS')
ORDER BY table_name ASC, column_name ASC, constraint_name ASC;