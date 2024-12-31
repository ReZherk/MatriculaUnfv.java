package model;

public class Seccion {

    private int idSeccion;
    private int idCurso;
    private Profesor profesor;
    private String nombreSeccion;
    private String horario1;
    private String horario2;

    public Seccion(int idSeccion, int idCurso, Profesor profesor, String nombreSeccion, String horario1, String horario2) {
        this.idSeccion = idSeccion;
        this.idCurso = idCurso;
        this.profesor = profesor;
        this.nombreSeccion = nombreSeccion;
        this.horario1 = horario1;
        this.horario2 = horario2;
    }

    // Constructor sin argumentos
    public Seccion() {
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getHorario1() {
        return horario1;
    }

    public void setHorario1(String horario1) {
        this.horario1 = horario1;
    }

    public String getHorario2() {
        return horario2;
    }

    public void setHorario2(String horario2) {
        this.horario2 = horario2;
    }

    @Override
    public String toString() {
        return "Sección " + nombreSeccion + " - " + profesor.toString() + " - " + horario1 + " - " + horario2;
    }
}

