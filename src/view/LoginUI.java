package view;

import controller.LoginController;
import model.MetodosEstudiante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame implements ActionListener {

    LoginController controller;
    JPanel panelPrincipal;
    JLabel etiquetaLogoPequeno;
    JComboBox comboOpciones;
    JLabel etiquetaTitulo;
    JLabel etiquetaUsuario;
    JTextField campoUsuario;
    JLabel etiquetaContrasena;
    JPasswordField campoContrasena;
    JButton botonEntrar;
    JButton botonSalir;
    JLabel etiquetaLogoGrande;
    JLabel etiquetaUnfv;
    JLabel etiquetaCiudad;

    public LoginUI() {
        controller = new LoginController(this, new MetodosEstudiante());
        Ventana();
    }

    public void Ventana() {
        setTitle("Login UNFV");
        setIconImage(new ImageIcon(getClass().getResource("/resources/images/icono.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 490);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setBounds(0, 0, 800, 490);
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        add(panelPrincipal);

        etiquetaLogoPequeno = new JLabel(new ImageIcon(getClass().getResource("/resources/images/logo_real.png")));
        etiquetaLogoPequeno.setBounds(10, 20, 190, 55);
        panelPrincipal.add(etiquetaLogoPequeno);

        comboOpciones = new JComboBox(new String[]{"Estudiante", "Administrativo"});
        comboOpciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboOpciones.setBounds(225, 70, 170, 30);
        panelPrincipal.add(comboOpciones);

        etiquetaTitulo = new JLabel("INICIAR SESIÓN");
        etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        etiquetaTitulo.setForeground(new Color(51, 51, 51));
        etiquetaTitulo.setBounds(20, 110, 400, 40);
        panelPrincipal.add(etiquetaTitulo);

        etiquetaUsuario = new JLabel("USUARIO");
        etiquetaUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        etiquetaUsuario.setForeground(new Color(102, 102, 102));
        etiquetaUsuario.setBounds(20, 160, 100, 20);
        panelPrincipal.add(etiquetaUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoUsuario.setBounds(20, 185, 340, 30);
        campoUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 153, 51)));
        panelPrincipal.add(campoUsuario);

        etiquetaContrasena = new JLabel("CONTRASEÑA");
        etiquetaContrasena.setFont(new Font("Segoe UI", Font.BOLD, 14));
        etiquetaContrasena.setForeground(new Color(102, 102, 102));
        etiquetaContrasena.setBounds(20, 225, 100, 20);
        panelPrincipal.add(etiquetaContrasena);

        campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoContrasena.setBounds(20, 250, 340, 30);
        campoContrasena.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 153, 51)));
        /*
         Los parámetros de createMatteBorder() son:
          0: grosor del borde superior
          0: grosor del borde izquierdo
          2: grosor del borde inferior
          0: grosor del borde derecho
         */
        panelPrincipal.add(campoContrasena);

        botonEntrar = new JButton("ENTRAR");
        botonEntrar.setBackground(new Color(255, 110, 33));
        botonEntrar.setForeground(Color.WHITE);
        botonEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonEntrar.setBounds(20, 300, 150, 40);
        botonEntrar.addActionListener(this);
        panelPrincipal.add(botonEntrar);

        botonSalir = new JButton("SALIR");
        botonSalir.setBackground(new Color(255, 110, 33));
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonSalir.setBounds(180, 300, 150, 40);
        botonSalir.addActionListener(this);
        panelPrincipal.add(botonSalir);

        etiquetaLogoGrande = new JLabel(new ImageIcon(getClass().getResource("/resources/images/LogoUnfv.jpg")));
        etiquetaLogoGrande.setBounds(587, 17, 60, 158);
        panelPrincipal.add(etiquetaLogoGrande);

        etiquetaUnfv = new JLabel("UNFV");
        etiquetaUnfv.setFont(new Font("Segoe UI", Font.BOLD, 32));
        etiquetaUnfv.setForeground(Color.WHITE);
        etiquetaUnfv.setBounds(570, 170, 100, 40);
        panelPrincipal.add(etiquetaUnfv);

        etiquetaCiudad = new JLabel(new ImageIcon(getClass().getResource("/resources/images/CiudadNaranja.png")));
        etiquetaCiudad.setBounds(420, 0, 380, 490);
        panelPrincipal.add(etiquetaCiudad);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEntrar) {
            String usuarioIngresado = campoUsuario.getText();
            String passwordIngresado = campoContrasena.getText();
            String rolSeleccionado = comboOpciones.getSelectedItem().toString();
            controller.autenticar(usuarioIngresado, passwordIngresado, rolSeleccionado);
        }

        if (e.getSource() == botonSalir) {
            this.dispose();
        }
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

}
