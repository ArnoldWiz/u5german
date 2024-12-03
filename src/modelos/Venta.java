package modelos;

/**
 * @author barre
 */
public class Venta {
    
    //se declaran los atibutos
    
    
    private int idventa;
    private double valorPagar;
    private int idUsuario;
    private int idCliente;
    private String fechaVenta;
    private int estado;
    
    
    //constructores
    public Venta(){
        this.idventa = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    public Venta(int idventa, double valorPagar, int idUsuario, int idCliente, String fechaVenta, int estado) {
        this.idventa = idventa;
        this.valorPagar = valorPagar;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    // getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idCabeceraventa) {
        this.idventa = idCabeceraventa;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idventa + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
}
