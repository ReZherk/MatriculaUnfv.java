package controller;

import model.Estudiante;
import model.MatriculaRealizada;
import model.MatriculaRealizadaDAO;
import view.PanelCursos;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.Desktop;


public class MatriculaRealizadaController {

    private Estudiante modelo; // El estudiante logueado
    private MatriculaRealizadaDAO matriculaDAO; // Accede a la base de datos de la matrícula del estudiante logueado
    private PanelCursos view; // La vista que se debe actualizar con estos datos

    // Constructor
    public MatriculaRealizadaController(Estudiante modelo, PanelCursos view) {
        this.modelo = modelo;
        this.view = view;
        this.matriculaDAO = new MatriculaRealizadaDAO();
    }

    // Método para obtener los datos de matrícula de estudiante logueado
    public ArrayList<MatriculaRealizada> obtenerMatricula() {
        try {
            return matriculaDAO.obtenerDatosMatricula(modelo.getCodigo()); // Se obtienen los cursos desde la base de datos, usando el código del estudiante
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de cursos matriculados");
            return new ArrayList<>(); // Retorna una lista vacía si ocurre algún error
        }
    }
    
    // Método para filtrar los datos de matrícula según el año y los ciclos(semestre)   
    public ArrayList<MatriculaRealizada> filtrarMatricula(String añoM, int[] ciclos) {
        List<Integer> listaCiclos = Arrays.stream(ciclos).boxed().collect(Collectors.toList()); // Convertir ciclos a una lista de enteros
        ArrayList<MatriculaRealizada> listaDatos = obtenerMatricula(); // Obtienes la lista completa de matrículas
        ArrayList<MatriculaRealizada> listaFiltrada = new ArrayList<>(); // Se crea una lista para las coincidencias
        for (MatriculaRealizada datos : listaDatos){
            String año = datos.getFecha().substring(0, 4); //Solo los 4 primeros digitos de "FECHA"    
            if (año.equals(añoM) && listaCiclos.contains(datos.getCiclo())) { 
                listaFiltrada.add(datos);
            }
        }
        return listaFiltrada;
    }
    
     
    public void ListarDatosMatriculaControlador(String añoM, int[] ciclos) {
         ArrayList<MatriculaRealizada> listaFiltrada = filtrarMatricula(añoM, ciclos);

        view.modelo.setNumRows(listaFiltrada.size()); // Establecer el número de filas en la tabla

        int i = 0;
        for (MatriculaRealizada datos : listaFiltrada) {
            view.modelo.setValueAt(datos.getIdCurso(), i, 0);
            view.modelo.setValueAt(datos.getCurso(), i, 1);
            view.modelo.setValueAt(datos.getCreditos(), i, 2);
            view.modelo.setValueAt(datos.getSeccion(), i, 3);
            view.modelo.setValueAt(datos.getHorario(), i, 4);
            view.modelo.setValueAt(datos.getProfesor(), i, 5);
            i++;
        }

        view.tabla.setModel(view.modelo); // Actualiza la tabla con el modelo filtrado
    }

    public void pintarInterseccionHorario(String añoM, int[] ciclos) {       
        for (int i = 0; i < view.modeloHorario.getRowCount(); i++) {
            for (int j = 1; j < view.modeloHorario.getColumnCount(); j++) { // Columnas del 1 al 6
                view.modeloHorario.setValueAt(null, i, j);
            }
        }
        
        ArrayList<MatriculaRealizada> listaFiltrada = filtrarMatricula(añoM, ciclos);

        for (MatriculaRealizada datos : listaFiltrada) { 
            int aula_tipo = datos.getIdAula();
            marcarHorario(datos.getDia1(), datos.getHoraInicio1(), datos.getHoraFin1(), datos.getCurso(), aula_tipo);
            marcarHorario(datos.getDia2(), datos.getHoraInicio2(), datos.getHoraFin2(), datos.getCurso(), aula_tipo);
        }
    }

    // Método que marca las intersecciones de la tablaHorario
    private void marcarHorario(int dia, int horaInicio, int horaFin, String curso, int aula) {
        if (dia > 0 && dia <= 6) {
            for (int i = (horaInicio - 1); i <= (horaFin - 2); i++) {
                if (i >= 0 && i < 14) {
                    Object valorActual = view.modeloHorario.getValueAt(i, dia); // Obtener valor actual de celda
                    String nuevoValor;
                    if (valorActual == null || valorActual.toString().isEmpty()) { // Si no hay contenido se asigna el nuevo curso
                        nuevoValor = curso + "|" + aula;
                    } else {
                        nuevoValor = valorActual.toString() + "/" + curso + "|" + aula; // Si ya hay contenido se concatena con el nuevo curso
                    }
                    view.modeloHorario.setValueAt(nuevoValor, i, dia); // Actualiza la celda con el valor concatenado
                }
            }
        }
    }
    
    
    public String obtenerNombreEscuela(int idEscuela) {
        String escuela = null;
        switch(idEscuela){
            case 1: escuela = "Ingeniería de Sistemas";
            break;
            
            case 2: escuela = "Ingeniería Industrial";
            break;
            
            case 3: escuela = "Ingeniería de Transporte";
            break;
            
            case 4: escuela = "Ingeniería Agroindustrial";
            break;
        } 
        return escuela;
    }
    
    // Método para generar un PDF de la Boleta de Matrícula
    public void generarPDFMatricula(String añoM, int[] ciclos, int Semestre) {
        try {

            // Crear archivo PDF
            Date date = new Date();
            String fechaActual = new SimpleDateFormat("yyyyMMddHHmm").format(date);
            String fecha = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String nombreArchivo = "Matrícula_" + modelo.getNombres()+ "" + modelo.getApellidos()+ "" + fechaActual + ".pdf";
            
            // Verificar y crear directorio si no existe
            File directorio = new File("src/pdf");
            if (!directorio.exists()){ 
                directorio.mkdirs();
            } // Crea los directorios necesarios
            
            File file = new File(directorio, nombreArchivo);
            FileOutputStream archivo = new FileOutputStream(file);
        
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Encabezado del PDF
            Image img = Image.getInstance("src/resources/images/encabezado.PNG"); // Cambiar ruta según tu imagen
            img.scaleToFit(250, 250);
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);

            Paragraph titulo = new Paragraph("BOLETA DE MATRÍCULA", new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);
            
            doc.add(new Paragraph("\n")); // Espacio en blanco
            
            Paragraph facultad = new Paragraph("Facultad de Ingeniería Industrial y de Sistemas", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            facultad.setAlignment(Element.ALIGN_CENTER);
            doc.add(facultad);

            doc.add(new Paragraph("\n\n"));
            
            int idEscuela = modelo.getIdEscuela();
            String escuela = obtenerNombreEscuela(idEscuela);

            // Información del estudiante
            PdfPTable tablaInfo = new PdfPTable(2); //tabla con 2 columnas
            tablaInfo.setWidthPercentage(100); //ocupará el 100% del ancho de la página
            tablaInfo.setWidths(new float[]{15, 85}); // ancho de las columnas en %

            PdfPCell celda= new PdfPCell(new Phrase("Alumno:", new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD)));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
            
            celda= new PdfPCell(new Phrase(modelo.getNombres() + " " + modelo.getApellidos()));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
            
            celda= new PdfPCell(new Phrase("Código:", new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD)));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
            
            celda= new PdfPCell(new Phrase(modelo.getCodigo()));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
            
            celda= new PdfPCell(new Phrase("Escuela:", new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD)));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
            
            celda= new PdfPCell(new Phrase(escuela));
            celda.setBorder(Rectangle.NO_BORDER);
            tablaInfo.addCell(celda);
      
            doc.add(tablaInfo);

            doc.add(new Paragraph("\n\n")); // Espacio en blanco

            // Tabla de cursos
            PdfPTable tablaCursos = new PdfPTable(5);
            tablaCursos.setWidthPercentage(100);
            tablaCursos.setWidths(new float[]{15, 15, 15, 40, 15});

            tablaCursos.addCell(new PdfPCell(new Phrase("Periodo", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)))).setBackgroundColor(new BaseColor(244, 143, 83));
            tablaCursos.addCell(new PdfPCell(new Phrase("Código", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)))).setBackgroundColor(new BaseColor(244, 143, 83));
            tablaCursos.addCell(new PdfPCell(new Phrase("Sección", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)))).setBackgroundColor(new BaseColor(244, 143, 83));
            tablaCursos.addCell(new PdfPCell(new Phrase("Nombre del Curso", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)))).setBackgroundColor(new BaseColor(244, 143, 83));
            tablaCursos.addCell(new PdfPCell(new Phrase("Créditos", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)))).setBackgroundColor(new BaseColor(244, 143, 83));

            ArrayList<MatriculaRealizada> listaFiltrada = filtrarMatricula(añoM, ciclos);
            String periodo = añoM + " - " + Semestre;

            for (MatriculaRealizada datos : listaFiltrada) {
                tablaCursos.addCell(new PdfPCell(new Phrase(periodo)));
                tablaCursos.addCell(new PdfPCell(new Phrase(String.valueOf(datos.getIdCurso()))));
                tablaCursos.addCell(new PdfPCell(new Phrase(datos.getSeccion())));
                tablaCursos.addCell(new PdfPCell(new Phrase(datos.getCurso())));
                tablaCursos.addCell(new PdfPCell(new Phrase(String.valueOf(datos.getCreditos()))));
            }

            doc.add(tablaCursos);

            doc.add(new Paragraph("\n\n\n\n\n\n")); // Espacio en blanco

            Paragraph firma = new Paragraph("_________\n\nFirma del Estudiante\n\n" + fecha, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            
            doc.add(new Paragraph("\n"));
            
            Paragraph mensaje = new Paragraph("¡Que tengas un año académico lleno de éxitos y aprendizajes!", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(228, 95, 14)));
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(file); // Abrir el documento generado automáticamente

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        } 
    }
}