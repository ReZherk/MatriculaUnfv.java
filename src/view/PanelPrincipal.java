package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Estudiante;

public class PanelPrincipal extends JPanel {

    private JPanel Principal;
    private JLabel imagenLabel,imagensalir;
    private int xPosicion;
    private int yPosicion = 0;
    private double velocidad = 1.0;
    private boolean moviendoDerecha = true;

    private int imagenActual = 0; // Índice de la imagen actual
    private final String[] imagenes = {
        "/resources/images/UNFV1.jpg",
        "/resources/images/UNFV2.jpg",
        "/resources/images/UNFV3.jpg"
    };
    
    private int anchoPanel = 750;
    private int anchoImagen;
    
    private Estudiante estudiante;
    
    public PanelPrincipal(Estudiante estudiante) {
        
        this.estudiante = estudiante;
        
        Componentes();

        ActualizarImagen();

        Temporizador();

        
    }

    private void Componentes() {
        Principal = new JPanel();
        Principal.setLayout(null);
        Principal.setPreferredSize(new Dimension(750, 600));
        Principal.setBackground(Color.WHITE);
        add(Principal);
        
        
        String genero= estudiante.getGenero();
        String palabra;
        
        if(genero.equals("MASCULINO")){
            palabra="BIENVENIDO";
        }else{
            palabra="BIENVENIDA";
        }
        JLabel lblTitulo = new JLabel(palabra+ " " + estudiante.getNombres());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblTitulo.setBounds(40, 242, 750, 40);
        lblTitulo.setForeground(new Color(191, 33, 5));
        Principal.add(lblTitulo);
        
        JTextArea fondo = new JTextArea();
        fondo.setBounds(0, 210, 750, 110);
        Principal.add(fondo);
        
        
        // Inicializar JLabel para mostrar imágenes
        imagenLabel = new JLabel();
        
    }

    private void ActualizarImagen() {
        ImageIcon icono = new ImageIcon(getClass().getResource(imagenes[imagenActual]));
        imagenLabel.setIcon(icono); // Cambia la imagen del JLabel
        
        anchoPanel = 750;
        anchoImagen = imagenLabel.getIcon().getIconWidth();
        xPosicion = anchoPanel - anchoImagen;
        
        imagenLabel.setBounds(xPosicion, yPosicion, icono.getIconWidth(), icono.getIconHeight());
        Principal.add(imagenLabel);
        imagenActual = (imagenActual + 1) % imagenes.length; // Cambiar a la siguiente imagen
    }
    
    private void Temporizador(){
        // Timer para el movimiento horizontal
        Timer temporizadorMovimiento = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (moviendoDerecha) {
                    xPosicion += velocidad;
                    if (xPosicion >= 0) {
                        moviendoDerecha = false;
                    }
                } else {
                    xPosicion -= velocidad;
                    if (xPosicion <= anchoPanel - anchoImagen) {
                        moviendoDerecha = true;
                        //cambiarImagen(); // Cambiar imagen al final del movimiento
                        ActualizarImagen();
                    }
                }
                imagenLabel.setLocation(xPosicion, yPosicion);
            }
        });

        temporizadorMovimiento.start();
    }
}