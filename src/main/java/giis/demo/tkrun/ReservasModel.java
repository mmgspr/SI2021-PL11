package giis.demo.tkrun;

import java.util.Iterator;
import java.util.List;

import giis.demo.util.Database;

public class ReservasModel {

private Database db = new Database();
	
	// buscar el sentido a db(de momento no tiene ninguno)


	//SQL para ver todas las reservas de una instalacion
	public static final String SQL_RESERVAS_INSTALACION = "SELECT id_reserva FROM reservas WHERE instalacion=";
	public List<Object[]> getReservasInstalacion(String instalacion){
		
		//return db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'");
		return db.executeQueryArray(SQL_RESERVAS_INSTALACION, instalacion);
	}
	
	//SQL para ver si un socio tiene reservada una hora determinada
		public static final String SQL_RESERVAS_INSTALACION_SOCIO = "SELECT id_reserva FROM reservas WHERE fecha_reserva=";
		public boolean getReservasInstalacionSocio(String fecha_reserva, int id_socio){
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_SOCIO+"'"+fecha_reserva+"'");
			if (((int)lista.get(0)[0] == id_socio)){
				return true;
			}
			else {
			    return false;
			}
			
		}
	
	public static final String SQL_RESERVAS_MANU= "SELECT persona, fecha_reserva, actividad FROM reservas WHERE instalacion=";
	public List<Object[]> getReservasManu(long id_instalacion){
		return db.executeQueryArray(SQL_RESERVAS_MANU+ "'"+id_instalacion+"'");
	}
	
	//SQL para comprobar si una instalacion está reservada un día a una hora
	//retorna true si no está reservado y se puede reservar
		public static final String SQL_RESERVADA = " AND fecha_reserva=";
		//public static final String SQL_RESERVADA = "SELECT id_reserva FROM reservas WHERE instalacion=?";
		List<Object[]> lista;
		public boolean comprobarDisponibilidad(String instalacion, String diaHora){
			lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'"+ SQL_RESERVADA+"'"+diaHora+"'");
			
			if (lista.size() == 0) {
				//System.out.println(lista.get(0)[0]);
				return true;
			}
			return false;
		}
		
	//SQL para comprobar si una instalacion está reservada por actividad
	//retorna 0 si puedes reservar, 1 si está reservada por un cliente y puedes tmb, y -1 si no puedes reservar
		public static final String SQL_RES_ACTIVIDAD = " AND actividad<>0";
		List<Object[]> lista1;
		boolean socio;
		public int comprobarDisponibilidadActividad(String instalacion, String diaHora){
			lista1 = db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'"+ SQL_RESERVADA+"'"+diaHora+"'"+ SQL_RES_ACTIVIDAD);
			socio = comprobarDisponibilidad(instalacion, diaHora);
			if (lista1.size() == 0 && socio) {
				//System.out.println(lista.get(0)[0]);
				return 0;
			}
			else if(socio)
				return 1;
			return -1;
		}

	//Método para instertar una nueva reserva
	public static final String SQL_NUEVA_RESERVA = "INSERT INTO reservas (id_reserva, persona, instalacion, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";
	public void nuevaReserva(int socio, int instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
		long id;
		id = siguienteIdReserva();		
		db.executeUpdate(SQL_NUEVA_RESERVA,id, null,instalacion, fecha, fecha_reserva, precio, actividad);
	}
	
	
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_reserva) from reservas;";
	public long siguienteIdReserva() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
	//Método para obtener el nombre de las actividades según su id 
	public static final String SQL_ACTIVIDAD = "SELECT nombre FROM actividades WHERE id_actividad=";
	
	public List<Object[]> getActividad(long id_actividad){
		return db.executeQueryArray(SQL_ACTIVIDAD+id_actividad);	
	}
	
	//Método para eliminar una reserva
		public static final String SQL_ELIMINAR_RESERVA = "DELETE from reservas WHERE instalacion=? AND fecha_reserva=?;";
		public void eliminarReserva(int instalacion, String fecha) {

			db.executeUpdate(SQL_ELIMINAR_RESERVA,instalacion, fecha);
		}
	
			
	//Método para obtener el nombre de las actividades que se encuentran en un periodo determinado	
    public static final String SQL_TODAS_ACTIVIDADES_PERIODO1 = "SELECT nombre, descripcion, fecha_ini, fecha_fin, aforo, precio_socio, precio_no_socio FROM actividades WHERE fecha_fin >=";
	
	public List<Object[]> getActividadPeriodo(String fechaIni, String fechaFin){
		return db.executeQueryArray(SQL_TODAS_ACTIVIDADES_PERIODO1+"'"+fechaIni+"'"+" AND fecha_ini <= '" + fechaFin +"';");	
		
	} 
		
	
}
