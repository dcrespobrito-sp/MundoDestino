package Vistas;

import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Iterator;
import Controlador.ReservaControlador;
import DTO.Reserva;
import Excepciones.ReservaInvalidoException;
import java.util.stream.Collectors;

public class ReservaView {

    Scanner sc = new Scanner(System.in);
    ReservaControlador controlador = new ReservaControlador();

    public void menuReserva() {
    	
   	 int opcion = -1;//Inicializamos a -1 para que entre al menú, ya que si lo inicializamos a 0 no entraría nunca al menú

        while (opcion != 0) {
        	
            try {
            
           
            System.out.println("---MENÚ RESERVA---");
            System.out.println("0. Volver");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Listar todas las reservas");
            System.out.println("3. Historial de reservas de un cliente");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                case 1:
                    registrarReserva();
                    break;

                case 2:
                    listarReservas();
                    break;

                case 3:
                    historialCliente();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
           } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

  				System.out.println("ERROR: Debes introducir un número.");
  				sc.nextLine(); //Limpiamos buffer
  			}
        }
    }

    private void registrarReserva() {
        sc.nextLine(); // limpiar buffer

        System.out.println("\n--- REGISTRAR RESERVA ---");

        System.out.print("Número de viajeros: ");
        int Numero_viajeros = sc.nextInt();

        System.out.print("Importe total: ");
        double Importe_Total = sc.nextDouble();
        sc.nextLine(); // limpiar buffer

        System.out.print("Estado de la reserva ('Pendiente','Pagada','Confirmada','Cancelada'): ");
        String Estado_reserva = sc.nextLine();

        System.out.print("ID del cliente: ");
        int ID_cliente = sc.nextInt();

        System.out.print("ID del destino: ");
        int ID_destino = sc.nextInt();

        System.out.print("ID del empleado: ");
        int ID_empleado = sc.nextInt();

        // Crear la reserva
        Reserva r = new Reserva(new Date(), new Date(), new Date(), Numero_viajeros, Importe_Total, Estado_reserva, ID_cliente, ID_empleado, ID_destino);


        try {
            controlador.registrarReserva(r);
            System.out.println("Reserva registrada correctamente.");
        } catch (ReservaInvalidoException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    

    private void historialCliente() {

        try {

            System.out.print("Introduce el ID del cliente: ");
            int ID_cliente = sc.nextInt();

            System.out.println("---HISTORIAL DE RESERVAS DEL CLIENTE---");
            Iterator<Reserva> it = controlador.historialCliente(ID_cliente).iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
            }

        } catch (ReservaInvalidoException e) {//Captura el error si el ID_cliente no es válido
            System.out.println("ERROR: " + e.getMessage());

        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números
            System.out.println("ERROR: Debes introducir un número.");
            sc.nextLine();
        }
    }
    
    
  //---------------------------------------------------------------------------------------------------------------------------------------
    //Submenu para listar reservas con dos opciones: ordenados por fecha de salida o sin orden (HashMap + Streams)
    private void listarReservas() {

        int opcion = -1;

        while (opcion != 0) {
        	
        	try {
        	
            System.out.println("---LISTAR RESERVAS---");
            System.out.println("0. Volver");
            System.out.println("1. Ordenados alfabéticamente");
            System.out.println("2. Orden de inserción");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); //Limpiamos buffer

            switch (opcion) {

                case 0:
                    System.out.println("Volviendo...");
                    break;

                case 1:
                    listarReservasOrdenados(); //Llamamos al método que lista ordenado
                    break;

                case 2:
                    listarReservasInsercion(); //Llamamos al método que lista sin ordenar
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

			System.out.println("ERROR: Debes introducir un número.");
			sc.nextLine(); //Limpiamos buffer
        }
    }
 }

    //Método para listar reservas ordenadas por fecha de salida  y sino por ID_reserva mediante el método compareTo() de Reserva
    private void listarReservasOrdenados() {

        System.out.println("---RESERVAS ORDENADAS---");

        Iterator<Reserva> it = controlador.listarReservas().iterator(); //Iterator para recorrer la lista ordenada que devuelve el controlador

        while (it.hasNext()) {
            System.out.println(it.next()); //Imprime cada reserva ordenada
        }
    }

    //Método para listar reservas sin orden usando un HashMap (clave = ID_reserva, valor = Reserva)
    private void listarReservasInsercion() {

        System.out.println("---RESERVAS POR INSERCIÓN---");
        //Rellenamos el HashMap con las reservas SIN ORDEN
        Map<Integer, Reserva> mapaReservas =
                controlador.listarReservasInsercion().stream()//Uso de stream ya que el controlador devuelve una lista, así podemos convertirla a Map con Collectors
                    .collect(Collectors.toMap(
                        r -> r.getID_reserva(),  // clave
                        r -> r                   // valor
                    ));

            //Imprimimos usando lambdas
            mapaReservas.values().forEach(System.out::println);
    }

//---------------------------------------------------------------------------------------------------------------------------------------
}
