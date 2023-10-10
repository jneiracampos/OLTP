package uniandes.isis2304.superAndes.negocio;

public class ProductoFactura implements VOProductoFactura {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador idFactura de los productoFactura
	 */
	private long idFactura;
	
	/**
	 * El idProducto del productoFactura
	 */
	private long idProducto;

	/**
	 * La cantidad donde se encuentra el productoFactura
	 */
	private int cantidad;
	
	/**
	 * El promocion del productoFactura 
	 */
	private Object promocion;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public ProductoFactura() 
    {
    	this.idFactura = 0;
		this.idProducto = 0;
		this.cantidad = 0;
		this.promocion = 0;
		
	}

	/**
	 * Constructor con valores
	 * @param idFactura - El idFactura del bart
	 * @param idProducto - El idProducto del productoFactura
	 * @param cantidad - La cantidad del productoFactura
	 * @param promocion - El promocion del productoFactura (ALTO, MEDIO, BAJO)
	 */
    public ProductoFactura(long idFactura, long idProducto, int cantidad, Object promocion) 
    {
    	this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.promocion = promocion;

	}

    /**
	 * @return El idFactura del productoFactura
	 */
	public long getIdFactura() 
	{
		return idFactura;
	}
	
	/**
	 * @param idFactura - El nuevo idFactura del productoFactura
	 */
	public void setId_factura(long idFactura) 
	{
		this.idFactura = idFactura;
	}
	
	/**
	 * @return el idProducto del productoFactura
	 */
	public long getIdProducto() 
	{
		return idProducto;
	}
	
	/**
	 * @param idProducto El nuevo idProducto del productoFactura
	 */
	public void setId_producto(int idProducto) 
	{
		this.idProducto = idProducto;
	}
	
	/**
	 * @return la cantidad del productoFactura
	 */
	public int getCantidad() 
	{
		return cantidad;
	}
	
	/**
	 * @param cantidad - La nueva cantidad del productoFactura
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}
	
	/**
	 * @return El promocion del productoFactura
	 */
	public Object getPromocion() 
	{
		return promocion;
	}
	
	/**
	 * @param promocion - El nuevo promocion del productoFactura
	 */
	public void setPromocion(Object promocion) 
	{
		this.promocion = promocion;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del productoFactura
	 */
	public String toString() 
	{
		return "ProductoFactura [idFactura=" + idFactura + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", promocion=" + promocion
				+ "]";
	}
	
}
