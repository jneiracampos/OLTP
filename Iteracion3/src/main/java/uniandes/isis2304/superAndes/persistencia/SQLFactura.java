package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.Factura;

public class SQLFactura {

    private final static String SQL = PersistenciaSuperAndes.SQL;
    
    private PersistenciaSuperAndes psa;
    
    public SQLFactura(PersistenciaSuperAndes psa) {
        this.psa = psa;
    }
    
    public long adicionarFactura(PersistenceManager pm, long id, long idCliente, long idSucursal, int subTotal, int totalPagar, String fecha) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFactura() + " (id, idcliente, idsucursal, subtotal, total_pagar, fecha) values (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))");
        q.setParameters(id, idCliente, idSucursal, subTotal, totalPagar, fecha);
        return (long) q.executeUnique();
    }
    
    public long eliminarFacturaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }
    
    public Factura darFacturaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura() + " WHERE id = ?");
        q.setResultClass(Factura.class);
        q.setParameters(id);
        return (Factura) q.executeUnique();
    }
    
    public List<Factura> darFacturas(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura());
        q.setResultClass(Factura.class);
        return (List<Factura>) q.executeList();
    }

    public void actualizarSubtotal(PersistenceManager pm, long idFacura, int subTotal) {
        Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaFactura() + " SET subtotal = ?, total_pagar = ? WHERE id = ?");
        int total = (int) (subTotal + (subTotal * 0.2));
        q.setParameters(subTotal, total, idFacura);
        q.executeUnique();
    }
    
}
