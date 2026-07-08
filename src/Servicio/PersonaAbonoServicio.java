package Servicio;

import java.util.List;

import Entidades.Abono;
import Entidades.Estadio;
import Entidades.PersonaAbono;
import Exceptiones.AbonoNoEncontradaException;
import Exceptiones.BorrandoAbonoException;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoAbonoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosAbonoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarAbonoException;
import Exceptiones.ModificarException;
import Persistencia.CrudAbono;
import Persistencia.CrudEstadio;
import Persistencia.CrudPersonaAbono;
import Persistencia.ICrud;

public class PersonaAbonoServicio implements IABMO<PersonaAbono>{
	private ICrud<PersonaAbono> personaAbonoCrud = new CrudPersonaAbono();

	@Override
	public PersonaAbono leer(int id) throws LeyendoException {
		try {
			return personaAbonoCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new AbonoNoEncontradaException("Abono No Encontrada - ID "+id);
		}
	}
	
	@Override
	public PersonaAbono modificar(PersonaAbono p) throws ModificarException {
		try {
			
			return personaAbonoCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarAbonoException("abono No Modificada ");
		}
	}
	@Override
	public void borrar(PersonaAbono p) throws BorrandoException {
		try {
			personaAbonoCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoAbonoException("abono No Borrada");
		}
		
	}
	@Override
	public void grabar(PersonaAbono p) throws GrabandoException {
		try {
			
			personaAbonoCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoAbonoException("Abono No Creada");
		}
		
	}
	@Override
	public List<PersonaAbono> leerTodos()throws LeyendoTodosException {
		try {
			return personaAbonoCrud.leerTodos();
		} catch (LeyendoTodosException e) {
			e.printStackTrace();
			throw new LeyendoTodosAbonoException("leer todas los abonos no se pudo ");
		}
	}

	public void consumirEntrada(PersonaAbono pa, int cantidad)
	        throws ModificarException {


	    if(pa == null)
	        return;


	    int restantes =
	        pa.getEntradasRestantes() - cantidad;


	    if(restantes <= 0) {
	        pa.setEntradasRestantes(0);
	        pa.setActivo(false);
	    } else {
	        pa.setEntradasRestantes(restantes);
	    }


	    modificar(pa);
	}

}
