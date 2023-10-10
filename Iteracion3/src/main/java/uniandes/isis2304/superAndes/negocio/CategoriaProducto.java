package uniandes.isis2304.superAndes.negocio;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class CategoriaProducto implements VOCategoriaProducto {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de la categoriaProducto
     *
	 */
	private long id;

	/**
	 * El tipo de la categoriaProducto
	 */
	private String tipo;

    /* ****************************************************************
     * 			Constantes
     *****************************************************************/

    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    public final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public CategoriaProducto() 
	{
		this.id = 0;
		this.tipo = "Default";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador de la categoriaProducto
     *
	 * @param tipo - El tipo de la categoriaProducto
	 * @return 
     *
	 */
	public CategoriaProducto(long id, String tipo) 
	{
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * @return El id de la categoriaProducto
     *
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id de la categoriaProducto
     *
	 */
	public void setId(long id) 
	{
		this.id = id;
	}


	/**
	 * @return Una cadena de caracteres con la información de la categoriaProducto
     *
	 */
	@Override
	public String toString() 
	{
		return "TipoCategoria [id=" + id + ", tipo=" + tipo + "]";
	}

	/**
	 * @param tipo - El TipoCategoriaProducto a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		CategoriaProducto tb = (CategoriaProducto) tipo;
		return id == tb.id && ((String) tipo).equalsIgnoreCase (tb.tipo);
	}

    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
