package view;

import model.MetodosDB;
import javax.swing.*;
import java.awt.*;
import controller.AdminController;
import model.Profesor;

public class ProfesoresPanel extends JPanel {
    private AdminController controller;
    private JTextField txtIdProfesor, txtNombre, txtApellido, txtEmail;
    private JButton btnBuscar, btnInsertar, btnActualizar, btnEliminar;

    public ProfesoresPanel() {
        controller=new AdminController(this, new MetodosDB());
        initComponents();
    }

   

    private void initComponents() {
        setLayout(null);
        setBounds(10, 10, 760, 500);
        setBorder(BorderFactory.createTitledBorder("Gestión de Profesores"));
        setBackground(Color.WHITE);

        String[] labels = {"ID Profesor", "Nombre", "Apellido", "Email"};
        int y = 30;
        for (String label : labels) {
            JLabel lbl = new JLabel(label + ":");
            lbl.setBounds(20, y, 100, 25);
            lbl.setForeground(new Color(255, 110, 33));
            JTextField txt = new JTextField();
            txt.setBounds(130, y, 200, 25);
            add(lbl);
            add(txt);
            y += 40;

            switch (label) {
                case "ID Profesor" -> txtIdProfesor = txt;
                case "Nombre" -> txtNombre = txt;
                case "Apellido" -> txtApellido = txt;
                case "Email" -> txtEmail = txt;
            }
        }

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
                    btnBuscar.addActionListener(e -> buscarProfesor());
                }
                case "Insertar" -> {
                    btnInsertar = btn;
                    btnInsertar.addActionListener(e -> insertarProfesor());
                }
                case "Actualizar" -> {
                    btnActualizar = btn;
                    btnActualizar.addActionListener(e -> actualizarProfesor());
                }
                case "Eliminar" -> {
                    btnEliminar = btn;
                    btnEliminar.addActionListener(e -> eliminarProfesor());
                }
            }
        }
    }

    private void buscarProfesor() {
        
        try {
            int idProfesor = Integer.parseInt(txtIdProfesor.getText());
            Profesor profesor = controller.buscarProfesor(idProfesor);
            if (profesor != null) {
                mostrarProfesor(profesor);
            } else {
                mostrarMensaje("No se encontró el profesor.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un ID de profesor válido.");
        }
    }

    private void insertarProfesor() {
        
        Profesor profesor = recogerInformacion();
        if (profesor != null) {
            controller.guardarProfesor(profesor);
            limpiarCampos();
        }
    }

    private void actualizarProfesor() {
        if (controller == null) {
            mostrarMensaje("El controlador no está configurado.");
            return;
        }
        Profesor profesor = recogerInformacion();
        if (profesor != null) {
            controller.modificarProfesor(profesor);
            limpiarCampos();
        }
    }

    private void eliminarProfesor() {
        if (controller == null) {
            mostrarMensaje("El controlador no está configurado.");
            return;
        }
        try {
            int idProfesor = Integer.parseInt(txtIdProfesor.getText());
            controller.eliminarProfesor(idProfesor);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un ID de profesor válido.");
        }
    }

    private Profesor recogerInformacion() {
        try {
            int idProfesor = txtIdProfesor.getText().isEmpty() ? 0 : Integer.parseInt(txtIdProfesor.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String email = txtEmail.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                mostrarMensaje("Todos los campos son obligatorios.");
                return null;
            }

            return new Profesor(idProfesor, nombre, apellido, email);
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un ID de profesor válido.");
            return null;
        }
    }

    public void mostrarProfesor(Profesor profesor) {
        txtIdProfesor.setText(String.valueOf(profesor.getIdProfesor()));
        txtNombre.setText(profesor.getNombre());
        txtApellido.setText(profesor.getApellido());
        txtEmail.setText(profesor.getEmail());
    }

    private void limpiarCampos() {
        txtIdProfesor.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
