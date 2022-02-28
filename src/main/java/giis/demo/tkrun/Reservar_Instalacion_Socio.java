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
	private SwingMain principal;
	private String precio="";
	private JTextField textFieldSocio;

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
	public Reservar_Instalacion_Socio(SwingMain principal) {
		initialize();
		this.principal = principal;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarInstalacin = new JFrame();
		frmReservarInstalacin.setTitle("Reservar Instalación");
		frmReservarInstalacin.setBounds(100, 100, 450, 300);
		frmReservarInstalacin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//obtener todas las instalaciones
				List<Object[]> lista=modelo.getInstalaciones();
				
				String[] instalaciones=new String[lista.size()];
				
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					instalaciones[i]=iterador.next()[0].toString();
					i++;
				}
				
	 //Obetener coste de la instalacion seleccionada
				
		
		JPanel panel = new JPanel();
		frmReservarInstalacin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelFechadeReserva = new JLabel("Fecha de la Reserva:");
		LabelFechadeReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFechadeReserva.setBounds(10, 10, 141, 27);
		panel.add(LabelFechadeReserva);
		
		JLabel LabelCoste = new JLabel("Coste de la Reserva:");
		LabelCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelCoste.setBounds(10, 159, 141, 29);
		panel.add(LabelCoste);
		
		textFieldCoste = new JTextField();
		textFieldCoste.setEditable(false);
		//textFieldCoste.setModel(new DefaultComboBoxModel(instalaciones));
		textFieldCoste.setBounds(161, 166, 93, 19);
		panel.add(textFieldCoste);
		textFieldCoste.setColumns(10);
		
		JLabel LabelMetododePago = new JLabel("Método de Pago:");
		LabelMetododePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelMetododePago.setBounds(10, 192, 113, 29);
		panel.add(LabelMetododePago);
		
		JCheckBox CheckBoxEstaLibre = new JCheckBox("Está libre");
		CheckBoxEstaLibre.setEnabled(false);
		CheckBoxEstaLibre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxEstaLibre.setBounds(161, 139, 93, 21);
		panel.add(CheckBoxEstaLibre);
		
		JCheckBox CheckBoxPuedesReservar = new JCheckBox("Puedes Reservar");
		CheckBoxPuedesReservar.setEnabled(false);
		CheckBoxPuedesReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxPuedesReservar.setBounds(278, 137, 152, 21);
		panel.add(CheckBoxPuedesReservar);
		
		JComboBox comboBoxMetodo = new JComboBox();
		comboBoxMetodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMetodo.setModel(new DefaultComboBoxModel(new String[] {"Pagar ahora", "Añadir a mi cuota"}));
		comboBoxMetodo.setBounds(146, 196, 170, 21);
		panel.add(comboBoxMetodo);
		
		JButton ButtonCancelar = new JButton("Cancelar\r\n");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservarInstalacin.dispose();
			}
		});
		
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(182, 232, 103, 21);
		panel.add(ButtonCancelar);
		
	
		JLabel LabelInstalacion = new JLabel("Seleccione Instalación:");
		LabelInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelInstalacion.setBounds(10, 103, 152, 30);
		panel.add(LabelInstalacion);
		
		JComboBox comboBoxInstalaciones = new JComboBox();
		comboBoxInstalaciones.setModel(new DefaultComboBoxModel(instalaciones));
		comboBoxInstalaciones.setBounds(161, 110, 141, 21);
		panel.add(comboBoxInstalaciones);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(159, 18, 113, 19);
		panel.add(dateChooserInicio);
		
		JLabel LabelHoraIni = new JLabel("Hora de Inicio:\r\n");
		LabelHoraIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelHoraIni.setBounds(10, 47, 113, 19);
		panel.add(LabelHoraIni);
		
		JComboBox comboBoxHoraIni = new JComboBox();
		comboBoxHoraIni.setModel(new DefaultComboBoxModel(new String[] {"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"}));
		comboBoxHoraIni.setBounds(158, 48, 85, 21);
		panel.add(comboBoxHoraIni);
		
		JLabel LabelFin = new JLabel("Hora de Fin:");
		LabelFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFin.setBounds(10, 76, 85, 17);
		panel.add(LabelFin);
		
		JComboBox comboBoxHoraFin = new JComboBox();
		comboBoxHoraFin.setModel(new DefaultComboBoxModel(new String[] {"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"}));
		comboBoxHoraFin.setBounds(159, 79, 85, 21);
		panel.add(comboBoxHoraFin);
		
		JButton ButtonReservar = new JButton("Reservar\r\n");
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
				
				id = nombre[0];
				System.out.println(id);
				
				
				
				//fecha
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooserInicio.getDate());
				String hora = (String)comboBoxHoraIni.getSelectedItem();
				String diaHora = date+" "+hora;
				System.out.println(diaHora);
				
				
				//id del socio
				String id_socio;
				id_socio = textFieldSocio.getText();
				
				
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
				//obtener el precio de la instalacion seleccionada
				precio = modelo.getPrecio((String)comboBoxInstalaciones.getSelectedItem());
						
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					if (modeloClientes.validarId(id_socio))
						if (diferencia_dias >= 0 && diferencia_años >= 0) {
							if (diferencia_dias <= 15 || diferencia_años>0) {
								String seleccion = "<selecciona uno>";
								//if(comboBoxMetodo.getSelectedItem() = "<selecciona uno>")
								modeloReservas.nuevaReserva(Integer.parseInt(id_socio), Integer.parseInt(id), sdf.format(d1), diaHora, precio ,0);
								JOptionPane.showMessageDialog(frmReservarInstalacin, "Éxito al reservar.\n"
										+ "  Precio de la reserva: "+precio
										+"\n  Socio que lo solicita: "+id_socio
										+"\n  Instalación reservada: "+id
										+"\n  Reserva para: "+diaHora
										+"\n Método de Pago: "+comboBoxMetodo.getSelectedItem());
								//System.out.printf("Correcto, has podido reservar, y tiene un coste de %s.\n",precio);
							}
							else {
								JOptionPane.showMessageDialog(frmReservarInstalacin,
									    "No puedes reservar con más de 15 días de antelación.",
									    "Error al reservar",
									    JOptionPane.ERROR_MESSAGE);
								//System.out.println("No puedes reservar con más de 15 días de antelación.");
							}	
						}
						else {
							JOptionPane.showMessageDialog(frmReservarInstalacin,
								    "No puedes reservar para una fecha ya pasada.",
								    "Error al reservar",
								    JOptionPane.ERROR_MESSAGE);
							//System.out.println("No puedes reservar para una fecha ya pasada.");
						}
							
					else {
						JOptionPane.showMessageDialog(frmReservarInstalacin,
							    "Introduce un número de socio válido.",
							    "Error al reservar",
							    JOptionPane.ERROR_MESSAGE);
						//System.out.println("Introduce un número de socio válido.");
					}
						
					
				}
				else {
					JOptionPane.showMessageDialog(frmReservarInstalacin,
						    "Está ocupado.",
						    "Error al reservar",
						    JOptionPane.ERROR_MESSAGE);
					//System.out.println("Está ocupado.");
				}
				
				
				
				
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
				
				id = nombre[0];
				System.out.println(id);
				
				
				
				//fecha
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooserInicio.getDate());
				String hora = (String)comboBoxHoraIni.getSelectedItem();
				String diaHora = date+" "+hora;
				System.out.println(diaHora);
				
				
				//id del socio
				String id_socio;
				id_socio = textFieldSocio.getText();
				
				
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
				//Compobamos si está al corriente de pago
				if (modeloClientes.DebeDinero(id_socio))
					CheckBoxPuedesReservar.setSelected(true);
										
				//obtener el precio de la instalacion seleccionada
				precio = modelo.getPrecio((String)comboBoxInstalaciones.getSelectedItem());
				
				textFieldCoste.setText(precio);
						
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					if (modeloClientes.validarId(id_socio))
						if (diferencia_dias >= 0 && diferencia_años >= 0) {
							if (diferencia_dias <= 15 || diferencia_años>0) {
								CheckBoxEstaLibre.setSelected(true);
								//modeloReservas.nuevaReserva(Integer.parseInt(id_socio), Integer.parseInt(id), sdf.format(d1), diaHora, precio ,0);
								JOptionPane.showMessageDialog(frmReservarInstalacin, "  Podrías reservar.\n"
										+ "  Precio de la reserva: "+precio
										+"\n  Socio que lo solicita: "+id_socio
										+"\n  Instalación a reservar: "+id
										+"\n  Fecha de reserva: "+diaHora);
								
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
					//System.out.println("Está ocupado.");
				}
				
				
			}
		});
		ButtonComprobar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonComprobar.setBounds(10, 139, 110, 21);
		panel.add(ButtonComprobar);
		
		ButtonReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonReservar.setBounds(313, 232, 113, 21);
		panel.add(ButtonReservar);
		
		JLabel lblNDeSocio = new JLabel("Nº de socio:");
		lblNDeSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNDeSocio.setBounds(309, 49, 85, 14);
		panel.add(lblNDeSocio);
		
		textFieldSocio = new JTextField();
		textFieldSocio.setColumns(10);
		textFieldSocio.setBounds(298, 73, 96, 20);
		panel.add(textFieldSocio);
	}



	public Window getFrmReservarInstalacin() {
		// TODO Auto-generated method stub
		return this.frmReservarInstalacin;
	}
}
