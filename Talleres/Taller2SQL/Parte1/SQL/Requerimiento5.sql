SELECT casiFinal.tipo, casiFinal.baresAlta, casiFinal.baresMedia, casiFinal.baresBaja, casiFinal.bebAlta, casiFinal.bebMedia, relacion2BebBaja.bebBaja

   FROM (SELECT relacion2BarAlta.tipo, relacion2BarAlta.baresAlta,relacion2BarMedia.baresMedia, relacion2BarBaja.baresBaja,relacion2BebAlta.bebAlta, relacion2BebMedia.bebMedia
    
       FROM (SELECT relacionBarAlta.tipo, COUNT(relacionBarAlta.presupuesto)as baresAlta
            
            FROM (SELECT  bebidas.tipo, bares.presupuesto  
            
            FROM parranderos.bebidas, parranderos.sirven, parranderos.bares
            
            WHERE bebidas.id = sirven.id_bebida
            
            AND bares.presupuesto ='Alto'
            
            AND bares.id = sirven.id_bar)relacionBarAlta 
            
            group by relacionBarAlta.tipo)relacion2BarAlta,
            
            (SELECT relacionBarMedia.tipo, COUNT(relacionBarMedia.presupuesto)as baresMedia
            
            FROM (SELECT  bebidas.tipo, bares.presupuesto  
            
            FROM parranderos.bebidas, parranderos.sirven, parranderos.bares
            
            WHERE bebidas.id = sirven.id_bebida
            
            AND bares.presupuesto ='Medio'
            
            AND bares.id = sirven.id_bar)relacionBarMedia
            
            group by relacionBarMedia.tipo)relacion2BarMedia,
            
            (SELECT relacionBarBaja.tipo, COUNT(relacionBarBaja.presupuesto)as baresBaja
            
            FROM (SELECT  bebidas.tipo, bares.presupuesto  
            
            FROM parranderos.bebidas, parranderos.sirven, parranderos.bares
            
            WHERE bebidas.id = sirven.id_bebida
            
            AND bares.presupuesto ='Bajo'
            
            AND bares.id = sirven.id_bar)relacionBarBaja
            
            group by relacionBarBaja.tipo)relacion2BarBaja,
            
            (SELECT relacionBebAlta.tipo, COUNT(relacionBebAlta.presupuesto)as bebAlta
            
            FROM (SELECT  bebidas.tipo, bebedores.presupuesto  
            
            FROM parranderos.bebidas, parranderos.gustan, parranderos.bebedores
            
            WHERE bebidas.id = gustan.id_bebedor
            
            AND bebedores.presupuesto ='Alto'
            
            AND bebedores.id = gustan.id_bebedor)relacionBebAlta
            
            group by relacionBebAlta.tipo)relacion2BebAlta,
            
            (SELECT relacionBebMedia.tipo, COUNT(relacionBebMedia.presupuesto)as bebMedia
            
            FROM (SELECT  bebidas.tipo, bebedores.presupuesto  
            
            FROM parranderos.bebidas, parranderos.gustan, parranderos.bebedores
            
            WHERE bebidas.id = gustan.id_bebedor
            
            AND bebedores.presupuesto ='Medio'
            
            AND bebedores.id = gustan.id_bebedor)relacionBebMedia
            
            group by relacionBebMedia.tipo)relacion2BebMedia
            
            WHERE relacion2BarAlta.tipo = relacion2BarMedia.tipo
            
            AND relacion2BarBaja.tipo = relacion2BarAlta.tipo
            
            AND relacion2BebAlta.tipo = relacion2BarAlta.tipo
            
            AND relacion2BebMedia.tipo = relacion2BarAlta.tipo)casiFinal
        
        FULL OUTER JOIN
        
        (SELECT relacionBebBaja.tipo, COUNT(relacionBebBaja.presupuesto)as bebBaja
            
            FROM (SELECT  bebidas.tipo, bebedores.presupuesto  
            
            FROM parranderos.bebidas, parranderos.gustan, parranderos.bebedores
            
            WHERE bebidas.id = gustan.id_bebedor
            
            AND bebedores.presupuesto ='Bajo'
            
            AND bebedores.id = gustan.id_bebedor)relacionBebBaja
            
            group by relacionBebBaja.tipo)relacion2BebBaja
            
        ON casiFinal.tipo = relacion2BebBaja.tipo
        
        ORDER BY casiFinal.tipo