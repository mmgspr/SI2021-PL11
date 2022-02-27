package giis.proyecto.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ModeloInscripcionSocio {

private static Database db = new Database();
	
	public static void inscripcionSocio(int idInscr, int idAct, int idSocio, int idNSocio) {
		Connection dbConnection=null;
		PreparedStatement preparedStatement=null;
		
		String insertInscripciones="INSERT INTO inscritos"
				+"(id_inscripcion, id_actividad, id_socio, id_noSocio) VALUES"
				+"(?,?,?,?)";
		try{
			dbConnection = db.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertInscripciones);
			
		
			
			preparedStatement.setInt(1, idInscr);
			preparedStatement.setInt(2, idAct);
			preparedStatement.setInt(3, idSocio);
			preparedStatement.setInt(4, idNSocio);
			
			preparedStatement.executeUpdate();
			dbConnection.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	//Consulta para sacar el id de la inscripcion asociada a una actividad
	public static int idInscripcion(String nombre) {
		
		String sql= "SELECT id_inscripcion FROM actividades WHERE nombre=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, nombre);

		return (int) rows.get(0)[0];
		
	}
	
	//Consulta para sacar el id de un socio a partir del DNI
	public static int idSocio(String dni) {
		
		String sql= "SELECT id_socio FROM socios WHERE dni=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, dni);

		return (int) rows.get(0)[0];
		
	}

	public static int idActividad(String nombre) {
	
		String sql= "SELECT id_actividad FROM actividades WHERE nombre=?";
	
		List<Object[]>rows=db.executeQueryArray(sql, nombre);

		return (int) rows.get(0)[0];
	
	}
	
	//Devuelve el numero de personas apuntadas
	public static int plazasLibres(int idActividad) {
		
		String sql="SELECT COUNT (id_socio) FROM inscritos WHERE id_actividad=?";
		
		List<Object[]>rows=db.executeQueryArray(sql, idActividad);

		return (int) rows.get(0)[0];
	
	}
	
	public static String fechaIniInscripcion(int idInscripcion) {
			
		String sql= "SELECT fechaInicioSocios FROM inscripciones WHERE id_inscripcion=?";
			
		List<Object[]>rows=db.executeQueryArray(sql, idInscripcion);

		return (String) rows.get(0)[0];
			
		}
	
	public static String fechaFinInscripcion(int idInscripcion) {
		
		String sql= "SELECT fechaFinSocios FROM inscripciones WHERE id_inscripcion=?";
			
		List<Object[]>rows=db.executeQueryArray(sql, idInscripcion);

		return (String) rows.get(0)[0];
			
		}
	
	public static int plazas(int idActividad) {
		
		String sql= "SELECT aforo FROM actividades WHERE id_actividad=?";
			
		List<Object[]>rows=db.executeQueryArray(sql, idActividad);

		return (int) rows.get(0)[0];
			
		}
	
	//consulta para saber si un socio esta ya inscrito en una actividad
	public static int estaInscrito(int idS) {

		String sql="SELECT COUNT (id_actividad) FROM inscritos WHERE id_socio=?";

		List<Object[]>rows=db.executeQueryArray(sql, idS);

		return (int) rows.get(0)[0];

	}
	
	//consulta para saber si existe el socio
	public static int existeSocio(String dniSocio) {
		String sql="SELECT COUNT (id_socio) FROM socios WHERE dni=?";

		List<Object[]>rows=db.executeQueryArray(sql, dniSocio);

		return (int) rows.get(0)[0];
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
}
