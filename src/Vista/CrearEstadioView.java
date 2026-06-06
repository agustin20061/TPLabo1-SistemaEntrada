package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	/*private JLabel fotoLabel = new JLabel("Foto");
	private JTextField fotoField = new JTextField();
	*/
	private EstadioServicio  estadioServicio= new EstadioServicio();
	
	public CrearEstadioView() {
		crearVentana();
		setearBoton();
		setearBotonCrearUbicacion();
	}
	
	private void crearVentana() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreLabel);
		panel.add(nombreField);
	
		/*panel.add(fotoLabel);
		panel.add(fotoField);
		*/
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		frame.getContentPane().add(buttonAgregarUbicacion);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				
				//String foto= apellidoField.getText();
				
				//System.out.println(personaServicio.crearPersona(lugar,precio,cantLugares));
				
				
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
				Estadio e1=new Estadio(nombre);
				estadioServicio.grabar(e1);
				frame.dispose();
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
}
