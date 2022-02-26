package giis.proyecto.Modelo;

import lombok.Getter;
import lombok.Setter;


public class InstalacionEntity {

	private String id_instalacion;
	private String nombre;
	private String precio;
	private String hora_apertura;
	private String hora_cierre;
	private String plazo_maximo_reserva;
	private String horas_maximas_reserva;
	
	
	public String getId_instalacion() {
		return id_instalacion;
	}
	public void setId_instalacion(String id_instalacion) {
		this.id_instalacion = id_instalacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	public String getHora_apertura() {
		return hora_apertura;
	}
	public void setHora_apertura(String hora_apertura) {
		this.hora_apertura = hora_apertura;
	}
	
	public String getHora_cierre() {
		return hora_cierre;
	}
	public void setHora_cierre(String hora_cierre) {
		this.hora_cierre = hora_cierre;
	}
	
	public String getPlazo_maximo_reserva() {
		return plazo_maximo_reserva;
	}
	public void setPlazo_maximo_reserva(String plazo_maximo_reserva) {
		this.plazo_maximo_reserva = plazo_maximo_reserva;
	}
	
	public String getHoras_maximas_reserva() {
		return horas_maximas_reserva;
	}
	public void setHoras_maximas_reserva(String horas_maximas_reserva) {
		this.horas_maximas_reserva = horas_maximas_reserva;
	}
	

	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
	//En un fichero con este mismo nombre, pero con extension .txt se muestra un ejemplo
}
