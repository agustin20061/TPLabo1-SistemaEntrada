package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Entidades.Estadio;
import Entidades.Persona;
import Servicio.EstadioServicio;
import Servicio.PersonaServicio;

public class CrearEstadioView {
	private JFrame frame;
	private JButton button = new JButton("Crear");
	private JButton buttonAgregarUbicacion = new JButton("Agregar Ubicacion");
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JButton botonImagen = new JButton("Seleccionar imagen");
	private JLabel imagenSeleccionada = new JLabel("Ninguna imagen");
	private String rutaFoto = "a";
	private EstadioServicio  estadioServicio= new EstadioServicio();
	
	public CrearEstadioView() {
		crearVentana();
		setearBoton();
		setearBotonCrearUbicacion();
		setearBotonImagen();
	}
	
	private void crearVentana() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 5, 5));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreLabel);
		panel.add(nombreField);
	
		panel.add(botonImagen);
		panel.add(imagenSeleccionada);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		frame.getContentPane().add(buttonAgregarUbicacion);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				new GestionarEstadiosView();
				frame.dispose();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void setearBotonCrearUbicacion() {
		buttonAgregarUbicacion.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				Estadio e1 = new Estadio(nombre, rutaFoto);
				estadioServicio.grabar(e1);
				int idEstadio=estadioServicio.obtenerID(nombre);
				e1.setId(idEstadio);
				new CrearUbicacionView(e1);
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void setearBotonImagen() {

	    botonImagen.addActionListener(e -> {

	        rutaFoto = GestorImagenes.seleccionarYGuardarImagen(frame);

	        if(rutaFoto != null) {

	            imagenSeleccionada.setText(new File(rutaFoto).getName());

	        }

	    });

	}
}
