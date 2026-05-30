package DTO;

public class Destino implements Comparable<Destino> {//Implementa Comparable para ordenar destinos por nombre y ID_destino

    private int ID_destino;
    private String Nombre;
    private String Pais;
    private String Ciudad;
    private String Descripcion;
    private double Precio_base;
    private int Dias;
    private int Disponibilidad;
    
    private int ID_categoria;//ID de la categoría a la que pertenece el destino ya que un destino solo puede pertenecer a una categoría

    //Constructor vacío para crear destinos nuevos
    public Destino() {

    }
    //Constructor SIN ID (para insertar) ya que ID_destino es autoincrement en la base de datos
    public Destino(String Nombre, String Pais, String Ciudad, String Descripcion,double Precio_base, int Dias, int Disponibilidad, int ID_categoria) {

        this.Nombre = Nombre;
        this.Pais = Pais;
        this.Ciudad = Ciudad;
        this.Descripcion = Descripcion;
        this.Precio_base = Precio_base;
        this.Dias = Dias;
        this.Disponibilidad = Disponibilidad;
        this.ID_categoria = ID_categoria;

    }
    
    //Constructor CON ID (para listar, modificar y eliminar)
    public Destino(int ID_destino, String nombre, String pais, String ciudad, String descripcion, double precio_base, int dias, int disponibilidad, int ID_categoria) {

        this.ID_destino = ID_destino;
        this.Nombre = nombre;
        this.Pais = pais;
        this.Ciudad = ciudad;
        this.Descripcion = descripcion;
        this.Precio_base = precio_base;
        this.Dias = dias;
        this.Disponibilidad = disponibilidad;
        this.ID_categoria = ID_categoria;
    }


    // Getters y setters
	public int getID_destino() {
		return ID_destino;
	}

	public void setID_destino(int iD_destino) {
		ID_destino = iD_destino;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		this.Pais = pais;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		this.Ciudad = ciudad;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

	public double getPrecio_base() {
		return Precio_base;
	}

	public void setPrecio_base(double precio_base) {
		this.Precio_base = precio_base;
	}

	public int getDias() {
		return Dias;
	}

	public void setDias(int dias) {
		this.Dias = dias;
	}

	public int getDisponibilidad() {
		return Disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.Disponibilidad = disponibilidad;
	}	

	public int getID_categoria() {
		return ID_categoria;
	}

	public void setID_categoria(int iD_categoria) {
		ID_categoria = iD_categoria;
	}
	
	@Override
	public int compareTo(Destino otro) {//Método compareTo para ordenar

	    int resultado = this.Nombre.compareToIgnoreCase(otro.Nombre);//Primero se compara por nombre (ignorando mayúsculas/minúsculas)

	    if (resultado == 0) {//Si empatan por nombre
	        resultado = this.ID_destino - otro.ID_destino;//Se compara por ID_destino (orden ascendente)
	    }

	    return resultado;
	}

	
	//Método toString para imprimir
	@Override
    public String toString() {
        return "Destino{ ID_destino=" + ID_destino + ", nombre=" + Nombre + ", pais=" + Pais +
               ", ciudad=" + Ciudad + ", descripcion=" + Descripcion + ", precio_base=" + Precio_base +
               ", dias=" + Dias + ", disponibilidad=" + Disponibilidad + ", Categoria=" +ID_categoria + "}";
    }
}
