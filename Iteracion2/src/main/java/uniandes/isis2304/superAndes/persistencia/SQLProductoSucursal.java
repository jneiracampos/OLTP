package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoSucursal;

public class SQLProductoSucursal {
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
	public SQLProductoSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ProductoSucursal a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador del ProductoSucursal
	 * @param idProducto - El idProducto del ProductoSucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProductoSucursal (PersistenceManager pm, long idProducto, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoSucursal () + " values (?, ?)");
		q.setParameters(idProducto, idSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ProductoSucursal de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador del ProductoSucursal
	 * @param idSucursal - El identificador del ProductoSucursal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoSucursalPorId (PersistenceManager pm, long idProducto, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal () + " WHERE idproducto = ? AND idsucursal = ?");
		q.setParameters(idProducto, idSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ProductoSucursal de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador del ProductoSucursal
	 * @param idSucursal - El identificador del ProductoSucursal
	 * @return El objeto ProductoSucursal que tiene el identificador dado
	 */
	public ProductoSucursal darProductoSucursalPorId (PersistenceManager pm, long idProducto, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal () + " WHERE idproducto = ? AND idsucursal = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(idProducto, idSucursal);
		return (ProductoSucursal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ProductoSucursal de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ProductoSucursal
	 */
	public List<ProductoSucursal> darProductoSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal ());
		q.setResultClass(ProductoSucursal.class);
		return (List<ProductoSucursal>) q.executeList();
	}

}
