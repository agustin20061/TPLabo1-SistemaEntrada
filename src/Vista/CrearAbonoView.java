package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Entidades.Abono;
import Entidades.Promocion;
import Servicio.AbonoServicio;
import Servicio.PromocionServicio;

public class CrearAbonoView {
	private JFrame frame;
	private JButton button = new JButton("Crear Abono");
	private JLabel precioLabel = new JLabel("Precio");
	private JTextField precioField = new JTextField();
	private JLabel cantEntradasLabel = new JLabel("Cantidad de Entradas");
	private JTextField cantEntradasField = new JTextField();
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	
	private AbonoServicio abonoServicio = new AbonoServicio();
	public CrearAbonoView() {
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Crear Abono");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		
		panel.add(nombreLabel);
		panel.add(nombreField);
		
		panel.add(precioLabel);
		panel.add(precioField);

		panel.add(cantEntradasLabel);
		panel.add(cantEntradasField);

		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				int precio= Integer.parseInt(precioField.getText());
				int cantEntradas= Integer.parseInt(cantEntradasField.getText());
				
				Abono p=new Abono(nombre,precio,cantEntradas);
				abonoServicio.grabar(p);
				frame.dispose();
				new GestionarAbonosView();
				System.out.println("se creo exitosamente");
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
