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

public class EditarPersonaView {
	private JFrame frame;
	private JButton button = new JButton("Edicion completa");
	private JLabel mailLabel = new JLabel("Mail");
	private JLabel contraseniaLabel = new JLabel("Contraseña");
	private JTextField mailField = new JTextField();
	private JPasswordField contraseniaField = new JPasswordField();
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private JLabel apellidoLabel = new JLabel("Apellido");
	private JTextField apellidoField = new JTextField();
	private JLabel dniLabel = new JLabel("Dni");
	private JTextField dniField = new JTextField();
	private PersonaServicio personaServicio = new PersonaServicio();
	public EditarPersonaView(Persona p) {
		crearVentana(p);
		setearBoton(p.getDni());
	}
	
	private void crearVentana(Persona p) {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(mailLabel);

		mailField.setText(p.getMail());
		panel.add(mailField);

		panel.add(contraseniaLabel);

		contraseniaField.setText(p.getContrasenia());
		panel.add(contraseniaField);

		panel.add(nombreLabel);

		nombreField.setText(p.getNombre());
		panel.add(nombreField);

		panel.add(apellidoLabel);

		apellidoField.setText(p.getApellido());
		panel.add(apellidoField);

		
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setearBoton(int dni) {
		button.addActionListener( e -> {
			try {
				String mail = mailField.getText();
				String contrasenia = new String(contraseniaField.getPassword());
				String nombre= nombreField.getText();
				String apellido= apellidoField.getText();
				Persona p=new UsuarioComun(nombre,apellido,dni,mail,contrasenia);
				System.out.println(personaServicio.modificar(p));
				frame.dispose();
				new UsuarioComunView(p);
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
