package uniandes.isis2304.superAndes.negocio;

public class Empleado implements VOEmpleado {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;
	
	private String nombre;

	private String correo;
	
	private long idSucursal;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Empleado()
	{
		this.id = 0;
		this.nombre = "";
		this.correo = "";
		this.idSucursal = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del empleado
	 * @param correo - La correo del empleado
	 * @param idSucursal - El idSucursal del empleado
	 */
	public Empleado(long id, String nombre, String correo, long idSucursal) 
	{
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.idSucursal = idSucursal;
	}
	
    /**
	 * @return El id del empleado
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del empleado
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del empleado
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del empleado
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return la correo del empleado
	 */
	public String getcorreo() 
	{
		return correo;
	}
	
	/**
	 * @param correo - La nueva correo del empleado
	 */
	public void setcorreo(String correo) 
	{
		this.correo = correo;
	}
	
	/**
	 * @return El sucursal del empleado
	 */
	public long getSucursal() 
	{
		return idSucursal;
	}
	
	/**
	 * @param sucursal - El nuevo sucursal del empleado (ALTO, MEDIO, BAJOO)
	 */
	public void setsucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del empleado
	 */
	public String toString() 
	{
		return "Empleado [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", idSucursal=" + idSucursal
				+ "]";
	}
	
}
