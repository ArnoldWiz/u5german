package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Usuario;

/**
 *
 * @author barre
 */
public class DaoUsuario {

    // metodo para guardar usuario 
    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        String sql = "INSERT INTO usuario (nombre, apellido, usuario, password, telefono, tipo) "
                   + "VALUES(?, ?, ?, SHA2(?, 256), ?, ?)";
        
        try (Connection cn = Conexion.conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement consulta = cn.prepareStatement(sql)) {
                consulta.setString(1, objeto.getNombre());
                consulta.setString(2, objeto.getApellido());
                consulta.setString(3, objeto.getUsuario());
                consulta.setString(4, objeto.getPassword());
                consulta.setString(5, objeto.getTelefono());
                consulta.setInt(6, objeto.getTipo());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                System.out.println("Error al guardar usuario: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e);
        }
        
        return respuesta;
    }

    // metodo para comprobar
    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "SELECT 1 FROM usuario WHERE usuario = ?";
        
        try (Connection cn = Conexion.conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement st = cn.prepareStatement(sql)) {
                st.setString(1, usuario);
                ResultSet rs = st.executeQuery();
                respuesta = rs.next();
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                System.out.println("Error al consultar usuario: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e);
        }
        
        return respuesta;
    }

    // metodo para  actualizar
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, usuario = ?, password = SHA2(?, 256), telefono = ?, tipo = ? "
                   + "WHERE idUsuario = ?";
        
        try (Connection cn = Conexion.conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement consulta = cn.prepareStatement(sql)) {
                consulta.setString(1, objeto.getNombre());
                consulta.setString(2, objeto.getApellido());
                consulta.setString(3, objeto.getUsuario());
                consulta.setString(4, objeto.getPassword());
                consulta.setString(5, objeto.getTelefono());
                consulta.setInt(6, objeto.getTipo());
                consulta.setInt(7, idUsuario);

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                System.out.println("Error al actualizar usuario: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e);
        }
        
        return respuesta;
    }

    // metodo para eliminar usuario
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        
        try (Connection cn = Conexion.conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement consulta = cn.prepareStatement(sql)) {
                consulta.setInt(1, idUsuario);
                
                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                System.out.println("Error al eliminar usuario: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e);
        }
        
        return respuesta;
    }

    // metodo para  buscar usuario por nombre
    public Usuario buscarUsuario(String usuario) {
        Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        
        try (Connection cn = Conexion.conectar()) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, usuario);
                ResultSet rs = ps.executeQuery();

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
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        
        return usuarioEncontrado;
    }

    // metodo para iniciar sesion
    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        String sql = "SELECT usuario, password FROM usuario WHERE usuario = ? AND password = SHA2(?, 256)";
        
        try (Connection cn = Conexion.conectar()) {
            try (PreparedStatement pst = cn.prepareStatement(sql)) {
                pst.setString(1, objeto.getUsuario());
                pst.setString(2, objeto.getPassword());
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion: " + e.getMessage());
        }

        return respuesta;
    }

    // metodo para buiscar usuario
    public Usuario recuperarUs(Usuario objeto) {
        try (Connection cn = Conexion.conectar()) {
            String sql = "SELECT tipo FROM usuario WHERE usuario = ? AND password = SHA2(?, 256)";
            
            try (PreparedStatement pst = cn.prepareStatement(sql)) {
                pst.setString(1, objeto.getUsuario());
                pst.setString(2, objeto.getPassword());
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    objeto.setTipo(rs.getInt("tipo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar datos del usuario: " + e.getMessage());
        }

        return objeto;
    }
}
