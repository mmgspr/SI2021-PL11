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
	
}
