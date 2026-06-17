package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoEstadioException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoTodosEstadioException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoUbicacionException;
import Exceptiones.ModificarException;

public class CrudEstadio extends H2Base implements ICrud<Estadio>{
	

	public CrudEstadio() {
		super();
	}
	
	@Override
	public Estadio leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE FROM ESTADIO WHERE ID=?";
		ResultSet rs=null;
		Estadio p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Estadio(
						    rs.getString("NOMBRE")
						    
						);
					p.setId(id);
					return p;
				
			}
				throw new LeyendoPersonaException("Registro no encontrado");
		} catch (SQLException e) {
			e.printStackTrace();
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
	public Estadio modificar(Estadio p) throws ModificarException {
		String sql = "UPDATE ESTADIO SET NOMBRE=? WHERE ID=?";
		ResultSet rs=null;
		try {
			int rows = updateDeleteInsertSql(
	                sql,
	                p.getNombre(),
	                p.getId()
	                
	            
	        );
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ModificarException(e.getMessage());
				}
		}
		return null;
	}

	@Override
	public void borrar(Estadio p) throws BorrandoException {
		String sql = "DELETE FROM ESTADIO WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoException(e.getMessage());
		}
		
	}

	@Override
	public void grabar(Estadio p) throws GrabandoException {

		String sql = "INSERT INTO ESTADIO " +
				     "(NOMBRE) " +
				     "VALUES (?)";

		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getNombre()	
			);
			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}
	}

	public int obtenerID(String nombre) throws LeyendoException {
		String sql = "SELECT ID FROM ESTADIO WHERE NOMBRE=?";
		ResultSet rs=null;
		int p;
		try {
			rs = selectSql(sql, nombre);
			if (rs.next()) {
				
					p = rs.getInt("ID");
					return p;
				
			}
				throw new LeyendoEstadioException("id no encontrado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null)
				try {
					rs.close();
					cerrarConexion();
				} catch (SQLException e) {
					throw new LeyendoException(e.getMessage());
				}
		}
		return -1;
	}
	@Override
	public List<Estadio> leerTodos() throws LeyendoTodosException{
		String sql = "SELECT ID, NOMBRE FROM ESTADIO";
		ResultSet rs=null;
		
		try {
			List<Estadio> p  = new ArrayList<>();
			CrudUbicacion crudUbicacion = new CrudUbicacion();
			List<Ubicacion>listaUbicacion=new ArrayList<>();

			rs = selectSql(sql);
			Estadio e;
			while (rs.next()) {
					try {
						listaUbicacion=crudUbicacion.obtenerPorEstadio(rs.getInt("ID"));
					} catch (LeyendoTodosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e=new Estadio( rs.getInt("ID"),rs.getString("NOMBRE"),listaUbicacion);
					p.add(e);
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosEstadioException("Error al leer todos los usuarios");
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
