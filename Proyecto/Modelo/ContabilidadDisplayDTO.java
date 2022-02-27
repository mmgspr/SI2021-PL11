package giis.proyecto.Modelo;

public class ContabilidadDisplayDTO {
	private int id_contabilidad;
	private String usuario;
	private String importe;
	private String descripcion;
	private String pendiente;
	private String fecha;
	
	
	public ContabilidadDisplayDTO(int rowIdC,String rowUsuario, String rowFecha,
			String rowDescripcion,String rowImporte,String rowPendiente) {
		this.id_contabilidad=rowIdC;
		this.usuario=rowUsuario;
		this.importe=rowImporte;
		this.descripcion=rowDescripcion;
		this.pendiente=rowPendiente;
		this.fecha=rowFecha;
	}
	
	public int getId_contabilidad() {
		return id_contabilidad;
	}
	
	public void setId_contabilidad(int id_contabilidad) {
		this.id_contabilidad=id_contabilidad;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario=usuario;
	}
	
	public String getImporte() {
		return importe;
	}
	
	public void setImporte(String importe) {
		this.importe=importe;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public String getPendiente() {
		return pendiente;
	}
	
	public void setPendiente(String pendiente) {
		this.pendiente=pendiente;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha=fecha;
	}
	
}
