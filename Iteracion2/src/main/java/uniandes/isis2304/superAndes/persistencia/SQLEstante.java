package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Estante;

public class SQLEstante {
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
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	

	public long adicionarEstante (PersistenceManager pm, long idEstante, Integer capacidadTotal, Integer nivelAbastecimiento, int categoriaProducto, long id_sucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante () + " values (?, ?, ?, ?, ?)");
		q.setParameters(idEstante, capacidadTotal, nivelAbastecimiento, categoriaProducto, id_sucursal);
		return (long) q.executeUnique();
	}

	public List<Estante> darEstantePorIdSucursal(PersistenceManager pm, long idSucursal) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante() + " WHERE id_sucursal = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idSucursal);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Estante de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del Estante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEstantePorId (PersistenceManager pm, long idEstante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante() + " WHERE id = ?");
        q.setParameters(idEstante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Estante de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del Estante
	 * @return El objeto Estante que tiene el identificador dado
	 */
	public Estante darEstantePorId (PersistenceManager pm, long idEstante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante() + " WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EstanteES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Estante
	 */
	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}

	public void actualizarNivelAbastecimientoEstante (PersistenceManager pm, long id, int cantidad) 
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaBodega () + " SET nivel_abastecimiento = ? WHERE id = ?");
        q.setParameters(cantidad, id);
        q.executeUnique();
    }

}
