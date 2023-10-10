package uniandes.isis2304.superAndes.negocio;

public interface VOCategoriaProducto {
    
        /* ****************************************************************
        * 			Métodos 
        *****************************************************************/
        /**
        * @return El id de la categoriaProducto
        *
        */
        public long getId();
    
        /**
        * @return El tipo de la categoriaProducto
        *
        */
        public String getTipo();
    
        /**
        * @return Una cadena de caracteres con todos los atributos de la categoriaProducto
        */
        @Override
        public String toString();
    
}
