package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import giis.demo.tkrun.CarreraDisplayDTO;

public class ModeloListaReservaSocio {
	private static Database db = new Database();

	/*public static List<ReservaDisplayDTO> getListaReservas(int idSocio, Date fechaInicio, Date fechaFinal) {
		String sql = "SELECT id_reserva, id_instalacion,id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, "
				+ "hora_inicioReserva, hora_finReserva from reservas where id_socio=? AND "
				+ "((fecha_inicioReserva>=?) AND (fecha_finReserva<=?)) "
				+ "order by id_reserva";
		String fi = Util.dateToIsoString(fechaInicio);
		String ff = Util.dateToIsoString(fechaFinal);
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, idSocio, fi, ff);
	}*/

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
	
	public static String ObtenerNombre(String socio) {
		String sql = "SELECT nombre from socios WHERE ?=dni OR ?=correo";
		
		List<Object[]>rows=db.executeQueryArray(sql,socio,socio);

		return (String) rows.get(0)[0];
	}
	
	public static String obtenerApellidos(String socio) {
		String sql = "SELECT apellidos from socios WHERE ?=dni OR ?=correo";
		
		List<Object[]>rows=db.executeQueryArray(sql,socio,socio);

		return (String) rows.get(0)[0];
	}
	
	



	public static int ObtenerIdSocio(String dniSocio) {
		String sql= "SELECT id_socio FROM socios WHERE dni=? OR ?=correo";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio, dniSocio);

		return (int) rows.get(0)[0];
	}

	public static int existeSocio(String dniSocio) {
		String sql="SELECT"
				+" COUNT ( CASE WHEN ?=dni then 'existe' end)"
				+" from socios";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio, dniSocio);

		return (int) rows.get(0)[0];
	}

}
