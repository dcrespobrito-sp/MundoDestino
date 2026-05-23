package Excepciones;

public class ClienteInvalidoException extends Exception {//Excepción personalizada para indicar que un cliente es inválido
    public ClienteInvalidoException(String mensaje) {
        super(mensaje);
    }
}
