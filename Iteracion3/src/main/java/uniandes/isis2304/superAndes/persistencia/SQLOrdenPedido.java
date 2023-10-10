package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.OrdenPedido;

public class SQLOrdenPedido {
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
	public SQLOrdenPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
         * Crea y ejecuta la sentencia SQL para adicionar una orden a la base de datos de SuperAndes
         * @param pm - El manejador de persistencia
         * @return El número de tuplas insertadas
         */
	public long adicionarOrdenPedido (PersistenceManager pm, long idOrdenPedido, String fechaEsperadaEntrega, String fechaEntrega, int calificacionServicio, long idSucursal, long idProveedor) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrdenPedido() + " values (?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)");
		q.setParameters(idOrdenPedido, fechaEsperadaEntrega, fechaEntrega, calificacionServicio, idSucursal, idProveedor);
		return (long) q.executeUnique();
	}

	public long finalizarOrdenPedido (PersistenceManager pm, long idOrdenPedido, String fechaEntrega, int calificacionServicio) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaOrdenPedido() + " SET fecha_entrega = TO_DATE(?, 'YYYY-MM-DD'), calificacion = ? WHERE id = ?");
		q.setParameters(fechaEntrega, calificacionServicio, idOrdenPedido);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN OrdenPedido de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idOrdenPedido - El identificador del OrdenPedido
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarOrdenPedidoPorId (PersistenceManager pm, long idOrdenPedido)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido() + " WHERE id = ?");
        q.setParameters(idOrdenPedido);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN OrdenPedido de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idOrdenPedido - El identificador del OrdenPedido
	 * @return El objeto OrdenPedido que tiene el identificador dado
	 */
	public OrdenPedido darOrdenPedidoPorId (PersistenceManager pm, long idOrdenPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido() + " WHERE id = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(idOrdenPedido);
		return (OrdenPedido) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS OrdenPedidoES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos OrdenPedido
	 */
	public List<OrdenPedido> darOrdenPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}

	
}
