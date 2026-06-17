package Vista;


import java.awt.BorderLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Entidades.Venta;
import Exceptiones.LeyendoTodosException;
import Servicio.VentaServicio;

public class VerVentasView {

    private JFrame frame;

    private JTable tabla;

    private JButton salirButton =new JButton("Salir");

    private VentaServicio ventaServicio =new VentaServicio();
    private  List<Venta> listaVentas=new ArrayList<>();

    public VerVentasView(Date fechaDesde,Date fechaHasta) {

        crearVentana(fechaDesde,fechaHasta);

        setearBotonSalir();
    }

    private void crearVentana(Date fechaDesde,Date fechaHasta) {

        frame = new JFrame("Reporte de Ventas");

        frame.setSize(700, 400);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        String[] columnas = {
                "Espectaculo",
                "Entradas Vendidas",
                "Total Recaudado"
        };

        
		try {
			listaVentas = ventaServicio.leerFechaEspectaculo(fechaDesde,fechaHasta);
		} catch (LeyendoTodosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Object[][] datos = new Object[listaVentas.size()][3];

        for(int i = 0; i < listaVentas.size(); i++) {

            Venta venta = listaVentas.get(i);

            datos[i][0] =
                    venta.getEspectaculo()
                         .getNombre();

            datos[i][1] =
                    venta.getCantidad();

            datos[i][2] =
                    "$" + venta.getTotal();
        }

        tabla = new JTable(
                datos,
                columnas);

        JScrollPane scrollPane =
                new JScrollPane(tabla);

        JPanel panelInferior =
                new JPanel();

        panelInferior.add(salirButton);

        frame.add(scrollPane,BorderLayout.CENTER);

        frame.add(panelInferior,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void setearBotonSalir() {
        salirButton.addActionListener(e -> {
            frame.dispose();
            new UsuarioAdminView();
        });
    }
}