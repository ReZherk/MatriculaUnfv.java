package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminosCondiciones extends JFrame implements ActionListener {

    JTextArea area;
    JButton aceptar, cancelar;
    JScrollPane barra;

    public TerminosCondiciones() {

        setResizable(false);
        setSize(800, 600);
        setTitle("Términos y Condiciones - UNFV");
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        JLabel encabezado = new JLabel("TÉRMINOS Y CONDICIONES", SwingConstants.CENTER);
        encabezado.setBounds(0, 20, 800, 40);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 24));
        encabezado.setForeground(new Color(191, 33, 5));
        add(encabezado);

        area = new JTextArea(
                "Bienvenido al sistema de matrícula de la Universidad Nacional Federico Villarreal (UNFV).\n\n" +
                "Al utilizar este sistema, usted acepta los siguientes términos y condiciones:\n\n" +
                "1. USO DEL SISTEMA: El sistema de matrícula está destinado exclusivamente para uso de los estudiantes " +
                "registrados en la UNFV. Cualquier uso indebido o no autorizado del sistema resultará en la suspensión de " +
                "sus derechos de acceso.\n\n" +
                "2. RESPONSABILIDAD DEL USUARIO: Usted es responsable de mantener la confidencialidad de su usuario y " +
                "contraseña. Cualquier actividad realizada desde su cuenta es su responsabilidad. En caso de uso no autorizado, " +
                "deberá notificar a la universidad de inmediato.\n\n" +
                "3. DISPONIBILIDAD DE CURSOS: La universidad se reserva el derecho de modificar la oferta de cursos, horarios o " +
                "sedes sin previo aviso. La asignación de cursos dependerá de la disponibilidad de cupos y los requisitos previos " +
                "cumplidos por el estudiante.\n\n" +
                "4. INFORMACIÓN PERSONAL: Los datos personales proporcionados en este sistema serán utilizados únicamente para " +
                "fines administrativos relacionados con la matrícula. La UNFV garantiza la confidencialidad y seguridad de su " +
                "información personal en conformidad con las normativas vigentes de protección de datos.\n\n" +
                "5. MODIFICACIONES: La universidad se reserva el derecho de modificar estos términos y condiciones en cualquier momento. " +
                "Es responsabilidad del usuario revisar los términos periódicamente.\n\n" +
                "Si está de acuerdo con estos términos y condiciones, presione 'Aceptar' para continuar con el proceso de matrícula. " +
                "Si no está de acuerdo, presione 'Cancelar' y su acceso será restringido.");

        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        area.setForeground(Color.BLACK);

        barra = new JScrollPane(area);
        barra.setBounds(50, 80, 500, 380);
        barra.setBorder(BorderFactory.createLineBorder(new Color(254, 234, 201), 2));
        add(barra);
        
        JLabel n1 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/vil.jpg")));
        n1.setBounds(580, 60, 150, 450);
        add(n1);
        aceptar = new JButton("ACEPTAR");
        aceptar.setBounds(200, 500, 150, 40);
        aceptar.setForeground(Color.WHITE); // Texto blanco
        aceptar.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente
        aceptar.setBorder(null); // Sin borde
        aceptar.setFocusPainted(false); // Sin indicador de foco
        aceptar.setContentAreaFilled(false); // Sin fondo
        aceptar.setOpaque(false); // Sin indicadores de foco
        aceptar.addActionListener(this); // Agregar ActionListener
        add(aceptar);

        JLabel n2 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/acept.jpg")));
        n2.setBounds(195, 500, 150, 40);
        add(n2);
        
        cancelar = new JButton("CANCELAR");
        cancelar.setBounds(400, 500, 150, 40);
        cancelar.setForeground(Color.WHITE); // Texto blanco
        cancelar.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente
        cancelar.setBorder(null); // Sin borde
        cancelar.setFocusPainted(false); // Sin indicador de foco
        cancelar.setContentAreaFilled(false); // Sin fondo
        cancelar.setOpaque(false); // Sin indicadores de foco
        cancelar.addActionListener(this);    // Agregar ActionListener
        add(cancelar);
       JLabel n3 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/deneg.jpg")));
        n3.setBounds(395, 500, 150, 40);
        add(n3);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aceptar) {
            LoginUI ventanaLogin = new LoginUI();
            ventanaLogin.setVisible(true);
            this.dispose();
        } else if (e.getSource() == cancelar) {
            JOptionPane.showMessageDialog(null, "Debe aceptar los términos para continuar.");
            System.exit(0);
        }
    }
}