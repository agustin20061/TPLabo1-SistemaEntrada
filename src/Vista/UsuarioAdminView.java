package Vista;

import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;

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
	private JLabel fechaDesdeLabel = new JLabel("Desde");
	private JLabel fechaHastaLabel = new JLabel("Hasta");
	private JButton buttonGestionarPromocion =new JButton("Gestionar Promociones");

	private JButton buttonGestionarAbono =new JButton("Gestionar Abonos");

	private JSpinner fechaDesdeSpinner = new JSpinner(new SpinnerDateModel());

	private JSpinner fechaHastaSpinner = new JSpinner(new SpinnerDateModel());

	public UsuarioAdminView() {
		
		crearVentana();
		setearBotonVerVentas();
		setearBotonGestionarEspectaculo();
		setearBotonGestionarEstadio();
		setearBotonCerrarSesion();
		setearBotonGestionarPromocion();
		setearBotonGestionarAbono();
	}
	

	private void crearVentana() {

		frame = new JFrame("Usuario Admin");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelBotones = new JPanel();
		JSpinner.DateEditor editorDesde =
		        new JSpinner.DateEditor(
		                fechaDesdeSpinner,
		                "dd/MM/yyyy");

		fechaDesdeSpinner.setEditor(editorDesde);

		JSpinner.DateEditor editorHasta =
		        new JSpinner.DateEditor(
		                fechaHastaSpinner,
		                "dd/MM/yyyy");

		fechaHastaSpinner.setEditor(editorHasta);
		panelBotones.setLayout(new FlowLayout());

		panelBotones.add(buttonGestionarEspectaculo);
		panelBotones.add(buttonGestionarEstadio);
		panelBotones.add(buttonGestionarPromocion);
		panelBotones.add(buttonGestionarAbono);

		panelBotones.add(fechaDesdeLabel);
		panelBotones.add(fechaDesdeSpinner);

		panelBotones.add(fechaHastaLabel);
		panelBotones.add(fechaHastaSpinner);

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

	    buttonVerVentas.addActionListener(e -> {

	        try {

	            java.util.Date fechaDesdeUtil =
	                    (java.util.Date)
	                            fechaDesdeSpinner.getValue();

	            java.util.Date fechaHastaUtil =
	                    (java.util.Date)
	                            fechaHastaSpinner.getValue();

	            java.sql.Date fechaDesde =
	                    new java.sql.Date(
	                            fechaDesdeUtil.getTime());

	            java.sql.Date fechaHasta =
	                    new java.sql.Date(
	                            fechaHastaUtil.getTime());

	            frame.dispose();

	            new VerVentasView(
	                    fechaDesde,
	                    fechaHasta);

	        } catch (Exception e1) {

	            e1.printStackTrace();
	        }
	    });
	}

	private void setearBotonGestionarPromocion() {

	    buttonGestionarPromocion.addActionListener(e -> {

	        try {

	            frame.dispose();
	            new GestionarPromocionView();

	        } catch (Exception e1) {

	            JDialog dialog =
	                    new JDialog(frame, "Error", true);

	            dialog.add(
	                    new JLabel(e1.getMessage()));

	            dialog.pack();
	            dialog.setVisible(true);
	        }
	    });
	}
	private void setearBotonGestionarAbono() {

	    buttonGestionarAbono.addActionListener(e -> {

	        try {

	            frame.dispose();
	            new GestionarAbonosView();

	        } catch (Exception e1) {

	            JDialog dialog =
	                    new JDialog(frame, "Error", true);

	            dialog.add(
	                    new JLabel(e1.getMessage()));

	            dialog.pack();
	            dialog.setVisible(true);
	        }
	    });
	}
}
