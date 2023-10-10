package uniandes.isis2304.superAndes.negocio;


public interface VOFactura {

    public long getId();

    public long getIdCliente();

    public long getIdSucursal();

    public int getSubTotal();

    public int getTotalPagar();

    public int getPuntos();

    public String getFecha();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();

}
