package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Reabastecer;

public class SQLReabastecer {
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
	public SQLReabastecer (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un REABASTECER a la base de datos de SuperAndes
	 * @return El número de tuplas insertadas
	 */
	public long adicionarReabastecer (PersistenceManager pm, long idBodega, long idEstante, int cantidad)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReabastecer () + "(idBodega, idEstante, cantidad) values (?, ?, ?)");
		q.setParameters(idBodega, idEstante, cantidad);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar REABASTECER de la base de datos de SuperAndes, por su identificador
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReabastecerPorId (PersistenceManager pm, long idBodega, long idEstante)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReabastecer () + " WHERE idBodega = ? AND idEstante = ?");
		q.setParameters(idBodega, idEstante);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REABASTECER de la 
	 * base de datos de SuperAndes, por su identificador
	 * @return El objeto REABASTECER que tiene el identificador dado
	 */
	public Reabastecer darReabastecerPorId (PersistenceManager pm, long idBodega, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReabastecer () + " WHERE idBodega = ? AND idEstante = ?");
		q.setResultClass(Reabastecer.class);
		q.setParameters(idBodega, idEstante);
		return (Reabastecer) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REABASTECER de la 
	 * base de datos de SuperAndes
	 * @return Una lista de objetos REABASTECER
	 */
	public List<Reabastecer> darReabastecers (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReabastecer ());
		q.setResultClass(Reabastecer.class);
		return (List<Reabastecer>) q.executeList();
	}

}