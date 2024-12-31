package model;

import java.sql.*;
import util.ConexionBD;
import javax.swing.JOptionPane;

public class MetodosDB {
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public MetodosDB() {
        cn = ConexionBD.getConexionBD();
    }

    public Curso buscarCurso(int idCurso) {
        Curso curso = null;
        try {
            ps = cn.prepareStatement("SELECT c.*, e.NOMBRE as NOMBRE_ESCUELA FROM CURSO c "
                    +"LEFT JOIN ESCUELA e ON c.ID_ESCUELA = e.ID_ESCUELA WHERE c.ID_CURSO = ?");
            ps.setInt(1, idCurso);
            rs = ps.executeQuery();

            if (rs.next()) {
                curso = new Curso(
                    rs.getInt("ID_CURSO"),
                    rs.getString("NOMBRE"),
                    rs.getInt("CREDITOS"),
                    rs.getInt("CICLO_DISPONIBLE"),
                    rs.getInt("anio"),
                    rs.getInt("ID_ESCUELA"),
                    rs.getString("NOMBRE_ESCUELA"),
                    rs.getInt("TOTAL_HORAS"),
                    rs.getInt("ID_AULA")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en el método buscarCurso: " + e.getMessage());
        }
        return curso;
    }

    public void insertarCurso(Curso curso) {
        try {
            ps = cn.prepareStatement("INSERT INTO CURSO (NOMBRE, CREDITOS, TOTAL_HORAS, ID_AULA, CICLO_DISPONIBLE, anio, ID_ESCUELA) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setInt(3, curso.getTotalHoras());
            ps.setInt(4, curso.getIdAula());
            ps.setInt(5, curso.getCicloDisponible());
            ps.setInt(6, curso.getAnio());
            ps.setInt(7, curso.getIdEscuela());

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Curso insertado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar curso");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método insertarCurso: " + e.getMessage());
        }
    }

    public void modificarCurso(Curso curso) {
        try {
            ps = cn.prepareStatement("UPDATE CURSO SET NOMBRE = ?, CREDITOS = ?, TOTAL_HORAS = ?, ID_AULA = ?, CICLO_DISPONIBLE = ?, anio = ?, ID_ESCUELA = ? WHERE ID_CURSO = ?");
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setInt(3, curso.getTotalHoras());
            ps.setInt(4, curso.getIdAula());
            ps.setInt(5, curso.getCicloDisponible());
            ps.setInt(6, curso.getAnio());
            ps.setInt(7, curso.getIdEscuela());
            ps.setInt(8, curso.getIdCurso());

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Curso modificado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el curso");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método modificarCurso: " + e.getMessage());
        }
    }

    public void eliminarCurso(int idCurso) {
        try {
            ps = cn.prepareStatement("DELETE FROM CURSO WHERE ID_CURSO = ?");
            ps.setInt(1, idCurso);

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Curso eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el curso");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método eliminarCurso: " + e.getMessage());
        }
    }

    public Profesor buscarProfesor(int idProfesor) {
        Profesor profesor = null;
        try {
            ps = cn.prepareStatement("SELECT * FROM PROFESOR WHERE ID_PROFESOR = ?");
            ps.setInt(1, idProfesor);
            rs = ps.executeQuery();

            if (rs.next()) {
                profesor = new Profesor(
                    rs.getInt("ID_PROFESOR"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"),
                    rs.getString("EMAIL")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en el método buscarProfesor: " + e.getMessage());
        }
        return profesor;
    }

    public void insertarProfesor(Profesor profesor) {
        try {
            ps = cn.prepareStatement("INSERT INTO PROFESOR (NOMBRE, APELLIDO, EMAIL) VALUES (?, ?, ?)");
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setString(3, profesor.getEmail());

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Profesor insertado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar profesor");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método insertarProfesor: " + e.getMessage());
        }
    }

    public void modificarProfesor(Profesor profesor) {
        try {
            ps = cn.prepareStatement("UPDATE PROFESOR SET NOMBRE = ?, APELLIDO = ?, EMAIL = ? WHERE ID_PROFESOR = ?");
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setString(3, profesor.getEmail());
            ps.setInt(4, profesor.getIdProfesor());

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Profesor modificado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el profesor");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método modificarProfesor: " + e.getMessage());
        }
    }

    public void eliminarProfesor(int idProfesor) {
        try {
            ps = cn.prepareStatement("DELETE FROM PROFESOR WHERE ID_PROFESOR = ?");
            ps.setInt(1, idProfesor);

            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el profesor");
            }
        } catch (SQLException e) {
            System.out.println("Error en el método eliminarProfesor: " + e.getMessage());
        }
    }
}