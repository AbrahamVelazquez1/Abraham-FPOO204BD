package proyectotercerparcial;

import javax.swing.*;
import java.awt.*;

public class FormularioRegistro extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreo;
    private JLabel lblContrasena;
    private JLabel lblDepartamento;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JComboBox<String> comboDepartamento;
    private JButton btnGuardar;
    private JButton btnMostrarContrasena;
    private boolean contrasenaVisible;

    public FormularioRegistro() {
        iniciarComponentes();
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        contrasenaVisible = false;
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabel("Registro de Nuevo Usuario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblNombre = new JLabel("Nombre:");
        lblApellido = new JLabel("Apellido:");
        lblCorreo = new JLabel("Correo:");
        lblContrasena = new JLabel("Contrasena:");
        lblDepartamento = new JLabel("Departamento:");

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();

        String[] departamentos = {"Compras", "Recursos Humanos", "Produccion", "Almacen", "Logistica"};
        comboDepartamento = new JComboBox<>(departamentos);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 204, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> btnGuardarAccionRealizada());

        btnMostrarContrasena = new JButton("Mostrar");
        btnMostrarContrasena.setBackground(new Color(255, 165, 0));
        btnMostrarContrasena.setForeground(Color.WHITE);
        btnMostrarContrasena.addActionListener(e -> btnMostrarContrasenaAccionRealizada());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        //respetar las dimensiones de los botones para mantener una armonia en la UI :)

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNombre)
                        .addComponent(lblApellido)
                        .addComponent(lblCorreo)
                        .addComponent(lblContrasena)
                        .addComponent(lblDepartamento))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombre, 200, 200, 200)
                        .addComponent(txtApellido, 200, 200, 200)
                        .addComponent(txtCorreo, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtContrasena, 150, 150, 150)
                            .addComponent(btnMostrarContrasena))
                        .addComponent(comboDepartamento, 200, 200, 200)))
                .addComponent(btnGuardar)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena)
                    .addComponent(btnMostrarContrasena))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(comboDepartamento))
                .addGap(20)
                .addComponent(btnGuardar)
        );
    }

    private void btnGuardarAccionRealizada() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String departamento = (String) comboDepartamento.getSelectedItem();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioCRUD crud = new UsuarioCRUD();
        boolean exito = crud.insertarUsuario(nombre, apellido, correo, contrasena, departamento);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
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
}