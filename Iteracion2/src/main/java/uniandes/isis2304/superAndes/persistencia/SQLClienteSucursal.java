package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ClienteSucursal;

public class SQLClienteSucursal {
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
	public SQLClienteSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un clienteSucursal a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador del clienteSucursal
	 * @param idCliente - El idCliente del clienteSucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarClienteSucursal (PersistenceManager pm, long idSucursal, long idCliente) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaClienteSucursal() + " values (?, ?)");
        q.setParameters(idSucursal, idCliente);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN clienteSucursal de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador del clienteSucursal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClienteSucursalPorId (PersistenceManager pm, long idSucursal, long idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaClienteSucursal() + " WHERE id = ?");
        q.setParameters(idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN clienteSucursal de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSucursal - El identificador del clienteSucursal
	 * @return El objeto clienteSucursal que tiene el identificador dado
	 */
	public ClienteSucursal darClienteSucursalPorId (PersistenceManager pm, long idCliente, long idSucursal)
	
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaClienteSucursal() + " WHERE idcliente = ? and idsucursal = ?");
		q.setResultClass(ClienteSucursal.class);
		q.setParameters(idCliente, idSucursal);
		return (ClienteSucursal) q.executeUnique();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS clienteSucursalES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos clienteSucursal
	 */
	public List<ClienteSucursal> darClientesSucursal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaClienteSucursal() );
		q.setResultClass(ClienteSucursal.class);
		return (List<ClienteSucursal>) q.executeList();
	}
}
