package giis.proyecto.Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;

public class ModeloInformeInstalacion {
	private static Database db = new Database();
	
	
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
	
	
	public static int ObtenerIdInstalacion(String nombreInstalacion) {

		String sql= "SELECT id_instalacion FROM instalaciones WHERE nombre=?";

		List<Object[]>rows=db.executeQueryArray(sql, nombreInstalacion);

		return (int) rows.get(0)[0];

	}
	
	public static int ObtenerNumeroReservas(int idInstalacion, String fi, String ff) {
		String sql="SELECT"
				+" COUNT ( CASE WHEN ?=id_instalacion AND ?<=fecha_inicioReserva AND fecha_inicioReserva<=? then 'numero' end) "
				+" from reservas";
		
		List<Object[]>rows=db.executeQueryArray(sql, idInstalacion,fi,ff);

		return (int) rows.get(0)[0];
	}
	
	public static String ObtenerNumeroActividades(int idInstalacion) {
		String sql= "SELECT numero FROM ocupaciones WHERE ?=id_instalacion";
		
		List<Object[]>rows=db.executeQueryArray(sql, idInstalacion);

		return (String) rows.get(0)[0];
	}
	
	public static String ObtenerHorasReservas(int IdInstalacion) {
		String sql= "SELECT horas FROM ocupaciones2 WHERE ?=id_instalacion";
		
		List<Object[]>rows=db.executeQueryArray(sql, IdInstalacion);

		return (String) rows.get(0)[0];
	}
	
	public static String ObtenerHorasActividades(int IdInstalacion) {
		String sql= "SELECT horas FROM ocupaciones WHERE ?=id_instalacion";
		
		List<Object[]>rows=db.executeQueryArray(sql, IdInstalacion);

		return (String) rows.get(0)[0];
	}
	
	public static String ObtenerIngresosR(int IdInstalacion) {
		String sql="SELECT importe FROM ocupaciones2 WHERE ?=id_instalacion";
		
		List<Object[]>rows=db.executeQueryArray(sql, IdInstalacion);

		return (String) rows.get(0)[0];
	}
	
	public static String ObtenerIngresosA(int IdInstalacion) {
		String sql="SELECT ingresos FROM ocupaciones WHERE ?=id_instalacion";
		
		List<Object[]>rows=db.executeQueryArray(sql, IdInstalacion);

		return (String) rows.get(0)[0];
	}
	
	

	
	
	
	
	public static void CrearInforme(String Instalacion, String numeroReservas, String numeroActividades, String HReservas, String HActividades, String PReservas, String PActividades, String IngresosR, String IngresosA, String IngresosT){
		try {
			String ruta = "src/test/resources/Informe_"+Instalacion+".txt";
			File resguardo= new File(ruta);
			
			if(resguardo.exists()) {
				resguardo.delete();
				FileWriter resguardoE= new FileWriter(resguardo,true);
				resguardoE.write("INFORME "+Instalacion+"\n\n");
				resguardoE.write("Numero de reservas durante el mes: "+numeroReservas+"\n");
				resguardoE.write("Numero de actividades durante el mes: "+numeroActividades+"\n\n");
				resguardoE.write("Numero de horas ocupadas por reservas: "+HReservas+"h\n");
				resguardoE.write("Numero de horas ocupadas por actividades: "+ HActividades+"h \n\n");
				resguardoE.write("% de ocupacion durante el mes para reservas: "+ PReservas+ "%\n");
				resguardoE.write("% de ocupacion durante el mes para actividades: "+ PActividades+"%\n\n");
				
				resguardoE.write("INGRESOS: \n");
				resguardoE.write("Ingresos generados por las reservas: "+ IngresosR+ " €\n");
				resguardoE.write("Ingresos generados por las actividades: "+IngresosA+" €\n");
				resguardoE.write("Ingresos totales generados: "+ IngresosT+ " € \n\n");
				
				resguardoE.write("FIN DEL INFORME");
				resguardoE.close();
			}
			else {
				FileWriter resguardoE= new FileWriter(resguardo,true);
				resguardoE.write("INFORME "+Instalacion+"\n\n");
				resguardoE.write("Numero de reservas durante el mes: "+numeroReservas+"\n");
				resguardoE.write("Numero de actividades durante el mes: "+numeroActividades+"\n\n");
				resguardoE.write("Numero de horas ocupadas por reservas: "+HReservas+"h\n");
				resguardoE.write("Numero de horas ocupadas por actividades: "+ HActividades+"h \n\n");
				resguardoE.write("% de ocupacion durante el mes para reservas: "+ PReservas+ "%\n");
				resguardoE.write("% de ocupacion durante el mes para actividades: "+ PActividades+"%\n\n");
				
				resguardoE.write("INGRESOS: \n");
				resguardoE.write("Ingresos generados por las reservas: "+ IngresosR+ " €\n");
				resguardoE.write("Ingresos generados por las actividades: "+IngresosA+" €\n");
				resguardoE.write("Ingresos totales generados: "+ IngresosT+ " € \n\n");
				
				resguardoE.write("FIN DEL INFORME");
				resguardoE.close();
			}


			

		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
