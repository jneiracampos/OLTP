package uniandes.isis2304.superAndes.negocio;


public class Factura implements VOFactura {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;

	private long idCliente;

    private long idSucursal;

	private int subTotal;
	
	private int totalPagar;

    private int puntos;

    private String fecha;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    public Factura() {
        this.id = 0;
        this.idCliente = 0;
        this.idSucursal = 0;
        this.subTotal = 0;
        this.totalPagar = 0;
        this.puntos = 0;
        this.fecha = "";
    }

    public Factura(long id, long idCliente, long idSucursal, int subTotal, int totalPagar, int puntos, String fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.subTotal = subTotal;
        this.totalPagar = totalPagar;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(int totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del estante
	 */
	public String toString() 
	{
        return "Factura [id=" + id + ", idCliente=" + idCliente + ", idSucursal=" + idSucursal + ", subTotal=" + subTotal + ", totalPagar=" + totalPagar + ", puntos=" + puntos + ", fecha=" + fecha + "]";
    }
	
}
