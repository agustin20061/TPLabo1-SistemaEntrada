package Entidades;

import java.time.LocalDateTime;

public class Promocion {
	private int id;
	private String nombre;
	private LocalDateTime tiempoInicio;
	private LocalDateTime tiempoFinal;
	private float descuento;
	public Promocion(String nombre, LocalDateTime tiempoInicio, LocalDateTime tiempoFinal, float descuento) {
		super();
		this.nombre = nombre;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFinal = tiempoFinal;
		this.descuento = descuento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDateTime getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(LocalDateTime tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public LocalDateTime getTiempoFinal() {
		return tiempoFinal;
	}
	public void setTiempoFinal(LocalDateTime tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
