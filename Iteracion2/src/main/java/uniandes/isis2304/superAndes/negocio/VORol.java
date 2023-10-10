package uniandes.isis2304.superAndes.negocio;

public interface VORol 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/ 
    public long getId();
    
    public String getNombre();
    
    public String getDescripcion();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();
}
