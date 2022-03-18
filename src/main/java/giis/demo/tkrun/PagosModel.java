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
		return l.get(0)[0].toString();
	}
	
	//SQL para añadir un pago
	public static final String SQL_ANADE_PAGO = "INSERT INTO pagos (id_pago, fecha, persona, inscripcion, reserva) VALUES (?, ?, ?, ?, ?);";
	public void anadirPago(String fecha, String persona, String inscripcion, String reserva){
		
		db.executeQueryArray(SQL_ANADE_PAGO, Long.toString(siguienteIdPago()) ,fecha, persona, inscripcion, reserva);
	}
	
	//SQL para añadir un pago
	public static final String SQL_ELIMINA_PAGO = "DELETE from pagos WHERE id_pago=?;";
	public void eliminarPago(String id_pago){
		
		db.executeQueryArray(SQL_ELIMINA_PAGO, id_pago);
	}
	
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_pago) from pagos;";
	public long siguienteIdPago() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
}
