package uniandes.isis2304.superAndes.negocio;

public interface VOProveedorSucursal 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return El idSucursal
	 */
	public long getIdSucursal();

	/**
	 * @return El idProveedor
	 */
	public long getIdProveedor();

	/**
	 * @return Una cadena de caracteres con todos los atributos del proveedorSucursal
	 */
	@Override
	public String toString();
}
