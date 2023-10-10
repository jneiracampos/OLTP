package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.AcuerdoVenta;

public class SQLAcuerdoVenta {

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
    public SQLAcuerdoVenta (PersistenciaSuperAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un acuerdoVenta a la base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @return El número de tuplas insertadas
     */
    public long adicionarAcuerdoVenta (PersistenceManager pm, long id, long producto, int cantidad, int precio) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAcuerdoVenta() + " values (?, ?, ?, ?)");
        q.setParameters(id, producto, cantidad, precio);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar un acuerdoVenta de la base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador del acuerdoVenta
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAcuerdoVentaPorId (PersistenceManager pm, long id, long producto)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAcuerdoVenta () + " WHERE id_acuerdo_venta = ? AND id_producto = ?");
        q.setParameters(id, producto);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UN acuerdoVenta de la 
     * base de datos de SuperAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param id - El identificador del acuerdoVenta
     * @return El objeto ACUERDOVENTA que tiene el identificador dado
     */
    public AcuerdoVenta darAcuerdoVentaPorId (PersistenceManager pm, long id, long producto)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAcuerdoVenta () + " WHERE id_acuerdo_venta = ? AND id_producto = ?");
        q.setResultClass(AcuerdoVenta.class);
        q.setParameters(id, producto);
        return (AcuerdoVenta) q.executeUnique();
    }
    
    public List<AcuerdoVenta> darAcuerdoVentaPorIdOrdenPedido(PersistenceManager pm, long idOrdenPedido)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAcuerdoVenta () + " WHERE id_orden_pedido = ?");
        q.setResultClass(AcuerdoVenta.class);
        q.setParameters(idOrdenPedido);
        return (List<AcuerdoVenta>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ACUERDOVENTA de la 
     * base de datos de SuperAndes
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos ACUERDOVENTA
     */
    public List<AcuerdoVenta> darAcuerdosVenta (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAcuerdoVenta ());
        q.setResultClass(AcuerdoVenta.class);
        return (List<AcuerdoVenta>) q.executeList();
    }

}
