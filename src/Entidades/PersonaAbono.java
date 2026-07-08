package Entidades;

public class PersonaAbono {
	    private int id;
	    private UsuarioComun persona;
	    private Abono abono;
	    private int entradasRestantes;
	    private boolean activo;

	    public PersonaAbono(UsuarioComun persona, Abono abono, int entradasRestantes, boolean activo) {
	        this.persona = persona;
	        this.abono = abono;
	        this.entradasRestantes = entradasRestantes;
	        this.activo = activo;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public UsuarioComun getPersona() {
			return persona;
		}

		public void setPersona(UsuarioComun persona) {
			this.persona = persona;
		}

		public Abono getAbono() {
			return abono;
		}

		public void setAbono(Abono abono) {
			this.abono = abono;
		}

		public int getEntradasRestantes() {
			return entradasRestantes;
		}

		public void setEntradasRestantes(int entradasRestantes) {
			this.entradasRestantes = entradasRestantes;
		}

		public boolean isActivo() {
			return activo;
		}

		public void setActivo(boolean activo) {
			this.activo = activo;
		}

	    
}
