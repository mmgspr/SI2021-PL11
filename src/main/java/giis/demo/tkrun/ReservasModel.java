package giis.demo.tkrun;

import java.util.Iterator;
import java.util.List;

import giis.demo.util.Database;

public class ReservasModel {

private Database db = new Database();
	
	// buscar el sentido a db(de momento no tiene ninguno)


	//SQL para ver todas las reservas de una instalacion
	public static final String SQL_RESERVAS_INSTALACION = "SELECT id_reserva FROM reservas WHERE instalacion=";
	public List<Object[]> getReservasInstalacion(String instalacion){
		
		//return db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'");
		return db.executeQueryArray(SQL_RESERVAS_INSTALACION, instalacion);
	}
	

	
	//SQL para ver si un socio tiene al menos 3 reservas en un periodo de tiempo
			public static final String SQL_RESERVAS_INSTALACION_SOCIO1 = "SELECT MIN(fecha_reserva) FROM reservas WHERE ((persona= ?) "
					+ "AND (fecha_reserva >= ?) AND (fecha_reserva <= ?));";
			public static final String SQL_RESERVAS_INSTALACION_ = "SELECT COUNT(persona) FROM reservas WHERE ((instalacion = ?)AND ((persona=?) AND ((fecha_reserva= ?) "
					+ "OR (fecha_reserva= ?) OR (fecha_reserva= ?) OR (fecha_reserva=?))));";
			public boolean getReservasInstalacionSocio(String fechaIni,String fechaFin, int id_socio, String id,int horaIni, int horaT, String Date, int horaJ){
				List<Object[]> lista;
				
				lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_SOCIO1,id_socio, fechaIni, fechaFin);
				//System.out.printf(" Fecha +3 %s \n",fechaFin);
				//System.out.printf(" Fecha -3 %s \n",fechaIni);
				//System.out.printf("Id socio %d \n",id_socio);
				
				try {
					System.out.println("Try");
			        String fecha_min =lista.get(0)[0].toString();
			    
			    
			   // String[] vector1=fecha_min.split("T"); 
			   // String fecha=vector1[1].split(":")[0];
			   // int horaMin = Integer.parseInt(fecha);
			   // System.out.println("HoraMin \n"+horaMin);
			   // System.out.println("HoraS \n"+(horaIni-1));
			    
			   // String diaHora_mas_1 = Date+" "+horaT+":00:00";
			   // String diaHora_menos_1 = Date+" "+horaJ+":00:00";
			    //System.out.println("HORA MAS 1 "+diaHora_mas_1);
			   // System.out.println("HORA MENOS 1"+diaHora_menos_1);
			    
			    if(fecha_min != null){			    
			    		 System.out.println("Entra en el if");
						    String[] vector=fecha_min.split("T"); 
						    fecha_min=vector[1].split(":")[0];
						    int hora1 = Integer.parseInt(fecha_min);
						    int hora2 = hora1+1;
						    int hora3 = hora1+2;
						    int hora4 = hora1+3;
						    
						    String fecha1= vector[0]+" "+hora1+":00:00";
						    String fecha2= vector[0]+" "+hora2+":00:00";
						    String fecha3= vector[0]+" "+hora3+":00:00";
						    String fecha4= vector[0]+" "+hora4+":00:00";
						    System.out.printf(" Fecha1=FechaMin %s \n",fecha1);
							System.out.printf(" Fecha2=FechaMin+1 %s \n",fecha2);
							System.out.printf(" Fecha3=FechaMin+2 %s \n",fecha3);
							System.out.printf(" Fecha4=FechaMin+3 %s \n",fecha4);
							
						    
						    lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_, id,id_socio,fecha1,fecha2,fecha3,fecha4 );
						    int jk= Integer.parseInt(lista.get(0)[0].toString());
						    System.out.printf("%d Count(reservas) \n", jk);
						    
							if ((Integer.parseInt(lista.get(0)[0].toString()) < 3)){
								
								return true;
							}
							else {
							    return false;
							}
			    		
			    	}
			    }		   
								
				catch (Exception E){
					System.out.printf("Catch \n");
					E.printStackTrace();
					//return true;					
				}				
				return true;				
			}
	


	
	public static final String SQL_RESERVAS_MANU= "SELECT persona, fecha_reserva, actividad FROM reservas WHERE instalacion=";
	public List<Object[]> getReservasManu(long id_instalacion){
		return db.executeQueryArray(SQL_RESERVAS_MANU+ "'"+id_instalacion+"'");
	}
	
	//SQL para comprobar si una instalacion está reservada un día a una hora
	//retorna true si no está reservado y se puede reservar
		public static final String SQL_RESERVADA = " AND fecha_reserva=";
		//public static final String SQL_RESERVADA = "SELECT id_reserva FROM reservas WHERE instalacion=?";
		List<Object[]> lista;
		public boolean comprobarDisponibilidad(String instalacion, String diaHora){
			lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'"+ SQL_RESERVADA+"'"+diaHora+"'");
			
			if (lista.size() == 0) {
				//System.out.println(lista.get(0)[0]);
				return true;
			}
			return false;
		}
		
		//SQL para comprobar si una instalacion está reservada un día a una hora
		//retorna true si no está reservado y se puede reservar
			public static final String SQL_RESERVADAS = " SELECT COUNT(persona) FROM reservas WHERE ((instalacion=4) AND (persona=2) AND (fecha_reserva=?))";
			List<Object[]> listaa;
			public boolean comprobarDisponibilidadS(String instalacion, String diaHora, int id_socio){
				lista = db.executeQueryArray(SQL_RESERVADAS, /*instalacion, id_socio, */diaHora);
				
				if (lista.size() == 0) {
					System.out.println("Disponibilidad");
					return true;
				}
				return false;
			}
		
	//SQL para comprobar si una instalacion está reservada por actividad
	//retorna 0 si puedes reservar, 1 si está reservada por un cliente y puedes tmb, y -1 si no puedes reservar
		public static final String SQL_RES_ACTIVIDAD = " AND actividad!=0";
		public static final String SQL_RES_SOCIO = " AND persona is not null";
		List<Object[]> lista1, lista2;
		//boolean socio;
		public int comprobarDisponibilidadActividad(String instalacion, String diaHora){
			lista1 = db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'"+ SQL_RESERVADA+"'"+diaHora+"'"+ SQL_RES_ACTIVIDAD);
			lista2 = db.executeQueryArray(SQL_RESERVAS_INSTALACION+"'"+instalacion+"'"+ SQL_RESERVADA+"'"+diaHora+"'"+ SQL_RES_SOCIO);
			//socio = comprobarDisponibilidad(instalacion, diaHora);
			//System.out.println(socio);
			if (lista1.size() == 0 && lista2.size() == 0) {
				//System.out.println(lista.get(0)[0]);
				return 0;
			}
			else if(lista1.size() == 0)
				return 1;
			return -1;
		}

	//Método para instertar una nueva reserva
	public static final String SQL_NUEVA_RESERVA = "INSERT INTO reservas (id_reserva, persona, instalacion, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";
	public void nuevaReserva(int socio, int instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
		long id;
		id = siguienteIdReserva();		
		db.executeUpdate(SQL_NUEVA_RESERVA,id, null,instalacion, fecha, fecha_reserva, precio, actividad);
	}
	
	//Método para instertar una nueva reserva
		public static final String SQL_NUEVA_RESERVA_SOCIO = "INSERT INTO reservas (id_reserva, persona, instalacion, fecha, fecha_reserva, precio, actividad) VALUES (?, ?, ?, ?, ?, ?, ?);";
		public void nuevaReserva1(int socio, int instalacion, String fecha, String fecha_reserva, String precio, Object actividad) {
			long id;
			id = siguienteIdReserva();		
			db.executeUpdate(SQL_NUEVA_RESERVA_SOCIO,id, socio,instalacion, fecha, fecha_reserva, precio, actividad);
		}
	
	
	
	
	
	
	//Método para obtener siguiente id
	public static final String SQL_SIGUIENTE_ID = "SELECT MAX(id_reserva) from reservas;";
	public long siguienteIdReserva() {
		List<Object[]> lista;
		lista = db.executeQueryArray(SQL_SIGUIENTE_ID);
		return (long)lista.get(0)[0] + 1;
	}
	
	//Método para obtener el nombre de las actividades según su id 
	public static final String SQL_ACTIVIDAD = "SELECT nombre FROM actividades WHERE id_actividad=";
	
	public List<Object[]> getActividad(long id_actividad){
		return db.executeQueryArray(SQL_ACTIVIDAD+id_actividad);	
	}
	
	//Método para eliminar una reserva
		public static final String SQL_ELIMINAR_RESERVA = "DELETE from reservas WHERE instalacion=? AND fecha_reserva=?;";
		public void eliminarReserva(int instalacion, String fecha) {

			db.executeUpdate(SQL_ELIMINAR_RESERVA,instalacion, fecha);
		}
	
			
	//Método para obtener el nombre de las actividades que se encuentran en un periodo determinado	
    public static final String SQL_TODAS_ACTIVIDADES_PERIODO1 = "SELECT nombre, descripcion, fecha_ini, fecha_fin, aforo, precio_socio, precio_no_socio FROM actividades WHERE fecha_fin >=";
	
	public List<Object[]> getActividadPeriodo(String fechaIni, String fechaFin){
		return db.executeQueryArray(SQL_TODAS_ACTIVIDADES_PERIODO1+"'"+fechaIni+"'"+" AND fecha_ini <= '" + fechaFin +"';");	
		
	} 
		
	//Método para obtener el cliente a partir  
	public static final String SQL_CLIENTE1 = "SELECT persona FROM reservas WHERE instalacion = '";
	public static final String SQL_CLIENTE2 = " AND fecha_reserva = '";
	
	public int getCliente(String instalacion, String fecha){
		List<Object[]> lista= db.executeQueryArray(SQL_CLIENTE1+instalacion+"'"+SQL_CLIENTE2 + fecha+"';");	
		return (int) lista.get(0)[0];
	} 
	
}
