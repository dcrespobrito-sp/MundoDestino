package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.Destino;

public class DestinoDAO extends AbstractaDAO<Destino> {

    public DestinoDAO() {}

    //Método INSERT para insertar un destino a la base de datos MundoDestino
    @Override
    public void insertar(Destino obj) {
        String sql = "INSERT INTO DESTINO (Nombre, Pais, Ciudad, Descripcion, Precio_base, Dias, Disponibilidad, ID_categoria) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getPais());
            ps.setString(3, obj.getCiudad());
            ps.setString(4, obj.getDescripcion());
            ps.setDouble(5, obj.getPrecio_base());
            ps.setInt(6, obj.getDias());
            ps.setInt(7, obj.getDisponibilidad());
            ps.setInt(8, obj.getID_categoria());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar destino: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }
    }

    //Método UPDATE para actualizar un destino a la base de datos MundoDestino
    public void actualizar(Destino obj) {
        String sql = "UPDATE DESTINO SET Nombre=?, Pais=?, Ciudad=?, Descripcion=?, Precio_base=?, Dias=?, Disponibilidad=?, ID_categoria=? "
                   + "WHERE ID_destino=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getPais());
            ps.setString(3, obj.getCiudad());
            ps.setString(4, obj.getDescripcion());
            ps.setDouble(5, obj.getPrecio_base());
            ps.setInt(6, obj.getDias());
            ps.setInt(7, obj.getDisponibilidad());
            ps.setInt(8, obj.getID_categoria());
            ps.setInt(9, obj.getID_destino());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar destino: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }
    }

	//Método DELETE para eliminar un destino a la base de datos MundoDestino
    public void eliminar(int id) {
        String sql = "DELETE FROM DESTINO WHERE ID_destino=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar destino: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }
    }

	//Método SELECT para listar los destinos a la base de datos MundoDestino
    @Override
    public ArrayList<Destino> listarTodos() {
        ArrayList<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Destino d = new Destino();
                d.setID_destino(rs.getInt("ID_destino"));
                d.setNombre(rs.getString("Nombre"));
                d.setPais(rs.getString("Pais"));
                d.setCiudad(rs.getString("Ciudad"));
                d.setDescripcion(rs.getString("Descripcion"));
                d.setPrecio_base(rs.getDouble("Precio_base"));
                d.setDias(rs.getInt("Dias"));
                d.setDisponibilidad(rs.getInt("Disponibilidad"));
                d.setID_categoria(rs.getInt("ID_categoria"));

                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar destinos: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }

        return lista;
    }
    
    
    //Método SELECT para listar los destinos por categoría a la base de datos MundoDestino
    public ArrayList<Destino> listarPorCategoria(int idCategoria) {
        ArrayList<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO WHERE ID_categoria=? AND Disponibilidad > 0";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Destino d = new Destino();
                d.setID_destino(rs.getInt("ID_destino"));
                d.setNombre(rs.getString("Nombre"));
                d.setPais(rs.getString("Pais"));
                d.setCiudad(rs.getString("Ciudad"));
                d.setDescripcion(rs.getString("Descripcion"));
                d.setPrecio_base(rs.getDouble("Precio_base"));
                d.setDias(rs.getInt("Dias"));
                d.setDisponibilidad(rs.getInt("Disponibilidad"));
                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar destinos por categoría: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }

        return lista;
    }

}
