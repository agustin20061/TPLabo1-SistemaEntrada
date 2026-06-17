package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Entidades.Abono;
import Entidades.Promocion;
import Exceptiones.BorrandoAbonoException;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoAbonoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoAbonoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoTodosAbonoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarAbonoException;
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
					a.setId(id);
					return a;
				
			}
				throw new LeyendoAbonoException("Registro no encontrado");
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
	    	e.printStackTrace();
	    	throw new ModificarAbonoException("Abono no modificado");
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
	public void borrar(Abono p) throws BorrandoException {
		String sql = "DELETE FROM ABONO WHERE ID=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getId());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoAbonoException("Abono no borrado");
		}finally {
	        try {
				cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BorrandoException(e.getMessage());
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
			throw new GrabandoAbonoException("Abono no creado");
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
	public List<Abono> leerTodos() throws LeyendoTodosException{
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
			throw new LeyendoTodosAbonoException("Error en leer todos los abonos");
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
