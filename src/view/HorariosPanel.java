package view;

import controller.AdminController2;
import model.MetodosCurso;
import model.Curso;
import model.Seccion;
import model.Profesor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HorariosPanel extends JPanel {

    private AdminController2 controller;
    private JTextField campoCurso;
    private JButton botonBuscar, botonLimpiar, botonInsertar, botonEliminar;
    private JTable tablaCursos, tablaHorarios;
    private DefaultTableModel modeloTablaCursos, modeloTablaHorarios;
    private JScrollPane scrollTablaCursos, scrollTablaHorarios;

    public HorariosPanel() {
        controller = new AdminController2(this, new MetodosCurso());
        crearVentana();
    }

    private void crearVentana() {
        setLayout(new BorderLayout());
        setBounds(10, 10, 760, 500);
        setBorder(BorderFactory.createTitledBorder("Gestión de Horarios"));
        setBackground(Color.WHITE);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelCurso = new JLabel("Curso:");
        campoCurso = new JTextField(20);
        botonBuscar = new JButton("Buscar");
        botonBuscar.setBackground(new Color(255, 110, 33));
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.addActionListener(e -> buscarCursos());
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBackground(new Color(255, 110, 33));
        botonLimpiar.setForeground(Color.WHITE);
        botonLimpiar.addActionListener(e -> limpiarCampos());

        panelBusqueda.add(labelCurso);
        panelBusqueda.add(campoCurso);
        panelBusqueda.add(botonBuscar);
        panelBusqueda.add(botonLimpiar);

        add(panelBusqueda, BorderLayout.NORTH);

        String[] columnasCursos = {"ID", "Nombre", "Créditos", "Ciclo", "Año", "Escuela"};
        modeloTablaCursos = new DefaultTableModel(columnasCursos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCursos = new JTable(modeloTablaCursos);
        tablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCursos.getSelectionModel().addListSelectionListener(e -> cargarHorarios());

        scrollTablaCursos = new JScrollPane(tablaCursos);

        String[] columnasHorarios = {"ID", "Sección", "Horario 1", "Horario 2", "Profesor"};
        modeloTablaHorarios = new DefaultTableModel(columnasHorarios, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaHorarios = new JTable(modeloTablaHorarios);

        scrollTablaHorarios = new JScrollPane(tablaHorarios);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTablaCursos, scrollTablaHorarios);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonInsertar = new JButton("Insertar Horario");
        botonInsertar.setBackground(new Color(255, 110, 33));
        botonInsertar.setForeground(Color.WHITE);
        botonInsertar.addActionListener(e -> insertarHorario());
        botonEliminar = new JButton("Eliminar Horario");
        botonEliminar.setBackground(new Color(255, 110, 33));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.addActionListener(e -> eliminarHorario());

        //panelBotones.add(botonInsertar);
        panelBotones.add(botonEliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscarCursos() {
        String nombreCurso = campoCurso.getText().trim();
        if (nombreCurso.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del curso para buscar.");
            return;
        }

        List<Curso> cursos = controller.buscarCursos(nombreCurso);
        modeloTablaCursos.setRowCount(0);
        for (Curso curso : cursos) {
            modeloTablaCursos.addRow(new Object[]{
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getCreditos(),
                curso.getCicloDisponible(),
                curso.getAnio(),
                curso.getNombreEscuela()
            });
        }

        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron cursos con ese nombre.");
        }
    }

    private void cargarHorarios() {
        int filaSeleccionada = tablaCursos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idCurso = (int) modeloTablaCursos.getValueAt(filaSeleccionada, 0);
            List<Seccion> secciones = controller.obtenerSeccionesPorCurso(idCurso);

            modeloTablaHorarios.setRowCount(0);
            for (Seccion seccion : secciones) {
                modeloTablaHorarios.addRow(new Object[]{
                    seccion.getIdSeccion(),
                    seccion.getNombreSeccion(),
                    seccion.getHorario1(),
                    seccion.getHorario2(),
                    seccion.getProfesor().getNombre() + " " + seccion.getProfesor().getApellido()
                });
            }
        }
    }

    private void limpiarCampos() {
        campoCurso.setText("");
        modeloTablaCursos.setRowCount(0);
        modeloTablaHorarios.setRowCount(0);
    }

    private void insertarHorario() {
        int filaSeleccionada = tablaCursos.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso primero.");
            return;
        }

        int idCurso = (int) modeloTablaCursos.getValueAt(filaSeleccionada, 0);
        String nombreCurso = (String) modeloTablaCursos.getValueAt(filaSeleccionada, 1);

        JTextField campoSeccion = new JTextField(5);
        JComboBox<String> comboDia1 = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"});
        JComboBox<String> comboHoraInicio1 = new JComboBox<>(generarHoras());
        JComboBox<String> comboHoraFin1 = new JComboBox<>(generarHoras());
        JComboBox<String> comboDia2 = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"});
        JComboBox<String> comboHoraInicio2 = new JComboBox<>(generarHoras());
        JComboBox<String> comboHoraFin2 = new JComboBox<>(generarHoras());
        JComboBox<Profesor> comboProfesor = new JComboBox<>(obtenerProfesores());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Sección:"));
        panel.add(campoSeccion);
        panel.add(new JLabel("Día 1:"));
        panel.add(comboDia1);
        panel.add(new JLabel("Hora Inicio 1:"));
        panel.add(comboHoraInicio1);
        panel.add(new JLabel("Hora Fin 1:"));
        panel.add(comboHoraFin1);
        panel.add(new JLabel("Día 2:"));
        panel.add(comboDia2);
        panel.add(new JLabel("Hora Inicio 2:"));
        panel.add(comboHoraInicio2);
        panel.add(new JLabel("Hora Fin 2:"));
        panel.add(comboHoraFin2);
        panel.add(new JLabel("Profesor:"));
        panel.add(comboProfesor);

        int result = JOptionPane.showConfirmDialog(null, panel, "Insertar Horario para " + nombreCurso,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Seccion nuevaSeccion = new Seccion();
            nuevaSeccion.setIdCurso(idCurso);
            nuevaSeccion.setNombreSeccion(campoSeccion.getText());
            nuevaSeccion.setHorario1(comboDia1.getSelectedItem() + " " + comboHoraInicio1.getSelectedItem() + "-" + comboHoraFin1.getSelectedItem());
            nuevaSeccion.setHorario2(comboDia2.getSelectedItem() + " " + comboHoraInicio2.getSelectedItem() + "-" + comboHoraFin2.getSelectedItem());
            nuevaSeccion.setProfesor((Profesor) comboProfesor.getSelectedItem());

            boolean exito = controller.insertarHorario(nuevaSeccion);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Horario insertado correctamente.");
                cargarHorarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar el horario.");
            }
        }
    }

    private void eliminarHorario() {
        int filaSeleccionada = tablaHorarios.getSelectedRow();
        int filaSeleccionadaCurso = tablaCursos.getSelectedRow();

        System.out.println("Las filas de tabla curso es:" + filaSeleccionadaCurso + " y de tabla horario" + filaSeleccionada);
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un horario para eliminar.");
            return;
        }

        int idSeccion = (int) modeloTablaHorarios.getValueAt(filaSeleccionada, 0);
        int idCurso = (int) modeloTablaCursos.getValueAt(filaSeleccionadaCurso, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar este horario?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = controller.eliminarHorario(idSeccion, idCurso);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Horario eliminado correctamente.");
                cargarHorarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el horario.");
            }
        }
    }

    
    
    public AdminController2 getController() {
        return controller;
    }

    private String[] generarHoras() {
        String[] horas = new String[24];
        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d:00", i);
        }
        return horas;
    }

    private Profesor[] obtenerProfesores() {
        // Aquí deberías obtener la lista de profesores de la base de datos
        // Por ahora, retornamos un array de ejemplo
        return new Profesor[]{
            new Profesor(1, "Juan", "Pérez", "juan@universidad.edu"),
            new Profesor(2, "María", "González", "maria@universidad.edu")
        };
    }
}
