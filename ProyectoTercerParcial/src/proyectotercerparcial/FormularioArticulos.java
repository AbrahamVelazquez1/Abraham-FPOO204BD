package proyectotercerparcial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioArticulos extends JFrame {
    private JLabel lblTitulo;
    private JTable tablaArticulos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private ArticuloCRUD crud;

    public FormularioArticulos() {
        crud = new ArticuloCRUD();
        iniciarComponentes();
        setTitle("Gestion de Articulos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        cargarArticulos();
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabel("Lista de Articulos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Categoria", "Cantidad Disponible"}, 0);
        tablaArticulos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaArticulos);

        btnAgregar = new JButton("Agregar Articulo");
        btnAgregar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAgregar.setBackground(new Color(0, 153, 76));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAgregar.addActionListener(e -> new FormularioAgregarArticulo(this).setVisible(true));

        btnEliminar = new JButton("Eliminar Articulo");
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEliminar.setBackground(new Color(204, 0, 0));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnEliminar.addActionListener(e -> btnEliminarAccionRealizada());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void btnEliminarAccionRealizada() {
        int filaSeleccionada = tablaArticulos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un articulo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idArticulo = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "Estas seguro de eliminar el articulo con ID " + idArticulo + "?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = crud.eliminarArticulo(idArticulo);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Articulo eliminado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarArticulos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar articulo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cargarArticulos() {
        modeloTabla.setRowCount(0);
        ResultSet rs = crud.consultarArticulos();
        try {
            while (rs != null && rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getInt("id_articulo"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getInt("cantidad_disponible")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar articulos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}