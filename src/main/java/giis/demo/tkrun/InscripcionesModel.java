package giis.demo.tkrun;

import java.util.List;

import giis.demo.util.Database;

public class InscripcionesModel {
	private Database db = new Database();
	
	//SQL para ver todas las inscripciones de un socio entre dos fechas
		public static final String SQL_TODAS_INSCRIPCIONES_SOCIO = "SELECT id_inscripcion, persona, actividad, fecha FROM inscripciones WHERE persona=? AND fecha>=? AND fecha<=? ORDER BY fecha DESC";
		public List<Object[]> getTodasInscripcionesSocio(String persona, String ini, String fin){
			
			return db.executeQueryArray(SQL_TODAS_INSCRIPCIONES_SOCIO, persona, ini, fin);
		}
		public static final String SQL_TODAS_INSCRIPCIONES_SOCIO2 = "SELECT id_inscripcion, actividad FROM inscripciones WHERE persona=? ORDER BY id_inscripcion DESC";
		public List<Object[]> getTodasInscripcionesSocio2(String persona, String ini){ 
			
			return db.executeQueryArray(SQL_TODAS_INSCRIPCIONES_SOCIO2, persona);
		}
		public static final String SQL_ELIMINAR_INSCRIPCIONES_ACTIVIDAD = "DELETE FROM inscripciones WHERE actividad=?";
		public void eliminarInscripciones(long actividad) {
			db.executeUpdate(SQL_ELIMINAR_INSCRIPCIONES_ACTIVIDAD, actividad);
		}
		
		public static final String SQL_PERSONA_ACTIVIDAD = "SELECT id_inscripcion FROM inscripciones WHERE actividad=? AND persona=?";
		public boolean personaActividadInscripciones(long actividad, String persona) {
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_PERSONA_ACTIVIDAD, actividad, persona);
			if(lista.size()==0) {
				return false;
			}
			else return true;
		}
		
		public static final String SQL_ID_INSCRIPCION = "SELECT id_inscripcion FROM inscripciones WHERE actividad=? AND persona=?";
		public String getIdInscripcion(String persona, long actividad) {
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_ID_INSCRIPCION, actividad, persona);
			return lista.get(0)[0].toString();
		}
		
		//Método para obtener siguiente id
		public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_inscripcion) from inscripciones;";
		public long siguienteIdInscripcion() {
			List<Object[]> lista;
			lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
			return (long)lista.get(0)[0] + 1;
		}
		
		//Método para instertar una nueva inscripcion
		public static final String SQL_NUEVA_INSCRIPCION = "INSERT INTO actividades(id_inscripcion, persona, actividad, fecha) VALUES (?, ?, ?, ?);";
		public void nuevaInscripcion(String persona, String actividad, String fecha) {
			long id;
			id = siguienteIdInscripcion();
			db.executeUpdate(SQL_NUEVA_INSCRIPCION,id, persona, actividad, fecha);
		}
		public long nuevaInscripcionRetornaId(String persona, String actividad, String fecha) {
			long id;
			id = siguienteIdInscripcion();
			db.executeUpdate(SQL_NUEVA_INSCRIPCION,id, persona, actividad, fecha);
			return id;
		}
	
	

}
