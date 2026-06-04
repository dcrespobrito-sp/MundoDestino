package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import DAO.DestinoDAO;
import DTO.Destino;
import Excepciones.DestinoInvalidoException;

public class DestinoControlador implements Validar<Destino> {

    private DestinoDAO dao = new DestinoDAO();

    @Override
    public void validar(Destino d) throws DestinoInvalidoException {

        if (d.getNombre() == null || d.getNombre().isBlank()) {
            throw new DestinoInvalidoException("El nombre no puede estar vacío.");
        }

        if (d.getPais() == null || d.getPais().isBlank()) {
            throw new DestinoInvalidoException("El país no puede estar vacío.");
        }

        if (d.getCiudad() == null || d.getCiudad().isBlank()) {
            throw new DestinoInvalidoException("La ciudad no puede estar vacía.");
        }

        if (d.getPrecio_base() <= 0) {
            throw new DestinoInvalidoException("El precio base debe ser mayor que 0.");
        }

        if (d.getDias() <= 0) {
            throw new DestinoInvalidoException("Los días deben ser mayores que 0.");
        }

        if (d.getDisponibilidad() < 0) {
            throw new DestinoInvalidoException("La disponibilidad no puede ser negativa.");
        }

        if (d.getID_categoria() <= 0) {
            throw new DestinoInvalidoException("Debe seleccionar una categoría válida.");
        }
    }

    public void registrarDestino(Destino d) throws DestinoInvalidoException {
        validar(d);
        dao.insertar(d);
    }


    public ArrayList<Destino> listarPorCategoria(int ID_categoria) throws DestinoInvalidoException {

        //ID negativo o 0
        if (ID_categoria <= 0) {
            throw new DestinoInvalidoException("El ID de categoría debe ser mayor que 0.");
        }

        ArrayList<Destino> lista = dao.listarPorCategoria(ID_categoria);

        //Si no hay destinos para esa categoría, lanzamos una excepción
        if (lista.isEmpty()) {
            throw new DestinoInvalidoException("No existen destinos disponibles para la categoría con ID: "+ ID_categoria);
        }

        return lista;
    }
    
    public void modificarDestino(Destino d)throws DestinoInvalidoException {
    	
    	//Si ID negativo o 0
        if (d.getID_destino() <= 0) {
            throw new DestinoInvalidoException("El ID del destino debe ser mayor que 0.");
        }
        validar(d);
        try {

            dao.actualizar(d);

        } catch (SQLException e) {//Ponemos SQLException pq viene del DAO, pero realmente es una excepción personalizada para el controlador
            throw new DestinoInvalidoException("No existe ningún destino con ID: "+ d.getID_destino());
        }
    }
    
    public void eliminarDestino(int ID_destino) throws DestinoInvalidoException {
    	
    	//Si ID negativo o 0
        if (ID_destino <= 0) {
        	throw new DestinoInvalidoException("El ID del destino debe ser mayor que 0.");
        }
        try {

            dao.eliminar(ID_destino);

        } catch (SQLException e) {//Ponemos SQLException pq viene del DAO, pero realmente es una excepción personalizada para el controlador
            throw new DestinoInvalidoException("No existe ningún destino con ID: "+ ID_destino);
        }
    }

    
    public ArrayList<Destino> listarDestinos() {//Método para listar todos los destinos
        ArrayList<Destino> lista = dao.listarTodos(); //Obtenemos la lista del DAO

        Collections.sort(lista); //Ordenamos usando compareTo()

        return lista; //Devolvemos la lista ordenada
    }
    
    public ArrayList<Destino> listarDestinosInsercion() {
        return dao.listarTodos(); //Tal cual, orden de inserción
    }

        
        
}
