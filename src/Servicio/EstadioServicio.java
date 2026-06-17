package Servicio;

import java.util.List;

import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoEstadioException;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.EstadioNoEncontradaException;
import Exceptiones.GrabandoEstadioException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosEstadioException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarEstadioException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudEstadio;
import Persistencia.CrudPersona;
import Persistencia.ICrud;

public class EstadioServicio implements IABMO<Estadio>{
	private ICrud<Estadio> EstadioCrud = new CrudEstadio();
	
	
	
	@Override
	public Estadio leer(int id) throws LeyendoException {
		try {
			return EstadioCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new EstadioNoEncontradaException("Estadio No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Estadio modificar(Estadio p) throws ModificarException {
		try {
			
			return EstadioCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarEstadioException("Estadio No Modificada ");
		}
	}
	@Override
	public void borrar(Estadio p) throws BorrandoException {
		try {
			EstadioCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoEstadioException("Estadio No Borrada");
		}
		
	}
	@Override
	public void grabar(Estadio p) throws GrabandoException {
		try {
			EstadioCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoEstadioException("Estadio No Creada");
		}
		
	}



	@Override
	public List<Estadio> leerTodos() throws LeyendoTodosException {
		try {
			return EstadioCrud.leerTodos();
		} catch (LeyendoTodosException e) {
			e.printStackTrace();
			throw new LeyendoTodosEstadioException("leer todos los estadios no se pudo");
		}
	}
	public int obtenerID(String nombre) throws LeyendoException {

	    CrudEstadio crud = new CrudEstadio();

	    return crud.obtenerID(nombre);
	}

}
