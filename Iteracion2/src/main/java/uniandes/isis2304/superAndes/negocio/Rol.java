package uniandes.isis2304.superAndes.negocio;

public class Rol implements VORol
{
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;

    private String nombre;

    private String descripcion;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/ 
    
    public Rol() 
    {
    	this.id = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public Rol(long id, String nombre, String descripcion) 
    {
    	this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() 
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() 
    {
        return "Rol [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }
}
