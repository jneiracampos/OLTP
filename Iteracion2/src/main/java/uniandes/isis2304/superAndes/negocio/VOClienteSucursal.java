package uniandes.isis2304.superAndes.negocio;

public interface VOClienteSucursal {

    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
     /**
     * @return El idSucursal del clienteSucursal
     */
    public long getIdSucursal();

    /**
     * @return El idCliente del clienteSucursal
     */
    public long getIdCliente();

    /**
     * @return Una cadena de caracteres con todos los atributos del clienteSucursal
     */
    @Override
    public String toString();

}
