
package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.AcuerdoVenta;
import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.CategoriaProducto;
import uniandes.isis2304.superAndes.negocio.CategoriaProductoSucursal;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.ClienteSucursal;
import uniandes.isis2304.superAndes.negocio.Empleado;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.OrdenPedido;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.ProductoBodega;
import uniandes.isis2304.superAndes.negocio.ProductoFactura;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.ProveedorSucursal;
import uniandes.isis2304.superAndes.negocio.Reabastecer;
import uniandes.isis2304.superAndes.negocio.Rol;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.Usuario;
import uniandes.isis2304.superAndes.negocio.UsuarioRol;


public class PersistenciaSuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */

	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipocategoriaProducto, categoriaProducto, empleado, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;

	private SQLUtil sqlUtil;

	private SQLUsuario sqlUsuario;

	private SQLRol sqlRol;

	private SQLSucursal sqlSucursal;

	private SQLProducto sqlProducto;

	private SQLCategoriaProducto sqlCategoriaProducto;
	
	private SQLProveedor sqlProveedor;

	private SQLEmpleado sqlEmpleado;

	private SQLCliente sqlCliente;

	private SQLClienteSucursal sqlClienteSucursal;

	private SQLProveedorSucursal sqlProveedorSucursal;

	private SQLProductoSucursal sqlProductoSucursal;

	private SQLCategoriaProductoSucursal sqlCategoriaProductoSucursal;

	private SQLOrdenPedido sqlOrdenPedido;

	private SQLAcuerdoVenta sqlAcuerdoVenta;

	private SQLPromocion sqlPromocion;

	private SQLFactura sqlFactura;

	private SQLProductoFactura sqlProductoFactura;

	private SQLBodega sqlBodega;

	private SQLEstante sqlEstante;

	private SQLProductoBodega sqlProductoBodega;

	private SQLReabastecer sqlReabastecer;

	private SQLUsuarioRol sqlUsuarioRol;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SuperAndes_sequence");
		tablas.add ("A_SUCURSAL");
		tablas.add ("A_PRODUCTO");
		tablas.add ("A_CATEGORIA_PRODUCTO");
		tablas.add ("A_PROVEEDOR");
		tablas.add ("A_EMPLEADO");
		tablas.add ("A_CLIENTE");
        tablas.add ("A_CLIENTE_SUCURSAL");
        tablas.add ("A_PROVEEDOR_SUCURSAL");
        tablas.add ("A_PRODUCTO_SUCURSAL");
        tablas.add ("A_CATEGORIA_PRODUCTO_SUCURSAL");
        tablas.add ("A_ORDEN_PEDIDO");
        tablas.add ("A_ACUERDO_VENTA");
        tablas.add ("A_PROMOCION");
        tablas.add ("A_FACTURA");
        tablas.add ("A_PRODUCTO_FACTURA");
        tablas.add ("A_BODEGA");
        tablas.add ("A_ESTANTE");
        tablas.add ("A_PRODUCTO_BODEGA");
        tablas.add ("A_REABASTECER");
		tablas.add ("A_USUARIO");
		tablas.add ("A_ROL");
		tablas.add ("A_USUARIO_ROL");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlCategoriaProducto = new SQLCategoriaProducto(this);
		sqlEmpleado = new SQLEmpleado(this);
		sqlSucursal = new SQLSucursal(this);
		sqlCategoriaProducto = new SQLCategoriaProducto(this);
		sqlClienteSucursal = new SQLClienteSucursal(this);
		sqlCategoriaProductoSucursal = new SQLCategoriaProductoSucursal(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlEstante = new SQLEstante(this);
		sqlReabastecer = new SQLReabastecer(this);
		sqlProductoBodega = new SQLProductoBodega(this);
		sqlBodega = new SQLBodega(this);
		sqlProductoFactura = new SQLProductoFactura(this);
		sqlPromocion = new SQLPromocion(this);
		sqlAcuerdoVenta = new SQLAcuerdoVenta(this);
		sqlProductoSucursal = new SQLProductoSucursal(this);
		sqlProveedorSucursal = new SQLProveedorSucursal(this);
		sqlCliente = new SQLCliente(this);
		sqlUtil = new SQLUtil(this);
		sqlProveedor = new SQLProveedor(this);
		sqlUsuario = new SQLUsuario(this);
		sqlRol = new SQLRol(this);
		sqlUsuarioRol = new SQLUsuarioRol(this);
		sqlProducto = new SQLProducto(this);
		sqlFactura = new SQLFactura(this);
		sqlProductoFactura = new SQLProductoFactura(this);
		sqlPromocion = new SQLPromocion(this);
	}

	/**
	 * @return La cadena de caracteres con el tipo del secuenciador de superAndes
	 */
	public String darSeqSuperAndes ()
	{
		return tablas.get (0);
	}

	public String darTablaSucursal ()
	{
		return tablas.get (1);
	}

	public String darTablaProducto ()
	{
		return tablas.get (2);
	}

	public String darTablaCategoriaProducto ()
	{
		return tablas.get (3);
	}

	public String darTablaProveedor ()
	{
		return tablas.get (4);
	}

	public String darTablaEmpleado ()
	{
		return tablas.get (5);
	}

	public String darTablaCliente ()
	{
		return tablas.get (6);
	}
    
    public String darTablaClienteSucursal ()
    {
        return tablas.get (7);
    }
    
	public String darTablaProveedorSucursal ()
    {
        return tablas.get (8);
    }
    
	public String darTablaProductoSucursal ()
    {
        return tablas.get (9);
    }
    
	public String darTablaCategoriaProductoSucursal ()
    {
        return tablas.get (10);
    }
    
	public String darTablaOrdenPedido ()
    {
        return tablas.get (11);
    }
    
	public String darTablaAcuerdoVenta ()
    {
        return tablas.get (12);
    }
    
	public String darTablaPromocion ()
    {
        return tablas.get (13);
    }
    
	public String darTablaFactura ()
    {
        return tablas.get (14);
    }
    
	public String darTablaProductoFactura ()
    {
        return tablas.get (15);
    }
    
	public String darTablaBodega ()
    {
        return tablas.get (16);
    }    
    
	public String darTablaEstante ()
    {
        return tablas.get (17);
    }    
    
	public String darTablaProductoBodega ()
    {
        return tablas.get (18);
    }
    
	public String darTablaReabastecer ()
    {
        return tablas.get (19);
    }

	public String darTablaUsuario ()
	{
		return tablas.get (20);
	}

	public String darTablaRol ()
	{
		return tablas.get (21);
	}

	public String darTablaUsuarioRol ()
	{
		return tablas.get (22);
	}
	
	/**
	 * Transacción para el generador de secuencia de superAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de superAndes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los USUARIOS
	 *****************************************************************/
	
	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Producto
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto Producto adicionado. null si ocurre alguna Excepción
	 */
	public Usuario adicionarUsuario(String nombre, String correo, String palabraClave)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idUsuario = nextval ();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, idUsuario, nombre, correo, palabraClave);
			tx.commit();
			
			log.trace ("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new Usuario (idUsuario, nombre, correo, palabraClave);
		}
		catch (Exception e)
		{
			log.error ("Exception adicionarUsuario: " + e.getMessage ());
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Usuario, dado el identificador del usuario
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarUsuarioPorId (long idUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlUsuario.eliminarUsuarioPorId(pm, idUsuario);
			tx.commit();
			
			log.trace ("Eliminación de usuario: " + idUsuario + ": " + resp + " tuplas eliminadas");
			
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception eliminarUsuarioPorId: " + e.getMessage ());
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Usuario darUsuarioPorId (long idUsuario)
	{
		return sqlUsuario.darUsuarioPorId (pmf.getPersistenceManager(), idUsuario);
	}

	public List<Usuario> darUsuarios ()
	{
		return sqlUsuario.darUsuarios (pmf.getPersistenceManager());
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los ROL
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Producto
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto Producto adicionado. null si ocurre alguna Excepción
	 */
	public Rol adicionarRol(String nombre, String descripcion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idRol = nextval ();
			long tuplasInsertadas = sqlRol.adicionarRol(pm, idRol, nombre, descripcion);
			tx.commit();
			
			log.trace ("Inserción de rol: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new Rol (idRol, nombre, descripcion);
		}
		catch (Exception e)
		{
			log.error ("Exception adicionarRol: " + e.getMessage ());
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Rol, dado el identificador del rol
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarRolPorId (long idRol)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlRol.eliminarRolPorId(pm, idRol);
			tx.commit();
			
			log.trace ("Eliminación de rol: " + idRol + ": " + resp + " tuplas eliminadas");
			
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception eliminarRolPorId: " + e.getMessage ());
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Rol darRolPorId (long idRol)
	{
		return sqlRol.darRolPorId (pmf.getPersistenceManager(), idRol);
	}

	public List<Rol> darRoles ()
	{
		return sqlRol.darRoles (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los USUARIO_ROL
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla USUARIO_ROL
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO_ROL adicionado. null si ocurre alguna Excepción
	 */
	public UsuarioRol adicionarUsuarioRol(long idUsuario, long idRol, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlUsuarioRol.adicionarUsuarioRol(pm, idUsuario, idRol, idSucursal);
			tx.commit();
			
			log.trace ("Inserción de usuarioRol: " + idUsuario + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new UsuarioRol (idUsuario, idRol, idSucursal);
		}
		catch (Exception e)
		{
			log.error ("Exception adicionarUsuarioRol: " + e.getMessage ());
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla USUARIO_ROL, dado el identificador 
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarUsuarioRolPorId (long idUsuario, long idRol)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlUsuarioRol.eliminarUsuarioRolPorId(pm, idUsuario, idRol);
			tx.commit();
			
			log.trace ("Eliminación de usuarioRol: " + idUsuario + ": " + resp + " tuplas eliminadas");
			
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception eliminarUsuarioRolPorId: " + e.getMessage ());
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public UsuarioRol darUsuarioRolPorId (long idUsuario, long idRol)
	{
		return sqlUsuarioRol.darUsuarioRolPorId (pmf.getPersistenceManager(), idUsuario, idRol);
	}

	public List<UsuarioRol> darUsuarioRol ()
	{
		return sqlUsuarioRol.darUsuarioRol (pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos para manejar los PRODUCTOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Producto
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El tipo de la categoriaProducto
	 * @param idTipocategoriaProducto - El identificador del tipo de categoriaProducto (Debe existir en la tabla TipocategoriaProducto)
	 * @param  - El grado de alcohol de la categoriaProducto (mayor que 0)
	 * @return El objeto Producto adicionado. null si ocurre alguna Excepción
	 */
	public Producto adicionarProducto(String nombre, String marca, String presentacion, long idCategoriaProducto, int precioUnitario, int cantidadPresentacion, int volumenEmpaque, int pesoEmpaque, int precioPorUnidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idProducto = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto(pm, idProducto, nombre, marca, presentacion, idCategoriaProducto, precioUnitario, cantidadPresentacion, volumenEmpaque, pesoEmpaque, precioPorUnidad);
            tx.commit();
            
			log.trace ("Inserción de producto: " + idProducto + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Producto (idProducto, nombre, marca, presentacion, idCategoriaProducto, precioUnitario, cantidadPresentacion, volumenEmpaque, pesoEmpaque, precioPorUnidad);
        }
        catch (Exception e)
        {
       	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Producto, dado el identificador de la categoriaProducto
	 * Adiciona entradas al log de la aplicación
	 * @param idcategoriaProducto - El identificador de la categoriaProducto
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProductoPorId (long idcategoriaProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorId (pm, idcategoriaProducto);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Producto daProductoPorId (long idcategoriaProducto)
	{
		return sqlProducto.darProductoPorId (pmf.getPersistenceManager(), idcategoriaProducto);
	}

	public Bodega darBodegaPorIdCategoriaIdSucursal (long idCategoria, long idSucursal)
	{
		return sqlBodega.darBodegaPorIdCategoriaIdSucursal (pmf.getPersistenceManager(), idCategoria, idSucursal);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Producto
	 * @return La lista de objetos Producto, construidos con base en las tuplas de la tabla categoriaProducto
	 */
	public List<Producto> darProductos ()
	{
		return sqlProducto.darProductos (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar las FACTURAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla FACTURA
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto FACTURA adicionado. null si ocurre alguna Excepción
	 */
	public Factura adicionarFactura (long idCliente, long idSucursal, int subTotal, int totalPagar, int puntos, String fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idFactura = nextval ();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, idFactura, idCliente, idSucursal, subTotal, totalPagar, puntos, fecha);
			tx.commit();
			
			log.trace ("Inserción factura: " + idFactura + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Factura (idFactura, idCliente, idSucursal, subTotal, totalPagar, puntos, fecha);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla FACTURA, dado el identificador de la factura
	 * Adiciona entradas al log de la aplicación
	 * @param idFactura - El identificador de la factura
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarFacturaPorId (long idFactura)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFactura.eliminarFacturaPorId (pm, idFactura);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla FACTURA
	 * @return La lista de objetos FACTURA, construidos con base en las tuplas de la tabla FACTURA
	 */
	public List<Factura> darFacturas ()
	{
		return sqlFactura.darFacturas (pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla FACTURA que tienen el identificador dado
	 * @param idFactura - El identificador de la factura
	 * @return La lista de objetos FACTURA, construidos con base en las tuplas de la tabla FACTURA
	 */
	public Factura darFacturaPorId (long idFactura)
	{
		return sqlFactura.darFacturaPorId (pmf.getPersistenceManager(), idFactura);
	}


	/* ****************************************************************
	 * 			Métodos para manejar los PROVEEDORES
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla PROVEEDORES
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto PROVEEDORES adicionado. null si ocurre alguna Excepción
	 */
	public Proveedor adicionarProveedor(String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idBebedor = nextval ();
            long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, idBebedor, tipo);
            tx.commit();

            log.trace ("Inserción de bebedor: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedor (idBebedor, tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR, dado el identificador del bebedor
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProveedorPorId (long idBebedor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedor.eliminarProveedorPorId (pm, idBebedor);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public Proveedor darProveedorPorId (long idBebedor) 
	{
		return (Proveedor) sqlProveedor.darProveedorPorId (pmf.getPersistenceManager(), idBebedor);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR
	 * @return La lista de objetos BEBEDOR, construidos con base en las tuplas de la tabla BEBEDOR
	 */
	public List<Proveedor> darBebedores ()
	{
		return sqlProveedor.darProveedores (pmf.getPersistenceManager());
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar las SUCURSALES
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla SUCURSAL
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto Sucursal adicionado. null si ocurre alguna Excepción
	 */
	public Sucursal adicionarSucursal(String nombre, String ciudad, String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idSucursal = nextval ();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, idSucursal, nombre, ciudad, direccion);
            tx.commit();

            log.trace ("Inserción de Sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Sucursal(idSucursal, nombre, ciudad, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla EMPLEADO, dado el tipo del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param nombreEmpleado - El tipo del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarSucursalPorNombre (String nombreEmpleado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalPorNombre(pm, nombreEmpleado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla EMPLEADO, dado el identificador del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param idEmpleado - El identificador del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarSucursalPorId (long idEmpleado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalPorId (pm, idEmpleado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO
	 * @return La lista de objetos EMPLEADO, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Sucursal> darSucursales ()
	{
		return sqlSucursal.darSucursales (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO que tienen el tipo dado
	 * @param nombreEmpleado - El tipo del empleado
	 * @return La lista de objetos EMPLEADO, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Sucursal> darSucursalesPorNombre (String nombreEmpleado)
	{
		return sqlSucursal.darSucursalesPorNombre (pmf.getPersistenceManager(), nombreEmpleado);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO que tienen el identificador dado
	 * @param idEmpleado - El identificador del empleado
	 * @return El objeto EMPLEADO, construido con base en la tuplas de la tabla EMPLEADO, que tiene el identificador dado
	 */
	public Sucursal darSucursalPorId (long idEmpleado)
	{
		return sqlSucursal.darSucursalPorId (pmf.getPersistenceManager(), idEmpleado);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación CATEGORIAPRODUCTO
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla categoriaProducto
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El tipo de la categoriaProducto
	 * @param idTipocategoriaProducto - El identificador del tipo de categoriaProducto (Debe existir en la tabla TipocategoriaProducto)
	 * @return El objeto categoriaProducto adicionado. null si ocurre alguna Excepción
	 */
	public CategoriaProducto adicionarCategoriaProducto(String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idcategoriaProducto = nextval ();
            long tuplasInsertadas = sqlCategoriaProducto.adicionarCategoriaProducto(pm, idcategoriaProducto, tipo);
            tx.commit();
            
            log.trace ("Inserción categoriaProducto: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            return new CategoriaProducto (idcategoriaProducto,tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla categoriaProducto, dado el identificador de la categoriaProducto
	 * Adiciona entradas al log de la aplicación
	 * @param idcategoriaProducto - El identificador de la categoriaProducto
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCategoriaProductoPorId (long idcategoriaProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCategoriaProducto.eliminarCategoriaProductoPorId (pm, idcategoriaProducto);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public CategoriaProducto darCategoriaProductoPorId (long idcategoriaProducto)
	{
		return sqlCategoriaProducto.darCategoriaProductoPorId (pmf.getPersistenceManager(), idcategoriaProducto);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación EMPLEADO
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla EMPLEADO
	 * Adiciona entradas al log de la aplicación
	 * @param idSucursal2 - El fechaEsperadaEntrega del empleado
	 * @param idSucursal2 - La fechaEsperadaEntrega del empleado
	 * @param correo - El correo del empleado (ALTO, MEDIO, BAJO)
	 * @param idSucursal - El número de idSucursal del empleado en la fechaEsperadaEntrega (Mayor que 0)
	 * @return El objeto Empleado adicionado. null si ocurre alguna Excepción
	 */

	public Empleado adicionarEmpleado(String fechaEsperadaEntrega, String correo, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEmpleado = nextval ();
            long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pm, idEmpleado, fechaEsperadaEntrega, correo, idSucursal);
            tx.commit();

            log.trace ("Inserción de Empleado: " + fechaEsperadaEntrega + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Empleado (idEmpleado, fechaEsperadaEntrega, correo, idSucursal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla EMPLEADO, dado el fechaEsperadaEntrega del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param nombreEmpleado - El fechaEsperadaEntrega del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorNombre (String nombreEmpleado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpleado.eliminarEmpleadoPorNombre(pm, nombreEmpleado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla EMPLEADO, dado el identificador del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param idEmpleado - El identificador del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorId (long idEmpleado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpleado.eliminarEmpleadoPorId (pm, idEmpleado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO
	 * @return La lista de objetos EMPLEADO, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Empleado> darEmpleados ()
	{
		return sqlEmpleado.darEmpleados (pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el nombre dado
	 * @param nombreEmpleado - El nombre del bebedor
	 * @return La lista de objetos BEBEDOR, construidos con base en las tuplas de la tabla BEBEDOR
	 */
	public List<Empleado> darEmpleadosPorNombre (String nombreEmpleado) 
	{
		return sqlEmpleado.darEmpleadosPorNombre (pmf.getPersistenceManager(), nombreEmpleado);
	}
 
 
	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO que tienen el identificador dado
	 * @param idEmpleado - El identificador del empleado
	 * @return El objeto EMPLEADO, construido con base en la tuplas de la tabla EMPLEADO, que tiene el identificador dado
	 */
	public Empleado darEmpleadoPorId (long idEmpleado)
	{
		return sqlEmpleado.darEmpleadoPorId (pmf.getPersistenceManager(), idEmpleado);
	}

	/**
	 * darEmpleadoPorNombre
	 * @param nombreEmpleado
	 * @return
	 */
	public List<Empleado> darEmpleadoPorNombre (String nombreEmpleado)
	{
		return sqlEmpleado.darEmpleadoPorNombre (pmf.getPersistenceManager(), nombreEmpleado);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación CLIENTE
	 *****************************************************************/

	/**
	 * @param idCliente
	 * @param nombre
	 * @param correo
	 * @param tipoCliente
	 * @return
	 */

	public Cliente adicionarCliente (String nombre, String correo, String tipoCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idCliente = nextval ();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm, idCliente, nombre, correo, tipoCliente);
			tx.commit();

			log.trace ("Inserción de Cliente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Cliente (idCliente, nombre, correo, tipoCliente);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Elimina un cliente por su identificador
	 * @param idCliente
	 * @return
	 */
	public long eliminarClientePorId (long idCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCliente.eliminarClientePorId(pm, idCliente);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Dar cliente por id
	 * @param idCliente
	 * @return
	 */
	public Cliente darClientePorId (long idCliente)
	{
		return sqlCliente.darClientePorId(pmf.getPersistenceManager(), idCliente);
	}

	/**
	 * Dar clientes
	 * @return
	 */
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}

	 /* ****************************************************************
	 * 			Métodos para manejar la relación CLIENTESUCURSAL
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ClienteSucursal
	 * Adiciona entradas al log de la aplicación
	 */
	public ClienteSucursal adicionarClienteSucursal(long idSucursal, long idCliente) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlClienteSucursal.adicionarClienteSucursal(pm,idSucursal,idCliente);
            tx.commit();

            log.trace ("Inserción deClienteSucursal: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ClienteSucursal (idSucursal,idCliente);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ClienteSucursal, dado el identificador del ClienteSucursal
	 * Adiciona entradas al log de la aplicación
	 * @param idClienteSucursal - El identificador del ClienteSucursal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarClienteSucursalPorId (long idSucursal, long idCliente) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlClienteSucursal.eliminarClienteSucursalPorId (pm, idSucursal, idCliente);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ClienteSucursal
	 * @return La lista de objetos ClienteSucursal, construidos con base en las tuplas de la tabla ClienteSucursal
	 */
	public List<ClienteSucursal> darClientesSucursal ()
	{
		return sqlClienteSucursal.darClientesSucursal (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla ClienteSucursal que tienen el identificador dado
	 * @param idClienteSucursal - El identificador del ClienteSucursal
	 * @return El objeto ClienteSucursal, construido con base en la tuplas de la tabla ClienteSucursal, que tiene el identificador dado
	 */
	public ClienteSucursal darClienteSucursalPorId (long idCliente, long idSucursal)
	{
		return sqlClienteSucursal.darClienteSucursalPorId (pmf.getPersistenceManager(), idCliente, idSucursal);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación BODEGA
	 *****************************************************************/

		/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BODEGA
	 * Adiciona entradas al log de la aplicación
	 */

	 public Bodega adicionarBodega(int capacidadTotal, int capacidadDisponible, long categoriaProducto, long idSucursal)
	 {
		 PersistenceManager pm = pmf.getPersistenceManager();
		 Transaction tx=pm.currentTransaction();
		 try
		 {
			 tx.begin();
			long idBodega = nextval ();
			 long tuplasInsertadas = sqlBodega.adicionarBodega(pm, idBodega, capacidadTotal, capacidadDisponible, categoriaProducto, idSucursal);
			 tx.commit();

			 log.trace ("Inserción de Bodega: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertadas");

			 return new Bodega (idBodega, capacidadTotal, capacidadDisponible, categoriaProducto, idSucursal);
		 }
		 catch (Exception e)
		 {
//			 e.printStackTrace();
			 log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			 return null;
		 }
		 finally
		 {
			 if (tx.isActive())
			 {
				 tx.rollback();
			 }
			 pm.close();
		 }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BODEGA, dado el identificador de la BODEGA
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El identificador de la BODEGA
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBodegaPorId (long idBodega)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBodega.eliminarBodegaPorId (pm, idBodega);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public void actualizarCapacidadDisponibleBodega(long id, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		sqlBodega.actualizarCapacidadDisponibleBodega (pm, id, cantidad);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BODEGA que tienen el identificador dado
	 * @param idBodega - El identificador de la BODEGA
	 * @return El objeto BODEGA, construido con base en la tuplas de la tabla BODEGA, que tiene el identificador dado
	 */
	public Bodega darBodegaPorId (long idBodega)
	{
		return sqlBodega.darBodegaPorId (pmf.getPersistenceManager(), idBodega);
	}

	public List<Bodega> darBodegasPorSucursal (long idSucursal)
	{
		return sqlBodega.darBodegasPorIdSucursal (pmf.getPersistenceManager(), idSucursal);
	}

	public List<Estante> darEstantePorIdSucursal (long idSucursal)
	{
		return sqlEstante.darEstantePorIdSucursal (pmf.getPersistenceManager(), idSucursal);
	}

	public List<Producto> darProductosPorMarca(String marca)
	{
		return sqlProducto.darProductosPorMarca (pmf.getPersistenceManager(), marca);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación PRODUCTOBODEGA
	 *****************************************************************/

	/**
	 * @param idProducto
	 * @param idBodega
	 * @return
	 */
	public ProductoBodega adicionarProductoBodega(long idBodega, long idProducto, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoBodega.adicionarProductoBodega(pm, idBodega, idProducto, cantidad);
			tx.commit();

			log.trace ("Inserción de ProductoBodega: " + idProducto + ": " + tuplasInsertadas + " tuplas insertadas");

			return new ProductoBodega (idBodega, idProducto, cantidad);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarProductoBodegaPorId (long idProducto, long idBodega)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductoBodega.eliminarProductoBodegaPorId (pm, idProducto, idBodega);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public ProductoBodega darProductoBodegaPorId (long idProducto, long idBodega)
	{
		return sqlProductoBodega.darProductoBodegaPorId (pmf.getPersistenceManager(), idProducto, idBodega);
	}

	public long actualizarCantidadProductoBodega(long idProducto, long idBodega, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductoBodega.actualizarCantidadProductoBodega (pm, idProducto, idBodega, cantidad);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación PROVEEDORSUCURSAL
	 *****************************************************************/

	/**
	 * @param idSucursal
	 * @param idProveedor
	 * @return
	 */
	public ProveedorSucursal adicionarProveedorSucursal(long idSucursal, long idProveedor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedorSucursal.adicionarProveedorSucursal(pm,idSucursal,idProveedor);
			tx.commit();

			log.trace ("Inserción deProveedorSucursal: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertadas");

			return new ProveedorSucursal (idSucursal,idProveedor);
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * 
	 * @param idSucursal
	 * @param idProveedor
	 * @return
	 */
	public long eliminarProveedorSucursalPorId (long idSucursal, long idProveedor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedorSucursal.eliminarProveedorSucursalPorId (pm, idSucursal, idProveedor);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public ProveedorSucursal darProveedorSucursalPorId (long idSucursal, long idProveedor)
	{
		return sqlProveedorSucursal.darProveedorSucursalPorId (pmf.getPersistenceManager(), idSucursal, idProveedor);
	}

	public List<ProveedorSucursal> darProveedoresSucursal ()
	{
		return sqlProveedorSucursal.darProveedorSucursales (pmf.getPersistenceManager());
	}

	 /* ****************************************************************
	 * 			Métodos para manejar la relación PRODUCTOSUCURSAL
	 *****************************************************************/
	
	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla PRODUCTOSUCURSAL
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto ProductoSucursal adicionado. null si ocurre alguna Excepción
	 */
	public ProductoSucursal adicionarProductoSucursal(long idSucursal, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductoSucursal.adicionarProductoSucursal(pm,idSucursal,idProducto);
            tx.commit();

            log.trace ("Inserción de ProductoSucursal: " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoSucursal (idSucursal,idProducto);
        }
        catch (Exception e)
        {
      		e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PRODUCTOSUCURSAL, dado el identificador del ProductoSucursal
	 * Adiciona entradas al log de la aplicación
	 * @param idProductoSucursal - El identificador del ProductoSucursal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProductoSucursalPorId (long idSucursal, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoSucursal.eliminarProductoSucursalPorId (pm,idSucursal, idProducto);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PRODUCTOSUCURSAL
	 * @return La lista de objetos PRODUCTOSUCURSAL, construidos con base en las tuplas de la tabla PRODUCTOSUCURSAL
	 */
	public List<ProductoSucursal> darProductoSucursales ()
	{
		return sqlProductoSucursal.darProductoSucursales (pmf.getPersistenceManager());
	}
 
	
	/**
	 * Método que consulta todas las tuplas en la tabla PRODUCTOSUCURSAL que tienen el identificador dado
	 * @return El objeto PRODUCTOSUCURSAL, construido con base en la tuplas de la tabla PRODUCTOSUCURSAL, que tiene el identificador dado
	 */
	public ProductoSucursal darProductoSucursalPorId (long idSucursal,long idProducto)
	{
		return sqlProductoSucursal.darProductoSucursalPorId (pmf.getPersistenceManager(), idSucursal, idProducto);
	}
 
	
	 /* ****************************************************************
	 * 			Métodos para manejar la relación ORDENPEDIDO
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla OrdenPedido
	 */
	public OrdenPedido adicionarOrdenPedido(Object fechaEsperadaEntrega, Object fechaEntrega, Object calificacion, long idProveedor, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idOrdenPedido = nextval ();
            long tuplasInsertadas = sqlOrdenPedido.adicionarOrdenPedido(pm, idOrdenPedido, fechaEsperadaEntrega, fechaEntrega, calificacion, idSucursal, idProveedor);
            tx.commit();

            log.trace ("Inserción de OrdenPedido: " + fechaEsperadaEntrega + ": " + tuplasInsertadas + " tuplas insertadas");

            return new OrdenPedido (idOrdenPedido, fechaEsperadaEntrega, fechaEntrega, calificacion, idProveedor, idSucursal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla OrdenPedido, dado el identificador del OrdenPedido
	 * Adiciona entradas al log de la aplicación
	 * @param idOrdenPedido - El identificador del OrdenPedido
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOrdenPedidoPorId (long idOrdenPedido) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.eliminarOrdenPedidoPorId (pm, idOrdenPedido);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla OrdenPedido
	 * @return La lista de objetos OrdenPedido, construidos con base en las tuplas de la tabla OrdenPedido
	 */
	public List<OrdenPedido> darOrdenPedidos ()
	{
		return sqlOrdenPedido.darOrdenPedidos (pmf.getPersistenceManager());
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla OrdenPedido que tienen el identificador dado
	 * @param idOrdenPedido - El identificador del OrdenPedido
	 * @return El objeto OrdenPedido, construido con base en la tuplas de la tabla OrdenPedido, que tiene el identificador dado
	 */
	public OrdenPedido darOrdenPedidoPorId (long idOrdenPedido)
	{
		return sqlOrdenPedido.darOrdenPedidoPorId (pmf.getPersistenceManager(), idOrdenPedido);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación CATEGORIAPRODUCTOSUCURSAL
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla CATEGORIAPRODUCTOSUCURSAL
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto CATEGORIAPRODUCTOSUCURSAL adicionado. null si ocurre alguna Excepción
	 */
	public CategoriaProductoSucursal adicionarCategoriaProductoSucursal(long idSucusal, long idCategoria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoriaProductoSucursal.adicionarCategoriaProductoSucursal(pm, idSucusal, idCategoria);
			tx.commit();

			log.trace ("Inserción de CategoriaProductoSucursal: " + idSucusal + ": " + tuplasInsertadas + " tuplas insertadas");

			return new CategoriaProductoSucursal (idSucusal, idCategoria);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CATEGORIAPRODUCTOSUCURSAL, dado el identificador del CATEGORIAPRODUCTOSUCURSAL
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCategoriaProductoSucursalPorId (long idSucursal, long idCategoria) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCategoriaProductoSucursal.eliminarCategoriaProductoSucursalPorId (pm, idSucursal, idCategoria);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CATEGORIAPRODUCTOSUCURSAL
	 * @return La lista de objetos CATEGORIAPRODUCTOSUCURSAL, construidos con base en las tuplas de la tabla CATEGORIAPRODUCTOSUCURSAL
	 */
	public List<CategoriaProductoSucursal> darCategoriaProductoSucursales ()
	{
		return sqlCategoriaProductoSucursal.darCategoriasProductoSucursal (pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CATEGORIAPRODUCTOSUCURSAL que tienen el identificador dado
	 * @return El objeto CATEGORIAPRODUCTOSUCURSAL, construido con base en la tuplas de la tabla CATEGORIAPRODUCTOSUCURSAL, que tiene el identificador dado
	 */
	public CategoriaProductoSucursal darCategoriaProductoSucursalPorId (long idSucursal, long idCategoria)
	{
		return sqlCategoriaProductoSucursal.darCategoriaProductoSucursalPorId (pmf.getPersistenceManager(), idSucursal, idCategoria);
	}


	/* ****************************************************************
	 * 			Métodos para manejar la relación ACUERDOVENTA
	 *****************************************************************/
	
	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ACUERDOVENTA
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto ACUERDOVENTA adicionado. null si ocurre alguna Excepción
	 */
	public AcuerdoVenta adicionarAcuerdoVenta (long idOrdenPedido, long idProducto, int cantidad, int precio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlAcuerdoVenta.adicionarAcuerdoVenta(pm, idOrdenPedido, idProducto, cantidad, precio);
			tx.commit();

			log.trace ("Inserción de AcuerdoVenta: " + idOrdenPedido + ": " + tuplasInsertadas + " tuplas insertadas");

			return new AcuerdoVenta (idOrdenPedido, idProducto, cantidad, precio);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ACUERDOVENTA, dado el identificador del ACUERDOVENTA
	 * Adiciona entradas al log de la aplicación
	 * @param idAcuerdoVenta - El identificador del ACUERDOVENTA
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAcuerdoVentaPorId (long idAcuerdoVenta, long idProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAcuerdoVenta.eliminarAcuerdoVentaPorId (pm, idAcuerdoVenta, idProducto);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long finalizarOrdenPedido(long idAcuerdoVenta, Object fechafin, Object calificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOrdenPedido.finalizarOrdenPedido(pm, idAcuerdoVenta, fechafin, calificacion);
			tx.commit();

			return resp;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<AcuerdoVenta> darAcuerdoVentaPorIdOrdenPedido (long idOrdenPedido)
	{
		return sqlAcuerdoVenta.darAcuerdoVentaPorIdOrdenPedido (pmf.getPersistenceManager(), idOrdenPedido);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ACUERDOVENTA
	 * @return La lista de objetos ACUERDOVENTA, construidos con base en las tuplas de la tabla ACUERDOVENTA
	 */
	public List<AcuerdoVenta> darAcuerdosVenta ()
	{
		return sqlAcuerdoVenta.darAcuerdosVenta (pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ACUERDOVENTA que tienen el identificador dado
	 * @param idAcuerdoVenta - El identificador del ACUERDOVENTA
	 * @return El objeto ACUERDOVENTA, construido con base en la tuplas de la tabla ACUERDOVENTA, que tiene el identificador dado
	 */
	public AcuerdoVenta darAcuerdoVentaPorId (long idAcuerdoVenta, long idProducto)
	{
		return sqlAcuerdoVenta.darAcuerdoVentaPorId (pmf.getPersistenceManager(), idAcuerdoVenta, idProducto);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación PROMOCION
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Promocion
	 * Adiciona entradas al log de la aplicación
	 * @param fechaFin - El fechaFin del promocion
	 * @param tipoPromocion - La tipoPromocion del promocion
	 * @param promocion - El promocion del promocion (ALTO, MEDIO, BAJO)
	 * @return El objeto Promocion adicionado. null si ocurre alguna Excepción
	 */
	public Promocion adicionarPromocion(long idProducto, String fechaFin, String tipoPromocion, String promocion, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPromocion = nextval ();
            long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, idPromocion, idProducto, fechaFin, tipoPromocion, promocion, idSucursal);
            tx.commit();

            log.trace ("Inserción de Promocion: " + fechaFin + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Promocion (idPromocion, idProducto, fechaFin, tipoPromocion, promocion, idSucursal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Promocion, dado el identificador del promocion
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - El identificador del promocion
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromocionPorId (long idPromocion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocion.eliminarPromocionPorId (pm, idPromocion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Promocion
	 * @return La lista de objetos Promocion, construidos con base en las tuplas de la tabla Promocion
	 */
	public List<Promocion> darPromociones ()
	{
		return sqlPromocion.darPromociones (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Promocion que tienen el identificador dado
	 * @param idPromocion - El identificador del promocion
	 * @return El objeto Promocion, construido con base en la tuplas de la tabla Promocion, que tiene el identificador dado
	 */
	public Promocion darPromocionPorId (long idPromocion)
	{
		return sqlPromocion.darPromocionPorId (pmf.getPersistenceManager(), idPromocion);
	}
 
	
	 /* ****************************************************************
	 * 			Métodos para manejar la relación PRODUCTOFACTURA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ProductoFactura
	 * Adiciona entradas al log de la aplicación
	 * @param idFactura - El idFactura del ProductoFactura
	 * @param idProducto - La idProducto del ProductoFactura
	 * @param cantidad - El cantidad del ProductoFactura (ALTO, MEDIO, BAJO)
	 * @param promocion - El número de promocion del ProductoFactura en la idProducto (Mayor que 0)
	 * @return El objeto ProductoFactura adicionado. null si ocurre alguna Excepción
	 */

	public ProductoFactura adicionarProductoFactura(long idFactura, long idProducto, int cantidad, Object promocion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductoFactura.adicionarProductoFactura(pm, idFactura, idProducto, cantidad, promocion);
            tx.commit();

            log.trace ("Inserción de ProductoFactura: " + idFactura + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoFactura (idFactura, idProducto, cantidad, promocion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProductoFactura, dado el identificador del ProductoFactura
	 * Adiciona entradas al log de la aplicación
	 * @param idProducto - El identificador del ProductoFactura
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProductoFacturaPorId (long idFactura, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoFactura.eliminarProductoFacturaPorId (pm,idFactura, idProducto);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoFactura
	 * @return La lista de objetos ProductoFactura, construidos con base en las tuplas de la tabla ProductoFactura
	 */
	public List<ProductoFactura> darProductoFacturas ()
	{
		return sqlProductoFactura.darProductosFactura (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla ProductoFactura que tienen el identificador dado
	 * @param idProducto - El identificador del ProductoFactura
	 * @return El objeto ProductoFactura, construido con base en la tuplas de la tabla ProductoFactura, que tiene el identificador dado
	 */
	public ProductoFactura darProductoFacturaPorId (long idFactura, long idProducto)
	{
		return sqlProductoFactura.darProductoFacturaPorId (pmf.getPersistenceManager(), idFactura, idProducto);
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar la relación ESTANTE
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Estante
	 * Adiciona entradas al log de la aplicación
	 * @param capacidadTotal - El capacidadTotal del Estante
	 * @param nivelAbastecimiento - La nivelAbastecimiento del Estante
	 * @param categoriaProducto - El categoriaProducto del Estante 
	 * @return El objeto Estante adicionado. null si ocurre alguna Excepción
	 */
	public Estante adicionarEstante(int capacidadTotal, int nivelAbastecimiento, int categoriaProducto, long id_sucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEstante = nextval ();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pm, idEstante, capacidadTotal, nivelAbastecimiento, categoriaProducto, id_sucursal);
            tx.commit();

            log.trace ("Inserción de Estante: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Estante (idEstante, capacidadTotal, nivelAbastecimiento, categoriaProducto, id_sucursal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Estante, dado el identificador del Estante
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del Estante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEstantePorId (long idEstante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEstante.eliminarEstantePorId (pm, idEstante);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Estante
	 * @return La lista de objetos Estante, construidos con base en las tuplas de la tabla Estante
	 */
	public List<Estante> darEstantes ()
	{
		return sqlEstante.darEstantes (pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * Método que consulta todas las tuplas en la tabla Estante que tienen el identificador dado
	 * @param idEstante - El identificador del Estante
	 * @return El objeto Estante, construido con base en la tuplas de la tabla Estante, que tiene el identificador dado
	 */
	public Estante darEstantePorId (long idEstante)
	{
		return sqlEstante.darEstantePorId (pmf.getPersistenceManager(), idEstante);
	}
 
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación Reabastecer
	 *****************************************************************/

	 /**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Reabastecer
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto Reabastecer adicionado. null si ocurre alguna Excepción
	 */
	public Reabastecer adicionarReabastecer(long idBodega, long idEstante, int cantidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlReabastecer.adicionarReabastecer(pm, idBodega, idEstante, cantidad);

			Bodega bodega =  sqlBodega.darBodegaPorId(pm, idBodega);
			Estante estante = sqlEstante.darEstantePorId(pm, idEstante);

			
			int capacidad_disponible = bodega.getCantidadDisponible();
			int diferencia = capacidad_disponible -cantidad;

			int nivel_abastecimiento = estante.getAbastecimiento();
			int suma = nivel_abastecimiento + cantidad;

			int capacidad_estante = estante.getCapacidadTotal();


			if (diferencia > 0 && suma <= capacidad_estante )
			{
				sqlBodega.actualizarCapacidadDisponibleBodega(pm, idBodega,diferencia);
				sqlEstante.actualizarNivelAbastecimientoEstante(pm, idEstante, suma);
				tx.commit();

            log.trace ("Inserción de Reabastecer: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");
			
			}
			else 
			{
				log.trace ("Error, la cantidad que se quiere ingresar supera la capcidad del estante o se quiere sacar de la bodega mas productos de los que hay");
			}
			
            return new Reabastecer (idBodega, idEstante, cantidad);

            
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Reabastecer, dado el identificador del Reabastecer
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El identificador del Reabastecer
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReabastecerPorId (long idBodega, long idEstante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReabastecer.eliminarReabastecerPorId (pm, idBodega, idEstante);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Reabastecer
	 * @return La lista de objetos Reabastecer, construidos con base en las tuplas de la tabla Reabastecer
	 */
	public List<Reabastecer> darReabasteceres ()
	{
		return sqlReabastecer.darReabastecers (pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * Método que consulta todas las tuplas en la tabla Reabastecer que tienen el identificador dado
	 * @param idBodega - El identificador del Reabastecer
	 * @return El objeto Reabastecer, construido con base en la tuplas de la tabla Reabastecer, que tiene el identificador dado
	 */
	public Reabastecer darReabastecerPorId (long idBodega, long idEstante)
	{
		return sqlReabastecer.darReabastecerPorId (pmf.getPersistenceManager(), idBodega, idEstante);
	}
 
	

	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes(pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1,-1, -1, -1, -1, -1,-1, -1, -1, -1, -1,-1, -1, -1, -1, -1,-1,-1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

    public List<Empleado> darEmpleado() {
        return null;
    }

 }
