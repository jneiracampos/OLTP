package uniandes.isis2304.superAndes.negocio;

public interface VOBodega {

    public long getId();

    public int getCapacidadTotal();

    public int getCantidadDisponible();

    public long getCategoriaProducto();

    public long getIdSucursal();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();

}
