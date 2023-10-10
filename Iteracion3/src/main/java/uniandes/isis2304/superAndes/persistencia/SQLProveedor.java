package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEDOR de AforaAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */

class SQLProveedor 
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
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarProveedor (PersistenceManager pm, long idProveedor, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor () + " values (?, ?)");
        q.setParameters(idProveedor, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del proveedor
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProveedorPorId (PersistenceManager pm, long idProveedor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE id = ?");
        q.setParameters(idProveedor);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PROVEEDOR de la 
	 * base de datos de AforaAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR que tiene el identificador dado
	 */
	public Proveedor darProveedorPorId (PersistenceManager pm, long idProveedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE id = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(idProveedor);
		return (Proveedor) q.executeUnique();
	}

	/**
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEDOR
	 */
	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor ());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}

}
