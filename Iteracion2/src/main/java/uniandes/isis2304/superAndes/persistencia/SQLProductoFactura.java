package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoFactura;

public class SQLProductoFactura {
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
	public SQLProductoFactura (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador del bar
	 * @param idProducto - El idProducto del bar
	 * @param cantidad - La cantidad del bar
	 * @param promocion - El promocion del bar (ALTO, MEDIO, BAJO)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProductoFactura (PersistenceManager pm, long idFactura, long idProducto, int cantidad, Object promocion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoFactura() + " (idfactura, idproducto, cantidad, idpromocion) values (?, ?, ?, ?)");
        q.setParameters(idFactura, idProducto, cantidad, promocion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador del bar
	 * @param idProducto - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoFacturaPorId (PersistenceManager pm, long idFactura, long idProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoFactura() + " WHERE id = ? AND idProducto = ?");
		q.setParameters(idFactura, idProducto);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador del bar
	 * @param idProducto - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public ProductoFactura darProductoFacturaPorId (PersistenceManager pm, long idFactura, long idProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoFactura() + " WHERE id = ? AND idProducto = ?");
		q.setResultClass(ProductoFactura.class);
		q.setParameters(idFactura, idProducto);
		return (ProductoFactura) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<ProductoFactura> darProductosFactura (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoFactura());
		q.setResultClass(ProductoFactura.class);
		return (List<ProductoFactura>) q.executeList();
	}
	
}
