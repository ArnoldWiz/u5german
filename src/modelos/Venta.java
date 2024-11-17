package modelos;

/**
 * @author barre
 */
public class Venta {
    private int idventa;
    private double valorPagar;
    private String fechaVenta;
    private int estado;
    
    public Venta(){
        this.idventa = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    public Venta(int idCabeceraventa, double valorPagar, String fechaVenta, int estado) {
        this.idventa = idCabeceraventa;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
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
