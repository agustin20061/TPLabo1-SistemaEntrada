package Vista;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Persona;
import Entidades.UsuarioAdmin;
import Servicio.PersonaServicio;


public  class PersonaView {
	private JFrame frame;
	private JButton button = new JButton("click aqui");
	private JButton buttonEditar = new JButton("Editar");
	private JButton buttonBorrar = new JButton("Borrar");
	private JLabel nombreLabel = new JLabel("Nombre");
	private JLabel apellidoLabel = new JLabel("Apellido");
	private JTextField nombreField = new JTextField();
	private JTextField apellidoField = new JTextField();
	
	
	private PersonaServicio personaServicio = new PersonaServicio();
	
	public PersonaView(Persona persona) {
		crearVentana(persona);
		setearBoton();
		setearBotonBorrar(persona.getDni());
		setearBotonEditar(persona.getDni());
	}
	
	
	private void crearVentana(Persona persona) {
		if(persona instanceof UsuarioAdmin) {
			crearVentanaAdmin();
		}else {
			crearVentanaNormal();
		}
	}
	private void crearVentanaNormal() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(apellidoLabel);
		panel.add(apellidoField);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		frame.getContentPane().add(buttonBorrar);
		frame.getContentPane().add(buttonEditar);
		
		frame.pack();
		frame.setVisible(true);
	}
	private void crearVentanaAdmin() {
		frame = new JFrame("Ventana nueva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		frame.getContentPane().setLayout(new FlowLayout());
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(apellidoLabel);
		panel.add(apellidoField);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	private void setearBotonBorrar(int dni) {
		buttonBorrar.addActionListener( e -> {
			try {
				
				int p = personaServicio.borrarPersona(dni);
				System.out.println(p);
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void setearBotonEditar(int dni) {
		button.addActionListener( e -> {
			try {
				
				//Persona p = personaServicio.leerPersona(dni);
				//new EditarView(p);
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
