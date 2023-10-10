package uniandes.isis2304.superAndes.negocio;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class Bodega implements VOBodega {
        /*****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;

	private int capacidadTotal;

    private int cantidadDisponible;

    private long categoriaProducto;

    private long idSucursal;

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
    public Bodega()
    {
        this.id = 0;
        this.capacidadTotal = 0;
        this.cantidadDisponible = 0;
        this.categoriaProducto = 0;
        this.idSucursal = 0;
    }

    /**
     * Constructor con valores
     * @param id - El identificador de la bodega
     * @param capacidadTotal - La capacidad total de la bodega
     * @param cantidadDisponible - La cantidad disponible de la bodega
     * @param categoriaProducto - La categoria de producto de la bodega
     */
    public Bodega(long id, int capacidadTotal, int cantidadDisponible, long categoriaProducto, long idSucursal)
    {
        this.id = id;
        this.capacidadTotal = capacidadTotal;
        this.cantidadDisponible = cantidadDisponible;
        this.categoriaProducto = categoriaProducto;
        this.idSucursal = idSucursal;
    }

    /**
     * @return El id de la bodega
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id de la bodega
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return La capacidad total de la bodega
     */
    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    /**
     * @param capacidadTotal - La nueva capacidad total de la bodega
     */
    public void setCAPACIDAD_TOTAL(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    /**
     * @return La cantidad disponible de la bodega
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * @param cantidadDisponible - La nueva cantidad disponible de la bodega
     */
    public void setCAPACIDAD_DISPONIBLE(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * @return La categoria de producto de la bodega
     */
    public long getCategoriaProducto() {
        return categoriaProducto;
    }

    /**
     * @param categoriaProducto - La nueva categoria de producto de la bodega
     */
    public void setID_CATEGORIA_PRODUCTO(long categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    /**
     * @return El id de la sucursal de la bodega
     */
    public long getIdSucursal() {
        return idSucursal;
    }

    /**
     * @param idSucursal - El nuevo id de la sucursal de la bodega
     */
    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del acuerdo de venta
     */
    @Override
    public String toString()
    {
    	return "Bodega [id=" + id + ", capacidadTotal=" + capacidadTotal + ", cantidadDisponible=" + cantidadDisponible + ", categoriaProducto=" + categoriaProducto + ", idSucursal=" + idSucursal + "]";
    }

}
