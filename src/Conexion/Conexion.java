package Conexion;

import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/MundoDestino";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {//Método que nos permite obtener una conexión a la base de datos
        Connection conex = null;
        try {
            conex = DriverManager.getConnection(URL, USER, PASSWORD);//Establece la conexion con el driverManager, utilizando la URL, el usuario y la contraseña definidos anteriormente

        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conex;//Devuelve la conexión establecida o null si hubo un error
    }
}

