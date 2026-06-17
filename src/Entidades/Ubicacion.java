package Entidades;

public class Ubicacion {
	private int id;
	private String lugar;
	private int precio;
	private int cantEspacio;
	private int idEstadio;
	private String foto;
	public Ubicacion(int id,String lugar, int precio, int cantEspacio,int idestadio) {
		super();
		this.id=id;
		this.lugar = lugar;
		this.precio = precio;
		this.cantEspacio = cantEspacio;
		this.idEstadio=idestadio;
		
	}
	
	public Ubicacion(String lugar, int precio, int cantEspacio, int idEstadio) {
		super();
		this.lugar = lugar;
		this.precio = precio;
		this.cantEspacio = cantEspacio;
		this.idEstadio = idEstadio;
	}

	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCantEspacio() {
		return cantEspacio;
	}
	public void setCantEspacio(int cantEspacio) {
		this.cantEspacio = cantEspacio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdestadio() {
		return this.idEstadio;
	}
	public void setIdestadio(int idestadio) {
		this.idEstadio = idestadio;
	}
	
	@Override
	public String toString() {
	    return lugar;
	}
	
}
