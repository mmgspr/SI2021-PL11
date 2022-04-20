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
	
	//Método para obtener siguiente id
			public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_espera) from esperas;";
			public long siguienteIdEspera() {
				List<Object[]> lista;
				lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
				return (long)lista.get(0)[0] + 1;
			}
	
	//Método para instertar una nueva inscripcion
			public static final String SQL_NUEVA_ESPERA = "INSERT INTO esperas(id_espera, persona, actividad, fecha) VALUES (?, ?, ?, ?);";
			public void nuevaEspera(String persona, String actividad, String fecha) {
				long id;
				id = siguienteIdEspera();
				db.executeUpdate(SQL_NUEVA_ESPERA,id, persona, actividad, fecha);
			}
			public long nuevaEsperaRetornaId(String persona, String actividad, String fecha) {
				long id;
				id = siguienteIdEspera();
				db.executeUpdate(SQL_NUEVA_ESPERA,id, persona, actividad, fecha);
				return id;
			}
		
			public static final String SQL_PERSONA_ACTIVIDAD_ESP = "SELECT id_espera FROM esperas WHERE actividad=? AND persona=?";
			public boolean personaActividadEsperas(long actividad, String persona) {
				List<Object[]> lista;
				lista = db.executeQueryArray(SQL_PERSONA_ACTIVIDAD_ESP, actividad, persona);
				if(lista.size()==0) {
					return false;
				}
				else return true;
			}
}
