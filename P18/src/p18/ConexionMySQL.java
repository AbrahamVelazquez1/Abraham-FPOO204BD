package p18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/dbpoo?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection conectar() {
        Connection conex = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conex = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("🚨 Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return conex;
    }
}