

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLUtil
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
	public SQLUtil (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
		Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega ());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal ());
		Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto ());
		Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor ());
		Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente ());
		Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion ());
		Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante ());
		Query qProductoBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoBodega ());
		Query qProductoSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal ());
		Query qProductoFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoFactura ());
		Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura ());
		Query qAcuerdoVenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAcuerdoVenta ());
		Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario ());
		Query rol = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol ());
		Query qProveedorSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedorSucursal ());
		Query qUsuarioRol = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioRol ());
		Query qCategoriaProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoriaProducto ());
		Query qCarroCompra = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarroCompra ());
		Query qCategoriaProductoSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoriaProductoSucursal ());
		Query qOrdenPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido ());
		
		long bodegaEliminados = (long) qBodega.executeUnique ();
		long sucursalEliminados = (long) qSucursal.executeUnique ();
		long productoEliminados = (long) qProducto.executeUnique ();
		long proveedorEliminados = (long) qProveedor.executeUnique ();
		long clienteEliminados = (long) qCliente.executeUnique ();
		long promocionEliminados = (long) qPromocion.executeUnique ();
		long estanteEliminados = (long) qEstante.executeUnique ();
		long productoBodegaEliminados = (long) qProductoBodega.executeUnique ();
		long productoSucursalEliminados = (long) qProductoSucursal.executeUnique ();
		long productoFacturaEliminados = (long) qProductoFactura.executeUnique ();
		long facturaEliminados = (long) qFactura.executeUnique ();
		long acuerdoVentaEliminados = (long) qAcuerdoVenta.executeUnique ();
		long usuarioEliminados = (long) qUsuario.executeUnique ();
		long rolEliminados = (long) rol.executeUnique ();
		long proveedorSucursalEliminados = (long) qProveedorSucursal.executeUnique ();
		long usuarioRolEliminados = (long) qUsuarioRol.executeUnique ();
		long categoriaProductoEliminados = (long) qCategoriaProducto.executeUnique ();
		long carroCompraEliminados = (long) qCarroCompra.executeUnique ();
		long categoriaProductoSucursalEliminados = (long) qCategoriaProductoSucursal.executeUnique ();
		long ordenPedidoEliminados = (long) qOrdenPedido.executeUnique ();

		return new long[] {bodegaEliminados, sucursalEliminados, productoEliminados, proveedorEliminados, clienteEliminados, promocionEliminados, estanteEliminados, productoBodegaEliminados, productoSucursalEliminados, productoFacturaEliminados, facturaEliminados, acuerdoVentaEliminados, usuarioEliminados, rolEliminados, proveedorSucursalEliminados, usuarioRolEliminados, categoriaProductoEliminados, carroCompraEliminados, categoriaProductoSucursalEliminados, ordenPedidoEliminados};

	}

}
