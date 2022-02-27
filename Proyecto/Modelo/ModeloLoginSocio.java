package giis.proyecto.Modelo;

import java.util.List;

public class ModeloLoginSocio {
	
	private static Database db = new Database();
	
	
	
	public static int CompruebaSocio(String usuario){
		String sql ="SELECT"
				+" COUNT ( CASE WHEN ?=dni OR ?=correo then 'socio' end)"
				+" from socios";
		
		List<Object[]>rows=db.executeQueryArray(sql, usuario,usuario);

		return (int) rows.get(0)[0];
	}
	
	public static String ObtenerContraseñaSocio (String usuario) {
		String sql= "SELECT contrasena FROM socios WHERE ?=dni OR ?=correo";
		
		List<Object[]>rows=db.executeQueryArray(sql, usuario, usuario);
		
		return (String) rows.get(0)[0];
	}
	
	

}
