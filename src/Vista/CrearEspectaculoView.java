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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Exceptiones.LeyendoTodosException;
import Servicio.EspectaculoServicio;
import Servicio.EstadioServicio;

public class CrearEspectaculoView {
	private JFrame frame;
	private JButton button = new JButton("Crear");
	
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JLabel descripcionLabel = new JLabel("Descripcion");
	private JTextField descripcionField = new JTextField();
	private JLabel estadioLabel = new JLabel("Estadio");
	private JComboBox<Estadio> estadioCombo = new JComboBox<>();
	private JLabel fechaLabel = new JLabel("Fecha");
	private List<Estadio> estadios;
	private JSpinner fechaSpinner =new JSpinner(new SpinnerDateModel());
	/*private JLabel fotoLabel = new JLabel("Foto");
	private JTextField fotoField = new JTextField();
	*/
	private EstadioServicio  estadioServicio= new EstadioServicio();
	private EspectaculoServicio espectaculoServicio=new EspectaculoServicio();
	
	public CrearEspectaculoView() {
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Crear Espectaculo");
		try {
			estadios = estadioServicio.leerTodos();
		} catch (LeyendoTodosException e) {
			JDialog dialog = new JDialog(frame, "Error", true);
		    dialog.add(new JLabel(e.getMessage()));
		    dialog.pack();
		    dialog.setVisible(true);
		    return;
		}
		for(Estadio e : estadios) {
		    estadioCombo.addItem(e);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(descripcionLabel);
		panel.add(descripcionField);
		panel.add(estadioLabel);
		panel.add(estadioCombo);
		panel.add(fechaLabel);
		JSpinner.DateEditor editor =
		        new JSpinner.DateEditor(
		                fechaSpinner,
		                "dd/MM/yyyy"
		        );

		fechaSpinner.setEditor(editor);
		panel.add(fechaSpinner);
	
		/*panel.add(fotoLabel);
		panel.add(fotoField);
		*/
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				String nombre = nombreField.getText();
				String descripcion =descripcionField.getText();
				Estadio estadioSeleccionado = (Estadio) estadioCombo.getSelectedItem();
				Date fechaSeleccionada = (Date) fechaSpinner.getValue();

				LocalDate fecha =
				        fechaSeleccionada.toInstant()
				                         .atZone(ZoneId.systemDefault())
				                         .toLocalDate();
				Espectaculo p=new Espectaculo(nombre,estadioSeleccionado,descripcion,fecha);
				if (nombreField.getText().trim().isEmpty()) {
				    JDialog dialog = new JDialog(frame, "Error", true);
				    dialog.add(new JLabel("Debe ingresar un nombre."));
				    dialog.pack();
				    dialog.setVisible(true);
				    return;
				}

				if (descripcionField.getText().trim().isEmpty()) {
				    JDialog dialog = new JDialog(frame, "Error", true);
				    dialog.add(new JLabel("Debe ingresar una descripción."));
				    dialog.pack();
				    dialog.setVisible(true);
				    return;
				}
				if (fecha.isBefore(LocalDate.now())) {
				    JDialog dialog = new JDialog(frame, "Error", true);
				    dialog.add(new JLabel("La fecha del espectáculo no puede ser anterior a la actual."));
				    dialog.pack();
				    dialog.setVisible(true);
				    return;
				}
				
				espectaculoServicio.grabar(p);
				frame.dispose();
				new UsuarioAdminView();
			} catch (Exception e1) {
				 e1.printStackTrace();
				    JDialog dialog = new JDialog(frame,"Error",true);
				    dialog.add(new JLabel(String.valueOf(e1)));
				    dialog.pack();
				    dialog.setVisible(true);
			}
		});
	}
	
}
