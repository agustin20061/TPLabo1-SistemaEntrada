package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Entidades.Estadio;
import Entidades.Promocion;
import Entidades.Ubicacion;
import Exceptiones.BorrandoException;
import Exceptiones.BorrandoPromocionException;
import Exceptiones.GrabandoException;
import Exceptiones.GrabandoPromocionException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoPromocionException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoTodosPromocionException;
import Exceptiones.ModificarException;
import Exceptiones.ModificarPromocionException;

public class CrudPromocion extends H2Base implements ICrud<Promocion>{
	

	public CrudPromocion() {
		super();
	}
	
	@Override
	public Promocion leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE,TIEMPO_INICIO,TIEMPO_FINAL,DESCUENTO FROM PROMOCION WHERE ID=?";
		ResultSet rs=null;
		Promocion p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					p = new Promocion(
						    rs.getString("NOMBRE"),
						    rs.getTime("TIEMPO_INICIO"),
						    rs.getTime("TIEMPO_FINAL"),
						    rs.getFloat("DESCUENTO")
						    
						);
					p.setId(id);
					return p;
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoPromocionException("Registro no encontrado");
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
	    String sql = "UPDATE PROMOCION SET NOMBRE=?, TIEMPO_INICIO=?, TIEMPO_FINAL=?, DESCUENTO=? WHERE ID=?";

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
	        throw new ModificarPromocionException("error al modificar la promocion");
	    } finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ModificarException(e.getMessage());
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
			throw new BorrandoPromocionException("error al borrar la promocion");
		}
		 finally {
		        try {
					cerrarConexion();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new BorrandoException(e.getMessage());
				}
		    }
	}
	@Override
	public void grabar(Promocion p) throws GrabandoException {

		String sql = "INSERT INTO PROMOCION " +
				     "(NOMBRE,TIEMPO_INICIO,TIEMPO_FINAL,DESCUENTO) " +
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
			throw new GrabandoPromocionException("error al grabar la promocion");
		}finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new GrabandoException(e.getMessage());
			}
	    }
	}

	@Override
	public List<Promocion> leerTodos() throws LeyendoTodosException{
		String sql = "SELECT ID, NOMBRE, TIEMPO_INICIO, TIEMPO_FINAL, DESCUENTO FROM PROMOCION";
		ResultSet rs=null;
		
		try {
			List<Promocion> p  = new ArrayList<>();
			

			rs = selectSql(sql);
			Promocion e;
			while (rs.next()) {
					
					e=new Promocion( rs.getString("NOMBRE"),rs.getTime("TIEMPO_INICIO"),rs.getTime("TIEMPO_FINAL"),rs.getFloat("DESCUENTO"));
					e.setId(rs.getInt("ID"));
					p.add(e);
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosPromocionException(e.getMessage());
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

	
		public Promocion obtenerPromocion(LocalTime hora) throws LeyendoException {

		    String sql = "SELECT ID, NOMBRE, TIEMPO_INICIO, TIEMPO_FINAL, DESCUENTO "
		               + "FROM PROMOCION "
		               + "WHERE ? BETWEEN TIEMPO_INICIO AND TIEMPO_FINAL";

		    ResultSet rs = null;

		    try {

		        rs = selectSql(sql, java.sql.Time.valueOf(hora));

		        if (rs.next()) {

		            Promocion p = new Promocion(
		                    rs.getString("NOMBRE"),
		                    rs.getTime("TIEMPO_INICIO"),
		                    rs.getTime("TIEMPO_FINAL"),
		                    rs.getFloat("DESCUENTO"));

		            p.setId(rs.getInt("ID"));

		            return p;
		        }

		        // No hay promoción vigente.
		        return null;

		    } catch (SQLException e) {

		        e.printStackTrace();
		        throw new LeyendoPromocionException("Error al buscar la promoción.");

		    } finally {

		        if (rs != null) {
		            try {
		                rs.close();
		                cerrarConexion();
		            } catch (SQLException e) {
		                throw new LeyendoException(e.getMessage());
		            }
		        }
		    }
		
		
	}

}
