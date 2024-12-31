package model;

import java.sql.*;
import util.ConexionBD;
import java.util.ArrayList;

public class MatriculaRealizadaDAO {
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null; 
   
   
    public ArrayList<MatriculaRealizada> obtenerDatosMatricula(String idEstudiante) {
        ArrayList<MatriculaRealizada> listaDatos = new ArrayList<>();
            
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT \n" +
                                     "m.ID_STUDENT, \n" +
                                     "m.ID_CURSO, \n" +
                                     "c.NOMBRE AS CURSO, \n" +
                                     "c.CREDITOS, \n" +
                                     "s.NOMBRE_SECCION AS SECCION, \n" +
                                     "CONCAT(\n" +
                                     "IF(h.ID_DIA_N1 = 0, '', CONCAT(d1.DIA, ' ', h1.HORA, ' - ', h2.HORA)), \n" +
                                     "IF(h.ID_DIA_N2 = 0, '', CONCAT(' / ', d2.DIA, ' ', h3.HORA, ' - ', h4.HORA))\n" +
                                     ") AS HORARIO, \n" +
                                     "IF(h.ID_PROFESOR = 0, '', CONCAT(p.NOMBRE, ' ', p.APELLIDO)) AS PROFESOR, \n" +
                                     "a.TIPO_AULA AS AULA, \n" +
                                     "a.ID_AULA, \n" +
                                     "m.FECHA_MATRICULA AS FECHA, \n" +
                                     "c.CICLO_DISPONIBLE AS CICLO, \n" +
                                     "IF(h.ID_DIA_N1 = 0, '', h.ID_DIA_N1) AS DIA_1, \n" +
                                     "IF(h.ID_DIA_N1 = 0, '', h1.ID_HORA) AS HORA_INICIO_1, \n" +
                                     "IF(h.ID_DIA_N1 = 0, '', h2.ID_HORA) AS HORA_FIN_1, \n" +
                                     "IF(h.ID_DIA_N2 = 0, '', h.ID_DIA_N2) AS DIA_2, \n" +
                                     "IF(h.ID_DIA_N2 = 0, '', h3.ID_HORA) AS HORA_INICIO_2, \n" +
                                     "IF(h.ID_DIA_N2 = 0, '', h4.ID_HORA) AS HORA_FIN_2\n" +
                                     "FROM \n" +
                                     "matricula m\n" +
                                     "INNER JOIN horario h ON \n" +
                                     "m.ID_CURSO = h.ID_CURSO AND m.ID_SECCION = h.ID_SECCION\n" +
                                     "INNER JOIN curso c ON \n" +
                                     "m.ID_CURSO = c.ID_CURSO\n" +
                                     "INNER JOIN profesor p ON \n" +
                                     "h.ID_PROFESOR = p.ID_PROFESOR\n" +
                                     "LEFT JOIN dia d1 ON \n" +
                                     "h.ID_DIA_N1 = d1.ID_DIA\n" +      
                                     "LEFT JOIN hora h1 ON \n" +
                                     "h.ID_HORA_INICIO_N1 = h1.ID_HORA\n" +
                                     "LEFT JOIN hora h2 ON \n" +
                                     "h.ID_HORA_FIN_N1 = h2.ID_HORA\n" +
                                     "LEFT JOIN dia d2 ON \n" +
                                     "h.ID_DIA_N2 = d2.ID_DIA\n" +
                                     "LEFT JOIN hora h3 ON \n" +
                                     "h.ID_HORA_INICIO_N2 = h3.ID_HORA\n" +
                                     "LEFT JOIN hora h4 ON \n" +
                                     "h.ID_HORA_FIN_N2 = h4.ID_HORA\n" +
                                     "INNER JOIN seccion s ON \n" +
                                     "m.ID_SECCION = s.ID_SECCION\n" +
                                     "INNER JOIN aula a ON \n" +
                                     "c.ID_AULA = a.ID_AULA\n" +
                                     "WHERE \n" +
                                     "m.ID_STUDENT = ?;");
            pt.setInt(1, Integer.parseInt(idEstudiante));
            rs = pt.executeQuery();

            while (rs.next()) {
                MatriculaRealizada datos = new MatriculaRealizada();
                datos.setIdEstudiante(rs.getInt("ID_STUDENT"));
                datos.setIdCurso(rs.getInt("ID_CURSO"));
                datos.setCurso(rs.getString("CURSO"));
                datos.setCreditos(rs.getInt("CREDITOS"));
                datos.setSeccion(rs.getString("SECCION"));
                datos.setHorario(rs.getString("HORARIO"));
                datos.setProfesor(rs.getString("PROFESOR"));
                datos.setAula(rs.getString("AULA"));
                datos.setIdAula(rs.getInt("ID_AULA"));
                datos.setFecha(rs.getString("FECHA"));
                datos.setCiclo(rs.getInt("CICLO"));
                datos.setDia1(rs.getInt("DIA_1"));
                datos.setHoraInicio1(rs.getInt("HORA_INICIO_1"));
                datos.setHoraFin1(rs.getInt("HORA_FIN_1"));
                datos.setDia2(rs.getInt("DIA_2"));
                datos.setHoraInicio2(rs.getInt("HORA_INICIO_2"));
                datos.setHoraFin2(rs.getInt("HORA_FIN_2"));
                listaDatos.add(datos);
            }
            
            rs.close();
            pt.close();
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error en el método obtenerDatosMatricula");
        }
        
        return listaDatos;
    } 

}