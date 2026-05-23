package Controlador;

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


    public ArrayList<Destino> listarPorCategoria(int ID_categoria) {//Método para listar destinos filtrados por categoría
        ArrayList<Destino> lista = dao.listarPorCategoria(ID_categoria); //Obtenemos la lista filtrada del DAO

        Collections.sort(lista); //Ordenamos usando compareTo()

        return lista; //Devolvemos la lista ordenada
    }
    
    public void modificarDestino(Destino d) throws DestinoInvalidoException {
        validar(d);
        dao.actualizar(d);
    }
    
    public void eliminarDestino(int ID_destino) {
        dao.eliminar(ID_destino);
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
