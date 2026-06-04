package Main;

import java.util.InputMismatchException;//Controla errores cuando el usuario mete letras en vez de números
import java.util.Scanner;

import Vistas.ClienteView;
import Vistas.EmpleadoView;
import Vistas.DestinoView;
import Vistas.CategoriaView;
import Vistas.ReservaView;

import Conexion.Conexion;

public class Main {

	public static void main(String[] args) {

		Conexion.getConexion();//Inicializamos la conexión a la base de datos para comprobar que funciona correctamente

		Scanner sc = new Scanner(System.in);

		// Creamos los objetos de las vistas para cada entidad
		ClienteView clienteView = new ClienteView();
		EmpleadoView empleadoView = new EmpleadoView();
		DestinoView destinoView = new DestinoView();
		CategoriaView categoriaView = new CategoriaView();
		ReservaView reservaView = new ReservaView();

		int opcion = -1;

		while (opcion != 0) {

			try {

				System.out.println("---MENU PRINCIPAL---");
				System.out.println("0. Salir");
				System.out.println("1. Cliente");
				System.out.println("2. Empleado");
				System.out.println("3. Destino");
				System.out.println("4. Categoria");
				System.out.println("5. Reserva");
				System.out.print("Elige una opción: ");
				opcion = sc.nextInt();
				System.out.println();

				

				switch (opcion) {
			
				case 0 -> System.out.println("Saliendo del programa...");
				case 1 -> clienteView.menuCliente();
				case 2 -> empleadoView.menuEmpleado();
				case 3 -> destinoView.menuDestino();
				case 4 -> categoriaView.menuCategoria();
				case 5 -> reservaView.menuReserva();
				default -> System.out.println("Opción no válida.");
			
				}

			} catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

				System.out.println("ERROR: Debes introducir un número.");
				sc.nextLine(); //Limpiamos buffer
			}
		}

		sc.close();
	}
}