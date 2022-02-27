package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

public class ModeloInformeActividad {
	
	private static Database db=new Database();
	

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
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
	
	public static void ObtenerInscripciones(JComboBox<String> CBInscripciones) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;
		ResultSet result= null;

		String sql= "SELECT etiqueta FROM inscripciones ORDER BY etiqueta ASC";

		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			result=preparedStatement.executeQuery();

			CBInscripciones.addItem("Selecione una inscripcion");
			while(result.next()) {
				CBInscripciones.addItem(result.getString("etiqueta"));
			}
			
			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
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
	
	public static String ObtenerPrecioReserva(String nombreInstalacion) {
		String sql="SELECT precio FROM instalaciones WHERE nombre=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, nombreInstalacion);
		
		return (String) rows.get(0)[0];
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
	public static int existeSocio(String dniSocio) {
		String sql="SELECT"
				+" COUNT ( CASE WHEN ?=dni then 'existe' end)"
				+" from socios";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);

		return (int) rows.get(0)[0];
	}


	public static int ObtenerIdSocio(String dniSocio) {
		String sql= "SELECT id_socio FROM socios WHERE dni=?";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);

		return (int) rows.get(0)[0];
	}

	public static int comprobarConflictoReservas(int idInstalacion,String fechaInicio, String fechafin, String horaInicio, String horaCierre) {

		/*String sql="SELECT"
				+" COUNT ( CASE WHEN ?=id_instalacion AND ?=fecha_inicioReserva AND ?=fecha_finReserva AND"
				+" ((hora_inicioReserva BETWEEN ? AND ?) OR (hora_finReserva BETWEEN ? and ?)) then 'ocupado' end)"
				+" from reservas";*/

		String sql="SELECT"
				+" COUNT ( CASE WHEN ?=id_instalacion AND ?=fecha_inicioReserva AND ?=fecha_finReserva AND"
				+" ((?<=hora_inicioReserva AND hora_inicioReserva<?) OR (?<hora_finReserva AND hora_finReserva<=?)) then 'ocupado' end)"
				+" from reservas";


		//String fi=Util.dateToIsoString(fechaInicio);
		//String fc=Util.dateToIsoString(fechafin);


		List<Object[]>rows=db.executeQueryArray(sql, idInstalacion,fechaInicio,fechafin,horaInicio,horaCierre,horaInicio,horaCierre);

		return (int) rows.get(0)[0];

	}

	public static String obtenerNombreSocio(String dniSocio) {
		String sql= "SELECT nombre FROM socios WHERE dni=?";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);

		return (String) rows.get(0)[0];
	}

	public static String obtenerApellidosSocio(String dniSocio) {
		String sql= "SELECT apellidos FROM socios WHERE dni=?";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);

		return (String) rows.get(0)[0];
	}
	
	//consulta que devuelve el numero de socios inscritos en una actividad
	public static int sociosInscritos(int idA) {

		String sql="SELECT COUNT (id_socio) FROM inscritos WHERE (id_actividad=? AND id_socio>0)";

		List<Object[]>rows=db.executeQueryArray(sql, idA);

		return (int) rows.get(0)[0];
	}
	
	//consulta que devuelve el numero de no socios inscritos en una actividad
	public static int noSociosInscritos(int idA) {

		String sql="SELECT COUNT (id_noSocio) FROM inscritos WHERE (id_actividad=? AND id_noSocio>0)";

		List<Object[]>rows=db.executeQueryArray(sql, idA);

		return (int) rows.get(0)[0];
	}
	
	//consulta que devuelve el numero total de usuarios inscritos en una actividad
	public static int inscritos(int idA) {

		String sql="SELECT COUNT (id_actividad) FROM inscritos WHERE id_actividad=?";

		List<Object[]>rows=db.executeQueryArray(sql, idA);

		return (int) rows.get(0)[0];
	}
	
	//consulta que devuelve el idactividad para un periodo
		public static int actividadesP(String f1, String f2, int cont) {

			String sql="SELECT id_actividad FROM actividades WHERE fechaIniActividad>=? and fechaFinActividad<=?";

			List<Object[]>rows=db.executeQueryArray(sql, f1, f2);

			return (int) rows.get(cont)[0];
		}
		
		//consulta que devuelve el numero de actividades en un periodo para usarlo como limite de contador en bucles
		public static int actividadesCount(String f1, String f2) {

			String sql="SELECT COUNT(id_actividad) FROM actividades WHERE fechaIniActividad>=? and fechaFinActividad<=?";

			List<Object[]>rows=db.executeQueryArray(sql, f1, f2);

			return (int) rows.get(0)[0];
		}
		
		public static List<Object[]> actividadesP2(String f1, String f2) {

			String sql="SELECT id_actividad FROM actividades WHERE fechaIniActividad>=? and fechaFinActividad<=?";

			List<Object[]>rows=db.executeQueryArray(sql, f1, f2);

			return rows;
		}
		
		//consulta para obtener el nombre de la actividad a traves de su id
		public static String nombreA(int id) {

			String sql="SELECT nombre FROM actividades WHERE id_actividad=?";

			List<Object[]>rows=db.executeQueryArray(sql, id);

			return (String) rows.get(0)[0];
		}
	
		//consulta para saber el aforo de una actividad
		public static int aforoA(int id) {

			String sql="SELECT aforo FROM actividades WHERE id_actividad=?";

			List<Object[]>rows=db.executeQueryArray(sql, id);

			return (int) rows.get(0)[0];
		}
		
		//consultas para saber el numero de gente en la lista de espera de una actividad
		public static int sociosLE(int id) {

			String sql="SELECT COUNT(id_socio) FROM listaEspera WHERE id_actividad=?";

			List<Object[]>rows=db.executeQueryArray(sql, id);

			return (int) rows.get(0)[0];
		}

		public static int noSociosLE(int id) {

			String sql="SELECT COUNT(id_noSocio) FROM listaEspera WHERE id_actividad=?";

			List<Object[]>rows=db.executeQueryArray(sql, id);

			return (int) rows.get(0)[0];
		}
}
