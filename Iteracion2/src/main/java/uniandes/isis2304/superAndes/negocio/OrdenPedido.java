package uniandes.isis2304.superAndes.negocio;

public class OrdenPedido implements VOOrdenPedido {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de las orden
	 */
	private long id;

	/**
	 * La fechaEsperadaEntrega donde se encuentra el ordenPedido
	 */
	private Object fechaEsperadaEntrega;
	
	/**
	 * El fechaEntrega del ordenPedido
	 */
	private Object fechaEntrega;
	
	/**
	 * El número de sedes del ordenPedido en la fechaEsperadaEntrega
	 */
	private Object calificacion;

    /**
	 * El id de la sucursal
	 */
	private long idSucursal;

    /**
	 * El id del proveedor
	 */
	private long idProveedor;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public OrdenPedido() 
    {
		this.id = 0;
		this.fechaEsperadaEntrega = null;
		this.fechaEntrega = null;
		this.calificacion = null;
		this.idSucursal = 0;
		this.idProveedor = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param fechaEsperadaEntrega - La fechaEsperadaEntrega del ordenPedido
	 * @param fechaEntrega - El fechaEntrega del ordenPedido (ALTO, MEDIO, BAJO)
	 * @param calificacion - Las sedes del ordenPedido (Mayor que 0)
	 */
	public OrdenPedido(long id, Object fechaEsperadaEntrega, Object fechaEntrega, Object calificacion, long idSucursal, long idProveedor) 
	{
		this.id = id;
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.fechaEntrega = fechaEntrega;
		this.calificacion = calificacion;
		this.idSucursal = idSucursal;
		this.idProveedor = idProveedor;
	}

    /**
	 * @return El id del ordenPedido
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del ordenPedido
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return la fechaEsperadaEntrega del ordenPedido
	 */
	public Object getFechaEsperadaEntrega()
	{
		return fechaEsperadaEntrega;
	}
	
	/**
	 * @param fechaEsperadaEntrega - La nueva fechaEsperadaEntrega del ordenPedido
	 */
	public void setFechaEsperadaEntrega(Object fechaEsperadaEntrega) 
	{
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}
	
	/**
	 * @return El fechaEntrega del ordenPedido
	 */
	public Object getFechaEntrega()
	{
		return fechaEntrega;
	}
	
	/**
	 * @param fechaEntrega - El nuevo fechaEntrega del ordenPedido 
	 */
	public void setFechaEntrega(Object fechaEntrega) 
	{
		this.fechaEntrega = fechaEntrega;
	}
	
	/**
	 * @return la calificacion del ordenPedido
	 */
	public Object getCalificacion() 
	{
		return calificacion;
	}
	
	/**
	 * @param calificacion - la nueva cantidad de sedes del ordenPedido
	 */
	public void setCalificacion(Object calificacion) 
	{
		this.calificacion = calificacion;
	}

    /**
	 * @return el idProovedor del ordenPedido
	 */
	public long getidProveedor() 
	{
		return idProveedor;
	}
	
	/**
	 * @param idProveedor - la nueva cantidad de sedes del ordenPedido
	 */
	public void setidProveedor(long idProveedor) 
	{
		this.idProveedor = idProveedor;
	}

      /**
	 * @return el idSucursal del ordenPedido
	 */
	public long getidSucursal() 
	{
		return idProveedor;
	}
	
	/**
	 * @param calificacion - la nueva cantidad de sedes del ordenPedido
	 */
	public void setidSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del ordenPedido
	 */
	public String toString() 
	{
		return "OrdenPedido [id=" + id + ", fechaEsperadaEntrega=" + fechaEsperadaEntrega + ", fechaEntrega=" + fechaEntrega + ", calificacion=" + calificacion + ", idSucursal=" + idSucursal + ", idProveedor=" + idProveedor + "]";
	}
}
