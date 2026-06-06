package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entidades.Espectaculo;
import Entidades.Estadio;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.ModificarException;

public class CrudEspectaculo extends H2Base implements ICrud<Espectaculo>{
	

	public CrudEspectaculo() {
		super();
	}
	
	@Override
	public Espectaculo leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE,DESCRIPCION,ID_ESTADIO"
				+ "FROM ESTADIO"
				+ "WHERE ID=?";
		ResultSet rs=null;
		Espectaculo p;
		Estadio e;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Espectaculo(
						    rs.getString("NOMBRE"),
						    e=new Estadio(rs.getString("ID_ESTADIO),
						    rs.get
						    
						);
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
	public Espectaculo modificar(Espectaculo p) throws ModificarException {
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
	public void borrar(Espectaculo p) throws BorrandoException {
		String sql = "DELETE FROM UBICACION WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoException(e.getMessage());
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
					p.getFecha(),
					p.getEstadio().getId()
			);
			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}
	}
	public List<Espectaculo> obtenerTodos() throws LeyendoException{
		String sql = "SELECT ID, NOMBRE FROM ESTADIO";
		ResultSet rs=null;
		
		try {
			List<Espectaculo> p  = new ArrayList<>();
			rs = selectSql(sql);
			Espectaculo e;
			Estadio estadio;
			CrudEstadio crudEstadio;
			CrudUbicacion crudUbicacion;
			List<Ubicacion>listaUbicacion=new ArrayList<>();
			while (rs.next()) {
				
				List<Ubicacion> ubicaciones = crudUbicacion.obtenerPorEstadio(rs.getInt("ID_ESTADIO"));
				estadio=crudEstadio.leer(rs.getInt("ID_ESTADIO"));
				estadio.setListaUbicacion(ubicaciones);
				e=new Espectaculo( rs.getInt("ID"),rs.getString("NOMBRE"),estadio,rs.getString("DESCRIPCION"),(LocalDate) rs.getDate("FECHA"));
				p.add(e);
			}
			return p;
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
}
