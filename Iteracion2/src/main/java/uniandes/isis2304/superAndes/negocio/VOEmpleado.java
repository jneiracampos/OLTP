package uniandes.isis2304.superAndes.negocio;

public interface VOEmpleado {

    /* ****************************************************************
     * 			Métodos 
     *****************************************************************/
     /**
     * @return El id del empleado
     */
    public long getId();

    /**
     * @return El nombre del empleado
     */
    public String getNombre();

    /**
     * @return El idSucursal del empleado
     */
    public long getSucursal();

    /**
     * @return Una cadena de caracteres con todos los atributos del empleado
     */
    @Override
    public String toString();

}
