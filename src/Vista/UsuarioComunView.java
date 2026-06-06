package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Persona;
import Entidades.UsuarioAdmin;
import Servicio.PersonaServicio;

public class UsuarioComunView {

	private JFrame frame;
	private JButton button = new JButton("click aqui");
	private JButton buttonEditar = new JButton("Editar");
	private JButton buttonBorrar = new JButton("Borrar");
	private JButton buttonComprarAbono= new JButton("Comprar Abono");
	private JLabel nombreLabel = new JLabel("Nombre");
	private JLabel apellidoLabel = new JLabel("Apellido");
	private JTextField nombreField = new JTextField();
	private JTextField apellidoField = new JTextField();
	private JLabel mailLabel = new JLabel("Mail");
	private JTextField mailField = new JTextField();
	private JLabel dniLabel = new JLabel("Dni");
	private JTextField dniField = new JTextField();
	
	
	private PersonaServicio personaServicio = new PersonaServicio();
	
	public UsuarioComunView(Persona persona) {
		crearVentana(persona);
		setearBoton();
		setearBotonBorrar(persona);
		setearBotonEditar(persona);
		setearBotonComprarAbono(persona);
	}
	
	
	


	private void crearVentana(Persona persona) {

		frame = new JFrame("Usuario Comun");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelDatos = new JPanel();

		panelDatos.setLayout(new GridLayout(4, 2));

		panelDatos.add(nombreLabel);
		panelDatos.add(nombreField);

		panelDatos.add(apellidoLabel);
		panelDatos.add(apellidoField);

		panelDatos.add(mailLabel);
		panelDatos.add(mailField);

		panelDatos.add(dniLabel);
		panelDatos.add(dniField);

		// cargar datos
		nombreField.setText(persona.getNombre());
		apellidoField.setText(persona.getApellido());
		mailField.setText(persona.getMail());
		dniField.setText(String.valueOf(persona.getDni()));

		// opcional: que no puedan editar directo
		dniField.setEditable(false);

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		panelBotones.add(buttonComprarAbono);
		panelBotones.add(buttonEditar);
		panelBotones.add(buttonBorrar);

		frame.getContentPane().setLayout(new GridLayout(2, 1));

		frame.getContentPane().add(panelDatos);
		frame.getContentPane().add(panelBotones);

		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
	private void setearBotonBorrar(Persona p) {
		buttonBorrar.addActionListener( e -> {
			try {
				
				personaServicio.borrar(p);
				frame.dispose();

				new IniciarSesionView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	private void setearBotonEditar(Persona p) {
		buttonEditar.addActionListener( e -> {
			try {
				frame.dispose();
				new EditarPersonaView(p);
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void setearBotonComprarAbono(Persona persona) {
		
		buttonComprarAbono.addActionListener( e -> {
			try {
				//new ComprarAbonoView(persona);
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void setearBoton() {
		/*button.addActionListener( e -> {
			try {
				String mail = mailField.getText();
				String contrasenia = new String(contraseniaField.getPassword());
				String p = personaServicio.leerPersona(mail,contrasenia);
				System.out.println(p);
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});*/
	}

}
