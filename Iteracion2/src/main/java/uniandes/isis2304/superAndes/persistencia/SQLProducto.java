package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Producto;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODUCTO de AforaAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLProducto 
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
	public SQLProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarProducto (PersistenceManager pm, long idProducto, String nombre, String marca, String presentacion, long idCategoriaProducto, int precioUnitario, int cantidadPresentacion, int volumenEmpaque, int pesoEmpaque, int precioPorUnidad) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idProducto, nombre, marca, presentacion, idCategoriaProducto, precioUnitario, cantidadPresentacion, volumenEmpaque, pesoEmpaque, precioPorUnidad);
		return (long) q.executeUnique();
	}

	public List<Producto> darProductosPorMarca(PersistenceManager pm, String marca) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto() + " WHERE marca = ?");
		q.setParameters(marca);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTO de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBebida - El identificador de la bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoPorId (PersistenceManager pm, long idProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE idProducto = ?");
		q.setParameters(idProducto);
		return (long) q.executeUnique();            
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA BEBIDA de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBebida - El identificador de la bebida
	 * @return El objeto BEBIDA que tiene el identificador dado
	 */
	public Producto darProductoPorId (PersistenceManager pm, long idProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE id = ?");
		q.setResultClass(Producto.class);
		q.setParameters(idProducto);
		return (Producto) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS BEBIDAS de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BEBIDA
	 */
	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto ());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}

}
