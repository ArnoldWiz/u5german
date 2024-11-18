package daos;

import conexion.Conexion;
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

    public boolean guardar(Venta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            cn.setAutoCommit(false);
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO venta VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0);
            consulta.setDouble(2, objeto.getValorPagar());
            consulta.setString(3, objeto.getFechaVenta());

            if (consulta.executeUpdate() > 0) {
                ResultSet rs = consulta.getGeneratedKeys();
                while (rs.next()) {
                    iDColVar = rs.getBigDecimal(1);
                    idCabeceraRegistrada = iDColVar.intValue();
                }
                respuesta = true;
            }

            if (respuesta) {
                cn.commit();
            } else {
                cn.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
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

    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            cn.setAutoCommit(false);
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO detalle_venta VALUES(?,?,?,?,?,?,?,16)");
            consulta.setInt(1, 0); // id
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubTotal());
            consulta.setDouble(7, objeto.getTotalPagar());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            if (respuesta) {
                cn.commit();
            } else {
                cn.rollback(); 
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
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
