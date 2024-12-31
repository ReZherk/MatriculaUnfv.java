package view;

import javax.swing.JPanel;
import controller.AdminController2;
import model.Estudiante;
import model.MetodosEstudiante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudiantePanel extends JPanel {

    AdminController2 controller;
    Estudiante estudiante;
    JButton botonGuardar, botonModificar, botonEliminar, botonLimpiar, botonBuscar;
    JPanel panelInsertarAlumno;
    JTextField campoId, campoNombre, campoCorreo, campoContrasena, campoApellidos;
    JComboBox campoAnio, campoEscuela, campoSexo;
    String[] Genero = {" ", "MASCULINO", "FEMENINO"};
    String[] Año = {" ", "Primer año", "Segundo año", "Tercer año", "Cuarto año", "Quinto año"};
    String[] Escuela = {" ", "Ingeniería de Sistemas", "Ingeniería Industrial", "Ingenieria de Trasportes", "Ingenieria Agroindustrial"};

    public EstudiantePanel() {
        controller = new AdminController2(this, new MetodosEstudiante());
        Ventana();
    }

    public void Ventana() {
        setBounds(10, 10, 760, 460);
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Gestión del Estudiante"));
        setBackground(Color.WHITE);

        panelInsertarAlumno = new JPanel(null);
        panelInsertarAlumno.setBounds(7, 15, 745, 437);
        panelInsertarAlumno.setBackground(Color.WHITE);

        JLabel labelId = new JLabel("ID Código:");
        labelId.setForeground(new Color(255, 110, 33));
        labelId.setBounds(50, 50, 100, 20);
        panelInsertarAlumno.add(labelId);

        campoId = new JTextField();
        campoId.setBounds(150, 50, 200, 25);
        panelInsertarAlumno.add(campoId);

        JLabel labelNombre = new JLabel("Nombres:");
        labelNombre.setForeground(new Color(255, 110, 33));
        labelNombre.setBounds(50, 90, 100, 20);
        panelInsertarAlumno.add(labelNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(150, 90, 200, 25);
        panelInsertarAlumno.add(campoNombre);

        JLabel labelApellidos = new JLabel("Apellidos:");
        labelApellidos.setForeground(new Color(255, 110, 33));
        labelApellidos.setBounds(50, 130, 100, 20);
        panelInsertarAlumno.add(labelApellidos);

        campoApellidos = new JTextField();
        campoApellidos.setBounds(150, 130, 200, 25);
        panelInsertarAlumno.add(campoApellidos);

        JLabel labelSexo = new JLabel("Sexo:");
        labelSexo.setForeground(new Color(255, 110, 33));
        labelSexo.setBounds(50, 170, 100, 20);
        panelInsertarAlumno.add(labelSexo);

        campoSexo = new JComboBox<>(Genero);
        campoSexo.setBounds(150, 170, 200, 25);
        panelInsertarAlumno.add(campoSexo);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setForeground(new Color(255, 110, 33));
        labelCorreo.setBounds(50, 210, 100, 20);
        panelInsertarAlumno.add(labelCorreo);

        campoCorreo = new JTextField();
        campoCorreo.setBounds(150, 210, 200, 25);
        panelInsertarAlumno.add(campoCorreo);

        JLabel labelContrasena = new JLabel("Contraseña:");
        labelContrasena.setForeground(new Color(255, 110, 33));
        labelContrasena.setBounds(50, 250, 100, 20);
        panelInsertarAlumno.add(labelContrasena);

        campoContrasena = new JTextField();
        campoContrasena.setBounds(150, 250, 200, 25);
        panelInsertarAlumno.add(campoContrasena);

        JLabel labelAnio = new JLabel("Ciclo en curso:");
        labelAnio.setForeground(new Color(255, 110, 33));
        labelAnio.setBounds(50, 290, 100, 20);
        panelInsertarAlumno.add(labelAnio);

        campoAnio = new JComboBox<>(Año);
        campoAnio.setBounds(150, 290, 200, 25);
        panelInsertarAlumno.add(campoAnio);

        JLabel labelEscuela = new JLabel("IdEscuela:");
        labelEscuela.setForeground(new Color(255, 110, 33));
        labelEscuela.setBounds(50, 330, 100, 20);
        panelInsertarAlumno.add(labelEscuela);

        campoEscuela = new JComboBox<>(Escuela);
        campoEscuela.setBounds(150, 330, 200, 25);
        panelInsertarAlumno.add(campoEscuela);

        botonGuardar = new JButton("Insertar");
        botonGuardar.setBounds(50, 370, 90, 30);
        botonGuardar.setForeground(Color.WHITE);
        botonGuardar.setBackground(new Color(255, 110, 33));
        panelInsertarAlumno.add(botonGuardar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiante = new Estudiante();
                estudiante = RecogerInformacion(estudiante);
                if (estudiante != null) {
                    controller.guardarEstudiante(estudiante);
                }
            }
        });

        botonModificar = new JButton("Modificar");
        botonModificar.setBounds(150, 370, 90, 30);
        botonModificar.setForeground(Color.WHITE);
        botonModificar.setBackground(new Color(255, 110, 33));
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = campoId.getText();
                if (codigo.equals("")) {
                    mostrarMensajeError("Llene el campo de ID_CODIGO");
                    LimpiarCampos();
                } else {
                    estudiante = new Estudiante();
                    estudiante = RecogerInformacion(estudiante);
                    if (estudiante != null) {
                        controller.modificarEstudiante(estudiante);
                    }
                }
            }
        });
        panelInsertarAlumno.add(botonModificar);
        
        botonEliminar = new JButton("Eliminar");
        botonEliminar.setBounds(250, 370, 90, 30);
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setBackground(new Color(255, 110, 33));
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = campoId.getText();
                if (codigo.equals("")) {
                    mostrarMensajeError("Rellene el campo de ID_CODIGO");
                } else {
                    controller.eliminarEstudiante(codigo);
                    LimpiarCampos();
                }
            }
        });
        panelInsertarAlumno.add(botonEliminar);
        
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBounds(350, 370, 90, 30);
        botonLimpiar.setForeground(Color.WHITE);
        botonLimpiar.setBackground(new Color(255, 110, 33));
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpiarCampos();
            }
        });
        panelInsertarAlumno.add(botonLimpiar);
        
        botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(360, 50, 90, 25);
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.setBackground(new Color(255, 110, 33));
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botonBuscar) {
                    String codigo = campoId.getText();
                    if (codigo.equals("")) {
                        mostrarMensajeError("Llene el campo de ID_CODIGO");
                        LimpiarCampos();
                    } else {
                        controller.buscarEstudiante(codigo);
                    }
                }
            }
        });

        panelInsertarAlumno.add(botonBuscar);
        add(panelInsertarAlumno);
        panelInsertarAlumno.setVisible(true);
    }

    public void mostrarEstudiante(Estudiante estudiante) {
        campoId.setText(estudiante.getCodigo());
        campoNombre.setText(estudiante.getNombres());
        campoApellidos.setText(estudiante.getApellidos());
        campoSexo.setSelectedItem(estudiante.getGenero());
        campoCorreo.setText(estudiante.getMail());
        campoContrasena.setText(estudiante.getPassword());
        campoAnio.setSelectedIndex(estudiante.getIdCiclo());
        campoEscuela.setSelectedIndex(estudiante.getIdEscuela());
    }

    public void LimpiarCampos() {
        campoId.setText("");
        campoNombre.setText("");
        campoApellidos.setText("");
        campoSexo.setSelectedIndex(0);
        campoCorreo.setText("");
        campoContrasena.setText("");
        campoAnio.setSelectedIndex(0);
        campoEscuela.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int AñoEstudio(String año) {
        return switch (año) {
            case "Primer año" ->
                1;
            case "Segundo año" ->
                2;
            case "Tercer año" ->
                3;
            case "Cuarto año" ->
                4;
            case "Quinto año" ->
                5;
            default ->
                0;
        };
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

    public Estudiante RecogerInformacion(Estudiante obj) {
        String nombres, apellidos, genero, mail, contrasena, codigo;
        int idCiclo, idEscuela;

        codigo = campoId.getText();
        idCiclo = AñoEstudio((String) campoAnio.getSelectedItem());
        idEscuela = Escuela((String) campoEscuela.getSelectedItem());
        nombres = campoNombre.getText();
        apellidos = campoApellidos.getText();
        genero = (String) campoSexo.getSelectedItem();
        mail = campoCorreo.getText();
        contrasena = campoContrasena.getText();

        if (codigo.isEmpty() || idCiclo == 0 || nombres.isEmpty() || apellidos.isEmpty() || genero.isEmpty() || mail.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
            return null;
        }

        obj.setCodigo(codigo);
        obj.setIdCiclo(idCiclo);
        obj.setIdEscuela(idEscuela);
        obj.setNombres(nombres);
        obj.setApellidos(apellidos);
        obj.setGenero(genero);
        obj.setMail(mail);
        obj.setPassword(contrasena);

        return obj;
    }
}
