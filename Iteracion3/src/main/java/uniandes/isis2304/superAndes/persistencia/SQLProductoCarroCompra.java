package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.ProductoCarroCompra;

public class SQLProductoCarroCompra 
{
    private final static String SQL = PersistenciaSuperAndes.SQL;
    
    private PersistenciaSuperAndes psa;
    
    public SQLProductoCarroCompra(PersistenciaSuperAndes psa)
    {
        this.psa = psa;
    }
    
    public long adicionarProductoCarroCompra(PersistenceManager pm, long idCarroCompra, long idProducto, int cantidad)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoCarroCompra() + " values (?, ?, ?)");
        q.setParameters(idCarroCompra, idProducto, cantidad);
        return (long) q.executeUnique();
    }

    public ProductoCarroCompra darProductoCarroCompraPorId(PersistenceManager pm, long idCarroCompra, long idProducto)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarroCompra() + " WHERE id_carro_compra = ? AND id_producto = ?");
        q.setResultClass(ProductoCarroCompra.class);
        q.setParameters(idCarroCompra, idProducto);
        return (ProductoCarroCompra) q.executeUnique();
    }

    public List<ProductoCarroCompra>darProductoCarroComprasPorIdCarroCompra(PersistenceManager pm, long idCarroCompra)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarroCompra() + " WHERE id_carro_compra = ? ");
        q.setResultClass(ProductoCarroCompra.class);
        q.setParameters(idCarroCompra);
        return (List<ProductoCarroCompra>) q.executeList();
    }

    public long eliminarProductoCarroCompraPorId(PersistenceManager pm, long idCarroCompra, long idProducto)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoCarroCompra() + " WHERE id_carro_compra = ? AND id_producto = ?");
        q.setParameters(idCarroCompra, idProducto);
        return (long) q.executeUnique();
    }

    public List<ProductoCarroCompra> darProductosCarroCompraPorIdCarroCompra(PersistenceManager pm, long idCarroCompra)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarroCompra() + " WHERE id_carro_compra = ?");
        q.setResultClass(ProductoCarroCompra.class);
        q.setParameters(idCarroCompra);
        return (List<ProductoCarroCompra>) q.executeList();
    }
    
    public List<ProductoCarroCompra> darProductosCarrosCompra(PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarroCompra());
        q.setResultClass(ProductoCarroCompra.class);
        return (List<ProductoCarroCompra>) q.executeList();
    }

    public void actualizarCantidadProductoCarroCompra(PersistenceManager pm, long idCarroCompra, long idProducto, int cantidad)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoCarroCompra() + " SET cantidad = ? WHERE id_carro_compra = ? AND id_producto = ?");
        q.setParameters(cantidad, idCarroCompra, idProducto);
        q.executeUnique();
    }

}
