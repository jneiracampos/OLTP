
package uniandes.isis2304.superAndes.negocio;


public class ProveedorSucursal implements VOProveedorSucursal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de la sucursal
	 */
	private long idSucursal;
	
	/**
	 * El identificador del proveedor
	 */
	private long idProveedor;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ProveedorSucursal() 
	{
		this.idSucursal = 0;
		this.idProveedor = 0;
	}

	/**
	 * Constructor con valores
	 */
	public ProveedorSucursal(long idSucursal, long idProveedor) 
	{
		this.idSucursal = idSucursal;
		this.idProveedor = idProveedor;
	}

	/**
	 * @return El idSucursal
	 */
	public long getIdSucursal()
	{
		return idSucursal;
	}

	/**
	 * @param idSucursal - El nuevo idSucursal. Debe existir una sucursal con dicho identificador
	 */
	public void setId_sucursal(long idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	/**
	 * @return El idProveedor
	 */
	public long getIdProveedor()
	{
		return idProveedor;
	}

	/**
	 * @param idBar - El nuevo idProveedor. Debe exixtir un proveedor con dicho identificador
	 */
	public void setId_proveedor(long idProveedor)
	{
		this.idProveedor = idProveedor;
	}

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Visitan [idSucursal=" + idSucursal + ", idProveedor=" + idProveedor + "]";
	}
}
