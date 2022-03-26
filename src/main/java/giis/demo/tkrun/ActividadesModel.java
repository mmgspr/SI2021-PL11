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
	
	//Método para instertar una nueva actividad
			public static final String SQL_NUEVA_ACTIVIDAD = "INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			public void nuevaActividad(String nombre, String descripcion, String aforo, String precio_socio, String precio_no_socio, String fecha_ini, String fecha_fin, String deporte, String instalacion, String periodo_inscripcion) {
				long id;
				id = siguienteIdActividad();
				db.executeUpdate(SQL_NUEVA_ACTIVIDAD,id, nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion);
			}
			public long nuevaActividadRetornaId(String nombre, String descripcion, String aforo, String precio_socio, String precio_no_socio, String fecha_ini, String fecha_fin, String deporte, String instalacion, String periodo_inscripcion) {
				long id;
				id = siguienteIdActividad();
				db.executeUpdate(SQL_NUEVA_ACTIVIDAD,id, nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion);
				return id;
			}
	
	
	//Método para obtener el id de una actividad
	public static final String SQL_ID_ACTIVIDAD = "SELECT id_actividad FROM actividades WHERE nombre=";
	
	public long getIdActividad(String nombre){
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_ID_ACTIVIDAD+"'"+nombre+"'");
		return (long)lista.get(0)[0]; 	
	}
	
	//Método para obtener la fecha inicial de una actividad
		public static final String SQL_FECHA_INICIAL_ACTIVIDAD = "SELECT fecha_ini FROM actividades WHERE nombre=";
		
		public String getFechaIniActividad(String nombre){
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_FECHA_INICIAL_ACTIVIDAD+"'"+nombre+"'");
			return lista.get(0)[0].toString(); 	
		}
		
	//Método para obtener la fecha final de una actividad
		public static final String SQL_FECHA_FINAL_ACTIVIDAD = "SELECT fecha_fin FROM actividades WHERE nombre=";
		
		public String getFechaFinActividad(String nombre){
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_FECHA_FINAL_ACTIVIDAD+"'"+nombre+"'");
			return lista.get(0)[0].toString(); 	
		}
		
	//Método para obtener el nombre de una actividad
			public static final String SQL_NOMBRE_ACTIVIDAD = "SELECT nombre FROM actividades WHERE id_actividad=";
				
			public String getNombreActividad(String id){
				List<Object[]> lista;
				lista = db.executeQueryArray(SQL_NOMBRE_ACTIVIDAD+"'"+id+"'");
				return lista.get(0)[0].toString(); 	
			}
			
	
}
