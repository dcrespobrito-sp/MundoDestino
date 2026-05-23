package Controlador;

import DAO.ClienteDAO;//Importamos la clase DAO para realizar operaciones CRUD con los clientes
import DTO.Cliente;
import Excepciones.ClienteInvalidoException;//Importamos la excepción personalizada para validar los datos del cliente
import java.util.ArrayList;
import java.util.Collections;

public class ClienteControlador implements Validar<Cliente> {//Implementamos la interfaz Validar para validar los datos del cliente

    private ClienteDAO dao = new ClienteDAO();

  //Método para validar los datos del cliente, lanza una excepción en caso de que algún dato no sea válido
    @Override
    public void validar(Cliente c) throws ClienteInvalidoException {

        if (c.getNombre_completo() == null || c.getNombre_completo().isBlank()) {//Si el nombre del cliente es nulo o está vacío, lanza una excepción
            throw new ClienteInvalidoException("El nombre no puede estar vacío.");
        }

        if ((c.getDNI() == null || c.getDNI().isBlank()) && //El cliente debe tener o un DNI o un pasaporte
            (c.getPasaporte() == null || c.getPasaporte().isBlank())) {
            throw new ClienteInvalidoException("Debe introducir DNI o Pasaporte.");
        }
        
        if (c.getDNI().trim().length() != 9) {//Si el DNI del cliente no tiene exactamente 9 caracteres, lanza una excepción
            throw new ClienteInvalidoException("El DNI debe tener exactamente 9 caracteres.");
        }

        if (c.getTelefono() == null || c.getTelefono().isBlank()) {//Si el teléfono del cliente es nulo o está vacío, lanza una excepción
            throw new ClienteInvalidoException("El teléfono no puede estar vacío.");
        }

        if (c.getCorreo() == null || !c.getCorreo().contains("@")) {//Si el correo del cliente es nulo o no contiene un símbolo de "@", lanza una excepción
            throw new ClienteInvalidoException("El correo no es válido.");
        }
    }

    public void registrarCliente(Cliente c) throws ClienteInvalidoException {//Lanza una excepción en caso de que el cliente no sea válido
        validar(c);//Valida el cliente antes de registrarlo
        dao.insertar(c);//Si el cliente es válido, lo inserta en la base de datos usando el DAO
    }
    
    public ArrayList<Cliente> listarClientes() {//Método para listar todos los clientes
        ArrayList<Cliente> lista = dao.listarTodos(); //Obtenemos la lista del DAO

        Collections.sort(lista); //Ordenamos usando compareTo()

        return lista; //Devolvemos la lista ordenada
    }
    
    public ArrayList<Cliente> listarClientesInsercion() {
        return dao.listarTodos(); //Tal cual, orden de inserción
    }

}
