package model;

public class Curso {
    private int idCurso;
    private String nombre;
    private int creditos;
    private int cicloDisponible;
    private int anio;
    private int idEscuela;
    private String nombreEscuela;
    private int totalHoras;
    private int idAula;

   
    public Curso(int idCurso, String nombre, int creditos, int cicloDisponible, int anio, int idEscuela, String nombreEscuela, int totalHoras, int idAula) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cicloDisponible = cicloDisponible;
        this.anio = anio;
        this.idEscuela = idEscuela;
        this.nombreEscuela = nombreEscuela;
        this.totalHoras = totalHoras;
        this.idAula = idAula;
    }

    public Curso() {
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getCicloDisponible() {
        return cicloDisponible;
    }

    public void setCicloDisponible(int cicloDisponible) {
        this.cicloDisponible = cicloDisponible;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public int getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(int totalHoras) {
        this.totalHoras = totalHoras;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", cicloDisponible=" + cicloDisponible +
                ", anio=" + anio +
                ", idEscuela=" + idEscuela +
                ", nombreEscuela='" + nombreEscuela + '\'' +
                ", totalHoras=" + totalHoras +
                ", idAula=" + idAula +
                '}';
    }
}

