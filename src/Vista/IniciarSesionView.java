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
import Entidades.UsuarioAdmin;
import Entidades.UsuarioComun;
import Entidades.UsuarioVendedor;
import Servicio.PersonaServicio;

public class IniciarSesionView {
	private JFrame frame;
	private JButton button = new JButton("Iniciar Sesion");
	private JLabel mailLabel = new JLabel("Mail");
	private JLabel contraseñaLabel = new JLabel("Contraseña");
	private JTextField mailField = new JTextField();
	private JPasswordField contraseniaField = new JPasswordField();
	
	private PersonaServicio personaServicio = new PersonaServicio();
	public IniciarSesionView() {
		crearVentana();
		setearBoton();
		
	}
	
	private void crearVentana() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(mailLabel);
		panel.add(mailField);
		panel.add(contraseñaLabel);
		panel.add(contraseniaField);
		
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
				Persona p= personaServicio.iniciarSesionPersona(mail,contrasenia);
				frame.dispose();
				p.iniciarVentana();
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	
}
