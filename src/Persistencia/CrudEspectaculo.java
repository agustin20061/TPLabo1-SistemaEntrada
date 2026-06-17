package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoEspectaculoException;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoEspectaculoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoEspectaculoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoTodosEspectaculoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarEspectaculoException;
import Exceptiones.ModificarException;

public class CrudEspectaculo extends H2Base implements ICrud<Espectaculo>{
	

	public CrudEspectaculo() {
		super();
	}
	
	@Override
	public Espectaculo leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE, DESCRIPCION, ID_ESTADIO, FECHA "
				+ "FROM ESPECTACULO "
				+ "WHERE ID=?";
		ResultSet rs=null;
		Espectaculo p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				CrudEstadio crudEstadio = new CrudEstadio();

				Estadio estadio = crudEstadio.leer(rs.getInt("ID_ESTADIO"));

			
					p = new Espectaculo(
						    rs.getString("NOMBRE"),
						    estadio,
						    rs.getString("DESCRIPCION"),
						    rs.getDate("FECHA").toLocalDate()
						);
					p.setId(id);
					return p;
				
			}
			throw new LeyendoEspectaculoException("error al leer el espectaculo");
		} catch (SQLException e1) {
			e1.printStackTrace();
			
		} finally {
			if (rs!=null)
				try {
					rs.close();
					cerrarConexion();
				} catch (SQLException e1) {
					throw new LeyendoException(e1.getMessage());
				}
		}
		return null;
		
	}

	@Override
	public Espectaculo modificar(Espectaculo p) throws ModificarException {
		String sql = "UPDATE ESPECTACULO "
		           + "SET NOMBRE=?, DESCRIPCION=?, FECHA=?, ID_ESTADIO=? "
		           + "WHERE ID=?";
	
		try {
			updateDeleteInsertSql(
	                sql,
	                p.getNombre(),
	                p.getDescripcion(),
	                java.sql.Date.valueOf(p.getFecha()),
	                p.getEstadio().getId(),
	                p.getId()
	                
	            
	        );
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModificarEspectaculoException("error al modificar al espectaculo");
		} finally {
	
				try {
		
					cerrarConexion();
				} catch (SQLException e) {
					throw new ModificarException(e.getMessage());
				}
		}
	}

	@Override
	public void borrar(Espectaculo p) throws BorrandoException {
		String sql = "DELETE FROM ESPECTACULO WHERE ID=?";

		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoEspectaculoException("error al borrar el espectaculo");
		}finally {
				try {
				
					cerrarConexion();
				} catch (SQLException e1) {
					throw new BorrandoException(e1.getMessage());
				}
		}
		
	}

	@Override
	public void grabar(Espectaculo p) throws GrabandoException {

		String sql = "INSERT INTO ESPECTACULO"
				+ "(NOMBRE,DESCRIPCION,FECHA,ID_ESTADIO)"
				+ "VALUES (?,?,?,?)";
		
		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getNombre(),
					p.getDescripcion(),
					java.sql.Date.valueOf(p.getFecha()),
					p.getEstadio().getId()
			);
			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoEspectaculoException(e.getMessage());
		}finally {
				try {
					cerrarConexion();
				} catch (SQLException e1) {
					throw new GrabandoException(e1.getMessage());
				}
		}
	}
	@Override
	public List<Espectaculo> leerTodos() throws LeyendoTodosException{
		String sql = "SELECT ID,NOMBRE,DESCRIPCION,FECHA,ID_ESTADIO FROM ESPECTACULO";
		ResultSet rs=null;
		
		try {
			List<Espectaculo> p  = new ArrayList<>();
			rs = selectSql(sql);
			Espectaculo e;
			Estadio estadio = null;
			CrudEstadio crudEstadio = new CrudEstadio();
			CrudUbicacion crudUbicacion = new CrudUbicacion();
			while (rs.next()) {
				
				List<Ubicacion> ubicaciones=new ArrayList<>();
				try {
					ubicaciones = crudUbicacion.obtenerPorEstadio(rs.getInt("ID_ESTADIO"));
				} catch (LeyendoTodosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				try {
					estadio=crudEstadio.leer(rs.getInt("ID_ESTADIO"));
				} catch (LeyendoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				estadio.setListaUbicacion(ubicaciones);
				e=new Espectaculo( rs.getString("NOMBRE"),estadio,rs.getString("DESCRIPCION"),rs.getDate("FECHA").toLocalDate());
				e.setId(rs.getInt("ID"));
				p.add(e);
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosEspectaculoException("error al leer todos los espectaculos");
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
