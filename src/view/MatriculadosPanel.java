package view;

import controller.AdminController2;
import model.Estudiante;
import model.MetodosEstudiante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.util.List;

public class MatriculadosPanel extends JPanel {

    AdminController2 controller;
    Estudiante estudiante;
    JTextArea areaMatriculas;
    JButton botonBuscarMatriculas;
    JPanel panelMatriculas;
    JTextField campoFecha;

    public MatriculadosPanel() {
        controller = new AdminController2(this, new MetodosEstudiante());
        crearVentana();
    }

    private void crearVentana() {
        setLayout(new BorderLayout());
        setBounds(10, 10, 760, 500);
        setBorder(BorderFactory.createTitledBorder("Gestión de Matriculados"));
        setBackground(Color.WHITE);

        panelMatriculas = new JPanel(null);
        panelMatriculas.setBounds(0, 0, 500, 400);
        panelMatriculas.setBackground(Color.WHITE);

        JLabel labelFecha = new JLabel("Fecha (YYYY-MM-DD):");
        labelFecha.setForeground(new Color(255, 110, 33));
        labelFecha.setBounds(50, 20, 150, 20);
        panelMatriculas.add(labelFecha);

        campoFecha = new JTextField();
        campoFecha.setBounds(200, 20, 150, 20);
        panelMatriculas.add(campoFecha);

        botonBuscarMatriculas = new JButton("Matriculados");
        botonBuscarMatriculas.setBounds(360, 20, 110, 20);
        botonBuscarMatriculas.setForeground(Color.WHITE);
        botonBuscarMatriculas.setBackground(new Color(255, 110, 33));
        botonBuscarMatriculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botonBuscarMatriculas) {
                    String fecha = campoFecha.getText();
                    if (fecha.isEmpty()) {
                        mostrarMensajeError("Por favor, ingrese una fecha válida.");
                    } else {
                        List<Estudiante> matriculados = controller.buscarMatriculadosPorFecha(fecha);
                        mostrarMatriculados(matriculados);
                    }
                }
            }
        });

        panelMatriculas.add(botonBuscarMatriculas);

        areaMatriculas = new JTextArea();
        areaMatriculas.setBounds(50, 50, 400, 300);
        areaMatriculas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMatriculas);
        scrollPane.setBounds(50, 50, 400, 300);
        panelMatriculas.add(scrollPane);
        add(panelMatriculas);

    }

    private void mostrarMatriculados(List<Estudiante> matriculados) {
        areaMatriculas.setText("Estudiantes matriculados en la fecha especificada:\n\n");
        if (matriculados.isEmpty()) {
            areaMatriculas.append("No se encontraron estudiantes matriculados para la fecha especificada.");
        } else {
            for (Estudiante estudiante : matriculados) {
                areaMatriculas.append(String.format("%-10s - %-20s %-20s - %s\n",
                        estudiante.getCodigo(),
                        estudiante.getNombres(),
                        estudiante.getApellidos(),
                        estudiante.getMail()));
            }
            areaMatriculas.append("\nTotal de estudiantes matriculados: " + matriculados.size());
        }
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
