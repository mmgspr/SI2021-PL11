package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import giis.demo.util.SwingMain;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class reserva_actividad_admin {

	private JFrame frmReservaParaActividad;
	
	//modelos
	private InstalacionesModel modelo = new InstalacionesModel();
	private ReservasModel modeloReservas = new ReservasModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private int i;
	
	//pantalla principal
	private SwingMain principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserva_actividad_admin window = new reserva_actividad_admin();
					window.frmReservaParaActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the application.
	 */
	public reserva_actividad_admin() {
		initialize();
	}
	
	public reserva_actividad_admin(SwingMain principal) {
		initialize();
		this.principal = principal;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaParaActividad = new JFrame();
		frmReservaParaActividad.setTitle("Reserva para actividad");
		frmReservaParaActividad.setBounds(100, 100, 700, 510);
		frmReservaParaActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//obtener todas las instalaciones
				List<Object[]> lista=modelo.getInstalaciones();
				
				String[] instalaciones=new String[lista.size()];
				
				Iterator<Object[]> iterador = lista.iterator();
				
				i=0;
				while(iterador.hasNext()) {
					instalaciones[i]=iterador.next()[0].toString();
					i++;
				}
		
		//obtener todas las instalaciones
		List<Object[]> listaActividades=modeloActividades.getActividades();
		
		String[] actividades=new String[listaActividades.size()];
		
		Iterator<Object[]> iteradorActividades = listaActividades.iterator();
		
		i=0;
		while(iteradorActividades.hasNext()) {
			actividades[i]=iteradorActividades.next()[0].toString();
			i++;
		}
				
		
		
		JPanel panel = new JPanel();
		frmReservaParaActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservaParaActividad.dispose();
			}
		});
		btnNewButton.setBounds(28, 439, 89, 23);
		panel.add(btnNewButton);
		
			
		JLabel lblNewLabel = new JLabel("Seleccione actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(46, 79, 135, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSeleccionePeriodo = new JLabel("Seleccione instalación:");
		lblSeleccionePeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo.setBounds(46, 153, 163, 14);
		panel.add(lblSeleccionePeriodo);
		
		JLabel lblSeleccionePeriodo_1 = new JLabel("Seleccione fecha inicial:");
		lblSeleccionePeriodo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo_1.setBounds(46, 229, 177, 14);
		panel.add(lblSeleccionePeriodo_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(actividades));
		comboBox.setBounds(242, 79, 156, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox_1.setBounds(242, 151, 156, 22);
		panel.add(comboBox_1);
		
		JLabel lblSeleccionePeriodo_1_1 = new JLabel("Seleccione fecha inicial:");
		lblSeleccionePeriodo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo_1_1.setBounds(46, 298, 163, 14);
		panel.add(lblSeleccionePeriodo_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(242, 229, 156, 19);
		panel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(242, 298, 156, 19);
		panel.add(dateChooser_1);
		
		JButton btnNewButton_1 = new JButton("Reservar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//id_instalacion
				String id;
				
				List<Object[]> lista=modelo.getIdInstalacion((String)comboBox_1.getSelectedItem());
				String[] nombre=new String[lista.size()];
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}
				id = nombre[0];
				
				
				//fecha inicio
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = sdf.format(dateChooser.getDate());
				
				//fecha fin
				String date2 = sdf.format(dateChooser_1.getDate());
				
				//fecha de hoy
				Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				//diferencia entre fecha inicio y fin
				long diferencia_dias= 0;
				long diferencia_años= 0;
				Date d1, d2;
				try {
					//pasar de string a fecha
		            d1 = sdf.parse(date1);
		            d2 = sdf.parse(date2);
		  
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
				
				
				//Comprobar si está ocupada por una actividad
				int dia = d1.getDay();
				Calendar c = Calendar.getInstance();
				c.setTime(d1);
				String diaHora;
				for (i=0; i<diferencia_dias+diferencia_años*365;i++) {
					
					
					
					//FALTA AÑADIR LA HORA
					diaHora= sdf.format(c.get(Calendar.DAY_OF_MONTH));
					
					
					
					if (modeloReservas.comprobarDisponibilidadActividad(id, diaHora) == 0) {
						if (modeloClientes.validarId(id_socio))
							if (diferencia_dias >= 0 && diferencia_años >= 0) {
								if (diferencia_dias <= 15 || diferencia_años>0) {
									modeloReservas.nuevaReserva(Integer.parseInt(id_socio), Integer.parseInt(id), sdf.format(d1), diaHora, precio ,0);
									JOptionPane.showMessageDialog(frmReservaParaActividad, "Éxito al reservar.\n"
											+ "  Precio de la reserva: "+precio
											+"\n  Socio que lo solicita: "+id_socio
											+"\n  Instalación reservada: "+id
											+"\n  Reserva para: "+diaHora);
									//System.out.printf("Correcto, has podido reservar, y tiene un coste de %s.\n",precio);
								}
								else {
									JOptionPane.showMessageDialog(frmReservaParaActividad,
										    "No puedes reservar con más de 15 días de antelación.",
										    "Error al reservar",
										    JOptionPane.ERROR_MESSAGE);
									//System.out.println("No puedes reservar con más de 15 días de antelación.");
								}	
							}
							else {
								JOptionPane.showMessageDialog(frmReservaParaActividad,
									    "No puedes reservar para una fecha ya pasada.",
									    "Error al reservar",
									    JOptionPane.ERROR_MESSAGE);
								//System.out.println("No puedes reservar para una fecha ya pasada.");
							}
								
						else {
							JOptionPane.showMessageDialog(frmReservaParaActividad,
								    "Introduce un número de socio válido.",
								    "Error al reservar",
								    JOptionPane.ERROR_MESSAGE);
							//System.out.println("Introduce un número de socio válido.");
						}
							
						
					}
					else {
						JOptionPane.showMessageDialog(frmReservaParaActividad,
							    "Está ocupado.",
							    "Error al reservar",
							    JOptionPane.ERROR_MESSAGE);
						//System.out.println("Está ocupado.");
					}
				}
					
			
					//c.add(Calendar.DATE, 1);
					c.add(c.DAY_OF_MONTH, 1);
				}
							
			
		});
		btnNewButton_1.setBounds(567, 439, 89, 23);
		panel.add(btnNewButton_1);


	}

	public Window getFrmReservaActividad() {
		// TODO Auto-generated method stub
		return frmReservaParaActividad;
	}
}
