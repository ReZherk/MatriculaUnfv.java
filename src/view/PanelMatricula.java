package view;

import controller.MatriculaController;
import model.Curso;
import model.MetodosCurso;
import model.Estudiante;
import model.Seccion;
import model.Profesor;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.border.TitledBorder;

public class PanelMatricula extends JPanel {

    private MatriculaController controller;
    private JTable tablaCursos;
    private JTable tablaSeleccionados;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloSeleccionados;
    private JComboBox<String> comboAnio;
    private JComboBox<String> comboCiclo;
    private Estudiante estudiante;
    private JButton btnMatricular;
    private Map<String, String[]> ciclosPorAnio;
    
    private final Color COLOR_PRINCIPAL = new Color(244, 127, 59);
    private final Color COLOR_SECUNDARIO = Color.WHITE;
    private final Color COLOR_FONDO = new Color(250, 250, 250);
    private final Color COLOR_CABECERA_TABLA = new Color(244, 127, 59);
    private final Color COLOR_CELDA_ALTERNA = new Color(255, 245, 238);

    public PanelMatricula(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.controller = new MatriculaController(this, new MetodosCurso());
        initUI();
        cargarCursosIniciales();
    }

    private void initUI() {
        setLayout(null);
        setPreferredSize(new Dimension(750, 700));
        setBackground(COLOR_FONDO);

        JLabel lblTitulo = new JLabel("Matrícula: " + estudiante.getNombres() + " " + estudiante.getApellidos());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 19));
        lblTitulo.setForeground(COLOR_PRINCIPAL);
        lblTitulo.setBounds(20, 10, 600, 30);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("Es responsabilidad del alumno cumplir con las normas establecidas en el reglamento de matrícula.");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblInfo.setBounds(20, 40, 600, 20);
        add(lblInfo);

        JPanel panelCreditos = new JPanel();
        panelCreditos.setBackground(COLOR_SECUNDARIO);
        panelCreditos.setBorder(BorderFactory.createLineBorder(COLOR_PRINCIPAL, 1));
        panelCreditos.setBounds(20, 70, 400, 40);
        panelCreditos.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JLabel lblMinCred = new JLabel("Mín. Créd.: 12");
        JLabel lblMaxCred = new JLabel("Max. Créd.: 15");
        JLabel lblTotalCred = new JLabel("Total Créd.: 14");
        JLabel lblTotalCursos = new JLabel("Total Cursos: 7");

        panelCreditos.add(lblMinCred);
        panelCreditos.add(lblMaxCred);
        panelCreditos.add(lblTotalCred);
        panelCreditos.add(lblTotalCursos);
        add(panelCreditos);

        JPanel panelCursosRegistrados = new JPanel();
        panelCursosRegistrados.setBackground(COLOR_SECUNDARIO);
        panelCursosRegistrados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_PRINCIPAL, 1),
            "Cursos Registrados",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            COLOR_PRINCIPAL
        ));
        panelCursosRegistrados.setBounds(20, 130, 690, 200);
        panelCursosRegistrados.setLayout(null);

        modeloSeleccionados = new DefaultTableModel(new String[]{"Código", "Nombre del Curso", "Créd.", "Sección", "Profesor", "Horario1", "Horario2"}, 0);
        tablaSeleccionados = new JTable(modeloSeleccionados) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? getBackground() : COLOR_CELDA_ALTERNA);
                }
                return c;
            }
        };
        tablaSeleccionados.setRowHeight(25);
        tablaSeleccionados.getColumnModel().getColumn(1).setPreferredWidth(150);
        JTableHeader headerSeleccionados = tablaSeleccionados.getTableHeader();
        headerSeleccionados.setBackground(COLOR_CABECERA_TABLA);
        headerSeleccionados.setForeground(COLOR_SECUNDARIO);
        JScrollPane scrollSeleccionados = new JScrollPane(tablaSeleccionados);
        scrollSeleccionados.setBounds(20, 30, 650, 150);
        panelCursosRegistrados.add(scrollSeleccionados);

        add(panelCursosRegistrados);

        JPanel panelCursosDisponibles = new JPanel();
        panelCursosDisponibles.setBackground(COLOR_SECUNDARIO);
        panelCursosDisponibles.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_PRINCIPAL, 1),
            "Cursos Disponibles",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            COLOR_PRINCIPAL
        ));
        panelCursosDisponibles.setBounds(20, 350, 690, 300);
        panelCursosDisponibles.setLayout(null);

        comboAnio = new JComboBox<>(new String[]{"Primer Año", "Segundo Año", "Tercer Año", "Cuarto Año", "Quinto Año"});
        comboAnio.setBounds(20, 30, 150, 25);
        comboAnio.setBackground(Color.WHITE);
        panelCursosDisponibles.add(comboAnio);

        ciclosPorAnio = new HashMap<>();
        ciclosPorAnio.put("Primer Año", new String[]{"Primer ciclo", "Segundo ciclo"});
        ciclosPorAnio.put("Segundo Año", new String[]{"Tercer ciclo", "Cuarto ciclo"});
        ciclosPorAnio.put("Tercer Año", new String[]{"Quinto ciclo", "Sexto ciclo"});
        ciclosPorAnio.put("Cuarto Año", new String[]{"Septimo ciclo", "Octavo ciclo"});
        ciclosPorAnio.put("Quinto Año", new String[]{"Noveno ciclo", "Decimo ciclo"});

        comboAnio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboAnio.getSelectedItem() != null) {
                    String año = (String) comboAnio.getSelectedItem();
                    actualizarCiclo(año);
                    actualizarCursos();
                }
            }
        });

        comboCiclo = new JComboBox<>();
        comboCiclo.setBounds(180, 30, 150, 25);
        comboCiclo.setBackground(Color.WHITE);
        comboCiclo.addItem("Primer ciclo");
        comboCiclo.addItem("Segundo ciclo");
        panelCursosDisponibles.add(comboCiclo);

        comboCiclo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboCiclo.getSelectedItem() != null) {
                    actualizarCursos();
                }
            }
        });

        modeloTabla = new DefaultTableModel(new String[]{"", "Código", "Nombre del Curso", "Créd.", "Sección", "Profesor", "Horario1", "Horario2", "ID_SECCION"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                } else {
                    return String.class;
                }
            }
        };
        tablaCursos = new JTable(modeloTabla) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? getBackground() : COLOR_CELDA_ALTERNA);
                }
                return c;
            }
        };
        tablaCursos.setRowHeight(25);
        JTableHeader headerCursos = tablaCursos.getTableHeader();
        headerCursos.setBackground(COLOR_CABECERA_TABLA);
        headerCursos.setForeground(COLOR_SECUNDARIO);
        JScrollPane scrollCursos = new JScrollPane(tablaCursos);
        scrollCursos.setBounds(20, 70, 650, 180);
        panelCursosDisponibles.add(scrollCursos);

        tablaCursos.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 0) {
                actualizarSeleccionados();
            }
        });

        tablaCursos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablaCursos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tablaCursos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaCursos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablaCursos.getColumnModel().getColumn(4).setPreferredWidth(50);
        tablaCursos.getColumnModel().getColumn(5).setPreferredWidth(120);
        tablaCursos.getColumnModel().getColumn(6).setPreferredWidth(150);
        tablaCursos.getColumnModel().getColumn(7).setPreferredWidth(150);

        tablaCursos.getColumnModel().getColumn(8).setMinWidth(0);
        tablaCursos.getColumnModel().getColumn(8).setMaxWidth(0);
        tablaCursos.getColumnModel().getColumn(8).setWidth(0);

        btnMatricular = new JButton("Matricular");
        btnMatricular.setBounds(20, 260, 100, 25);
        btnMatricular.setBackground(COLOR_PRINCIPAL);
        btnMatricular.setForeground(Color.WHITE);
        btnMatricular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matricular();
            }
        });
        panelCursosDisponibles.add(btnMatricular);

        add(panelCursosDisponibles);
    }

    private void actualizarCiclo(String año) {
        comboCiclo.removeAllItems();
        String[] ciclos = ciclosPorAnio.get(año);

        if (ciclos != null) {
            for (String ciclo : ciclos) {
                comboCiclo.addItem(ciclo);
            }
        }
    }

    private void actualizarCursos() {
        if (estudiante != null && comboAnio.getSelectedItem() != null && comboCiclo.getSelectedItem() != null) {
            modeloTabla.setRowCount(0);
            modeloSeleccionados.setRowCount(0);

            int anio = obtenerAnioPorSeleccion((String) comboAnio.getSelectedItem());
            int ciclo = obtenerCicloPorSeleccion((String) comboCiclo.getSelectedItem());

            if (comboAnio.getSelectedItem() == null || comboCiclo.getSelectedItem() == null) {
                mostrarMensajeError("Debe seleccionar un año y un ciclo antes de continuar.");
                return;
            }

            System.out.println("Actualizando cursos para: ");
            System.out.println("- Escuela: " + estudiante.getIdEscuela());
            System.out.println("- Año: " + anio);
            System.out.println("- Ciclo: " + ciclo);

            SwingUtilities.invokeLater(() -> {
                controller.cargarCursosPorEscuelaAnioCiclo(estudiante.getIdEscuela(), anio, ciclo);
            });
        }
    }

    public void mostrarCursos(List<Curso> cursos) {
        modeloTabla.setRowCount(0);
        for (Curso curso : cursos) {
            List<Seccion> secciones = controller.obtenerSeccionesPorCurso(curso.getIdCurso());
            for (Seccion seccion : secciones) {
                modeloTabla.addRow(new Object[]{
                    false,
                    curso.getIdCurso(),
                    curso.getNombre(),
                    curso.getCreditos(),
                    seccion.getNombreSeccion(),
                    seccion.getProfesor().getNombre() + " " + seccion.getProfesor().getApellido(),
                    seccion.getHorario1(),
                    seccion.getHorario2(),
                    seccion.getIdSeccion() // Agregar ID_SECCION como columna oculta
                });
                System.out.println("Curso: " + curso.getNombre() + ", Horario2: " + seccion.getHorario2());
            }
        }
    }

    private void actualizarSeleccionados() {
        modeloSeleccionados.setRowCount(0);
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Boolean seleccionado = (Boolean) modeloTabla.getValueAt(i, 0);
            if (seleccionado) {
                modeloSeleccionados.addRow(new Object[]{
                    modeloTabla.getValueAt(i, 1),
                    modeloTabla.getValueAt(i, 2),
                    modeloTabla.getValueAt(i, 3),
                    modeloTabla.getValueAt(i, 4),
                    modeloTabla.getValueAt(i, 5),
                    modeloTabla.getValueAt(i, 6),
                    modeloTabla.getValueAt(i, 7)
                });
            }
        }
    }

    private int obtenerAnioPorSeleccion(String anio) {
        switch (anio) {
            case "Primer Año":
                return 1;
            case "Segundo Año":
                return 2;
            case "Tercer Año":
                return 3;
            case "Cuarto Año":
                return 4;
            case "Quinto Año":
                return 5;
            default:
                return 0;
        }
    }

    private int obtenerCicloPorSeleccion(String ciclo) {
        return switch (ciclo) {
            case "Primer ciclo" -> 1;
            case "Segundo ciclo" -> 2;
            case "Tercer ciclo" -> 3;
            case "Cuarto ciclo" -> 4;
            case "Quinto ciclo" -> 5;
            case "Sexto ciclo" -> 6;
            case "Septimo ciclo" -> 7;
            case "Octavo ciclo" -> 8;
            case "Noveno ciclo" -> 9;
            case "Decimo ciclo" -> 10;
            default -> 0;
        };
    }

    private void matricular() {
        List<Curso> cursosSeleccionados = new ArrayList<>();
        List<Seccion> seccionesSeleccionadas = new ArrayList<>();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Boolean seleccionado = (Boolean) modeloTabla.getValueAt(i, 0);
            if (seleccionado) {
                Curso curso = new Curso();
                curso.setIdCurso((Integer) modeloTabla.getValueAt(i, 1));
                curso.setNombre((String) modeloTabla.getValueAt(i, 2));
                curso.setCreditos((Integer) modeloTabla.getValueAt(i, 3));
                cursosSeleccionados.add(curso);

                String nombreProfesor = ((String) modeloTabla.getValueAt(i, 5)).split(" ")[0];
                String apellidoProfesor = ((String) modeloTabla.getValueAt(i, 5)).split(" ")[1];
                Profesor profesor = new Profesor(0, nombreProfesor, apellidoProfesor, "");

                Seccion seccion = new Seccion(
                        (Integer) modeloTabla.getValueAt(i, 8),
                        curso.getIdCurso(),
                        profesor,
                        (String) modeloTabla.getValueAt(i, 4), // NOMBRE_SECCION
                        (String) modeloTabla.getValueAt(i, 6), // HORARIO1
                        (String) modeloTabla.getValueAt(i, 7) //HORARIO2
                );
                seccionesSeleccionadas.add(seccion);
            }
        }

        if (cursosSeleccionados.isEmpty()) {
            mostrarMensajeError("Debe seleccionar al menos un curso para matricularse");
            return;
        }

        int anio = obtenerAnioPorSeleccion((String) comboAnio.getSelectedItem());
        int ciclo = obtenerCicloPorSeleccion((String) comboCiclo.getSelectedItem());
        controller.matricularEstudiante(estudiante, cursosSeleccionados, seccionesSeleccionadas, anio, ciclo);
    }

    private void cargarCursosIniciales() {
        if (estudiante != null) {
            if (comboAnio.getSelectedItem() == null) {
                comboAnio.setSelectedIndex(0);
            }
            if (comboCiclo.getSelectedItem() == null) {
                comboCiclo.setSelectedIndex(0);
            }

            actualizarCursos();
        } else {
            System.err.println("Error: El objeto estudiante es nulo en PanelMatricula");
            mostrarMensajeError("Error al cargar los datos del estudiante");
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}