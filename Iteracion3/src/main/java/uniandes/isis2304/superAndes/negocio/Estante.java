package uniandes.isis2304.superAndes.negocio;

public class Estante implements VOEstante {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los estantes
	 */
	private long id;
	
	/**
	 * La capacidadTotal del estante
	 */
	private int capacidadTotal;

	/**
	 * La nivelAbastecimiento donde se encuentra el estante
	 */
	private int nivelAbastecimiento;
	
	/**
	 * El categoriaProducto del estante
	 */
	private int categoriaProducto;

	/**
	 * El idSucursal del estante
	 */
	private long idSucursal;

	/**
	 * La capacidad disponible del estante
	 */
	private int capacidadDisponible;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Estante() 
    {
    	this.id = 0;
		this.capacidadTotal = 0;
		this.nivelAbastecimiento = 0;
		this.categoriaProducto = 0;
		this.idSucursal = 0;
		this.capacidadDisponible = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del estantet
	 * @param capacidadTotal - El capacidadTotal del estante
	 * @param nivelAbastecimiento - La nivelAbastecimiento del estante
	 * @param categoriaProducto - El categoriaProducto del estante (ALTO, MEDIO, BAJO)

	 */
    public Estante(long id, int capacidadTotal, int nivelAbastecimiento, int categoriaProducto, long idSucursal, int capacidadDisponible) 
	{
		this.id = id;
		this.capacidadTotal = capacidadTotal;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.categoriaProducto = categoriaProducto;
		this.idSucursal = idSucursal;
		this.capacidadDisponible = capacidadDisponible;
	}

    /**
	 * @return El id del estante
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del estante
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return la capacidadTotal del estante
	 */
	public int getCapacidadTotal() 
	{
		return capacidadTotal;
	}
	
	/**
	 * @param capacidadTotal El nuevo capacidadTotal del estante
	 */
	public void setCAPACIDAD_TOTAL(int capacidadTotal) 
	{
		this.capacidadTotal = capacidadTotal;
	}
	
	/**
	 * @return la nivelAbastecimiento del estante
	 */
	public int getAbastecimiento() 
	{
		return nivelAbastecimiento;
	}
	
	/**
	 * @param nivelAbastecimiento - La nueva nivelAbastecimiento del estante
	 */
	public void setNIVEL_ABASTECIMIENTO(int nivelAbastecimiento) 
	{
		this.nivelAbastecimiento = nivelAbastecimiento;
	}
	
	/**
	 * @return El categoriaProducto del estante
	 */
	public int getCategoriaProducto() 
	{
		return categoriaProducto;
	}
	
	/**
	 * @param categoriaProducto - El nuevo categoriaProducto del estante (ALTO, MEDIO, BAJOO)
	 */
	public void setID_CATEGORIA_PRODUCTO(int categoriaProducto) 
	{
		this.categoriaProducto = categoriaProducto;
	}

	/**
	 * @return El idSucursal del estante
	 */
	public long getIdSucursal()
	{
		return idSucursal;
	}

	/**
	 * @param idSucursal - El nuevo idSucursal del estante
	 */
	public void setID_SUCURSAL(long idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	/**
	 * @return La capacidad disponible del estante
	 */
	public int getCapacidadDisponible()
	{
		return capacidadDisponible;
	}

	/**
	 * @param capacidadDisponible - La nueva capacidad disponible del estante
	 */
	public void setCAPACIDAD_DISPONIBLE(int capacidadDisponible)
	{
		this.capacidadDisponible = capacidadDisponible;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del estante
	 */
	public String toString() 
	{
		return "estante [id=" + id + ", capacidadTotal=" + capacidadTotal + ", nivelAbastecimiento=" + nivelAbastecimiento + ", categoriaProducto=" + categoriaProducto + ", idSucursal=" + idSucursal + "]";
	}
	
}
