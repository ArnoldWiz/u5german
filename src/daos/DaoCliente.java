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
import modelos.Cliente;

/**
 *
 * @author Alumno LMC
 */
public class DaoCliente {

    public void insertarCliente(String nombre, String telefono) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = Conexion.conectar();

            if (connection != null) {
                String sql = "{CALL InsertarCliente(?, ?, ?)}";
                callableStatement = connection.prepareCall(sql);

                callableStatement.setString(1, nombre);
                callableStatement.setString(2, telefono);
                callableStatement.setInt(3, 3);

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

    // Método para verificar si un cliente ya existe por nombre
    public boolean clienteExiste(String nombre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtener la conexión desde la clase Conexion
            connection = Conexion.conectar();

            if (connection != null) {
                // Consulta para verificar si el cliente existe
                String sql = "SELECT COUNT(*) FROM clientes WHERE nombre = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nombre);

                // Ejecutar la consulta
                resultSet = preparedStatement.executeQuery();

                // Si hay resultados, verificar el conteo
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Devuelve true si existe al menos un cliente con el nombre
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar cliente: " + e.getMessage());
        } finally {
            // Cerrar los recursos
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
        return false; // Devuelve false si ocurre un error o no se encuentra el cliente
    }

    public Cliente buscarClientePorNombre(String nombre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;

        try {
            // Obtener la conexión desde la clase Conexion
            connection = Conexion.conectar();

            if (connection != null) {
                // Consulta para buscar el cliente por nombre
                String sql = "SELECT * FROM clientes WHERE nombre = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nombre);

                // Ejecutar la consulta
                resultSet = preparedStatement.executeQuery();

                // Si se encuentra el cliente, crear un objeto Cliente
                if (resultSet.next()) {
                    int idCliente = resultSet.getInt("idCliente");
                    String telefono = resultSet.getString("telefono");
                    int estado = resultSet.getInt("estado");

                    cliente = new Cliente(idCliente, nombre, telefono, estado);
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        } finally {
            // Cerrar los recursos
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

        return cliente; // Devuelve el cliente o null si no se encontró
    }
}
