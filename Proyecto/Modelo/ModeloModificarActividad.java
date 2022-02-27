package giis.proyecto.Modelo;

import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import giis.proyecto.Controlador.ControladorCreacionActividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModeloModificarActividad {
	private static Database db=new Database();
	
	//SQL para modificar actividad
		public static void  update(String nombre,int idI, int aforo, String Psocios, String PNsocios, String f1, String f2, int idInscr, int idA) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement1= null;


			String sql = "UPDATE actividades SET nombre=?, id_instalacion=?, aforo=?, cuota_socio=?, cuota_no_socio=?, "
					+ "fechaIniActividad=?, fechaFinActividad=?, id_inscripcion=? WHERE id_actividad=?";



			try {
				dbConnection = db.getConnection();
				preparedStatement1 = dbConnection.prepareStatement(sql);

				preparedStatement1.setString(1, nombre);
				preparedStatement1.setInt(2, idI);
				preparedStatement1.setInt(3, aforo);
				preparedStatement1.setString(4, Psocios);
				preparedStatement1.setString(5, PNsocios);
				preparedStatement1.setString(6, f1);
				preparedStatement1.setString(7, f2);
				preparedStatement1.setInt(8, idInscr);
				preparedStatement1.setInt(9, idA);


				preparedStatement1.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
		}
		
		//sql para eliminar los horarios asociados a esa actividad
		public static void delete(int idA) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement2= null;

			String sql="DELETE FROM horario WHERE id_actividad=?";
			
			try {
				dbConnection = db.getConnection();
				preparedStatement2 = dbConnection.prepareStatement(sql);

				preparedStatement2.setInt(1, idA);


				preparedStatement2.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
		}
		
		//sql para borrar las reservas asociadas a esa actividad
		public static void deleteR(int idA) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement2= null;

			String sql="DELETE FROM reservas WHERE id_actividad=?";
			
			try {
				dbConnection = db.getConnection();
				preparedStatement2 = dbConnection.prepareStatement(sql);

				preparedStatement2.setInt(1, idA);


				preparedStatement2.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
			}
		}
		
		//sql para crear los nuevos horarios
		/*public static void  setNuevoHorario(int idA, String dia, String horaIni, String horaFin) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement1= null;


			String insertActividad = "INSERT INTO horario" 
					+"(id_actividad, dia, horaInicio, horaFin) VALUES "
					+"(?,?,?,?)";



			try {
				dbConnection = db.getConnection();
				preparedStatement1 = dbConnection.prepareStatement(insertActividad);


				preparedStatement1.setInt(1, idA);
				preparedStatement1.setString(2, dia);
				preparedStatement1.setString(3, horaIni);
				preparedStatement1.setString(4, horaFin);



				preparedStatement1.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
				System.out.print(e.getMessage());


			}
		}*/
		
		//consulta para sacar el id_instalacion a partir de su nombre
		public static int ObtenerIdInstalacion(String nombre) {
			String sql= "SELECT id_instalacion FROM instalaciones WHERE nombre=?";

			List<Object[]>rows=db.executeQueryArray(sql, nombre);

			return (int) rows.get(0)[0];
		}
		
		//consulta para sacar el id_inscripcion a partir de su etiqueta
		public static int ObtenerIdInscripcion(String etiqueta) {
			String sql= "SELECT id_inscripcion FROM inscripciones WHERE etiqueta=?";

			List<Object[]>rows=db.executeQueryArray(sql, etiqueta);

			return (int) rows.get(0)[0];
		}
		
		
		//consulta para rellenar el combobox de idactividades
		public static void ObtenerIDActividades(JComboBox<Integer> CBIDActividades) {
			Connection dbConnection= null;
			PreparedStatement preparedStatement= null;
			ResultSet result= null;

			String sql= "SELECT id_actividad FROM actividades ORDER BY id_actividad ASC";

			try {
				dbConnection= db.getConnection();
				preparedStatement= dbConnection.prepareStatement(sql);

				result=preparedStatement.executeQuery();

				//CBIDActividades.addItem("Selecione una actividad");
				while(result.next()) {
					CBIDActividades.addItem(result.getInt("id_actividad"));
				}
				
				dbConnection.close();
			}
			catch (SQLException e) {
				System.out.print(e.getMessage());
			}
		}
		
		public static String getFechaActual() {
			Date ahora = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			return formateador.format(ahora);
		}
		

		/**
	     * Permite convertir un String en fecha (Date).
	     * @param fecha Cadena de fecha yyyy/mm/dd
	     * @return Objeto Date
	     */
	    public static Date ParseFecha(String fecha)
	    {
	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaDate = null;
	        try {
	            fechaDate = formato.parse(fecha);
	        } 
	        catch (ParseException ex) 
	        {
	            System.out.println(ex);
	        }
	        return fechaDate;
	    }

		//SQL para crear una nueva actividad
		public static void  setNuevaActividad(int idI, String nombre, int aforo, String Psocios, String PNsocios, String f1, String f2, int idInscr) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement1= null;


			String insertActividad = "INSERT INTO actividades" 
					+"(id_instalacion, nombre, aforo, cuota_socio, cuota_no_socio, fechaIniActividad, fechaFinActividad, "
					+ "id_inscripcion) VALUES "
					+"(?,?,?,?,?,?,?,?)";



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

				preparedStatement1.setInt(1, idI);
				preparedStatement1.setString(2, nombre);
				preparedStatement1.setInt(3, aforo);
				preparedStatement1.setString(4, Psocios);
				preparedStatement1.setString(5, PNsocios);
				preparedStatement1.setString(6, f1);
				preparedStatement1.setString(7, f2);
				preparedStatement1.setInt(8, idInscr);


				preparedStatement1.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
				System.out.print(e.getMessage());


			}
		}
		
		public static void  setNuevoHorario(int idA, String dia, String horaIni, String horaFin) {
			Connection dbConnection=null;
			PreparedStatement preparedStatement1= null;


			String insertActividad = "INSERT INTO horario" 
					+"(id_actividad, dia, horaInicio, horaFin) VALUES "
					+"(?,?,?,?)";



			try {
				dbConnection = db.getConnection();
				preparedStatement1 = dbConnection.prepareStatement(insertActividad);


				preparedStatement1.setInt(1, idA);
				preparedStatement1.setString(2, dia);
				preparedStatement1.setString(3, horaIni);
				preparedStatement1.setString(4, horaFin);



				preparedStatement1.executeUpdate();
				
				dbConnection.close();
			
			}
			catch(SQLException e){
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
		
			
		public static int ObtenerIdActividad(String nombreActividad) {
			
			String sql= "SELECT id_actividad FROM actividades WHERE nombre=?";
			
			List<Object[]>rows=db.executeQueryArray(sql, nombreActividad);
			return (int) rows.get(0)[0];
			
		}
		
}
