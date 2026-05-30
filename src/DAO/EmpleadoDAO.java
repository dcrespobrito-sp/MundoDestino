package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.Empleado;

public class EmpleadoDAO extends AbstractaDAO<Empleado> {

    public EmpleadoDAO() {}

    //Método INSERT para insertar un empleado a la base de datos MundoDestino
    @Override
    public void insertar(Empleado obj) {
        String sql = "INSERT INTO EMPLEADO (Nombre_completo, Cargo, Especialidad, Turno, Anios_experiencia) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql)) {//Preparamos la consulta SQL

            ps.setString(1, obj.getNombre_completo());
            ps.setString(2, obj.getCargo());
            ps.setString(3, obj.getEspecialidad());
            ps.setString(4, obj.getTurno());
            ps.setInt(5, obj.getAnios_experiencia());

            ps.executeUpdate();//Ejecutamos la consulta para insertar el empleado

        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());

        }
    }

    //Método SELECT para listar los empleados a la base de datos MundoDestino
    @Override
    public ArrayList<Empleado> listarTodos() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql);//Preparamos la consulta SQL
             ResultSet rs = ps.executeQuery()) {//Ejecutamos la consulta y obtenemos el resultado

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setID_empleado(rs.getInt("ID_empleado"));
                e.setNombre_completo(rs.getString("Nombre_completo"));
                e.setCargo(rs.getString("Cargo"));
                e.setEspecialidad(rs.getString("Especialidad"));
                e.setTurno(rs.getString("Turno"));
                e.setAnios_experiencia(rs.getInt("Anios_experiencia"));

                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());

        }

        return lista;
    }
}
