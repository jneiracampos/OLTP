package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;

public class SQLBodega 
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
    public SQLBodega (PersistenciaSuperAndes pp)
    {
        this.pp = pp;
    }
    
    /**
     * Crea y ejecuta la sentencia SQL para adicionar una BODEGA a la base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la bodega
     * @param capacidadTotal - La capacidad total de la bodega
     * @param cantidadDisponible - La cantidad disponible de la bodega
     * @param categoriaProducto - La categoria de producto de la bodega
     * @return El número de tuplas insertadas
     */
    public long adicionarBodega (PersistenceManager pm, long id, int capacidadTotal, int cantidadDisponible, long categoriaProducto, long idSucursal) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega () + " values (?, ?, ?, ?, ?)");
        q.setParameters(id, capacidadTotal, cantidadDisponible, categoriaProducto, idSucursal);
        return (long) q.executeUnique();
    }
    /**
     * Crea y ejecuta la sentencia SQL para eliminar una BODEGA de la base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la bodega
     * @return EL número de tuplas eliminadas
     */
    public long eliminarBodegaPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA BODEGA de la 
     * base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador de la bodega
     * @return El objeto BODEGA que tiene el identificador dado
     */
    public Bodega darBodegaPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() + " WHERE id = ?");
        q.setResultClass(Bodega.class);
        q.setParameters(id);
        return (Bodega) q.executeUnique();
    }

    public Bodega darBodegaPorIdCategoriaIdSucursal(PersistenceManager pm, long idCategoria, long idSucursal) {
    	Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() + " WHERE id_categoria_producto = ? AND idsucursal = ?");
        q.setResultClass(Bodega.class);
        q.setParameters(idCategoria, idSucursal);
        return (Bodega) q.executeUnique();
    }

    public void actualizarCapacidadDisponibleBodega (PersistenceManager pm, long id, int cantidad) 
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaBodega () + " SET capacidad_disponible = ? WHERE id = ?");
        q.setParameters(cantidad, id);
        q.executeUnique();
    }


    public List<Bodega> darBodegasPorIdSucursal(PersistenceManager pm, long idSucursal) {
    	Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() + " WHERE idsucursal = ?");
        q.setResultClass(Bodega.class);
        q.setParameters(idSucursal);
        return (List<Bodega>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LAS BODEGAS de la 
     * base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos BODEGA
     */
    public List<Bodega> darBodegas (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega());
        q.setResultClass(Bodega.class);
        return (List<Bodega>) q.executeList();
    }
    
}
