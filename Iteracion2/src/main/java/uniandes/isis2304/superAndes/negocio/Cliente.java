package uniandes.isis2304.superAndes.negocio;

public class Cliente implements VOCliente {

    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long idCliente;

	private String direccion;

    private String correo;

    private String tipo_cliente;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
   
     /**
     * Constructor por defecto
     */
    public Cliente()
    {
    	this.idCliente = 0;
    	this.direccion = "";
    	this.correo = "";
        this.tipo_cliente = "";        
    }

	/**
	 * Constructor con valores
	 */
    public Cliente(long idCliente, String direccion, String correo, String tipo_cliente)
    {
    	this.idCliente = idCliente;
    	this.direccion = direccion;
    	this.correo = correo;
        this.tipo_cliente = tipo_cliente;        
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
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

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del clienteSucursal
	 */
	public String toString() 
	{
		return "ClienteSucursal [idCliente=" + idCliente + ", direccion=" + direccion + ", correo=" + correo + ", tipo_cliente=" + tipo_cliente + "]";
	}
	
}
