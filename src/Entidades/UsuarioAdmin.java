package Entidades;

import Exceptiones.LeyendoException;
import Vista.UsuarioAdminView;
import Vista.UsuarioVendedorView;

public class UsuarioAdmin extends Persona{

	public UsuarioAdmin(String nombre, String apellido, int dni, String mail, String contrasenia) {
		super(nombre, apellido, dni, mail, contrasenia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsuarioAdmin ["+super.toString() + "]";
	}

	 @Override
	    public void iniciarVentana() throws LeyendoException {
	       new UsuarioAdminView();
	    }

	    

	    @Override
	    public String getRolPersistencia() { return "ADMIN"; }
		
	


}
