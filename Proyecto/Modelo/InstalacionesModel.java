package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;

//import org.apache.commons.dbutils.handlers.columns.TimestampColumnHandler;

import giis.proyecto.Modelo.Util;
//import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.Database;
/**
 * Acceso a los datos de instalaciones, 
 * utilizado como modelo para el ejemplo de swing y para las pruebas unitarias y de interfaz de usuario.
 * 
 * <br/>En los metodos de este ejemplo toda la logica de negocio se realiza mediante una unica query sql por lo que siempre
 * se utilizan los metodos de utilidad en la clase Database que usan apache commons-dbutils y controlan la conexion. 
 * En caso de que en un mismo metodo se realicen diferentes queries se deberia controlar la conexion desde esta clase 
 * (ver como ejemplo la implementacion en Database).
 * 
 * <br/>Si utilizase algún otro framework para manejar la persistencia, la funcionalidad proporcionada por esta clase sería la asignada
 * a los Servicios, Repositorios y DAOs.
 */
public class InstalacionesModel {
	private static Database db = new Database();

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	/**
	 * Obtiene un objeto InstalacionEntity con los valores de la instalacion que tiene el id indicado
	 * @param id_instalacion
	 * @return
	 */
	public static InstalacionEntity getInstalacion(String id_instalacion) {
		if (id_instalacion == null || id_instalacion.equals("")) 
			return null;
		else {
			String sql = "SELECT * from instalaciones where id_instalacion=?";
			List<InstalacionEntity> list = db.executeQueryPojo(InstalacionEntity.class, sql, id_instalacion);
			if (list.equals(null) || list.size()<1)	
				return null;
			else
				return list.get(0);
		}
	}
	/**
	 * Obtiene un objeto InstalacionEntity con los valores de la instalacion que tiene el id indicado
	 * @param id_instalacion
	 * @return
	 */
	public static InstalacionEntity getInstalacion(int id_instalacion) {
		return getInstalacion(Integer.toString(id_instalacion));
	}
	/**
	 * Obtiene un objeto InstalacionEntity con los valores de la instalacion que tiene el nombre indicado
	 * @param id_instalacion
	 * @return
	 */
	public static InstalacionEntity getInstalacion_nombre(String nombre_instalacion) {
		if (nombre_instalacion == null || nombre_instalacion.equals("")) 
			return null;
		else {
			return getInstalacion(ObtenerIdInstalacion(nombre_instalacion));
		}
	}
	
	public static int ObtenerIdInstalacion(String nombreInstalacion) {
		
		String sql= "SELECT id_instalacion FROM instalaciones WHERE nombre=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, nombreInstalacion);

		return (int) rows.get(0)[0];
		
	}
	
	public static int ObtenerHorasMaximasReserva(String nombreInstalacion) {
		String sql="SELECT horas_maximas_reserva FROM instalaciones WHERE nombre=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, nombreInstalacion);

		return (int) rows.get(0)[0];
		
	}
	
	public static boolean comprobarPlazoMaximo(String nombreInstalacion,String fecha) {
		
		String sql="SELECT plazo_maximo_reserva FROM instalaciones WHERE nombre=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, nombreInstalacion);

		int dias= (int) rows.get(0)[0];
		Date fechaSelecion=Util.isoStringToDate(fecha);
		Date fechaaActual=Util.isoStringToDate(getFechaActual());
		
		Date nuevaFecha = new Date();

        Calendar cal = Calendar.getInstance(); 
        cal.setTime(fechaaActual); 
        cal.add(Calendar.DATE, dias);
        nuevaFecha = cal.getTime();
        
        System.out.println(nuevaFecha);
        
        if(fechaSelecion.after(nuevaFecha)) {
        	return false;
        }
        else {
        	return true;
        }
	}

	/**
	 * Obtiene el nombre de una instalacion, en forma string, para un id de instalacion dado
	 */
	public String getNombreInstalacion (String id_instalacion) {
		if (id_instalacion == null || id_instalacion.equals("")) 
			return null;
		else {
			String sql = "SELECT nombre from instalaciones where id_instalacion=?";
			List<Object[]> list = db.executeQueryArray(sql, id_instalacion);
			if (list.equals(null) || list.size()<1)	
				return "";
			else
				return (String)list.get(0)[0];
		}
	}
	public String getNombreInstalacion (int id_instalacion) {
		String id_inst = Integer.toString(id_instalacion);
		return this.getNombreInstalacion(id_inst);
	}
	
	
	public static void ObtenerInstalaciones(JComboBox<String> CBInstalaciones) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;
		ResultSet result= null;

		String sql= "SELECT nombre FROM instalaciones ORDER BY nombre ASC";

		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			result=preparedStatement.executeQuery();

			CBInstalaciones.addItem("Selecione una instalacion");
			while(result.next()) {
				CBInstalaciones.addItem(result.getString("nombre"));
			}
			
			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

}
