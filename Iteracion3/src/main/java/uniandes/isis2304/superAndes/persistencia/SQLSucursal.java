package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL de AforaAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLSucursal 
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
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una SUCURSAL a la base de datos de AforaAndes
	 * @param pm - El manejador de persistencia
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSucursal (PersistenceManager pm, long idSucursal, String nombre, String ciudad, String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal () + " values (?, ?, ?, ?)");
        q.setParameters(idSucursal, nombre, ciudad, direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar SUCURSAL de la base de datos de AforaAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreSucursal - El nombre de la sucursal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSucursalPorNombre (PersistenceManager pm, String nombreSucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
        q.setParameters(nombreSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA SUCURSAL de la base de datos de AforaAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador de la sucursal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSucursalPorId (PersistenceManager pm, long idSucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE id = ?");
        q.setParameters(idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA SUCURSAL de la 
	 * base de datos de AforaAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador de la sucursal
	 * @return El objeto SUCURSAL que tiene el identificador dado
	 */
	public Sucursal darSucursalPorId (PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idSucursal);
		return (Sucursal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la AforaAndes SQL para encontrar la información de LAS SUCURSALES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreSucursal - El nombre de la sucursal buscado
	 * @return Una lista de objetos SUCURSAL que tienen el nombre dado
	 */
	public List<Sucursal> darSucursalesPorNombre (PersistenceManager pm, String nombreSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(nombreSucursal);
		return (List<Sucursal>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS SUCURSALES de la 
	 * base de datos de AforaAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SUCURSAL
	 */
	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal ());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
}
