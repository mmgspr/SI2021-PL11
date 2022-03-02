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
	
	//SQL para obtener el id de un periodo de inscripcion
		public static final String SQL_ID_PERIODO_INS = "SELECT id_periodo_inscripcion FROM periodos_inscripcion WHERE nombre=";
		
		public List<Object[]> getIdPeriodoIns(String nombre){
			return db.executeQueryArray(SQL_ID_PERIODO_INS+"'"+nombre+"'");	
		}
	
	//Método para instertar un nuevo periodo de inscripcion
		public static final String SQL_NUEVO_PERIODO_INS = "INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre, descripcion, fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES (?, ?, ?, ?, ?, ?);";
		public void nuevoPeriodoIns(String nombre, String descripcion, String fecha_ini_soc, String fecha_fin_soc, String fecha_fin_no_soc) {
			long id;
			id = siguienteIdPeriodoIns();
			db.executeUpdate(SQL_NUEVO_PERIODO_INS,id, nombre, descripcion, fecha_ini_soc, fecha_fin_soc, fecha_fin_no_soc);
		}
	
		//Método para obtener siguiente id
		public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_periodo_inscripcion) from periodos_inscripcion;";
		public long siguienteIdPeriodoIns() {
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
			return (long)lista.get(0)[0] + 1;
		}
	
	

}
