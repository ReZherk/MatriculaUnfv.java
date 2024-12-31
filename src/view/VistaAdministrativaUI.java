package view;

import javax.swing.*;

public class VistaAdministrativaUI extends JFrame {

    private MatriculadosPanel panelMatriculados;
    private EstudiantePanel panelEstudiante;
    private HorariosPanel panelHorarios;
    private CursosPanel1 panelCursos;
    private ProfesoresPanel panelProfesores;

    public VistaAdministrativaUI() {
        setTitle("Gestión de Datos");
        setIconImage(new ImageIcon(getClass().getResource("/resources/images/icono.png")).getImage());
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuGestion = new JMenu("Opciones");
        JMenuItem cerrar = new JMenuItem("Salir");
        JMenuItem menuMatriculados = new JMenuItem("Buscar Matriculados");
        JMenuItem menuEstudiante = new JMenuItem("Gestionar Estudiante");
        JMenuItem menuHorarios = new JMenuItem("Gestionar Horarios");
        JMenuItem menuCursos = new JMenuItem("Gestionar Cursos");
        JMenuItem menuProfesores = new JMenuItem("Gestionar Profesores");

        menuGestion.add(menuMatriculados);
        menuGestion.add(menuEstudiante);
        menuGestion.add(menuHorarios);
        menuGestion.add(menuCursos);
        menuGestion.add(menuProfesores);
        menuGestion.add(cerrar);
        menuBar.add(menuGestion);
        setJMenuBar(menuBar);

        panelEstudiante = new EstudiantePanel();
        panelHorarios = new HorariosPanel();
        panelCursos = new CursosPanel1();
        panelProfesores = new ProfesoresPanel();
        panelMatriculados = new MatriculadosPanel();

        add(panelMatriculados);
        add(panelEstudiante);
        add(panelHorarios);
        add(panelCursos);
        add(panelProfesores);

        OpcionSeleccionada(panelMatriculados);

        cerrar.addActionListener(e -> salir());
        menuMatriculados.addActionListener(e -> OpcionSeleccionada(panelMatriculados));
        menuEstudiante.addActionListener(e -> OpcionSeleccionada(panelEstudiante));
        menuHorarios.addActionListener(e -> OpcionSeleccionada(panelHorarios));
        menuCursos.addActionListener(e -> OpcionSeleccionada(panelCursos));
        menuProfesores.addActionListener(e -> OpcionSeleccionada(panelProfesores));

        setVisible(true);
    }

    private void salir() {
        this.dispose();
        LoginUI ventana = new LoginUI();
        ventana.setVisible(true);
    }

    private void OpcionSeleccionada(JPanel panelSeleccionado) {
        panelMatriculados.setVisible(false);
        panelEstudiante.setVisible(false);
        panelHorarios.setVisible(false);
        panelCursos.setVisible(false);
        panelProfesores.setVisible(false);

        if (panelSeleccionado != null) {
            panelSeleccionado.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new VistaAdministrativaUI();
    }
}
