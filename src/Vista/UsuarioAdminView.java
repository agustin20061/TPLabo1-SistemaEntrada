package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Persona;
import Servicio.PersonaServicio;

public class UsuarioAdminView {
	private JFrame frame;
	private JButton button = new JButton("Cerrar sesion");
	private JButton buttonVerVentas = new JButton("Ver Ventas");
	private JButton buttonGestionarEstadio= new JButton("Gestionar Estadios");
	private JButton buttonGestionarEspectaculo= new JButton("Gestionar Espectaculos");

	public UsuarioAdminView() {
		
		crearVentana();
		setearBotonVerVentas();
		setearBotonGestionarEspectaculo();
		setearBotonGestionarEstadio();
		setearBotonCerrarSesion();
	}
	

	private void crearVentana() {

		frame = new JFrame("Usuario Admin");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		panelBotones.add(buttonGestionarEspectaculo);
		panelBotones.add(buttonGestionarEstadio);
		panelBotones.add(buttonVerVentas);
		panelBotones.add(button);

		frame.getContentPane().setLayout(new GridLayout(2, 1));

		
		frame.getContentPane().add(panelBotones);

		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
	
	
	
	private void setearBotonCerrarSesion() {
		button.addActionListener( e -> {
			try {
				frame.dispose();
				new IniciarSesionView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}





	private void setearBotonGestionarEspectaculo() {
		buttonGestionarEspectaculo.addActionListener( e -> {
			try {
				frame.dispose();
				new GestionarEspectaculosView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
		
	



	private void setearBotonGestionarEstadio() {
		buttonGestionarEstadio.addActionListener( e -> {
			try {
				frame.dispose();
				new GestionarEstadiosView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	


	private void setearBotonVerVentas() {
		
		
	}




}
