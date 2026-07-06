package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Espectaculo;
import Entidades.Persona;
import Entidades.Ubicacion;
import Entidades.UsuarioAdmin;
import Entidades.UsuarioComun;
import Entidades.UsuarioVendedor;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoUbicacionException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoUbicacionException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoTodosUbicacionException;
import Exceptiones.LeyendoUbicacionException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarUbicacionException;

public class CrudUbicacion extends H2Base implements ICrud<Ubicacion>{
	

	public CrudUbicacion() {
		super();
	}
	
	@Override
	public Ubicacion leer(int id) throws LeyendoException {
		String sql = "SELECT  LUGAR,PRECIO,CANTESPACIO,FOTO,ID_ESTADIO FROM UBICACION WHERE ID=?";
		ResultSet rs=null;
		Ubicacion p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Ubicacion(
						    rs.getString("LUGAR"),
						    rs.getInt("PRECIO"),
						    rs.getInt("CANTESPACIO"),
						    rs.getInt("ID_ESTADIO"),
						    rs.getString("FOTO")
						);
					p.setId(id);
					return p;
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoUbicacionException("ubicacion no encontrada");
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
	public Ubicacion modificar(Ubicacion p) throws ModificarException {
		String sql = "UPDATE UBICACION SET LUGAR=?,PRECIO=?,CANTESPACIO=?,FOTO=? WHERE ID=?";
		ResultSet rs=null;
		try {
			int rows = updateDeleteInsertSql(
	                sql,
	                p.getLugar(),
	                p.getPrecio(),
	                p.getCantEspacio(),
	                p.getFoto(),
	                p.getId()
	                
	        );
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModificarUbicacionException("error al modificar la ubicacion");
		} finally {
			if (rs!=null)
				try {
					rs.close();
					cerrarConexion();
				} catch (SQLException e) {
					throw new ModificarException(e.getMessage());
				}
		}
	
	}

	@Override
	public void borrar(Ubicacion p) throws BorrandoException {
		String sql = "DELETE FROM UBICACION WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoUbicacionException("error al borrar la ubicacion");
		}finally {
			
				try {

					cerrarConexion();
				} catch (SQLException e) {
					throw new BorrandoException(e.getMessage());
				}
		}
		
	}

	@Override
	public void grabar(Ubicacion p) throws GrabandoException {

		String sql = "INSERT INTO UBICACION " +
				     "(LUGAR,PRECIO,CANTESPACIO,ID_ESTADIO,FOTO) " +
				     "VALUES (?,?,?,?,?)";
		
		try {
			int rows = updateDeleteInsertSql(
					sql,
					
					p.getLugar(),
					p.getPrecio(),
					p.getCantEspacio(),
					p.getIdestadio(),
					p.getFoto()
					
			);

			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoUbicacionException("error al grabar la ubicacion");
		}finally {
			
			try {
				
				cerrarConexion();
			} catch (SQLException e) {
				throw new GrabandoException(e.getMessage());
			}
	}
	}
	
	public List<Ubicacion> obtenerPorEstadio(int id) throws LeyendoTodosException {
		String sql = "SELECT * FROM UBICACION WHERE ID_ESTADIO=?";
		ResultSet rs=null;
		Ubicacion p;
		List<Ubicacion> listaUbicacion = new ArrayList<>();
		try {
			rs = selectSql(sql, id);
			while (rs.next()) {
				
					p = new Ubicacion(
							rs.getInt("ID"),
						    rs.getString("LUGAR"),
						    rs.getInt("PRECIO"),
						    rs.getInt("CANTESPACIO"),
						    rs.getInt("ID_ESTADIO"),
						    rs.getString("FOTO")
						);
					listaUbicacion.add(p);
				
			}
				return listaUbicacion;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosUbicacionException("error al leer todas las ubicaciones");
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

	@Override
	public List<Ubicacion> leerTodos() throws LeyendoTodosException {
		// TODO Auto-generated method stub
		return null;
	}

}
