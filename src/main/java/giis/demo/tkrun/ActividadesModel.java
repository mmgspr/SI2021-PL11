package giis.demo.tkrun;
import java.sql.ResultSet;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class ActividadesModel {
	private Database db = new Database();

	//SQL para ver todas las actividades
		public static final String SQL_TODAS_ACTIVIDADES = "SELECT nombre FROM actividades";
		
		public List<Object[]> getActividades(){
			
			return db.executeQueryArray(SQL_TODAS_ACTIVIDADES);
		}
		
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_actividad) from actividades;";
	public long siguienteIdActividad() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
}
