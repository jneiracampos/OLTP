package uniandes.isis2304.superAndes.negocio;


public class Promocion implements VOPromocion {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El producto de la promocion
	 */
	private long idProducto;

	/**
	 * La fechaFin donde se encuentra e la promocion
	 */
	private String fechaFin;
	
	/**
	 * El tipoPromocion de la promocion
	 */
	private String tipoPromocion;
	
	/**
	 * La promocion
	 */
	private String promocion;

	/**
	 * La sucursal de la promocion
	 */
	private long idSucursal;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Promocion () 
    {
    	this.id = 0;
		this.idProducto = 0;
		this.fechaFin = "";
		this.tipoPromocion = "";
		this.promocion = "";
		this.idSucursal = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param idProducto - El nombreProducto de la promocion
	 * @param fechaFin - La fechaFin de la promocion
	 * @param tipoPromocion - El tipoPromocion de la promocion (ALTO, MEDIO, BAJO)
	 * @param promocion - Las sedes de la promocion (Mayor que 0)
	 */
    public Promocion(long id, long idProducto, String fechaFin, String tipoPromocion, String promocion, long idSucursal)
	{
		this.id = id;
		this.idProducto = idProducto;
		this.fechaFin = fechaFin;
		this.tipoPromocion = tipoPromocion;
		this.promocion = promocion;
		this.idSucursal = idSucursal;
	}

    /**
	 * @return El id de la promocion
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id de la promocion
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El idProducto de la promocion
	 */
	public long getIdProducto()
	{
		return idProducto;
	}
	
	/**
	 * @param idProducto - El nuevo idProducto de la promocion
	 */
	public void setId_producto(long idProducto)
	{
		this.idProducto = idProducto;
	}
	
	/**
	 * @return la fechaFin de la promocion
	 */
	public String getFechaFin() 
	{
		return fechaFin;
	}
	
	/**
	 * @param fechaFin - La nueva fechaFin de la promocion
	 */
	public void setFechaFin(String fechaFin) 
	{
		this.fechaFin = fechaFin;
	}
	
	/**
	 * @return El tipoPromocion de la promocion
	 */
	public String getTipoPromocion() 
	{
		return tipoPromocion;
	}
	
	/**
	 * @param tipoPromocion - El nuevo tipoPromocion de la promocion (ALTO, MEDIO, BAJOO)
	 */
	public void setTipoPromocion(String tipoPromocion) 
	{
		this.tipoPromocion = tipoPromocion;
	}
	
	/**
	 * @return la promocion de la promocion
	 */
	public String getPromocion() 
	{
		return promocion;
	}
	
	/**
	 * @param promocion - la nueva promocion
	 */
	public void setPromocion(String promocion) 
	{
		this.promocion = promocion;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setId_sucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la promocion
	 */
	public String toString() 
	{
		return "Promocion [id=" + id + ", nombreProducto=" + idProducto + ", fechaFin=" + fechaFin + ", tipoPromocion=" + tipoPromocion
				+ ", promocion=" + promocion + "]";
	}
	
}
