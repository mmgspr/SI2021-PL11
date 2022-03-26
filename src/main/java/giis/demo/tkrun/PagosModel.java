package giis.demo.tkrun;
import java.util.List;

import giis.demo.util.Database;
public class PagosModel {
	private Database db = new Database();
	
	//SQL para ver todas las reservas de una instalacion
	public static final String SQL_TODOS_CLIENTE = "SELECT id_pago FROM pagos WHERE persona=?";
	public List<Object[]> getPagosCliente(String cliente){
		
		return db.executeQueryArray(SQL_TODOS_CLIENTE, cliente);
	}
	
	//SQL para ver la reserva de un pago
	public static final String SQL_RESERVA = "SELECT reserva FROM pagos WHERE id_pago=?";
	public String getReserva(String pago){
		
		List<Object[]> l = db.executeQueryArray(SQL_RESERVA, pago);
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0)[0].toString();
	}
	
	//SQL para añadir un pago
	public static final String SQL_ANADE_PAGO = "INSERT INTO pagos (id_pago, fecha, persona, inscripcion, reserva) VALUES (?, ?, ?, ?, ?);";
	public void anadirPago(String fecha, String persona, String inscripcion, String reserva){
		
		db.executeUpdate(SQL_ANADE_PAGO, Long.toString(siguienteIdPago()) ,fecha, persona, inscripcion, reserva);
	}
	
	//SQL para eliminar un pago con el id
	public static final String SQL_ELIMINA_PAGO = "DELETE from pagos WHERE id_pago=?;";
	public void eliminarPago(String id_pago){
		
		db.executeUpdate(SQL_ELIMINA_PAGO, id_pago);
	}
	
	//SQL para eliminar un pago con el id
	public static final String SQL_ELIMINA_PAGO2 = "DELETE from pagos WHERE reserva=?;";
	public void eliminarPagoReserva(String id_reserva){
		
		db.executeUpdate(SQL_ELIMINA_PAGO2, id_reserva);
	}
	
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_pago) from pagos;";
	public long siguienteIdPago() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
	//SQL para comprobar si inscripcion pagada
		public static final String SQL_PAGO_INSCRIPCION = "SELECT id_pago FROM pagos WHERE inscripcion=?";
		public boolean getPagoInscripcion(String inscripcion){
			
			List<Object[]> l = db.executeQueryArray(SQL_PAGO_INSCRIPCION, inscripcion);
			if (l.isEmpty()) {
				return false;
			}
			else {
				return true;
			}
		}
		
		//SQL para comprobar si inscripcion pagada
			public static final String SQL_PAGO_RESERVA = "SELECT id_pago FROM pagos WHERE reserva=?";
			public boolean getPagoReserva(String reserva){
					
				List<Object[]> l = db.executeQueryArray(SQL_PAGO_RESERVA, reserva);
				if (l.isEmpty()) {
					return false;
				}
				else {
					return true;
				}
			}
	
}
