package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoBodega;

public class SQLProductoBodega 
{
    private final static String SQL = PersistenciaSuperAndes.SQL;
    
    private PersistenciaSuperAndes psa;
    
    public SQLProductoBodega(PersistenciaSuperAndes psa)
    {
        this.psa = psa;
    }
    
    public long adicionarProductoBodega(PersistenceManager pm, long idBodega, long idProducto, int cantidad)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoBodega() + " values (?, ?, ?)");
        q.setParameters(idBodega, idProducto, cantidad);
        return (long) q.executeUnique();
    }

    public ProductoBodega darProductoBodegaPorId(PersistenceManager pm, long idBodega, long idProducto)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoBodega() + " WHERE idbodega = ? AND idproducto = ?");
        q.setResultClass(ProductoBodega.class);
        q.setParameters(idBodega, idProducto);
        return (ProductoBodega) q.executeUnique();
    }

    public long eliminarProductoBodegaPorId(PersistenceManager pm, long idBodega, long idProducto)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoBodega() + " WHERE idBodega = ? AND idProducto = ?");
        q.setParameters(idBodega, idProducto);
        return (long) q.executeUnique();
    }
    
    public List<ProductoBodega> darProductosBodega(PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoBodega());
        q.setResultClass(ProductoBodega.class);
        return (List<ProductoBodega>) q.executeList();
    }

    public long actualizarCantidadProductoBodega(PersistenceManager pm, long idBodega, long idProducto, int cantidad)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoBodega() + " SET cantidad = ? WHERE idbodega = ? AND idproducto = ?");
        q.setParameters(cantidad, idBodega, idProducto);
        return (long) q.executeUnique();
    }


   
}
