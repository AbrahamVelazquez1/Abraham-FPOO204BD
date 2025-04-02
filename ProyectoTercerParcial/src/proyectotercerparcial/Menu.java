package proyectotercerparcial;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JLabel lblBienvenida;
    private JLabel lblSubtitulo;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnGestionarArticulos;
    private JButton btnGestionarPedidos;
    private JButton btnCerrarSesion;
    private int idUsuario;

    public Menu(int idUsuario) {
        this.idUsuario = idUsuario;
        setTitle("Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblBienvenida = new JLabel("Bienvenido a MerksAndSpen");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        lblBienvenida.setForeground(new Color(0, 102, 204));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblSubtitulo = new JLabel("Sistema de Gestion");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        lblSubtitulo.setForeground(new Color(100, 100, 100));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRegistrar = new JButton("Registrar Usuario");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegistrar.setBackground(new Color(0, 153, 76));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.setMaximumSize(new Dimension(200, 40));
        btnRegistrar.addActionListener(e -> new FormularioRegistro().setVisible(true));

        btnConsultar = new JButton("Consultar Usuarios");
        btnConsultar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnConsultar.setBackground(new Color(0, 153, 76));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnConsultar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConsultar.setMaximumSize(new Dimension(200, 40));
        //btnConsultar.addActionListener(e -> new FormularioConsulta().setVisible(true));

        btnGestionarArticulos = new JButton("Gestionar Articulos");
        btnGestionarArticulos.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGestionarArticulos.setBackground(new Color(0, 153, 76));
        btnGestionarArticulos.setForeground(Color.WHITE);
        btnGestionarArticulos.setFocusPainted(false);
        btnGestionarArticulos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGestionarArticulos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarArticulos.setMaximumSize(new Dimension(200, 40));
        btnGestionarArticulos.addActionListener(e -> new FormularioArticulos().setVisible(true));

        btnGestionarPedidos = new JButton("Gestionar Pedidos");
        btnGestionarPedidos.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGestionarPedidos.setBackground(new Color(0, 153, 76));
        btnGestionarPedidos.setForeground(Color.WHITE);
        btnGestionarPedidos.setFocusPainted(false);
        btnGestionarPedidos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGestionarPedidos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarPedidos.setMaximumSize(new Dimension(200, 40));
        //btnGestionarPedidos.addActionListener(e -> new FormularioPedidos(idUsuario).setVisible(true));

        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCerrarSesion.setBackground(new Color(204, 0, 0));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setMaximumSize(new Dimension(200, 40));
        btnCerrarSesion.addActionListener(e -> {
            new InicioSesion().setVisible(true);
            dispose();
        });

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(lblBienvenida);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lblSubtitulo);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(btnRegistrar);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnConsultar);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnGestionarArticulos);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnGestionarPedidos);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnCerrarSesion);
        mainPanel.add(Box.createVerticalGlue());

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(new Color(220, 220, 220));
        wrapper.add(mainPanel);

        getContentPane().setBackground(new Color(220, 220, 220));
        getContentPane().add(wrapper);
    }
}