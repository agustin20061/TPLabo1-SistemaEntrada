package Entidades;

import java.util.ArrayList;

import Exceptiones.LeyendoException;

public class UsuarioComun extends Persona{
	
	private PersonaAbono personaAbono;
	
	private ArrayList<Espectaculo> listaEspectaculo;
	
	
	public UsuarioComun(String nombre, String apellido, int dni, String mail, String contrasenia) {
		super(nombre, apellido, dni, mail, contrasenia);
	}

	
	public ArrayList<Espectaculo> getListaEspectaculo() {
		return listaEspectaculo;
	}
	public void setListaEspectaculo(ArrayList<Espectaculo> listaEspectaculo) {
		this.listaEspectaculo = listaEspectaculo;
	}
    @Override
    public void iniciarVentana() throws LeyendoException {
       
    }

   

    @Override
    public String getRolPersistencia() { return "COMUN"; }


	public PersonaAbono getPersonaAbono() {
		return personaAbono;
	}


	public void setPersonaAbono(PersonaAbono personaAbono) {
		this.personaAbono = personaAbono;
	}
	@Override
	public String toString() {
		return nombre+" "+apellido;
	}
}
