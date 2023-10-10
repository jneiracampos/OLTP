package uniandes.isis2304.superAndes.negocio;

public interface VOCarroCompra 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
    /**
     * @return El id del carro de compra
     */
    public long getId();
    
    /**
     * @return El id del cliente
     */
    public long getIdCliente();

    /**
     * @return El estado del carro de compra
     */
    public String getIsActivo();

    /**
     * @return Booleano que indica si el carro de compra sigue activo o no
     */
    public void setIsActivo(String isActivo);

    /**
     * @return Una cadena de caracteres con todos los atributos del carro de compra
     */
    @Override
    public String toString();

    /**
     * @return El estado del carro de compra
     */
    public long getId_Sucursal();

}
