package Conexion;

import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/MundoDestino";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {
        Connection conex = null;
        try {
            conex = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos MundoDestino");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conex;
    }
}

