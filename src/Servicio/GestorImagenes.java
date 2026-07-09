package Servicio;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;


public class GestorImagenes {

    public  String seleccionarYGuardarImagen(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen");
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg"));
        int opcion = chooser.showOpenDialog(frame);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File archivo = chooser.getSelectedFile();
        try {
            Path carpeta = Paths.get("src/Fotos");
            if (!Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }
            String nombreNuevo =System.currentTimeMillis() + "_" + archivo.getName();
            Path destino = carpeta.resolve(nombreNuevo);
            Files.copy(archivo.toPath(),destino,StandardCopyOption.REPLACE_EXISTING);
            return "Fotos/" + nombreNuevo;
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
    public  ImageIcon obtenerImagen(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon("src/" + ruta);
        Image imagen = icono.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }
}

