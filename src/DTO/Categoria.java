package DTO;

import java.util.ArrayList;

public class Categoria implements Comparable<Categoria> {//Implementa Comparable para ordenar categorías por nombre y ID_categoria

    private int ID_categoria;
    private String Categoria;
    
    private ArrayList<Destino> destinos;//Destinos de una categoría

    //Constructor vacío para crear categorías nuevas.
    public Categoria() {
        this.destinos = new ArrayList<>();
    }
    
    //Constructor sin ID (insertar)
	public Categoria(String Categoria) {
		this.Categoria = Categoria;
		this.destinos = new ArrayList<>();
	}
	
    //Constructor con ID (listar)
    public Categoria(int ID_categoria, String Categoria) {
        this.ID_categoria = ID_categoria;
        this.Categoria = Categoria;
        this.destinos = new ArrayList<>();
    }
    
    //Getters y setters
    public int getID_categoria() {
		return ID_categoria;
	}

	public void setID_categoria(int iD_categoria) {
		ID_categoria = iD_categoria;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		this.Categoria = categoria;
	}

	public ArrayList<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(ArrayList<Destino> destinos) {
		this.destinos = destinos;
	}
	
	@Override
    public int compareTo(Categoria otra) {//Método compareTo para ordenar

        int resultado = this.Categoria.compareToIgnoreCase(otra.Categoria);//Primero se compara por nombre (ignorando mayúsculas/minúsculas)

        if (resultado == 0) {//Si empatan por nombre
            resultado = this.ID_categoria - otra.ID_categoria;//Se compara por ID_categoria (orden ascendente)
        }

        return resultado;
    }
	
	//Método toString para imprimir
	@Override
    public String toString() {
        return "Categoria{"+"ID_categoria="+ID_categoria+", categoria="+ Categoria+"}";
    }
}
