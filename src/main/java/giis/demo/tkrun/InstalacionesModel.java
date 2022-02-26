package giis.demo.tkrun;
import java.sql.ResultSet;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

/**
 * Acceso a los datos de las instalaciones
 *  * @author danig
 *
 */

public class InstalacionesModel {
	
	private Database db = new Database();
	
	
	//SQL para ver todas las instalaciones
	public static final String SQL_TODAS_INSTALACIONES = "SELECT nombre FROM instalacion";
	public List<Object[]> getInstalaciones(){
		
		return db.executeQueryArray(SQL_TODAS_INSTALACIONES );
	}
	
	//SQL para ver todas las instalaciones de un deporte
		public static final String SQL_TODAS_INSTALACIONES_DEPORTE = "SELECT nombre FROM instalacion WHERE deporte=";
		public List<Object[]> getInstalacionesDeporte(String deporte){
			
			return db.executeQueryArray(SQL_TODAS_INSTALACIONES+"'"+deporte+"'");
		}
	
	
	
			
}
