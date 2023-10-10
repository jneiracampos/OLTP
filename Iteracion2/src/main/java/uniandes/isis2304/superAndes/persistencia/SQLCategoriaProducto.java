package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.CategoriaProducto;

public class SQLCategoriaProducto
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
	public SQLCategoriaProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
    /**
     * Crea y ejecuta la sentencia SQL para adicionar una CATEGORIAPRODUCTO a la base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la categoriaProducto
     * @param tipo - El tipo de la categoriaProducto
     * @return El número de tuplas insertadas
     */
    public long adicionarCategoriaProducto (PersistenceManager pm, long id, String tipo)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCategoriaProducto() + " values (?, ?)");
        q.setParameters(id, tipo);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar una CATEGORIAPRODUCTO de la base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la categoriaProducto
     * @return EL número de tuplas eliminadas
     */
    public long eliminarCategoriaProductoPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoriaProducto () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA CATEGORIAPRODUCTO de la 
     * base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la categoriaProducto
     * @return El objeto CATEGORIAPRODUCTO que tiene el identificador dado
     */
    public CategoriaProducto darCategoriaProductoPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoriaProducto () + " WHERE id = ?");
        q.setResultClass(CategoriaProducto.class);
        q.setParameters(id);
        return (CategoriaProducto) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LAS CATEGORIAPRODUCTO de la 
     * base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos CATEGORIAPRODUCTO
     */
    public List<CategoriaProducto> darCategoriasProducto (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoriaProducto ());
        q.setResultClass(CategoriaProducto.class);
        return (List<CategoriaProducto>) q.executeList();
    }

}
