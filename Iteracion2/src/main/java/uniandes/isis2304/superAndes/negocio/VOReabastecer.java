package uniandes.isis2304.superAndes.negocio;

public interface VOReabastecer 
{
    /*****************************************************************
     * 			Métodos 
     *****************************************************************/
    
    /**
     * @return la cantidad del rebastecer
     */
    public int getCantidad();
    
    /**
     * @return El idSucursal del rebastecer
     */
    public long getIdBodega();
    
    /**
     * @return El idEstante del rebastecer
     */
    public long getIdEstante();
    
    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del rebastecer
     */
    public String toString();

}
