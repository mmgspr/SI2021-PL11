package giis.demo.tkrun;
import java.util.Iterator;
import java.util.List;

import giis.demo.util.Database;
public class SesionesModel {
		private Database db = new Database();

		//método para obtener las sesiones de una actividad
		public static final String SQL_SESIONES_ACTIVIDAD= "SELECT dia, hora_ini, hora_fin FROM sesiones WHERE actividad='";
	
		public List<Object[]> getSesionesActividad(String actividad){
			return db.executeQueryArray(SQL_SESIONES_ACTIVIDAD + actividad + "'");
		}
}
