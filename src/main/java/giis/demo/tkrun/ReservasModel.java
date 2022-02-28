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
		
	//Método para instertar una nueva reserva
	public static final String SQL_NUEVA_RESERVA = "INSERT INTO reservas (id_reserva, persona, instalacion, fecha, fecha_reserva) VALUES (?, ?, ?, ?, ?);";
	public void nuevaReserva(int socio, int instalacion, String fecha, String fecha_reserva) {
		long id;
		id = siguienteIdReserva()
;		db.executeUpdate(SQL_NUEVA_RESERVA,id, socio,instalacion, fecha, fecha_reserva);
	}
	
	
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_reserva) from reservas;";
	public long siguienteIdReserva() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
}

