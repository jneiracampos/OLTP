package uniandes.isis2304.superAndes.negocio;

public class Reabastecer implements VOReabastecer {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long idBodega;
	
	private long idEstante;

	private int cantidad;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Reabastecer() 
    {
    	this.idBodega = 0;
		this.idEstante = 0;
		this.cantidad = 0;
	}

	/**
	 * Constructor con valores
	 * @param idBodega - El idBodega del bart
	 * @param idEstante - El idEstante del rebastecer
	 * @param cantidad - La cantidad del rebastecer
	 */
    public Reabastecer(long idBodega, long idEstante, int cantidad) 
    {
    	this.idBodega = idBodega;
		this.idEstante = idEstante;
		this.cantidad = cantidad;
	}

    /**
	 * @return El idBodega del rebastecer
	 */
	public long getIdBodega() 
	{
		return idBodega;
	}
	
	/**
	 * @param idBodega - El nuevo idBodega del rebastecer
	 */
	public void setIdBodega(long idBodega) 
	{
		this.idBodega = idBodega;
	}
	
	/**
	 * @return el idEstante del rebastecer
	 */
	public long getIdEstante() 
	{
		return idEstante;
	}
	
	/**
	 * @param idEstante El nuevo idEstante del rebastecer
	 */
	public void setIdEstante(int idEstante) 
	{
		this.idEstante = idEstante;
	}
	
	/**
	 * @return la cantidad del rebastecer
	 */
	public int getCantidad() 
	{
		return cantidad;
	}
	
	/**
	 * @param cantidad - La nueva cantidad del rebastecer
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del rebastecer
	 */
	public String toString() 
	{
		return "Rebastecer [idBodega=" + idBodega + ", idEstante=" + idEstante + ", cantidad=" + cantidad  + "]";
	}
	
}
