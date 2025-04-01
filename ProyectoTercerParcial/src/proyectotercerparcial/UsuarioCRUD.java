package proyectotercerparcial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioCRUD {
    private Connection conexion;

    public UsuarioCRUD() {
        conexion = ConexionMySQL.conectar();
    }

    public boolean insertarUsuario(String nombre, String apellido, String correo, String contrasena, String departamento) {
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena, departamento) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, contrasena);
            ps.setString(5, departamento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public ResultSet buscarPorDepartamento(String departamento) {
        String sql = "SELECT * FROM usuarios WHERE departamento = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, departamento);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }
}