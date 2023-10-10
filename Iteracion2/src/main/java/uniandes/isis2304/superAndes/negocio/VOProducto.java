package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de PRODUCTO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProducto 
{
	public long getId();

	public String getNombre();

	public String getMarca();

    public String getPresentacion();

    public long getIdCategoriaProducto();

    public int getPrecioUnitario();

    public int getCantidadPresentacion();

    public int getVolumenEmpaque();

    public int getPesoEmpaque();

    public int getPrecioPorUnidad();

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();

}
