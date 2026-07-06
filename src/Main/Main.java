package Main;

import Persistencia.InicializadorDB;
import Vista.IniciarSesionView;

public class Main {
	public static void main(String[] args) {
		InicializadorDB.inicializar();
		IniciarSesionView view = new IniciarSesionView();	
		System.out.println(System.getProperty("user.dir"));	
	}
}
