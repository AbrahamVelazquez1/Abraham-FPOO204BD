package p18;

import javax.swing.JOptionPane;

public class FormularioConsulta extends javax.swing.JFrame {
    private UsuarioCRUD crud;

    public FormularioConsulta() {
        iniciarComponentes();
        crud = new UsuarioCRUD();
    }

    @SuppressWarnings("unchecked")
    private void iniciarComponentes() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Usuario");

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 0, 18));
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Consultar Usuario");

        jLabel2.setText("ID:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAccionRealizada(evt);
            }
        });

        btnLimpiar.setForeground(new java.awt.Color(0, 0, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarAccionRealizada(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnLimpiar)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(30, 30, 30)
                .addComponent(btnLimpiar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnBuscarAccionRealizada(java.awt.event.ActionEvent evt) {
        String idTexto = txtID.getText();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            java.sql.ResultSet rs = crud.buscarPorID(id);

            if (rs != null && rs.next()) {
                String mensaje = "Nombre: " + rs.getString("Nombre") + "\n" +
                                 "Correo: " + rs.getString("Correo") + "\n" +
                                 "Contraseña: " + rs.getString("Contrasena");
                JOptionPane.showMessageDialog(this, mensaje, "Usuario Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimpiarAccionRealizada(java.awt.event.ActionEvent evt) {
        txtID.setText("");
    }

    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtID;
}