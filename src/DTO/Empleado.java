package DTO;

public class Empleado implements Comparable<Empleado> {//Implementa Comparable para ordenar empleados por nombre y ID_empleado

    private int ID_empleado;
    private String Nombre_completo;
    private String Cargo;
    private String Especialidad;
    private String Turno;
    private int Anios_experiencia;

    //Constructor vacío para crear empleados nuevos
    public Empleado() {

    }
    //Constructor SIN ID (para insertar) ya que ID_destino es autoincrement en la base de datos
    public Empleado(String nombre_completo, String cargo,String especialidad, String turno, int anios_experiencia) {

        this.Nombre_completo = nombre_completo;
        this.Cargo = cargo;
        this.Especialidad = especialidad;
        this.Turno = turno;
        this.Anios_experiencia = anios_experiencia;


    }
    
    //Constructor CON ID (para listar)
    public Empleado(int ID_empleado, String nombre_completo, String cargo,String especialidad, String turno, int anios_experiencia) {

        this.ID_empleado = ID_empleado;
        this.Nombre_completo = nombre_completo;
        this.Cargo = cargo;
        this.Especialidad = especialidad;
        this.Turno = turno;
        this.Anios_experiencia = anios_experiencia;

    }
    //Getter y setters

	public int getID_empleado() {
		return ID_empleado;
	}

	public void setID_empleado(int iD_empleado) {
		ID_empleado = iD_empleado;
	}

	public String getNombre_completo() {
		return Nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		Nombre_completo = nombre_completo;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public String getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(String especialidad) {
		Especialidad = especialidad;
	}

	public String getTurno() {
		return Turno;
	}

	public void setTurno(String turno) {
		Turno = turno;
	}

	public int getAnios_experiencia() {
		return Anios_experiencia;
	}

	public void setAnios_experiencia(int anios_experiencia) {
		Anios_experiencia = anios_experiencia;
	}
	
	@Override
    public int compareTo(Empleado otro) {//Método compareTo para ordenar

        int resultado = this.Nombre_completo.compareToIgnoreCase(otro.Nombre_completo);//Primero se compara por nombre (ignorando mayúsculas/minúsculas)

        if (resultado == 0) {//Si empatan por nombre
            resultado = this.ID_empleado - otro.ID_empleado;//Se compara por ID_empleado (orden ascendente)
        }

        return resultado;
    }
	
	//Método toString para imprimir
	@Override
    public String toString() {
        return "Empleado{ ID_empleado=" + ID_empleado + ", Nombre_completo=" + Nombre_completo +
                ", Cargo=" + Cargo + ", Especialidad=" + Especialidad + ", Turno=" + Turno +
                ", Anios_experiencia=" + Anios_experiencia + " }";
    }
}