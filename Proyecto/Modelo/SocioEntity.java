package giis.proyecto.Modelo;

import lombok.Getter;
import lombok.Setter;


public class SocioEntity {

	private String id_socio;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private String telefono;
	private String iban;
	private String correo;
	
	
	public String getId_socio() {
		return id_socio;
	}
	public void setId_socio(String id_socio) {
		this.id_socio = id_socio;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
	//En un fichero con este mismo nombre, pero con extension .txt se muestra un ejemplo
}
