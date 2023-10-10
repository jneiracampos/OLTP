package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Empleado;

public class SQLEmpleado {
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
        public SQLEmpleado (PersistenciaSuperAndes pp)
        {
            this.pp = pp;
        }
        
        /**
         * Crea y ejecuta la sentencia SQL para adicionar un empleado a la base de datos de SuperAndes
         * @param pm - El manejador de persistencia
         * @return El número de tuplas insertadas
         */
        public long adicionarEmpleado (PersistenceManager pm, long id, String nombre, String correo, long sucursal) 
        {
            Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleado() + " values (?, ?, ?,?)");
            q.setParameters(id, nombre, correo, sucursal);
            return (long) q.executeUnique();
        }
    
        /**
         * Crea y ejecuta la sentencia SQL para elimina empleado de la base de datos de SuperAndes, por su nombre
         * @param pm - El manejador de persistencia
         * @param nombreEmpleado - El nombre de un empleado
         * @return EL número de tuplas eliminadas
         */
        public long eliminarEmpleadoPorNombre (PersistenceManager pm, String nombreEmpleado)
        {
            Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado () + " WHERE nombre = ?");
            q.setParameters(nombreEmpleado);
            return (long) q.executeUnique();
        }
    
        /**
         * Crea y ejecuta la sentencia SQL para eliminar UN empleado de la base de datos de SuperAndes, por su identificador
         * @param pm - El manejador de persistencia
         * @param id - El identificador de un empleado
         * @return EL número de tuplas eliminadas
         */
        public long eliminarEmpleadoPorId (PersistenceManager pm, long id)
        {
            Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado () + " WHERE id = ?");
            q.setParameters(id);
            return (long) q.executeUnique();
        }
    
        /**
         * Crea y ejecuta la sentencia SQL para encontrar la información de UN empleado de la 
         * base de datos de SuperAndes, por su identificador
         * @param pm - El manejador de persistencia
         * @param id - El identificador de un empleado
         * @return El objet empleado que tiene el identificador dado
         */
        public Empleado darEmpleadoPorId (PersistenceManager pm, long id) 
        {
            Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado () + " WHERE id = ?");
            q.setResultClass(Empleado.class);
            q.setParameters(id);
            return (Empleado) q.executeUnique();
        }
    
        /**
         * Crea y ejecuta la SuperAndes SQL para encontrar la información de LAS SUCURSALES de la 
         * base de datos de Parranderos, por su nombre
         * @param pm - El manejador de persistencia
         * @param nombreEmpleado - El nombre de un empleado buscado
         * @return Una lista de objeto empleado que tienen el nombre dado
         */
        public List<Empleado> darEmpleadosPorNombre (PersistenceManager pm, String nombreEmpleado) 
        {
            Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado () + " WHERE nombre = ?");
            q.setResultClass(Empleado.class);
            q.setParameters(nombreEmpleado);
            return (List<Empleado>) q.executeList();
        }
    
        /**
         * Crea y ejecuta la sentencia SQL para encontrar la información de LAS SUCURSALES de la 
         * base de datos de SuperAndes
         * @param pm - El manejador de persistencia
         * @return Una lista de objeto empleado
         */
        public List<Empleado> darEmpleados (PersistenceManager pm)
        {
            Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado ());
            q.setResultClass(Empleado.class);
            return (List<Empleado>) q.executeList();
        }

        public List<Empleado> darEmpleadoPorNombre(PersistenceManager persistenceManager, String nombreEmpleado) {
            return null;
        }
        
}
