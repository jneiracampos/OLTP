package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PRODUCTO_CARRRO_COMPRA del negocio de los AforaAndes
 */
public class ProductoCarroCompra implements VOProductoCarroCompra
{
    /* ****************************************************************
	* 			Atributos
	******************************************************************/

    /**
    * El identificador ÚNICO de los carros de compra
    */
    private long id_carro_compra;

    /**
     * El identificador ÚNICO del producto
     */
    private long id_producto;

    /*
     * Cantidades de productos
     */
    private int cantidad;

    /* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
    public ProductoCarroCompra()
    {
        this.id_carro_compra = 0;
        this.id_producto = 0;
        this.cantidad = 0;
    }

    /**
     * Constructor con valores
     * @param id_carro_compra - El identificador del carro de compra
     * @param id_producto - El identificador del producto
     */
    public ProductoCarroCompra(long id_carro_compra, long id_producto, int cantidad)
    {
        this.id_carro_compra = id_carro_compra;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public long getIdCarroCompra() {
        return id_carro_compra;
    }

    public void setId_carro_compra(long id_carro_compra) {
        this.id_carro_compra = id_carro_compra;
    }

    public long getIdProducto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
	public String toString() 
	{
		return "ProductoCarroCompra [id_carro_compra=" + id_carro_compra + ", id_producto=" + id_producto + "]";
	}

}
