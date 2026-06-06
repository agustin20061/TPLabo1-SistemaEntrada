package Servicio;

import java.util.List;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudEspectaculo;
import Persistencia.ICrud;

public class EspectaculoServicio implements IABMO<Espectaculo>{
	private ICrud<Espectaculo> EspectaculoCrud = new CrudEspectaculo();
	private CrudEspectaculo crudEspectaculo= new CrudEspectaculo();
	
	
	
	@Override
	public Espectaculo leer(int id) throws LeyendoException {
		try {
			return EspectaculoCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Espectaculo modificar(Espectaculo p) throws ModificarException {
		try {
			
			return EspectaculoCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Espectaculo p) throws BorrandoException {
		try {
			EspectaculoCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Espectaculo p) throws GrabandoException {
		try {
			EspectaculoCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}
	
	public List<Espectaculo> obtenerTodos() {
		try {
			return crudEspectaculo.obtenerTodos();
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}




}
