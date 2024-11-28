package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Producto;

/**
 *
 * @author barre
 */
public class DaoProducto {

    public boolean guardar(Producto objeto, String categoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        DaoCategoria daoC = new DaoCategoria();
        try {
            cn.setAutoCommit(false);

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO producto VALUES(?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);  // id
            consulta.setString(3, objeto.getNombre());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecio());
            consulta.setString(6, objeto.getDescripcion());
            consulta.setInt(9, objeto.getPorcentajeIva());
            consulta.setInt(7, daoC.IdCategoria(categoria));
            consulta.setInt(8, objeto.getEstado());
            consulta.setInt(2, objeto.getCodigo());
            consulta.setInt(10, objeto.getMinimo());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
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

    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "SELECT codigo FROM producto WHERE codigo = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, producto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    public boolean existeProductoCodigo(String codigo) {
        boolean respuesta = false;
        String sql = "SELECT nombre FROM producto WHERE codigo = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE codigo = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    int codigoProducto = rs.getInt("codigo");
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");
                    String descripcion = rs.getString("descripcion");
                    int porcentajeIva = rs.getInt("iva");
                    int idCategoria = rs.getInt("idCategoria");
                    int estado = rs.getInt("estado");
                    int minimo = rs.getInt("minimo");

                    producto = new Producto(idProducto, codigoProducto, nombre, cantidad, precio, descripcion,
                            porcentajeIva, idCategoria, estado, minimo);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto por código: " + e);
        }
        return producto;
    }

    public boolean actualizar(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            cn.setAutoCommit(false);

            PreparedStatement consulta = cn.prepareStatement("UPDATE producto SET nombre=?, cantidad=?, precio=?, descripcion=?, idCategoria=?, estado=? WHERE idProducto = ?");
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getIdCategoria());
            consulta.setInt(6, objeto.getEstado());
            consulta.setInt(7, idProducto);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
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

    public boolean desactivarProductoPorCodigo(int codigo) {
        boolean actualizado = false;
        String sql = "UPDATE producto SET estado = 0 WHERE codigo = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, codigo); // Se establece el código como parámetro
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del producto: " + e);
        }

        return actualizado;
    }

}
