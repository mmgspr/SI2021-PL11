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

public class ClientesModel {
	
	private Database db = new Database();
	
	
	//SQL para ver todos los id's de los clientes
	public static final String SQL_TODOS_ID = "SELECT id_socio FROM clientes WHERE id_socio IS NOT NULL";
	public List<Object[]> getIdTodos(){
		
		return db.executeQueryArray(SQL_TODOS_ID);
	}
	
	//SQL para comprobar si un id es válido
		public static final String SQL_ID_VALIDO = "SELECT id_socio FROM clientes WHERE id_socio =";
		public boolean validarId(String id){
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_ID_VALIDO+"'"+id+"'");
			if (lista.size() == 0){
				return false;
			}
			return true;
		}
		
		//SQL para comprobar si debe alguna cuota
				public static final String SQL_MOROSO = "SELECT moroso FROM clientes WHERE id_socio =";
				public boolean DebeDinero(String id){
					List<Object[]> lista;
					lista = db.executeQueryArray(SQL_MOROSO+"'"+id+"'");
					if (lista.size() == 0){
						return false;
					}
					return true;
				}
	
	/*
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
	
	*/
	
			
}

