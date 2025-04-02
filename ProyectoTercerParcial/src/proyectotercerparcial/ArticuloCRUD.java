package proyectotercerparcial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticuloCRUD {
    private Connection conexion;

    public ArticuloCRUD() {
        conexion = ConexionMySQL.conectar();
    }

    public boolean insertarArticulo(String nombre, String categoria, int cantidadDisponible) {
        String sql = "INSERT INTO articulos (nombre, categoria, cantidad_disponible) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setInt(3, cantidadDisponible);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar articulo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarArticulo(int idArticulo) {
        String sql = "DELETE FROM articulos WHERE id_articulo = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idArticulo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar articulo: " + e.getMessage());
            return false;
        }
    }

    public ResultSet consultarArticulos() {
        String sql = "SELECT * FROM articulos";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al consultar articulos: " + e.getMessage());
            return null;
        }
    }
}