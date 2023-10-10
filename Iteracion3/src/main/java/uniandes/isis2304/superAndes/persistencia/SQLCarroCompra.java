package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.CarroCompra;

public class SQLCarroCompra
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
	public SQLCarroCompra (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
    /**
     * Crea y ejecuta la sentencia SQL para adicionar un CARRO_COMPRA a la base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @param id - El identificador del carro de compra
     * @param id_cliente - El identificador del cliente
     * @param isActivo - Indica si el carro de compra sigue activo o no
     * @return El número de tuplas insertadas
     */
    public long adicionarCarroCompra (PersistenceManager pm, long id, long id_cliente, String isActivo, long id_Sucursal)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarroCompra () + "(id, id_cliente, isActivo, id_Sucursal) values (?, ?, ?, ?)");
        q.setParameters(id, id_cliente, isActivo, id_Sucursal);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar una CARRO_COMPRA de la base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la categoriaProducto
     * @return EL número de tuplas eliminadas
     */
    public long eliminarCarroCompraPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarroCompra() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA CARRO_COMPRA de la 
     * base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la categoriaProducto
     * @return El objeto CARRO_COMPRA que tiene el identificador dado
     */
    public CarroCompra darCarroCompraPorId (PersistenceManager pm, long id) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarroCompra() + " WHERE id = ?");
        q.setResultClass(CarroCompra.class);
        q.setParameters(id);
        return (CarroCompra) q.executeUnique();
    }

    public CarroCompra darCarroCompraPorIdCliente (PersistenceManager pm, long id_cliente) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarroCompra() + " WHERE id_cliente = ?");
        q.setResultClass(CarroCompra.class);
        q.setParameters(id_cliente);
        return (CarroCompra) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de los CARRO_COMPRA de la 
     * base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos CARRO_COMPRA
     */
    public List<CarroCompra> darCarrosCompra (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarroCompra());
        q.setResultClass(CarroCompra.class);
        return (List<CarroCompra>) q.executeList();
    }

    public long actualizarEstadoCarroCompra (PersistenceManager pm, long id, String isActivo)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarroCompra() + " SET isActivo = ? WHERE id = ?");
        q.setParameters(isActivo, id);
        return (long) q.executeUnique();
    }

    public List<CarroCompra> darCarroCompraAbandonado (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarroCompra() + " WHERE isActivo = 'FALSE'");
        q.setResultClass(CarroCompra.class);
        return (List<CarroCompra>) q.executeList();
    }

    

}
