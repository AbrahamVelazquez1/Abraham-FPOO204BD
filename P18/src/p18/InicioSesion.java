package p18;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblCorreo;
    private JLabel lblContrasena;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIniciar;

    public InicioSesion() {
        iniciarComponentes();
        setTitle("Inicio de Sesion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabel("Bienvenido a MerksAndSpen");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 14));

        lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));

        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();

        btnIniciar = new JButton("Iniciar Sesion");
        btnIniciar.setBackground(new Color(0, 204, 102));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(e -> btnIniciarAccionRealizada());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblCorreo)
                        .addComponent(lblContrasena))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtCorreo, 200, 200, 200)
                        .addComponent(txtContrasena, 200, 200, 200)))
                .addComponent(btnIniciar)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena))
                .addGap(20)
                .addComponent(btnIniciar)
        );
    }

    private void btnIniciarAccionRealizada() {
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioCRUD crud = new UsuarioCRUD();
       ResultSet rs = crud.buscarPorCorreo(correo);

        try {
            if (rs != null && rs.next()) {
                if (rs.getString("contrasena").equals(contrasena)) {
                    JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
                   // new Menu(rs.getInt("id_usuario")).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Contrasena incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSesion().setVisible(true));
    }
}