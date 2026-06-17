package Entidades;

import Exceptiones.LeyendoException;
import Vista.UsuarioVendedorView;

public class UsuarioVendedor extends Persona{

	public UsuarioVendedor(String nombre, String apellido, int dni, String mail, String contrasenia) {
		super(nombre, apellido, dni, mail, contrasenia);
		// TODO Auto-generated constructor stub
	}

	
	
	 @Override
	    public void iniciarVentana() throws LeyendoException {
	       new UsuarioVendedorView();
	    }

	    

	    @Override
	    public String getRolPersistencia() { return "VENDEDOR"; }
		
	
}
