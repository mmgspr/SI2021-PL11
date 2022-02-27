package giis.demo.tkrun;
import java.sql.ResultSet;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class PeriodosInscripcionModel {

	private Database db = new Database();
	
	
	//SQL para ver todos los periodos de inscripcion
	public static final String SQL_TODOS_PERIODOS_INS = "SELECT nombre FROM periodos_inscripcion";
	public List<Object[]> getPeriodosIns(){
		
		return db.executeQueryArray(SQL_TODOS_PERIODOS_INS );
	}
	
	

}
