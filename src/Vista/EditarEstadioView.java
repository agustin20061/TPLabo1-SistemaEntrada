package Vista;
import java.io.File;import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	private JButton cambiarImagenButton = new JButton("Cambiar imagen");
	private JLabel imagenSeleccionada = new JLabel();
	private String rutaFoto;
	private JLabel imagenLabel = new JLabel();
	
	public EditarEstadioView(Estadio e) {
		this.estadio=e;
		rutaFoto = estadio.getFoto();
		
		crearVentana();
        cargarUbicaciones();

        setearBotonGuardar();
        setearBotonAgregarUbicacion();
        setearBotonImagen();
	}
	private void crearVentana() {
		 frame = new JFrame("Editar Estadio");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(700, 500);

	        JPanel datosPanel = new JPanel(new GridLayout(3, 2));

	        nombreField.setText(estadio.getNombre());
	        if(estadio.getFoto() != null && !estadio.getFoto().isEmpty()) {
	        	imagenLabel.setIcon(GestorImagenes.obtenerIcono(estadio.getFoto(), 200, 120));
	            imagenSeleccionada.setText(estadio.getFoto());
	        }
	        
	        datosPanel.add(nombreLabel);
	        datosPanel.add(nombreField);

	        datosPanel.add(new JLabel("Imagen: "));
	        datosPanel.add(imagenSeleccionada);

	        
	        datosPanel.add(imagenLabel);
	        datosPanel.add(cambiarImagenButton);
	        
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


	            // Imagen de la ubicación
	            JLabel imagen = new JLabel();

	            if(u.getFoto() != null && !u.getFoto().isEmpty()) {

	                imagen.setIcon(
	                    GestorImagenes.obtenerIcono(
	                        u.getFoto(),
	                        100,
	                        70
	                    )
	                );

	            }


	            // Datos de la ubicación
	            JLabel datos = new JLabel(
	                    "Lugar: " + u.getLugar() +
	                    " | Precio: $" + u.getPrecio() +
	                    " | Capacidad: " + u.getCantEspacio()
	            );


	            JButton modificarButton = new JButton("Modificar");
	            JButton borrarButton = new JButton("Borrar");


	            modificarButton.addActionListener(e -> {

	                new EditarUbicacionView(u, estadio);

	            });


	            borrarButton.addActionListener(e -> {

	                try {

	                    ubicacionServicio.borrar(u);
	                    estadio.getListaUbicacion().remove(u);
	                    cargarUbicaciones();

	                } catch (BorrandoException e1) {

	                    e1.printStackTrace();

	                }

	            });


	            // Agregamos todo a la fila
	            fila.add(imagen);
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
                estadio.setFoto(rutaFoto);

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
	
    private void setearBotonImagen() {

        cambiarImagenButton.addActionListener(e -> {

            String nuevaRuta = GestorImagenes.seleccionarYGuardarImagen(frame);

            if(nuevaRuta != null) {

                rutaFoto = nuevaRuta;

                imagenSeleccionada.setText(new File(rutaFoto).getName());

                imagenLabel.setIcon(GestorImagenes.obtenerIcono(rutaFoto, 200, 120));

            }

        });

    }
   
	
}
