package uniandes.isis2304.superAndes.negocio;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;


public class CategoriaProductoSucursal implements VOCategoriaProductoSucursal {
    
    private long idSucursal;

    private long idCategoria;

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
    public CategoriaProductoSucursal()
    {
        this.idSucursal = 0;
        this.idCategoria = 0;
    }

    /**
     * Constructor con valores
     * @param idSucursal - El identificador de la sucursal
     * @param idCategoria - El identificador de la categoria
     */
    public CategoriaProductoSucursal(long idSucursal, long idCategoria)
    {
        this.idSucursal = idSucursal;
        this.idCategoria = idCategoria;
    }

    /**
     * @return El id de la sucursal
     */
    public long getIdSucursal()
    {
        return idSucursal;
    }

    /**
     * @param idSucursal - El nuevo id de la sucursal
     */
    public void setIdSucursal(long idSucursal)
    {
        this.idSucursal = idSucursal;
    }

    /**
     * @return El id de la categoria
     */
    public long getIdCategoria()
    {
        return idCategoria;
    }

    /**
     * @param idCategoria - El nuevo id de la categoria
     */
    public void setIdCategoria(long idCategoria)
    {
        this.idCategoria = idCategoria;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos de la categoriaProductoSucursal
     */
    @Override
    public String toString()
    {
        return "CategoriaProductoSucursal [idSucursal=" + idSucursal + ", idCategoria=" + idCategoria + "]";
    }
    
}
