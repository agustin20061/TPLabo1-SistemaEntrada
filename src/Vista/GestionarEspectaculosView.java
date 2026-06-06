package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

import Entidades.Espectaculo;
import Entidades.Ubicacion;
import Persistencia.CrudUbicacion;
import Servicio.EspectaculoServicio;

public class GestionarEspectaculosView {
	private JFrame frame;
	private JButton buttonAgregarEspectaculo = new JButton("Agregar Espectaculo");
	private JButton buttonModificarEspectaculo= new JButton("Modificar Espectaculo");
	private JButton buttonBorrarEspectaculo = new JButton("Borrar Espectaculo");
	private JButton buttonSalir = new JButton("Salir");
	
	private JLabel mailLabel = new JLabel("Mail");
	private JLabel contraseniaLabel = new JLabel("Contrasenia");
	private JTextField mailField = new JTextField();
	private JTextField contraseniaField = new JTextField();
	private List<Espectaculo> listaEspectaculo=new ArrayList<>();
	private EspectaculoServicio espectaculoServicio = new EspectaculoServicio();
	
	public GestionarEspectaculosView() {
		crearVentana();
		agregarEspectaculoBoton();
		salirBoton();
	}
	
	private void crearVentana() {
		listaEspectaculo=espectaculoServicio.obtenerTodos();
		frame = new JFrame("Gestionar Espectaculo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
       
        // PANEL CENTRAL CON 2 COLUMNAS
        JPanel panelEspectaculos = new JPanel();
        panelEspectaculos.setLayout(new GridLayout(0, 2, 10, 10));
        for (Espectaculo espectaculo : listaEspectaculo) {
            panelEspectaculos.add(crearTarjetaEspectaculo(espectaculo));
        }

        JScrollPane scrollPane = new JScrollPane(panelEspectaculos);

		
        // PANEL INFERIOR
        JPanel panelInferior = new JPanel();
        panelInferior.add(buttonAgregarEspectaculo);
        panelInferior.add(buttonSalir);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        frame.setVisible(true);
		/*frame.getContentPane().add(panel);
		frame.getContentPane().add(buttonAgregarEspectaculo);
		frame.getContentPane().add(buttonModificarEspectaculo);
		frame.getContentPane().add(buttonBorrarEspectaculo);
		frame.getContentPane().add(buttonSalir);
		
		frame.pack();
		frame.setVisible(true);*/
        
	}
	 private JPanel crearTarjetaEspectaculo(Espectaculo espectaculo) {

	        JPanel tarjeta = new JPanel(new BorderLayout());

	        tarjeta.setBorder(
	                BorderFactory.createTitledBorder(espectaculo.getNombre()));

	        JPanel info = new JPanel(new GridLayout(3, 1));

	        info.add(new JLabel("Descripcion: " + espectaculo.getDescripcion()));
	        info.add(new JLabel("Fecha: " + espectaculo.getFecha()));
	        info.add(new JLabel("Estadio: " + espectaculo.getEstadio().getNombre()));

	        tarjeta.add(info, BorderLayout.CENTER);

	        JButton btnModificar = new JButton("Modificar");
	        JButton btnBorrar = new JButton("Borrar");

	        btnModificar.addActionListener(e -> {
	            try {
	                new ModificarEspectaculoView(espectaculo);
	            } catch (Exception ex) {
	                mostrarError(ex.getMessage());
	            }
	        });

	        btnBorrar.addActionListener(e -> {
	            try {
	            	espectaculoServicio.borrar(espectaculo);
	            } catch (Exception ex) {
	                mostrarError(ex.getMessage());
	            }
	        });

	        JPanel panelBotones = new JPanel();
	        panelBotones.add(btnModificar);
	        panelBotones.add(btnBorrar);

	        tarjeta.add(panelBotones, BorderLayout.SOUTH);

	        return tarjeta;
	    }
	
	private void agregarEspectaculoBoton() {
	
		buttonAgregarEspectaculo.addActionListener( e -> {
			try {
				new CrearEspectaculoView();
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
	private void salirBoton() {
		
		buttonAgregarEspectaculo.addActionListener( e -> {
			try {
				new UsuarioAdminView();
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
