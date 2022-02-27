package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class ModeloContabilidad {
	private static Database db = new Database();

	public static ArrayList<ContabilidadDisplayDTO> muestraContabilidad(Date fechaInicio,Date fechaFin){
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;

		ArrayList<ContabilidadDisplayDTO> ContabilidadList= new ArrayList<>();

		String sql= "SELECT id_contabilidad, usuario, fecha,descripcion,importe, pendiente FROM contabilidad WHERE fecha>=? AND fecha<=?";

		try {
			dbConnection=db.getConnection();
			preparedStatement=dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff= Util.dateToIsoString(fechaFin);

			preparedStatement.setString(1, fi);
			preparedStatement.setString(2, ff);

			ResultSet rs= preparedStatement.executeQuery();

			ContabilidadDisplayDTO CDdto;
			while (rs.next()) {
				CDdto = new ContabilidadDisplayDTO(rs.getInt("id_contabilidad"), rs.getString("usuario"), rs.getString("fecha"), rs.getString("descripcion"), rs.getString("importe"),rs.getString("pendiente"));
				ContabilidadList.add(CDdto);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ContabilidadList;

	}


	public static int CalculaPagado(Date fechaInicio, Date fechaFin) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;



		ResultSet result= null;

		String sql= "SELECT importe FROM contabilidad WHERE fecha>=? AND fecha<=? AND pendiente ='pagado'";
		int valor=0;
		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff= Util.dateToIsoString(fechaFin);

			preparedStatement.setString(1, fi);
			preparedStatement.setString(2, ff);

			result=preparedStatement.executeQuery();



			while(result.next()) {
				valor = valor + Integer.parseInt(result.getString("importe"));
			}
			dbConnection.close();


		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return valor;
	}

	public static int CalculaPendiente(Date fechaInicio, Date fechaFin) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;



		ResultSet result= null;

		String sql= "SELECT importe FROM contabilidad WHERE fecha>=? AND fecha<=? AND pendiente ='pendiente'";
		int valor=0;
		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff= Util.dateToIsoString(fechaFin);

			preparedStatement.setString(1, fi);
			preparedStatement.setString(2, ff);

			result=preparedStatement.executeQuery();



			while(result.next()) {
				valor = valor + Integer.parseInt(result.getString("importe"));
			}
			dbConnection.close();


		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return valor;
	}

	public static int CalculaTotal(Date fechaInicio, Date fechaFin) {
		Connection dbConnection= null;
		PreparedStatement preparedStatement= null;



		ResultSet result= null;

		String sql= "SELECT importe FROM contabilidad WHERE fecha>=? AND fecha<=?";
		int valor=0;
		try {
			dbConnection= db.getConnection();
			preparedStatement= dbConnection.prepareStatement(sql);

			String fi= Util.dateToIsoString(fechaInicio);
			String ff= Util.dateToIsoString(fechaFin);

			preparedStatement.setString(1, fi);
			preparedStatement.setString(2, ff);

			result=preparedStatement.executeQuery();



			while(result.next()) {
				valor = valor + Integer.parseInt(result.getString("importe"));
			}
			dbConnection.close();


		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return valor;
	}

	public static void RealizaCobros(Date fechaInicio,Date fechaFin) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;

		String UpdateContabilidad = "UPDATE contabilidad SET pendiente = 'pagado' WHERE pendiente ='pendiente' AND fecha>=? AND fecha <=?";

		String fi= Util.dateToIsoString(fechaInicio);
		String ff= Util.dateToIsoString(fechaFin);


		try {
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(UpdateContabilidad);

			preparedStatement.setString(1, fi);
			preparedStatement.setString(2, ff);

			preparedStatement.executeUpdate();

			dbConnection.close();

		}
		catch (SQLException e) {
			System.out.print(e.getMessage());
		}

	}




}




