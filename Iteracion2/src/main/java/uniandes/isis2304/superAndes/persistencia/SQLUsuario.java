package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Usuario;

public class SQLUsuario {
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
	 * @return 
	 */
    public SQLUsuario (PersistenciaSuperAndes pp)
    {
    	this.pp = pp;
    }

    public long adicionarUsuario (PersistenceManager pm, long id, String nombre, String correo, String palabraClave)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + " values (?, ?, ?, ?)");
        q.setParameters(id, nombre, correo, palabraClave);
        return (long) q.executeUnique();
    }

    public long eliminarUsuarioPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Usuario darUsuarioPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE id = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(id);
        return (Usuario) q.executeUnique();
    }

    public List<Usuario> darUsuarios (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario());
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }
}
