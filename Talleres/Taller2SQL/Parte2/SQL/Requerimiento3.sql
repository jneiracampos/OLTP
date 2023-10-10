SELECT table_name, data_type, COUNT(data_type) as numcolstipodato, CAST(AVG(avg_col_len) as DECIMAL(10,2)) as promediolongitudcol
FROM ALL_TAB_COLS
WHERE owner = 'PARRANDEROS'
GROUP BY table_name, data_type
ORDER BY table_name ASC, data_type ASC, COUNT(data_type) ASC;