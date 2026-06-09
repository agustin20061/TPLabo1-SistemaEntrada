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

import Entidades.Promocion;
import Servicio.PromocionServicio;

public class EditarPromocionView {
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
	private Promocion promocion;
	private PromocionServicio promocionServicio = new PromocionServicio();
	public EditarPromocionView(Promocion p) {
		this.promocion=p;
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Modificar Promocion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		
		panel.add(nombreLabel);
		nombreField.setText(promocion.getNombre());
		panel.add(nombreField);
		panel.add(tiempoInicioLabel);
		horaInicioSpinner.setValue(promocion.getTiempoInicio());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(horaInicioSpinner, "HH:mm");
		horaInicioSpinner.setEditor(editor);
		
		
		panel.add(horaInicioSpinner);

		panel.add(tiempoFinalLabel);
		
		horaFinSpinner.setValue(promocion.getTiempoFinal());
		JSpinner.DateEditor editorFinal = new JSpinner.DateEditor(horaFinSpinner, "HH:mm");
		horaFinSpinner.setEditor(editorFinal);
		panel.add(horaFinSpinner);
		panel.add(descuentoLabel);
		descuentoField.setText(String.valueOf(promocion.getDescuento()));
		panel.add(descuentoField);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
	    button.addActionListener(e -> {
	        try {

	            String nombre = nombreField.getText();
	            float descuento = Float.parseFloat(descuentoField.getText());

	            Date fechaInicio = (Date) horaInicioSpinner.getValue();
	            LocalTime horaInicio = fechaInicio.toInstant()
	                    .atZone(java.time.ZoneId.systemDefault())
	                    .toLocalTime();

	            Date fechaFin = (Date) horaFinSpinner.getValue();
	            LocalTime horaFin = fechaFin.toInstant()
	                    .atZone(java.time.ZoneId.systemDefault())
	                    .toLocalTime();

	            promocion.setNombre(nombre);
	            promocion.setTiempoInicio(horaInicio);
	            promocion.setTiempoFinal(horaFin);
	            promocion.setDescuento(descuento);

	            promocionServicio.modificar(promocion);

	            frame.dispose();
	            new GestionarPromocionView();

	            System.out.println("Se modificó exitosamente");

	        } catch (Exception e1) {

	            JDialog dialog = new JDialog(frame, "Error", true);
	            dialog.add(new JLabel(e1.getMessage()));
	            dialog.pack();
	            dialog.setLocationRelativeTo(frame);
	            dialog.setVisible(true);

	        }
	    });
	}
}
