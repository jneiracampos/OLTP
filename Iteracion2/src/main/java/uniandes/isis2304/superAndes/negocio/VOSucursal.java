package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de SUCURSAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */

public interface VOSucursal 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id
	 */
	public long getId();
	
	/**
	 * @return el nombre
	 */
	public String getNombre();
	
	/**
	 * @return la ciudad
	 */
	public String getCiudad();
	
	/**
	 * @return direccion
	 */
	public String getDireccion();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
