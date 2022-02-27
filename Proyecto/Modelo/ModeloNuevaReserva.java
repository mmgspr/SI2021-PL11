package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ModeloNuevaReserva {
	private Database db=new Database();
	
	
	/*public List<ReservaDisplayDTO> getListaReservas(Date fecha, Date fecha2) {
		String sql = "SELECT id_actividad, id_socio, fecha_inicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva "
				+ "from reservas where fecha_inicioReserva<=? and fecha_finReserva>=? and hora_inicioReserva!=? and hora_finReserva!=? order by id_reserva";
		String f = Util.dateToIsoString(fecha);
		String f2 = Util.dateToIsoString(fecha2);
		return db.executeQueryPojo(ReservaDisplayDTO.class, sql, f, f2);
	}
	*/
	
	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    return formateador.format(ahora);
	}
	
	
	
		//SQL para realizar una nueva reserva
	public void setNuevaReserva() {
		Connection dbConnection=null;
		PreparedStatement preparedStatement= null;

		String insertReserva = "INSERT INTO reservas" 
				+"(id_instalacion, id_actividad, id_socio, fecha_InicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva) VALUES "
				+"(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertReserva);

			
			/*preparedStatement.setInt(1, CNR.getIDI());
			preparedStatement.setInt(2, CNR.getIDA());
			preparedStatement.setInt(3, CNR.getIDS());
			preparedStatement.setInt(4,CNR.getFI());
			preparedStatement.setInt(5, CNR.getFF());
			preparedStatement.setString(6, CNR.getCBD());
			preparedStatement.setString(7, CNR.getCBH());
			*/
			
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 2);
			preparedStatement.setInt(3, 27);
			preparedStatement.setString(4, "18-03-2019");
			preparedStatement.setString(5, "20-03-019");
			preparedStatement.setString(6, "19:00");
			preparedStatement.setString(7, "21:00");


			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e.getMessage());


		}

	}
		
		final String SQL_COMPROBAR="SELECT d_instalacion"
				+"FROM reservas r WHERE id_instalacion=? AND fecha_InicioResrva=? AND fecha_FinReserva=?, AND, hora_inicioReserva=?, AND hora_finReserva=?";
		
		
		
	}

	

