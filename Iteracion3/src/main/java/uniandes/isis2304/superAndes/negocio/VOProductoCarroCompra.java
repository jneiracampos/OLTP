package uniandes.isis2304.superAndes.negocio;

public interface VOProductoCarroCompra 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
    
    public long getIdCarroCompra();
    
    public long getIdProducto();

    public int getCantidad();

    @Override
    public String toString();
}
