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
		
	}
	
	//Método INSERT para insertar una categoría a la base de datos MundoDestino
	@Override
    public void insertar(Categoria obj) {
        String sql = "INSERT INTO CATEGORIA (Categoria) VALUES (?)";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql)) {//Preparamos la consulta SQL

            ps.setString(1, obj.getCategoria());
            ps.executeUpdate();//Ejecutamos la consulta para insertar la categoría

        } catch (SQLException e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());

        }
    }

	//Método SELECT para listar las categorías a la base de datos MundoDestino
	@Override
    public ArrayList<Categoria> listarTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";

        try (Connection con = Conexion.getConexion();//Obtenemos la conexión a la base de datos
             PreparedStatement ps = con.prepareStatement(sql);//Preparamos la consulta SQL
             ResultSet rs = ps.executeQuery()) {////Ejecutamos la consulta y obtenemos el resultado

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setID_categoria(rs.getInt("ID_categoria"));
                c.setCategoria(rs.getString("Categoria"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());

        }

        return lista;
    }

}
