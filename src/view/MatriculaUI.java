package view;

import model.Estudiante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatriculaUI extends JFrame implements ActionListener {

    JPanel panelPrincipal;
    JPanel panelIzquierdo;
    JPanel panelDerecho;

    JButton botonPrincipal;
    JButton botonMatricula;
    JButton botonCursos;
    JButton botonPerfil;
    JButton botonAyuda;
    JButton botonSalir;
    Estudiante estudiante;

    public MatriculaUI(Estudiante estudiante) {
        this.estudiante = estudiante;
        inicializarUI();
    }

    private void inicializarUI() {
        setTitle("Matrícula - " + estudiante.getNombres() + " " + estudiante.getApellidos());
        setIconImage(new ImageIcon(getClass().getResource("/resources/images/icono.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setBounds(0, 0, 1000, 600);
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        add(panelPrincipal);

        crearPanelIzquierdo();
        crearPanelDerecho();
    }

    private void crearPanelIzquierdo() {
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(null);
        panelIzquierdo.setBounds(0, 0, 250, 600);
        panelIzquierdo.setBackground(new Color(244, 127, 59));
        panelPrincipal.add(panelIzquierdo);

        botonPrincipal = new JButton("PRINCIPAL");
        botonPerfil = new JButton("PERFIL");
        botonMatricula = new JButton("MATRÍCULA");
        botonCursos = new JButton("MIS CURSOS");
        botonAyuda = new JButton("AYUDA");
        botonSalir = new JButton("Salir");

        JButton[] botones = {botonPrincipal, botonPerfil, botonMatricula, botonCursos, botonAyuda, botonSalir};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setBounds(0, ((i + 3) * 60) - 40, 250, 60);
            botones[i].setForeground(Color.WHITE);
            botones[i].setContentAreaFilled(false);
            botones[i].setOpaque(false);
            botones[i].setBorderPainted(false);
            botones[i].setFocusPainted(false);
            botones[i].setFont(new Font("Segoe UI", Font.BOLD, 18));
            botones[i].addActionListener(this);
            panelIzquierdo.add(botones[i]);
        }

        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/resources/images/panel_logo.png")));
        logo.setBounds(20, 40, 220, 80);

        JLabel principal = new JLabel(new ImageIcon(getClass().getResource("/resources/images/principal.jpg")));
        principal.setBounds(40, 159, 25, 25);

        JLabel perfil = new JLabel(new ImageIcon(getClass().getResource("/resources/images/perfil.jpg")));
        perfil.setBounds(60, 220, 25, 28);

        JLabel matricula = new JLabel(new ImageIcon(getClass().getResource("/resources/images/matricula.jpg")));
        matricula.setBounds(32, 279, 25, 25);

        JLabel cursos = new JLabel(new ImageIcon(getClass().getResource("/resources/images/miscursos.jpg")));
        cursos.setBounds(32, 339, 25, 25);

        JLabel ayuda = new JLabel(new ImageIcon(getClass().getResource("/resources/images/ayuda.jpg")));
        ayuda.setBounds(60, 399, 25, 25);

        JLabel salir = new JLabel(new ImageIcon(getClass().getResource("/resources/images/salirpanel.jpg")));
        salir.setBounds(70, 459, 25, 25);

        panelIzquierdo.add(logo);
        panelIzquierdo.add(principal);
        panelIzquierdo.add(perfil);
        panelIzquierdo.add(matricula);
        panelIzquierdo.add(cursos);
        panelIzquierdo.add(ayuda);
        panelIzquierdo.add(salir);
    }

    private void crearPanelDerecho() {
        panelDerecho = new JPanel();
        panelDerecho.setLayout(null);
        panelDerecho.setBounds(250, 0, 750, 600);
        panelDerecho.setBackground(Color.WHITE);
        panelPrincipal.add(panelDerecho);

        mostrarPanel("Principal");
    }

    private void mostrarPanel(String nombrePanel) {
        panelDerecho.removeAll();

        JPanel nuevoPanel = null;

        switch (nombrePanel) {
            case "Principal":
                nuevoPanel = crearPanelPrincipal();
                break;
            case "Matrícula":
                nuevoPanel = crearPanelMatricula();
                break;
            case "Cursos":
                nuevoPanel = crearPanelCursos();
                break;
            case "Perfil":
                nuevoPanel = crearPanelPerfil();
                break;
            case "Ayuda":
                nuevoPanel = crearPanelAyuda();
                break;
        }

        if (nuevoPanel != null) {
            nuevoPanel.setBounds(0, 0, 750, 600);
            panelDerecho.add(nuevoPanel);
        }

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 600);
        panel.setBackground(Color.WHITE);

        PanelPrincipal panelPrincipal = new PanelPrincipal(estudiante);
        JPanel panelInterno = new JPanel(new BorderLayout());

        panelInterno.setBounds(0, 0, 750, 600);
        panelInterno.add(panelPrincipal, BorderLayout.CENTER);

        panel.add(panelInterno);

        return panel;
    }

    private JPanel crearPanelMatricula() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 600);

        JPanel panelInterno = new JPanel(new BorderLayout());
        panelInterno.setBounds(0, 0, 750, 600);

        System.out.println("Creando PanelMatricula para estudiante: " + estudiante.getCodigo() + ", Escuela: " + estudiante.getIdEscuela());
        PanelMatricula panelMatricula = new PanelMatricula(estudiante);

        panelInterno.add(panelMatricula, BorderLayout.CENTER);

        JScrollPane barra = new JScrollPane(panelInterno);
        barra.setBounds(0, 0, 740, 600);

        panel.add(barra);

        return panel;
    }

    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 600);

        PanelCursos panelCursos = new PanelCursos(estudiante);

        JPanel panelInterno = new JPanel(new BorderLayout());
        panelInterno.setBounds(0, 0, 750, 600);
        panelInterno.add(panelCursos, BorderLayout.CENTER);

        panel.add(panelInterno);

        return panel;
    }

    private JPanel crearPanelPerfil() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 600);

        PerfilUsuarioPanel panelPerfil = new PerfilUsuarioPanel(estudiante);

        JPanel panelInterno = new JPanel(new BorderLayout());
        panelInterno.setBounds(0, 0, 750, 600);
        panelInterno.add(panelPerfil, BorderLayout.CENTER);

        panel.add(panelInterno);

        return panel;
    }

    private JPanel crearPanelAyuda() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 600);

        PanelAyuda PanelAyuda = new PanelAyuda();

        JPanel panelInterno = new JPanel(new BorderLayout());
        panelInterno.setBounds(0, 0, 750, 600);
        panelInterno.add(PanelAyuda, BorderLayout.CENTER);

        panel.add(panelInterno);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonPrincipal) {
            mostrarPanel("Principal");
        } else if (e.getSource() == botonMatricula) {
            mostrarPanel("Matrícula");
        } else if (e.getSource() == botonCursos) {
            mostrarPanel("Cursos");
        } else if (e.getSource() == botonPerfil) {
            mostrarPanel("Perfil");
        } else if (e.getSource() == botonAyuda) {
            mostrarPanel("Ayuda");
        } else if (e.getSource() == botonSalir) {
            LoginUI ventana = new LoginUI();
            ventana.setVisible(true);
            this.dispose();
        }
    }

}
