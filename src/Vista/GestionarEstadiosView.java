package Vista;
import java.awt.Image;
import javax.swing.ImageIcon;
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
import Exceptiones.LeyendoTodosException;
import Persistencia.CrudUbicacion;
import Servicio.EspectaculoServicio;
import Servicio.EstadioServicio;
import Servicio.GestorImagenes;

public class GestionarEstadiosView {
	private JFrame frame;
	private JButton buttonAgregarEstadio = new JButton("Agregar Estadio");
	private JButton buttonModificarEstadio= new JButton("Modificar Estadio");
	private JButton buttonBorrarEstadio = new JButton("Borrar Estadio");
	private JButton buttonSalir = new JButton("Salir");
	private GestorImagenes gestorImagen=new GestorImagenes();
	
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
		try {
			listaEstadio=estadioServicio.leerTodos();
		} catch (LeyendoTodosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame("Gestionar Espectaculo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
       
        // PANEL CENTRAL CON 2 COLUMNAS
        JPanel panelEstadios = new JPanel();
        panelEstadios.setLayout(new GridLayout(0, 1, 10, 10));
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

	    JLabel imagenEstadio = new JLabel();
	    imagenEstadio.setHorizontalAlignment(JLabel.CENTER);

	    if(estadio.getFoto() != null && !estadio.getFoto().isEmpty()) {
	        imagenEstadio.setIcon(
	        		gestorImagen.obtenerImagen(
	                        estadio.getFoto(),
	                        180,
	                        120
	                )
	        );
	    }
	    JPanel info = new JPanel();
	    info.setLayout(new GridLayout(0,1));
	    info.add(new JLabel("Ubicaciones:"));
	    if(estadio.getListaUbicacion().isEmpty()) {
	        info.add(new JLabel("No tiene ubicaciones cargadas"));
	    } else {
	        for(Ubicacion u : estadio.getListaUbicacion()) {
	            JPanel panelUbicacion = new JPanel(new BorderLayout());
	            JLabel imagenUbicacion = new JLabel();
	            if(u.getFoto() != null && !u.getFoto().isEmpty()) {
	                imagenUbicacion.setIcon(
	                		gestorImagen.obtenerImagen(
	                                u.getFoto(),
	                                80,
	                                60
	                        )
	                );
	            }
	            JLabel datosUbicacion = new JLabel(
	                    u.getLugar()
	                    + " - $"
	                    + u.getPrecio()
	                    + " - "
	                    + u.getCantEspacio()
	                    + " lugares"
	            );
	            panelUbicacion.add(imagenUbicacion,BorderLayout.WEST);
	            panelUbicacion.add(datosUbicacion,BorderLayout.CENTER);
	            info.add(panelUbicacion);

	        }

	    }
	    tarjeta.add(
	            imagenEstadio,
	            BorderLayout.NORTH
	    );
	    tarjeta.add(
	            info,
	            BorderLayout.CENTER
	    );
	    JButton btnModificar = new JButton("Modificar");
	    JButton btnBorrar = new JButton("Borrar");
	    btnModificar.addActionListener(e -> {
	        try {
	            new EditarEstadioView(estadio);
	            frame.dispose();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
	    });
	    btnBorrar.addActionListener(e -> {
	        try {
	            estadioServicio.borrar(estadio);
	            frame.dispose();
	            new GestionarEstadiosView();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }

	    });
	    JPanel panelBotones = new JPanel();
	    panelBotones.add(btnModificar);
	    panelBotones.add(btnBorrar);
	    tarjeta.add(panelBotones,BorderLayout.SOUTH);
	    return tarjeta;
	}
	
	private void agregarEstadioBoton() {
	
		buttonAgregarEstadio.addActionListener( e -> {
			try {
				new CrearEstadioView();
				frame.dispose();
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
				frame.dispose();
			} catch (Exception e1) {
				JDialog dialog = new JDialog(frame,"Error",true);
				dialog.add(new JLabel(e1.getMessage()));
				dialog.setVisible(true);
			}
		});
	}
}
