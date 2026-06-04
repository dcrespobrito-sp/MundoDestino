package Vistas;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import Controlador.EmpleadoControlador;
import DTO.Empleado;
import Excepciones.EmpleadoInvalidoException;

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
            System.out.println();


            switch (opcion) {
            
            case 0 -> System.out.println("Volviendo al menú principal...");
            case 1 -> registrarEmpleado();
            case 2 -> listarEmpleados();
            default -> System.out.println("Opción no válida.");
            
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
        } catch (EmpleadoInvalidoException ex) {
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
            sc.nextLine(); //Limpiamos buffer
            System.out.println();


            switch (opcion) {

            case 0 -> System.out.println("Volviendo...");
            case 1 -> listarEmpleadosOrdenados();
            case 2 -> listarEmpleadosInsercion();
            default -> System.out.println("Opción no válida.");
            
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

    //Método para listar empleados en orden de inserción
    private void listarEmpleadosInsercion() {

        System.out.println("---EMPLEADOS POR INSERCIÓN---");

        LinkedHashMap<Integer, Empleado> mapaClientes = new LinkedHashMap<>();//LinkedHashMap para mantener el orden de inserción

        controlador.listarEmpleadosInsercion().stream()//Paso la lista de empleados a un stream para poder usar forEach
                   .forEach(c -> mapaClientes.put(c.getID_empleado(), c));//Con el forEach obtengo de cada empleado su ID_empleado y su objeto empleado completo para guardarlo en el LinkedHashMap

        //Uso de expresiones lambda para imprimir cada empleado en orden de inserción
        mapaClientes.values().forEach(System.out::println);
    }
}
