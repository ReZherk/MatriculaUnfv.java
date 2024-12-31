package model;

import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodosCurso {
    
    private Connection cn;
    private PreparedStatement pt;
    private ResultSet rs;
    
    public List<Curso> obtenerCursosPorEscuelaAnioCiclo(int idEscuela, int anio, int ciclo) {
        List<Curso> cursos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM curso WHERE ID_ESCUELA = ? AND anio = ? AND CICLO_DISPONIBLE = ?");
            pt.setInt(1, idEscuela);
            pt.setInt(2, anio);
            pt.setInt(3, ciclo);
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("ID_CURSO"));
                curso.setNombre(rs.getString("NOMBRE"));
                curso.setCreditos(rs.getInt("CREDITOS"));
                curso.setCicloDisponible(rs.getInt("CICLO_DISPONIBLE"));
                curso.setAnio(rs.getInt("anio"));
                curso.setIdEscuela(rs.getInt("ID_ESCUELA"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerCursosPorEscuelaAnioCiclo: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return cursos;
    }

    public List<Seccion> obtenerSeccionesPorCurso(int idCurso) {
        List<Seccion> secciones = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            String query = "SELECT s.ID_SECCION, s.NOMBRE_SECCION, " +
                           "CONCAT(d1.dia, ' ', h1.hora, ' - ', h2.hora) AS HORARIO1, " +
                           "CONCAT(d2.dia, ' ', h3.hora, ' - ', h4.hora) AS HORARIO2, " +
                           "p.ID_PROFESOR, p.NOMBRE, p.APELLIDO, p.EMAIL " +
                           "FROM horario hr " +
                           "JOIN seccion s ON hr.id_seccion = s.id_seccion " +
                           "JOIN profesor p ON hr.ID_PROFESOR = p.ID_PROFESOR " +
                           "JOIN hora h1 ON hr.ID_HORA_INICIO_N1 = h1.id_hora " +
                           "JOIN hora h2 ON hr.ID_HORA_FIN_N1 = h2.id_hora " +
                           "JOIN hora h3 ON hr.ID_HORA_INICIO_N2 = h3.ID_HORA " +
                           "JOIN hora h4 ON hr.ID_HORA_FIN_N2 = h4.ID_HORA " +
                           "JOIN dia d1 ON hr.ID_DIA_N1 = d1.ID_DIA " +
                           "JOIN dia d2 ON hr.ID_DIA_N2 = d2.ID_DIA " +
                           "WHERE hr.id_curso = ? " +
                           "ORDER BY s.NOMBRE_SECCION;";
            pt = cn.prepareStatement(query);
            pt.setInt(1, idCurso);
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Profesor profesor = new Profesor(rs.getInt("ID_PROFESOR"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"));
                Seccion seccion = new Seccion(rs.getInt("ID_SECCION"), idCurso, profesor, rs.getString("NOMBRE_SECCION"), rs.getString("HORARIO1"), rs.getString("HORARIO2"));
                secciones.add(seccion);
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerSeccionesPorCurso: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return secciones;
    }
    
    public List<Curso> buscarCursos(String nombreCurso) {
        List<Curso> cursos = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            String query = "SELECT c.*, e.NOMBRE as NOMBRE_ESCUELA FROM curso c " +
                           "JOIN escuela e ON c.ID_ESCUELA = e.ID_ESCUELA " +
                           "WHERE c.NOMBRE LIKE ?";
            pt = cn.prepareStatement(query);
            pt.setString(1, "%" + nombreCurso + "%");
            rs = pt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("ID_CURSO"));
                curso.setNombre(rs.getString("NOMBRE"));
                curso.setCreditos(rs.getInt("CREDITOS"));
                curso.setCicloDisponible(rs.getInt("CICLO_DISPONIBLE"));
                curso.setAnio(rs.getInt("anio"));
                curso.setIdEscuela(rs.getInt("ID_ESCUELA"));
                curso.setNombreEscuela(rs.getString("NOMBRE_ESCUELA"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error en buscarCursos: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return cursos;
    }

    public boolean insertarHorario(Seccion seccion) {
        boolean exito = false;
        try {
            cn = ConexionBD.getConexionBD();
            String query = "INSERT INTO horario (ID_CURSO, ID_SECCION, ID_DIA_N1, ID_HORA_INICIO_N1, ID_HORA_FIN_N1, " +
                           "ID_DIA_N2, ID_HORA_INICIO_N2, ID_HORA_FIN_N2, ID_PROFESOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pt = cn.prepareStatement(query);
            pt.setInt(1, seccion.getIdCurso());
            pt.setInt(2, seccion.getIdSeccion());
            // Aquí deberías convertir los horarios de String a IDs correspondientes
            // Por simplicidad, estamos usando 1 para todos los IDs
            pt.setInt(3, 1); // ID_DIA_N1
            pt.setInt(4, 1); // ID_HORA_INICIO_N1
            pt.setInt(5, 1); // ID_HORA_FIN_N1
            pt.setInt(6, 1); // ID_DIA_N2
            pt.setInt(7, 1); // ID_HORA_INICIO_N2
            pt.setInt(8, 1); // ID_HORA_FIN_N2
            pt.setInt(9, seccion.getProfesor().getIdProfesor());

            int filasAfectadas = pt.executeUpdate();
            exito = filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error en insertarHorario: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return exito;
    }

    public boolean eliminarHorario(int idSeccion,int idCurso) {
        boolean exito = false;
        try {
            cn = ConexionBD.getConexionBD();
            String query = "DELETE FROM horario WHERE ID_SECCION = ? AND ID_CURSO = ? ";
            pt = cn.prepareStatement(query);
            pt.setInt(1, idSeccion);
            pt.setInt(2,idCurso);

            int filasAfectadas = pt.executeUpdate();
            exito = filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error en eliminarHorario: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return exito;
    }

    public boolean matricularEstudiante(Estudiante estudiante, List<Curso> cursosSeleccionados, List<Seccion> seccionesSeleccionadas, int anio, int ciclo) {
        boolean exito = false;
        try {
            cn = ConexionBD.getConexionBD();
            cn.setAutoCommit(false);

            // Validar que el estudiante no esté ya matriculado en alguno de estos cursos
            for (Curso curso : cursosSeleccionados) {
                pt = cn.prepareStatement(
                        "SELECT COUNT(*) FROM matricula WHERE ID_STUDENT = ? AND ID_CURSO = ? AND CICLO = ?");
                pt.setString(1, estudiante.getCodigo());
                pt.setInt(2, curso.getIdCurso());
                pt.setInt(3, ciclo);
                ResultSet rs = pt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new SQLException("El estudiante ya está matriculado en uno o más de los cursos seleccionados");
                }
            }

            for (int i = 0; i < cursosSeleccionados.size(); i++) {
                Curso curso = cursosSeleccionados.get(i);
                Seccion seccion = seccionesSeleccionadas.get(i);
                
                pt = cn.prepareStatement("INSERT INTO matricula (ID_STUDENT,ID_CURSO,CICLO,ID_SECCION) VALUES (?,?,?,?)");
                pt.setString(1, estudiante.getCodigo());
                pt.setInt(2, curso.getIdCurso());
                pt.setInt(3, ciclo);
                pt.setInt(4, seccion.getIdSeccion());
                pt.executeUpdate();
            }
            
            pt = cn.prepareStatement("INSERT INTO estado_matricula (ID_STUDENT,anio,ciclo,estado) VALUES (?,?,?,?)" +
                                     " ON DUPLICATE KEY UPDATE estado = ?");
            pt.setString(1, estudiante.getCodigo());
            pt.setInt(2, anio);
            pt.setInt(3, ciclo);
            pt.setString(4, "MATRICULADO");
            pt.setString(5, "MATRICULADO");
            pt.executeUpdate();
            
            cn.commit();
            exito = true;
        } catch (SQLException e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
        return exito;
    }

    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (pt != null) pt.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}

