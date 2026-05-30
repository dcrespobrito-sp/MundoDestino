package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Controlador.ClienteControlador;
import DTO.Cliente;

public class ClienteView {
	
	
    Scanner sc = new Scanner(System.in);
    ClienteControlador controlador = new ClienteControlador();

    public void menuCliente() {
    	
   	 int opcion = -1;//Inicializamos a -1 para que entre al menú, ya que si lo inicializamos a 0 no entraría nunca al menú
   	 
        while (opcion != 0) {
        	
          try {
        	  
          
            System.out.println("---MENU CLIENTE---");
            System.out.println("0. Volver");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
            	case 0:
            		System.out.println("Volviendo al menú principal...");
            		break;
                case 1:
                	registrarCliente();
                	break;
                case 2:
                	listarClientes();
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

    public void registrarCliente() {
    	sc.nextLine(); //Limpiamos buffer antes de leer cadenas, ya que se ha leído un entero en el menú

        System.out.print("Nombre completo: ");
        String Nombre_completo = sc.nextLine();

        System.out.print("DNI: ");
        String DNI = sc.nextLine();

        System.out.print("Teléfono: ");
        String Telefono = sc.nextLine();

        System.out.print("Correo: ");
        String Correo = sc.nextLine();

        System.out.print("Dirección: ");
        String Direccion = sc.nextLine();

        System.out.print("Pasaporte: ");
        String Pasaporte = sc.nextLine();

        Cliente c = new Cliente(Nombre_completo, DNI, Telefono, Correo, Direccion, Pasaporte);

        try {
            controlador.registrarCliente(c);
            System.out.println("Cliente registrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//------------------------------------------------------------------------------------------------------------------
    //Submenu para listar clientes con dos opciones: orden alfabético (compareTo) o orden de inserción (HashMap + Streams)
    public void listarClientes() {

        int opcion = -1;

        while (opcion != 0) {
        	       
        	try {
        		       	
            System.out.println("---LISTAR CLIENTES---");
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
                    listarClientesOrdenados(); //Lista ordenada usando compareTo()
                    break;

                case 2:
                    listarClientesInsercion(); //Lista sin ordenar usando HashMap + Streams
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

    //Método para listar clientes ordenados alfabéticamente mediante compareTo()
    private void listarClientesOrdenados() {

        System.out.println("---CLIENTES ORDENADOS---");

        Iterator<Cliente> it = controlador.listarClientes().iterator(); //Iterator para recorrer la lista ordenada

        while (it.hasNext()) {
            System.out.println(it.next()); //Imprime cada cliente ordenado
        }
    }

    //Método para listar clientes en orden de inserción usando un HashMap + Streams
    private void listarClientesInsercion() {

        System.out.println("---CLIENTES POR INSERCIÓN---");

        //Convertimos la lista del controlador en un Map usando Streams
        Map<Integer, Cliente> mapaClientes =
                controlador.listarClientesInsercion().stream()////Uso de stream ya que el controlador devuelve una lista, así podemos convertirla a Map con Collectors
                    .collect(Collectors.toMap(
                        c -> c.getID_cliente(), //Clave = ID_cliente
                        c -> c                  //Valor = objeto Cliente
                    ));

        //Imprimimos usando lambdas
        mapaClientes.values().forEach(System.out::println);
    }
}
//------------------------------------------------------------------------------------------------------------------
