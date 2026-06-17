package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Entidades.Estadio;
import Entidades.Ubicacion;
import Servicio.PersonaServicio;
import Servicio.UbicacionServicio;

public class CrearUbicacionView {
	private JFrame frame;
	private JButton button = new JButton("Salir");
	private JButton buttonAgregarUbicacion = new JButton("CrearUbicacion");
	private JLabel lugarLabel = new JLabel("Lugar");
	private JLabel precioLabel = new JLabel("Precio");
	private JTextField lugarField = new JTextField();
	private JTextField precioField = new JTextField();
	private JLabel cantEspacioLabel = new JLabel("Cantidad de lugares");
	private JTextField cantEspacioField = new JTextField();
	private JLabel nombreEstadioLabel = new JLabel("Nombre Del Estadio");
	/*private JLabel fotoLabel = new JLabel("Foto");
	private JTextField fotoField = new JTextField();
	*/
	private Estadio estadio;
	private UbicacionServicio ubicacionServicio = new UbicacionServicio();
	public CrearUbicacionView(Estadio e) {
		this.estadio =e;
		crearVentana();
		crearUbicacion();
		salir();
	}
	
	private void crearVentana() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreEstadioLabel);
		panel.add(new JLabel(estadio.getNombre()));
		panel.add(lugarLabel);
		panel.add(lugarField);
		panel.add(precioLabel);
		panel.add(precioField);
		panel.add(cantEspacioLabel);
		panel.add(cantEspacioField);
		/*panel.add(fotoLabel);
		panel.add(fotoField);
		*/
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		frame.getContentPane().add(buttonAgregarUbicacion);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void crearUbicacion() {
		buttonAgregarUbicacion.addActionListener( e -> {
			try {
				String lugar = lugarField.getText();
				int precio= Integer.parseInt(precioField.getText());
				int cantLugares= Integer.parseInt(cantEspacioField.getText());
				Ubicacion u=new Ubicacion(lugar,precio,cantLugares,estadio.getId());
				ubicacionServicio.grabar(u);
				frame.dispose();
				new CrearUbicacionView(estadio);
				
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void salir() {
		button.addActionListener( e -> {
			try {
				new UsuarioAdminView();
				frame.dispose();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
