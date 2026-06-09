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
import Entidades.Promocion;
import Servicio.EspectaculoServicio;
import Servicio.PromocionServicio;

public class GestionarPromocionView {
	private JFrame frame;
	private JButton buttonAgregarPromocion = new JButton("Agregar Promocion");
	private JButton buttonSalir = new JButton("Salir");
	private List<Promocion> listaPromocion=new ArrayList<>();
	private PromocionServicio promocionServicio = new PromocionServicio();
	
	public GestionarPromocionView() {
		crearVentana();
		agregarPromocionBoton();
		salirBoton();
	}
	private void crearVentana() {
	listaPromocion=promocionServicio.obtenerTodos();
	frame = new JFrame("Gestionar Promociones");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel panel = new JPanel();
	frame.setSize(900, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
   
    // PANEL CENTRAL CON 2 COLUMNAS
    JPanel panelPromociones = new JPanel();
    panelPromociones.setLayout(new GridLayout(0, 2, 10, 10));
    for (Promocion promocion: listaPromocion) {
        panelPromociones.add(crearTarjetaPromocion(promocion));
    }

    JScrollPane scrollPane = new JScrollPane(panelPromociones);

	
    // PANEL INFERIOR
    JPanel panelInferior = new JPanel();
    panelInferior.add(buttonAgregarPromocion);
    panelInferior.add(buttonSalir);

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(panelInferior, BorderLayout.SOUTH);

    frame.setVisible(true);
	
    
}
 private JPanel crearTarjetaPromocion(Promocion promocion) {

        JPanel tarjeta = new JPanel(new BorderLayout());

        tarjeta.setBorder(
                BorderFactory.createTitledBorder(promocion.getNombre()));

        JPanel info = new JPanel(new GridLayout(3, 1));

        info.add(new JLabel("Hora  Inicio: " + promocion.getTiempoInicio()));
        info.add(new JLabel("Hora  Final: " + promocion.getTiempoFinal()));
        info.add(new JLabel("Descuento: " + promocion.getDescuento()));

        tarjeta.add(info, BorderLayout.CENTER);

        JButton btnModificar = new JButton("Modificar");
        JButton btnBorrar = new JButton("Borrar");

        btnModificar.addActionListener(e -> {
            try {
                new EditarPromocionView(promocion);
            } catch (Exception ex) {
                mostrarError(ex.getMessage());
            }
        });

        btnBorrar.addActionListener(e -> {
            try {
            	promocionServicio.borrar(promocion);
            	 frame.dispose();
                 new GestionarPromocionView();
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

private void agregarPromocionBoton() {

	buttonAgregarPromocion.addActionListener( e -> {
		try {
			frame.dispose();
			new CrearPromocionView();
			
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
			new UsuarioAdminView();
			
		} catch (Exception e1) {
			JDialog dialog = new JDialog(frame,"Error",true);
			dialog.add(new JLabel(e1.getMessage()));
			dialog.setVisible(true);
		}
	});
}
}
