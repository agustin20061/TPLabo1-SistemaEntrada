package Servicio;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import Entidades.Promocion;
import Entidades.Venta;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoEspectaculoException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoVentaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoTodosPromocionException;
import Exceptiones.LeyendoTodosVentaException;
import Exceptiones.ModificarException;
import Exceptiones.VentaNoEncontradaException;
import Persistencia.CrudPersona;
import Persistencia.CrudPromocion;
import Persistencia.CrudVenta;
import Persistencia.ICrud;

public class VentaServicio implements IABMO<Venta>{
	
	private ICrud<Venta> ventaCrud = new CrudVenta();
	private CrudPromocion crudPromocion = new CrudPromocion();
	private CrudVenta crudVenta = new CrudVenta();
	@Override
	public Venta leer(int id) throws LeyendoException {
		try {
			return ventaCrud.leer(id);
		} catch (LeyendoException e) {
			e.printStackTrace();
			throw new VentaNoEncontradaException("Venta No Encontrada - ID "+id);
		}
	}

	@Override
	public Venta modificar(Venta p) throws ModificarException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Venta p) throws BorrandoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grabar(Venta venta) throws GrabandoException {
	    try {
	        int vendidas = crudVenta.obtenerEntradasVendidas(
	                venta.getEspectaculo().getId(),
	                venta.getUbicacion().getId());
	        int capacidad = venta.getUbicacion().getCantEspacio();
	        int disponibles = capacidad - vendidas;
	        if(venta.getCantidad() > disponibles) {
	            throw new GrabandoVentaException(
	                    "No hay capacidad suficiente.\n" +
	                    "Disponibles: " + disponibles);
	        }
	        ventaCrud.grabar(venta);
	    } catch (LeyendoException e) {
	        throw new GrabandoVentaException("No se pudo verificar la capacidad.");
	    } catch (GrabandoException e) {
	        throw new GrabandoVentaException("Venta no creada.");
	    }
	}

	@Override
	public List<Venta> leerTodos() throws LeyendoTodosException {
		// TODO Auto-generated method stub
		return null;
	}
	public  List<Venta> leerFechaEspectaculo(Date fechaInicial, Date fechaHasta) throws LeyendoTodosException{
		try {
			return crudVenta.leerFechaEspectaculo(fechaInicial,fechaHasta);
		} catch (LeyendoTodosException e) {
			e.printStackTrace();
			throw new LeyendoTodosVentaException("Ventas No Encontradas");
		}
	}
	public int calcularTotal(int precioBase) {
    	LocalTime horaActual = LocalTime.now();
    	Promocion promo;
		try {
			promo = crudPromocion.obtenerPromocion(horaActual);
			if(promo != null){
				precioBase=(int) (precioBase - precioBase * promo.getDescuento() / 100);
	    	}
		} catch (LeyendoException e) {
			e.printStackTrace();
		}
		return precioBase;
    	
	}
}
