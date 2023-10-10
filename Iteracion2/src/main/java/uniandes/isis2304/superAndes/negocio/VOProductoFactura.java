package uniandes.isis2304.superAndes.negocio;

public interface VOProductoFactura 
{
    /*****************************************************************
     * 			Métodos 
     *****************************************************************/

    /**
     * @return El idFactura del productoFactura
     */
    public long getIdFactura();

    /**
     * @return El idProducto del productoFactura
     */
    public long getIdProducto();

    /**
     * @return La cantidad del productoFactura
     */
    public int getCantidad();

    /**
     * @return El promocion del productoFactura
     */
    public Object getPromocion();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoFactura
     */
    public String toString();

}
