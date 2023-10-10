package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PROVEEDOR del negocio de los Parranderos
 */
public class Proveedor implements VOProveedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;	
	
	/**
	 * El nombre del proveedor
	 */
	private String nombre;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Proveedor() 
	{
		this.id = 0;
		this.nombre = "";
	}

	/**
	 * Constructor con valores
	 * @param id 
	 * @param nombre
	 */
	public Proveedor(long id, String nombre) 
	{
		this.id = id;
		this.nombre = nombre;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return Una cadena de caracteres con la información básica del bebedor
	 */
	@Override
	public String toString() 
	{
		return "Proveedor [id=" + id + ", nombre=" + nombre + "]";
	}
}
