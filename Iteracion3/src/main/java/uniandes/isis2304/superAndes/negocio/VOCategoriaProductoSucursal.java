package uniandes.isis2304.superAndes.negocio;

public interface VOCategoriaProductoSucursal 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
     /**
     * @return El id de la sucursal
     */
    public long getIdSucursal();

    /**
     * @return El id de la categoria
     */
    public long getIdCategoria();

    /**
     * @return Una cadena de caracteres con todos los atributos del categoriaProductoSucursal
     */
    @Override
    public String toString();
}
