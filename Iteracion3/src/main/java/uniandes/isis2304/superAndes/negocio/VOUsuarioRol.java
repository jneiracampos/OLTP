package uniandes.isis2304.superAndes.negocio;

public interface VOUsuarioRol 
{
    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
    public long getIdUsuario();
    
    public long getIdRol();
    
    public Object getIdSucursal();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();
}
