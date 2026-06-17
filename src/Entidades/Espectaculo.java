package Entidades;

import java.time.LocalDate;

public class Espectaculo {
	private int id;
	private String nombre;
	private Estadio estadio;
	private String descripcion;
	private LocalDate 	fecha;
	
	public Espectaculo(String nombre, Estadio estadio, String descripcion,LocalDate fecha) {
		this.nombre = nombre;
		this.estadio = estadio;
		this.descripcion = descripcion;
		this.fecha =fecha;
	}
	
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Estadio getEstadio() {
		return estadio;
	}
	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	@Override
	public String toString() {
	    return nombre;
	}
	
}
