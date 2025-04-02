package proyectotercerparcial;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregarArticulo extends JDialog {
    private JLabel lblNombre;
    private JLabel lblCategoria;
    private JLabel lblCantidadDisponible;
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextField txtCantidadDisponible;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private ArticuloCRUD crud;
    private FormularioArticulos parent;

    public FormularioAgregarArticulo(FormularioArticulos parent) {
        super(parent, true);
        this.parent = parent;
        crud = new ArticuloCRUD();
        iniciarComponentes();
        setTitle("Agregar Articulo");
        setSize(400, 250);
        setLocationRelativeTo(parent);
    }

    private void iniciarComponentes() {
        lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCantidadDisponible = new JLabel("Cantidad Disponible:");
        lblCantidadDisponible.setFont(new Font("Arial", Font.PLAIN, 14));

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombre.setPreferredSize(new Dimension(200, 30));
        txtCategoria = new JTextField();
        txtCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCategoria.setPreferredSize(new Dimension(200, 30));
        txtCantidadDisponible = new JTextField();
        txtCantidadDisponible.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCantidadDisponible.setPreferredSize(new Dimension(200, 30));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGuardar.addActionListener(e -> btnGuardarAccionRealizada());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(204, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBackground(new Color(245, 245, 245));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(lblCategoria, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulario.add(txtCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(lblCantidadDisponible, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulario.add(txtCantidadDisponible, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelFormulario, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    private void btnGuardarAccionRealizada() {
        String nombre = txtNombre.getText();
        String categoria = txtCategoria.getText();
        String cantidadStr = txtCantidadDisponible.getText();

        if (nombre.isEmpty() || categoria.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cantidadDisponible = Integer.parseInt(cantidadStr);

            if (cantidadDisponible <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad disponible debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exito = crud.insertarArticulo(nombre, categoria, cantidadDisponible);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Articulo agregado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                parent.cargarArticulos();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar articulo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad disponible debe ser numerica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
