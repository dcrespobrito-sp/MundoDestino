package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Controlador.EmpleadoControlador;
import DTO.Empleado;

public class EmpleadoView {

    Scanner sc = new Scanner(System.in);
    EmpleadoControlador controlador = new EmpleadoControlador();

    public void menuEmpleado() {
    	
   	 int opcion = -1;//Inicializamos a -1 para que entre al menú, ya que si lo inicializamos a 0 no entraría nunca al menú

        while (opcion != 0) {
        	
          try {
        	  
          
            System.out.println("---MENU EMPLEADO---");
            System.out.println("0. Volver");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar todos los empleados");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                case 1:
                    registrarEmpleado();
                    break;

                case 2:
                    listarEmpleados();
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

    public void registrarEmpleado() {
    	sc.nextLine(); //Limpiar el buffer antes de leer cadenas, ya que se ha leído un entero en el menú

        System.out.print("Nombre completo: ");
        String Nombre_completo = sc.nextLine();

        System.out.print("Cargo: ");
        String Cargo = sc.nextLine();

        System.out.print("Especialidad: ");
        String Especialidad = sc.nextLine();

        System.out.print("Turno: ");
        String Turno = sc.nextLine();

        System.out.print("Años de experiencia: ");
        int Anios_experiencia = sc.nextInt();
        sc.nextLine();//Limpiar el buffer después de leer un entero

        Empleado e = new Empleado(Nombre_completo, Cargo, Especialidad, Turno, Anios_experiencia);

        try {
            controlador.registrarEmpleado(e);
            System.out.println("Empleado registrado correctamente.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Submenú para listar empleados con dos opciones: orden alfabético (compareTo) o orden de inserción (HashMap + Streams)
    public void listarEmpleados() {

        int opcion = -1;

        while (opcion != 0) {
        	
            try {
            	         
            System.out.println("---LISTAR EMPLEADOS---");
            System.out.println("0. Volver");
            System.out.println("1. Ordenados alfabéticamente");
            System.out.println("2. Orden de inserción");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 0:
                    System.out.println("Volviendo...");
                    break;

                case 1:
                    listarEmpleadosOrdenados();
                    break;

                case 2:
                    listarEmpleadosInsercion();
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
    //Lista ordenada usando compareTo()
    private void listarEmpleadosOrdenados() {

        System.out.println("---EMPLEADOS ORDENADOS---");

        Iterator<Empleado> it = controlador.listarEmpleados().iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //Lista sin ordenar usando HashMap + Streams
    private void listarEmpleadosInsercion() {

        System.out.println("---EMPLEADOS POR INSERCIÓN---");

        Map<Integer, Empleado> mapaEmpleados =
                controlador.listarEmpleadosInsercion().stream() //Uso de stream ya que el controlador devuelve una lista, así podemos convertirla a Map con Collectors
                    .collect(Collectors.toMap(
                        e -> e.getID_empleado(), //Clave = ID_empleado
                        e -> e                   //Valor = Empleado
                    ));

        mapaEmpleados.values().forEach(System.out::println);//Uso de lambda para imprimir
    }
}
