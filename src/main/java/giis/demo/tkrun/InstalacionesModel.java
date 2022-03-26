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
	public static final String SQL_TODAS_INSTALACIONES = "SELECT nombre FROM instalaciones";
	
	public List<Object[]> getInstalaciones(){
		
		return db.executeQueryArray(SQL_TODAS_INSTALACIONES );
	}
	
	//SQL para ver todas las instalaciones
		public static final String SQL_TODOS_DEPORTES = "SELECT DISTINCT deporte FROM instalaciones";
		
		public List<Object[]> getDeportes(){
			
			return db.executeQueryArray(SQL_TODOS_DEPORTES );
		}
	
	//SQL para ver todas las instalaciones de un deporte
	public static final String SQL_TODAS_INSTALACIONES_DEPORTE = "SELECT nombre FROM instalaciones WHERE deporte=";
	
	public List<Object[]> getInstalacionesDeporte(String deporte){
		return db.executeQueryArray(SQL_TODAS_INSTALACIONES_DEPORTE+"'"+deporte+"'");
	}
		
	//SQL para obtener el id de una instalacion
	public static final String SQL_ID_INSTALACION = "SELECT id_instalacion FROM instalaciones WHERE nombre=";
	
	public List<Object[]> getIdInstalacion(String nombre){
		return db.executeQueryArray(SQL_ID_INSTALACION+"'"+nombre+"'");	
	}
	
	//obtener el precio de una instalacion
	public static final String SQL_PRECIO = "SELECT precio FROM instalaciones WHERE nombre=";
	
	public String getPrecio(String nombre){
		List<Object[]> lista;
		String precio;
		lista= db.executeQueryArray(SQL_PRECIO+"'"+nombre+"'");	
		precio = lista.get(0)[0].toString();
		return precio;
	}
	
	//obtener el nombre de una instalacion
		public static final String SQL_NOMBRE_INSTALACION = "SELECT nombre FROM instalaciones WHERE id_instalacion=";
		
		public String getNombreInstalacion(String id){
			List<Object[]> lista;
			String nombre;
			lista= db.executeQueryArray(SQL_NOMBRE_INSTALACION+"'"+id+"'");	
			nombre = lista.get(0)[0].toString();
			return nombre;
		}
	
	
	
			
}
