package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estadio {
	private int id;
	private String nombre;
	private ArrayList<Ubicacion> listaUbicacion;
	private String foto;
	
	public Estadio(String nombre, ArrayList<Ubicacion> listaUbicacion,String foto) {
		this.nombre = nombre;
		this.listaUbicacion = listaUbicacion;
		this.setFoto(foto);
	}
	
	
	public Estadio(String nombre,String foto) {
		super();
		this.nombre = nombre;
		this.foto=foto;
	}
	


	public Estadio(int id, String nombre, List<Ubicacion> listaUbicacion,String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaUbicacion = (ArrayList<Ubicacion>) listaUbicacion;
		this.foto=foto;
	}


	public Estadio(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Ubicacion> getListaUbicacion() {
		return listaUbicacion;
	}

	public void setListaUbicacion(List<Ubicacion> ubicaciones) {
		this.listaUbicacion = (ArrayList<Ubicacion>) ubicaciones;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
	    return nombre;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	

	
	
}
