package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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

            switch (opcion) {

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                case 1:
                    registrarCategoria();
                    break;

                case 2:
                    listarCategorias();
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

            switch (opcion) {

                case 0:
                    System.out.println("Volviendo...");
                    break;

                case 1:
                    listarCategoriasOrdenadas(); //Lista ordenada usando compareTo()
                    break;

                case 2:
                    listarCategoriasInsercion(); //Lista sin ordenar usando HashMap + Streams
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

    //Método para listar categorías ordenadas alfabéticamente mediante compareTo()
    private void listarCategoriasOrdenadas() {

        System.out.println("---CATEGORÍAS ORDENADAS---");

        Iterator<Categoria> it = controlador.listarCategorias().iterator(); //Iterator para recorrer la lista ordenada

        while (it.hasNext()) {
            System.out.println(it.next()); //Imprime cada categoría ordenada
        }
    }

    //Método para listar categorías en orden de inserción usando un HashMap + Streams
    private void listarCategoriasInsercion() {

        System.out.println("---CATEGORÍAS POR INSERCIÓN---");

        //Convertimos la lista del controlador en un Map usando Streams
        Map<Integer, Categoria> mapaCategorias =
                controlador.listarCategoriasInsercion().stream()//Uso de stream ya que el controlador devuelve una lista, así podemos convertirla a Map con Collectors
                    .collect(Collectors.toMap(
                        c -> c.getID_categoria(), //Clave = ID_categoria
                        c -> c                    //Valor = objeto Categoria
                    ));

        //Imprimimos usando lambdas
        mapaCategorias.values().forEach(System.out::println);
    }
}
//------------------------------------------------------------------------------------------------------------------