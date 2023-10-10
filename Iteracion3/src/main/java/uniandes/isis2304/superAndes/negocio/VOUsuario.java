package uniandes.isis2304.superAndes.negocio;

public interface VOUsuario 
{

    public long getId();

    public String getNombre();

    public String getCorreo();

    public String getPalabraClave();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();
}
