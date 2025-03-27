package p18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioCRUD {
    private Connection conexion;

    public UsuarioCRUD() {
        conexion = ConexionMySQL.conectar();
    }

    public boolean insertarUsuario(String nombre, String correo, String contrasena) {
        String sqlInsertar = "INSERT INTO usuarios(Nombre, Correo, Contrasena) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlInsertar);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
            return false;
        }
    }

    public ResultSet buscarPorID(int id) {
        String sqlBuscar = "SELECT * FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlBuscar);
            ps.setInt(1, id);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por ID: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarUsuario(int id, String nombre, String correo, String contrasena) {
        String sqlActualizar = "UPDATE usuarios SET Nombre = ?, Correo = ?, Contrasena = ? WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlActualizar);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }
}