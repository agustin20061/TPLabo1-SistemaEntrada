package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InicializadorDB {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void inicializar() {

        try {
            Class.forName("org.h2.Driver");

            try (Connection conn =
                    DriverManager.getConnection(URL, USER, PASS);
                 Statement st = conn.createStatement()) {

                st.execute("""
                    CREATE TABLE IF NOT EXISTS PERSONA (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NOMBRE VARCHAR(100) NOT NULL,
                        APELLIDO VARCHAR(100) NOT NULL,
                        DNI INT NOT NULL UNIQUE,
                        MAIL VARCHAR(150) NOT NULL UNIQUE,
                        CONTRASENIA VARCHAR(255) NOT NULL,
                        ROL VARCHAR(20) NOT NULL
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS PROMOCION (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NOMBRE VARCHAR(100) NOT NULL,
                        TIEMPO_INICIO TIME NOT NULL,
                        TIEMPO_FINAL TIME NOT NULL,
                        DESCUENTO FLOAT NOT NULL
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS ABONO (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NOMBRE VARCHAR(100) NOT NULL,
                        PRECIO INT NOT NULL,
                        CANT_ENTRADAS INT NOT NULL
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS ESTADIO (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NOMBRE VARCHAR(100) NOT NULL
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS UBICACION (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        LUGAR VARCHAR(100) NOT NULL,
                        PRECIO INT NOT NULL,
                        CANTESPACIO INT NOT NULL,
                        ID_ESTADIO INT NOT NULL,
                        CONSTRAINT FK_UBICACION_ESTADIO
                        FOREIGN KEY (ID_ESTADIO)
                        REFERENCES ESTADIO(ID)
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS ESPECTACULO (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NOMBRE VARCHAR(100) NOT NULL,
                        ID_ESTADIO INT NOT NULL,
                        DESCRIPCION TEXT,
                        FECHA DATE NOT NULL,
                        CONSTRAINT FK_ESPECTACULO_ESTADIO
                        FOREIGN KEY (ID_ESTADIO)
                        REFERENCES ESTADIO(ID)
                    )
                """);

                st.execute("""
                    CREATE TABLE IF NOT EXISTS VENTA (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        ID_ESPECTACULO INT NOT NULL,
                        ID_UBICACION INT NOT NULL,
                        CANTIDAD INT NOT NULL,
                        FECHA_COMPRA TIMESTAMP NOT NULL,
                        TOTAL INT NOT NULL
                    )
                """);

                insertarUsuariosBase(conn);

                System.out.println("Base inicializada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertarUsuariosBase(Connection conn) throws Exception {

        PreparedStatement check =
                conn.prepareStatement(
                        "SELECT COUNT(*) FROM PERSONA WHERE ROL=?");

        check.setString(1, "ADMIN");

        var rs = check.executeQuery();

        rs.next();

        if (rs.getInt(1) == 0) {

            PreparedStatement insert =
                    conn.prepareStatement("""
                        INSERT INTO PERSONA
                        (NOMBRE,APELLIDO,DNI,MAIL,CONTRASENIA,ROL)
                        VALUES (?,?,?,?,?,?)
                    """);

            insert.setString(1, "Admin");
            insert.setString(2, "Sistema");
            insert.setInt(3, 11111111);
            insert.setString(4, "admin");
            insert.setString(5, "admin");
            insert.setString(6, "ADMIN");

            insert.executeUpdate();

            System.out.println("Usuario admin creado.");
        }

        check =
                conn.prepareStatement(
                        "SELECT COUNT(*) FROM PERSONA WHERE ROL=?");

        check.setString(1, "VENDEDOR");

        rs = check.executeQuery();

        rs.next();

        if (rs.getInt(1) == 0) {

            PreparedStatement insert =
                    conn.prepareStatement("""
                        INSERT INTO PERSONA
                        (NOMBRE,APELLIDO,DNI,MAIL,CONTRASENIA,ROL)
                        VALUES (?,?,?,?,?,?)
                    """);

            insert.setString(1, "Vendedor");
            insert.setString(2, "Sistema");
            insert.setInt(3, 22222222);
            insert.setString(4, "vendedor");
            insert.setString(5, "vendedor");
            insert.setString(6, "VENDEDOR");

            insert.executeUpdate();

            System.out.println("Usuario vendedor creado.");
        }
    }
}