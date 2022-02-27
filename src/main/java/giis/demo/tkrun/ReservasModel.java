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
}
