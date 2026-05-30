package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.Reserva;

public class ReservaDAO extends AbstractaDAO<Reserva> {

    public ReservaDAO() {}

    // INSERTAR RESERVA
    @Override
    public void insertar(Reserva obj) {
        String sql = "INSERT INTO RESERVA (Fecha_reserva, Fecha_salida, Fecha_regreso, Numero_viajeros, Importe_total, Estado_reserva, ID_cliente, ID_empleado, ID_destino) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql)) {//Preparamos la consulta SQL

        	ps.setDate(1, java.sql.Date.valueOf(obj.getFecha_reserva()));
        	ps.setDate(2, java.sql.Date.valueOf(obj.getFecha_salida()));
        	ps.setDate(3, java.sql.Date.valueOf(obj.getFecha_regreso()));
            ps.setInt(4, obj.getNumero_viajeros());
            ps.setDouble(5, obj.getImporte_total());
            ps.setString(6, obj.getEstado_reserva());

            ps.setInt(7, obj.getID_cliente());
            ps.setInt(8, obj.getID_empleado());
            ps.setInt(9, obj.getID_destino());

            ps.executeUpdate();//Ejecutamos la consulta para insertar la reserva

        } catch (SQLException e) {
            System.out.println("Error al insertar reserva: " + e.getMessage());
        }
    }

    // LISTAR TODAS LAS RESERVAS
    @Override
    public ArrayList<Reserva> listarTodos() {
        ArrayList<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql);//Preparamos la consulta SQL
             ResultSet rs = ps.executeQuery()) {//Ejecutamos la consulta y obtenemos el resultado

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setID_reserva(rs.getInt("ID_reserva"));
                r.setFecha_reserva(rs.getDate("Fecha_reserva").toLocalDate());
                r.setFecha_salida(rs.getDate("Fecha_salida").toLocalDate());
                r.setFecha_regreso(rs.getDate("Fecha_regreso").toLocalDate());
                r.setNumero_viajeros(rs.getInt("Numero_viajeros"));
                r.setImporte_total(rs.getDouble("Importe_total"));
                r.setEstado_reserva(rs.getString("Estado_reserva"));

                r.setID_cliente(rs.getInt("ID_cliente"));
                r.setID_empleado(rs.getInt("ID_empleado"));
                r.setID_destino(rs.getInt("ID_destino"));

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }

        return lista;
    }

    // HISTORIAL DE RESERVAS POR CLIENTE
    public ArrayList<Reserva> historialPorCliente(int idCliente) {
        ArrayList<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA WHERE ID_cliente=?";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql)) {//Preparamos la consulta SQL

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();//Ejecutamos la consulta y obtenemos el resultado

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setID_reserva(rs.getInt("ID_reserva"));
                r.setFecha_reserva(rs.getDate("Fecha_reserva").toLocalDate());
                r.setFecha_salida(rs.getDate("Fecha_salida").toLocalDate());
                r.setFecha_regreso(rs.getDate("Fecha_regreso").toLocalDate());
                r.setNumero_viajeros(rs.getInt("Numero_viajeros"));
                r.setImporte_total(rs.getDouble("Importe_total"));
                r.setEstado_reserva(rs.getString("Estado_reserva"));

                r.setID_cliente(rs.getInt("ID_cliente"));
                r.setID_empleado(rs.getInt("ID_empleado"));
                r.setID_destino(rs.getInt("ID_destino"));

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial: " + e.getMessage());
        }

        return lista;
    }
}
