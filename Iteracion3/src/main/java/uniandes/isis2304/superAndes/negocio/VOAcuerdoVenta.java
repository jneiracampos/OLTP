package uniandes.isis2304.superAndes.negocio;

public interface VOAcuerdoVenta 
{
    /**
     * @return El id del acuerdo de venta
     */
    public long getId();

    /**
     * @return El producto del acuerdo de venta
     */
    public long getProducto();

    /**
     * @return La cantidad del acuerdo de venta
     */
    public int getCantidad();

    /**
     * @return El precio del acuerdo de venta
     */
    public int getPrecio();

    /**
     * @return Una cadena de caracteres con todos los atributos del acuerdo de venta
     */
    @Override
    public String toString();
}
