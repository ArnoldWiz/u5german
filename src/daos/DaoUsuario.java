package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Usuario;

/**
 *
 * @author barre
 */
public class DaoUsuario {

    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into "
                    + "usuario (nombre, apellido, usuario, password, telefono, tipo) "
                    + "values(?,?,?,SHA2(?, 256),?,?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getTipo());
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    
    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "select usuario from usuario where usuario = '" + usuario + "';";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }
    
    public Usuario buscarUsuario(String usuario) {
    Usuario usuarioEncontrado = null;
    String sql = "SELECT * FROM usuario WHERE usuario = ?";

    try {
        Connection cn = Conexion.conectar();
        PreparedStatement ps = cn.prepareStatement(sql);
        
        // Asigna el valor de 'usuario' al primer parámetro de la consulta
        ps.setString(1, usuario);
        
        ResultSet rs = ps.executeQuery();

        // Si el usuario existe, crear un objeto MUsuario con los valores obtenidos
        if (rs.next()) {
            int idUsuario = rs.getInt("idUsuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String usuarioBD = rs.getString("usuario");
            String password = rs.getString("password");
            String telefono = rs.getString("telefono");
            int tipo = rs.getInt("tipo");

            usuarioEncontrado = new Usuario(idUsuario, nombre, apellido, usuarioBD, password, telefono, tipo);
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar usuario: " + e);
    }

    return usuarioEncontrado; // Retorna el objeto MUsuario o null si no se encontró
}
    
    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "SELECT usuario, password FROM usuario WHERE usuario = ? AND password = SHA2(?, 256)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, objeto.getUsuario());
            pst.setString(2, objeto.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                respuesta = true;
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion: " + e.getMessage());
        }

        return respuesta;
    }
    
    public Usuario recuperarUs(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "SELECT tipo FROM usuario WHERE usuario = ? AND password = SHA2(?, 256)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, objeto.getUsuario());
            pst.setString(2, objeto.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                objeto.setTipo(rs.getInt("tipo"));
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion: " + e.getMessage());
        }

        return objeto;
    }
    
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update usuario set nombre=?, apellido = ?, usuario = ?, password= SHA2(?,256), telefono = ?,tipo=? where idUsuario ='" + idUsuario + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getTipo());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
        return respuesta;
    }

    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from usuario where idUsuario ='" + idUsuario + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }
        return respuesta;
    }
}
