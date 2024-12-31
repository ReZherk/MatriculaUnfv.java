package controller;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.MetodosCurso;
import model.Estudiante;
import model.MetodosEstudiante;
import view.LoginUI;
import view.PerfilUsuarioPanel;

public class PerfilController1 {

    private PerfilUsuarioPanel view;
    private Estudiante model;
    private MetodosEstudiante estudianteDAO;

    public PerfilController1(PerfilUsuarioPanel view, Estudiante model) {
        this.view = view;
        this.model = model;
        this.estudianteDAO = new MetodosEstudiante();
        view.addGuardarListener(e -> guardarCambios());
        view.addEditarFotoListener(e -> editarFoto());
        
        cargarDatosEstudiante();
    }

      private void cargarDatosEstudiante() {
        // Obtener datos del estudiante desde la base de datos
        Estudiante estudianteDatos = estudianteDAO.BuscarEstudiante(model);
        if (estudianteDatos != null) {
            view.DatosEstudiante(estudianteDatos); // Usar el método mostrarEstudiante
        }
    }

    private void guardarCambios() {
        // Recoger información ingresada por el usuario
        Estudiante datosActualizados = view.recogerEstudiante(model);
        if (datosActualizados != null) { // Validar que los datos sean válidos
            estudianteDAO.ModificarEstudiante(datosActualizados);
            view.mostrarMensaje("Datos actualizados con éxito.");
        }
    }

    private void eliminarCuenta() {
        estudianteDAO.EliminarEstudiante(model);
        view.mostrarMensaje("Cuenta eliminada con éxito.");
         LoginUI ventana = new LoginUI();
            ventana.setVisible(true);
            this.dispose();
        // Cerrar la ventana actual y redirigir al panel de matrícula
        // Cerrar el formulario actual
 
    }

    private void editarFoto() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Seleccionar una foto de perfil");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    // Filtro para permitir solo imágenes (jpg, png)
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png"));

    int result = fileChooser.showOpenDialog(view);
    if (result == JFileChooser.APPROVE_OPTION) {
        java.io.File file = fileChooser.getSelectedFile(); // Obtener el archivo seleccionado

        try {
            // Cargar la imagen seleccionada en un objeto ImageIcon
            ImageIcon fotoSeleccionada = new ImageIcon(file.getAbsolutePath());

            // Escalar la imagen para que se ajuste al tamaño del JLabel
            Image image = fotoSeleccionada.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon fotoEscalada = new ImageIcon(image);

            // Actualizar el JLabel de la imagen de perfil
            view.actualizarImagenPerfil(fotoEscalada);

            // Puedes guardar la ruta o imagen en el modelo si es necesario
            model.setFotoPath(file.getAbsolutePath()); // Necesitarías agregar este atributo al modelo Estudiante
            view.mostrarMensaje("Foto de perfil actualizada con éxito.");
        } catch (Exception e) {
            view.mostrarMensaje("Error al cargar la imagen: " + e.getMessage());
        }
    } else {
        view.mostrarMensaje("No se seleccionó ninguna imagen.");
    }       
}

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}