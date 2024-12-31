package controller;

import java.util.List;
import model.Curso;
import model.MetodosCurso;
import model.Estudiante;
import model.Seccion;
import view.PanelMatricula;

public class MatriculaController {

    private PanelMatricula view;
    private MetodosCurso model;

    public MatriculaController(PanelMatricula view, MetodosCurso model) {
        this.view = view;
        this.model = model;
    }

    public void cargarCursosPorEscuelaAnioCiclo(int idEscuela, int anio, int ciclo) {
        List<Curso> cursos = model.obtenerCursosPorEscuelaAnioCiclo(idEscuela, anio, ciclo);
        
        view.mostrarCursos(cursos);
    }

    public List<Seccion> obtenerSeccionesPorCurso(int idCurso) {
        return model.obtenerSeccionesPorCurso(idCurso);
    }

    public void matricularEstudiante(Estudiante estudiante, List<Curso> cursosSeleccionados, List<Seccion> seccionesSeleccionadas, int anio, int ciclo) {
        boolean exito = model.matricularEstudiante(estudiante, cursosSeleccionados, seccionesSeleccionadas, anio, ciclo);

        if (exito) {
            view.mostrarMensaje("Matrícula realizada con éxito");
        } else {
            view.mostrarMensajeError("Error al realizar la matrícula");
        }
    }
}
