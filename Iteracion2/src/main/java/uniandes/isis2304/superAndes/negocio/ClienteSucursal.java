package uniandes.isis2304.superAndes.negocio;

public class ClienteSucursal implements VOClienteSucursal {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los ClienteSucursal
	 */
	private long idSucursal;
	
	/**
	 * El identificador ÚNICO de los ClienteSucursal
	 */
	private long idCliente;

	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public ClienteSucursal() 
    {
    	this.idSucursal= 0;
		this.idCliente = 0;
		
    }
	/**
	 * Constructor con valores
	 * @param idSucursal - El idSucursal del bart
	 * @param idCliente - El idCliente del clienteSucursal
	 */
    public ClienteSucursal(long idSucursal, long idCliente) 
    {
    	this.idSucursal = idSucursal;
		this.idCliente = idCliente;
		
	}

    /**
	 * @return El idSucursal del clienteSucursal
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}

    /**
	 * @return El idCliente del clienteSucursal
	 */
	public long getIdCliente() 
	{
		return idSucursal;
	}
	
	/**
	 * @param idSucursal - El nuevo idSucursal del clienteSucursal
	 */
	public void setIdSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}

    /**
	 * @param idCliente - El nuevo idSucursal del clienteSucursal
	 */
	public void setIdCliente(long idCliente) 
	{
		this.idCliente = idCliente;
	}
	

	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del clienteSucursal
	 */
	public String toString() 
	{
		return "ClienteSucursal [idSucursal=" + idSucursal + ", idCliente=" + idCliente +"]";
	}
	
}
