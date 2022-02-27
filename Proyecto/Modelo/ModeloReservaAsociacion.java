package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import giis.proyecto.Controlador.ControladorReservasAdministracion;

public class ModeloReservaAsociacion {
	private Database db=new Database();
	private ControladorReservasAdministracion CRA;
	
	//SQL para obtener la lista de reservas que entran en conflicto con la nueva que se quiere introducir,
		//se incluye aqui porque debe saberse antes de introducir la reserva actual y machar este dato
	public static final String SQL_LISTA_RESERVAS="SELECT id_reserva, id_instalacion, id_actividad, fecha_InicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva,"
			+" from reservas  where fecha_inicioReserva<=? AND fecha_finReserva>=? order by fecha_inicioReserva";
	
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
				+"(id_instalacion, id_actividad, fecha_inicioReserva, fecha_finReserva, hora_inicioReserva, hora_finReserva) VALUES "
				+"(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertReserva);

			
			/*preparedStatement.setInt(1, CRA.getIDI());
			preparedStatement.setInt(2, CRA.getIDA());
			preparedStatement.setInt(3,CRA.getFI());
			preparedStatement.setInt(4, CRA.getFF());
			preparedStatement.setStrig(5, CRA.getCBD());
			preparedStatement.setString(6, CRA.getCBH());
			*/
			
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 2);
			preparedStatement.setString(3, "18-03-2019");
			preparedStatement.setString(4, "22-03-019");
			preparedStatement.setString(5, "19:00");
			preparedStatement.setString(6, "21:00");

			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e.getMessage());

		}
	}
		
}
