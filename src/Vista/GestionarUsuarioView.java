package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Entidades.Persona;
import Entidades.Promocion;
import Entidades.UsuarioComun;
import Exceptiones.LeyendoTodosException;
import Servicio.PersonaServicio;
import Servicio.PromocionServicio;

public class GestionarUsuarioView {
	private JFrame frame;
	private JButton buttonAgregarUsuario = new JButton("Agregar Usuario");
	private JButton buttonSalir = new JButton("Salir");
	private List<Persona> listaUsuarioComun=new ArrayList<>();
	private PersonaServicio personaServicio = new PersonaServicio();
	
	public GestionarUsuarioView() {
		try {
			this.listaUsuarioComun=personaServicio.leerTodos();
		} catch (LeyendoTodosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crearVentana();
		agregarUsuarioBoton();
		salirBoton();
	}
	private void crearVentana() {
	frame = new JFrame("Gestionar Usuarios");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel panel = new JPanel();
	frame.setSize(900, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
   
    // PANEL CENTRAL CON 2 COLUMNAS
    JPanel panelUsuario = new JPanel();
    panelUsuario.setLayout(new GridLayout(0, 2, 10, 10));
    for (Persona usuario: listaUsuarioComun) {
        panelUsuario.add(crearTarjetaUsuario((UsuarioComun) usuario));
    }

    JScrollPane scrollPane = new JScrollPane(panelUsuario);

	
    // PANEL INFERIOR
    JPanel panelInferior = new JPanel();
    panelInferior.add(buttonAgregarUsuario);
    panelInferior.add(buttonSalir);

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(panelInferior, BorderLayout.SOUTH);

    frame.setVisible(true);
	
    
}
 private JPanel crearTarjetaUsuario(UsuarioComun usuario) {

        JPanel tarjeta = new JPanel(new BorderLayout());

        tarjeta.setBorder(
                BorderFactory.createTitledBorder(usuario.getNombre()+" "+usuario.getApellido()));

        JPanel info = new JPanel(new GridLayout(4, 1));

        info.add(new JLabel("Dni: " + usuario.getDni()));
        info.add(new JLabel("Mail: " + usuario.getMail()));
        info.add(new JLabel("Contrasenia: " + usuario.getContrasenia()));
        if(usuario.getAbono()==null) {
        	info.add(new JLabel("Abono: "+ "-"));
        }
        info.add(new JLabel("Abono: "+ usuario.getAbono().getNombre()));
        
        tarjeta.add(info, BorderLayout.CENTER);

        JButton btnModificar = new JButton("Modificar");
        JButton btnBorrar = new JButton("Borrar");

        btnModificar.addActionListener(e -> {
            try {
                new EditarPersonaView(usuario);
            } catch (Exception ex) {
                ex.getMessage();
            }
        });

        btnBorrar.addActionListener(e -> {
            try {
            	personaServicio.borrar(usuario);
            	 frame.dispose();
                 new GestionarUsuarioView();
            } catch (Exception ex) {
                ex.getMessage();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);

        tarjeta.add(panelBotones, BorderLayout.SOUTH);

        return tarjeta;
    }

private void agregarUsuarioBoton() {

	buttonAgregarUsuario.addActionListener( e -> {
		try {
			frame.dispose();
			new CrearCuentaView();
			
		} catch (Exception e1) {
			JDialog dialog = new JDialog(frame,"Error",true);
			dialog.add(new JLabel(e1.getMessage()));
			dialog.setVisible(true);
		}
	});
}
private void salirBoton() {
	
	buttonSalir.addActionListener( e -> {
		try {
			frame.dispose();
			new UsuarioVendedorView();
			
		} catch (Exception e1) {
			JDialog dialog = new JDialog(frame,"Error",true);
			dialog.add(new JLabel(e1.getMessage()));
			dialog.setVisible(true);
		}
	});
}
}