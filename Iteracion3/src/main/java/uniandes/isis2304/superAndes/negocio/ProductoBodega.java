package uniandes.isis2304.superAndes.negocio;

public class ProductoBodega implements VOProductoBodega
{
    /****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long idBodega;

	private long idProducto;

    private int cantidad;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
    public ProductoBodega()
    {
        this.idBodega = 0;
        this.idProducto = 0;
        this.cantidad = 0;
    }

    /**
     * Constructor con valores
     */
    public ProductoBodega(long idBodega, long idProducto, int cantidad)
    {
        this.idBodega = idBodega;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del clienteSucursal
	 */
	public String toString() 
    {
        return "ProductoBodega [idBodega=" + idBodega + ", idProducto=" + idProducto + "]";
    }

}
