package uniandes.isis2304.superAndes.negocio;

public interface VOProductoSucursal
{
    /*****************************************************************
     * 			Métodos 
     *****************************************************************/

    /**
     * @return El idSucursal del productoSucursal
     */
    public long getIdSucursal();

    /**
     * @return El idProveedor del productoSucursal
     */
    public long getIdProducto();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();

}
