package giis.proyecto.Modelo;

import java.util.*;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.Database;
/**
 * Acceso a los datos de actividades e inscripciones, 
 * utilizado como modelo para el ejemplo de swing y para las pruebas unitarias y de interfaz de usuario.
 * 
 * <br/>En los metodos de este ejemplo toda la logica de negocio se realiza mediante una unica query sql por lo que siempre
 * se utilizan los metodos de utilidad en la clase Database que usan apache commons-dbutils y controlan la conexion. 
 * En caso de que en un mismo metodo se realicen diferentes queries se deberia controlar la conexion desde esta clase 
 * (ver como ejemplo la implementacion en Database).
 * 
 * <br/>Si utilizase algún otro framework para manejar la persistencia, la funcionalidad proporcionada por esta clase sería la asignada
 * a los Servicios, Repositorios y DAOs.
 */
public class ActividadesModel {
	private Database db = new Database();
	
	//SQL para obtener la lista de actividades activas para una fecha dada,
	//se incluye aqui porque se usara en diferentes versiones de los metodos bajo prueba
	public static final String SQL_LISTA_ACTIVIDADES = "SELECT id_actividad, nombre, aforo, cuota_socio, "
			+ "cuota_no_socio, fechaIniS_ins, fechaFinS_ins, fechaFinNS_ins, fechaIniActividad, fechaFinActividad, hora_inicioActividad, hora_finActividad, "
			+ "case when ?<fechaIniS_ins then ''" //antes de inscripcion
			+ "   when ?<=fechaFinS_ins then '(Abierta)'" //fase 1
			+ "   when ?<=fechaFinNS_ins then '(Abierta)'" //fase 2
			+ "   when ?<fechaIniActividad then '(Abierta)'" //fase 3
			+ "   else '' " //despues de fin actividad
			+ " end as abierta"
			+ " from actividades where fechaIniActividad>=? and fechaIniS_ins<? order by id_actividad";
	
	/**
	 * Obtiene la lista de actividades futuras (posteriores a una fecha dada) con el id, descripcion
	 * y la indicacion de si tienen inscripcion abierta.
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<Object[]> getListaActividadesArray(Date fechaInscripcion, Date fechaFinal) {
		//concatena los campos deseados en una unica columna pues el objetivo es devolver una lista de strings
		String sql = "SELECT id_actividad || '-' || nombre || ' ' || abierta as item from (" + SQL_LISTA_ACTIVIDADES + ")";
		String fecha=Util.dateToIsoString(fechaInscripcion);
		String fecha2 = Util.dateToIsoString(fechaFinal);
		return db.executeQueryArray(sql, fecha, fecha, fecha, fecha, fecha, fecha2);
	}
	
	/**
	 * Obtiene la lista de actividades activas en forma objetos para una fecha de inscripcion dada
	 */
	public List<ActividadDisplayDTO> getListaActividades(Date fechaInscripcion, Date fechaFinal) {
		String sql = "SELECT id_actividad, id_instalacion, nombre, aforo, cuota_socio, cuota_no_socio, "
				+ "fechaIniActividad, fechaFinActividad, id_inscripcion "
				+ "from actividades where (fechaIniActividad BETWEEN ? AND ?) order by fechaIniActividad";
		String fecha = Util.dateToIsoString(fechaInscripcion);
		String fecha2 = Util.dateToIsoString(fechaFinal);
		return db.executeQueryPojo(ActividadDisplayDTO.class, sql, fecha, fecha2);
	}

	/**
	 * Obtiene todos los datos de la actividad con el id indicado
	 */
	public ActividadEntity getActividad(int id) {
		String sql = "SELECT id_actividad, id_instalacion, nombre, aforo, cuota_socio, cuota_no_socio, "
				+ "fechaIniActividad, fechaFinActividad, id_inscripcion "
				+ "from actividades where id_actividad=?";
		return db.executeQueryPojo(ActividadEntity.class, sql, id).get(0);
	}
	/**
	 * Obtiene el nomvbre de la actividad con el id indicado
	 */
	public String getNombreActividad(String id_actividad) {
		if (id_actividad == null || id_actividad.equals("")) 
			return null;
		else {
			String sql = "SELECT nombre from actividades where id_actividad=?";
			List<Object[]> list = db.executeQueryArray(sql, id_actividad);
			if (list.equals(null) || list.size()<1)
				return "";
			else
				return (String)list.get(0)[0];
		}
	}
	

	
	/** 
	 * Obtiene el porcentaje de descuento (valor negativo) o recargo aplicable a una actividad dada por su id cuando se
	 * realiza la inscripcion en una fecha dada.
	 * Causa excepcion si no esta abierta la inscripcion.
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * restultado de la ejecucion de una query sql
	 */
	public int getDescuentoRecargo(long idActividad, Date fechaInscripcion) {
		String sql = "SELECT "
				+" case when ?<fechaIniS_ins then NULL" //antes de inscripcion
				+"   when ?<=fechaFinS_ins then -30" //fase 1
				+"   when ?<=fechaFinNS_ins then 0" //fase 2
				+"   when ?<fechaIniActividad then 50" //fase 3
				+"   else NULL "
				+" end as descuentoRecargo"
				+" from actividades where id=? order by id_actividad";
		
		String fecha = Util.dateToIsoString(fechaInscripcion);
		List<Object[]> rows = db.executeQueryArray(sql, fecha, fecha, fecha, fecha, idActividad);
		//determina el valor a devolver o posibles excepciones
		
		if (rows.isEmpty())
			throw new ApplicationException("Id de actividad no encontrado: " + idActividad);
		else if (rows.get(0)[0]==null)
			throw new ApplicationException("No es posible la inscripcion en esta fecha");
		else
			return (int)rows.get(0)[0];
	}
	
	/**
	 * Obtiene todos los datos de la actividad con el id indicado
	 */
	/*public ActividadEntity getActividad(int id) {
		String sql = "SELECT id_actividad, id_instalacion, nombre, aforo, cuota_socio, cuota_no_socio, fechaIniS_ins, "
				+ "fechaFinS_ins, fechaFinNS_ins, fechaIniActividad, fechaFinActividad, hora_inicioActividad,hora_finActividad from actividades where id_actividad=?";
		return db.executeQueryPojo(ActividadEntity.class, sql, id).get(0);
	}*/
	
}
