package uniandes.isis2304.superAndes.negocio;

public class Cliente implements VOCliente {

    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;

	private String direccion;

    private String correo;

    private String palabra_clave;

    private String tipo_cliente;

    private int puntos;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
   
     /**
     * Constructor por defecto
     */
    public Cliente()
    {
    	this.id = 0;
    	this.direccion = "";
    	this.correo = "";
        this.palabra_clave = "";
        this.tipo_cliente = ""; 
        this.puntos = 0;
    }

	/**
	 * Constructor con valores
	 */
    public Cliente(long idCliente, String direccion, String correo, String tipo_cliente, int puntos, String palabra_clave)
    {
    	this.id = idCliente;
    	this.direccion = direccion;
    	this.correo = correo;
        this.palabra_clave = palabra_clave;
        this.tipo_cliente = tipo_cliente; 
        this.puntos = puntos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoCliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getPalabraClave() {
        return palabra_clave;
    }

    public void setPalabra_clave(String palabra_clave) {
        this.palabra_clave = palabra_clave;
    }

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del clienteSucursal
	 */
	public String toString() 
	{
		return "ClienteSucursal [idCliente=" + id + ", direccion=" + direccion + ", correo=" + correo + ", tipo_cliente=" + tipo_cliente + "]";
	}
	
}
