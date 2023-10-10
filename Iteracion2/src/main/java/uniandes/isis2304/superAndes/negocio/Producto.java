package uniandes.isis2304.superAndes.negocio;


/**
 * Clase para modelar el concepto BEBIDA del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Producto implements VOProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del producto
	 */
	private long id;
	
	private String nombre;

	private String marca;
	
	private String presentacion;
	
	private long idCategoriaProducto; 
	
	private int precioUnitario;
	
	private int cantidadPresentacion; 
	
	private int volumenEmpaque; 
	
	private int pesoEmpaque;
	
	private int precioPorUnidad;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Producto() 
	{
		this.id = 0;
		this.nombre = "";
		this.marca = "";
		this.presentacion = "";
		this.idCategoriaProducto = 0;
		this.precioUnitario = 0;
	    this.cantidadPresentacion = 0;
	    this.volumenEmpaque = 0;
	    this.pesoEmpaque = 0;
        this.precioPorUnidad = 0;
	}

	/**
	 * Constructor con valores
	 */
	public Producto(long idProducto, String nombre, String marca, String presentacion, long idCategoriaProducto, int precioUnitario, int cantidadPresentacion, int volumenEmpaque, int pesoEmpaque, int precioPorUnidad) 
	{
        this.id = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.presentacion = presentacion;
        this.idCategoriaProducto = idCategoriaProducto;
        this.precioUnitario = precioUnitario;
        this.cantidadPresentacion = cantidadPresentacion;
        this.volumenEmpaque = volumenEmpaque;
        this.pesoEmpaque = pesoEmpaque;
        this.precioPorUnidad = precioPorUnidad;
	}



	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public long getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setID_CATEGORIA_PRODUCTO(long idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPRECIO_UNITARIO(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCANTIDAD_PRESENTACION(int cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public int getVolumenEmpaque() {
        return volumenEmpaque;
    }

    public void setVOLUMEN_EMPAQUE(int volumenEmpaque) {
        this.volumenEmpaque = volumenEmpaque;
    }

    public int getPesoEmpaque() {
        return pesoEmpaque;
    }

    public void setPESO_EMPAQUE(int pesoEmpaque) {
        this.pesoEmpaque = pesoEmpaque;
    }

    public int getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPRECIO_POR_UNIDAD(int precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    /**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString() 
	{
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", presentacion=" + presentacion + "]";
	}

}
