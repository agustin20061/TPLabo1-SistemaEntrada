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
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.ModificarException;

public class CrudUbicacion extends H2Base implements ICrud<Ubicacion>{
	

	public CrudUbicacion() {
		super();
	}
	
	@Override
	public Ubicacion leer(int id) throws LeyendoException {
		String sql = "SELECT  LUGAR,PRECIO,CANTESPACIO FROM UBICACION WHERE ID=?";
		ResultSet rs=null;
		Ubicacion p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Ubicacion(
						    rs.getString("LUGAR"),
						    rs.getInt("PRECIO"),
						    rs.getInt("CANTESPACIO"),
						    rs.getInt("ID_ESTADIO")
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
	public Ubicacion modificar(Ubicacion p) throws ModificarException {
		String sql = "UPDATE UBICACION SET LUGAR=?,PRECIO=?,CANTESPACIO=? WHERE ID=?";
		ResultSet rs=null;
		try {
			int rows = updateDeleteInsertSql(
	                sql,
	                p.getLugar(),
	                p.getPrecio(),
	                p.getCantEspacio()
	                
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
	public void borrar(Ubicacion p) throws BorrandoException {
		String sql = "DELETE FROM UBICACION WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getPrecio());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoException(e.getMessage());
		}
		
	}

	@Override
	public void grabar(Ubicacion p) throws GrabandoException {

		String sql = "INSERT INTO UBICACION " +
				     "(ID_ESTADIOLUGAR,PRECIO,CANTESPACIO) " +
				     "VALUES (?,?,?,?)";

		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getIdestadio(),
					p.getLugar(),
					p.getPrecio(),
					p.getCantEspacio()
					
			);

			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}
	}
	
	public List<Ubicacion> obtenerPorEstadio(int id) throws LeyendoException {
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
						    rs.getInt("ID_ESTADIO")
						);
					listaUbicacion.add(p);
				
			}
				return listaUbicacion;
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
