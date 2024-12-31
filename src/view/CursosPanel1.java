package view;

import javax.swing.*;
import java.awt.*;
import controller.AdminController;
import model.Curso;
import model.MetodosDB;

public class CursosPanel1 extends JPanel {

    private AdminController controller;
    private JTextField txtIdCurso, txtNombreCurso, txtCreditos, txtTotalHoras, txtIdAula, txtCicloDisponible, txtAnio, txtIdEscuela;
    private JButton btnBuscar, btnInsertar, btnActualizar, btnEliminar;
    private JComboBox<String> comboEscuela;
    private JComboBox<String> comboAula;

    public CursosPanel1() {
        controller = new AdminController(this, new MetodosDB());
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBounds(10, 10, 760, 500);
        setBorder(BorderFactory.createTitledBorder("Gestión de Cursos"));
        setBackground(Color.WHITE);

        String[] labels = {"ID Curso", "Nombre Curso", "Créditos", "Total Horas", "ID Aula", "Ciclo Disponible", "Año", "ID Escuela"};
        JTextField[] textFields = {txtIdCurso, txtNombreCurso, txtCreditos, txtTotalHoras, txtIdAula, txtCicloDisponible, txtAnio, txtIdEscuela};
        String[] escuelas = {" ", "Ingeniería de Sistemas", "Ingeniería Industrial", "Ingenieria de Trasportes", "Ingenieria Agroindustrial"};
        String[] aulas = {"", "Salon", "Laboratorio"};
        comboEscuela = new JComboBox<>(escuelas);
        comboAula = new JComboBox<>(aulas);
        int y = 30;

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setBounds(20, y, 100, 25);
            lbl.setForeground(new Color(255, 110, 33));

            if (i == 4) { // ID Aula
                comboAula.setBounds(130, y, 200, 25);
                add(lbl);
                add(comboAula);
            } else if (i == 7) {
                comboEscuela.setBounds(130, y, 200, 25);
                add(lbl);
                add(comboEscuela);
            } else {
                textFields[i] = new JTextField();
                textFields[i].setBounds(130, y, 200, 25);
                add(lbl);
                add(textFields[i]);
            }
            y += 40;
        }

        txtIdCurso = textFields[0];
        txtNombreCurso = textFields[1];
        txtCreditos = textFields[2];
        txtTotalHoras = textFields[3];
        txtIdAula = textFields[4];
        txtCicloDisponible = textFields[5];
        txtAnio = textFields[6];
        txtIdEscuela = textFields[7];

        // Botones
        String[] buttons = {"Buscar", "Insertar", "Actualizar", "Eliminar"};
        int x = 20;
        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.setBounds(x, y, 100, 25);
            btn.setBackground(new Color(255, 110, 33));
            btn.setForeground(Color.WHITE);
            add(btn);
            x += 120;

            switch (button) {
                case "Buscar" -> {
                    btnBuscar = btn;
                    btnBuscar.addActionListener(e -> buscarCurso());
                }
                case "Insertar" -> {
                    btnInsertar = btn;
                    btnInsertar.addActionListener(e -> insertarCurso());
                }
                case "Actualizar" -> {
                    btnActualizar = btn;
                    btnActualizar.addActionListener(e -> actualizarCurso());
                }
                case "Eliminar" -> {
                    btnEliminar = btn;
                    btnEliminar.addActionListener(e -> eliminarCurso());
                }
            }
        }
    }

    private void buscarCurso() {
        if (controller == null) {
            mostrarMensaje("El controlador no está configurado.");
            return;
        }
        try {
            int idCurso = Integer.parseInt(txtIdCurso.getText());
            Curso curso = controller.buscarCurso(idCurso);
            if (curso != null) {
                mostrarCurso(curso);
            } else {
                mostrarMensaje("No se encontró el curso.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un ID de curso válido.");
        }
    }

    private void insertarCurso() {
        Curso curso = recogerInformacion();
        if (curso != null) {
            controller.guardarCurso(curso);
            limpiarCampos();
        }
    }

    private void actualizarCurso() {
        Curso curso = recogerInformacion();
        if (curso != null) {
            controller.modificarCurso(curso);
            limpiarCampos();
        }
    }

    private void eliminarCurso() {
        try {
            int idCurso = Integer.parseInt(txtIdCurso.getText());
            controller.eliminarCurso(idCurso);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un ID de curso válido.");
        }
    }

    public int Escuela(String escuela) {
        return switch (escuela) {
            case "Ingeniería de Sistemas" ->
                1;
            case "Ingeniería Industrial" ->
                2;
            case "Ingenieria de Trasportes" ->
                3;
            case "Ingenieria Agroindustrial" ->
                4;
            default ->
                0;
        };
    }

    public int Aula(String Aula) {
        return switch (Aula) {
            case "Salon" ->
                1;
            case "Laboratorio" ->
                2;
            default ->
                0;
        };
    }

    private Curso recogerInformacion() {
        try {
            int idCurso = txtIdCurso.getText().isEmpty() ? 0 : Integer.parseInt(txtIdCurso.getText());
            String nombreCurso = txtNombreCurso.getText();
            int creditos = Integer.parseInt(txtCreditos.getText());
            int totalHoras = Integer.parseInt(txtTotalHoras.getText());
            int idAula = Aula((String) comboAula.getSelectedItem());
            int cicloDisponible = Integer.parseInt(txtCicloDisponible.getText());
            int anio = Integer.parseInt(txtAnio.getText());
            int idEscuela = Escuela((String) comboEscuela.getSelectedItem());

            return new Curso(idCurso, nombreCurso, creditos, cicloDisponible, anio, idEscuela, "", totalHoras, idAula);
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese valores numéricos válidos en los campos correspondientes.");
            return null;
        }
    }

    public void mostrarCurso(Curso curso) {
        txtIdCurso.setText(String.valueOf(curso.getIdCurso()));
        txtNombreCurso.setText(curso.getNombre());
        txtCreditos.setText(String.valueOf(curso.getCreditos()));
        txtTotalHoras.setText(String.valueOf(curso.getTotalHoras()));
        comboAula.setSelectedIndex(curso.getIdAula());
        txtCicloDisponible.setText(String.valueOf(curso.getCicloDisponible()));
        txtAnio.setText(String.valueOf(curso.getAnio()));
        comboEscuela.setSelectedIndex(curso.getIdEscuela());
    }

    private void limpiarCampos() {
        txtIdCurso.setText("");
        txtNombreCurso.setText("");
        txtCreditos.setText("");
        txtTotalHoras.setText("");
        comboAula.setSelectedIndex(0);
        txtCicloDisponible.setText("");
        txtAnio.setText("");
        comboEscuela.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
