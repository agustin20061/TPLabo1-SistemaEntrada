package Servicio;

import java.util.List;

import Entidades.Abono;
import Entidades.Promocion;
import Exceptiones.AbonoNoEncontradaException;
import Exceptiones.BorrandoAbonoException;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPersonaException;
import Exceptiones.GrabandoAbonoException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPersonaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosAbonoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarAbonoException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPersonaException;
import Exceptiones.PersonaNoEncontradaException;
import Persistencia.CrudAbono;
import Persistencia.CrudPromocion;
import Persistencia.ICrud;

public class AbonoServicio implements IABMO<Abono>{
	
	private ICrud<Abono> abonoCrud = new CrudAbono();
	
	
	
	
	@Override
	public Abono leer(int id) throws LeyendoException {
		try {
			return abonoCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new AbonoNoEncontradaException("Abono No Encontrada - ID "+id);
		}
	}
	
	@Override
	public Abono modificar(Abono p) throws ModificarException {
		try {
			
			return abonoCrud.modificar(p);
		} catch (ModificarException e) {
			e.printStackTrace();
			throw new ModificarAbonoException("abono No Modificada ");
		}
	}
	@Override
	public void borrar(Abono p) throws BorrandoException {
		try {
			abonoCrud.borrar(p);
		} catch (BorrandoException e) {
			e.printStackTrace();
			throw new BorrandoAbonoException("abono No Borrada");
		}
		
	}
	@Override
	public void grabar(Abono p) throws GrabandoException {
		try {
			
			abonoCrud.grabar(p);
		} catch (GrabandoException e) {
			e.printStackTrace();
			throw new GrabandoAbonoException("Abono No Creada");
		}
		
	}
	@Override
	public List<Abono> leerTodos()throws LeyendoTodosException {
		try {
			return abonoCrud.leerTodos();
		} catch (LeyendoTodosException e) {
			e.printStackTrace();
			throw new LeyendoTodosAbonoException("leer todas los abonos no se pudo ");
		}
	}


}
