package uniandes.isis2304.superAndes.negocio;

import java.time.LocalDate;

public interface VOFactura {

    public long getId();

    public long getIdCliente();

    public long getIdSucursal();

    public int getSubTotal();

    public int getTotalPagar();

    public LocalDate getFecha();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del productoSucursal
     */
    public String toString();

}
