package uniandes.isis2304.superAndes.negocio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;


public class SuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		pp = PersistenciaSuperAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los USUARIO 
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Usuario adicionarUsuario (String nombre, String correo, String contrasenia)
	{
		log.info ("Adicionando Usuario: " + nombre);
		Usuario usuario = pp.adicionarUsuario (nombre, correo, contrasenia);		
		log.info ("Adicionando Usuario: " + usuario);
		return usuario;
	}

	/**
	 * Elimina un Usuario por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Usuario a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorId (long id)
	{
		log.info ("Eliminando Usuario por id: " + id);
		long resp = pp.eliminarUsuarioPorId (id);
		log.info ("Eliminando Usuario por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Usuarios en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Usuario con todos los Usuarios que conoce la aplicación, llenos con su información básica
	 */
	public List<Usuario> darUsuarios ()
	{
		log.info ("Consultando Usuarios");
		List<Usuario> usuarios = pp.darUsuarios ();	
		log.info ("Consultando Usuarios: " + usuarios.size() + " usuarios existentes");
		return usuarios;
	}

	/**
	 * Encuentra un Usuario y su información básica, según su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Usuario buscado
	 * @return Un objeto Usuario que corresponde con el nombre buscado y lleno con su información básica
	 * 			null, si un Usuario con dicho nombre no existe
	 */
	public Usuario darUsuarioPorId (long id)
	{
		log.info ("Dar información de un Usuario por id: " + id);
		Usuario usuario = pp.darUsuarioPorId (id);
		log.info ("Buscando Usuario por id: " + usuario != null ? usuario : "NO EXISTE");
		return usuario;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ROL
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un ROL 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Rol adicionarRol (String nombre, String descripcion)
	{
		log.info ("Adicionando Rol: " + nombre);
		Rol rol = pp.adicionarRol (nombre, descripcion);		
		log.info ("Adicionando Rol: " + rol);
		return rol;
	}

	/**
	 * Elimina un Rol por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Rol a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarRolPorId (long id)
	{
		log.info ("Eliminando Rol por id: " + id);
		long resp = pp.eliminarRolPorId (id);
		log.info ("Eliminando Rol por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Roles en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Rol con todos los Roles que conoce la aplicación, llenos con su información básica
	 */
	public List<VORol> darVORoles ()
	{
		log.info ("Consultando Roles");
		List<VORol> roles = new LinkedList<VORol> ();
		for (Rol r : pp.darRoles ())
		{
			roles.add (r);
		}
		log.info ("Consultando Roles: " + roles.size() + " roles existentes");
		return roles;
	}

	/**
	 * Encuentra un Rol y su información básica, según su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Rol buscado
	 * @return Un objeto Rol que corresponde con el nombre buscado y lleno con su información básica
	 * 			null, si un Rol con dicho nombre no existe
	 */
	public Rol darRolPorId (long id)
	{
		log.info ("Dar información de un Rol por id: " + id);
		Rol rol = pp.darRolPorId (id);
		log.info ("Buscando Rol por id: " + rol != null ? rol : "NO EXISTE");
		return rol;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los USUARIO_ROL 
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un usuarioRol 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto usuario_rol adicionado. null si ocurre alguna Excepción
	 */
	public UsuarioRol adicionarUsuarioRol (long idUsuario, long idRol, long idSucursal)
	{
		log.info ("Adicionando usuarioRol: " + idUsuario + " - " + idRol);
		UsuarioRol usuarioRol = pp.adicionarUsuarioRol (idUsuario, idRol, idSucursal);		
		log.info ("Adicionando usuarioRol: " + usuarioRol);
		return usuarioRol;
	}

	/**
	 * Elimina un usuarioRol por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del usuarioRol a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarUsuarioRolPorId (long idUsuario, long idRol)
	{
		log.info ("Eliminando usuarioRol por id: " + idUsuario + " - " + idRol);
		long resp = pp.eliminarUsuarioRolPorId (idUsuario, idRol);
		log.info ("Eliminando usuarioRol por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/**
	 * Encuentra un  usuarioRol en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 */
	public UsuarioRol darUsuarioRolPorId (long idUsuario, long idRol)
	{
		log.info ("Dar información de un usuarioRol por id: " + idUsuario + " - " + idRol);
		UsuarioRol usuarioRol = pp.darUsuarioRolPorId (idUsuario, idRol);
		log.info ("Buscando usuarioRol por id: " + usuarioRol != null ? usuarioRol : "NO EXISTE");
		return usuarioRol;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las SUCURSAL
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un SUCURSAL 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto SUCURSAL adicionado. null si ocurre alguna Excepción
	 */
	public Sucursal adicionarSucursal (String nombre, String ciudad, String direccion)
	{
		log.info ("Adicionando Sucursal: " + nombre);
		Sucursal sucursal = pp.adicionarSucursal (nombre, ciudad, direccion);		
		log.info ("Adicionando Sucursal: " + sucursal);
		return sucursal;
	}

		/**
	 * Encuentra un SUCURSAL y su información básica, según su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del SUCURSAL buscado
	 * @return Un objeto Rol que corresponde con el nombre buscado y lleno con su información básica
	 * 			null, si un Rol con dicho nombre no existe
	 */
	public Sucursal darSucursalPorId (long id)
	{
		log.info ("Dar información de un Sucursal por id: " + id);
		Sucursal sucursal = pp.darSucursalPorId (id);
		log.info ("Buscando Sucursal por id: " + sucursal != null ? sucursal : "NO EXISTE");
		return sucursal;
	}

	/**
	 * Encuentra todos los SUCURSAL en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos SUCURSAL con todos los SUCURSAL que conoce la aplicación, llenos con su información básica
	 */
	public List<Sucursal> darSucursales ()
	{
		log.info ("Listando Sucursales");
		List<Sucursal> sucursales = pp.darSucursales ();	
		log.info ("Listando Sucursales: " + sucursales.size() + " sucursales existentes");
		return sucursales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una PRODUCTO 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto PRODUCTO adicionado. null si ocurre alguna Excepción
	 */
	public Producto adicionarProducto(String nombre, String marca, String presentacion, long idCategoriaProducto, int precioUnitario, int cantidadPresentacion, int volumenEmpaque, int pesoEmpaque, int precioPorUnidad)
	{
		log.info ("Adicionando Producto: " + nombre);
		Producto producto = pp.adicionarProducto (nombre, marca, presentacion, idCategoriaProducto, precioUnitario, cantidadPresentacion, volumenEmpaque, pesoEmpaque, precioPorUnidad);		
		log.info ("Adicionando Producto: " + producto);
		return producto;
	}

	public Producto darProductoPorId (long id)
	{
		log.info ("Dar información de un Producto por id: " + id);
		Producto producto = pp.daProductoPorId (id);
		log.info ("Buscando Producto por id: " + producto != null ? producto : "NO EXISTE");
		return producto;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación CATEGORIAPRODUCTO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una CATEGORIA_PRODUCTO 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto CATEGORIA_PRODUCTO adicionado. null si ocurre alguna Excepción
	 */
	public CategoriaProducto adicionarCategoriaProducto (String tipo)
	{
		log.info ("Adicionando CategoriaProducto: " + tipo);
		CategoriaProducto categoriaProducto = pp.adicionarCategoriaProducto (tipo);		
		log.info ("Adicionando CategoriaProducto: " + categoriaProducto);
		return categoriaProducto;
	}

	/**
	 * Elimina una CATEGORIA_PRODUCTO por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id de la CATEGORIA_PRODUCTO a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCategoriaProductoPorId (long id)
	{
		log.info ("Eliminando CategoriaProducto por id: " + id);
		long resp = pp.eliminarCategoriaProductoPorId (id);
		log.info ("Eliminando CategoriaProducto por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una CATEGORIA_PRODUCTO en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 */
	public CategoriaProducto darCategoriaProductoPorId (long id)
	{
		log.info ("Dar información de una CategoriaProducto por id: " + id);
		CategoriaProducto categoriaProducto = pp.darCategoriaProductoPorId (id);
		log.info ("Buscando CategoriaProducto por id: " + categoriaProducto != null ? categoriaProducto : "NO EXISTE");
		return categoriaProducto;
	}

	/* ****************************************************************
	 * 			Métodos para BODEGA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una BODEGA 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto BODEGA adicionado. null si ocurre alguna Excepción
	 */
	public Bodega adicionarBodega (int capacidadTotal, int capacidadDisponible, long id_categoriaProducto, long id_sucursal)
	{
		log.info ("Adicionando Bodega: " + capacidadTotal + " - " + capacidadDisponible + " - " + id_categoriaProducto + " - " + id_sucursal);
		Bodega bodega = pp.adicionarBodega (capacidadTotal, capacidadDisponible, id_categoriaProducto, id_sucursal);		
		log.info ("Adicionando Bodega: " + bodega);
		return bodega;
	}

	/**
	 * Elimina una BODEGA por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id de la BODEGA a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarBodegaPorId (long id)
	{
		log.info ("Eliminando Bodega por id: " + id);
		long resp = pp.eliminarBodegaPorId (id);
		log.info ("Eliminando Bodega por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una BODEGA en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 */
	public Bodega darBodegaPorId (long id)
	{
		log.info ("Dar información de una Bodega por id: " + id);
		Bodega bodega = pp.darBodegaPorId (id);
		log.info ("Buscando Bodega por id: " + bodega != null ? bodega : "NO EXISTE");
		return bodega;
	}

	public Bodega darBodegaIdCategoriaIdSucursal(long idCategoria, long idSucursal)
	{
		log.info ("Dar información de una Bodega por idCategoria: " + idCategoria + " - " + idSucursal);
		Bodega bodega = pp.darBodegaPorIdCategoriaIdSucursal(idCategoria, idSucursal);
		log.info ("Buscando Bodega por idCategoria: " + bodega != null ? bodega : "NO EXISTE");
		return bodega;
	}

	/* ****************************************************************
	 * 			Métodos para CLIENTE
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un cliente
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del cliente
	 * @return El objeto cliente adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente (String direccion, String correo, String tipo_cliente, int puntos, String palabra_clave)
	{
        log.info ("Adicionando empleado.." );
        Cliente cliente = pp.adicionarCliente(direccion, correo, tipo_cliente, puntos, palabra_clave);		
        log.info ("Adicionando cliente: " + cliente);
        return cliente;
	}
	
	/**
	 * Elimina un empleado por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idCliente - El id del cliente a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarClientePorId (long idCliente)
	{
		log.info ("Eliminando cliente por id: " + idCliente);
        long resp = pp.eliminarClientePorId (idCliente);		
        log.info ("Eliminando cliente por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los clientes en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cliente con todos los cliente que conoce la aplicación, llenos con su información básica
	 */
	public List<Cliente> darClientes ()
	{
		log.info ("Consultando Cliente");
        List<Cliente> clientes = pp.darClientes() ;	
        log.info ("Consultando Clientes: " + clientes.size() + " existentes");
        return clientes;
	}

	public Cliente darClientePorId (long idCliente)
	{
		log.info ("Consultando Cliente por id: " + idCliente);
		Cliente cliente = pp.darClientePorId(idCliente) ;	
		log.info ("Consultando Cliente por id: " + cliente != null ? cliente : "NO EXISTE");
		return cliente;
	}


	/* ****************************************************************
	 * 			METODOS CONSULTA
	 *****************************************************************/

	 public List<Bodega> darBodegasPorSucursal (long idSucursal)
	 {
		 log.info ("Dar bodegas por sucursal: " + idSucursal);
		 List<Bodega> bodegas = pp.darBodegasPorSucursal(idSucursal);
		 log.info ("Dar bodegas por sucursal: " + bodegas.size() + " existentes");
		 return bodegas;
	 }

	 public List<Estante> darEstantePorSucursal(long idSucursal)
	 {
		 log.info ("Dar estantes por sucursal: " + idSucursal);
		 List<Estante> estantes = pp.darEstantePorIdSucursal(idSucursal);
		 log.info ("Dar estantes por sucursal: " + estantes.size() + " existentes");
		 return estantes;
	 }

	 public List<Producto> darProductosPorMarca(String marca)
	 {
		 log.info ("Dar productos por marca: " + marca);
		 List<Producto> productos = pp.darProductosPorMarca(marca);
		 log.info ("Dar productos por marca: " + productos.size() + " existentes");
		 return productos;
	 }
	
	/* ****************************************************************
	 * 			Métodos para manejar los PRODUCTOSUCURSAL
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un ProductoSucursal 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del ProductoSucursal
	 * @param presupuesto - El presupuesto del ProductoSucursal (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del ProductoSucursal
	 * @return El objeto ProductoSucursal adicionado. null si ocurre alguna Excepción
	 */
	public ProductoSucursal adicionarProductoSucursal (long idSucursal, long idProducto)
	{
        log.info ("Adicionando ProductoSucursal.." );
        ProductoSucursal ProductoSucursal = pp.adicionarProductoSucursal (idSucursal,idProducto);
        log.info ("Adicionando ProductoSucursal: " + ProductoSucursal);
        return ProductoSucursal;
	}


	/**
	 * Elimina un ProductoSucursal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idProductoSucursal - El identificador del ProductoSucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoSucursalPorId (long idSucursal, long idProducto)
	{
        log.info ("Eliminando ProductoSucursal por id...");
        long resp = pp.eliminarProductoSucursalPorId (idSucursal,idProducto);
        log.info ("Eliminando ProductoSucursal por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un ProductoSucursal y su información básica, según su identificador
	 * @param idProductoSucursal - El identificador del ProductoSucursal buscado
	 * @return Un objeto ProductoSucursal que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un ProductoSucursal con dicho identificador no existe
	 */
	public ProductoSucursal darProductoSucursalPorId (long idSucursal, long idProducto)
	{
        log.info ("Dar información de un ProductoSucursal por id..");
        ProductoSucursal ProductoSucursal = pp.darProductoSucursalPorId (idSucursal, idProducto);
        log.info ("Buscando ProductoSucursal por Id: " + ProductoSucursal != null ? ProductoSucursal : "NO EXISTE");
        return ProductoSucursal;
	}


	/**
	 * Encuentra todos los ProductoSucursales en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoSucursal con todos las ProductoSucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoSucursal> darProductoSucursales ()
	{
        log.info ("Listando ProductoSucursales");
        List<ProductoSucursal> ProductoSucursales = pp.darProductoSucursales ();	
        log.info ("Listando ProductoSucursales: " + ProductoSucursales.size() + " ProductoSucursales existentes");
        return ProductoSucursales;
	}
	
	/**
	 * Encuentra todos los ProductoSucursales en Parranderos y los devuelve como VOProductoSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoSucursal con todos las ProductoSucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoSucursal> darVOProductoSucursales ()
	{
        log.info ("Generando los VO de ProductoSucursales");
         List<VOProductoSucursal> voProductoSucursales = new LinkedList<VOProductoSucursal> ();
        for (ProductoSucursal bdor : pp.darProductoSucursales ())
        {
        	voProductoSucursales.add (bdor);
        }
        log.info ("Generando los VO de ProductoSucursales: " + voProductoSucursales.size() + " ProductoSucursales existentes");
       return voProductoSucursales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ORDENPEDIDO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un OrdenPedido 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del OrdenPedido
	 * @param presupuesto - El presupuesto del OrdenPedido (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del OrdenPedido
	 * @return El objeto OrdenPedido adicionado. null si ocurre alguna Excepción
	 */
	public OrdenPedido adicionarOrdenPedido (LocalDate fechaEsperadaEntrega, LocalDate fechaEntrega, int calificacion, long idProveedor, long idSucursal)
	{
        log.info ("Adicionando OrdenPedido..");
        OrdenPedido OrdenPedido = pp.adicionarOrdenPedido (fechaEsperadaEntrega, fechaEntrega, calificacion, idProveedor, idSucursal);
        log.info ("Adicionando OrdenPedido: " + OrdenPedido);
        return OrdenPedido;
	}


	/**
	 * Elimina un OrdenPedido por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idOrdenPedido - El identificador del OrdenPedido a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarOrdenPedidoPorId (long idOrdenPedido)
	{
        log.info ("Eliminando OrdenPedido por id: " + idOrdenPedido);
        long resp = pp.eliminarOrdenPedidoPorId (idOrdenPedido);
        log.info ("Eliminando OrdenPedido por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un OrdenPedido y su información básica, según su identificador
	 * @param idOrdenPedido - El identificador del OrdenPedido buscado
	 * @return Un objeto OrdenPedido que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un OrdenPedido con dicho identificador no existe
	 */
	public OrdenPedido darOrdenPedidoPorId (long idOrdenPedido)
	{
        log.info ("Dar información de un OrdenPedido por id: " + idOrdenPedido);
        OrdenPedido OrdenPedido = pp.darOrdenPedidoPorId (idOrdenPedido);
        log.info ("Buscando OrdenPedido por Id: " + OrdenPedido != null ? OrdenPedido : "NO EXISTE");
        return OrdenPedido;
	}


	/**
	 * Encuentra todos los OrdenPedidoes en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos OrdenPedido con todos las OrdenPedidoes que conoce la aplicación, llenos con su información básica
	 */
	public List<OrdenPedido> darOrdenPedidos ()
	{
        log.info ("Listando OrdenPedidoes");
        List<OrdenPedido> OrdenPedidoes = pp.darOrdenPedidos ();	
        log.info ("Listando OrdenPedidoes: " + OrdenPedidoes.size() + " OrdenPedidoes existentes");
        return OrdenPedidoes;
	}
	
	/**
	 * Encuentra todos los OrdenPedidoes en Parranderos y los devuelve como VOOrdenPedido
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOOrdenPedido con todos las OrdenPedidoes que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOrdenPedido> darVOOrdenPedidoes ()
	{
        log.info ("Generando los VO de OrdenPedidoes");
         List<VOOrdenPedido> voOrdenPedidoes = new LinkedList<VOOrdenPedido> ();
        for (OrdenPedido bdor : pp.darOrdenPedidos ())
        {
        	voOrdenPedidoes.add (bdor);
        }
        log.info ("Generando los VO de OrdenPedidoes: " + voOrdenPedidoes.size() + " OrdenPedidoes existentes");
       return voOrdenPedidoes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCION
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Promocion 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Promocion
	 * @param presupuesto - El presupuesto del Promocion 
	 * @param ciudad - La ciudad del Promocion
	 * @return El objeto Promocion adicionado. null si ocurre alguna Excepción
	 */
	public Promocion adicionarPromocion (long idProducto, LocalDate fechaFin, String tipoPromocion, String promocion, long idSucursal)
	{
		log.info ("Adicionando Promocion..");
		Promocion Promocion = pp.adicionarPromocion (idProducto, fechaFin, tipoPromocion, promocion, idSucursal);
		log.info ("Adicionando Promocion: " + Promocion);
		return Promocion;
	}

	/**
	 * Elimina un Promocion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - El identificador del Promocion a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionPorId (long idPromocion)
	{
        log.info ("Eliminando Promocion por id: " + idPromocion);
        long resp = pp.eliminarPromocionPorId (idPromocion);
        log.info ("Eliminando Promocion por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Promocion y su información básica, según su identificador
	 * @param idPromocion - El identificador del Promocion buscado
	 * @return Un objeto Promocion que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Promocion con dicho identificador no existe
	 */
	public Promocion darPromocionPorId (long idPromocion)
	{
        log.info ("Dar información de un Promocion por id: " + idPromocion);
        Promocion Promocion = pp.darPromocionPorId (idPromocion);
        log.info ("Buscando Promocion por Id: " + Promocion != null ? Promocion : "NO EXISTE");
        return Promocion;
	}


	/**
	 * Encuentra todos los Promociones en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Promocion con todos las Promociones que conoce la aplicación, llenos con su información básica
	 */
	public List<Promocion> darPromociones ()
	{
        log.info ("Listando Promociones");
        List<Promocion> Promociones = pp.darPromociones ();	
        log.info ("Listando Promociones: " + Promociones.size() + " Promociones existentes");
        return Promociones;
	}
	
	/**
	 * Encuentra todos los Promociones en Parranderos y los devuelve como VOPromocion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocion con todos las Promociones que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocion> darVOPromociones ()
	{
        log.info ("Generando los VO de Promociones");
         List<VOPromocion> voPromociones = new LinkedList<VOPromocion> ();
        for (Promocion bdor : pp.darPromociones ())
        {
        	voPromociones.add (bdor);
        }
        log.info ("Generando los VO de Promociones: " + voPromociones.size() + " Promociones existentes");
       return voPromociones;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PROVEEDOR
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un PROVEEDOR 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto PROVEEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Proveedor adicionarProveedor (String nombre)
	{
		log.info ("Adicionando Proveedor..");
		Proveedor Proveedor = pp.adicionarProveedor (nombre);
		log.info ("Adicionando Proveedor: " + Proveedor);
		return Proveedor;
	}

	/* ****************************************************************
	 * 			Métodos para manejar ACUERDO_VENTA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un ACUERDO_VENTA 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto ACUERDO_VENTA adicionado. null si ocurre alguna Excepción
	 */
	public AcuerdoVenta adicionarAcuerdoVenta (long idOrdenPedido, long idProducto, int cantidad, int precio)
	{
		log.info ("Adicionando AcuerdoVenta..");
		AcuerdoVenta AcuerdoVenta = pp.adicionarAcuerdoVenta (idOrdenPedido, idProducto, cantidad, precio);
		log.info ("Adicionando AcuerdoVenta: " + AcuerdoVenta);
		return AcuerdoVenta;
	}

	public List<AcuerdoVenta> darAcuerdoVentaPorIdOrdenPedido (long id)
	{
		log.info ("Listando AcuerdoVenta");
		List<AcuerdoVenta> AcuerdoVenta = pp.darAcuerdoVentaPorIdOrdenPedido (id);	
		log.info ("Listando AcuerdoVenta: " + AcuerdoVenta.size() + " AcuerdoVenta existentes");
		return AcuerdoVenta;
	}

	public long finalizarOrdenPedido (long idOrdenPedido, LocalDate fecha, int califiacion)
	{
		log.info ("Finalizando OrdenPedido..");
		long resp = pp.finalizarOrdenPedido (idOrdenPedido, fecha, califiacion);
		log.info ("Finalizando OrdenPedido: " + resp);
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar CATEGORIA_PRODUCTO_SUCURSAL
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un ACUERDO_VENTA 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto ACUERDO_VENTA adicionado. null si ocurre alguna Excepción
	 */
	public CategoriaProductoSucursal adicionarCategoriaProductoSucursal (long idCategoria, long idSucursal)
	{
		log.info ("Adicionando CategoriaProductoSucursal..");
		CategoriaProductoSucursal CategoriaProductoSucursal = pp.adicionarCategoriaProductoSucursal (idCategoria, idSucursal);
		log.info ("Adicionando CategoriaProductoSucursal: " + CategoriaProductoSucursal);
		return CategoriaProductoSucursal;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las FACTURA 
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un FACTURA 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto FACTURA adicionado. null si ocurre alguna Excepción
	 */
	public Factura adicionarFactura (long idCliente, long idSucursal, int subTotal, int totalPagar, LocalDate fecha)
	{
		log.info ("Adicionando Factura..");
		Factura Factura = pp.adicionarFactura (idCliente, idSucursal, subTotal, totalPagar, fecha);
		log.info ("Adicionando Factura: " + Factura);
		return Factura;
	}

	public void actualizarSubTotal(long idFactura, int subTotal)
	{
		log.info ("Actualizando SubTotal..");
		pp.actualizarSubTotal(idFactura, subTotal);
		log.info ("Actualizando SubTotal: " + idFactura);
	}

	public Factura darFacturaPorId(long idFactura)
	{
		log.info ("Buscando Factura por id: " + idFactura);
		Factura Factura = pp.darFacturaPorId(idFactura);
		log.info ("Buscando Factura por id: " + Factura != null ? Factura : "NO EXISTE");
		return Factura;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PROVEEDOR_SUCURSAL
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un PROVEEDOR_SUCURSAL 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto PROVEEDOR_SUCURSAL adicionado. null si ocurre alguna Excepción
	 */
	public ProveedorSucursal adicionarProveedorSucursal (long idProveedor, long idSucursal)
	{
		log.info ("Adicionando ProveedorSucursal..");
		ProveedorSucursal ProveedorSucursal = pp.adicionarProveedorSucursal (idProveedor, idSucursal);
		log.info ("Adicionando ProveedorSucursal: " + ProveedorSucursal);
		return ProveedorSucursal;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación PRODUCTOFACTURA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un ProductoFactura 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del ProductoFactura
	 * @param presupuesto - El presupuesto del ProductoFactura (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del ProductoFactura
	 * @return El objeto ProductoFactura adicionado. null si ocurre alguna Excepción
	 */
	public ProductoFactura adicionarProductoFactura (long idFactura, long idProducto, int cantidad)
	{
        log.info ("Adicionando ProductoFactura..");
        ProductoFactura ProductoFactura = pp.adicionarProductoFactura (idFactura,idProducto,cantidad);
        log.info ("Adicionando ProductoFactura: " + ProductoFactura);
        return ProductoFactura;
	}

	
	/**
	 * Elimina un ProductoFactura por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idFactura, 
	 * @param idProducto 
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoFacturaPorId (long idFactura, long idProducto)
	{
        log.info ("Eliminando ProductoFactura por id..." );
        long resp = pp.eliminarProductoFacturaPorId (idFactura, idProducto);
        log.info ("Eliminando ProductoFactura por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un ProductoFactura y su información básica, según su identificador
	 * @param idFactura, idProducto - El identificador del ProductoFactura buscado
	 * @return Un objeto ProductoFactura que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un ProductoFactura con dicho identificador no existe
	 */
	public ProductoFactura darProductoFacturaPorId (long idFactura,long idProducto)
	{
        log.info ("Dar información de un ProductoFactura por id.. " );
        ProductoFactura ProductoFactura = pp.darProductoFacturaPorId (idFactura, idProducto);
        log.info ("Buscando ProductoFactura por Id: " + ProductoFactura != null ? ProductoFactura : "NO EXISTE");
        return ProductoFactura;
	}

	/**
	 * Encuentra todos los ProductoFacturaes en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoFactura con todos las ProductoFacturaes que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoFactura> darProductoFacturas ()
	{
        log.info ("Listando ProductoFacturaes");
        List<ProductoFactura> ProductoFacturaes = pp.darProductoFacturas ();	
        log.info ("Listando ProductoFacturaes: " + ProductoFacturaes.size() + " ProductoFacturaes existentes");
        return ProductoFacturaes;
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar la relación ESTANTE
	 *****************************************************************/

	 /**
	 * Adiciona de manera persistente un Estante 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto Estante adicionado. null si ocurre alguna Excepción
	 */
	public Estante adicionarEstante (int capacidadTotal, int nivelAbastecimiento, int categoriaProducto, long id_sucursal, int capacidadDisponible)
	{
		log.info ("Adicionando Estante..");
		Estante Estante = pp.adicionarEstante (capacidadTotal, nivelAbastecimiento, categoriaProducto, id_sucursal, capacidadDisponible);
		log.info ("Adicionando Estante: " + Estante);
		return Estante;
	}
	
	public Estante darEstantePorIdSucursalIdCategoriaProducto(long idSucursal, long categoriaProducto)
	{
		log.info ("Dar información de un Estante por id.. " );
		Estante Estante = pp.darEstantePorIdSucursalIdCategoriaProducto (idSucursal, categoriaProducto);
		log.info ("Buscando Estante por Id: " + Estante != null ? Estante : "NO EXISTE");
		return Estante;
	}

	public void actualizarNivelInventarioEstante(long idEstante, int nivelAbastecimiento)
	{
		log.info ("Actualizando nivel de abastecimiento de un estante.. " );
		pp.actualizarNivelAbastecimientoEstante(idEstante, nivelAbastecimiento);
		log.info ("Actualizando nivel de abastecimiento de un estante: " + idEstante);
	}

	/**
	 * Elimina un Estante por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del Estante a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEstantePorId (long idEstante)
	{
        log.info ("Eliminando Estante por id: " + idEstante);
        long resp = pp.eliminarEstantePorId (idEstante);
        log.info ("Eliminando Estante por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Estante y su información básica, según su identificador
	 * @param idEstante - El identificador del Estante buscado
	 * @return Un objeto Estante que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Estante con dicho identificador no existe
	 */
	public Estante darEstantePorId (long idEstante)
	{
        log.info ("Dar información de un Estante por id: " + idEstante);
        Estante Estante = pp.darEstantePorId (idEstante);
        log.info ("Buscando Estante por Id: " + Estante != null ? Estante : "NO EXISTE");
        return Estante;
	}

	/**
	 * Encuentra todos los Estantes en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos las Estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<Estante> darEstantes ()
	{
        log.info ("Listando Estantes");
        List<Estante> Estantes = pp.darEstantes ();	
        log.info ("Listando Estantes: " + Estantes.size() + " Estantes existentes");
        return Estantes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO_BODEGA
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un PRODUCTO_BODEGA 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto PRODUCTO_BODEGA adicionado. null si ocurre alguna Excepción
	 */
	public ProductoBodega adicionarProductoBodega(long idBodega, long idProducto, int cantidad)
	{
		log.info("Adicionando ProductoBodega");
		ProductoBodega productoBodega = pp.adicionarProductoBodega(idBodega, idProducto, cantidad);
		log.info("Adicionando ProductoBodega: " + productoBodega);
		return productoBodega;
	}

	public long actualizarCantidadProductoBodega(long idBodega, long idProducto, int cantidad)
	{
		log.info("Actualizando cantidad de ProductoBodega");
		long resp = pp.actualizarCantidadProductoBodega(idBodega, idProducto, cantidad);
		log.info("Actualizando cantidad de ProductoBodega: " + resp + " tuplas actualizadas");
		return resp;
	}

	public void actualizarCapacidadDisponibleBodega(long id, int cantidad)
	{
		pp.actualizarCapacidadDisponibleBodega(id, cantidad);
	}

	public ProductoBodega darProductoBodegaPorId(long idProducto, long idBodega)
	{
		log.info("Dar información de un ProductoBodega por id");
		ProductoBodega productoBodega = pp.darProductoBodegaPorId(idProducto, idBodega);
		log.info("Buscando ProductoBodega por Id: " + productoBodega != null ? productoBodega : "NO EXISTE");
		return productoBodega;
	}

	/* ****************************************************************
	 * 			Métodos para manejar CARRO_COMPRA
	 *****************************************************************/

	public long adicionarCarroCompra(long idCliente, String isActivo,long id_Sucursal)
	{
		log.info("Adicionando CarroCompra");
		long resp = pp.adicionarCarroCompra(idCliente, isActivo,id_Sucursal);
		log.info("Adicionando CarroCompra: " + resp);
		return resp;
	}

	public CarroCompra darCarroCompraPorId(long id)
	{
		log.info("Dar información de un CarroCompra por id");
		CarroCompra carroCompra = pp.darCarroCompraPorId(id);
		log.info("Buscando CarroCompra por Id: " + carroCompra != null ? carroCompra : "NO EXISTE");
		return carroCompra;
	}

	public CarroCompra darCarroCompraPorIdCliente(long idCliente)
	{
		log.info("Dar información de un CarroCompra por idCliente");
		CarroCompra carroCompra = pp.darCarroCompraPorIdCliente(idCliente);
		log.info("Buscando CarroCompra por IdCliente: " + carroCompra != null ? carroCompra : "NO EXISTE");
		return carroCompra;
	}

	public List<CarroCompra> darCarrosCompra()
	{
		log.info("Listando CarrosCompra");
		List<CarroCompra> carrosCompra = pp.darCarrosCompra();
		log.info("Listando CarrosCompra: " + carrosCompra.size() + " CarrosCompra existentes");
		return carrosCompra;
	}

	public long eliminarCarroCompraPorId(long id)
	{
		log.info("Eliminando CarroCompra por id: " + id);
		long resp = pp.eliminarCarroCompraPorId(id);
		log.info("Eliminando CarroCompra por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	public long actualizarEstadoCarroCompra (long id, String isActivo)
	{
		log.info("Actualizando estado de CarroCompra: " + id);
		long resp = pp.actualizarEstadoCarroCompra(id, isActivo);
		log.info("Actualizando estado de CarroCompra: " + resp + " tuplas actualizadas");
		return resp;
	}

	public List<CarroCompra> darCarroCompraAbandonado()
	{
		log.info("Eliminando CarroCompra eliminado");
		List<CarroCompra> carrosCompra = pp.darCarroCompraAbandonado();
		log.info("Eliminando CarroCompra eliminado: " + carrosCompra.size() + " CarrosCompra eliminados");
		return carrosCompra;
	}

	/**********************************************************************
	 * 			Métodos para manejar la relación PRODUCTO_CARRO_COMPRA
	************************************************************************/

	public ProductoCarroCompra adicionarProductoCarroCompra(long idCarroCompra, long idProducto, int cantidad)
	{
		log.info("Adicionando ProductoCarroCompra");
		ProductoCarroCompra productoCarroCompra = pp.adicionarProductoCarroCompra(idCarroCompra, idProducto, cantidad);
		log.info("Adicionando ProductoCarroCompra: " + productoCarroCompra);
		return productoCarroCompra;
	}

	public ProductoCarroCompra darProductoCarroCompraPorId(long idCarroCompra, long idProducto)
	{
		log.info("Dar información de un ProductoCarroCompra por id");
		ProductoCarroCompra productoCarroCompra = pp.darProductoCarroCompraPorId(idCarroCompra, idProducto);
		log.info("Buscando ProductoCarroCompra por Id: " + productoCarroCompra != null ? productoCarroCompra : "NO EXISTE");
		return productoCarroCompra;
	}

	public List<ProductoCarroCompra> darProductosCarrosCompra()
	{
		log.info("Listando ProductosCarroCompra");
		List<ProductoCarroCompra> productosCarroCompra = pp.darProductosCarrosCompra();
		log.info("Listando ProductosCarroCompra: " + productosCarroCompra.size() + " ProductosCarroCompra existentes");
		return productosCarroCompra;
	}

	public long eliminarProductoCarroCompraPorId(long idCarroCompra, long idProducto)
	{
		log.info("Eliminando ProductoCarroCompra por id: " + idCarroCompra + " " + idProducto);
		long resp = pp.eliminarProductoCarroCompraPorId(idCarroCompra, idProducto);
		log.info("Eliminando ProductoCarroCompra por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	public void actualizarCantidadProductoCarroCompra(long idCarroCompra, long idProducto, int cantidad)
	{
		log.info("Actualizando cantidad de ProductoCarroCompra");
		pp.actualizarCantidadProductoCarroCompra(idCarroCompra, idProducto, cantidad);
		log.info("Actualizando cantidad de ProductoCarroCompra");
	}

	public List<ProductoCarroCompra> darProductosCarroCompraPorIdCarroCompra(long idCarroCompra)
	{
		log.info("Eliminando ProductosCarroCompra por idCarroCompra: " + idCarroCompra);
		List<ProductoCarroCompra> resp = pp.darProductosCarroCompraPorIdCarroCompra(idCarroCompra);
		log.info("Eliminando ProductosCarroCompra por idCarroCompra: " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<ProductoCarroCompra> darProductoCarroComprasPorIdCarroCompra(long idCarroCompra)
	{
		log.info("Dar información de un ProductoCarroCompra por id del carro compra");
		List<ProductoCarroCompra> productoCarroCompras = pp.darProductoCarroComprasPorIdCarroCompra(idCarroCompra);
		log.info("Buscando ProductoCarroCompra por IdCarroCompra: " + idCarroCompra != null ? productoCarroCompras : "NO EXISTE");
		return productoCarroCompras;
	}

	/*****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/

	public long [] limpiarSuperAndes ()
	{
        log.info ("Limpiando la BD de SuperAndes");
        long [] borrrados = pp.limpiarSuperAndes();	
        log.info ("Limpiando la BD de SuperAndes: Listo!");
        return borrrados;
	}
}
