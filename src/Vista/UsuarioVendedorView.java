package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsuarioVendedorView {
	private JFrame frame;
	private JButton button = new JButton("Cerrar sesion");
	private JButton buttonVenderEntradas = new JButton("Vender Entradas");
	private JButton buttonGestionarUsuarios= new JButton("Gestionar Usuarios");
	private JButton buttonComprarAbono= new JButton("ComprarAbono");

	public UsuarioVendedorView() {
		
		crearVentana();
		setearBotonComprarAbono();
		setearBotonGestionarUsuario();
		setearBotonVenderEntradas();
		setearBotonCerrarSesion();
	}
	

	private void crearVentana() {

		frame = new JFrame("Usuario Admin");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		panelBotones.add(buttonVenderEntradas);
		panelBotones.add(buttonGestionarUsuarios);
		panelBotones.add(buttonComprarAbono);
		panelBotones.add(button);

		frame.getContentPane().setLayout(new GridLayout(2, 1));

		
		frame.getContentPane().add(panelBotones);

		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
	
	
	
	private void setearBotonCerrarSesion() {
		button.addActionListener( e -> {
			try {
				frame.dispose();
				new IniciarSesionView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}





	private void setearBotonComprarAbono() {
		buttonComprarAbono.addActionListener( e -> {
			try {
				frame.dispose();
				//new ComprarAbonoView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
		
	



	private void setearBotonGestionarUsuario() {
		buttonGestionarUsuarios.addActionListener( e -> {
			try {
				frame.dispose();
				new GestionarUsuarioView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	


	private void setearBotonVenderEntradas() {
		buttonVenderEntradas.addActionListener( e -> {
			try {
				frame.dispose();
				new VenderEntradasView();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
		
	}


}
