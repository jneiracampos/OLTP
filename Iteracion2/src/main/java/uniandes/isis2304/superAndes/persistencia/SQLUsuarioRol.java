package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.UsuarioRol;


class SQLUsuarioRol 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUsuarioRol (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

    public long adicionarUsuarioRol (PersistenceManager pm, long idUsuario, long idRol, long idSucursal)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarioRol () + " values (?, ?, ?)");
		q.setParameters(idUsuario, idRol, idSucursal);
		return (long) q.executeUnique();          
    }

    public long eliminarUsuarioRolPorId (PersistenceManager pm, long idUsuario, long idRol)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioRol () + " WHERE idUsuario = ? AND idRol = ?");
        q.setParameters(idUsuario, idRol);
        return (long) q.executeUnique();            
    }

    public List<UsuarioRol> darUsuarioRol (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioRol ());
        q.setResultClass(UsuarioRol.class);
        return (List<UsuarioRol>) q.executeList();
    }

    public UsuarioRol darUsuarioRolPorId(PersistenceManager pm, long idUsuario, long idRol)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioRol () + " WHERE id_usuario = ? AND id_rol = ?");
        q.setResultClass(UsuarioRol.class);
        q.setParameters(idUsuario, idRol);
        return (UsuarioRol) q.executeUnique();
    }

}
