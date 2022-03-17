package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JSeparator;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class Reservar_Instalacion_Socio {

	private InstalacionesModel modelo = new InstalacionesModel();	
	private ReservasModel modeloReservas = new ReservasModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private JFrame frmReservarInstalacin;
	private JTextField textFieldCoste;
	private Login vLogin;
	private String precio="";
	private int id_socio;
	private String date;
	private Object hora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservar_Instalacion_Socio window = new Reservar_Instalacion_Socio();
					window.frmReservarInstalacin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Reservar_Instalacion_Socio() {
		initialize();
	}
	public Reservar_Instalacion_Socio(Login login) {
		initialize();
		this.vLogin= login;
		this.id_socio=this.vLogin.getId_socio();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarInstalacin = new JFrame();
		frmReservarInstalacin.setTitle("Reservar Instalación");
		frmReservarInstalacin.setBounds(100, 100, 450, 300);
		frmReservarInstalacin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		System.out.println(getHora("2022-3-25T17:00:00"));
		System.out.println(getHora1("17:00"));
		
		//obtener todas las instalaciones
				List<Object[]> lista=modelo.getInstalaciones();
				
				String[] instalaciones=new String[lista.size()];
				
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					instalaciones[i]=iterador.next()[0].toString();
					i++;
				}
				
			
				
				
				
				//String id_socio = "2";
				
				
		JPanel panel = new JPanel();
		frmReservarInstalacin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelFechadeReserva = new JLabel("Fecha de la Reserva:");
		LabelFechadeReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFechadeReserva.setBounds(10, 10, 141, 27);
		panel.add(LabelFechadeReserva);
		
		JLabel LabelCoste = new JLabel("Coste de la Reserva:");
		LabelCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelCoste.setBounds(10, 116, 141, 29);
		panel.add(LabelCoste);
		
		textFieldCoste = new JTextField();
		textFieldCoste.setEditable(false);
		textFieldCoste.setBounds(161, 123, 93, 19);
		panel.add(textFieldCoste);
		textFieldCoste.setColumns(10);
		
		JLabel LabelMetododePago = new JLabel("Método de Pago:");
		LabelMetododePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelMetododePago.setBounds(10, 155, 113, 29);
		panel.add(LabelMetododePago);
		
		JComboBox comboBoxMetodo = new JComboBox();
		comboBoxMetodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMetodo.setModel(new DefaultComboBoxModel(new String[] {"Pagar ahora", "Añadir a mi cuota"}));
		comboBoxMetodo.setBounds(161, 159, 170, 21);
		panel.add(comboBoxMetodo);
		
		JButton ButtonCancelar = new JButton("Cancelar\r\n");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservarInstalacin.dispose();
			}
		});
		
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(34, 232, 103, 21);
		panel.add(ButtonCancelar);
		
	
		JLabel LabelInstalacion = new JLabel("Seleccione Instalación:");
		LabelInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelInstalacion.setBounds(10, 76, 152, 30);
		panel.add(LabelInstalacion);
		
		JComboBox comboBoxInstalaciones = new JComboBox();
		comboBoxInstalaciones.setModel(new DefaultComboBoxModel(instalaciones));
		comboBoxInstalaciones.setBounds(161, 83, 141, 21);
		panel.add(comboBoxInstalaciones);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(159, 18, 113, 19);
		panel.add(dateChooserInicio);
		
		JLabel LabelHoraIni = new JLabel("Hora de Inicio:\r\n");
		LabelHoraIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelHoraIni.setBounds(10, 47, 113, 19);
		panel.add(LabelHoraIni);
		
		JComboBox comboBoxHoraIni = new JComboBox();
		comboBoxHoraIni.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		comboBoxHoraIni.setBounds(158, 48, 85, 21);
		panel.add(comboBoxHoraIni);
		
		JButton ButtonReservar = new JButton("Reservar\r\n");
		ButtonReservar.setEnabled(false);
		ButtonReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//id_instalacion
				String id;
				
				List<Object[]> lista=modelo.getIdInstalacion((String)comboBoxInstalaciones.getSelectedItem());
				String[] nombre=new String[lista.size()];
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}
				
				//Id instalacion
				id = nombre[0];
				//System.out.println(id);
				
				
				
				//fecha
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooserInicio.getDate());
				String hora = (String)comboBoxHoraIni.getSelectedItem();
				System.out.println("Esta es la hora elegida"+hora);
				String diaHora = date+" "+hora;
				
				//Cogemos el indice de la elección
				int indice = comboBoxHoraIni.getSelectedIndex();
				//System.out.printf("%d hora  ",indice);
				//System.out.printf("%d hora  ",indice-1);
				
				
				
				//Mtemos la hora anterior
				String hora_menos_1 = (String)comboBoxHoraIni.getItemAt(indice-1);				
				//Hora Anterior
				String diaHora1 = date+" "+hora_menos_1+":00";
								
				//Metemos -2 hora 
				String hora_menos_2 = (String)comboBoxHoraIni.getItemAt(indice-2);				
		        
				//Metemos -2 hora 
				String hora_menos_3= (String)comboBoxHoraIni.getItemAt(indice-3);				
				
				
								
				//Mtemos la hora siguiente
				String hora_mas_1 = (String)comboBoxHoraIni.getItemAt(indice+1);
				  
				//Hora Siguiente
				String diaHora3 = date+" "+hora_mas_1+":00";
								
				//Metemos +2 hora 
				String hora_mas_2 = (String)comboBoxHoraIni.getItemAt(indice+2);	
				 
				//Metemos +3 hora 
				String hora_mas_3 = (String)comboBoxHoraIni.getItemAt(indice+3);	
				//System.out.println("\n Indice \n"+indice);
				//System.out.println(comboBoxHoraIni.getItemAt(indice+3));
				
				
		
				
				//fecha de hoy
				Date d1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				
				//diferencia entre las dos fechas
				long diferencia_dias= 0;
				long diferencia_años= 0;
				try {
					//pasar de string a fecha
		            Date d2 = sdf.parse(date);
		  
		            //Calcular la diferencia en milisegundos
		            long difference_In_Time
		                = d2.getTime() - d1.getTime();
		  
		            // Calcular la diferencia en segundos, minutos, horas, años y dias
		            long difference_In_Seconds
		                = (difference_In_Time
		                   / 1000)
		                  % 60;
		  
		            long difference_In_Minutes
		                = (difference_In_Time
		                   / (1000 * 60))
		                  % 60;
		  
		            long difference_In_Hours
		                = (difference_In_Time
		                   / (1000 * 60 * 60))
		                  % 24;
		  
		            long difference_In_Years
		                = (difference_In_Time
		                   / (1000l * 60 * 60 * 24 * 365));
		  
		            long difference_In_Days
		                = (difference_In_Time
		                   / (1000 * 60 * 60 * 24))
		                  % 365;
		            diferencia_dias = difference_In_Days;
		            diferencia_años = difference_In_Years;
		           
		        }
		  
		        // Catch the Exception
		        catch (ParseException excepcion) {
		            excepcion.printStackTrace();
		        }
				
				
					
	            //id del socio				
				int id_socioS = vLogin.getId_socio();
				String id_socio = String.valueOf(id_socioS); 
				
				  String fecha_ini=hora;
		   		  String[] vector1=fecha_ini.split(":"); 
			      fecha_ini=vector1[0].split(":")[0];
		   		 //System.out.println("Fecha_ini"+fecha_ini);
				 int horaS= Integer.parseInt(fecha_ini);
				// System.out.println("horS"+horaS);
		   			
		   		switch(hora) {
		   		case"09:00":hora_menos_3=hora;
		   		break;
		   		case"10:00":hora_menos_3=hora_menos_1;
		   		break;
		   		case"11:00":hora_menos_3=hora_menos_2;
		   		break;
		   		case"21:00":hora_mas_3=hora;
		   		hora_mas_1=hora;
		   		break;
		   		case"20:00":hora_mas_3=hora_mas_1;
		   		hora_mas_2="21:00";
		   		break;
		   		case"19:00":hora_mas_3=hora_mas_2;
		   		break;
		   		
		   		}
		   		
		   		switch(horaS) {
		   		case 8:horaS=horaS+1;
		   		break;
		   		}
		   		
		   		int horaT=horaS+1;
		   		switch(horaT) {
		   		case 22: horaT=horaT-1;
		   		break;
		   		}
		   		
		   		int horaJ=horaS-1;
		   		switch(horaJ) {
		   		case 8:horaJ=horaJ+1;
		   		break;
		   		}
		   		
		   	// System.out.println("horS "+horaS);
		   		
		   		
		   		
		   		
		   		
		   		
		   		String[] vector3=hora_mas_1.split(":"); 
			     String hora_mas_111=vector3[0].split(":")[0];
				int hora_mas_11= Integer.valueOf(hora_mas_111);
				
				String[] vector2=hora_mas_1.split(":"); 
			     String hora_mas_222=vector2[0].split(":")[0];
				int hora_mas_22= Integer.valueOf(hora_mas_222);
				
				
		   		
		   		String diaHora_menos_3 = date+" "+hora_menos_3+":00";
		   		String diaHora_mas_3 = date+" "+hora_mas_3+":00";

		   		//String diaHora_mas_1 = date+" "+hora_mas_1+":00";
		   		
		   		//String diaHora_mas_2 = date+" "+hora_mas_2+":00";
		   		
				//System.out.println("\n Esta es la hora (+3)  "+hora_mas_3);
				//System.out.printf("\n Esta es la hora (-3)  "+hora_menos_3);

				//System.out.println("\n diaHora_menos_3  "+diaHora_menos_3);
				//System.out.println("\n diaHora_mas_3   "+diaHora_mas_3);
		   		
		   	
		   		
		   		double prix;
				prix = Double.parseDouble(modelo.getPrecio((String)comboBoxInstalaciones.getSelectedItem()));										
				
				
				if(comboBoxMetodo.getSelectedIndex()==1) {
			          double k= modeloReservas.nuevaCuota(id_socioS);  
			          k = k+ prix;
			          modeloReservas.añadeacuota(k, id_socioS);
				
				}
				/*public boolean reservasMaxDia (String idSocio) {
				String fech = this.fecha.getSelectedItem().toString();
				List<> l=lista.getListaReservasUsuario(idSocio);
				int contador=0;
				int horai;
				int horaf;
				for(int i=0;i<l.size();i++) {
					if(l.get(i).getFecha().equals(fech)) {
						horaf=l.get(i).getHora_fin();
						horai=l.get(i).getHora_ini();
						contador=contador+(horaf-horai);
					}
				}		
				int max_horas=3;
				if(contador>= max_horas) return true;
				else return false;
			}
				*/
				 hora = (String)comboBoxHoraIni.getSelectedItem();
				List<Object[]> I= modeloReservas.getListaReservasUsuario(id_socioS, id);
				int contador=0;
				int Hora_Max=3;
				for(int k=0; k<I.size();k++){
					if(getFecha((I.get(k)[0]).toString()).equals(date)) {
						if( ( (getHora((I.get(k)[0]).toString())) <= ((getHora1(hora))+Hora_Max)) && ( (getHora((I.get(k)[0]).toString())) >= ((getHora1(hora))-Hora_Max))) {								
							System.out.println("\nHora Bucle"+(getHora((I.get(k)[0]).toString())));
						    System.out.println("\nHoraIni"+(getHora1(hora)-Hora_Max));
						    System.out.println("\nHoraFin"+(getHora1(hora)+Hora_Max));
							contador++;
							System.out.println("\nContador"+contador);
							
						}
					}		
				}
				
				boolean seguidas=true;
				
				if(contador>=3) {
					seguidas=false;
				}
				else 
					seguidas=true;
	
			
				
				
		if(seguidas) {
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					//obtener el precio de la instalacion seleccionada
					precio = modelo.getPrecio((String)comboBoxInstalaciones.getSelectedItem());										
					textFieldCoste.setText(precio);
					
					if (modeloClientes.validarId(id_socio)) {
						if (diferencia_dias >= 0 && diferencia_años >= 0) {
							if (diferencia_dias <= 15 || diferencia_años>0) {	
								  // System.out.printf("%d",);
									if ((modeloClientes.DebeDinero(id_socio))==1) {																																							
										   //CheckBoxPuedesReservar.setSelected(false);
										   JOptionPane.showMessageDialog(frmReservarInstalacin ," No se ha podido reservar. \n Está libre pero debes dinero. \n               ¡MOROSO! ","Error",JOptionPane.ERROR_MESSAGE);										   								              											
									}		
									else {																				
										//CheckBoxPuedesReservar.setSelected(true);
										JOptionPane.showMessageDialog(frmReservarInstalacin, "  Has reservado.\n"
												+ "  Precio de la reserva: "+precio
												+"\n  Socio que lo solicita: "+id_socio
												+"\n  Instalación a reservar: "+id
												+"\n  Fecha de reserva: "+diaHora
												+"\n  Selección de pago: "+comboBoxMetodo.getSelectedItem());	
										modeloReservas.nuevaReserva1(Integer.parseInt(id_socio), Integer.parseInt(id), sdf.format(d1), diaHora, precio ,0);
										ButtonReservar.setSelected(false);
									}
									
								//CheckBoxEstaLibre.setSelected(true);																
							}								
							else {
								JOptionPane.showMessageDialog(frmReservarInstalacin,
									    "No puedes reservar con más de 15 días de antelación.",
									    "No puedes reservar",
									    JOptionPane.ERROR_MESSAGE);								
							}	
						}
						else {
							JOptionPane.showMessageDialog(frmReservarInstalacin,
								    "No puedes reservar para una fecha ya pasada.",
								    "No puedes reservar",
								    JOptionPane.ERROR_MESSAGE);							
						}
				}
					else {
						JOptionPane.showMessageDialog(frmReservarInstalacin,
							    "Introduce un número de socio válido.",
							    "No puedes reservar",
							    JOptionPane.ERROR_MESSAGE);						
					}											
				}
				else {
					JOptionPane.showMessageDialog(frmReservarInstalacin,
						    "Está ocupado.",
						    "No puedes reservar",
						    JOptionPane.ERROR_MESSAGE);
					
				}
				
		}
		else {
			JOptionPane.showMessageDialog(frmReservarInstalacin,
				    "No puedes reservar más de 3h seguidas",
				    "Error Reservando",
				    JOptionPane.ERROR_MESSAGE);
		} 
		
		ButtonReservar.setEnabled(false);
		contador=0;
		System.out.println(contador);
		seguidas=true;
		
			
			
				
				
			}
		});
		
		JButton ButtonComprobar = new JButton("Comprobar\r\n");
		ButtonComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//id_instalacion
				String id;
				
				List<Object[]> lista=modelo.getIdInstalacion((String)comboBoxInstalaciones.getSelectedItem());
				String[] nombre=new String[lista.size()];
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}
				
				//Id instalacion
				id = nombre[0];
				//System.out.println(id);
				
				
				
				//fecha
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String date = sdf.format(dateChooserInicio.getDate());
				String hora = (String)comboBoxHoraIni.getSelectedItem();
				String diaHora = date+" "+hora;
				//System.out.println(diaHora);
				
				
		
				
				//fecha de hoy
				Date d1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				
				//diferencia entre las dos fechas
				long diferencia_dias= 0;
				long diferencia_años= 0;
				try {
					//pasar de string a fecha
		            Date d2 = sdf.parse(date);
		  
		            //Calcular la diferencia en milisegundos
		            long difference_In_Time
		                = d2.getTime() - d1.getTime();
		  
		            // Calcular la diferencia en segundos, minutos, horas, años y dias
		            long difference_In_Seconds
		                = (difference_In_Time
		                   / 1000)
		                  % 60;
		  
		            long difference_In_Minutes
		                = (difference_In_Time
		                   / (1000 * 60))
		                  % 60;
		  
		            long difference_In_Hours
		                = (difference_In_Time
		                   / (1000 * 60 * 60))
		                  % 24;
		  
		            long difference_In_Years
		                = (difference_In_Time
		                   / (1000l * 60 * 60 * 24 * 365));
		  
		            long difference_In_Days
		                = (difference_In_Time
		                   / (1000 * 60 * 60 * 24))
		                  % 365;
		            diferencia_dias = difference_In_Days;
		            diferencia_años = difference_In_Years;
		           
		        }
		  
		        // Catch the Exception
		        catch (ParseException excepcion) {
		            excepcion.printStackTrace();
		        }
				
				
					
	            //id del socio
				
				int id_socioS = vLogin.getId_socio();
				String id_socio = String.valueOf(id_socioS); 
		   			
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					//obtener el precio de la instalacion seleccionada
					precio = modelo.getPrecio((String)comboBoxInstalaciones.getSelectedItem());										
					textFieldCoste.setText(precio);
					
					if (modeloClientes.validarId(id_socio)) {
						if (diferencia_dias >= 0 && diferencia_años >= 0) {
							if (diferencia_dias <= 15 || diferencia_años>0) {	
								  // System.out.printf("%d",);
									if ((modeloClientes.DebeDinero(id_socio))==1) {																																							
										 //  CheckBoxPuedesReservar.setSelected(false);
										   JOptionPane.showMessageDialog(frmReservarInstalacin ," No se ha podido reservar. \n Está libre pero debes dinero. \n               ¡MOROSO! ","Error",JOptionPane.ERROR_MESSAGE);										   								              											
										   ButtonReservar.setEnabled(false);
									}		
									else {																				
										//CheckBoxPuedesReservar.setSelected(true);
										JOptionPane.showMessageDialog(frmReservarInstalacin, "  Podrías reservar.\n"
												+ "  Precio de la reserva: "+precio
												+"\n  Socio que lo solicita: "+id_socio
												+"\n  Instalación a reservar: "+id
												+"\n  Fecha de reserva: "+diaHora);	
										ButtonReservar.setEnabled(true);
									}
									
								//CheckBoxEstaLibre.setSelected(true);																
							}								
							else {
								JOptionPane.showMessageDialog(frmReservarInstalacin,
									    "No puedes reservar con más de 15 días de antelación.",
									    "No puedes reservar",
									    JOptionPane.ERROR_MESSAGE);	
								 ButtonReservar.setEnabled(false);
							}	
						}
						else {
							JOptionPane.showMessageDialog(frmReservarInstalacin,
								    "No puedes reservar para una fecha ya pasada.",
								    "No puedes reservar",
								    JOptionPane.ERROR_MESSAGE);	
							 ButtonReservar.setEnabled(false);
						}
				}
					else {
						JOptionPane.showMessageDialog(frmReservarInstalacin,
							    "Introduce un número de socio válido.",
							    "No puedes reservar",
							    JOptionPane.ERROR_MESSAGE);	
						 ButtonReservar.setEnabled(false);
					}											
				}
				else {
					JOptionPane.showMessageDialog(frmReservarInstalacin,
						    "Está ocupado.",
						    "No puedes reservar",
						    JOptionPane.ERROR_MESSAGE);
					 ButtonReservar.setEnabled(false);
					
				}
				
				
			}
		});
		ButtonComprobar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonComprobar.setBounds(161, 232, 110, 21);
		panel.add(ButtonComprobar);
		
		ButtonReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonReservar.setBounds(295, 232, 113, 21);
		panel.add(ButtonReservar);
	}



	public Window getFrmReservarInstalacin() {
		// TODO Auto-generated method stub
		return this.frmReservarInstalacin;
	}
	
	public String getFecha (String date) {
		String[] vector=date.split("T");
		String fecha = vector[0];
		
			return fecha;
		}


	public void setFecha(String date) {
			this.date = date;
		}
	
	public int getHora (String date) {
		String[] vector=date.split("T");
		date=vector[1].split(":")[0];
		int hora=Integer.parseInt(date);
		
		
			return hora;
		}

	public int getHora1 (String date) {
		
		  String[] vector1=date.split(":"); 
          date=vector1[0].split(":")[0];
	      int horaS= Integer.parseInt(date);
	 
	 return horaS;
	}
	

}
