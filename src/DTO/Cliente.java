package DTO;

import java.util.ArrayList;

public class Cliente  implements Comparable<Cliente> {//Implementa Comparable para ordenar clientes por nombre y ID_cliente

    private int ID_cliente;
    private String Nombre_completo;
    private String DNI;
    private String Telefono;
    private String Correo;
    private String Direccion;
    private String Pasaporte;

    private ArrayList<Reserva> reservas;

    // Constructor vacío
    public Cliente() {
        this.reservas = new ArrayList<>();
    }

    //Constructor SIN ID (para insertar) ya que ID_destino es autoincrement en la base de datos
    public Cliente(String nombre_completo, String dni, String telefono, String correo, String direccion, String pasaporte) {
        this.Nombre_completo = nombre_completo;
        this.DNI = dni;
        this.Telefono = telefono;
        this.Correo = correo;
        this.Direccion = direccion;
        this.Pasaporte = pasaporte;
        this.reservas = new ArrayList<>();
    }

    //Constructor CON ID (para listar)
    public Cliente(int ID_cliente, String nombre_completo, String dni, String telefono, String correo, String direccion, String pasaporte) {
        this.ID_cliente = ID_cliente;
        this.Nombre_completo = nombre_completo;
        this.DNI = dni;
        this.Telefono = telefono;
        this.Correo = correo;
        this.Direccion = direccion;
        this.Pasaporte = pasaporte;
        this.reservas = new ArrayList<>();
    }
    
    //Getters y Setters

	public int getID_cliente() {
		return ID_cliente;
	}

	public void setID_cliente(int iD_cliente) {
		ID_cliente = iD_cliente;
	}

	public String getNombre_completo() {
		return Nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		Nombre_completo = nombre_completo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getPasaporte() {
		return Pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		Pasaporte = pasaporte;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	@Override
    public int compareTo(Cliente otro) {//Método compareTo para ordenar

        int resultado = this.Nombre_completo.compareToIgnoreCase(otro.Nombre_completo);//Primero se compara por nombre (ignorando mayúsculas/minúsculas)

        if (resultado == 0) {//Si empatan por nombre
            resultado = this.ID_cliente - otro.ID_cliente;//Se compara por ID_cliente (orden ascendente)
        }

        return resultado;
    }
	
	//Método toString para imprimir
    @Override
    public String toString() {
        return "Cliente{ ID_cliente="+ID_cliente+", nombre_completo="+Nombre_completo+", DNI="+DNI+", telefono="+Telefono+", correo="+Correo+
        		", direccion="+Direccion+", pasaporte="+Pasaporte+"}";
    }

}
