package uniandes.isis2304.superAndes.negocio;

public class UsuarioRol implements VOUsuarioRol
{
    	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private long id_usuario;

	private long id_rol;

	private Object id_sucursal;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public UsuarioRol()
	{
		this.id_usuario = 0;
		this.id_rol = 0;
		this.id_sucursal = null;
	}

	public UsuarioRol(long id_usuario, long id_rol, Object id_sucursal) 
	{
		this.id_usuario = id_usuario;
		this.id_rol = id_rol;
		this.id_sucursal = id_sucursal;
	}

	public long getIdUsuario() 
	{
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) 
	{
		this.id_usuario = id_usuario;
	}

	public long getIdRol() 
	{
		return id_rol;
	}

	public void setId_rol(long id_rol) 
	{
		this.id_rol = id_rol;
	}

	public Object getIdSucursal() 
	{
		return id_sucursal;
	}

	public void setId_sucursal(Object id_sucursal) 
	{
		this.id_sucursal = id_sucursal;
	}

	@Override
	public String toString() 
	{
		return "UsuarioRol [idUsuario=" + id_usuario + ", idRol=" + id_rol + ", idSucursal=" + id_sucursal + "]";
	}
}
