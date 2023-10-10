package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.CategoriaProductoSucursal;

public class SQLCategoriaProductoSucursal 
{

    private final static String SQL = PersistenciaSuperAndes.SQL;

    private PersistenciaSuperAndes pp;

    public SQLCategoriaProductoSucursal (PersistenciaSuperAndes pp)
    {
        this.pp = pp;
    }

    public long adicionarCategoriaProductoSucursal (PersistenceManager pm, long idSucursal, long idCategoria)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCategoriaProductoSucursal() + " values (?, ?)");
        q.setParameters(idSucursal, idCategoria);
        return (long) q.executeUnique();
    }

    public long eliminarCategoriaProductoSucursalPorId (PersistenceManager pm, long idSucursal, long idCategoria)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoriaProductoSucursal() + " WHERE idSucursal = ? AND idCategoria = ?");
        q.setParameters(idSucursal, idCategoria);
        return (long) q.executeUnique();
    }

    public CategoriaProductoSucursal darCategoriaProductoSucursalPorId (PersistenceManager pm, long idSucursal, long idCategoria)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoriaProductoSucursal() + " WHERE idSucursal = ? AND idCategoria = ?");
        q.setResultClass(CategoriaProductoSucursal.class);
        q.setParameters(idSucursal, idCategoria);
        return (CategoriaProductoSucursal) q.executeUnique();
    }

    public List<CategoriaProductoSucursal> darCategoriasProductoSucursal (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoriaProductoSucursal());
        q.setResultClass(CategoriaProductoSucursal.class);
        return (List<CategoriaProductoSucursal>) q.executeList();
    }
   
}
