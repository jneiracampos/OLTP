package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto CARRRO_COMPRA del negocio de los AforaAndes
 */
public class CarroCompra implements VOCarroCompra 
{
    /* ****************************************************************
	* 			Atributos
	******************************************************************/

    /**
    * El identificador ÚNICO de los carros de compra
    */
    private long id;

    /**
     * El identificador ÚNICO del cliente
     */
    private long id_cliente;

    /**
     * Indica si el carro de compra sigue activo o no
     */
    private String isActivo;

     /**
     * Indica si el carro de compra sigue activo o no
     */
    private long id_Sucursal;

    /* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
    public CarroCompra()
    {
        this.id = 0;
        this.id_cliente = 0;
        this.isActivo = "TRUE";
        this.id_Sucursal = 0;
    }

    /**
     * Constructor con valores
     * @param id - El id del carro de compra
     * @param id_cliente - El id del cliente
     */
    public CarroCompra(long id, long id_cliente, String isActivo, long id_Sucursal)
    {
        this.id = id;
        this.id_cliente = id_cliente;
        this.isActivo = isActivo;
        this.id_Sucursal = id_Sucursal;
    }

    /**
     * @return El id del carro de compra
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id - El nuevo id del carro de compra
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return El id del cliente
     */
    public long getIdCliente()
    {
        return id_cliente;
    }

    /**
     * @param id_cliente - El nuevo id del cliente
     */
    public void setId_cliente(long id_cliente)
    {
        this.id_cliente = id_cliente;
    }

    /**
     * @return Boolean que indica si el carro de compra sigue activo o no
     */
    public String getIsActivo()
    {
        return isActivo;
    }

    /**
     * @param isActivo - Boolean que indica si el carro de compra sigue activo o no
     */
    public void setIsActivo(String isActivo)
    {
        this.isActivo = isActivo;
    }

    /**
     * @return Boolean que indica si el carro de compra sigue activo o no
     */
    public long getId_Sucursal()
    {
        return id_Sucursal;
    }

    /**
     * @param isActivo - Boolean que indica si el carro de compra sigue activo o no
     */
    public void setId_Sucursal(long id_Sucursal)
    {
        this.id_Sucursal = id_Sucursal;
    }

    @Override
	public String toString() 
	{
		return "CarroCompra [id=" + id + ", id_cliente=" + id_cliente + "]";
	}

}
