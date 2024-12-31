package view;

import model.Estudiante;
import controller.MatriculaRealizadaController;

import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class PanelCursos extends JPanel {

    public Estudiante estudiante;
    public JTable tabla, tablaHorario;
    public DefaultTableModel modelo, modeloHorario;
    public MatriculaRealizadaController controller;
    public JScrollPane barra, barraHorario, scrollPaneP;
    public JButton btnBuscar, btnImprimir;  
    public JTextField txtAño;  
    public JComboBox<String> cmbSemest;
    public JPanel panelPrincipal;
    public JLabel icono, lblHorario, lblCursos, encabezado, lblAño, lblSemest, encabezado2, encabezado3, salón, lab, choque;

    public PanelCursos(Estudiante estudiante) {
        this.estudiante = estudiante; // Guarda la información del estudiante logueado
        inicializarPanelPrincipal(); // Configura el panel principal con scroll
        this.controller = new MatriculaRealizadaController(estudiante, this); // Conectar la vista con el controlador
    }

    private void inicializarPanelPrincipal() {

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setPreferredSize(new Dimension(750, 1400));

        scrollPaneP = new JScrollPane(panelPrincipal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneP.setPreferredSize(new Dimension(750, 600));
        scrollPaneP.getVerticalScrollBar().setUI(new BasicScrollBarUI() {          
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(20, super.getPreferredSize(c).height); //Ancho del scroll
            }         
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(244, 126, 58); // Color de la barra
                this.trackColor = new Color(240, 240, 240); // Color de fondo
            }
        });

        setLayout(new BorderLayout());
        add(scrollPaneP, BorderLayout.CENTER);
        inicializarPanel(panelPrincipal); 
    }

    private void inicializarPanel(JPanel panel) {

        lblCursos = new JLabel("Cursos Matriculados");
        lblCursos.setBounds(170, 10, 500, 70);
        lblCursos.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblCursos.setForeground(new Color(191, 33, 5));
        lblCursos.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(lblCursos);

        lblAño = new JLabel("Año lectivo:");
        lblAño.setBounds(95, 99, 100, 30);
        lblAño.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblAño.setForeground(new Color(194, 47, 1));
        panel.add(lblAño);

        txtAño = new JTextField();
        txtAño.setBounds(184, 103, 95, 25);
        txtAño.setBorder(BorderFactory.createEmptyBorder());
        txtAño.setForeground(new Color(194, 47, 1));
        txtAño.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAño.addKeyListener(new KeyAdapter() { //KeyListener para limitar la entrada a solo 4 dígitos
            @Override
            public void keyTyped(KeyEvent e) {
                String text = txtAño.getText();
                if (text.length() >= 4 || !Character.isDigit(e.getKeyChar())) { // Permitir solo números y limitar la longitud a 4 caracteres
                    e.consume();  // Si el texto tiene 4 caracteres o la tecla presionada no es un dígito, ignorar la entrada
                }
            }
        });
        panel.add(txtAño);

        lblSemest = new JLabel("Semestre:");
        lblSemest.setBounds(330, 99, 100, 30);
        lblSemest.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSemest.setForeground(new Color(194, 47, 1));
        panel.add(lblSemest);

        cmbSemest = new JComboBox<>();
        cmbSemest.addItem(" ------ "); 
        cmbSemest.addItem(" Primero");
        cmbSemest.addItem(" Segundo");
        cmbSemest.setBounds(410, 103, 95, 25);
        cmbSemest.setBackground(Color.WHITE); // Relleno blanco        
        cmbSemest.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbSemest.setForeground(new Color(194, 47, 1));
        cmbSemest.setBorder(BorderFactory.createEmptyBorder());
        panel.add(cmbSemest);
           
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(600, 103, 75, 25);
        btnBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(243, 116, 42));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> visualizarConsulta());
        panel.add(btnBuscar);
        
        encabezado = new JLabel(new ImageIcon(getClass().getResource("/resources/images/seleccionCurso.PNG")));
        encabezado.setBounds(30, 75, 670, 80);
        panel.add(encabezado);
        
        String[] titulo = {"CÓDIGO", "NOMBRE DEL CURSO", "CRÉDITOS", "SECCIÓN", "HORARIO", "PROFESOR"};
        tabla = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Ninguna celda será editable
            }
        };

        modelo = new DefaultTableModel(null, titulo);
        
        // Personalizar encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        header.setBackground(new Color(255, 186, 145));
        header.setForeground(new Color(194,42,12));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        tabla.setModel(modelo);
        tabla.getTableHeader().setReorderingAllowed(false); // No permite reordenar columnas
        tabla.setGridColor(Color.WHITE); // Líneas internas blancas
        tabla.setShowGrid(true); // Asegura que las líneas sean visibles 
        tabla.setBackground(new Color(255, 221, 171));
        tabla.setForeground(new Color(183,87,30));
        tabla.setOpaque(true);

        // Personalizar celdas
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setRowHeight(30);
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER); // Centrado
                return c;
            }
        });
        
        barra = new JScrollPane(tabla);
        barra.setBounds(50, 170, 625, 325);
        barra.getViewport().setBackground(Color.WHITE); // Fondo blanco
        panel.add(barra);
        
        lblHorario = new JLabel("Horario");
        lblHorario.setBounds(305, 510, 500, 100);
        lblHorario.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblHorario.setForeground(new Color(191, 33, 5));
        lblHorario.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(lblHorario);
        
        salón = new JLabel("Salón");
        salón.setBounds(107, 612, 100, 30);
        salón.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        salón.setForeground(new Color(194, 47, 1));
        panel.add(salón);
        
        lab = new JLabel("Laboratorio");
        lab.setBounds(270, 612, 100, 30);
        lab.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lab.setForeground(new Color(194, 47, 1));
        panel.add(lab);
        
        choque= new JLabel("Cruce de Horarios");
        choque.setBounds(484, 612, 200, 30);
        choque.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        choque.setForeground(new Color(194, 47, 1));
        panel.add(choque);
        
        String[] dias = {"HORA", "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO"};
        tablaHorario = new JTable();
        barraHorario = new JScrollPane();
        modeloHorario = new DefaultTableModel(null, dias);

        int horaInicial = 8;
        int minutoInicial = 0;
        int intervaloMinutos = 50;

        for (int i = 0; i < 14; i++) {
            Object[] row = new Object[7]; // 6 días + 1 columna para el intervalo de horas

            int horaInicio = horaInicial + (minutoInicial + (i * intervaloMinutos)) / 60;
            int minutoInicio = (minutoInicial + (i * intervaloMinutos)) % 60;
            int horaFin = horaInicial + (minutoInicial + ((i + 1) * intervaloMinutos)) / 60;
            int minutoFin = (minutoInicial + ((i + 1) * intervaloMinutos)) % 60;

            // Formatea los tiempos como hh:mm
            String tiempoInicio = String.format("%02d:%02d", horaInicio, minutoInicio);
            String tiempoFin = String.format("%02d:%02d", horaFin, minutoFin);

            // Asigna el intervalo de tiempo a la primera columna
            row[0] = tiempoInicio + " - " + tiempoFin;

            modeloHorario.addRow(row);
        }

        tablaHorario.setModel(modeloHorario);
        tablaHorario.getTableHeader().setReorderingAllowed(false); // No permite reordenar columnas
        tablaHorario.setGridColor(new Color(255, 245, 229)); // Líneas internas 
        tablaHorario.setShowGrid(true); // Asegura que las líneas sean visibles 
        tablaHorario.setBackground(new Color(255, 211, 171)); // Fondo de 
        tablaHorario.setOpaque(true);
        tablaHorario.setRowHeight(32);

        JTableHeader headerHorario = tablaHorario.getTableHeader();
        headerHorario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        headerHorario.setBackground(new Color(255, 189, 151));
        headerHorario.setForeground(new Color(214, 98, 54));
        ((DefaultTableCellRenderer) headerHorario.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        class CustomCellRenderer extends DefaultTableCellRenderer {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Configura la fuente para todas las celdas
                cell.setFont(new Font("Segoe UI", Font.BOLD, 12));
                setHorizontalAlignment(CENTER); // Centra el texto en todas las celdas

                if (column == 0) {
                    cell.setBackground(new Color(255, 189, 151)); // Fondo de columna 0
                    setForeground(new Color(211, 90, 46));
                } else {
                    if (value != null && !value.toString().isEmpty()) {
                    String contenido = value.toString();
                    String[] cursos = contenido.split("/"); // Divide los cursos concatenados
                    setForeground(Color.WHITE);
                
                    // Construir el texto visible en la celda (mostrar solo los nombres de los cursos)
                    StringBuilder textoVisible = new StringBuilder();
                    for (String cursoAula : cursos) {
                        if (cursoAula.contains("|")) {
                            String[] partes = cursoAula.split("\\|");
                            textoVisible.append(partes[0]).append(" / "); // Agrega el curso al texto visible
                        }
                    }

                    setText(textoVisible.substring(0, textoVisible.length() - 3)); // Elimina el último separador "/ "

                    if (cursos.length > 1) {
                        cell.setBackground(new Color(215, 67, 21)); // Choque de Cursos
                    } else {
                        String[] partes = cursos[0].split("\\|");
                        int idAula = Integer.parseInt(partes[1].trim());
                        if (idAula == 1) {
                            cell.setBackground(new Color(255, 134, 64)); // Salón
                        } else if (idAula == 2) {
                            cell.setBackground(new Color(147, 178, 181)); // Laboratorio
                          }
                        }
                } else {
                    cell.setBackground(new Color(255, 211, 171)); // Vacías
                }
            }
            return cell;
        }    
    }

        // Asignar el renderizador personalizado
        tablaHorario.setDefaultRenderer(Object.class, new CustomCellRenderer());
        barraHorario.setViewportView(tablaHorario);
        barraHorario.setBounds(56, 689, 620, 475);
        panel.add(barraHorario);

        tablaHorario.setDefaultRenderer(Object.class, new CustomCellRenderer());
        
        encabezado2 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/HorarioFondo2.PNG")));
        encabezado2.setBounds(30, 750, 670, 600);
        panel.add(encabezado2);
        encabezado3 = new JLabel(new ImageIcon(getClass().getResource("/resources/images/HorarioFondo.PNG")));
        encabezado3.setBounds(30, 510, 670, 480);
        panel.add(encabezado3);
        
        // Crear el botón "Imprimir"
        btnImprimir = new JButton("DESCARGAR MATRÍCULA");
        btnImprimir.setBounds(240, 1261, 300, 30);
        btnImprimir.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btnImprimir.setForeground(Color.WHITE);
        btnImprimir.setBackground(new Color(243, 116, 42));
        btnImprimir.setBorderPainted(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.addActionListener(e -> imprimirMatricula());
        panel.add(btnImprimir);
        
        icono = new JLabel(new ImageIcon(getClass().getResource("/resources/images/Descargar.PNG")));
        icono.setBounds(170, 1200, 400, 150);
        panel.add(icono);
    }

    private Object[] obtenerConsulta() {
        String año = txtAño.getText().trim();  // Obtener el código ingresado (4 dígitos) y eliminar espacios al principio y al final
        // Inicializar el array de cicloParametros
        int[] cicloSemestre = {}; 

        // Validación para el código vacío
        if (año.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un año.");
            return null;  // Salir del método si el código está vacío
        }

        // Validación para el ciclo seleccionado (índice 0 significa que no se seleccionó nada)
        else if (cmbSemest.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un semestre.");
            return null;  // Salir del método si no se ha seleccionado un semestre
        } else {
            // Asignar los valores del ciclo según la selección
            if (cmbSemest.getSelectedIndex() == 1) {  // Si se selecciona el ciclo "Primero"
                cicloSemestre = new int[]{1, 3, 5, 7, 9};  // Ciclo primero (números impares)
            } else if (cmbSemest.getSelectedIndex() == 2) {  // Si se selecciona el ciclo "Segundo"
                cicloSemestre = new int[]{2, 4, 6, 8, 10};  // Ciclo segundo (números pares)
            }
            
            return new Object[]{año, cicloSemestre};
        }
       
    }
    
    private void visualizarConsulta() {
        Object[] consulta = obtenerConsulta();
    
         if (consulta != null) {
            String año = (String) consulta[0];
            int[] cicloSemestre = (int[]) consulta[1];
        
            // Llamar a los métodos para listar los datos de matrícula y pintar el horario
            ListarDatosdeMatrícula(año, cicloSemestre);
            PintarHorario(año, cicloSemestre);
        }
    }

    // Llama al controlador para listar los datos de matrícula con los parámetros proporcionados
    public void ListarDatosdeMatrícula(String añoM, int[] ciclos) {
        controller.ListarDatosMatriculaControlador(añoM, ciclos);
    }

    // Llama al controlador para pintar el horario con los parámetros proporcionados
    public void PintarHorario(String añoM, int[] ciclos) {
        controller.pintarInterseccionHorario(añoM, ciclos);
    }
    
    
    private void imprimirMatricula() {
        Object[] consulta = obtenerConsulta();
    
        if (consulta != null) {
            String año = (String) consulta[0];
            int[] cicloSemestre = (int[]) consulta[1];
            int semestreSeleccionado = cmbSemest.getSelectedIndex();
        
            // Generar el PDF utilizando el controlador
            controller.generarPDFMatricula(año, cicloSemestre, semestreSeleccionado);
            JOptionPane.showMessageDialog(this, "Su descarga fue exitosa.");
        } 
    }
    
}