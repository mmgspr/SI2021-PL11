package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;

//import org.apache.commons.dbutils.handlers.columns.TimestampColumnHandler;

import giis.proyecto.Modelo.Util;
//import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.Database;
/**
 * Acceso a los datos de reservas e inscripciones, 
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
public class ModeloReservasSocios {
	private static Database db = new Database();

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
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

	public static void CrearResguardo(String dni,String instalacion, String fecha, String horaInicio, String horaFin, String nombre, String apellidos){
		try {
			String ruta = "src/test/resources/resguardo"+dni+".txt";
			File resguardo= new File(ruta);
			
			if(resguardo.exists()) {
				resguardo.delete();
				FileWriter resguardoE= new FileWriter(resguardo,true);
				resguardoE.write("RESERVA\n");
				resguardoE.write("Instalacion Reservada: "+instalacion+"\n");
				resguardoE.write("Fecha de Reserva: "+fecha+"\n");
				resguardoE.write("De: "+horaInicio+" a "+horaFin+"\n");
				resguardoE.write("Nombre: "+nombre+" "+apellidos+"\n");
				resguardoE.write("DNI: "+dni+"\n\n");
				resguardoE.write("GRACIAS POR SU RESERVA");
				resguardoE.close();
			}
			else {
				FileWriter resguardoE= new FileWriter(resguardo,true);
				resguardoE.write("RESERVA\n");
				resguardoE.write("Instalacion Reservada: "+instalacion+"\n");
				resguardoE.write("Fecha de Reserva: "+fecha+"\n");
				resguardoE.write("De: "+horaInicio+" a "+horaFin+"\n");
				resguardoE.write("Nombre: "+nombre+" "+apellidos+"\n");
				resguardoE.write("DNI: "+dni+"\n\n");
				resguardoE.write("GRACIAS POR SU RESERVA");
				resguardoE.close();
			}


			

		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Obtiene la lista de reservas activas, en forma objetos, para un id de instalación dado, a partir de una fecha
	 */
	public List<ReservaDisplayDTO> getListaReservas(int idInstalacion, Date fecha) {
		String sql = "SELECT id_reserva, id_instalacion, id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva "
				+ "from reservas where id_instalacion=? and fecha_inicioReserva>=? order by id_reserva";
		String f = Util.dateToIsoString(fecha);
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, idInstalacion, f);
	}
	// -----TODO-----
	/**
	 * Obtiene la lista de reservas activas, en forma objetos, para un nombre de instalación dado, a partir de una fecha
	 */
	public List<ReservaDisplayDTO> getListaReservas(String nombreInstalacion, Date fecha) {
		String sql = "SELECT id_instalacion from instalaciones where nombre=?";
		List<?> instalacion = db.executeQueryArray(sql, nombreInstalacion);
		int id = Integer.parseInt((String) instalacion.get(0));

		return getListaReservas(id, fecha);
	}
	public static List<ReservaDisplayDTO> getListaReservas(int idInstalacion, Date fechaInicio, Date fechaFinal) {
		String sql = "SELECT id_reserva, id_instalacion, id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva "
				+ "from reservas where id_instalacion=? AND ((fecha_inicioReserva BETWEEN ? and ?) "
				+ "OR (fecha_finReserva BETWEEN ? and ?)) order by id_reserva";
		String fi = Util.dateToIsoString(fechaInicio);
		String ff = Util.dateToIsoString(fechaFinal);
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, idInstalacion, fi, ff, fi, ff);
	}

	public static void setNuevaReserva(int idI,int idS, String f1, String h1,String h2) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;

		String insertReserva= "INSERT INTO reservas"
				+"(id_instalacion,id_actividad,id_socio,fecha_InicioReserva,fecha_finReserva,hora_inicioReserva,hora_finReserva) VALUES "
				+"(?,null,?,?,?,?,?)";
		try {
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertReserva);

			preparedStatement.setInt(1, idI);
			preparedStatement.setInt(2, idS);
			preparedStatement.setString(3, f1);
			preparedStatement.setString(4, f1);
			preparedStatement.setString(5, h1);
			preparedStatement.setString(6, h2);

			preparedStatement.executeUpdate();

			dbConnection.close();

		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}


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

	public static void ObtenerActividades(JComboBox<String> CBActividades) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;
		ResultSet result= null;

		String sql= "SELECT nombre FROM actividades ORDER BY nombre ASC";

		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			result=preparedStatement.executeQuery();

			CBActividades.addItem("Selecione una actividad");
			while(result.next()) {
				CBActividades.addItem(result.getString("nombre"));
			}

			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}





	/**
	 * Obtiene todos los datos de la reserva con el id indicado
	 */
	public ReservaEntity getReserva(int id) {
		String sql = "SELECT id_reserva, id_instalacion, id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, "
				+ "hora_inicioReserva, hora_finReserva from reservas where id_reserva=?";
		return db.executeQueryPojo(ReservaEntity.class, sql, id).get(0);
	}

}
