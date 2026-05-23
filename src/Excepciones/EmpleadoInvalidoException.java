package Excepciones;

public class EmpleadoInvalidoException extends Exception {//Excepción personalizada para indicar que un empleado es inválido
	public EmpleadoInvalidoException(String mensaje) {
		super(mensaje);
	}

}
