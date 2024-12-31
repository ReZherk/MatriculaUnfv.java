package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelAyuda extends JPanel {
    
    JLabel lbl1, lbl2;
    JLabel sub1, sub2, sub3, sub4, sub5;
    JTextArea txt1, txt2, txt3;
    JButton btnVideo;
    
    
    public PanelAyuda() {
        setLayout(new BorderLayout());
        JPanel Ayuda = new JPanel();
        Ayuda.setLayout(null);
        Ayuda.setPreferredSize(new Dimension(750, 600));
        Ayuda.setBackground(Color.WHITE);
        add(Ayuda, BorderLayout.CENTER);
        
        lbl1 = new JLabel("¿Necesitas Ayuda? ¡Lo Solucionamos Juntos!");
        lbl1.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbl1.setBounds(50, 10, 750, 50);
        lbl1.setForeground(new Color(191, 33, 5));
        Ayuda.add(lbl1);
        
        lbl2 = new JLabel("Si tienes dudas sobre tu matrícula, ¡no te preocupes! Estamos aquí para ayudarte.");
        lbl2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lbl2.setBounds(40, 60, 750, 40);
        lbl2.setForeground(new Color(191, 33, 5));
        Ayuda.add(lbl2);
        
        sub1 = new JLabel("¡Ocurrió un Problema!");
        sub1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sub1.setBounds(70, 130, 750, 40);
        sub1.setForeground(new Color(244, 113, 34));
        Ayuda.add(sub1);
        
        JTextArea Instrucciones= TextDesplegable(Ayuda, "           Mostrar", 40, 207, 644, 140);
        Instrucciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        Instrucciones.setForeground(new Color(191, 33, 5));
        Instrucciones.append("\n      • Describe el problema con el mayor detalle posible.");
        Instrucciones.append("\n          Ejemplo:");
        Instrucciones.append("\n         \"Intenté registrarme en la seccion de matrícula pero el botón de guardar no responde.\"");
        Instrucciones.append("\n         \"Al matricularmes los horarios me aparecen en blanco.\"");
        Instrucciones.append("\n      • Describa los pasos realizados antes del error o problema.");
        Instrucciones.append("\n          Ejemplo:");
        Instrucciones.append("\n           1. Ingresé al sistema con mi usuario y contraseña..");
        Instrucciones.append("\n           2. Fui a la sección de \"Formulario de Matrícula\".");
        Instrucciones.append("\n           3. Al hacer clic en \"Enviar\", apareció un mensaje de error.");
        Instrucciones.append("\n      • Incluya cual es el error que le aparece en la consola");
        Instrucciones.append("\n          -Anótalo exactamente como se muestra o toma una captura de pantalla.");
        Instrucciones.append("\n          -Si es posible proporcione un video.");
        Instrucciones.append("\n      • Proporciona tu nombre de usuario y el horario en el que ocurrió el problema.");
        Instrucciones.append("\n          ");
        
        JLabel ojo = new JLabel(new ImageIcon(getClass().getResource("/resources/images/ojo.jpg")));
        ojo.setBounds(60, 212, 25, 20);
        Ayuda.add(ojo);
        
        sub2 = new JLabel("Contáctanos:");
        sub2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sub2.setBounds(60, 231, 750, 40);
        sub2.setForeground(new Color(244, 113, 34));
        Ayuda.add(sub2);
        
        JLabel correo = new JLabel(new ImageIcon(getClass().getResource("/resources/images/correo.jpg")));
        correo.setBounds(163, 235, 30, 30);
        Ayuda.add(correo);
        
        JLabel telefono = new JLabel(new ImageIcon(getClass().getResource("/resources/images/movil.jpg")));
        telefono.setBounds(348, 235, 30, 30);
        Ayuda.add(telefono);
        
        sub3 = new JLabel("Horario de Atención:");
        sub3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sub3.setBounds(60, 271, 750, 40);
        sub3.setForeground(new Color(244, 113, 34));
        Ayuda.add(sub3);
        
        JLabel hora = new JLabel(new ImageIcon(getClass().getResource("/resources/images/hora.jpg")));
        hora.setBounds(210, 273, 30, 30);
        Ayuda.add(hora);
        
        txt1 = crearTextArea(40, 120, 644, 196);
        txt1.append("\n");
        txt1.append("\n");
        txt1.append("\n     Sigue estas instrucciones para reportar el problema y poder solucionarlo rápidamente:");
        txt1.append("\n");
        txt1.append("\n");
        txt1.append("\n                                       soporte@unfv.edu.pe              987654321 - 987654321");
        txt1.append("\n");
        txt1.append("\n                                                  Lunes a Viernes, 8:00 AM – 5:00 PM");
        txt1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt1.setForeground(new Color(191, 33, 5));
        Ayuda.add(txt1);
        
        
        
        btnVideo = new JButton("Play");
        btnVideo.setBounds(150, 490, 100, 30);
        btnVideo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVideo.setForeground(Color.WHITE);
        btnVideo.setBackground(new Color(244, 113, 34));
        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirVentanaVideo();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(PanelAyuda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnVideo.setFocusPainted(false);
        Ayuda.setComponentZOrder(btnVideo, 0);
        Ayuda.add(btnVideo);
        
        sub4 = new JLabel("¡Mira el Video Tutorial!");
        sub4.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sub4.setBounds(80, 350, 750, 40);
        sub4.setForeground(new Color(244, 113, 34));
        Ayuda.add(sub4);
        
        txt2 = crearTextArea(40, 340, 304, 196);
        txt2.append("\n");
        txt2.append("\n");
        txt2.append("\n     Si no sabes cómo completar tu matrícula, te");
        txt2.append("\n       recomendamos ver nuestro video tutorial");
        txt2.append("\n     ¡Es rápido, fácil y te guiará paso a paso. Solo");
        txt2.append("\n               haz clic en \"Play\" para verlo.");
        txt2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt2.setForeground(new Color(191, 33, 5));
        Ayuda.add(txt2);
        
        sub5 = new JLabel("¡Recuerda!");
        sub5.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sub5.setBounds(470, 350, 750, 40);
        sub5.setForeground(new Color(244, 113, 34));
        Ayuda.add(sub5);
        
        JLabel calendario = new JLabel(new ImageIcon(getClass().getResource("/resources/images/calendario.jpg")));
        calendario.setBounds(560, 405, 100, 100);
        Ayuda.add(calendario);
        
        txt3 = crearTextArea(380, 340, 304, 196);
        txt3.append("\n");
        txt3.append("\n");
        txt3.append("\n     El proceso de matrícula");
        txt3.append("\n     estará disponible hasta");
        txt3.append("\n     estará disponible hasta");
        txt3.append("\n     algún inconveniente");
        txt3.append("\n     infórmanos antes de la");
        txt3.append("\n     fecha límite.");
        txt3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt3.setForeground(new Color(191, 33, 5));
        Ayuda.add(txt3);
        
        
    }

    private JTextArea crearTextArea(int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibujar el fondo con bordes redondos
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Dibujar ell borde
                g2.setColor(new Color(254, 234, 201));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
                super.paintComponent(g); // Permite que el contenido sea dibujado
            }

            @Override
            public void setBorder(Border border) {
                // Evitar que Swing sobrescriba el borde personalizado
            }
        };

        textArea.setBounds(x, y, width, height);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setBackground(new Color(254, 234, 201));
        textArea.setFont(new Font("Segoe UI", Font.BOLD, 13));
        textArea.setMargin(new Insets(10, 10, 10, 10)); 

        return textArea;
    }

    private JTextArea TextDesplegable(JPanel panel, String buttonText, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(false);
        textArea.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(x, y + 30, width, height - 30);
        scrollPane.setVisible(false);

        JButton BtnDesp = new JButton(buttonText);
        
        BtnDesp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        BtnDesp.setForeground(new Color(191, 33, 5));
        BtnDesp.setHorizontalAlignment(SwingConstants.LEFT);
        BtnDesp.setOpaque(false);
        BtnDesp.setContentAreaFilled(false);
        BtnDesp.setBorderPainted(false);
        BtnDesp.setFocusPainted(false);
        
        BtnDesp.setBounds(x, y, width, 30);
        
        BtnDesp.addActionListener(new ActionListener() {
            private boolean isVisible = false; 

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible; 
                textArea.setVisible(isVisible);
                scrollPane.setVisible(isVisible);
                BtnDesp.setText(isVisible ? "           Ocultar" : buttonText);
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(BtnDesp);
        panel.add(scrollPane);

        return textArea;
    }

    
    private void abrirVentanaVideo() throws URISyntaxException {
        try {
            File videoFile = new File(getClass().getResource("/resources/images/Tutorial.mp4").toURI());

            if (videoFile.exists()) {
                Desktop.getDesktop().open(videoFile);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el video.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar abrir el video.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}