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
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Ubicacion;
import Entidades.Venta;
import Servicio.EspectaculoServicio;
import Servicio.VentaServicio;

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
    private VentaServicio ventaServicio =new VentaServicio();
    private List<Espectaculo> espectaculos;

    public VenderEntradasView() {

        crearVentana();

        cargarEspectaculos();

        setearComboEspectaculo();

        setearBotonComprar();
    }

    private void crearVentana() {

        frame = new JFrame("Venta de Entradas");

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(new GridLayout(5, 2));

        panel.add(espectaculoLabel);
        panel.add(espectaculoCombo);

        panel.add(estadioLabel);
        panel.add(estadioValorLabel);

        panel.add(ubicacionLabel);
        panel.add(ubicacionCombo);

        panel.add(cantidadLabel);
        panel.add(cantidadField);

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

                actualizarDatos(
                        espectaculos.get(0));
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
    }

    private void setearBotonComprar() {

        comprarButton.addActionListener(e -> {

            try {

                Espectaculo espectaculo =(Espectaculo)espectaculoCombo.getSelectedItem();
                Ubicacion ubicacion =(Ubicacion)ubicacionCombo.getSelectedItem();
                LocalDateTime fechaHoy = LocalDateTime.now();

                int cantidad = Integer.parseInt( cantidadField.getText());

                Venta venta =new Venta(espectaculo,espectaculo.getEstadio(),ubicacion,cantidad,fechaHoy,cantidad*ubicacion.getPrecio());

                ventaServicio.grabar(venta);

 
                frame.dispose();
                new UsuarioVendedorView();

            } catch(Exception ex) {

            	ex.printStackTrace();

            }
        });
    }
}