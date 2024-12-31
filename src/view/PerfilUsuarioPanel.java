package view;

import view.PerfilUsuarioPanel;
import controller.PerfilController1;
import model.Curso;
import model.MetodosCurso;
import model.Estudiante;
import javax.swing.*;
import java.awt.*;
import model.Estudiante;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class PerfilUsuarioPanel extends JPanel {
    private PerfilController1 controller;
    private Estudiante estudiante;
    private JLabel lblImagenPerfil;
    private JTextField txtNombreUsuario;
    private JTextField txtApellidosUsuario;
    private JTextField txtContrasena;
    private JTextField txtCorreo;
    private JButton btnGuardar;
    private JButton btnEliminarCuenta;
    private JButton btnEditarFoto;

    public PerfilUsuarioPanel(Estudiante estudiante) {
        this.estudiante = estudiante;
        inicializarComponentes();
        this.controller = new PerfilController1(this, estudiante);
        
    }

    public void inicializarComponentes() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750, 600));
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        add(panelPrincipal, BorderLayout.CENTER);

        lblImagenPerfil = new JLabel(new ImageIcon(getClass().getResource("/resources/images/Perfil.png")));
        lblImagenPerfil.setBounds(50, 20, 100, 100);
        panelPrincipal.add(lblImagenPerfil);

        JLabel lblNombreUsuario = new JLabel(estudiante.getNombres()+" "+estudiante.getApellidos());
        lblNombreUsuario.setBounds(160, 50,540, 25);
        lblNombreUsuario.setFont(new Font("Segoe UI", Font.BOLD,20));
        
        lblNombreUsuario.setForeground(new Color(191, 33, 5));
        panelPrincipal.add(lblNombreUsuario);
        // Crear el botón transparente
        btnEditarFoto = new JButton("EDITAR FOTO");
        btnEditarFoto.setBounds(175, 99, 130, 30);
        btnEditarFoto.setForeground(Color.WHITE); // Texto blanco
        btnEditarFoto.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente
        btnEditarFoto.setBorder(null); // Sin borde
        btnEditarFoto.setFocusPainted(false); // Sin indicador de foco
        btnEditarFoto.setContentAreaFilled(false); // Sin fondo
        btnEditarFoto.setOpaque(false); // Sin indicadores de foco

// Agregar el botón después del JLabel para que esté encima
        panelPrincipal.add(btnEditarFoto); 
        JLabel camara = new JLabel(new ImageIcon(getClass().getResource("/resources/images/cam2.jpg")));
        camara.setBounds(160, 100, 140, 32);
        panelPrincipal.add(camara);
        
       
        
        /*
        JLabel lblLogoUniversidad = new JLabel(new ImageIcon(getClass().getResource("/resources/images/Universidad.jpg")));
        lblLogoUniversidad.setBounds(530, 20, 200, 81);
        panelPrincipal.add(lblLogoUniversidad);
        */
        JPanel panelDatos1 = new JPanel(null) {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo con bordes redondeados
        g2.setColor(new Color(254, 234, 201)); // Fondo del panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Dibujar borde redondeado
        g2.setColor(Color.WHITE); // Color del borde
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        g2.dispose();
    }
};
panelDatos1.setBounds(50, 150, 620, 80);
panelDatos1.setOpaque(false); // Hacer que el fondo predeterminado no se pinte
panelPrincipal.add(panelDatos1);
        
        JLabel facultad = new JLabel(new ImageIcon(getClass().getResource("/resources/images/1.jpg")));
        facultad.setBounds(20, 2, 35, 35);
        panelDatos1.add(facultad);
        
        String nombreFacultad = switch (estudiante.getIdEscuela()) {
          case 1 -> "Ingeniería de Sistemas";
          case 2 -> "Ingeniería Industrial";
          case 3 -> "Ingeniería de Transportes";
          case 4 -> "Ingeniería Agroindustrial";
          default -> "No Asignado";
        };
        JLabel lblFacultad = new JLabel(nombreFacultad);
        lblFacultad.setBounds(60, 8, 200, 25);
        lblFacultad.setFont(new Font("Segoe UI", Font.BOLD, 14)); //CAMBIA FUENTE SEGUI
        
        lblFacultad.setForeground(new Color(191, 33, 5)); //Cambiarcolo, VOY A REVISAR
        panelDatos1.add(lblFacultad);
        
        
        JLabel Escuela = new JLabel(new ImageIcon(getClass().getResource("/resources/images/2.jpg")));
        Escuela.setBounds(20, 35, 35, 35);
        panelDatos1.add(Escuela);

        String nombreEscuela = switch (nombreFacultad) {
            case "Ingeniería de Sistemas" -> "Escuela Profesional de Ingeniería de Sistemas";
            case "Ingeniería Industrial" -> "Escuela Profesional de Ingeniería Industrial";
            case "Ingeniería de Transportes" -> "Escuela Profesional de Ingeniería de Transportes";
            case "Ingeniería Agroindustrial" -> "Escuela Profesional de Ingeniería Agroindustrial";
            default -> "Escuela No Asignada";
        };
        JLabel lblEscuela = new JLabel(nombreEscuela);
        lblEscuela.setBounds(60, 41, 400, 25);
        lblEscuela.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEscuela.setForeground(new Color(191, 33, 5)); //Cambiarcolo, VOY A REVISAR
        panelDatos1.add(lblEscuela);    

    // Crear el segundo panel personalizado (panelDatos2)
    JPanel panelDatos2 = new JPanel(null) {
            @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo con bordes redondeados
            g2.setColor(new Color(254, 234, 201)); // Fondo del panel
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Dibujar borde redondeado
            g2.setColor(Color.WHITE); // Color del borde
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            g2.dispose();
        }
    };
        panelDatos2.setBounds(50, 250, 620, 150);
        panelDatos2.setOpaque(false); // Hacer que el fondo predeterminado no se pinte
        panelPrincipal.add(panelDatos2);
        
        JLabel n1 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/3.jpg")));
        n1.setBounds(5, 31, 34, 34);
        panelDatos2.add(n1);
        
        JLabel n2 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/3.jpg")));
        n2.setBounds(304, 31, 34, 34);
        panelDatos2.add(n2);
        
        JLabel n3 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/3.jpg")));
        n3.setBounds(5, 85, 34, 34);
        panelDatos2.add(n3);
        
        JLabel n4 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/3.jpg")));
        n4.setBounds(305, 85, 34, 34);
        panelDatos2.add(n4);

        txtNombreUsuario = crearCampo1("Nombres", estudiante.getNombres(),139, 32, panelDatos2);
        txtApellidosUsuario=crearCampo1("Apellidos", estudiante.getApellidos(),139, 82, panelDatos2);
        txtContrasena = crearCampo2("Contraseña", estudiante.getPassword(), 319,82, panelDatos2);
        txtCorreo = crearCampo2("Correo Institucional", estudiante.getMail(),319, 32, panelDatos2); 
        btnGuardar = new JButton("     Guardar Cambios");
        btnGuardar.setBounds(280, 410, 160, 40);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBorder(null);
        btnGuardar.setOpaque(false);
        btnGuardar.setContentAreaFilled(false);
        panelPrincipal.add(btnGuardar);
        
        JLabel guard = new JLabel(new ImageIcon(getClass().getResource("/resources/images/guard.jpg")));
        guard.setBounds(270,  410, 180, 40);
        panelPrincipal.add(guard);
    }

    public JTextField crearCampo1(String label, String valor, int posX , int posY, JPanel panel) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(posX - 100, posY+1, 150, 30);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(191, 33, 5));
        panel.add(lbl);

        JTextField textField = new JTextField(valor);
        textField.setBounds(posX-40, posY+1, 180, 28);
        textField.setBorder(null); // Sin borde
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField);

        return textField;
    }
    public JTextField crearCampo2(String label, String valor, int posX , int posY, JPanel panel) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(posX+15, posY+1, 180, 30);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(191, 33, 5));
        panel.add(lbl);

        JTextField textField = new JTextField(valor);
        textField.setBounds(posX+130, posY+1, 160, 28);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBorder(null);
        panel.add(textField);

        return textField;
    }
// Método para mostrar los datos del estudiante en los campos
    public void DatosEstudiante(Estudiante estudiante) {
    txtNombreUsuario.setText(estudiante.getNombres());
    txtApellidosUsuario.setText(estudiante.getApellidos());
    txtCorreo.setText(estudiante.getMail());
    txtContrasena.setText(estudiante.getPassword());
    // Convertir el ID de la escuela en texto 
    String escuela = switch (estudiante.getIdEscuela()) {
        case 1 -> "Ingeniería de Sistemas";
        case 2 -> "Ingeniería Industrial";
        case 3 -> "Ingenieria de Trasportes";
        case 4 -> "Ingenieria Agroindustrial";
        default -> "No Asignado";
    };
}
    // Método para recoger la información ingresada en el formulario
    public Estudiante recogerEstudiante(Estudiante obj) {
    String nombres, apellidos, mail, contrasena;

    // Recoger valores de los JTextFields
    nombres = txtNombreUsuario.getText();
    apellidos = txtApellidosUsuario.getText();
    mail = txtCorreo.getText();
    contrasena = txtContrasena.getText();

    // Validar que todos los campos estén llenos
    if (nombres.isEmpty() || apellidos.isEmpty() || mail.isEmpty() || contrasena.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos y válidos");
        return null;
    }

    // Asignar valores al objeto estudiante
    obj.setNombres(nombres);
    obj.setApellidos(apellidos);
    obj.setMail(mail);
    obj.setPassword(contrasena);

    // Mantener el ID de la escuela existente
    obj.setIdEscuela(estudiante.getIdEscuela());

    return obj;
}
    public int escuela(String escuela) {
        return switch (escuela) {
            case "Ingeniería de Sistemas" -> 1;
            case "Ingeniería Industrial" -> 2;
            case "Ingenieria de Trasportes" -> 3;
            case "Ingenieria Agroindustrial" -> 4;
            default -> 0;
        };
    }
    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void addEditarFotoListener(ActionListener listener) {
        btnEditarFoto.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

   public void actualizarImagenPerfil(ImageIcon nuevaImagen) {
    lblImagenPerfil.setIcon(nuevaImagen);

    }
}