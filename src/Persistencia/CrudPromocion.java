package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Entidades.Estadio;
import Entidades.Promocion;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.ModificarException;

public class CrudPromocion extends H2Base implements ICrud<Promocion>{
	

	public CrudPromocion() {
		super();
	}
	
	@Override
	public Promocion leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE,FECHAINICIO,FECHAFIN,DESCUENTO FROM PROMOCION WHERE ID=?";
		ResultSet rs=null;
		Promocion p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Promocion(
						    rs.getString("NOMBRE"),
						    rs.getTime("FECHAINICIO"),
						    rs.getTime("FECHAFIN"),
						    rs.getFloat("DESCUENTO")
						    
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
	public Promocion modificar(Promocion p) throws ModificarException {
	    String sql = "UPDATE PROMOCION SET NOMBRE=?, FECHAINICIO=?, FECHAFIN=?, DESCUENTO=? WHERE ID=?";

	    try {
	        updateDeleteInsertSql(
	            sql,
	            p.getNombre(),
	            Time.valueOf(p.getTiempoInicio()),
	            Time.valueOf(p.getTiempoFinal()),
	            p.getDescuento(),
	            p.getId()
	        );

	        return p;

	    } catch (SQLException e) {
	        throw new ModificarException(e.getMessage());
	    } finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

	@Override
	public void borrar(Promocion p) throws BorrandoException {
		String sql = "DELETE FROM PROMOCION WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoException(e.getMessage());
		}finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }	
		
	}

	@Override
	public void grabar(Promocion p) throws GrabandoException {

		String sql = "INSERT INTO PROMOCION " +
				     "(NOMBRE,FECHAINICIO,FECHAFIN,DESCUENTO) " +
				     "VALUES (?,?,?,?)";

		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getNombre(),
					Time.valueOf(p.getTiempoInicio()),
	                Time.valueOf(p.getTiempoFinal()),
	                p.getDescuento()
			);
			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

	/*public int obtenerID(String nombre) throws LeyendoException {
		String sql = "SELECT ID FROM ESTADIO WHERE NOMBRE=?";
		ResultSet rs=null;
		int p;
		try {
			rs = selectSql(sql, nombre);
			if (rs.next()) {
				
					p = rs.getInt("ID");
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
		return -1;
	}*/

	public List<Promocion> obtenerTodos() throws LeyendoException{
		String sql = "SELECT ID, NOMBRE, FECHAINICIO, FECHAFIN, DESCUENTO FROM PROMOCION";
		ResultSet rs=null;
		
		try {
			List<Promocion> p  = new ArrayList<>();
			

			rs = selectSql(sql);
			Promocion e;
			while (rs.next()) {
					
					e=new Promocion( rs.getString("NOMBRE"),rs.getTime("FECHAINICIO"),rs.getTime("FECHAFIN"),rs.getFloat("DESCUENTO"));
					e.setId(rs.getInt("ID"));
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
