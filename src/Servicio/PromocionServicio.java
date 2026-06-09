package Servicio;

import java.util.List;

import Entidades.Estadio;
import Entidades.Persona;
import Entidades.Promocion;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudPersona;
import Persistencia.CrudPromocion;
import Persistencia.ICrud;

public class PromocionServicio implements IABMO<Promocion>{
	
	private ICrud<Promocion> promocionCrud = new CrudPromocion();
	private CrudPromocion crudPromocion = new CrudPromocion();
	
	
	
	
	@Override
	public Promocion leer(int id) throws LeyendoException {
		try {
			return promocionCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Promocion modificar(Promocion p) throws ModificarException {
		try {
			
			return promocionCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarPersonaException("Persona No Modificada ");
		}
	}
	@Override
	public void borrar(Promocion p) throws BorrandoException {
		try {
			 promocionCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoPersonaException("Persona No Borrada");
		}
		
	}
	@Override
	public void grabar(Promocion p) throws Exception {
		try {
			if(p.getNombre().isBlank())
			    throw new Exception("Ingrese un nombre");

			if(p.getDescuento() <= 0 || p.getDescuento()> 100)
			    throw new Exception("Descuento inválido");

			if(!p.getTiempoFinal().isAfter(p.getTiempoInicio()))
			    throw new Exception("La hora final debe ser posterior a la inicial");
			 promocionCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoPersonaException("Persona No Creada");
		}
		
	}
	public List<Promocion> obtenerTodos() {
		try {
			return crudPromocion.obtenerTodos();
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new PersonaNoEncontradaException("Persona No Encontrada - ID "+id);
		}
	}

}
