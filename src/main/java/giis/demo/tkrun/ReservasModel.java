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
	
	//SQL para ver todas las reservas de un socio
	public static final String SQL_RESERVAS_SOCIO = "SELECT fecha_reserva FROM reservas WHERE ((persona=?) AND (instalacion=?))";
	public List<Object[]> getListaReservasUsuario(int idsocio, String instalacion){
		
		return db.executeQueryArray(SQL_RESERVAS_SOCIO, idsocio, instalacion);
	}
	//SQL para ver todas las reservas de un socio
		public static final String SQL_RESERVAS_SOCIO_GENERAL = "SELECT persona FROM reservas WHERE ((persona= ?) AND (fecha_reserva >= ?)) ";
		public List<Object[]> getListaReservasUsuario1(int idsocio, String Date){
			
			return db.executeQueryArray(SQL_RESERVAS_SOCIO_GENERAL, idsocio, Date);
		}
		
	//SQL para ver todas las reservas de un socio
				public static final String SQL_RESERVAS_SOCIO_DIA = "SELECT fecha_reserva FROM reservas WHERE ((persona=?) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?)) ";
				public List<Object[]> getListaReservasUsuario2(int idsocio, String Date, String Date1){
					
					return db.executeQueryArray(SQL_RESERVAS_SOCIO_DIA, idsocio, Date, Date1);
				}
	
	
	//SQL para ver si un socio tiene al menos 3 reservas en un periodo de tiempo
			public static final String SQL_RESERVAS_INSTALACION_SOCIO1 = "SELECT MIN(fecha_reserva) FROM reservas WHERE ((persona= ?) "
					+ "AND (fecha_reserva >= ?) AND (fecha_reserva <= ?) AND (instalacion =?));";
			public static final String SQL_RESERVAS_INSTALACION_ = "SELECT COUNT(persona) FROM reservas WHERE ((instalacion = ?)AND ((persona=?) AND ((fecha_reserva= ?) "
					+ "OR (fecha_reserva= ?) OR (fecha_reserva= ?) OR (fecha_reserva=?))));";
			public boolean getReservasInstalacionSocio(String fechaIni,String fechaFin, int hora_mas_1,int  hora_mas_2, int id_socio, String id,int horaIni, int horaT, String Date, int horaJ){
				List<Object[]> lista;
				
				lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_SOCIO1,id_socio, fechaIni, fechaFin, id);
				//System.out.printf(" Fecha +3 %s \n",fechaFin);
				//System.out.printf(" Fecha -3 %s \n",fechaIni);
				//System.out.printf("Id socio %d \n",id_socio);
				
				try {
					//System.out.println("Try");
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
			    		// System.out.println("Entra en el if");
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
						   // System.out.printf(" Fecha1=FechaMin %s \n",fecha1);
						//	System.out.printf(" Fecha2=FechaMin+1 %s \n",fecha2);
						//	System.out.printf(" Fecha3=FechaMin+2 %s \n",fecha3);
							//System.out.printf(" Fecha4=FechaMin+3 %s \n",fecha4);
							
							if(hora1 == hora_mas_1) {
								//System.out.println("\n hora1"+hora1);
							//	System.out.println("\n hora_mas_1"+hora_mas_1);
								
								 lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_, id,id_socio,fecha1,fecha2,fecha2,fecha2 );
								    int jk= Integer.parseInt(lista.get(0)[0].toString());
								  //  System.out.printf("\n %d Count(reservas) \n", jk);
								    
									if ((Integer.parseInt(lista.get(0)[0].toString()) < 3)){
										
										return true;
									}
									else {
									    return false;
									}	
								
								
							}
							else {
								
							if(hora1 >= hora_mas_2){
							//	System.out.println("\n hora1"+hora1);
							//	System.out.println("\n hora_mas_2"+hora_mas_2);
			   
							    return true;
							}
							
							
							else {

							    
							    lista = db.executeQueryArray(SQL_RESERVAS_INSTALACION_, id,id_socio,fecha1,fecha2,fecha3,fecha4 );
							    int jk= Integer.parseInt(lista.get(0)[0].toString());
							 //   System.out.printf("\n %d Count(reservas) \n", jk);
							    
								if ((Integer.parseInt(lista.get(0)[0].toString()) < 3)){
									
									return true;
								}
								else {
								    return false;
								}
								
								
								
								
							}
							
								
							
							}
			    		
			    	}
			    }		   
								
				catch (Exception E){
					//System.out.printf("Catch \n");
					//E.printStackTrace();
					//return true;					
				}				
				return true;				
			}
	


	
	public static final String SQL_RESERVAS_MANU= "SELECT persona, fecha_reserva, actividad, id_reserva FROM reservas WHERE instalacion=";
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
		
		
	
//Método para actualizar la cuota de un cliente
public static final String SQL_SUMA_CUOTA = "UPDATE clientes SET cuotaReservas=? WHERE (id_socio=?);";
public void añadeacuota(double cuota, int id_socio) {	
	//System.out.println("La cuota es"+cuota);
	db.executeUpdate(SQL_SUMA_CUOTA,cuota, id_socio);
}

//Método para actualizar la cuota de todos los clientes a 0
public static final String SQL_SUMA_CUOTA1 = "UPDATE clientes SET cuotaReservas=?;";
public void añadeacuota1() {	
	//System.out.println("La cuota es"+cuota);
	db.executeUpdate(SQL_SUMA_CUOTA1,0);
}

//Método para actualizar la cuota actividad de todos los clientes a 0
public static final String SQL_SUMA_CUOTA2 = "UPDATE clientes SET cuotaActividades=?;";
public void añadeacuota2() {	
	//System.out.println("La cuota es"+cuota);
	db.executeUpdate(SQL_SUMA_CUOTA2,0);
}


//Método para obtener la cuota
public static final String SQL_CUOTA = "SELECT cuotaReservas from clientes WHERE (id_socio=?);";
public double nuevaCuota(int id_socio) {
	List<Object[]> lista;
	lista = db.executeQueryArray(SQL_CUOTA, id_socio);
	return (double)lista.get(0)[0];
	
}


//Método para obtener la cuota
public static final String SQL_PAGOS1 = "SELECT nombre, id_socio, cuotaInicial, cuotaReservas, cuotaActividades from clientes WHERE (id_socio >= 0)";
public List<Object[]> nuevaCuota1() {
	List<Object[]> lista;
	lista = db.executeQueryArray(SQL_PAGOS1);
	return lista;
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
	
	//Método para eliminar una reserva con el id
	public static final String SQL_ELIMINAR_RESERVA_ID = "DELETE from reservas WHERE id_reserva = ?;";
	public void eliminarReserva(String id) {

		db.executeUpdate(SQL_ELIMINAR_RESERVA_ID,id);
	}
	
			
	//Método para obtener el nombre de las actividades que se encuentran en un periodo determinado	
    public static final String SQL_TODAS_ACTIVIDADES_PERIODO1 = "SELECT nombre, descripcion, fecha_ini, fecha_fin, aforo, precio_socio, precio_no_socio FROM actividades WHERE fecha_fin >=";
	
	public List<Object[]> getActividadPeriodo(String fechaIni, String fechaFin){
		return db.executeQueryArray(SQL_TODAS_ACTIVIDADES_PERIODO1+"'"+fechaIni+"'"+" AND fecha_ini <= '" + fechaFin +"';");	
		
	} 
	//Método para obtener el nombre de las actividades que se encuentran en un periodo determinado	
    public static final String SQL_TODAS_LISTA_RESERVAS1 = "SELECT id_reserva, instalacion, fecha, fecha_reserva, precio, actividad FROM reservas WHERE ((fecha_reserva>= ?) AND (persona = ?) AND (fecha_reserva <= ?))";

	public List<Object[]> getActividadPeriodo2(String fechaIni, String fechaFin, int id_socio){
		return db.executeQueryArray(SQL_TODAS_LISTA_RESERVAS1, fechaIni,  id_socio, fechaFin);	
		
	} 
		
	//Método para obtener el cliente a partir  
	public static final String SQL_CLIENTE1 = "SELECT persona FROM reservas WHERE instalacion = '";
	public static final String SQL_CLIENTE2 = " AND fecha_reserva = '";
	public static final String SQL_CLIENTE = "SELECT persona FROM reservas WHERE id_reserva=?";

	
	public int getCliente(String instalacion, String fecha){
		List<Object[]> lista= db.executeQueryArray(SQL_CLIENTE1+instalacion+"'"+SQL_CLIENTE2 + fecha+"';");	
		return (int) lista.get(0)[0];
	}
	

	public Object getCliente(String id_reserva){
		List<Object[]> lista= db.executeQueryArray(SQL_CLIENTE,id_reserva);	
		return lista.get(0)[0];
	}
	public int getClienteInt(String id_reserva){
		List<Object[]> lista= db.executeQueryArray(SQL_CLIENTE,id_reserva);	
		return Math.toIntExact((long)lista.get(0)[0]);
	}

	
	//método para obtener todas las reservas de un socio
	public static final String SQL_RESERVAS_CLIENTE = "SELECT id_reserva, fecha_reserva, instalacion FROM reservas WHERE persona = ?";
	public List<Object[]> todasReservasSocio(int n){
		return db.executeQueryArray(SQL_RESERVAS_CLIENTE,n);	
	}
	
	//método para comprobar si existe una reserva de un socio(osea una reserva hecha por un socio, no concreto)
	public static final String SQL_EXISTE_RESERVA = "SELECT instalacion FROM reservas WHERE id_reserva = ? AND persona IS NOT NULL";
	public boolean existeReserva(int id){
		List<Object[]> l = db.executeQueryArray(SQL_EXISTE_RESERVA,id);	
		if(l.isEmpty()) return false;
		return true;
	}
	
	//método para obtener el precio de una reserva
		public static final String SQL_PRECIO = "SELECT precio FROM reservas WHERE id_reserva = ?";
		public double getPrecio(int id_reserva){
			List<Object[]> l = db.executeQueryArray(SQL_PRECIO,id_reserva);
			return Double.parseDouble(l.get(0)[0].toString());
		}
	
		
		
		
		
		//SQL para ver todas las reservas de un socio
		public static final String SQL_RESERVAS_SOCIO_TODO = "SELECT id_reserva, persona, instalacion, fecha, fecha_reserva, precio, actividad FROM reservas WHERE persona= ? AND fecha>=? AND fecha<=? ORDER BY fecha DESC";
		public List<Object[]> getReservasSocioTodo(int persona, String ini, String fin){
			
			return db.executeQueryArray(SQL_RESERVAS_SOCIO_TODO, persona, ini, fin);
		}
		
		//SQL para ver la actividad de una reserva
		public static final String SQL_ACTIVIDAD_RESERVA = "SELECT actividad FROM reservas WHERE fecha_reserva=? AND instalacion=?";
		public String getActividadReserva(String fecha, String actividad){
			
			List<Object[]> l = db.executeQueryArray(SQL_ACTIVIDAD_RESERVA, fecha, actividad);
			return l.get(0)[0].toString();
		}
		
		//SQL para ver el socio de una reserva
				public static final String SQL_SOCIO_RESERVA = "SELECT persona FROM reservas WHERE fecha_reserva=? AND instalacion=?";
				public String getSocioReserva(String fecha, String actividad){
					
					List<Object[]> l = db.executeQueryArray(SQL_SOCIO_RESERVA, fecha, actividad);
					return l.get(0)[0].toString();
				}
				
				//SQL para ver el socio de una reserva
				public static final String SQL_ID_RESERVA_F = "SELECT id_reserva FROM reservas WHERE fecha_reserva=? AND instalacion=?";
				public String getIdReservaF(String fecha, String actividad){
					
					List<Object[]> l = db.executeQueryArray(SQL_ID_RESERVA_F, fecha, actividad);
					return l.get(0)[0].toString();
				}
				
				//SQL para ver el socio de una reserva
				public static final String SQL_DETALLES_ACTIVIDADES = "SELECT nombre,  fecha_ini, fecha_fin, instalacion, id_actividad FROM actividades WHERE fecha_ini >= ? OR fecha_fin <= ?;"
						+ "";
				public List<Object[]> getActividades(String fechaIni, String fechaFin){
					
					 List<Object[]> l = db.executeQueryArray(SQL_DETALLES_ACTIVIDADES, fechaIni, fechaFin);
					return l;
				}
				
				//SQL para ver el socio de una reserva
				public static final String SQL_AFORO_ACTIVIDADES = "SELECT aforo FROM actividades  WHERE id_actividad=?; ";
				public int getAforoActividades(int id){
					
					List<Object[]> l = db.executeQueryArray(SQL_AFORO_ACTIVIDADES,id);
					return (int)l.get(0)[0];
				}
				
				//SQL para ver el socio de una reserva
				public static final String SQL_AFORO_SOCIOS = "SELECT  COUNT(id_reserva) FROM reservas WHERE ( (actividad = ?) AND (persona >=0) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?) );";
				public String getAforoSocios(String fecha, int actividad, String fechaFin){
					
					List<Object[]> l = db.executeQueryArray(SQL_AFORO_SOCIOS, actividad, fecha, fechaFin);
					return  l.get(0)[0].toString();
				}
				//SQL para ver el socio de una reserva
				public static final String SQL_AFORO_NO_SOCIOS = "SELECT COUNT(id_reserva) FROM reservas WHERE ((actividad =?) AND (persona is null) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?));";
				public String getAforoNoSocios(String fecha, int actividad, String fechaFin){
					
					List<Object[]> l = db.executeQueryArray(SQL_AFORO_NO_SOCIOS, actividad, fecha, fechaFin);
					return l.get(0)[0].toString();
				}
				//SQL para ver el socio de una reserva
				public static final String SQL_RESERVAS_SOCIOS = "SELECT COUNT(id_reserva) FROM reservas WHERE ((persona =?) AND (fecha_reserva >= ?) AND (fecha_reserva <= ?));";
				public String getReservasSocio(String fecha, int actividad, String fechaFin){
					
					List<Object[]> l = db.executeQueryArray(SQL_RESERVAS_SOCIOS, actividad, fecha, fechaFin);
					return l.get(0)[0].toString();
				}
				//SQL para ver el socio de una reserva
				public static final String SQL_SOCIOS = "SELECT id_socio FROM clientes WHERE (id_socio is not null);";
				public List<Object[]> getSocios(){
					
					List<Object[]> l = db.executeQueryArray(SQL_SOCIOS);
					return l;
				}
				
				
				

}
