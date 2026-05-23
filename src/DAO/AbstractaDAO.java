package DAO;
import java.util.ArrayList;

public abstract class AbstractaDAO<T> {//Clase abstracta que trabajará con una clase "<T>"
	
	//<T> trabajará con un objeto génerico dependiendo de la clase
    public abstract void insertar(T obj);
    public abstract ArrayList<T> listarTodos();
    
}

