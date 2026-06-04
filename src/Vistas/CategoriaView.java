package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import Controlador.CategoriaControlador;
import DTO.Categoria;
import Excepciones.CategoriaInvalidoException;

public class CategoriaView {

    Scanner sc = new Scanner(System.in);
    CategoriaControlador controlador = new CategoriaControlador();

    public void menuCategoria() {
   	 int opcion = -1;//Inicializamos a -1 para que entre al menú, ya que si lo inicializamos a 0 no entraría nunca al menú
    	
      while (opcion != 0) {
        	
        try {       	

            System.out.println("---MENÚ CATEGORÍA---");
            System.out.println("0. Volver");
            System.out.println("1. Registrar categoría");
            System.out.println("2. Listar categorías");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println();

            

            switch (opcion) {

                case 0 -> System.out.println("Volviendo al menú principal...");

                case 1 -> registrarCategoria();

                case 2 -> listarCategorias();

                default-> System.out.println("Opción no válida.");
            }
        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

			System.out.println("ERROR: Debes introducir un número.");
			sc.nextLine(); //Limpiamos buffer
		}
        }
    }

    private void registrarCategoria() {
    	
    	sc.nextLine(); //Limpiamos buffer
        System.out.print("Introduce el nombre de la categoría: ");
        String Categoria = sc.nextLine();

        Categoria c = new Categoria(Categoria);

        try {
            controlador.registrarCategoria(c);
            System.out.println("Categoría registrada correctamente.");

        } catch (CategoriaInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

  //------------------------------------------------------------------------------------------------------------------
    //Submenu para listar categorías con dos opciones: orden alfabético (compareTo) y orden de inserción (HashMap + Streams)
    private void listarCategorias() {

        int opcion = -1;

        while (opcion != 0) {
        	
            try {

            System.out.println("---LISTAR CATEGORÍAS---");
            System.out.println("0. Volver");
            System.out.println("1. Ordenadas alfabéticamente");
            System.out.println("2. Orden de inserción");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); //Limpiamos buffer
            System.out.println();



            switch (opcion) {

                case 0 -> System.out.println("Volviendo...");

                case 1 -> listarCategoriasOrdenadas(); //Lista ordenada usando compareTo()

                case 2 -> listarCategoriasInsercion(); //Lista sin ordenar usando HashMap + Streams

                default -> System.out.println("Opción no válida.");
            }
        } catch (InputMismatchException e) {//Captura el error si el usuario introduce letras en vez de números

			System.out.println("ERROR: Debes introducir un número.");
			sc.nextLine(); //Limpiamos buffer
            
        }
      }
    }

    //Método para listar categorías ordenadas alfabéticamente mediante compareTo()
    private void listarCategoriasOrdenadas() {

        System.out.println("---CATEGORÍAS ORDENADAS---");

        Iterator<Categoria> it = controlador.listarCategorias().iterator(); //Iterator para recorrer la lista ordenada

        while (it.hasNext()) {
            System.out.println(it.next()); //Imprime cada categoría ordenada
        }
    }

    //Método para listar categorías en orden de inserción
    private void listarCategoriasInsercion() {

        System.out.println("---CATEGORÍAS POR INSERCIÓN---");

        LinkedHashMap<Integer, Categoria> mapaClientes = new LinkedHashMap<>();//LinkedHashMap para mantener el orden de inserción

        controlador.listarCategoriasInsercion().stream()//Paso la lista de categorías a un stream para poder usar forEach
                   .forEach(c -> mapaClientes.put(c.getID_categoria(), c));//Con el forEach obtengo de cada categoría su ID_categoria y su objeto categoría completo para guardarlo en el LinkedHashMap

        //Uso de expresiones lambda para imprimir cada categoría en orden de inserción
        mapaClientes.values().forEach(System.out::println);
    }
    
}
//------------------------------------------------------------------------------------------------------------------