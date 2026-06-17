package Persistencia;

import java.util.List;

import Entidades.Abono;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarException;

public interface ICrud<T> {
	public T leer(int id) throws LeyendoException;
	public T modificar(T p)throws ModificarException;
	public void borrar(T p)throws BorrandoException;
	public void grabar(T p) throws GrabandoException;
	public List<T> leerTodos() throws LeyendoTodosException;
}

