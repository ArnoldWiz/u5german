package daos;

import conexion.Conexion;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Venta;
import modelos.DetalleVenta;

/**
 * @author barre
 */
public class DaoRegistrarVenta {

    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

    // metodo para registrar la venta con sus detalles
    public boolean guardarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            cn.setAutoCommit(false);

            // Guardar cabecera de la venta
            PreparedStatement consultaVenta = cn.prepareStatement(
                    "INSERT INTO venta (idUsuario, idCliente, valorPagar, fechaVenta) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            consultaVenta.setInt(1, venta.getIdUsuario());
            consultaVenta.setInt(2, venta.getIdCliente());
            consultaVenta.setDouble(3, venta.getValorPagar());
            consultaVenta.setString(4, venta.getFechaVenta());

            if (consultaVenta.executeUpdate() > 0) {
                ResultSet rs = consultaVenta.getGeneratedKeys();
                int idCabeceraRegistrada = 0;

                if (rs.next()) {
                    idCabeceraRegistrada = rs.getInt(1); // Obtener idVenta generado
                }

                // Guardar detalles de la venta
                PreparedStatement consultaDetalle = cn.prepareStatement(
                        "INSERT INTO detalle_venta VALUES(?,?,?,?,?,?,?,16)"
                );

                for (DetalleVenta detalle : detalles) {
                    consultaDetalle.setInt(1, 0); // id
                    consultaDetalle.setInt(2, idCabeceraRegistrada); // idVenta
                    consultaDetalle.setInt(3, detalle.getIdProducto());
                    consultaDetalle.setInt(4, detalle.getCantidad());
                    consultaDetalle.setDouble(5, detalle.getPrecioUnitario());
                    consultaDetalle.setDouble(6, detalle.getSubTotal());
                    consultaDetalle.setDouble(7, detalle.getTotalPagar());

                    if (consultaDetalle.executeUpdate() <= 0) {
                        throw new SQLException("Error al guardar un detalle de venta.");
                    }
                }
                respuesta = true;
            }

            if (respuesta) {
                cn.commit();
            } else {
                cn.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar venta completa: " + e);
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error en el rollback: " + rollbackEx);
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex);
            }
        }
        return respuesta;
    }

}
