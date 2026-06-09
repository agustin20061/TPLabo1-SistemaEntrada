package Entidades;

public class Abono {
	private int id;
	private String nombre;
	private int precio;
	private int cantEntradas;
	public Abono(String nombre, int precio, int cantEntradas) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantEntradas = cantEntradas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCantEntradas() {
		return cantEntradas;
	}
	public void setCantEntradas(int cantEntradas) {
		this.cantEntradas = cantEntradas;
	}
	
	
}
