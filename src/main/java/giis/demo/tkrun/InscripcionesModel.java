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
	

}
