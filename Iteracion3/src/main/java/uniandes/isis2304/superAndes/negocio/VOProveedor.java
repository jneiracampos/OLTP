package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de PROVEEDOR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */

public interface VOProveedor 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return El id del proveedor
	 */
	public long getId();

	/**
	 * @return El nombre del proveedor
	 */
	public String getNombre();

	/**
	 * @return Una cadena de caracteres con la información básica del proveedor
	 */
	@Override
	public String toString();
}
