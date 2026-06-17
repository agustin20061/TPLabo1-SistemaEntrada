package Entidades;

import java.time.LocalDateTime;

public class Venta {
	private int id;
	private Espectaculo espectaculo;
	private Estadio estadio;
	private Ubicacion ubicacion;
	private int cantidad;
	private LocalDateTime fechaCompra;
	private int total;
	public Venta(Espectaculo espectaculo, Estadio estadio, Ubicacion ubicacion, int cantidad, LocalDateTime fechaCompra,
			int total) {
		super();
		this.espectaculo = espectaculo;
		this.estadio = estadio;
		this.ubicacion = ubicacion;
		this.cantidad = cantidad;
		this.fechaCompra = fechaCompra;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Espectaculo getEspectaculo() {
		return espectaculo;
	}
	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}
	public Estadio getEstadio() {
		return estadio;
	}
	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
