package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
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
            consulta.setInt(1, 0);  
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

    public DefaultTableModel cargarTablaProductos() {
        Connection con = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT p.idProducto, p.codigo, p.nombre, p.cantidad, p.precio, p.descripcion, c.descripcion AS categoria, p.minimo "
                + "FROM producto p "
                + "JOIN categoria c ON p.idCategoria = c.idCategoria "
                + "WHERE p.estado = 1"; 
        Statement st;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            model.addColumn("idProducto");
            model.addColumn("Codigo");
            model.addColumn("Nombre");
            model.addColumn("Cantidad");
            model.addColumn("Precio");
            model.addColumn("Descripcion");
            model.addColumn("Categoria");
            model.addColumn("Minimo");

            while (rs.next()) {
                Object fila[] = new Object[8];  
                fila[0] = rs.getObject(1);  
                fila[1] = rs.getObject(2);  
                fila[2] = rs.getObject(3);  
                fila[3] = rs.getObject(4);  
                fila[4] = rs.getObject(5);  
                fila[5] = rs.getObject(6); 
                fila[6] = rs.getObject(7);  
                fila[7] = rs.getObject(8);  

                model.addRow(fila);
            }

            con.commit();
            con.close();
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex);
            }
            System.out.println("Error al llenar la tabla productos: " + e);
        }

        return model; 
    }
    
        public int obtenerStockProducto(int idProducto) {
        Connection cn = null;
        int stock = -1;
        try {
            cn = Conexion.conectar();
            String sql = "SELECT cantidad FROM producto WHERE idProducto = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("cantidad");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el stock del producto: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
        return stock;
    }
        
        public boolean actualizarStock(int idProducto, int nuevoStock) {
        Connection cn = null;
        try {
            cn = Conexion.conectar();
            
            String sql = "UPDATE producto SET cantidad = ? WHERE idProducto = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, nuevoStock); 
            pst.setInt(2, idProducto);

            int filasActualizadas = pst.executeUpdate();
            
            if (filasActualizadas > 0) {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar el stock: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false; 
    }

}
