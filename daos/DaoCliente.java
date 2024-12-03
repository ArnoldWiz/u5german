/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;

/**
 *
 * @author Alumno LMC
 */
public class DaoCliente {

    // metodo para obtener cliente por nombre
    public int ObtenerIdClientePorNombre(String nombreCliente) {
        Connection cn = Conexion.conectar(); 
        String sql = "SELECT idCliente FROM clientes WHERE nombre = ? AND estado = 1";
        int idCliente = -1; 

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nombreCliente); 
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    idCliente = rs.getInt("idCliente"); 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del cliente: " + e);
        }

        return idCliente;
    }

    // metodo para cargar todos los clientes
    public JComboBox<String> CargarComboClientes(JComboBox<String> jComboBox_cliente) {
        Connection cn = Conexion.conectar(); 
        String sql = "SELECT * FROM clientes";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            jComboBox_cliente.removeAllItems();
            jComboBox_cliente.addItem("Seleccione cliente:");

            while (rs.next()) {
                if (rs.getInt("estado") == 1) { 
                    jComboBox_cliente.addItem(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar clientes: " + e);
        }
        return jComboBox_cliente;
    }

    // Método para verificar si un cliente ya existe por nombre
    public boolean clienteExiste(String nombre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.conectar();
            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM clientes WHERE nombre = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nombre);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar cliente: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    // metodo para buscar cliente por nombre
    public Cliente buscarClientePorNombre(String nombre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "SELECT * FROM clientes WHERE nombre = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nombre);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int idCliente = resultSet.getInt("idCliente");
                    String telefono = resultSet.getString("telefono");
                    int estado = resultSet.getInt("estado");
                    String direccion = resultSet.getString("direccion");
                    String email = resultSet.getString("email");
                    String rfc = resultSet.getString("rfc");
                    cliente = new Cliente(idCliente, nombre, telefono, estado, direccion, email, rfc);
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return cliente;
    }

    // metodo para insertar cliente
    public void insertarCliente(String nombre, String telefono, String direccion, String email, String rfc) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "{CALL InsertarCliente(?, ?, ?, ?, ?, ?)}";
                callableStatement = connection.prepareCall(sql);

                callableStatement.setString(1, nombre);      
                callableStatement.setString(2, telefono);    
                callableStatement.setInt(3, 1);             
                callableStatement.setString(4, direccion);   
                callableStatement.setString(5, email);       
                callableStatement.setString(6, rfc);         

                callableStatement.execute();
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // metodo para actualizar cliente
    public void actualizarCliente(String nombre, String telefono, String direccion, String email, String rfc) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "{CALL UpdateCliente(?, ?, ?, ?, ?, ?)}";
                callableStatement = connection.prepareCall(sql);

                callableStatement.setString(1, nombre);     
                callableStatement.setString(2, telefono);   
                callableStatement.setInt(3, 1);         
                callableStatement.setString(4, direccion);   
                callableStatement.setString(5, email);       
                callableStatement.setString(6, rfc);         

                callableStatement.execute();
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // metodo para eliminar cliente logico
    public void eliminarCliente(String nombre) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "{CALL EliminarCliente(?)}";
                callableStatement = connection.prepareCall(sql);

                callableStatement.setString(1, nombre);

                callableStatement.execute();
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // metodo para restaurar el eliminado logico
    public void restaurarCliente(String nombre) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "{CALL RestaurarCliente(?)}";
                callableStatement = connection.prepareCall(sql);

                callableStatement.setString(1, nombre); 

                callableStatement.execute();
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al restaurar cliente: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // metodo para cargar rabkla con los clientes
    public DefaultTableModel cargarTablaClientes() {
        Connection con = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT idCliente, nombre, telefono, direccion, email, estado FROM clientes";
        Statement st;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Agregar las columnas al modelo
            model.addColumn("idCliente");
            model.addColumn("nombre");
            model.addColumn("telefono");
            model.addColumn("direccion");
            model.addColumn("email");
            model.addColumn("estado");
            while (rs.next()) {
                if (rs.getInt("estado") == 1) { 
                    Object fila[] = new Object[6];
                    for (int i = 0; i < 6; i++) {
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
            System.out.println("Error al llenar la tabla clientes: " + e);
        }

        return model;
    }

}
