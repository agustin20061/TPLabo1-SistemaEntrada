package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Servicio.EstadioServicio;
import Servicio.UbicacionServicio;

public class EditarEstadioView {
	private JFrame frame;
	private JButton guardarButton = new JButton("Guardar Cambios");
	private JButton agregarUbicacionButton = new JButton("Agregar Ubicacion");
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField nombreField = new JTextField();
	private EstadioServicio  estadioServicio= new EstadioServicio();
	private UbicacionServicio ubicacionServicio=new UbicacionServicio();
	private JPanel ubicacionesPanel = new JPanel();
	private Estadio estadio;
	
	
	public EditarEstadioView(Estadio e) {
		this.estadio=e;
		crearVentana();
        cargarUbicaciones();

        setearBotonGuardar();
        setearBotonAgregarUbicacion();
	}
	private void crearVentana() {
		 frame = new JFrame("Editar Estadio");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(700, 500);

	        JPanel datosPanel = new JPanel(new GridLayout(1, 2));

	        nombreField.setText(estadio.getNombre());

	        datosPanel.add(nombreLabel);
	        datosPanel.add(nombreField);

	        ubicacionesPanel.setLayout(new GridLayout(0, 1));

	        JScrollPane scroll = new JScrollPane(ubicacionesPanel);

	        JPanel botonesPanel = new JPanel();
	        botonesPanel.add(agregarUbicacionButton);
	        botonesPanel.add(guardarButton);

	        frame.setLayout(new BorderLayout());

	        frame.add(datosPanel, BorderLayout.NORTH);
	        frame.add(scroll, BorderLayout.CENTER);
	        frame.add(botonesPanel, BorderLayout.SOUTH);

	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	}
	 protected void cargarUbicaciones() {

	        ubicacionesPanel.removeAll();

	        if(estadio.getListaUbicacion() != null) {

	            for(Ubicacion u : estadio.getListaUbicacion()) {

	                JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));

	                JLabel datos = new JLabel(
	                        "Lugar: " + u.getLugar() +
	                        " | Precio: $" + u.getPrecio() +
	                        " | Capacidad: " + u.getCantEspacio()
	                );

	                JButton modificarButton = new JButton("Modificar");
	                JButton borrarButton = new JButton("Borrar");

	                modificarButton.addActionListener(e -> {

	                    new EditarUbicacionView(u,estadio);

	                    cargarUbicaciones();
	                });

	                borrarButton.addActionListener(e -> {

	                    estadio.getListaUbicacion().remove(u);
	                    try {
							ubicacionServicio.borrar(u);
						} catch (BorrandoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    cargarUbicaciones();
	                   
	                });

	                fila.add(datos);
	                fila.add(modificarButton);
	                fila.add(borrarButton);

	                ubicacionesPanel.add(fila);
	            }
	        }

	        ubicacionesPanel.revalidate();
	        ubicacionesPanel.repaint();
	    }
	
	 private void setearBotonAgregarUbicacion() {

	        agregarUbicacionButton.addActionListener(e -> {

	            try {

	                new CrearUbicacionView(estadio);

	            } catch(Exception ex) {

	                JDialog dialog = new JDialog(frame, "Error", true);
	                dialog.add(new JLabel(ex.getMessage()));
	                dialog.pack();
	                dialog.setVisible(true);
	            }
	        });
	    }
    private void setearBotonGuardar() {

        guardarButton.addActionListener(e -> {

            try {

                estadio.setNombre(nombreField.getText());

                estadioServicio.modificar(estadio);

                frame.dispose();

                new GestionarEstadiosView();

            } catch(Exception ex) {

                JDialog dialog = new JDialog(frame, "Error", true);
                dialog.add(new JLabel(ex.getMessage()));
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }
	
	

	
}
