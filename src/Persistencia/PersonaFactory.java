package Persistencia;

import Entidades.Persona;
import Entidades.UsuarioAdmin;
import Entidades.UsuarioComun;
import Entidades.UsuarioVendedor;

public class PersonaFactory {
	public static Persona crearPersona(
            String rol,
            String nombre,
            String apellido,
            int dni,
            String mail,
            String contrasenia) {

        return switch (rol) {
            case "COMUN" ->
                new UsuarioComun(nombre, apellido, dni, mail, contrasenia);

            case "VENDEDOR" ->
                new UsuarioVendedor(nombre, apellido, dni, mail, contrasenia);

            case "ADMIN" ->
                new UsuarioAdmin(nombre, apellido, dni, mail, contrasenia);

            default ->
                throw new IllegalArgumentException("Rol inválido");
        };
    }
}
