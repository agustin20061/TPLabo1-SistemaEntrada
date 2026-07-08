package Vista;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entidades.Abono;
import Entidades.Persona;
import Entidades.PersonaAbono;
import Entidades.UsuarioComun;
import Servicio.AbonoServicio;
import Servicio.PersonaAbonoServicio;
import Servicio.PersonaServicio;

public class VenderAbonoView {

    private JFrame frame;
    private JLabel usuarioLabel = new JLabel("Usuario");
    private JComboBox<Persona> usuarioCombo = new JComboBox<>();
    private JLabel abonoLabel = new JLabel("Abono");
    private JComboBox<Abono> abonoCombo = new JComboBox<>();
    private JLabel precioLabel = new JLabel("Precio");
    private JLabel precioValorLabel = new JLabel("$0");
    private JButton comprarButton = new JButton("Comprar");
    private PersonaServicio personaServicio = new PersonaServicio();
    private AbonoServicio abonoServicio = new AbonoServicio();
    private PersonaAbonoServicio personaAbonoServicio = new PersonaAbonoServicio();
    private List<Persona> usuarios;
    private List<Abono> abonos;


    public VenderAbonoView() {

        crearVentana();
        cargarUsuarios();
        cargarAbonos();
        setearComboAbono();
        setearBotonComprar();
    }


    private void crearVentana() {

        frame = new JFrame("Venta de Abono");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(usuarioLabel);
        panel.add(usuarioCombo);


        panel.add(abonoLabel);
        panel.add(abonoCombo);


        panel.add(precioLabel);
        panel.add(precioValorLabel);


        frame.setLayout(new FlowLayout());

        frame.add(panel);
        frame.add(comprarButton);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    private void cargarUsuarios() {
        try {
            usuarios = personaServicio.leerTodos();
            for(Persona p : usuarios){
                usuarioCombo.addItem(p);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }



    private void cargarAbonos(){
        try {
            abonos = abonoServicio.leerTodos();
            for(Abono a : abonos){
                abonoCombo.addItem(a);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }



    private void setearComboAbono(){
        abonoCombo.addActionListener(e -> {
            Abono abono =(Abono)abonoCombo.getSelectedItem();
            if(abono != null){
                precioValorLabel.setText(
                        "$"+abono.getPrecio()
                );
            }

        });

    }

    private void setearBotonComprar(){
        comprarButton.addActionListener(e -> {
            try {
                UsuarioComun usuario = (UsuarioComun)usuarioCombo.getSelectedItem();
                Abono abono =(Abono)abonoCombo.getSelectedItem();
                PersonaAbono personaAbono = new PersonaAbono(usuario,abono,abono.getCantEntradas(),true);
                personaAbonoServicio.grabar(personaAbono);
                usuario.setPersonaAbono(personaAbono);

                JOptionPane.showMessageDialog(
                        frame,
                        "Abono comprado correctamente"
                );


                frame.dispose();
                new UsuarioVendedorView();



            } catch(Exception ex){


                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        });

    }

}
