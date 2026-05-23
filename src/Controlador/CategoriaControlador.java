package Controlador;

import java.util.ArrayList;
import java.util.Collections;

import DAO.CategoriaDAO;
import DTO.Categoria;
import Excepciones.CategoriaInvalidoException;

public class CategoriaControlador implements Validar<Categoria> {

    private CategoriaDAO dao = new CategoriaDAO();

    @Override
    public void validar(Categoria c) throws CategoriaInvalidoException {//Método para validar el nombre de la categoría con excepción personalizada

        if (c.getCategoria() == null || c.getCategoria().isBlank()) {
            throw new CategoriaInvalidoException("El nombre de la categoría no puede estar vacío.");
        }

        if (c.getCategoria().length() > 50) {
            throw new CategoriaInvalidoException("El nombre de la categoría no puede superar los 50 caracteres.");
        }
    }

    public void registrarCategoria(Categoria c) throws CategoriaInvalidoException {
        validar(c);
        dao.insertar(c);
    }

    public ArrayList<Categoria> listarCategorias() {

        ArrayList<Categoria> lista = dao.listarTodos();//Obtenemos la lista del DAO
        Collections.sort(lista);//Ordenamos usando compareTo()

        return lista;//Devolvemos la lista ordenada
    }
    
    public ArrayList<Categoria> listarCategoriasInsercion() {
        return dao.listarTodos();
    }

}
