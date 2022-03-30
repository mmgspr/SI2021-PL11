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
	

}
