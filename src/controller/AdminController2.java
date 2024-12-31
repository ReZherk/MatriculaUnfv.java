package controller;

import java.util.List;
import model.Estudiante;
import view.MatriculadosPanel;
import model.MetodosEstudiante;
import model.MetodosCurso;
import model.Curso;
import model.Seccion;
import view.HorariosPanel;
import view.EstudiantePanel;

public class AdminController2 {

    private MatriculadosPanel view3;
    private EstudiantePanel view1;
    private MetodosEstudiante model1;
    private HorariosPanel view2;
    private MetodosCurso model2;
    public Estudiante estudiante;

    public AdminController2(EstudiantePanel view1, MetodosEstudiante model1) {
        this.view1 = view1;
        this.model1 = model1;
    }

    public AdminController2(MatriculadosPanel view1, MetodosEstudiante model1) {
        this.view3 = view1;
        this.model1 = model1;
    }

    public AdminController2(HorariosPanel view2, MetodosCurso model2) {
        this.view2 = view2;
        this.model2 = model2;
    }

    public MetodosCurso getModel() {
        return model2;
    }

    public void buscarEstudiante(String codigo) {
        estudiante = new Estudiante();
        estudiante.setCodigo(codigo);
        estudiante = model1.BuscarEstudiante(estudiante);

        if (estudiante != null) {
            view1.mostrarEstudiante(estudiante);
        } else {
            view1.mostrarMensajeError("No se encontró el estudiante.");
        }
    }

    public List<Estudiante> buscarMatriculadosPorFecha(String fecha) {
        return model1.buscarMatriculadosPorFecha(fecha);
    }

    public void guardarEstudiante(Estudiante estudiante) {
        model1.InsertarEstudiante(estudiante);
        view1.mostrarMensaje("Estudiante guardado correctamente");
    }

    public void modificarEstudiante(Estudiante estudiante) {
        model1.ModificarEstudiante(estudiante);
        view1.mostrarMensaje("Estudiante modificado correctamente");
    }

    public void eliminarEstudiante(String codigo) {
        estudiante = new Estudiante();
        estudiante.setCodigo(codigo);
        model1.EliminarEstudiante(estudiante);
        view1.mostrarMensaje("Estudiante eliminado correctamente");
    }

    public List<Curso> buscarCursos(String nombreCurso) {
        return model2.buscarCursos(nombreCurso);
    }

    public List<Seccion> obtenerSeccionesPorCurso(int idCurso) {
        return model2.obtenerSeccionesPorCurso(idCurso);
    }

    public boolean insertarHorario(Seccion seccion) {
        return model2.insertarHorario(seccion);
    }

    public boolean eliminarHorario(int idSeccion, int idCurso) {
        return model2.eliminarHorario(idSeccion, idCurso);
    }
}
