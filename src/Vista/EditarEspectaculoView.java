package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Persona;
import Entidades.UsuarioComun;
import Servicio.EspectaculoServicio;
import Servicio.EstadioServicio;
import Servicio.PersonaServicio;

public class EditarEspectaculoView {
	private JFrame frame;
	private JButton button = new JButton("Edicion completa");
	private JLabel descripcionLabel = new JLabel("Descripcion");
	private JTextField descripcionField = new JTextField();
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JLabel estadioLabel = new JLabel("Estadio");
	private JComboBox<Estadio> estadioCombo = new JComboBox<>();
	private List<Estadio> estadios;
	private EspectaculoServicio espectaculoServicio = new EspectaculoServicio();
	private Espectaculo espectaculo;
	private EstadioServicio  estadioServicio= new EstadioServicio();
	private JSpinner fechaSpinner =new JSpinner(new SpinnerDateModel());
	private JLabel fechaLabel = new JLabel("Fecha");
	
	public EditarEspectaculoView(Espectaculo p) {
		this.estadios=estadioServicio.obtenerTodos();
		for(Estadio e : estadios) {
		    estadioCombo.addItem(e);
		}
		this.espectaculo=p;
		crearVentana();
		setearBoton(espectaculo.getId());
	}
	
	private void crearVentana() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		
		
		frame.getContentPane().setLayout(new FlowLayout());
		
		
		panel.add(nombreLabel);
		nombreField.setText(espectaculo.getNombre());
		panel.add(nombreField);
		
		panel.add(descripcionLabel);
		descripcionField.setText(espectaculo.getDescripcion());
		panel.add(descripcionField);

		
		panel.add(estadioLabel);
		estadioCombo.setSelectedItem(espectaculo.getEstadio());
		panel.add(estadioCombo);

		panel.add(fechaLabel);
		fechaSpinner.setValue(espectaculo.getFecha());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
		fechaSpinner.setEditor(editor);
		panel.add(fechaSpinner);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton(int id) {
		button.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				String descripcion = descripcionField.getText();
				Estadio estadioSeleccionado = (Estadio) estadioCombo.getSelectedItem();
				Date fechaSeleccionada = (Date) fechaSpinner.getValue();

				LocalDate fecha =
				        fechaSeleccionada.toInstant()
				                         .atZone(ZoneId.systemDefault())
				                         .toLocalDate();
				Espectaculo p=new Espectaculo(id,nombre,estadioSeleccionado,descripcion,fecha);
				System.out.println(espectaculoServicio.modificar(p));
				frame.dispose();
				new GestionarEspectaculosView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
