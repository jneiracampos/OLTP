package uniandes.isis2304.superAndes.negocio;

import java.time.LocalDate;

public interface VOOrdenPedido 
{
    /*****************************************************************
     *             Métodos
     * ****************************************************************/
    
     /**
	 * @return El id del ordenPedido
	 */
	public long getId();
	
	/**
	 * @return la fechaEsperadaEntrega del ordenPedido
	 */
	public LocalDate getFechaEsperadaEntrega();
	
	/**
	 * @return El fechaEntrega del ordenPedido
	 */
	public LocalDate getFechaEntrega();
	
	/**
	 * @return la calificacion del ordenPedido
	 */
	public int getCalificacion();

    /**
	 * @return el idProovedor del ordenPedido
	 */
	public long getidProveedor();

      /**
	 * @return el idSucursal del ordenPedido
	 */
	public long getidSucursal();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del ordenPedido
	 */
	public String toString();

}
