package proyectotercerparcial;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblDepartamento;
    private JLabel lblContrasena;
    private JComboBox<String> comboDepartamento;
    private JPasswordField txtContrasena;
    private JButton btnIniciar;
    private JButton btnRegistrarse;
    private JButton btnMostrarContrasena;
    private boolean contrasenaVisible;

    public InicioSesion() {
        iniciarComponentes();
        setTitle("Inicio de Sesion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        contrasenaVisible = false;
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabel("Bienvenido a MerksAndSpen");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblDepartamento = new JLabel("Departamento:");
        lblDepartamento.setFont(new Font("Arial", Font.PLAIN, 14));

        lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] departamentos = {"Compras", "Recursos Humanos", "Produccion", "Almacen", "Logistica"};
        comboDepartamento = new JComboBox<>(departamentos);
        txtContrasena = new JPasswordField();

        btnIniciar = new JButton("Iniciar Sesion");
        btnIniciar.setBackground(new Color(0, 204, 102));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(e -> btnIniciarAccionRealizada());

        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBackground(new Color(0, 102, 204));
        btnRegistrarse.setForeground(Color.WHITE);
        btnRegistrarse.addActionListener(e -> new FormularioRegistro().setVisible(true));

        btnMostrarContrasena = new JButton("Mostrar");
        btnMostrarContrasena.setBackground(new Color(255, 165, 0));
        btnMostrarContrasena.setForeground(Color.WHITE);
        btnMostrarContrasena.addActionListener(e -> btnMostrarContrasenaAccionRealizada());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblDepartamento)
                        .addComponent(lblContrasena))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(comboDepartamento, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtContrasena, 150, 150, 150)
                            .addComponent(btnMostrarContrasena))))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnIniciar)
                    .addComponent(btnRegistrarse))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(comboDepartamento))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena)
                    .addComponent(btnMostrarContrasena))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnRegistrarse))
        );
    }

    private void btnIniciarAccionRealizada() {
        String departamento = (String) comboDepartamento.getSelectedItem();
        String contrasena = new String(txtContrasena.getPassword());

        if (departamento == null || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioCRUD crud = new UsuarioCRUD();
        ResultSet rs = crud.buscarPorDepartamento(departamento);

        try {
            boolean usuarioEncontrado = false;
            int idUsuario = -1;

            while (rs != null && rs.next()) {
                if (rs.getString("contrasena").equals(contrasena)) {
                    usuarioEncontrado = true;
                    idUsuario = rs.getInt("id_usuario");
                    break;
                }
            }

            if (usuarioEncontrado) {
                JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
                new Menu(idUsuario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Contrasena incorrecta o usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnMostrarContrasenaAccionRealizada() {
        if (contrasenaVisible) {
            txtContrasena.setEchoChar('*');
            btnMostrarContrasena.setText("Mostrar");
            contrasenaVisible = false;
        } else {
            txtContrasena.setEchoChar((char) 0);
            btnMostrarContrasena.setText("Ocultar");
            contrasenaVisible = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSesion().setVisible(true));
    }
}