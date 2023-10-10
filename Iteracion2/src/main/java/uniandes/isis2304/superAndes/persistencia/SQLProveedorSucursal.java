package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProveedorSucursal;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODUCTO de AforaAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLProveedorSucursal 
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
	public SQLProveedorSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
    public long adicionarProveedorSucursal (PersistenceManager pm, long idSucursal, long idProveedor)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedorSucursal () + " values (?, ?)");
        q.setParameters(idSucursal, idProveedor);
        return (long) q.executeUnique();            
    }

    public long eliminarProveedorSucursalPorId (PersistenceManager pm, long idSucursal, long idProveedor)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedorSucursal () + " WHERE idsucursal = ? AND idproveedor = ?");
        q.setParameters(idSucursal, idProveedor);
        return (long) q.executeUnique();            
    }

    public ProveedorSucursal darProveedorSucursalPorId (PersistenceManager pm, long idSucursal, long idProveedor) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedorSucursal () + " WHERE idsucursal = ? AND idproveedor = ?");
        q.setResultClass(ProveedorSucursal.class);
        q.setParameters(idSucursal, idProveedor);
        return (ProveedorSucursal) q.executeUnique();
    }

    public List<ProveedorSucursal> darProveedorSucursales (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedorSucursal ());
        q.setResultClass(ProveedorSucursal.class);
        return (List<ProveedorSucursal>) q.executeList();
    }
}
