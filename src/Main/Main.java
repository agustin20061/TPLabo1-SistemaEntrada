package Main;

import Vista.IniciarSesionView;

public class Main {
	public static void main(String[] args) {
		//Persona personaLeida, personaModificada;
		//Persona p = new Persona(0,"Gaston","Geler",LocalDate.of(2026, 4, 29));
		
			/*ICrud<Persona> abm = new CrudPersonasH2DB();
			Persona p = abm.leer(1);
			
			try {
				abm.grabar(new Persona(2,"Pepe","Pepe",LocalDate.of(2026, 4, 29)));
			} catch (GrabandoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				abm.leer(2);
			} catch (LeyendoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("FIN");
			//abm.grabar(p);
			//personaLeida = abm.leer();
			//personaLeida.setApellido("pepepep");
			//abm.modificar(personaLeida);
			//personaModificada = abm.leer();
			//System.out.println(personaModificada.toString());
			*/
		IniciarSesionView view = new IniciarSesionView();	
		System.out.println(System.getProperty("user.dir"));	
	}
}
