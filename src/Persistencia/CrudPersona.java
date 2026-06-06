package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import Entidades.Persona;
import Entidades.UsuarioAdmin;
import Entidades.UsuarioComun;
import Entidades.UsuarioVendedor;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.ModificarException;

public class CrudPersona extends H2Base implements ICrud<Persona>{
	

	public CrudPersona() {
		super();
	}
	
	public Persona iniciarSesion(String mail,String contrasenia) throws LeyendoException {
		String sql = "SELECT DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA,ROL FROM PERSONAS WHERE MAIL=? AND CONTRASENIA=?";
		ResultSet rs=null;
		Persona p;
		try {
			rs = selectSql(sql, mail,contrasenia);
			if (rs.next()) {
				if(rs.getString(6).equals("ADMIN")) {
					p = new UsuarioAdmin(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					return p;
				}else if(rs.getString(6).equals("COMUN")) {
					p = new UsuarioComun(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					//aca iria el usuario normal
					return p;
				} else if(rs.getString(6).equals("VENDEDOR")){
					p = new UsuarioVendedor(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					return p;
				}
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
	public Persona leer(int dni) throws LeyendoException {
		String sql = "SELECT  DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA,ROL FROM PERSONAS WHERE DNI=?";
		ResultSet rs=null;
		Persona p;
		try {
			rs = selectSql(sql, dni);
			if (rs.next()) {
				if(rs.getString(6).equals("ADMIN")) {
					p = new UsuarioAdmin(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					return p;
				}else if(rs.getString(6).equals("COMUN")) {
					p = new UsuarioComun(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					//aca iria el usuario normal
					return p;
				} else if(rs.getString(6).equals("VENDEDOR")){
					p = new UsuarioVendedor(
					        rs.getString(2),
					        rs.getString(3),
					        rs.getInt(1),
					        rs.getString(4),
					        rs.getString(5)
					);
					return p;
				}
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
	public Persona modificar(Persona p) throws ModificarException {
		String sql = "UPDATE PERSONAS SET NOMBRE=?,APELLIDO=?,MAIL=?,CONTRASENIA=? WHERE DNI=?";
		ResultSet rs=null;
		try {
			int rows = updateDeleteInsertSql(
	                sql,
	                p.getNombre(),
	                p.getApellido(),
	                p.getMail(),
	                p.getContrasenia(),
	                p.getDni()
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
	public void borrar(Persona p) throws BorrandoException {
		String sql = "DELETE FROM PERSONAS WHERE DNI=?";
		try {
			int rows = updateDeleteInsertSql(sql, p.getDni());
			System.out.println("Registros eliminados: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BorrandoException(e.getMessage());
		}
		
	}

	@Override
	public void grabar(Persona p) throws GrabandoException {

		String sql = "INSERT INTO PERSONAS " +
				     "(DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA,ROL) " +
				     "VALUES (?,?,?,?,?,?)";

		try {

			String rol = "COMUN";

			/*if (p instanceof UsuarioAdmin) {
				rol = "ADMIN";

			} else if (p instanceof UsuarioComun) {
				rol = "COMUN";

			} else if (p instanceof UsuarioVendedor) {
				rol = "VENDEDOR";
			}*/

			int rows = updateDeleteInsertSql(
					sql,
					p.getDni(),
					p.getNombre(),
					p.getApellido(),
					p.getMail(),
					p.getContrasenia(),
					rol
			);

			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}
	}
		
	}


