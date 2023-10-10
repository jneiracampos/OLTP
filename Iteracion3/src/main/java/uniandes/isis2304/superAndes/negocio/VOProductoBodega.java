package uniandes.isis2304.superAndes.negocio;

public interface VOProductoBodega 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
    public long getIdBodega();
    
    public long getIdProducto();

    public int getCantidad();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();

}
