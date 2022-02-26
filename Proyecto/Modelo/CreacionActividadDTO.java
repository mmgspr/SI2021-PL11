package giis.proyecto.Modelo;

public class CreacionActividadDTO {
	private String lastID;
	
	public CreacionActividadDTO() {}
	
	public CreacionActividadDTO(String rowID) {
		this.lastID=rowID;
	}
	
	public String getlastId() {return this.lastID;}

}
