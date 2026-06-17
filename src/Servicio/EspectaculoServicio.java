package Servicio;

import java.util.List;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Exceptiones.BorrandoEspectaculoException;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.EspectaculoNoEncontradaException;
import Exceptiones.GrabandoEspectaculoException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosEspectaculoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarEspectaculoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudEspectaculo;
import Persistencia.CrudEstadio;
import Persistencia.CrudUbicacion;
import Persistencia.ICrud;

public class EspectaculoServicio implements IABMO<Espectaculo>{
	private ICrud<Espectaculo> EspectaculoCrud = new CrudEspectaculo();
	
	
	
	@Override
	public Espectaculo leer(int id) throws LeyendoException {
		try {
			return EspectaculoCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new EspectaculoNoEncontradaException("Espectaculo No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Espectaculo modificar(Espectaculo p) throws ModificarException {
		try {
			
			return EspectaculoCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarEspectaculoException("Espectaculo No Modificada ");
		}
	}
	@Override
	public void borrar(Espectaculo p) throws BorrandoException {
		try {
			EspectaculoCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoEspectaculoException("Espectaculo No Borrada");
		}
		
	}
	@Override
	public void grabar(Espectaculo p) throws GrabandoException {
		try {
			EspectaculoCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoEspectaculoException("Espectaculo No Creada");
		}
		
	}
	

	@Override
	public List<Espectaculo> leerTodos() throws LeyendoTodosException {
try {	
			
			return EspectaculoCrud.leerTodos();
		} catch (LeyendoTodosException e) {
			e.printStackTrace();
			throw new LeyendoTodosEspectaculoException("Espectaculos no leidos");
		}
	}




}
