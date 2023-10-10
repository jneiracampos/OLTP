
package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.AcuerdoVenta;
import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOAcuerdoVenta;
import uniandes.isis2304.superAndes.negocio.VOBodega;
import uniandes.isis2304.superAndes.negocio.VOCategoriaProducto;
import uniandes.isis2304.superAndes.negocio.VOCategoriaProductoSucursal;
import uniandes.isis2304.superAndes.negocio.VOCliente;
import uniandes.isis2304.superAndes.negocio.VOClienteSucursal;
import uniandes.isis2304.superAndes.negocio.VOEmpleado;
import uniandes.isis2304.superAndes.negocio.VOEstante;
import uniandes.isis2304.superAndes.negocio.VOFactura;
import uniandes.isis2304.superAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.superAndes.negocio.VOProducto;
import uniandes.isis2304.superAndes.negocio.VOProductoBodega;
import uniandes.isis2304.superAndes.negocio.VOProductoFactura;
import uniandes.isis2304.superAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOProveedorSucursal;
import uniandes.isis2304.superAndes.negocio.VOReabastecer;
/*import uniandes.isis2304.superAndes.negocio.VOBodega;
*/
import uniandes.isis2304.superAndes.negocio.VORol;
import uniandes.isis2304.superAndes.negocio.VOSucursal;
import uniandes.isis2304.superAndes.negocio.VOUsuario;
import uniandes.isis2304.superAndes.negocio.VOUsuarioRol;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private SuperAndes superAndes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperAndesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superAndes = new SuperAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }

	/* ****************************************************************
	 * 			 Seguridad - Validar Usuario
	 *****************************************************************/

	public boolean validarAdministradorBaseDatos()
	{
		boolean seguridad = true;	
		String [] administrador = {"Id", "Contrasena"};
		String [] validar = new String [administrador.length];

		for (int i = 0; i < administrador.length; i++)
		{
			validar[i] = JOptionPane.showInputDialog (this, administrador[i], "Validar administrador base de datos", JOptionPane.QUESTION_MESSAGE);
			if (validar[i] == null || validar[i].equals (""))
			{
				seguridad = false;
			}
		}

		long id = Long.parseLong(validar[0]);
		String contrasena = validar[1];
		VOUsuario usuario = superAndes.darUsuarioPorId(id);

		if (usuario == null)
		{
			seguridad = false;
			return seguridad;
		}

		long idAdministradorDatos = 0;
		VOUsuarioRol usuarioRol = superAndes.darUsuarioRolPorId(id, idAdministradorDatos);

		if (usuarioRol == null)
		{
			seguridad = false;
		}

		if (!usuario.getPalabraClave().equals(contrasena))
		{
			seguridad = false;
		}

		return seguridad;
	}

	public Object [] validarGerenteSucursal()
	{
		Object [] seguridad =  new Object [2];
		seguridad[0] = true;
		String [] administrador = {"Id", "Contrasena"};
		String [] validar = new String [administrador.length];
	
		for (int i = 0; i < administrador.length; i++)
		{
			validar[i] = JOptionPane.showInputDialog (this, administrador[i], "Validar gerente sucursal", JOptionPane.QUESTION_MESSAGE);
			if (validar[i] == null || validar[i].equals (""))
			{
				seguridad[0] = false;
			}
		}

		long id = Long.parseLong(validar[0]);
		String contrasena = validar[1];
		VOUsuario usuario = superAndes.darUsuarioPorId(id);

		if (usuario == null)
		{
			seguridad[0] = false;
			return seguridad;
		}

		if (!usuario.getPalabraClave().equals(contrasena))
		{
			seguridad[0] = false;
		}

		long idGerenteSucursal = 9;
		VOUsuarioRol usuarioRol = superAndes.darUsuarioRolPorId(id, idGerenteSucursal);

		if (usuarioRol == null)
		{
			seguridad[0] = false;
		}

		Object idSucursal = usuarioRol.getIdSucursal();
		String idSucursalString = idSucursal.toString();
		Long idSucursalLong = Long.parseLong(idSucursalString);
		seguridad[1] = idSucursalLong;
		return seguridad;
	}

	public Object [] validarCajeroSucursal()
	{
		Object [] seguridad =  new Object [2];
		seguridad[0] = true;
		String [] administrador = {"Id", "Contrasena"};
		String [] validar = new String [administrador.length];
	
		for (int i = 0; i < administrador.length; i++)
		{
			validar[i] = JOptionPane.showInputDialog (this, administrador[i], "Validar cajero sucursal", JOptionPane.QUESTION_MESSAGE);
			if (validar[i] == null || validar[i].equals (""))
			{
				seguridad[0] = false;
			}
		}

		long id = Long.parseLong(validar[0]);
		String contrasena = validar[1];

		VOUsuario usuario = superAndes.darUsuarioPorId(id);

		if (usuario == null)
		{
			seguridad[0] = false;
			return seguridad;
		}

		if (!usuario.getPalabraClave().equals(contrasena))
		{
			seguridad[0] = false;
		}

		long idCajeroSucursal = 11;
		VOUsuarioRol usuarioRol = superAndes.darUsuarioRolPorId(id, idCajeroSucursal);

		if (usuarioRol == null)
		{
			seguridad[0] = false;
		}

		Object idSucursalUsuario = usuarioRol.getIdSucursal();
		String idSucursalUsuarioString = idSucursalUsuario.toString();
		Long idSucursalUsuarioLong = Long.parseLong(idSucursalUsuarioString);
		seguridad[1] = idSucursalUsuarioLong;

		return seguridad;
	}
    
	/* ****************************************************************
	 * 			Registrar ROL
	 *****************************************************************/
	
	 public void registrarRol()
	{
		try
		{
			boolean seguridad = validarAdministradorBaseDatos();

			if (!seguridad)
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar un rol");
			}
			else
			{
				String [] opciones = {"Nombre", "Descripción"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Adicionar Rol", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Adición de Rol cancelada por el usuario");
						return;
					}
				}

				String nombre = respuestas[0];
				String descripcion = respuestas[1];
				if (nombre.equals("") || descripcion.equals(""))
				{
					throw new Exception ("Nombre y descripción del rol no deben ser vacíos");
				}

				VORol rol = superAndes.adicionarRol(nombre, descripcion);
				if (rol == null)
				{
					throw new Exception ("No se pudo crear un rol con nombre: " + nombre);
				}

				String resultado = "En adicionarRol\n\n";
				resultado += "Rol adicionado exitosamente: " + rol;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			Registrar USUARIO
	 *****************************************************************/

	public void registrarUsuario()
	{
		try
		{
			boolean seguridad = validarAdministradorBaseDatos();

			if (!seguridad)
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar un usuario");
			}
			else 
			{
				String [] opciones = {"Nombre", "Correo", "Contraseña", "idRol", "idSucursal"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Usuario cancelado por el usuario");
						return;
					}
				}

				String nombre = respuestas[0];
				String correo = respuestas[1];
				String contrasena = respuestas[2];
				long idRol = Long.parseLong(respuestas[3]);
				long idSucursal = Long.parseLong(respuestas[4]);

				if (nombre.equals("") || correo.equals("") || contrasena.equals(""))
				{
					throw new Exception ("Nombre, correo, contrasena no deben ser vacíos");
				}

				VOUsuario usuario = superAndes.adicionarUsuario(nombre, correo, contrasena);

				if (usuario == null)
				{
					throw new Exception ("No se pudo registrar un usuario con nombre: " + nombre);
				}

				VOUsuarioRol usuarioRol = superAndes.adicionarUsuarioRol(usuario.getId(), idRol, idSucursal);

				if (usuarioRol == null)
				{
					throw new Exception ("No se pudo registrar un usuarioRol con idUsuario: " + usuario.getId());
				}

				VOEmpleado empleado = superAndes.adicionarEmpleado(nombre, correo, idSucursal);

				if (empleado == null)
				{
					throw new Exception ("No se pudo registrar un empleado con idUsuario: " + usuario.getId());
				}

				String resultado = "En registrarUsuario\n\n";
				resultado += "Usuario registrado exitosamente: " + usuario;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			Registrar SUCURSAL
	 *****************************************************************/

	public void registrarSucursal()
	{
		try
		{
			boolean seguridad = validarAdministradorBaseDatos();

			if (!seguridad)
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar un usuario");
			}
			else
			{
				String [] opciones = {"Nombre", "Ciudad", "Direccion"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Sucursal cancelado por el usuario");
						return;
					}
				}

				String nombre = respuestas[0];
				String ciudad = respuestas[1];
				String direccion = respuestas[2];

				VOSucursal sucursal = superAndes.adicionarSucursal(nombre, ciudad, direccion);

				if (sucursal == null)
				{
					throw new Exception ("No se pudo registrar una sucursal con nombre: " + nombre);
				}

				String resultado = "En registrarSucursal\n\n";
				resultado += "Sucursal registrada exitosamente: " + sucursal;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			Registrar BODEGA
	 *****************************************************************/

	public void registrarBodega()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una bodega");
			}
			else
			{
				String [] opciones = {"Capacidad total", "Capacidad disponible", "id categoria producto"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Bodega", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Bodega cancelado por el usuario");
						return;
					}
				}

				int capacidadTotal = Integer.parseInt(respuestas[0]);
				int capacidadDisponible = Integer.parseInt(respuestas[1]);
				long idCategoriaProducto = Long.parseLong(respuestas[2]);
				Long idSucursal = (Long) seguridad[1];

				VOBodega bodega = superAndes.adicionarBodega(capacidadTotal, capacidadDisponible, idCategoriaProducto, idSucursal);

				if (bodega == null)
				{
					throw new Exception ("No se pudo registrar una bodega con id_categoria_producto: " + idCategoriaProducto);
				}

				String resultado = "En registrarBodega\n\n";
				resultado += "Bodega registrada exitosamente: " + bodega;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR ESTANTE 
	 *****************************************************************/

	public void registrarEstante()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar un estante");
			}
			else
			{
				String [] opciones = {"CapacidadTotal", "NivelAbastecimiento", "CategoriaProducto"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Estante", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Estante cancelado por el usuario");
						return;
					}
				}

				int capacidadTotal = Integer.parseInt(respuestas[0]);
				int nivelAbastecimiento = Integer.parseInt(respuestas[1]);
				int categoriaProducto = Integer.parseInt(respuestas[2]);
				long idSucursal = (long) seguridad[1];
				

				VOEstante estante = superAndes.adicionarEstante(capacidadTotal, nivelAbastecimiento, categoriaProducto, idSucursal);

				if (estante == null)
				{
					throw new Exception ("No se pudo registrar el estante. " );
				}

				String resultado = "En registrarEstante\n\n";
				resultado += "Sucursal registrada exitosamente: " + estante;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			Registrar PROVEEDOR
	 *****************************************************************/

	public void registrarProveedor()
	{
		try 
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una promoción");
			}
			else
			{
				String nombre = JOptionPane.showInputDialog (this, "Nombre", "Registrar Proveedor", JOptionPane.QUESTION_MESSAGE);

				VOProveedor proveedor = superAndes.adicionarProveedor(nombre);

				if (proveedor == null)
				{
					throw new Exception ("No se pudo registrar el proveedor con nombre: " + nombre);
				}

				VOProveedorSucursal proveedorSucursal = superAndes.adicionarProveedorSucursal(proveedor.getId(), (long) seguridad[1]);

				if (proveedorSucursal == null)
				{
					throw new Exception ("No se pudo registrar el proveedorSucursal con nombre: " + nombre);
				}

				String resultado = "En registrarProveedor\n\n";
				resultado += "Proveedor registrado exitosamente: " + proveedor;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}
	/* ****************************************************************
	 * 			Registrar CLIENTE
	 *****************************************************************/

	public void registrarCliente()
	{
		try 
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar un cliente");
			}
			else
			{
				String [] opciones = {"direccion", "correo", "tipo_cliente"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Factura cancelado por el usuario");
						return;
					}
				}

				String direccion = respuestas[0];
				String correo = respuestas[1];
				String tipoCliente = respuestas[2];
				
				VOCliente cliente = superAndes.adicionarCliente(direccion,correo,tipoCliente);

				if (cliente == null)
				{
					throw new Exception ("No se pudo registrar el cliente");
				}

				String resultado = "En registrarCliente\n\n";
				resultado += "Cliente registrado exitosamente: " + cliente;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR ORDEN_PEDIDO
	 *****************************************************************/

	public void registrarOrdenPedido()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una orden de pedido");
			}
			else
			{
				String [] opciones = {"Fecha esperada entrega", "idProveedor"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Orden Pedido", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Orden Pedido cancelado por el usuario");
						return;
					}
				}

				Object fechaEsperadaEntrega = respuestas[0];
				long idProveedor = Long.parseLong(respuestas[1]);
				long idSucursal = (long) seguridad[1];

				VOOrdenPedido ordenPedido = superAndes.adicionarOrdenPedido(fechaEsperadaEntrega, null, null, idProveedor, idSucursal);

				if (ordenPedido == null)
				{
					throw new Exception ("No se pudo registrar la orden de pedido con id_producto");
				}

				registrarAcuerdoVenta(ordenPedido.getId());

				String acuerdoVenta = JOptionPane.showInputDialog (this, "Registrar otro acuerdo venta (si / no)", "Registrar Acuerdo Venta", JOptionPane.QUESTION_MESSAGE);
				while (acuerdoVenta.equals("si"))
				{
					registrarAcuerdoVenta(ordenPedido.getId());
					acuerdoVenta = JOptionPane.showInputDialog (this, "Registrar otro producto (si / no)", "Registrar Producto Factura", JOptionPane.QUESTION_MESSAGE);
				}

				String resultado = "En registrarOrdenPedido\n\n";
				resultado += "Orden de pedido registrada exitosamente: " + ordenPedido;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR ACUERDO_VENTA
	 *****************************************************************/
	
	public void registrarAcuerdoVenta(long idAcuerdoVenta)
	{
		try
		{
			String [] opciones = {"idProducto", "cantidad", "precio"};
			String [] respuestas = new String [opciones.length];

			for (int i = 0; i < respuestas.length; i++)
			{
				respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Acuerdo Venta", JOptionPane.QUESTION_MESSAGE);
				if (respuestas[i] == null)
				{
					panelDatos.actualizarInterfaz("Registro de Acuerdo Venta cancelado por el usuario");
					return;
				}
			}

			long idProducto = Long.parseLong(respuestas[0]);
			int cantidad = Integer.parseInt(respuestas[1]);
			int precio = Integer.parseInt(respuestas[2]);

			VOAcuerdoVenta acuerdoVenta = superAndes.adicionarAcuerdoVenta(idAcuerdoVenta, idProducto, cantidad, precio);

			if (acuerdoVenta == null)
			{
				throw new Exception ("No se pudo registrar el acuerdo de venta con id_producto");
			}

			String resultado = "En registrarAcuerdoVenta\n\n";
			resultado += "Acuerdo de venta registrado exitosamente: " + acuerdoVenta;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			FINALIZAR ORDEN_PEDIDO
	 *****************************************************************/

	 public void finalizarOrdenPedido()
	 {
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar la llegada de un acuerdo de venta");
			}
			else
			{
				String [] opciones = {"idOrdenPedido", "fechaLlegada", "calificacion (0 - 5)"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Finalizar Orden Pedido", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Finalizar Orden Pedido cancelado por el usuario");
						return;
					}
				}

				long idOrdenPedido = Long.parseLong(respuestas[0]);
				Object fechaLlegada = respuestas[1];
				Object calificacion = respuestas[2];

				superAndes.finalizarOrdenPedido(idOrdenPedido, fechaLlegada, calificacion);

				List<AcuerdoVenta> acuerdoVenta = superAndes.darAcuerdoVentaPorIdOrdenPedido(idOrdenPedido);

				for (int i = 0; i < acuerdoVenta.size(); i++)
				{
					VOProducto producto = superAndes.darProductoPorId(acuerdoVenta.get(i).getProducto());
					VOBodega bodega = superAndes.darBodegaIdCategoriaIdSucursal(producto.getIdCategoriaProducto(), (long) seguridad[1]);

					int cantidad = bodega.getCantidadDisponible() - acuerdoVenta.get(i).getCantidad();
					
					superAndes.actualizarCapacidadDisponibleBodega(bodega.getId(), cantidad);

					VOProductoBodega productoBodega = superAndes.adicionarProductoBodega(bodega.getId(), producto.getId(), acuerdoVenta.get(i).getCantidad());

					if (productoBodega == null)
					{
						throw new Exception ("No se pudo registrar el acuerdo de venta con id_producto");
					}
				}

				String resultado = "En finalizarOrdenPedido\n\n";
				resultado += "Orden de pedido finalizada exitosamente: " + idOrdenPedido;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	 }

	/* ****************************************************************
	 * 			APROVISIONAR UN ESTANTE DE PRODUCTOS 
	 *****************************************************************/
	public void Reabastecer()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para aprovisionar un estante de productos");
			}
			else
			{
				String [] opciones = {"BodegaId","EstanteId","Cantidad"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Aprovisionar Estante", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Aprovisionar Estente cancelado por el usuario");
						return;
					}
				}

				long bodegaId = Long.parseLong(respuestas[0]);
				long estanteId = Long.parseLong(respuestas[1]);
				int cantidad = Integer.parseInt(respuestas[2]);

	
				VOReabastecer reabastecer = superAndes.adicionarReabastecer(bodegaId, estanteId, cantidad);

				if (reabastecer == null)
				{
					throw new Exception ("No se pudo rebastecer el estante ");
				}

			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}
	
	/* ****************************************************************
	 * 			Registrar ProductoFactura
	 *****************************************************************/

	public void registrarProductoFactura()
	{
		try
		{
			String [] opciones = {"idProducto", "idFactura", "cantidad", "idPromocion (deje vacio si no aplica)"};
			String [] respuestas = new String [opciones.length];

			for (int i = 0; i < respuestas.length - 1; i++)
			{
				respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Producto Factura", JOptionPane.QUESTION_MESSAGE);
				if (respuestas[i] == null)
				{
					panelDatos.actualizarInterfaz("Registro de Producto Factura cancelado por el usuario");
					return;
				}
			}

			long idProducto = Long.parseLong(respuestas[0]);
			long idFactura = Long.parseLong(respuestas[1]);
			int cantidad = Integer.parseInt(respuestas[2]);
			Object idPromocion = respuestas[3];

			VOProductoFactura productoFactura = superAndes.adicionarProductoFactura(idProducto, idFactura, cantidad, idPromocion);

			if (productoFactura == null)
			{
				throw new Exception ("No se pudo registrar el productoFactura: " + idProducto);
			}

			String resultado = "En registrarProductoFactura\n\n";
			resultado += "Producto Factura registrado exitosamente: " + idProducto;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}
	/* ****************************************************************
	 * 			MOSTRAR NIVEL BODEGA
	 *****************************************************************/
	public void ocupacionBodegasEstantes()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar la llegada de un acuerdo de venta");
			}
			else
			{
				List<Bodega> bodega = superAndes.darBodegasPorSucursal((long) seguridad[1]);

				String resultado = "En ocupacionBodegasEstantes\n\n";

				for (int i = 0; i < bodega.size(); i++)
				{
					resultado += "\nBodega: " + bodega.get(i).getId() + "\n";
					resultado += "Capacidad: " + bodega.get(i).getCapacidadTotal() + "\n";
					resultado += "Capacidad Disponible: " + bodega.get(i).getCantidadDisponible() + "\n";
				}

				List<Estante> estante = superAndes.darEstantePorSucursal((long) seguridad[1]);

				for (int i = 0; i < estante.size(); i++)
				{
					resultado += "\nEstante: " + estante.get(i).getId() + "\n";
					resultado += "Capacidad: " + estante.get(i).getCapacidadTotal() + "\n";
					resultado += "Capacidad Disponible: " + estante.get(i).getAbastecimiento() + "\n";
					resultado += "Capacidad Disponible: " + estante.get(i).getCategoriaProducto() + "\n";
				}

				panelDatos.actualizarInterfaz(resultado);


			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			MOSTRAR PRODUCTO
	 *****************************************************************/

	public void mostrarProducto()
	{
		try
		{
			String marca = JOptionPane.showInputDialog (this, "Marca del producto", "Mostrar Producto", JOptionPane.QUESTION_MESSAGE);

			List<Producto> producto = superAndes.darProductosPorMarca(marca);

			String resultado = "En mostrarProducto\n\n";

			for (int i = 0; i < producto.size(); i++)
			{
				resultado += "\nProducto: " + producto.get(i).getId() + "\n";
				resultado += "Marca: " + producto.get(i).getMarca() + "\n";
				resultado += "Nombre: " + producto.get(i).getNombre() + "\n";
				resultado += "Presentacion: " + producto.get(i).getPresentacion() + "\n";
				resultado += "Categoria producto: " + producto.get(i).getIdCategoriaProducto() + "\n";
				resultado += "Precio unitario: " + producto.get(i).getPrecioUnitario() + "\n";
				resultado += "Precio por unidad de empaque: " + producto.get(i).getPrecioUnitario() + "\n";
				
			}

			panelDatos.actualizarInterfaz(resultado);

		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR PROMOCION
	 *****************************************************************/

	public void registrarPromocion()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una promoción");
			}
			else
			{
				String [] opciones = {"IdProducto", "FechaFin", "TipoPromocion","Promocion"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Promocion", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Promocion cancelado por el usuario");
						return;
					}
				}

				long idProducto = Long.parseLong(respuestas[0]);
				String fechaFin = respuestas[1];
				String tipoPromocion = respuestas[2];
				String promocion = respuestas[3];
				long idSucursal = (long) seguridad[1];

				VOPromocion promocionV = superAndes.adicionarPromocion(idProducto, fechaFin, tipoPromocion,promocion, idSucursal);

				if (promocionV == null)
				{
					throw new Exception ("No se pudo registrar la promoción: " + promocion  );
				}

				String resultado = "En registrarPromocion\n\n";
				resultado += "Promocion registrada exitosamente: " + promocion;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	
	/* ****************************************************************
	 * 			FINALIZAR PROMOCION
	 *****************************************************************/

	public void finalizarPromocion()
	{
		try
		{

		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR PRODUCTO
	 *****************************************************************/

	public void registrarProducto()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una promoción");
			}
			else
			{
				String [] opciones = {"nombre", "marca", "presentacion", "idCategoriaProducto", "precio unitario (numero)", "cantidad presentacion (numero)", "volumen empaque (numero)", "peso empaque (numero)", "precio por unidad (numero)"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Producto cancelado por el usuario");
						return;
					}
				}

				String nombre = respuestas[0];
				String marca = respuestas[1];
				String presentacion = respuestas[2];
				long idCategoriaProducto = Long.parseLong(respuestas[3]);
				int precioUnitario = Integer.parseInt(respuestas[4]);
				int cantidadPresentacion = Integer.parseInt(respuestas[5]);
				int volumenEmpaque = Integer.parseInt(respuestas[6]);
				int pesoEmpaque = Integer.parseInt(respuestas[7]);
				int precioUnidad = Integer.parseInt(respuestas[8]);
				long idSucursal = (long) seguridad[1];

				VOProducto producto = superAndes.adicionarProducto(nombre, marca, presentacion, idCategoriaProducto, precioUnitario, cantidadPresentacion, volumenEmpaque, pesoEmpaque, precioUnidad);

				if (producto == null)
				{
					throw new Exception ("No se pudo registrar el producto: " + nombre);
				}

				VOProductoSucursal productoSucursal = superAndes.adicionarProductoSucursal(producto.getId(), idSucursal);

				if (productoSucursal == null)
				{
					throw new Exception ("No se pudo registrar el productoSucursal: " + nombre);
				}

				String resultado = "En registrarProducto\n\n";
				resultado += "Producto registrado exitosamente: " + nombre;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR CATEGORIA_PRODUCTO
	 *****************************************************************/

	public void registrarCategoriaProducto()
	{
		try
		{
			Object [] seguridad = validarGerenteSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una promoción");
			}
			else
			{
				String tipo = JOptionPane.showInputDialog (this, "tipo", "Registrar Categoria Producto", JOptionPane.QUESTION_MESSAGE);

				if (tipo == null)
				{
					panelDatos.actualizarInterfaz("Registro de Categoria Producto cancelado por el usuario");
					return;
				}

				VOCategoriaProducto categoriaProducto = superAndes.adicionarCategoriaProducto(tipo);

				if (categoriaProducto == null)
				{
					throw new Exception ("No se pudo registrar la categoria de producto: " + tipo  );
				}

				VOCategoriaProductoSucursal categoriaProductoSucursal = superAndes.adicionarCategoriaProductoSucursal(categoriaProducto.getId(), (long) seguridad[1]);

				if (categoriaProductoSucursal == null)
				{
					throw new Exception ("No se pudo registrar la categoria de producto sucursal: " + tipo  );
				}

				String resultado = "En registrarCategoriaProducto\n\n";
				resultado += "Categoria de producto registrada exitosamente: " + tipo;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR FACTURA
	 *****************************************************************/

	public void registrarFactura()
	{
		try
		{
			Object [] seguridad = validarCajeroSucursal();

			if (seguridad[0].equals(false))
			{
				panelDatos.actualizarInterfaz("No tiene permisos para registrar una factura");
			}
			else
			{
				String [] opciones = {"idCliente", "subTotal", "total a pagar", "puntos", "fecha"};
				String [] respuestas = new String [opciones.length];

				for (int i = 0; i < respuestas.length; i++)
				{
					respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Factura", JOptionPane.QUESTION_MESSAGE);
					if (respuestas[i] == null)
					{
						panelDatos.actualizarInterfaz("Registro de Factura cancelado por el usuario");
						return;
					}
				}

				long idCliente = Long.parseLong(respuestas[0]);
				int subTotal = Integer.parseInt(respuestas[1]);
				int totalAPagar = Integer.parseInt(respuestas[2]);
				int puntos = Integer.parseInt(respuestas[3]);
				String fecha = respuestas[4];
				long idSucursal = (long) seguridad[1];

				VOFactura factura = superAndes.adicionarFactura(idCliente, idSucursal, subTotal, totalAPagar, puntos, fecha);

				if (factura == null)
				{
					throw new Exception ("No se pudo registrar la factura: " + idCliente);
				}

				VOClienteSucursal clienteSucursal = superAndes.darClienteSucursalPorId(idCliente, idSucursal);

				if (clienteSucursal == null)
				{
					VOClienteSucursal nuevoClienteSucursal = superAndes.adicionarClienteSucursal(idCliente, idSucursal);

					if (nuevoClienteSucursal == null)
					{
						throw new Exception ("No se pudo registrar el clienteSucursal: " + idCliente);
					}
				}

				registrarProductoFactura(factura.getId(), idSucursal);

				String productos = JOptionPane.showInputDialog (this, "Registrar otro producto (si / no)", "Registrar Producto Factura", JOptionPane.QUESTION_MESSAGE);
				while (productos.equals("si"))
				{
					registrarProductoFactura(factura.getId(), idSucursal);
					productos = JOptionPane.showInputDialog (this, "Registrar otro producto (si / no)", "Registrar Producto Factura", JOptionPane.QUESTION_MESSAGE);
				}

				String resultado = "En registrarFactura\n\n";
				resultado += "Factura registrada exitosamente: " + idCliente;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}

	/* ****************************************************************
	 * 			REGISTRAR PRODUCTO_FACTURA
	 *****************************************************************/

	public void registrarProductoFactura(long idFactura, long idSucursal)
	{
		try
		{
			String [] opciones = {"idProducto", "cantidad", "idPromocion (deje vacio si no aplica)"};
			String [] respuestas = new String [opciones.length];

			for (int i = 0; i < respuestas.length - 1; i++)
			{
				respuestas[i] = JOptionPane.showInputDialog (this, opciones[i], "Registrar Producto Factura", JOptionPane.QUESTION_MESSAGE);
				if (respuestas[i] == null)
				{
					panelDatos.actualizarInterfaz("Registro de Producto Factura cancelado por el usuario");
					return;
				}
			}

			long idProducto = Long.parseLong(respuestas[0]);
			int cantidad = Integer.parseInt(respuestas[1]);
			Object idPromocion = respuestas[2];


			VOProductoFactura productoFactura = superAndes.adicionarProductoFactura(idFactura, idProducto, cantidad, idPromocion);

			if (productoFactura == null)
			{
				throw new Exception ("No se pudo registrar el productoFactura: " + idProducto);
			}

			VOProducto producto = superAndes.darProductoPorId(idProducto);

			VOBodega bodega = superAndes.darBodegaIdCategoriaIdSucursal(producto.getIdCategoriaProducto(), idSucursal);

			VOProductoBodega productoBodega = superAndes.darProductoBodegaPorId(bodega.getId(), idProducto);

			int cantidadDisponible = bodega.getCantidadDisponible() + cantidad;

			int cantidadProductoBodega = productoBodega.getCantidad() - cantidad;

			superAndes.actualizarCapacidadDisponibleBodega(bodega.getId(), cantidadDisponible);

			superAndes.actualizarCantidadProductoBodega(bodega.getId(), idProducto, cantidadProductoBodega);

			String resultado = "En registrarProductoFactura\n\n";
			resultado += "Producto Factura registrado exitosamente: " + idProducto;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch (Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);			
		}
	}


	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/

	 /**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("parranderos.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + "Eliminados\n";
			resultado += eliminados [1] + "Eliminados\n";
			resultado += eliminados [2] + "Eliminados\n";
			resultado += eliminados [3] + "Eliminados\n";
			resultado += eliminados [4] + "Eliminados\n";
			resultado += eliminados [5] + "Eliminados\n";
			resultado += eliminados [6] + "Eliminados\n";
			resultado += eliminados [7] + "Eliminados\n";
			resultado += eliminados [8] + "Eliminados\n";
			resultado += eliminados [9] + "Eliminados\n";
			resultado += eliminados [10] + "Eliminados\n";
			resultado += eliminados [11] + "Eliminados\n";
			resultado += eliminados [12] + "Eliminados\n";
			resultado += eliminados [13] + "Eliminados\n";
			resultado += eliminados [14] + "Eliminados\n";
			resultado += eliminados [15] + "Eliminados\n";
			resultado += eliminados [16] + "Eliminados\n";
			resultado += eliminados [17] + "Eliminados\n";
			resultado += eliminados [18] + "Eliminados\n";
			resultado += eliminados [19] + "Eliminados\n";
			resultado += eliminados [20] + "Eliminados\n";
			resultado += eliminados [21] + "Eliminados\n";
			resultado += eliminados [22] + "Eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaSuperAndes.sql");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
