package giis.demo.tkrun;


import java.util.List;

import giis.demo.util.Database;

public class SesionesModel {
	private Database db = new Database();
	
	
		//Método para obtener siguiente id
		public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_sesion) from sesiones;";
		public long siguienteIdSesion() {
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
			return (long)lista.get(0)[0] + 1;
		}
		
		//Método para instertar una nueva actividad
		public static final String SQL_NUEVA_SESION = "INSERT INTO sesiones(id_sesion, dia, hora_ini, hora_fin, actividad) VALUES (?, ?, ?, ?, ?);";
		public void nuevaSesion(String dia, String hora_ini, String hora_fin, long actividad) {
			long id;
			id = siguienteIdSesion();
			db.executeUpdate(SQL_NUEVA_SESION, id, dia, hora_ini, hora_fin, actividad);
		}
		
		//método para obtener las sesiones de una actividad
		public static final String SQL_SESIONES_ACTIVIDAD= "SELECT dia, hora_ini, hora_fin FROM sesiones WHERE actividad='";
			
		public List<Object[]> getSesionesActividad(String actividad){
			return db.executeQueryArray(SQL_SESIONES_ACTIVIDAD + actividad + "'");
		}
		
		public static final String SQL_ELIMINAR_SESIONES_ACTIVIDAD = "DELETE FROM sesiones WHERE actividad=?";
		public void eliminarSesiones(long actividad) {
			db.executeUpdate(SQL_ELIMINAR_SESIONES_ACTIVIDAD, actividad);
		}
}
