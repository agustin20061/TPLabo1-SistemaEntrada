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

import Entidades.Persona;
import Entidades.UsuarioComun;
import Servicio.PersonaServicio;

public class CrearCuentaView {
	private JFrame frame;
	private JButton button = new JButton("Crear Usuario");
	private JLabel mailLabel = new JLabel("Mail");
	private JLabel contraseniaLabel = new JLabel("ContraseÃ±a");
	private JTextField mailField = new JTextField();
	private JPasswordField contraseniaField = new JPasswordField();
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JLabel apellidoLabel = new JLabel("Apellido");
	private JTextField apellidoField = new JTextField();
	private JLabel dniLabel = new JLabel("dni");
	private JTextField dniField = new JTextField();
	
	private PersonaServicio personaServicio = new PersonaServicio();
	public CrearCuentaView() {
		crearVentana();
		setearBoton();
	}
	
	private void crearVentana() {
		frame = new JFrame("Crear Usuario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(mailLabel);
		panel.add(mailField);
		panel.add(contraseniaLabel);
		panel.add(contraseniaField);
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(apellidoLabel);
		panel.add(apellidoField);
		panel.add(dniLabel);
		panel.add(dniField);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton() {
		button.addActionListener( e -> {
			try {
				String mail = mailField.getText();
				String contrasenia = new String(contraseniaField.getPassword());
				String nombre= nombreField.getText();
				String apellido= apellidoField.getText();
				int dni= Integer.parseInt(dniField.getText());
				UsuarioComun p=new UsuarioComun(nombre,apellido,dni,mail,contrasenia);
				personaServicio.grabar(p);
				frame.dispose();
				new GestionarUsuarioView();
				System.out.println("se creo exitosamente");
				
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}