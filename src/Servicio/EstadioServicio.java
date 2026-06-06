package Servicio;

import java.util.List;

import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudEstadio;
import Persistencia.CrudPersona;
import Persistencia.ICrud;

public class EstadioServicio implements IABMO<Estadio>{
	private ICrud<Estadio> EstadioCrud = new CrudEstadio();
	private CrudEstadio crudEstadio= new CrudEstadio();
	
	
	
	@Override
	public Estadio leer(int id) throws LeyendoException {
		try {
			return EstadioCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Estadio modificar(Estadio p) throws ModificarException {
		try {
			
			return EstadioCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Estadio p) throws BorrandoException {
		try {
			EstadioCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Estadio p) throws GrabandoException {
		try {
			EstadioCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}

	public int obtenerID(String nombre) {
		try {
			return crudEstadio.obtenerID(nombre);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
		
	}

	public List<Estadio> obtenerTodos() {
		try {
			return crudEstadio.obtenerTodos();
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}

}
