package Persistencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Ubicacion;
import Entidades.Venta;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoVentaException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoTodosVentaException;
import Exceptiones.LeyendoVentaException;
import Exceptiones.ModificarException;

public class CrudVenta extends H2Base implements ICrud<Venta>{

	
	public CrudVenta() {
		super();
	}

	@Override
	public Venta leer(int id) throws LeyendoException {
		String sql = "SELECT  ID_ESPECTACULO,ID_ESTADIO,ID_UBICACION,CANTIDAD,FECHACOMPRA,PRECIO FROM VENTA WHERE ID=?";
		ResultSet rs=null;
		Venta p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				CrudEstadio crudEstadio = new CrudEstadio();
				CrudEspectaculo crudEspectaculo=new CrudEspectaculo();
				CrudUbicacion crudUbicacion=new CrudUbicacion();
				Estadio estadio = crudEstadio.leer(rs.getInt("ID_ESTADIO"));
				Espectaculo es= crudEspectaculo.leer(rs.getInt("ID_ESPECTACULO"));
				Ubicacion ubi= crudUbicacion.leer(rs.getInt("ID_UBICACION"));
					p = new Venta(
						    es,
						    estadio,
						    ubi,
						    rs.getInt("CANTIDAD"),
						    rs.getTimestamp("FECHACOMPRA").toLocalDateTime(),
						    rs.getInt("PRECIO")
						);
					p.setId(id);
					return p;
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoVentaException("ubicacion no encontrada");
		} finally {
			if (rs!=null)
				try {
					rs.close();
					cerrarConexion();
				} catch (SQLException e) {
					throw new LeyendoException(e.getMessage());
				}
		}
		return null;
		
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
	public void grabar(Venta p) throws GrabandoException {
		String sql = "INSERT INTO VENTA"
				+ "(ID_ESPECTACULO,ID_ESTADIO,ID_UBICACION,CANTIDAD,FECHACOMPRA,PRECIO)"
				+ "VALUES (?,?,?,?,?,?)";
		
		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getEspectaculo().getId(),
					p.getEstadio().getId(),
					p.getUbicacion().getId(),
					p.getCantidad(),
					p.getFechaCompra(),
					p.getTotal()
					
			);
			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoVentaException("error al grabar la venta");
		}finally {
				try {
					cerrarConexion();
				} catch (SQLException e1) {
					throw new GrabandoException(e1.getMessage());
				}
		}
		
	}

	@Override
	public List<Venta> leerTodos() throws LeyendoTodosException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Venta> leerFechaEspectaculo(Date fechaInicial, Date fechaHasta) throws LeyendoTodosException{
		String sql = "SELECT ID_ESPECTACULO,SUM(CANTIDAD) AS TOTAL_ENTRADAS,SUM(PRECIO) AS TOTAL_VENDIDO FROM VENTA WHERE FECHACOMPRA BETWEEN ? AND ? GROUP BY ID_ESPECTACULO";
		ResultSet rs=null;
		Venta p;
		List<Venta> lista=new ArrayList<>();
		CrudEspectaculo crudEspectaculo=new CrudEspectaculo();
		try {
			rs = selectSql(sql,fechaInicial,fechaHasta);
			while (rs.next()) {
				
				Espectaculo es = null;
				try {
					es = crudEspectaculo.leer(rs.getInt("ID_ESPECTACULO"));
				} catch (LeyendoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					p = new Venta(
						    es,
						    null,
						    null,
						    rs.getInt("TOTAL_ENTRADAS"),
						    null,
						    rs.getInt("TOTAL_VENDIDO")
						);
					lista.add(p);
					
				
				
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosVentaException("Ventas no encontradas");
		} finally {
			if (rs!=null)
				try {
					rs.close();
					cerrarConexion();
				} catch (SQLException e) {
					throw new LeyendoTodosException(e.getMessage());
				}
		}
	}

}
