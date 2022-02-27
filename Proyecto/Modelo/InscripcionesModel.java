package giis.proyecto.Modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class InscripcionesModel {
	private static Database db = new Database();
	
	
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	public static void nuevaInscripcion(String etiqueta, String fechaInicioS,String fechaFinS,String fechaFinNS) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;
		
		String insertInscripciones="INSERT INTO inscripciones"
				+"(etiqueta, fechaInicioSocios, fechaFinSocios, fechaFinNSocios) VALUES"
				+"(?,?,?,?)";
		try{
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertInscripciones);
			
		
			
			preparedStatement.setString(1, etiqueta);
			preparedStatement.setString(2, fechaInicioS);
			preparedStatement.setString(3, fechaFinS);
			preparedStatement.setString(4, fechaFinNS);
			
			preparedStatement.executeUpdate();
			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	public InscripcionEntity getInscripcion(String id_inscripcion) {
		if (id_inscripcion == null || id_inscripcion.equals("")) 
			return null;
		else {
			String sql = "SELECT id_inscripcion, etiqueta, fechaInicioSocios, fechaFinSocios, fechaFinNSocios "
					+ "from inscripciones where id_inscripcion=?";
			return db.executeQueryPojo(InscripcionEntity.class, sql, id_inscripcion).get(0);
		}
	}


}
