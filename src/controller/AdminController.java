package controller;

import model.*;
import view.*;

public class AdminController {

    private MetodosDB modelo;
    private CursosPanel1 viewCurso;
    private ProfesoresPanel viewProfesor;

    public AdminController(CursosPanel1 view, MetodosDB modelo) {
        this.modelo = modelo;
        this.viewCurso = view;
    }

    public AdminController(ProfesoresPanel view, MetodosDB modelo) {
        this.modelo = modelo;
        this.viewProfesor = view;
    }

    public Curso buscarCurso(int idCurso) {
        return modelo.buscarCurso(idCurso);
    }

    public void guardarCurso(Curso curso) {
        modelo.insertarCurso(curso);
        viewCurso.mostrarMensaje("Curso guardado correctamente");
    }

    public void modificarCurso(Curso curso) {
        modelo.modificarCurso(curso);
        viewCurso.mostrarMensaje("Curso modificado correctamente");
    }

    public void eliminarCurso(int idCurso) {
        modelo.eliminarCurso(idCurso);
        viewCurso.mostrarMensaje("Curso eliminado correctamente");
    }

    public Profesor buscarProfesor(int idProfesor) {
        return modelo.buscarProfesor(idProfesor);
    }

    public void guardarProfesor(Profesor profesor) {
        modelo.insertarProfesor(profesor);
        viewProfesor.mostrarMensaje("Profesor guardado correctamente");
    }

    public void modificarProfesor(Profesor profesor) {
        modelo.modificarProfesor(profesor);
        viewProfesor.mostrarMensaje("Profesor modificado correctamente");
    }

    public void eliminarProfesor(int idProfesor) {
        modelo.eliminarProfesor(idProfesor);
        viewProfesor.mostrarMensaje("Profesor eliminado correctamente");
    }
}
