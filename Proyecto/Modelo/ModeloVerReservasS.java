package giis.proyecto.Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

public class ModeloVerReservasS {
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
	
	public static int ObtenerIdSocio(String dniSocio) {
		String sql= "SELECT id_socio FROM socios WHERE dni=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);
		
		return (int) rows.get(0)[0];
	}
	
	public static String ObtenerNombreSocio(String dniSocio) {
		String sql= "SELECT nombre FROM socios WHERE dni=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);
		
		return (String) rows.get(0)[0];
	}
	
	
	public static String ObtenerDNISocio(int idSocio) {
		String sql= "SELECT dni FROM socios WHERE id_socio=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, idSocio);
		
		return (String) rows.get(0)[0];
	}
	
	public static String ObtenerApellidosSocio(String dniSocio) {
		String sql= "SELECT apellidos FROM socios WHERE dni=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);
		
		return (String) rows.get(0)[0];
	}
	
	/**
	 * Obtiene la lista de reservas activas, en forma objetos, para un id de socio dado y un periodo
	 */
	public static List<ReservaDisplayDTO> getListaReservasS(int idSocio, String fechaInicio, String fechaFinal) {
		String sql = "SELECT * FROM reservas where id_socio=? AND "
				+ "fecha_inicioReserva>=? AND fecha_finReserva<=? "
				+ "ORDER BY id_instalacion";
		
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, idSocio, fechaInicio, fechaFinal);
	}
	
	/**
	 * Obtiene la lista de reservas activas, en forma objetos, para un id de socio dado y un id instalacion y un periodo
	 */
	public static List<ReservaDisplayDTO> getListaReservasI(int idSocio, int idInstalacion, String fechaInicio, String fechaFinal) {
		String sql = "SELECT * FROM reservas where id_socio=? AND id_instalacion=?"
				+ "fecha_inicioReserva>=? AND fecha_finReserva<=? "
				+ "ORDER BY id_instalacion";
		
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, idSocio, idInstalacion, fechaInicio, fechaFinal);
	}
	
	/**
	 * Obtiene la informacion de una reserva activa, en forma objeto, para un id de reserva dado
	 */
	public ReservaDisplayDTO getInfoReserva(int id_reserva) {
		String sql = "SELECT id_instalacion, id_actividad, id_socio from reservas where id_reserva=?";
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, id_reserva).get(0);
	}
	/**
	 * Obtiene todos los datos de la reserva con el id indicado
	 */
	public ReservaEntity getReserva(int id) {
		String sql = "SELECT id_reserva, id_instalacion, id_actividad, id_socio, fecha_inicioReserva, "
				+ "fecha_finReserva, hora_inicioReserva, hora_finReserva "
				+ "from reservas where id_reserva=?";
		return db.executeQueryPojo(ReservaEntity.class, sql, id).get(0);
	}

	/**
	 * Obtiene el nombre de una instalacion, en forma string, para un id de instalacion dado
	 */
	public String getNombreInstalacion (String id_instalacion) {
		String sql = "SELECT nombre from instalaciones where id_instalacion=?";
		List<Object[]> list = db.executeQueryArray(sql, id_instalacion);
		if (list.equals(null) || list.size()<1)
			return "";
		else
			return (String)list.get(0)[0];
	}
	/**
	 * Obtiene el nombre de una actividad, en forma string, para un id de actividad dado
	 */
	public String getNombreActividad (String id_actividad) {
		String sql = "SELECT nombre from actividades where id_actividad=?";
		List<Object[]> list = db.executeQueryArray(sql, id_actividad);
		if (list.equals(null) || list.size()<1)
			return "";
		else
			return (String)list.get(0)[0];
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
	
	public static void ObtenerSocios(JComboBox<String> CBSocios) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;
		ResultSet result= null;

		String sql= "SELECT dni FROM socios ORDER BY dni ASC";

		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			result=preparedStatement.executeQuery();

			CBSocios.addItem("Selecione su DNI");
			while(result.next()) {
				CBSocios.addItem(result.getString("dni"));
			}
			
			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	public static ArrayList<ReservaDisplayDTO> muestraReservas(int idSocio, Date fechaInicio, Date fechaFinal){
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;
		
		ArrayList<ReservaDisplayDTO> ReservasList= new ArrayList<>();
		
		/*String sql = "SELECT id_reserva, id_instalacion,id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, "
				+ "hora_inicioReserva, hora_finReserva from reservas where id_socio=? AND "
				+ "((fecha_inicioReserva>=?) AND (fecha_finReserva<=?)) "
				+ "order by id_reserva"; */
		String sql = "SELECT reservas.id_reserva, instalaciones.nombre, reservas.fecha_inicioReserva, reservas.hora_inicioReserva, reservas.hora_finReserva " 
				+ "FROM reservas INNER JOIN instalaciones USING (id_instalacion) WHERE reservas.id_socio=? "  
				+ "AND ((reservas.fecha_inicioReserva>=?) AND (reservas.fecha_finReserva<=?)) order by fecha_inicioReserva";
		
		
		try {
			dbConnection =db.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff=Util.dateToIsoString(fechaFinal);
			
			preparedStatement.setInt(1, idSocio);
			preparedStatement.setString(2, fi);
			preparedStatement.setString(3, ff);

			
			ResultSet rs= preparedStatement.executeQuery();
			

			ReservaDisplayDTO RDdto;
			while (rs.next()) {
				RDdto= new ReservaDisplayDTO(rs.getInt("id_reserva"),rs.getString("nombre"),rs.getString("fecha_inicioReserva"),rs.getString("hora_inicioReserva"),rs.getString("hora_finReserva"));
				//RDdto= new ReservaDisplayDTO(rs.getInt("id_reserva"),rs.getInt("id_instalacion"),rs.getInt("id_actividad"),rs.getInt("id_socio"),rs.getString("fecha_inicioReserva"),rs.getString("fecha_finReserva"),rs.getString("hora_inicioReserva"),rs.getString("hora_finReserva"));
				ReservasList.add(RDdto);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ReservasList;
	}	
	
	public static ArrayList<ReservaDisplayDTO> muestraReservasI(int idSocio, int idInst, Date fechaInicio, Date fechaFinal){
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;
		
		ArrayList<ReservaDisplayDTO> ReservasList= new ArrayList<>();
		
		/*String sql = "SELECT id_reserva, id_instalacion,id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, "
				+ "hora_inicioReserva, hora_finReserva from reservas where id_socio=? AND "
				+ "((fecha_inicioReserva>=?) AND (fecha_finReserva<=?)) "
				+ "order by id_reserva"; */
		String sql = "SELECT reservas.id_reserva, instalaciones.nombre, reservas.fecha_inicioReserva, reservas.hora_inicioReserva, reservas.hora_finReserva " 
				+ "FROM reservas INNER JOIN instalaciones USING (id_instalacion) WHERE reservas.id_socio=? AND reservas.id_instalacion=?"  
				+ "AND ((reservas.fecha_inicioReserva>=?) AND (reservas.fecha_finReserva<=?)) order by fecha_inicioReserva";
		
		
		try {
			dbConnection =db.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff=Util.dateToIsoString(fechaFinal);
			
			preparedStatement.setInt(1, idSocio);
			preparedStatement.setInt(2, idInst);
			preparedStatement.setString(3, fi);
			preparedStatement.setString(4, ff);

			
			ResultSet rs= preparedStatement.executeQuery();
			

			ReservaDisplayDTO RDdto;
			while (rs.next()) {
				RDdto= new ReservaDisplayDTO(rs.getInt("id_reserva"),rs.getString("nombre"),rs.getString("fecha_inicioReserva"),rs.getString("hora_inicioReserva"),rs.getString("hora_finReserva"));
				//RDdto= new ReservaDisplayDTO(rs.getInt("id_reserva"),rs.getInt("id_instalacion"),rs.getInt("id_actividad"),rs.getInt("id_socio"),rs.getString("fecha_inicioReserva"),rs.getString("fecha_finReserva"),rs.getString("hora_inicioReserva"),rs.getString("hora_finReserva"));
				ReservasList.add(RDdto);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ReservasList;
	}	
}
