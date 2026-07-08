package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.PersonaAbono;
import Entidades.UsuarioComun;
import Exceptiones.BorrandoException;
import Exceptiones.GrabandoAbonoException;
import Exceptiones.GrabandoException;
import Exceptiones.LeyendoAbonoException;
import Exceptiones.LeyendoException;
import Exceptiones.LeyendoTodosAbonoException;
import Exceptiones.LeyendoTodosException;
import Exceptiones.ModificarAbonoException;
import Exceptiones.ModificarException;

public class CrudPersonaAbono extends H2Base implements ICrud<PersonaAbono>{
	private CrudPersona crudPersona = new CrudPersona();
	private CrudAbono crudAbono = new CrudAbono();
	public CrudPersonaAbono() {
		super();
	}
	@Override
	public PersonaAbono leer(int id) throws LeyendoException {
	    String sql = """
				        SELECT *
			FROM PERSONA_ABONO
			WHERE ID_PERSONA = ?
			AND ACTIVO = TRUE
	    """;
	    ResultSet rs = null;
	    PersonaAbono pa;
	    try {
	        rs = selectSql(sql, id);
	        if (rs.next()) {
	            pa=new PersonaAbono((UsuarioComun)crudPersona.leer(rs.getInt("ID_PERSONA")),crudAbono.leer(rs.getInt("ID_ABONO")),rs.getInt("ENTRADAS_RESTANTES"),rs.getBoolean("ACTIVO"));
	            pa.setId(rs.getInt("ID"));
	            return pa;
	        }

	        throw new LeyendoAbonoException("PersonaAbono no encontrado");

	    } catch (SQLException e) {
	        throw new LeyendoException(e.getMessage());
	    } finally {
	        try {
	            if (rs != null)
	                rs.close();
	            cerrarConexion();
	        } catch (SQLException e) {
	            throw new LeyendoException(e.getMessage());
	        }
	    }
	}

	@Override
	public PersonaAbono modificar(PersonaAbono p) throws ModificarException {
	    String sql = """
	        UPDATE PERSONA_ABONO
	        SET ID_PERSONA=?,
	            ID_ABONO=?,
	            ENTRADAS_RESTANTES=?,
	            ACTIVO=?
	        WHERE ID=?
	    """;
	    try {

	        updateDeleteInsertSql(
	                sql,
	                p.getPersona().getId(),
	                p.getAbono().getId(),
	                p.getEntradasRestantes(),
	                p.isActivo(),
	                p.getId());
	        return p;
	    } catch (SQLException e) {
	        throw new ModificarAbonoException("PersonaAbono no modificada");
	    } finally {

	        try {
	            cerrarConexion();
	        } catch (SQLException e) {
	            throw new ModificarException(e.getMessage());
	        }
	    }
	}

	@Override
	public void borrar(PersonaAbono p) throws BorrandoException {

	}
	
	@Override
	public void grabar(PersonaAbono p) throws GrabandoException {

	    String sql = """
	        INSERT INTO PERSONA_ABONO
	        (ID_PERSONA, ID_ABONO, ENTRADAS_RESTANTES, ACTIVO)
	        VALUES (?,?,?,?)
	    """;

	    try {

	        updateDeleteInsertSql(
	                sql,
	                p.getPersona().getId(),
	                p.getAbono().getId(),
	                p.getEntradasRestantes(),
	                p.isActivo());

	    } catch (SQLException e) {

	        throw new GrabandoAbonoException("Error al grabar PersonaAbono");

	    } finally {

	        try {
	            cerrarConexion();
	        } catch (SQLException e) {
	            throw new GrabandoException(e.getMessage());
	        }
	    }
	}
	


	@Override
	public List<PersonaAbono> leerTodos() throws LeyendoTodosException {
	    String sql = """
	        SELECT ID, ID_PERSONA, ID_ABONO,
	               ENTRADAS_RESTANTES, ACTIVO
	        FROM PERSONA_ABONO
	    """;
	    ResultSet rs = null;
	    try {
	        List<PersonaAbono> lista = new ArrayList<>();
	        PersonaAbono pa;
	        rs = selectSql(sql);
	        while (rs.next()) {
	        	pa=new PersonaAbono((UsuarioComun)crudPersona.leer(rs.getInt("ID_PERSONA")),crudAbono.leer(rs.getInt("ID_ABONO")),rs.getInt("ENTRADAS_RESTANTES"),rs.getBoolean("ACTIVO"));
	            pa.setId(rs.getInt("ID"));
	            lista.add(pa);
	        }
	        return lista;

	    } catch (Exception e) {

	        throw new LeyendoTodosAbonoException("Error al leer PersonaAbono");

	    } finally {

	        try {
	            if (rs != null)
	                rs.close();
	            cerrarConexion();
	        } catch (SQLException e) {
	            throw new LeyendoTodosException(e.getMessage());
	        }
	    }
	}
}
