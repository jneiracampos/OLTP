package uniandes.isis2304.superAndes.negocio;

public interface VOEstante 
{
    /*****************************************************************
    * 			Métodos 
    *****************************************************************/

    /**
	 * @return El id del estante
	 */
	public long getId();

	
	/**
	 * @return la capacidadTotal del estante
	 */
	public int getCapacidadTotal();
	
	/**
	 * @return la nivelAbastecimiento del estante
	 */
	public int getAbastecimiento();
	
	/**
	 * @return El categoriaProducto del estante
	 */
	public int getCategoriaProducto();

	/**
	 * @return El idSucursal del estante
	 */
	public long getIdSucursal();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del estante
	 */
	public String toString();
}
