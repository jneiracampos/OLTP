package uniandes.isis2304.superAndes.negocio;

public class ProductoSucursal implements VOProductoSucursal {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador idSucursal producto de los productoSucursal
	 */
	private long idSucursal;
	
	/**
	 * El idProducto del ProductoSucursal
	 */
	private long idProducto;

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public ProductoSucursal() 
    {
    	this.idSucursal = 0;
		this.idProducto = 0;
	}

	/**
	 * Constructor con valores
	 * @param idSucursal - El idSucursal del productoSucursal
	 * @param idProducto - El idProducto del ProductoSucursal
	 */
    public ProductoSucursal(long idSucursal, long idProducto) 
    {
    	this.idSucursal = idSucursal;
		this.idProducto = idProducto;
		
	}

    /**
	 * @return El idSucursal del ProductoSucursal
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}
	
	/**
	 * @param idSucursal - El nuevo idSucursal del ProductoSucursal
	 */
	public void setId_sucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	/**
	 * @return el idProducto del ProductoSucursal
	 */
	public long getIdProducto() 
	{
		return idProducto;
	}
	
	/**
	 * @param idProducto El nuevo idProducto del ProductoSucursal
	 */
	public void setId_producto(long idProducto) 
	{
		this.idProducto = idProducto;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del ProductoSucursal
	 */
	public String toString() 
	{
		return "ProductoSucursal [idSucursal=" + idSucursal + ", idProducto=" + idProducto + "]";
	}    
}
