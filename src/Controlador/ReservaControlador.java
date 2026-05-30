package Controlador;
import DAO.ReservaDAO;
import DTO.Reserva;
import Excepciones.ReservaInvalidoException;
import java.util.ArrayList;
import java.util.Collections;

public class ReservaControlador implements Validar<Reserva> {

    private ReservaDAO dao = new ReservaDAO();

    @Override
    public void validar(Reserva r) throws ReservaInvalidoException {

        // Validación de IDs
        if (r.getID_cliente() <= 0) {
            throw new ReservaInvalidoException("Debe seleccionar un cliente válido.");
        }

        if (r.getID_destino() <= 0) {
            throw new ReservaInvalidoException("Debe seleccionar un destino válido.");
        }

        if (r.getID_empleado() <= 0) {
            throw new ReservaInvalidoException("Debe seleccionar un empleado válido.");
        }

        // Validación de viajeros e importe
        if (r.getNumero_viajeros() <= 0) {
            throw new ReservaInvalidoException("El número de viajeros debe ser mayor que 0.");
        }

        if (r.getImporte_total() <= 0) {
            throw new ReservaInvalidoException("El importe total debe ser mayor que 0.");
        }

        // Validación del estado
        String Estado_reserva = r.getEstado_reserva();
        if (Estado_reserva == null || Estado_reserva.isBlank()) {
            throw new ReservaInvalidoException("El estado de la reserva no puede estar vacío.");
        }
        Estado_reserva = Estado_reserva.trim().toLowerCase(); // Convertimos a minúsculas y eliminamos espacios

        if (!Estado_reserva.equals("pendiente") &&
            !Estado_reserva.equals("pagada") &&
            !Estado_reserva.equals("confirmada") &&
            !Estado_reserva.equals("cancelada")) {

            throw new ReservaInvalidoException("Estado inválido. Debe ser: Pendiente, Pagada, Confirmada o Cancelada.");
        }

        // Validación de fechas
        if (r.getFecha_reserva() == null ||r.getFecha_salida() == null ||r.getFecha_regreso() == null) {

            throw new ReservaInvalidoException("Las fechas no pueden ser nulas.");
        }

        if (r.getFecha_reserva().isAfter(r.getFecha_salida())) {
            throw new ReservaInvalidoException("La fecha de reserva no puede ser posterior a la fecha de salida.");
        }

        if (r.getFecha_salida().isAfter(r.getFecha_regreso())) {
            throw new ReservaInvalidoException("La fecha de salida no puede ser posterior a la fecha de regreso.");
        }
    }

    public void registrarReserva(Reserva r) throws ReservaInvalidoException {
        validar(r);
        dao.insertar(r);
    }
  

    public ArrayList<Reserva> historialCliente(int ID_cliente) throws ReservaInvalidoException {

    	    // Validar ID negativo o 0
    	    if (ID_cliente <= 0) {
    	        throw new ReservaInvalidoException("El ID del cliente debe ser mayor que 0.");
    	    }

    	    ArrayList<Reserva> lista = dao.historialPorCliente(ID_cliente);

    	    // Si no hay reservas
    	    if (lista.isEmpty()) {
    	        throw new ReservaInvalidoException("No existen reservas asociadas al cliente con ID: " + ID_cliente);
    	    }

    	    Collections.sort(lista);

    	    return lista;
    	}
    
    
    
    public ArrayList<Reserva> listarReservas() {
        ArrayList<Reserva> lista = dao.listarTodos(); //Obtenemos la lista del DAO

        Collections.sort(lista); //Ordenamos usando compareTo()

        return lista; //Devolvemos la lista ordenada
    }
    
    public ArrayList<Reserva> listarReservasInsercion() {
        return dao.listarTodos(); // tal cual, orden de inserción
    }
}
