package giis.proyecto.Modelo;

public class ReservaDisplayDTO {
	private int id_reserva;
	private int id_instalacion;
	private int id_actividad;
	private int id_socio;
	private String fecha_inicioReserva;
	private String fecha_finReserva;
	private String hora_inicioReserva;
	private String hora_finReserva;
	private String nombreInstalacion;

	public ReservaDisplayDTO(int rowIdR,int rowIdI, int rowIdA, int rowIdS, String rowFechaIni, 
			String rowFechaFin, String rowHoraIni, String rowHoraFin) {
		this.id_reserva=rowIdR;
		this.id_actividad = rowIdA;
		this.id_instalacion=rowIdI;
		this.id_socio=rowIdS;
		this.fecha_inicioReserva= rowFechaIni;
		this.fecha_finReserva= rowFechaFin;
		this.hora_inicioReserva = rowHoraIni;
		this.hora_finReserva = rowHoraFin;
	}
	
	public ReservaDisplayDTO(int rowIdR, String rowNombreInstalacion,String rowFechaIni,String rowHoraIni, String rowHoraFin) {
		this.id_reserva=rowIdR;
		this.nombreInstalacion=rowNombreInstalacion;
		this.fecha_inicioReserva=rowFechaIni;
		this.hora_inicioReserva=rowHoraIni;
		this.hora_finReserva=rowHoraFin;
	}



	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public int getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}

	public int getId_socio() {
		return id_socio;
	}

	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}

	public String getFecha_inicioReserva() {
		return fecha_inicioReserva;
	}

	public void setFecha_inicioReserva(String fecha_InicioReserva) {
		this.fecha_inicioReserva = fecha_InicioReserva;
	}

	public String getFecha_finReserva() {
		return fecha_finReserva;
	}

	public void setFecha_finReserva(String fecha_finReserva) {
		this.fecha_finReserva = fecha_finReserva;
	}

	public String getHora_inicioReserva() {
		return hora_inicioReserva;
	}

	public void setHora_inicioReserva(String hora_inicioReserva) {
		this.hora_inicioReserva = hora_inicioReserva;
	}

	public String getHora_finReserva() {
		return hora_finReserva;
	}

	public void setHora_finReserva(String hora_finReserva) {
		this.hora_finReserva = hora_finReserva;
	}
	
	public String getNombreInstalacion() {
		return nombreInstalacion;
	}
	
	public void setNombreInstalacion(String NombreInstalacion) {
		this.nombreInstalacion=NombreInstalacion;
	}

	public ReservaDisplayDTO() {}




	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
}