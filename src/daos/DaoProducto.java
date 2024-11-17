package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Producto;

/**
 *
 * @author barre
 */
public class DaoProducto {
    
    public boolean guardar(Producto objeto,String categoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        DaoCategoria daoC = new DaoCategoria();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into producto values(?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
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

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }

        return respuesta;
    }

    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from producto where nombre = '" + producto + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }
    
    public boolean existeProductoCodigo(String codigo) {
        boolean respuesta = false;
        String sql = "select nombre from producto where codigo = '" + codigo + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE codigo = '" + codigo + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Si el producto existe, crear un objeto Producto con los valores obtenidos
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
                        porcentajeIva, idCategoria, estado,minimo);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto por código: " + e);
        }
        return producto; // Retorna el objeto Producto o null si no se encontró
    }

    public boolean actualizar(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update producto set nombre=?, cantidad = ?, precio = ?, descripcion= ?, idCategoria = ?, estado = ? where idProducto ='" + idProducto + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getIdCategoria());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }
}
