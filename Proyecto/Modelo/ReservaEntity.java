package giis.proyecto.Modelo;

import lombok.Getter;
import lombok.Setter;


public class ReservaEntity {

	@Getter
	@Setter
	private String id_reserva;
	@Getter
	@Setter
	private String id_instalacion;
	@Getter
	@Setter
	private String id_actividad;
	@Getter
	@Setter
	private String id_socio;
	@Getter
	@Setter
	private String fecha_inicioReserva;
	@Getter
	@Setter
	private String fecha_finReserva;
	@Getter
	@Setter
	private String hora_inicioReserva;
	@Getter
	@Setter
	private String hora_finReserva;
	
	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
	//En un fichero con este mismo nombre, pero con extension .txt se muestra un ejemplo
}
