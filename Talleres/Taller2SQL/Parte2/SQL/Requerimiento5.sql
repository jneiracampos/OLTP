--Requerimiento 5

SELECT Table_Name, Column_Name, RESTRICIONES.NumRestriciones
FROM ALL_TAB_COLUMNS
INNER JOIN(
    SELECT Column_Name AS ColumnaNombre, COUNT(Constraint_Name) AS NumRestriciones
    FROM ALL_CONS_COLUMNS
    WHERE Table_Name 
            IN('BARES', 'BEBEDORES', 'BEBIDAS', 'FRECUENTAN', 'VISITAN', 'GUSTAN', 'SIRVEN', 'TIPO_BEBIDA')
    GROUP BY Column_Name)RESTRICIONES ON
    RESTRICIONES.ColumnaNombre = Column_Name
WHERE Table_Name 
    IN('BARES', 'BEBEDORES', 'BEBIDAS', 'FRECUENTAN', 'VISITAN', 'GUSTAN', 'SIRVEN', 'TIPO_BEBIDA')
    AND LENGTH(Column_Name)>0 
    AND (INSTR(Column_Name, 'R')) = 0
    AND (INSTR(Column_Name, 'A')+ INSTR(Column_Name, 'E')+INSTR(Column_Name, 'I')+INSTR(Column_Name, 'O')+INSTR(Column_Name, 'U'))>=2;
    

SELECT 'SELECT * FROM"' || TABLE_NAME || '";' FROM user_tables; 

SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;


SELECT ALL_TAB_COLUMNS.column_name as ColumnaNombre, PuntoB.numrestricciones, sys.all_tab_columns.TABLE_NAME as nombreTabla
    FROM sys.all_tab_columns, 
        (SELECT LENGTH(sys.all_tab_columns.column_name)
        - LENGTH(replace(replace(replace(replace(replace( lower(sys.all_tab_columns.column_name), 'a', ''), 'e', ''), 'i', ''), 'o', ''), 'u', '')
        ) as vocales, sys.all_tab_columns.column_name as nombreCol
    FROM sys.all_tab_columns 
            WHERE owner ='PARRANDEROS'
            GROUP BY sys.all_tab_columns.column_name)PuntoA, 
            (SELECT sys.all_cons_columns.column_name as nomColumn, 
             COUNT(sys.all_cons_columns.column_name) as NumRestricciones
    FROM sys.all_cons_columns
        WHERE owner ='PARRANDEROS'
        GROUP BY sys.all_cons_columns.column_name)PuntoB
        WHERE owner ='PARRANDEROS' 
            AND PuntoA.nombreCol = sys.all_tab_columns.column_name 
            AND PuntoB.nomColumn = sys.all_tab_columns.column_name
            AND PuntoA.vocales>=2;