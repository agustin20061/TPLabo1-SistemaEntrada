package Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Persona;
import Entidades.Ubicacion;
import Entidades.UsuarioComun;
import Entidades.Venta;
import Servicio.EspectaculoServicio;
import Servicio.PersonaServicio;
import Servicio.PersonaAbonoServicio;
import Servicio.VentaServicio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class VenderEntradasView {

    private JFrame frame;
    private JLabel espectaculoLabel =new JLabel("Espectaculo");
    private JComboBox<Espectaculo> espectaculoCombo =new JComboBox<>();
    private JLabel estadioLabel =new JLabel("Estadio");
    private JLabel estadioValorLabel =new JLabel("");
    private JLabel ubicacionLabel =new JLabel("Ubicacion");
    private JComboBox<Ubicacion> ubicacionCombo = new JComboBox<>();
    private JLabel cantidadLabel =new JLabel("Cantidad");
    private JTextField cantidadField =new JTextField();
    private JButton comprarButton =new JButton("Comprar");
    private EspectaculoServicio espectaculoServicio =new EspectaculoServicio();
    private PersonaAbonoServicio personaAbonoServicio =new PersonaAbonoServicio();
    private VentaServicio ventaServicio =new VentaServicio();
    private JLabel totalLabel = new JLabel("Total");
    private JLabel totalValorLabel = new JLabel("$0");
    private List<Espectaculo> espectaculos;
    private PersonaServicio personaServicio =new PersonaServicio();
    private List<Persona> usuariosComun;
    private JLabel usuarioLabel =new JLabel("Usuario");
    private JComboBox<Persona> usuarioCombo =new JComboBox<>();
    
    public VenderEntradasView() {

        crearVentana();

        cargarEspectaculos();
        cargarUsuarios();
        setearComboEspectaculo();
        setearComboUbicacion();
        setearCantidad();
        setearBotonComprar();
    }

   

	private void crearVentana() {

        frame = new JFrame("Venta de Entradas");

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        
        
        panel.add(usuarioLabel);
        panel.add(usuarioCombo);
        
        panel.add(espectaculoLabel);
        panel.add(espectaculoCombo);

        panel.add(estadioLabel);
        panel.add(estadioValorLabel);
        
        panel.add(ubicacionLabel);
        panel.add(ubicacionCombo);

        panel.add(cantidadLabel);
        panel.add(cantidadField);
        
        panel.add(totalLabel);
        panel.add(totalValorLabel);

        frame.getContentPane().setLayout(new FlowLayout());

        frame.getContentPane().add(panel);
        frame.getContentPane().add(comprarButton);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cargarEspectaculos() {

        try {
            espectaculos = espectaculoServicio.leerTodos();
            for(Espectaculo e : espectaculos) {
                espectaculoCombo.addItem(e);
            }
            if(!espectaculos.isEmpty()) {
                actualizarDatos(espectaculos.get(0));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void cargarUsuarios() {
    	try {
    		usuariosComun = personaServicio.leerTodos();
            for(Persona e : usuariosComun) {
            	usuarioCombo.addItem(e);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
	}

    private void setearComboEspectaculo() {
        espectaculoCombo.addActionListener(e -> {
            Espectaculo espectaculoSeleccionado =(Espectaculo) espectaculoCombo.getSelectedItem();
            if(espectaculoSeleccionado != null) {
                actualizarDatos(espectaculoSeleccionado);
            }
        });
    }

    private void actualizarDatos( Espectaculo espectaculo) {
        Estadio estadio =espectaculo.getEstadio();
        estadioValorLabel.setText(estadio.getNombre());
        ubicacionCombo.removeAllItems();
        for(Ubicacion u :
                estadio.getListaUbicacion()) {
            ubicacionCombo.addItem(u);
        }
        actualizarTotal();
    }

    private void setearBotonComprar() {

        comprarButton.addActionListener(e -> {

            try {
            	UsuarioComun usuario=(UsuarioComun)usuarioCombo.getSelectedItem();
                Espectaculo espectaculo =(Espectaculo)espectaculoCombo.getSelectedItem();
                Ubicacion ubicacion =(Ubicacion)ubicacionCombo.getSelectedItem();
                LocalDateTime fechaHoy = LocalDateTime.now();

                int cantidad = Integer.parseInt( cantidadField.getText());
                Venta venta =new Venta(espectaculo,espectaculo.getEstadio(),ubicacion,cantidad,fechaHoy,cantidad*ubicacion.getPrecio());

                ventaServicio.grabar(venta);
                personaAbonoServicio.consumirEntrada(usuario.getPersonaAbono(),cantidad);
                JOptionPane.showMessageDialog(
                	    frame,
                	    "Valor sin descuento: $" + cantidad*ubicacion.getPrecio() +
                	    "\nValor con descuento: $" + ventaServicio.calcularTotalConAbono(cantidad*ubicacion.getPrecio(),usuario,cantidad)
                	);
                frame.dispose();
                new UsuarioVendedorView();

            } catch(Exception ex) {
            	 JOptionPane.showMessageDialog(
            	            frame,
            	            ex.getMessage(),
            	            "Error",
            	            JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void actualizarTotal() {
        totalValorLabel.setText("$0");
        try {
            Ubicacion ubicacion = (Ubicacion) ubicacionCombo.getSelectedItem();
            if (ubicacion == null)
                return;
            if (cantidadField.getText().isBlank())
                return;
            int cantidad = Integer.parseInt(cantidadField.getText());
            if (cantidad <= 0)
                return;
            int total = cantidad * ubicacion.getPrecio();
            UsuarioComun usuario =(UsuarioComun) usuarioCombo.getSelectedItem();
            	total = ventaServicio.calcularTotalConAbono(total,usuario,cantidad);
            totalValorLabel.setText("$" + total);
        } catch (NumberFormatException e) {
        }
    }
    private void setearComboUbicacion() {
        ubicacionCombo.addActionListener(e -> actualizarTotal());
    }
    private void setearCantidad() {
        cantidadField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarTotal();
            }
        });

    }
}