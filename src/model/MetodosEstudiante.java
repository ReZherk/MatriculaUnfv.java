package model;

import java.util.*;
import java.sql.*;
import util.ConexionBD;
import javax.swing.JOptionPane;

public class MetodosEstudiante {

    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;

    Estudiante objEstudiante = null;

    public boolean autenticarUsuario(String usuario, String contrasena) {
        boolean acceso = false;
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM ESTUDIANTE WHERE ID_STUDENT = ? AND PASSWORD = ?");
            pt.setString(1, usuario);
            pt.setString(2, contrasena);
            rs = pt.executeQuery();

            if (rs.next()) {
                objEstudiante = new Estudiante();
                acceso = true;
                objEstudiante.setCodigo(rs.getString("ID_STUDENT"));
                objEstudiante.setNombres(rs.getString("NAME"));
                objEstudiante.setApellidos(rs.getString("SURNAME"));
                objEstudiante.setMail(rs.getString("MAIL"));
                objEstudiante.setGenero(rs.getString("GENDER"));
                objEstudiante.setPassword(rs.getString("PASSWORD"));
                objEstudiante.setIdCiclo(rs.getInt("ID_CICLO"));
                objEstudiante.setIdEscuela(rs.getInt("ID_ESCUELA"));
            }

            rs.close();
            pt.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error en el método autenticarUsuario: " + e.getMessage());
        }
        return acceso;
    }

    public Estudiante BuscarEstudiante(Estudiante Est) {
        objEstudiante = null;
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM ESTUDIANTE WHERE ID_STUDENT=?");
            pt.setString(1, Est.getCodigo());

            rs = pt.executeQuery();
            System.out.println("Estudiante:" + rs);

            if (rs.next()) {
                objEstudiante = new Estudiante();
                objEstudiante.setCodigo(rs.getString("ID_STUDENT"));
                objEstudiante.setNombres(rs.getString("NAME"));
                objEstudiante.setApellidos(rs.getString("SURNAME"));
                objEstudiante.setMail(rs.getNString("MAIL"));
                objEstudiante.setGenero(rs.getString("GENDER"));
                objEstudiante.setPassword(rs.getString("PASSWORD"));
                objEstudiante.setIdCiclo(rs.getInt("ID_CICLO"));
                objEstudiante.setIdEscuela(rs.getInt("ID_ESCUELA"));
                objEstudiante.setIdEscuela(rs.getInt("ID_ESCUELA"));
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se encontro el estudiante");
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("El problema esta en el metodo Buscar");
        }
        return objEstudiante;
    }

    public void InsertarEstudiante(Estudiante Est) {

        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO ESTUDIANTE(ID_STUDENT,NAME,SURNAME,MAIL,GENDER,PASSWORD,ID_CICLO,ID_ESCUELA) VALUES(?,?,?,?,?,?,?,?);");
            pt.setString(1, Est.getCodigo());
            pt.setString(2, Est.getNombres());
            pt.setString(3, Est.getApellidos());
            pt.setString(4, Est.getMail());
            pt.setString(5, Est.getGenero());
            pt.setString(6, Est.getPassword());
            pt.setInt(7, Est.getIdCiclo());
            pt.setInt(8, Est.getIdEscuela());

            int res = pt.executeUpdate();
            System.out.println("El total de filas afectadas fueron:" + res);

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante ingresado correctamente");

            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar estudiante");
            }

            pt.close();
            cn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el estudiante");
        }
    }

    public void ModificarEstudiante(Estudiante Est) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("UPDATE ESTUDIANTE SET NAME=?,SURNAME=?,MAIL=?,GENDER=?,PASSWORD=?,ID_CICLO=? WHERE ID_STUDENT=?;");
            pt.setString(1, Est.getNombres());
            pt.setString(2, Est.getApellidos());
            pt.setString(3, Est.getMail());
            pt.setString(4, Est.getGenero());
            pt.setString(5, Est.getPassword());
            pt.setInt(6, Est.getIdCiclo());
            pt.setString(7, Est.getCodigo());

            int rs = pt.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante modificado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró al estudiante");
            }

            System.out.println("El total de columnas afectas fueron:" + rs);
            pt.close();
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar");
        }
    }

    public void EliminarEstudiante(Estudiante Est) {
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM ESTUDIANTE WHERE ID_STUDENT=?;");
            pt.setString(1, Est.getCodigo());

            int rs = pt.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Estudiante eliminado ");

            } else {

                JOptionPane.showMessageDialog(null, "Estudiante no eliminado ");
            }

            pt.close();
            cn.close();

        } catch (Exception e) {
            System.out.println("El error ocurrio en el metodo EliminarEstudiante");

        }
    }

    public List<Estudiante> buscarMatriculadosPorFecha(String fecha) {
        List<Estudiante> matriculados = new ArrayList<>();
        try {
            cn = ConexionBD.getConexionBD();
            pt = cn.prepareStatement(
                    "SELECT DISTINCT e.* FROM ESTUDIANTE e "
                    + "INNER JOIN MATRICULA m ON e.ID_STUDENT = m.ID_STUDENT "
                    + "WHERE DATE(m.FECHA_MATRICULA) = ?"
            );
            pt.setString(1, fecha);
            rs = pt.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setCodigo(rs.getString("ID_STUDENT"));
                estudiante.setNombres(rs.getString("NAME"));
                estudiante.setApellidos(rs.getString("SURNAME"));
                estudiante.setMail(rs.getString("MAIL"));
                estudiante.setGenero(rs.getString("GENDER"));
                estudiante.setIdCiclo(rs.getInt("ID_CICLO"));
                estudiante.setIdEscuela(rs.getInt("ID_ESCUELA"));
                matriculados.add(estudiante);
            }

            rs.close();
            pt.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error en el método buscarMatriculadosPorFecha: " + e.getMessage());
        }
        return matriculados;
    }

    public Estudiante getObjEstudiante() {
        return objEstudiante;
    }

    public static void main(String[] args) {
        MetodosEstudiante xd = new MetodosEstudiante();
        String codigotexto = "2023023531";
        Estudiante obj = new Estudiante();
        obj.setCodigo(codigotexto);

        obj = xd.BuscarEstudiante(obj);
        String codigo = obj.getCodigo();
        System.out.println("Toma tu codigo perra:" + codigo);

    }

}
