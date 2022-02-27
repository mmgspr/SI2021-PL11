package giis.demo.tkrun;
import java.sql.ResultSet;
import java.util.List;

import giis.demo.util.Database;

public class CosteModel {

	private Database db = new Database();
	
	
	//SQL para ver todas los precios de las instalaciones
	public static final String SQL_TODOS_PRECIOS = "SELECT precio FROM instalaciones";
	public List<Object[]> getCoste(){
		
		return db.executeQueryArray(SQL_TODOS_PRECIOS );
	}
	
	//SQL para ver el precio de las istaaciones de un deporte
		public static final String SQL_TODOS_PRECIOS_INSTALACIONES = "SELECT precio FROM instalaciones WHERE deporte=";
		public List<Object[]> getCosteDeporte(String deporte){
			
			return db.executeQueryArray(SQL_TODOS_PRECIOS+"'"+deporte+"'");
		}
	
}
