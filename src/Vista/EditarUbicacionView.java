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
import Entidades.Ubicacion;
import Servicio.EstadioServicio;
import Servicio.UbicacionServicio;

public class EditarUbicacionView {
	private JFrame frame;
	private JButton buttonEditarUbicacion = new JButton("Editar Ubicacion");
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
	private Ubicacion ubicacion;
	private UbicacionServicio ubicacionServicio = new UbicacionServicio();
	
	public EditarUbicacionView(Ubicacion u,Estadio e) {
		this.ubicacion=u;
		this.estadio=e;
		crearVentana();
		editarUbicacion();
	}
	
	private void crearVentana() {
		frame = new JFrame("Editar Ubicacion");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreEstadioLabel);
		panel.add(new JLabel(estadio.getNombre()));
		
		panel.add(lugarLabel);
		lugarField.setText(ubicacion.getLugar());
		panel.add(lugarField);
		
		panel.add(precioLabel);
		precioField.setText(String.valueOf(ubicacion.getPrecio()));
		panel.add(precioField);
		
		
		panel.add(cantEspacioLabel);
		cantEspacioField.setText(String.valueOf(ubicacion.getCantEspacio()));
		panel.add(cantEspacioField);
		/*panel.add(fotoLabel);
		panel.add(fotoField);
		*/
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(buttonEditarUbicacion);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void editarUbicacion() {
		buttonEditarUbicacion.addActionListener( e -> {
			try {
				String lugar = lugarField.getText();
				int precio= Integer.parseInt(precioField.getText());
				int cantLugares= Integer.parseInt(cantEspacioField.getText());
				Ubicacion u=new Ubicacion(ubicacion.getId(),lugar,precio,cantLugares,ubicacion.getIdestadio());
				ubicacionServicio.modificar(u);
				ubicacion.setLugar(lugar);
				ubicacion.setPrecio(precio);
				ubicacion.setCantEspacio(cantLugares);
				frame.dispose();
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	
}
