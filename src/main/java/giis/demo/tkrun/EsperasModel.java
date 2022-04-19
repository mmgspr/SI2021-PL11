package giis.demo.tkrun;

import java.util.List;
import giis.demo.util.Database;

public class EsperasModel {

	private Database db = new Database();
	public static final String SQL_TODAS_ESPERAS_SOCIO = "SELECT id_espera, persona, actividad, fecha FROM esperas WHERE persona=? AND fecha>=? AND fecha<=? ORDER BY fecha DESC";
	public List<Object[]> getTodasEsperasSocio(String persona, String ini, String fin){
		
		return db.executeQueryArray(SQL_TODAS_ESPERAS_SOCIO, persona, ini, fin);
	}
	public static final String SQL_TODAS_ESPERAS_SOCIO2 = "SELECT id_espera, actividad FROM esperas WHERE persona=? ORDER BY id_espera DESC";
	public List<Object[]> getTodasEsperasSocio2(String persona, String ini){ 
		
		return db.executeQueryArray(SQL_TODAS_ESPERAS_SOCIO2, persona);
	}
	public static final String SQL_ELIMINAR_ESPERAS_ACTIVIDAD = "DELETE FROM esperas WHERE actividad=?";
	public void eliminarEsperas(long actividad) {
		db.executeUpdate(SQL_ELIMINAR_ESPERAS_ACTIVIDAD, actividad);
	}
}
