package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import Controlador.DestinoControlador;
import DTO.Destino;
import Excepciones.DestinoInvalidoException;

public class DestinoView {


    Scanner sc = new Scanner(System.in);
    DestinoControlador controlador = new DestinoControlador();

    public void menuDestino() {
    	 int opcion = -1;//Inicializamos a -1 para que entre al menú, ya que si lo inicializamos a 0 no entraría nunca al menú

        while (opcion != 0) {
           
        	
          try {
        	  
          
            System.out.println("---MENU DESTINO---");
            System.out.println("0. Volver");
            System.out.println("1. Registrar destino");
            System.out.println("2. Listar todos los destinos");
            System.out.println("3. Listar destinos por categoría");
            System.out.println("4. Modificar destino");
            System.out.println("5. Eliminar destino");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println();
            

            switch (opcion) {
            
            case 0 -> System.out.println("Volviendo al menú principal...");
            case 1 -> registrarDestino();
            case 2 -> listarDestinos();
            case 3 -> listarPorCategoria();
            case 4 -> modificarDestino();
            case 5 -> eliminarDestino();
            default -> System.out.println("Opción no válida.");
            
            }
          } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

				System.out.println("ERROR: Debes introducir un número.");
				sc.nextLine(); //Limpiamos buffer
			}
        }
    }

    public void registrarDestino() {

        sc.nextLine(); //Limpiamos buffer antes de leer cadenas

        System.out.print("Nombre: ");
        String Nombre = sc.nextLine();

        System.out.print("País: ");
        String Pais = sc.nextLine();

        System.out.print("Ciudad: ");
        String Ciudad = sc.nextLine();

        System.out.print("Descripción: ");
        String Descripcion = sc.nextLine();

        System.out.print("Precio base: ");
        double Precio_base = sc.nextDouble();
        sc.nextLine();//Limpiamos buffer

        System.out.print("Días: ");
        int Dias = sc.nextInt();
        sc.nextLine();//Limpiamos buffer

        System.out.print("Disponibilidad: ");
        int Disponibilidad = sc.nextInt();
        sc.nextLine();//Limpiamos buffer

        System.out.print("ID categoría: ");
        int ID_categoria = sc.nextInt();
        sc.nextLine();//Limpiamos buffer

        //Creamos un objeto Destino con los datos introducidos
        Destino d = new Destino(Nombre, Pais, Ciudad, Descripcion, Precio_base, Dias, Disponibilidad, ID_categoria);

        try {
            controlador.registrarDestino(d);
            System.out.println("Destino registrado correctamente.");
        } catch (DestinoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void modificarDestino() {

        System.out.print("Introduce el ID del destino a modificar: ");
        int ID_destino = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String Nombre = sc.nextLine();

        System.out.print("Nuevo país: ");
        String Pais = sc.nextLine();

        System.out.print("Nueva ciudad: ");
        String Ciudad = sc.nextLine();

        System.out.print("Nueva descripción: ");
        String Descripcion = sc.nextLine();

        System.out.print("Nuevo precio base: ");
        double Precio_base = sc.nextDouble();
        sc.nextLine();

        System.out.print("Nuevos días de duración: ");
        int Dias = sc.nextInt();
        sc.nextLine();

        System.out.print("Nueva disponibilidad: ");
        int Disponibilidad = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo ID de categoría: ");
        int ID_categoria = sc.nextInt();
        sc.nextLine();

        //Creamos el destino con todos los datos nuevos
        Destino d = new Destino(ID_destino, Nombre, Pais, Ciudad, Descripcion, Precio_base, Dias, Disponibilidad, ID_categoria);


        try {
            controlador.modificarDestino(d);
            System.out.println("Destino modificado correctamente.");
        } catch (DestinoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
    
    public void eliminarDestino() {

        System.out.print("Introduce el ID del destino a eliminar: ");
        int ID_destino = sc.nextInt();
        sc.nextLine();//Limpiamos buffer

        try {
            controlador.eliminarDestino(ID_destino);
            System.out.println("Destino eliminado correctamente.");
        } catch (DestinoInvalidoException e) {
            System.out.println("Error al eliminar destino: " + e.getMessage());

        }
    }
    
     
    public void listarPorCategoria() {

        try {

            System.out.print("Introduce el ID de la categoría: ");
            int ID_categoria = sc.nextInt();

            System.out.println("---LISTA DE DESTINOS POR CATEGORÍA---");
            Iterator<Destino> it = controlador.listarPorCategoria(ID_categoria).iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
            }

        } catch (DestinoInvalidoException e) {
            System.out.println("ERROR: " + e.getMessage());

        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números
            System.out.println("ERROR: Debes introducir un número.");
            sc.nextLine();
        }
    }
    
//------------------------------------------------------------------------------------------------------------------
    //Submenu para listar destinos con dos opciones: orden alfabético (compareTo) o orden de inserción (HashMap + Streams)
    public void listarDestinos() {

        int opcion = -1;

        while (opcion != 0) {
        	
        	try {

            System.out.println("---LISTAR DESTINOS---");
            System.out.println("0. Volver");
            System.out.println("1. Ordenados alfabéticamente");
            System.out.println("2. Orden de inserción");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); //Limpiamos buffer
            System.out.println();

            switch (opcion) {

            case 0 -> System.out.println("Volviendo...");
            case 1 -> listarDestinosOrdenados();
            case 2 -> listarDestinosInsercion();
            default -> System.out.println("Opción no válida.");
            
            }
        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

			System.out.println("ERROR: Debes introducir un número.");
			sc.nextLine(); //Limpiamos buffer
        }
    }
  }
    //Lista ordenada usando compareTo()
    private void listarDestinosOrdenados() {

        System.out.println("---DESTINOS ORDENADOS---");

        Iterator<Destino> it = controlador.listarDestinos().iterator();

        while (it.hasNext()) {
            System.out.println(it.next());//Imprimimos cada destino ordenado alfabéticamente
        }
    }

    //Método para listar destinos en orden de inserción.
    private void listarDestinosInsercion() {

        System.out.println("---DESTINOS POR INSERCIÓN---");


        LinkedHashMap<Integer, Destino> mapaClientes = new LinkedHashMap<>();//LinkedHashMap para mantener el orden de inserción

        controlador.listarDestinosInsercion().stream()//Paso la lista de destinos a un stream para poder usar forEach
                   .forEach(c -> mapaClientes.put(c.getID_destino(), c));//Con el forEach obtengo de cada destino su ID_destino y su objeto destino completo para guardarlo en el LinkedHashMap

        //Uso de expresiones lambda para imprimir cada destino en orden de inserción
        mapaClientes.values().forEach(System.out::println);
    }

    //------------------------------------------------------------------------------------------------------------------



}
