package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto SUCURSAL del negocio de los AforaAndes
 */
public class Sucursal implements VOSucursal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de las Sucurales
	 */
	private long id;
	
	/**
	 * El nombre de la sucursal
	 */
	private String nombre;

	/**
	 * La ciudad donde se encuentra la sucursal
	 */
	private String ciudad;
	
	/**
     * La direccion donde se encuentra la sucursal
	 */
	private String direccion;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
	public Sucursal() 
    {
    	this.id = 0;
		this.nombre = "";
		this.ciudad = "";
		this.direccion = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Sucursal(long id, String nombre, String ciudad, String direccion) 
    {
    	this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}

    /**
	 * @return El id
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return la ciudad
	 */
	public String getCiudad() 
	{
		return ciudad;
	}
	
	/**
	 * @param ciudad - La nueva ciudad
	 */
	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * @return La direccion
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * @param direccion
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del sucursal
	 */
	public String toString() 
	{
		return "Bar [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ "]";
	}
	

}
