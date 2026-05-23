package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.Cliente;

public class ClienteDAO extends AbstractaDAO<Cliente> {

    public ClienteDAO() {}

    //Método INSERT para insertar un cliente a la base de datos MundoDestino
    @Override
    public void insertar(Cliente obj) {
        String sql = "INSERT INTO CLIENTE (Nombre_completo, DNI, Telefono, Correo, Direccion, Pasaporte) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();//
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getNombre_completo());
            ps.setString(2, obj.getDNI());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getCorreo());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getPasaporte());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }
    }

	//Método SELECT para listar los clientes a la base de datos MundoDestino
    @Override
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setID_cliente(rs.getInt("ID_cliente"));
                c.setNombre_completo(rs.getString("Nombre_completo"));
                c.setDNI(rs.getString("DNI"));
                c.setTelefono(rs.getString("Telefono"));
                c.setCorreo(rs.getString("Correo"));
                c.setDireccion(rs.getString("Direccion"));
                c.setPasaporte(rs.getString("Pasaporte"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }

        return lista;
    }
}