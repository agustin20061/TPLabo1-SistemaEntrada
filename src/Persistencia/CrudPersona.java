package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Persona;
import Entidades.Promocion;
import Entidades.UsuarioAdmin;
import Entidades.UsuarioComun;
import Entidades.UsuarioVendedor;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoPersonaException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.LeyendoTodosPersonaException;
import Exceptiones.ModificarException;

public class CrudPersona extends H2Base implements ICrud<Persona>{
	

	public CrudPersona() {
		super();
	}
	
	public Persona iniciarSesion(String mail,String contrasenia) throws LeyendoException {
		String sql = "SELECT DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA,ROL,ID FROM PERSONAS WHERE MAIL=? AND CONTRASENIA=?";
		ResultSet rs=null;
		Persona p;
		try {
			rs = selectSql(sql, mail,contrasenia);
			if (rs.next()) {
				p = PersonaFactory.crearPersona(
				        rs.getString("ROL"),
				        rs.getString("NOMBRE"),
				        rs.getString("APELLIDO"),
				        rs.getInt("DNI"),
				        rs.getString("MAIL"),
				        rs.getString("CONTRASENIA")
				);
				p.setId(rs.getInt("ID"));
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
	public Persona leer(int id) throws LeyendoException {
		String sql = "SELECT  DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA,ROL FROM PERSONAS WHERE ID=?";
		ResultSet rs=null;
		Persona p;
		try {
			rs = selectSql(sql, id);
			if (rs.next()) {
				p = PersonaFactory.crearPersona(
				        rs.getString("ROL"),
				        rs.getString("NOMBRE"),
				        rs.getString("APELLIDO"),
				        rs.getInt("DNI"),
				        rs.getString("MAIL"),
				        rs.getString("CONTRASENIA")
				);
				p.setId(rs.getInt("ID"));
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
	public Persona modificar(Persona p) throws ModificarException {
		String sql = "UPDATE PERSONAS SET NOMBRE=?,APELLIDO=?,DNI=?,MAIL=?,CONTRASENIA=? WHERE ID=?";
		ResultSet rs=null;
		try {
			int rows = updateDeleteInsertSql(
	                sql,
	                p.getNombre(),
	                p.getApellido(),
	                p.getDni(),
	                p.getMail(),
	                p.getContrasenia(),
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

			int rows = updateDeleteInsertSql(
					sql,
					p.getDni(),
					p.getNombre(),
					p.getApellido(),
					p.getMail(),
					p.getContrasenia(),
					p.getRolPersistencia()
			);

			System.out.println("Registros insertados: " + rows);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new GrabandoException(e.getMessage());
		}
	}

	@Override
	public List<Persona> leerTodos() throws LeyendoTodosException {
		String sql = "SELECT  ID,DNI,NOMBRE,APELLIDO,MAIL,CONTRASENIA FROM PERSONAS WHERE ROL=?";
		ResultSet rs=null;
		Persona p;
		List<Persona> pe  = new ArrayList<>();
		String rol="COMUN";
		try {
			rs = selectSql(sql, rol);
			while (rs.next()) {

					p = new UsuarioComun(
					        rs.getString("NOMBRE"),
					        rs.getString("APELLIDO"),
					        rs.getInt("DNI"),
					        rs.getString("MAIL"),
					        rs.getString("CONTRASENIA")
					);
					p.setId(rs.getInt("ID"));
					pe.add(p);
				}
				return pe;
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeyendoTodosPersonaException("Registro no encontrado");
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


