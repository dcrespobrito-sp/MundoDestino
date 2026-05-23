package Excepciones;

public class DestinoInvalidoException extends Exception {//Excepción personalizada para indicar que un destino es inválido
	public DestinoInvalidoException(String mensaje) {
		super(mensaje);
	}

}
