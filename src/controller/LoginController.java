package controller;

import model.Estudiante;
import model.MetodosEstudiante;
import view.MatriculaUI;
import view.LoginUI;
import view.VistaAdministrativaUI;

public class LoginController {

    private LoginUI view;
    private MetodosEstudiante model;

    public LoginController(LoginUI view, MetodosEstudiante model) {
        this.view = view;
        this.model = model;
    }

    public void autenticar(String usuario, String contrasena, String rol) {
        boolean acceso = model.autenticarUsuario(usuario, contrasena);

        if (acceso) {
            if (rol.equals("Estudiante")) {
                Estudiante estudiante = model.BuscarEstudiante(model.getObjEstudiante());
                if (estudiante != null) {
                    MatriculaUI ventanaMatricula = new MatriculaUI(estudiante);
                    ventanaMatricula.setVisible(true);
                    view.dispose();
                } else {
                    view.mostrarMensajeError("No se pudo cargar la información del estudiante");
                }
            } else if (rol.equals("Administrativo")) {
                VistaAdministrativaUI ventanaAdmin = new VistaAdministrativaUI();
                ventanaAdmin.setVisible(true);
                view.dispose();
            }
        } else {
            view.mostrarMensajeError("Usuario o contraseña incorrectos");
        }
    }

}
