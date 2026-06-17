package Servicio;

import java.util.List;

import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Exceptiones.UbicacionNoEncontradaException;
import Persistencia.CrudUbicacion;
import Persistencia.ICrud;

public class UbicacionServicio implements IABMO<Ubicacion>{
	private ICrud<Ubicacion> ubicacionCrud = new CrudUbicacion();
	
	
	
	
	@Override
	public Ubicacion leer(int id) throws LeyendoException {
		try {
			return ubicacionCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new UbicacionNoEncontradaException("Ubicacion No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Ubicacion modificar(Ubicacion p) throws ModificarException {
		try {
			
			return ubicacionCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Ubicacion p) throws BorrandoException {
		try {
			ubicacionCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Ubicacion p) throws GrabandoException {
		try {
			ubicacionCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}

	@Override
	public List<Ubicacion> leerTodos() throws LeyendoTodosException {
		// TODO Auto-generated method stub
		return null;
	}
}
