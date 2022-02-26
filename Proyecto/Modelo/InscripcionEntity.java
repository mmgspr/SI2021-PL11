package giis.proyecto.Modelo;

import lombok.Getter;
import lombok.Setter;


public class InscripcionEntity {

	private String id_inscripcion;
	private String etiqueta;
	private String fechaInicioSocios;
	private String fechaFinSocios;
	private String fechaFinNSocios;
	
	
	public String getId_inscripcion() {
		return id_inscripcion;
	}
	public void setId_inscripcion(String id_inscripcion) {
		this.id_inscripcion = id_inscripcion;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getFechaInicioSocios() {
		return fechaInicioSocios;
	}
	public void setFechaInicioSocios(String fechaInicioSocios) {
		this.fechaInicioSocios = fechaInicioSocios;
	}
	
	public String getFechaFinSocios() {
		return fechaFinSocios;
	}
	public void setFechaFinSocios(String fechaFinSocios) {
		this.fechaFinSocios = fechaFinSocios;
	}
	
	public String getFechaFinNSocios() {
		return fechaFinNSocios;
	}
	public void setFechaFinNSocios(String fechaFinNSocios) {
		this.fechaFinNSocios = fechaFinNSocios;
	}
	
	

	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
	//En un fichero con este mismo nombre, pero con extension .txt se muestra un ejemplo
}
