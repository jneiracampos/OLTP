package uniandes.isis2304.superAndes.persistencia;


import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

public class SQLPromocion {
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
	public SQLPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PROMOCION a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promocion
	 * @param nombreProducto - El nombreProducto de la promocion
	 * @param fechaFiString - La fechaFiString de la promocion
	 * @param tipoPromocion - El tipoPromocion de la promocion (ALTO, MEDIO, BAJO)
	 * @param promocion - El número de promocion de la promocion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, long idProducto, String fechaFiString, String tipoPromocion, String promocion, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocion () + " values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idPromocion, idProducto, fechaFiString, tipoPromocion, promocion, idSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PROMOCION de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promocion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromocionPorId (PersistenceManager pm, long idPromocion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion() + " WHERE id = ?");
        q.setParameters(idPromocion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PROMOCION de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promocion
	 * @return El objeto PROMOCION que tiene el identificador dado
	 */
	public Promocion darPromocionPorId (PersistenceManager pm, long idPromocion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion() + " WHERE id = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(idPromocion);
		return (Promocion) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Promocion de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROMOCION
	 */
	public List<Promocion> darPromociones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
}
