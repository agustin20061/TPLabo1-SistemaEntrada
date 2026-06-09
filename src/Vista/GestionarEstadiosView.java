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
import javax.swing.JTextField;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Ubicacion;
import Persistencia.CrudUbicacion;
import Servicio.EspectaculoServicio;
import Servicio.EstadioServicio;

public class GestionarEstadiosView {
	private JFrame frame;
	private JButton buttonAgregarEstadio = new JButton("Agregar Estadio");
	private JButton buttonModificarEstadio= new JButton("Modificar Estadio");
	private JButton buttonBorrarEstadio = new JButton("Borrar Estadio");
	private JButton buttonSalir = new JButton("Salir");
	
	
	private List<Estadio> listaEstadio=new ArrayList<>();
	private  List<Ubicacion> listaUbicacion=new ArrayList<>();
	private EstadioServicio estadioServicio = new EstadioServicio();
	private CrudUbicacion crudUbicacion = new CrudUbicacion();
	public GestionarEstadiosView() {
		crearVentana();
		agregarEstadioBoton();
		salirBoton();
	}
	
	private void crearVentana() {
		listaEstadio=estadioServicio.obtenerTodos();
		frame = new JFrame("Gestionar Espectaculo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
       
        // PANEL CENTRAL CON 2 COLUMNAS
        JPanel panelEstadios = new JPanel();
        panelEstadios.setLayout(new GridLayout(0, 2, 10, 10));
        for (Estadio estadio : listaEstadio) {
            panelEstadios.add(crearTarjetaEstadio(estadio));
        }

        JScrollPane scrollPane = new JScrollPane(panelEstadios);

		
        // PANEL INFERIOR
        JPanel panelInferior = new JPanel();
        panelInferior.add(buttonAgregarEstadio);
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
	 private JPanel crearTarjetaEstadio(Estadio estadio) {

		 JPanel tarjeta = new JPanel(new BorderLayout());

		    tarjeta.setBorder(
		            BorderFactory.createTitledBorder(
		                    estadio.getNombre()
		            )
		    );

		    JPanel info = new JPanel();

		    info.setLayout(new GridLayout(0, 1));

		    info.add(new JLabel("Ubicaciones:"));

		    /*List<Ubicacion> ubicaciones =
		            crudUbicacion.obtenerPorEstadio(
		                    estadio.getId()
		            );*/

		    if(estadio.getListaUbicacion().isEmpty()) {

		        info.add(
		            new JLabel(
		                "No tiene ubicaciones cargadas"
		            )
		        );

		    } else {

		        for(Ubicacion u : estadio.getListaUbicacion()) {

		            info.add(
		                new JLabel(
		                    u.getLugar()
		                    + " - $"
		                    + u.getPrecio()
		                    + " - "
		                    + u.getCantEspacio()
		                    + " lugares"
		                )
		            );
		        }
		    }

		    tarjeta.add(info, BorderLayout.CENTER);

		    JButton btnModificar =
		            new JButton("Modificar");

		    JButton btnBorrar =
		            new JButton("Borrar");

		    btnModificar.addActionListener(e -> {
		        try {

		            new ModificarEstadioView(estadio);

		        } catch (Exception ex) {

		            mostrarError(ex.getMessage());
		        }
		    });

		    btnBorrar.addActionListener(e -> {
		        try {

		            estadioServicio.borrar(estadio);

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
	
	private void agregarEstadioBoton() {
	
		buttonAgregarEstadio.addActionListener( e -> {
			try {
				new CrearEstadioView();
				
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
				new UsuarioAdminView();
				
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
