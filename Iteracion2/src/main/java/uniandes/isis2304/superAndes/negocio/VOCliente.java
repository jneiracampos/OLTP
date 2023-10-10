package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de CLIENTE.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCliente 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
     /**
     * @return El id del cliente
     */
    public long getIdCliente();

    /**
     * @return La dirección del cliente
     */
    public String getDireccion();

    /**
     * @return El correo del cliente
     */
    public String getCorreo();

    /**
     * @return El tipo de cliente
     */
    public String getTipo_cliente();

    /**
     * @return Una cadena de caracteres con todos los atributos del cliente
     */
    @Override
    public String toString();
}
