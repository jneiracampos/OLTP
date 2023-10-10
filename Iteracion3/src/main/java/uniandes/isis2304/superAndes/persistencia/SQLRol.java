package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Rol;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ROL de AforaAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLRol
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
	public SQLRol (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una SUCURSAL a la base de datos de AforaAndes
	 * @param pm - El manejador de persistencia
	 * @return El número de tuplas insertadas
	 */
	public long adicionarRol (PersistenceManager pm, long idRol, String nombre, String descripcion)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRol () + " values (?, ?, ?)");
        q.setParameters(idRol, nombre, descripcion);
        return (long) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA SUCURSAL de la base de datos de AforaAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador de la sucursal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRolPorId (PersistenceManager pm, long idRol)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol () + " WHERE id = ?");
        q.setParameters(idRol);
        return (long) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA SUCURSAL de la 
	 * base de datos de AforaAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador de la sucursal
	 * @return El objeto SUCURSAL que tiene el identificador dado
	 */
	public Rol darRolPorId (PersistenceManager pm, long idRol)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol () + " WHERE id = ?");
        q.setResultClass(Rol.class);
        q.setParameters(idRol);
        return (Rol) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS SUCURSALES de la 
	 * base de datos de AforaAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SUCURSAL
	 */
	public List<Rol> darRoles (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol ());
        q.setResultClass(Rol.class);
        return (List<Rol>) q.executeList();
    }
	
}
