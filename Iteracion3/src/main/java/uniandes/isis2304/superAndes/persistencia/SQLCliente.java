package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;

public class SQLCliente {
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
    public SQLCliente (PersistenciaSuperAndes pp)
    {
    	this.pp = pp;
    }

    public long adicionarCliente (PersistenceManager pm, long id, String correo, String direccion, String tipoCliente, int puntos, String palabra_clave)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente() + " (id, correo, direccion, tipo_cliente, puntos, palabra_clave) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, correo, direccion, tipoCliente, puntos, palabra_clave);
        return (long) q.executeUnique();
    } 

    public long eliminarClientePorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Cliente darClientePorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE id = ?");
        q.setResultClass(Cliente.class);
        q.setParameters(id);
        return (Cliente) q.executeUnique();
    }

    public List<Cliente> darClientes (PersistenceManager pm)
    {
    	Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente());
    	q.setResultClass(Cliente.class);
    	return (List<Cliente>) q.executeList();
    }
}
