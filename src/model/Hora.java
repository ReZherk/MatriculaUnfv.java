package model;

public class Hora {
    private int idHora;
    private String hora;

    public Hora(int idHora, String hora) {
        this.idHora = idHora;
        this.hora = hora;
    }

    // Constructor sin argumentos
    public Hora() {
    }

    public int getIdHora() {
        return idHora;
    }

    public void setIdHora(int idHora) {
        this.idHora = idHora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Hora{" +
                "idHora=" + idHora +
                ", hora='" + hora + '\'' +
                '}';
    }
}

