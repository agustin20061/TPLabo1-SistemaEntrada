package Entidades;

import java.util.ArrayList;

import Vista.UsuarioComunView;

public class UsuarioComun extends Persona{
	
	private Abono abono;
	
	private ArrayList<Espectaculo> listaEspectaculo;
	
	
	public UsuarioComun(String nombre, String apellido, int dni, String mail, String contrasenia) {
		super(nombre, apellido, dni, mail, contrasenia);
	}

	public Abono getAbono() {
		return abono;
	}
	public void setAbono(Abono abono) {
		this.abono = abono;
	}
	public ArrayList<Espectaculo> getListaEspectaculo() {
		return listaEspectaculo;
	}
	public void setListaEspectaculo(ArrayList<Espectaculo> listaEspectaculo) {
		this.listaEspectaculo = listaEspectaculo;
	}
	
	
}
