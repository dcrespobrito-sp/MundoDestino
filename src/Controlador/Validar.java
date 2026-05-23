package Controlador;

	public interface Validar<T> {//Interfaz genérica para validar objetos de cualquier tipo
		void validar(T objeto) throws Exception;//Método para validar un objeto, lanza una excepción en caso de que el objeto no sea válido
}
