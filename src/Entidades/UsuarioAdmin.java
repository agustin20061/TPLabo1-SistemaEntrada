package Entidades;

import Vista.UsuarioAdminView;

public class UsuarioAdmin extends Persona{

	public UsuarioAdmin(String nombre, String apellido, int dni, String mail, String contrasenia) {
		super(nombre, apellido, dni, mail, contrasenia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsuarioAdmin ["+super.toString() + "]";
	}




}
