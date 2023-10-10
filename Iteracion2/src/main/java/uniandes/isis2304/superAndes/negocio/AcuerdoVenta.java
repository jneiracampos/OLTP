package uniandes.isis2304.superAndes.negocio;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class AcuerdoVenta implements VOAcuerdoVenta
{
    /*****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;

	private long producto;

    private int cantidad;

    private int precio;

    /*****************************************************************
     * 			Constantes
    *****************************************************************/

    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    public final static String SQL = PersistenciaSuperAndes.SQL;

    /*****************************************************************
	* 			Métodos
	*****************************************************************/

    /**
     * Constructor por defecto
     */
    public AcuerdoVenta()
    {
        this.id = 0;
        this.producto = 0;
        this.cantidad = 0;
        this.precio = 0;
    }

    /**
     * Constructor con valores
     * @param id - El identificador del acuerdo de venta
     * @param producto - El producto del acuerdo de venta
     * @param cantidad - La cantidad del acuerdo de venta
     * @param precio - El precio del acuerdo de venta
     */
    public AcuerdoVenta (long id, long producto, int cantidad, int precio)
    {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * @return El id del acuerdo de venta
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id - El nuevo id del acuerdo de venta
     */
    public void setID_ORDEN_PEDIDO(long id)
    {
        this.id = id;
    }

    /**
     * @return El producto del acuerdo de venta
     */
    public long getProducto()
    {
        return producto;
    }

    /**
     * @param producto - El nuevo producto del acuerdo de venta
     */
    public void setID_PRODUCTO(long producto)
    {
        this.producto = producto;
    }

    /**
     * @return La cantidad del acuerdo de venta
     */
    public int getCantidad()
    {
        return cantidad;
    }

    /**
     * @param cantidad - La nueva cantidad del acuerdo de venta
     */
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    /**
     * @return El precio del acuerdo de venta
     */
    public int getPrecio()
    {
        return precio;
    }

    /**
     * @param precio - El nuevo precio del acuerdo de venta
     */
    public void setPrecio(int precio)
    {
        this.precio = precio;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del acuerdo de venta
     */
    @Override
    public String toString()
    {
        return "AcuerdoVenta [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + ", precio=" + precio + "]";
    }

}
