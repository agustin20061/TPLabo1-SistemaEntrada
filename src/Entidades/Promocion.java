package Entidades;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Promocion {
	private int id;
	private String nombre;
	private LocalTime tiempoInicio;
	private LocalTime tiempoFinal;
	private float descuento;
		public Promocion(String nombre, Time tiempoInicio, Time tiempoFinal, float descuento) {
			super();
			this.nombre = nombre;
			this.tiempoInicio =tiempoInicio.toLocalTime();
		    this.tiempoFinal = tiempoFinal.toLocalTime();
		    this.descuento = descuento;
		}
		
	public Promocion(int id, String nombre, LocalTime tiempoInicio, LocalTime tiempoFinal, float descuento) {
			super();
			this.id = id;
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
	public LocalTime getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(LocalTime tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public LocalTime getTiempoFinal() {
		return tiempoFinal;
	}
	public void setTiempoFinal(LocalTime tiempoFinal) {
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
