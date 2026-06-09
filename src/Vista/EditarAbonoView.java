package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Abono;
import Servicio.AbonoServicio;

public class EditarAbonoView {
	private JFrame frame;
	private JButton button = new JButton("Guardar Cambios");
	private JLabel precioLabel = new JLabel("Precio");
	private JTextField precioField = new JTextField();
	private JLabel cantEntradasLabel = new JLabel("Cantidad de Entradas");
	private JTextField cantEntradasField = new JTextField();
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private Abono abono;
	private AbonoServicio abonoServicio = new AbonoServicio();
	public EditarAbonoView(Abono a) {
		this.abono=a;
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Editar Abono");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		
		panel.add(nombreLabel);
		nombreField.setText(abono.getNombre());
		panel.add(nombreField);
		
		panel.add(precioLabel);
		precioField.setText(String.valueOf(abono.getPrecio()));
		panel.add(precioField);

		panel.add(cantEntradasLabel);
		cantEntradasField.setText(String.valueOf(abono.getCantEntradas()));
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
				p.setId(abono.getId());
				abonoServicio.modificar(p);
				frame.dispose();
				new GestionarAbonosView();
				System.out.println("se modificó  exitosamente");
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
