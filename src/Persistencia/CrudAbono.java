package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Entidades.Abono;
import Entidades.Promocion;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.ModificarException;

public class CrudAbono extends H2Base implements ICrud<Abono>{
	

	public CrudAbono() {
		super();
	}
	
	@Override
	public Abono leer(int id) throws LeyendoException {
		String sql = "SELECT NOMBRE,PRECIO,CANT_ENTRADAS FROM ABONO WHERE ID=?";
		ResultSet rs=null;
		Abono a;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				
					a = new Abono(
						    rs.getString("NOMBRE"),
						    rs.getInt("PRECIO"),
						    rs.getInt("CANT_ENTRADAS")
						    
						);
					a.setId(rs.getInt("ID"));
					return a;
				
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
	public Abono modificar(Abono p) throws ModificarException {
	    String sql = "UPDATE ABONO  SET NOMBRE=?, PRECIO=?, CANT_ENTRADAS=? WHERE ID=?";

	    try {
	        updateDeleteInsertSql(
	            sql,
	            p.getNombre(),
	            p.getPrecio(),
	            p.getCantEntradas(),
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
	public void borrar(Abono p) throws BorrandoException {
		String sql = "DELETE FROM ABONO WHERE ID=?";
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
	public void grabar(Abono p) throws GrabandoException {

		String sql = "INSERT INTO ABONO " +
				     "(NOMBRE,PRECIO,CANT_ENTRADAS) " +
				     "VALUES (?,?,?)";

		try {
			int rows = updateDeleteInsertSql(
					sql,
					p.getNombre(),
					p.getPrecio(),
					p.getCantEntradas()
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



	public List<Abono> obtenerTodos() throws LeyendoException{
		String sql = "SELECT ID, NOMBRE,PRECIO,CANT_ENTRADAS FROM ABONO";
		ResultSet rs=null;
		
		try {
			List<Abono> p  = new ArrayList<>();
			

			rs = selectSql(sql);
			Abono e;
			while (rs.next()) {
					
					e=new Abono( rs.getString("NOMBRE"),rs.getInt("PRECIO"),rs.getInt("CANT_ENTRADAS"));
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
