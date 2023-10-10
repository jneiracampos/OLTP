package uniandes.isis2304.superAndes.negocio;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class Usuario implements VOUsuario
{
    /*****************************************************************
	 * 			Atributos
	 *****************************************************************/

     private long id;

    private String nombre;

    private String correo;

    private String palabra_clave;

    /*****************************************************************
     * 			Constantes
    *****************************************************************/

    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    public final static String SQL = PersistenciaSuperAndes.SQL;

    /*****************************************************************
	* 			Métodos
	*****************************************************************/

    /**
     * Constructor por defecto
     */
    public Usuario()
    {
        this.id = 0;
        this.nombre = "";
        this.correo = "";
        this.palabra_clave = "";
    }

    /**
     * Constructor con valores
     * @param id - El identificador del usuario
     * @param nombre - El nombre del usuario
     * @param correo - El correo del usuario
     * @param tipo - El tipo de usuario
     * @param palabraClave - La palabra clave del usuario
     */
    public Usuario(long id, String nombre, String correo, String palabra_clave)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.palabra_clave = palabra_clave;
    }

    /**
     * @return El id del usuario
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del usuario
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo - El nuevo correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return La palabra clave del usuario
     */
    public String getPalabraClave() {
        return palabra_clave;
    }

    /**
     * @param palabraClave - La nueva palabra clave del usuario
     */
    public void setPalabra_clave(String palabra_clave) {
        this.palabra_clave = palabra_clave;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del usuario
     */
    @Override
    public String toString() 
    {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", palabraClave=" + palabra_clave + "]";
    }

}
