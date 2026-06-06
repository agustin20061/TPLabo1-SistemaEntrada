package Servicio;

import Entidades.Persona;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.ICrud;
import Persistencia.CrudPersona;

public class PersonaServicio implements IABMO<Persona>{
	
	private ICrud<Persona> personaCrud = new CrudPersona();
	private CrudPersona crudPersona = new CrudPersona();
	
	public Persona iniciarSesionPersona(String mail, String contrasenia) throws PersonaNoEncontradaException {
		try {
			return crudPersona.iniciarSesion(mail,contrasenia);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - MAIL: "+mail+" - CONTRASENIA: "+contrasenia);
		}
	}
	
	
	@Override
	public Persona leer(int id) throws LeyendoException {
		try {
			return personaCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Persona modificar(Persona p) throws ModificarException {
		try {
			
			return personaCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Persona p) throws BorrandoException {
		try {
			 personaCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Persona p) throws GrabandoException {
		try {
			 personaCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}
}
