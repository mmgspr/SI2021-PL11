package giis.proyecto.Modelo;


public class ActividadDisplayDTO {
	
	private String id_actividad;
	private String id_instalacion;
	private String nombre;
	private String aforo;
	private String cuota_socio;
	private String cuota_no_socio;
	private String fechaIniActividad;
	private String fechaFinActividad;
	private String id_inscripcion;
	
	
	public String getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(String id_actividad) {
		this.id_actividad = id_actividad;
	}
	
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

	public String getAforo() {
		return aforo;
	}
	public void setAforo(String aforo) {
		this.aforo = aforo;
	}

	public String getCuota_socio() {
		return cuota_socio;
	}
	public void setCuota_socio(String cuota_socio) {
		this.cuota_socio = cuota_socio;
	}

	public String getCuota_no_socio() {
		return cuota_no_socio;
	}
	public void setCuota_no_socio(String cuota_no_socio) {
		this.cuota_no_socio = cuota_no_socio;
	}

	public String getFechaIniActividad() {
		return fechaIniActividad;
	}
	public void setFechaIniActividad(String fechaIniActividad) {
		this.fechaIniActividad = fechaIniActividad;
	}

	public String getFechaFinActividad() {
		return fechaFinActividad;
	}
	public void setFechaFinActividad(String fechaFinActividad) {
		this.fechaFinActividad = fechaFinActividad;
	}

	public String getId_inscripcion() {
		return id_inscripcion;
	}
	public void setId_inscripcion(String id_inscripcion) {
		this.id_inscripcion = id_inscripcion;
	}

	
	public ActividadDisplayDTO() {}
	
	public ActividadDisplayDTO(String rowidActi, String rowidInst, String rowNombre, String rowAforo, String rowCuotaNoSocio, 
			String rowCuotaSocio, String rowFechaIniActividad, String rowFechaFinActividad, String rowidInsc) {
		this.id_actividad = rowidActi;
		this.id_instalacion = rowidInst;
		this.nombre = rowNombre;
		this.aforo = rowAforo;
		this.cuota_no_socio = rowCuotaNoSocio;
		this.cuota_socio = rowCuotaSocio;
		this.fechaIniActividad = rowFechaIniActividad;
		this.fechaFinActividad = rowFechaFinActividad;
		this.id_inscripcion = rowidInsc;
	}
	
	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
}
