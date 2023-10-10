package uniandes.isis2304.superAndes.negocio;

import java.time.LocalDate;

public interface VOPromocion 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	 /**
	  * @return El id de la promocion
	  */
	public long getId();
	
	/**
	 * @return El idProducto de la promocion
	 */
	public long getIdProducto();
	
	/**
	 * @return La fechaFin de la promocion
	 */
	public LocalDate getFechaFin();
	
	/**
	 * @return El tipoPromocion de la promocion
	 */
	public String getTipoPromocion();
	
	/**
	 * @return La promocion de la promocion
	 */
	public String getPromocion();
	
	/**
	 * @return El idSucursal de la promocion
	 */
	public long getIdSucursal();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la promocion
	 */
	public String toString();
}
