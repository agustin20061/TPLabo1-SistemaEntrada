package Servicio;

import java.util.List;

import Entidades.Abono;
import Entidades.Promocion;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudAbono;
import Persistencia.CrudPromocion;
import Persistencia.ICrud;

public class AbonoServicio implements IABMO<Abono>{
	
	private ICrud<Abono> abonoCrud = new CrudAbono();
	private CrudAbono crudAbono = new CrudAbono();
	
	
	
	
	@Override
	public Abono leer(int id) throws LeyendoException {
		try {
			return abonoCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Abono modificar(Abono p) throws ModificarException {
		try {
			
			return abonoCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Abono p) throws BorrandoException {
		try {
			abonoCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Abono p) {
		try {
			
			abonoCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}
	public List<Abono> obtenerTodos() {
		try {
			return crudAbono.obtenerTodos();
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}


}
