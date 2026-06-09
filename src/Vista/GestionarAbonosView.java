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

import Entidades.Abono;
import Entidades.Promocion;
import Servicio.AbonoServicio;
import Servicio.PromocionServicio;

public class GestionarAbonosView {
	private JFrame frame;
	private JButton buttonAgregarAbono = new JButton("Agregar Abono");
	private JButton buttonSalir = new JButton("Salir");
	private List<Abono> listaAbono=new ArrayList<>();
	private AbonoServicio abonoServicio = new AbonoServicio();
	
	public GestionarAbonosView() {
		crearVentana();
		agregarAbonoBoton();
		salirBoton();
	}
	private void crearVentana() {
	listaAbono=abonoServicio.obtenerTodos();
	frame = new JFrame("Gestionar Abonos");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel panel = new JPanel();
	frame.setSize(900, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
   
    // PANEL CENTRAL CON 2 COLUMNAS
    JPanel panelAbonos = new JPanel();
    panelAbonos.setLayout(new GridLayout(0, 2, 10, 10));
    for (Abono abono : listaAbono) {
        panelAbonos.add(crearTarjetaAbono(abono));
    }

    JScrollPane scrollPane = new JScrollPane(panelAbonos);

	
    // PANEL INFERIOR
    JPanel panelInferior = new JPanel();
    panelInferior.add(buttonAgregarAbono);
    panelInferior.add(buttonSalir);

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(panelInferior, BorderLayout.SOUTH);

    frame.setVisible(true);
	
    
}
 private JPanel crearTarjetaAbono(Abono abono) {

        JPanel tarjeta = new JPanel(new BorderLayout());

        tarjeta.setBorder(
                BorderFactory.createTitledBorder(abono.getNombre()));

        JPanel info = new JPanel(new GridLayout(2, 1));

        info.add(new JLabel("Precio: " + abono.getPrecio()));
        info.add(new JLabel("Cantidad de Entradas: " + abono.getCantEntradas()));

        tarjeta.add(info, BorderLayout.CENTER);

        JButton btnModificar = new JButton("Modificar");
        JButton btnBorrar = new JButton("Borrar");

        btnModificar.addActionListener(e -> {
            try {
            	frame.dispose();
                new EditarAbonoView(abono);
            } catch (Exception ex) {
                mostrarError(ex.getMessage());
            }
        });

        btnBorrar.addActionListener(e -> {
            try {
            	abonoServicio.borrar(abono);
            	 frame.dispose();
                 new GestionarAbonosView();
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

private void agregarAbonoBoton() {

	buttonAgregarAbono.addActionListener( e -> {
		try {
			frame.dispose();
			new CrearAbonoView();
			
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
