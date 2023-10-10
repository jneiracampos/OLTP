package uniandes.isis2304.superAndes.negocio;

public interface VOBodega {

    public long getId();

    public int getCapacidadTotal();

    public int getCantidadDisponible();

    public long getCategoriaProducto();

    public long getIdSucursal();

    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    @Override
    public String toString();

}
