package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.Categoria;

public class CategoriaDAO extends AbstractaDAO<Categoria> {

	public CategoriaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	//Método INSERT para insertar una categoría a la base de datos MundoDestino
	@Override
    public void insertar(Categoria obj) {
        String sql = "INSERT INTO CATEGORIA (Categoria) VALUES (?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getCategoria());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }
    }

	//Método SELECT para listar las categorías a la base de datos MundoDestino
	@Override
    public ArrayList<Categoria> listarTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setID_categoria(rs.getInt("ID_categoria"));
                c.setCategoria(rs.getString("Categoria"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
            e.printStackTrace();//Sirve para imprimir el error completo en la consola
        }

        return lista;
    }

}
