package Controlador;

import DAO.EmpleadoDAO;
import DTO.Empleado;
import Excepciones.EmpleadoInvalidoException;
import java.util.ArrayList;
import java.util.Collections;

public class EmpleadoControlador implements Validar<Empleado> {

    private EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    public void validar(Empleado e) throws EmpleadoInvalidoException {

        if (e.getNombre_completo() == null || e.getNombre_completo().isBlank()) {
            throw new EmpleadoInvalidoException("El nombre no puede estar vacío.");
        }

        if (e.getCargo() == null || e.getCargo().isBlank()) {
            throw new EmpleadoInvalidoException("El cargo no puede estar vacío.");
        }

        if (e.getEspecialidad() == null || e.getEspecialidad().isBlank()) {
            throw new EmpleadoInvalidoException("La especialidad no puede estar vacía.");
        }

        if (e.getTurno() == null || e.getTurno().isBlank()) {
            throw new EmpleadoInvalidoException("El turno no puede estar vacío.");
        }

        if (e.getAnios_experiencia() < 0) {
            throw new EmpleadoInvalidoException("Los años de experiencia no pueden ser negativos.");
        }
    }

    public void registrarEmpleado(Empleado e) throws EmpleadoInvalidoException {
        validar(e);
        dao.insertar(e);
    }

    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> lista = dao.listarTodos(); //Obtenemos la lista del DAO

        Collections.sort(lista); //Ordenamos usando compareTo()

        return lista; //Devolvemos la lista ordenada
    }
    
    public ArrayList<Empleado> listarEmpleadosInsercion() {
        return dao.listarTodos(); //Devolvemos la lista como la devuelve el DAO, sin ordenar
    }

}

