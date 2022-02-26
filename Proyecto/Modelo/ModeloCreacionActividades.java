package giis.proyecto.Modelo;

import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import giis.proyecto.Controlador.ControladorCreacionActividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class ModeloCreacionActividades {
	private static Database db=new Database();
	






	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	




	//SQL para crear una nueva actividad
	public static void  setNuevaActividad(String nombre,int idI, int aforo, String Psocios, String PNsocios, String f1, String f2, String f3, String f4, String f5, String horaI, String horaF) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement1= null;


		String insertActividad = "INSERT INTO actividades" 
				+"(nombre,id_instalacion, aforo, cuota_socio, cuota_no_socio, fechaIniS_ins, fechaFinS_ins, fechaFinNS_ins, fechaIniActividad, fechaFinActividad,hora_inicioActividad, hora_finActividad) VALUES "
				+"(?,?,?,?,?,?,?,?,?,?,?,?)";



		try {
			dbConnection = db.getConnection();
			preparedStatement1 = dbConnection.prepareStatement(insertActividad);



			/*preparedStatement.setString(1, CCA.getNA());
			preparedStatement1.setInt(2, CCA.getAforo());
			preparedStatement1.setString(3,CCA.getPSocio());
			preparedStatement1.setString(4, CCA.getPNSocio());
			preparedStatement1.setString(5, CCA.getIIS());
			preparedStatement1.setString(6, CCA.getFIS());
			preparedStatement1.setString(7, CCA.getFINS());
			preparedStatement1.setString(8, CCA.getIA());
			preparedStatement1.setString(9, CCA.getFA());*/

			preparedStatement1.setString(1, nombre);
			preparedStatement1.setInt(2, idI);
			preparedStatement1.setInt(3, aforo);
			preparedStatement1.setString(4, Psocios);
			preparedStatement1.setString(5, PNsocios);
			preparedStatement1.setString(6, f1);
			preparedStatement1.setString(7, f2);
			preparedStatement1.setString(8, f3);
			preparedStatement1.setString(9, f4);
			preparedStatement1.setString(10, f5);
			preparedStatement1.setString(11, horaI);
			preparedStatement1.setString(12, horaF);


			preparedStatement1.executeUpdate();
			
			dbConnection.close();
		
		}
		catch(SQLException e){
			System.out.print(e.getMessage());


		}


	}

	public static void setNuevaReserva(int idI,int idA,int idS, String f1, String f2, String h1,String h2) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement2= null;
		String insertReservas = "INSERT INTO reservas"
				+"(id_instalacion,id_actividad,id_socio,fecha_InicioReserva,fecha_finReserva,hora_inicioReserva,hora_finReserva)VALUES "
				+"(?,?,?,?,?,?,?)";
		try {
			dbConnection = db.getConnection();
			preparedStatement2 = dbConnection.prepareStatement(insertReservas);

			preparedStatement2.setInt(1, idI);
			preparedStatement2.setNull(2, java.sql.Types.INTEGER);//(2, idA);
			preparedStatement2.setInt(3, idS);
			preparedStatement2.setString(4, f1);
			preparedStatement2.setString(5, f2);
			preparedStatement2.setString(6, h1);
			preparedStatement2.setString(7, h2);

			preparedStatement2.executeUpdate();
			
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
	
	


}