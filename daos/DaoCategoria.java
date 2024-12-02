package daos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelos.Categoria;

/**
 *
 * @author barre
 */
public class DaoCategoria {

    public JComboBox<String> CargarComboCategorias(JComboBox<String> jComboBox_categoria) {
        Connection cn = Conexion.conectar();
        String sql = "SELECT * FROM categoria";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            jComboBox_categoria.removeAllItems();
            jComboBox_categoria.addItem("Seleccione categoría:");

            while (rs.next()) {
                if (rs.getInt("estado") == 1) {
                    jComboBox_categoria.addItem(rs.getString("descripcion"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar categorías: " + e);
        }
        return jComboBox_categoria;
    }

    public int IdCategoria(String categoria) {
        int obtenerIdCategoriaCombo = 0;
        String sql = "SELECT idCategoria FROM categoria WHERE descripcion = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obtenerIdCategoriaCombo = rs.getInt("idCategoria");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ID de categoría: " + e);
        }
        return obtenerIdCategoriaCombo;
    }

    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        String sql = "INSERT INTO categoria (idCategoria, descripcion, estado) VALUES (NULL, ?, ?)";
        Connection cn = Conexion.conectar();

        try {
            cn.setAutoCommit(false);

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, objeto.getDescripcion());
                ps.setInt(2, objeto.getEstado());

                if (ps.executeUpdate() > 0) {
                    respuesta = true;
                }
            }

            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoría: " + e);
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error en rollback: " + rollbackEx);
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return respuesta;
    }
    
    public boolean guardar2(Categoria objeto) {
        boolean respuesta = false;
        String sql = "UPDATE categoria SET estado=1 where descripcion=?";
        Connection cn = Conexion.conectar();
        try {
            cn.setAutoCommit(false);

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, objeto.getDescripcion());

                if (ps.executeUpdate() > 0) {
                    respuesta = true;
                }
            }

            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoría: " + e);
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error en rollback: " + rollbackEx);
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return respuesta;
    }

    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "SELECT descripcion FROM categoria WHERE descripcion = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    respuesta = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de categoría: " + e);
        }
        return respuesta;
    }

    public Categoria obtenerCategoria(String descripcionCategoria) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE descripcion = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, descripcionCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idCategoria = rs.getInt("idCategoria");
                    String descripcion = rs.getString("descripcion");
                    int estado = rs.getInt("estado");

                    categoria = new Categoria(idCategoria, descripcion, estado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoría: " + e);
        }
        return categoria;
    }

    public String buscarDescripcionCategoria(int categoria) {
        String descripcionEncontrada = null;
        String sql = "SELECT descripcion FROM categoria WHERE idCategoria = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    descripcionEncontrada = rs.getString("descripcion");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar descripción de categoría: " + e);
        }
        return descripcionEncontrada;
    }

    public boolean actualizar(Categoria objeto, int idCategoria, int estado) {
        boolean respuesta = false;
        String sql = "UPDATE categoria SET descripcion = ?, estado = ? WHERE idCategoria = ?";
        Connection cn = Conexion.conectar();

        try {
            cn.setAutoCommit(false);

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, objeto.getDescripcion());
                ps.setInt(2, estado);
                ps.setInt(3, idCategoria);

                if (ps.executeUpdate() > 0) {
                    respuesta = true;
                }
            }

            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e);
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error en rollback: " + rollbackEx);
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return respuesta;
    }

    public void cargarTablaCategorias(DefaultTableModel model) {
        Connection con = null;
        String sql = "SELECT idCategoria, descripcion, estado FROM categoria";
        Statement st;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0);
            while (rs.next()) {
                Object fila[] = new Object[3];
                for (int i = 0; i < 3; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
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
            System.out.println("Error al cargar la tabla de categorías: " + e);
        }
    }

    public void enviarDatosCategoriaSeleccionada(int idCategoria, Categoria categoria) {
        Connection con = null;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM categoria WHERE idCategoria = ?");
            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getInt("estado"));
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
            System.out.println("Error al seleccionar categoría: " + e);
        }
    }

    public DefaultTableModel cargarTablaCategorias() {
        Connection con = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT idCategoria, descripcion, estado FROM categoria";
        Statement st;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            model.addColumn("idCategoria");
            model.addColumn("descripcion");
            model.addColumn("estado");

            while (rs.next()) {
                Object fila[] = new Object[3];
                if (rs.getInt("estado") == 1) {
                    for (int i = 0; i < 3; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                }
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
            System.out.println("Error al llenar la tabla categorias: " + e);
        }

        return model;
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        Connection con = null;
        Categoria categoria = null;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?");
            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getInt("estado"));
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
            System.out.println("Error al seleccionar categoria: " + e);
        }

        return categoria;
    }
}
