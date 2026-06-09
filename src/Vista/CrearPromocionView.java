package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Entidades.Promocion;
import Entidades.UsuarioComun;
import Servicio.PersonaServicio;
import Servicio.PromocionServicio;

public class CrearPromocionView {
	private JFrame frame;
	private JButton button = new JButton("Crear Promocion");
	private JLabel tiempoInicioLabel = new JLabel("Tiempo Inicio");
	private JLabel tiempoFinalLabel = new JLabel("Tiempo Final");
	private JSpinner horaInicioSpinner = new JSpinner(new SpinnerDateModel());
	private JSpinner horaFinSpinner = new JSpinner(new SpinnerDateModel());
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JLabel descuentoLabel = new JLabel("Descuento");
	private JTextField descuentoField = new JTextField();
	
	private PromocionServicio promocionServicio = new PromocionServicio();
	public CrearPromocionView() {
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Crear Promocion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(tiempoInicioLabel);

		horaInicioSpinner.setEditor(
			new JSpinner.DateEditor(horaInicioSpinner, "HH:mm")
		);
		panel.add(horaInicioSpinner);

		panel.add(tiempoFinalLabel);
		
		horaFinSpinner.setEditor(
			new JSpinner.DateEditor(horaFinSpinner, "HH:mm")
		);
		panel.add(horaFinSpinner);
		panel.add(descuentoLabel);
		panel.add(descuentoField);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				//LocalTime horaInicio= LocalTime.parse(tiempoInicioField.getText());
				//LocalTime horaFin =LocalTime.parse(tiempoFinalField.getText());
				float descuento= Integer.parseInt(descuentoField.getText());
				
				Date fechaInicio = (Date) horaInicioSpinner.getValue();

				LocalTime horaInicio = fechaInicio.toInstant()
				    .atZone(java.time.ZoneId.systemDefault())
				    .toLocalTime();
				
				Date fechaFin = (Date) horaFinSpinner.getValue();

				LocalTime horaFin = fechaFin.toInstant()
				    .atZone(java.time.ZoneId.systemDefault())
				    .toLocalTime();
				
				Promocion p=new Promocion(nombre,horaInicio,horaFin,descuento);
				promocionServicio.grabar(p);
				frame.dispose();
				new GestionarPromocionView();
				System.out.println("se creo exitosamente");
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
