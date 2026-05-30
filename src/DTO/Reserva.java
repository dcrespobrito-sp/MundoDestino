package DTO;

import java.util.Date;

public class Reserva implements Comparable<Reserva> {//Implementa Comparable para ordenar reservas por fecha de salida y ID_reserva

    private int ID_reserva;
    private Date Fecha_reserva;
    private Date Fecha_salida;
    private Date Fecha_regreso;
    private int Numero_viajeros;
    private double Importe_total;
    private String Estado_reserva;
    
    //Relaciones con otras clases
    private int ID_cliente;
    private int ID_empleado;
    private int ID_destino;

    //Constructor vacío para crear reservas nuevos
    public Reserva() {
    	
    }
    //Constructor SIN ID (para insertar)
	public Reserva(Date fecha_reserva, Date fecha_salida, Date fecha_regreso, int numero_viajeros, double importe_total, String estado_reserva,
                   int ID_cliente, int ID_empleado, int ID_destino) {

        this.Fecha_reserva = fecha_reserva;
        this.Fecha_salida = fecha_salida;
        this.Fecha_regreso = fecha_regreso;
        this.Numero_viajeros = numero_viajeros;
        this.Importe_total = importe_total;
        this.Estado_reserva = estado_reserva;
        
        this.ID_cliente = ID_cliente;
        this.ID_empleado = ID_empleado;
        this.ID_destino = ID_destino;
	}
   
    //Constructor CON ID (para listar)
    public Reserva(int ID_reserva, Date fecha_reserva, Date fecha_salida, Date fecha_regreso, int numero_viajeros, double importe_total, String estado_reserva,
                   int ID_cliente, int ID_empleado, int ID_destino) {

        this.ID_reserva = ID_reserva;
        this.Fecha_reserva = fecha_reserva;
        this.Fecha_salida = fecha_salida;
        this.Fecha_regreso = fecha_regreso;
        this.Numero_viajeros = numero_viajeros;
        this.Importe_total = importe_total;
        this.Estado_reserva = estado_reserva;
        
        this.ID_cliente = ID_cliente;
        this.ID_empleado = ID_empleado;
        this.ID_destino = ID_destino;

    }
    
    //Getters y setters
    
	public int getID_reserva() {
		return ID_reserva;
	}

	public void setID_reserva(int iD_reserva) {
		ID_reserva = iD_reserva;
	}

	public Date getFecha_reserva() {
		return Fecha_reserva;
	}

	public void setFecha_reserva(Date fecha_reserva) {
		Fecha_reserva = fecha_reserva;
	}

	public Date getFecha_salida() {
		return Fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		Fecha_salida = fecha_salida;
	}

	public Date getFecha_regreso() {
		return Fecha_regreso;
	}

	public void setFecha_regreso(Date fecha_regreso) {
		Fecha_regreso = fecha_regreso;
	}

	public int getNumero_viajeros() {
		return Numero_viajeros;
	}

	public void setNumero_viajeros(int numero_viajeros) {
		Numero_viajeros = numero_viajeros;
	}

	public double getImporte_total() {
		return Importe_total;
	}

	public void setImporte_total(double importe_total) {
		Importe_total = importe_total;
	}

	public String getEstado_reserva() {
		return Estado_reserva;
	}

	public void setEstado_reserva(String estado_reserva) {
		Estado_reserva = estado_reserva;
	}

	public int getID_cliente() {
		return ID_cliente;
	}

	public void setID_cliente(int iD_cliente) {
		ID_cliente = iD_cliente;
	}

	public int getID_empleado() {
		return ID_empleado;
	}

	public void setID_empleado(int iD_empleado) {
		ID_empleado = iD_empleado;
	}

	public int getID_destino() {
		return ID_destino;
	}

	public void setID_destino(int iD_destino) {
		ID_destino = iD_destino;
	}
	
	@Override
    public int compareTo(Reserva otra) {//Método compareTo para ordenar

        int resultado = this.Fecha_salida.compareTo(otra.Fecha_salida);//Primero se compara por fecha de salida (orden ascendente)

        if (resultado == 0) {//Si empatan por fecha de salida
            resultado = this.ID_reserva - otra.ID_reserva;//Se compara por ID_reserva (orden ascendente)
        }

        return resultado;
    }


    @Override
    public String toString() {
        return "Reserva{ ID_reserva=" + ID_reserva + ", Fecha_reserva=" + Fecha_reserva +", Fecha_salida=" + Fecha_salida + ", Fecha_regreso=" + Fecha_regreso +
               ", Numero_viajeros=" + Numero_viajeros + ", Importe_total=" + Importe_total +", Estado_reserva=" + Estado_reserva +
               ", clienteID=" +ID_cliente +", empleadoID=" + ID_empleado+", destinoID=" + ID_destino;
              
    }

}

