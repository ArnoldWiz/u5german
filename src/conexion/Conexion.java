package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author barre
 */
public class Conexion {

    //conexion local
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ventas?useSSL=false&requireSSL=false",
                    "root", 
                    "root");
             
            /*Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://arnold-itsur.mysql.database.azure.com/ventas?useSSL=true&requireSSL=true",
                    "arnoldrg",
                    "Whiskaz001");*/

            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
}
