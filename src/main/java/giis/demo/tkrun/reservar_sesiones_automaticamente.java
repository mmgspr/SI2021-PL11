package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.util.Calendar;

public class reservar_sesiones_automaticamente {

	private JFrame frmReservarSesionesAutomticamente;
	
	private SesionesModel modeloSesiones = new SesionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private ReservasModel modeloReservas = new ReservasModel();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservar_sesiones_automaticamente window = new reservar_sesiones_automaticamente();
					window.frmReservarSesionesAutomticamente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reservar_sesiones_automaticamente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarSesionesAutomticamente = new JFrame();
		frmReservarSesionesAutomticamente.setTitle("Reservar Automáticamente");
		frmReservarSesionesAutomticamente.setBounds(100, 100, 350, 200);
		frmReservarSesionesAutomticamente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservarSesionesAutomticamente.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//obtener todas las instalaciones
		List<Object[]> listaActividades=modeloActividades.getActividades();
				
		String[] actividades=new String[listaActividades.size()];
				
		Iterator<Object[]> iteradorActividades = listaActividades.iterator();
				
		int i=0;
		while(iteradorActividades.hasNext()) {
			actividades[i]=iteradorActividades.next()[0].toString();
			i++;
		}
		
		JLabel lblActividad = new JLabel("• Actividad:");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActividad.setBounds(10, 10, 79, 17);
		panel.add(lblActividad);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(actividades));
		comboBox.setBounds(99, 10, 160, 21);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Reservar automáticamente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Reservar sesiones
				
				List<Object[]> sesiones = modeloSesiones.getSesionesActividad(Long.toString(modeloActividades.getIdActividad(comboBox.getSelectedItem().toString())));
				String fecha_ini = modeloActividades.getFechaIniActividad(comboBox.getSelectedItem().toString());
				String fecha_fin = modeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString());
				//System.out.println(fecha_ini);
				Date ini=null;
				Date fin=null;
				try {
					ini = sdf.parse(fecha_ini);
					fin = sdf.parse(fecha_fin);
				} catch (ParseException e1) {
				}
				
				Calendar c_ini = Calendar.getInstance();
				Calendar c_hora = Calendar.getInstance();
				Locale es = new Locale("es", "ES");
				boolean encontrado=false;
				String diaHora=null;
				String horaFin=null;
				Date hi=null;
				Date hf=null;

				while(ini.getTime()-fin.getTime()<=0) {
					String diaSemana=getDayString(ini,es);
					Iterator<Object[]> it = sesiones.iterator();
					Object sesion[];
					while(it.hasNext()) {
						encontrado=false;
						sesion = (Object[])it.next();
						if (sesion[0].equals("miercoles")) {
							sesion[0]="miércoles";
						}
						if (sesion[0].equals("sabado")) {
							sesion[0]="sábado";
						}
						if (sesion[0].equals(diaSemana)) {
							diaHora=sdf.format(ini)+" "+ sesion[1];
							System.out.println(diaHora);
							horaFin=sdf.format(ini)+" "+ sesion[2];
							System.out.println(horaFin);
							try {
								hi=dh.parse(diaHora);
								System.out.println(dh.format(hi));
								hf=dh.parse(horaFin);
							} catch (ParseException e1) {
							}
							encontrado=true;
						}
						if (encontrado){
							//System.out.println("encontrado");
							while(hi.getTime()<hf.getTime()) {
								//System.out.println("menor hora final");
								int cliente = modeloReservas.comprobarDisponibilidadActividad(comboBox.getSelectedItem().toString(), diaHora);
								if (cliente==-1) {
									JOptionPane.showMessageDialog(frmReservarSesionesAutomticamente,
									    "Está ocupado por otra actividad.",
									    "Error al reservar",
									    JOptionPane.ERROR_MESSAGE);
								}
								else if (cliente == 0){
									//modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
									JOptionPane.showMessageDialog(frmReservarSesionesAutomticamente, "Reservado.\n");
								}
								else {
									//modeloReservas.eliminarReserva(Integer.parseInt(id), diaHora);
									//modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
									JOptionPane.showMessageDialog(frmReservarSesionesAutomticamente, "Estaba reservado por un cliente pero tienes prioridad.\n");
								}
								//SUMAMOS UNA HORA
								c_hora.setTime(hi);
								c_hora.add(Calendar.HOUR, 1);
								try {
									hi = dh.parse(dh.format(c_hora.getTime()));
									System.out.println(c_hora);
								} catch (ParseException e1) {	
								}
								
								
							}
						}
					}
					c_ini.setTime(ini);
					c_ini.add(Calendar.DATE, 1);
					try {
						ini = sdf.parse(sdf.format(c_ini.getTime()));
						System.out.println(getDayString(ini,es));
					} catch (ParseException e1) {	
					}
				}
				
				
				/*
				String diaSemana = getDayString(modeloActividades.getFechaIniActividad((String)comboBox.getSelectedItem()), new Locale("es", "ES"));
				System.out.println(diaSemana);
				Iterator<Object[]> it = sesiones.iterator();
				Object sesion[];
				boolean encontrado = false;
				while(it.hasNext()) {
					sesion = (Object[])it.next();
					if (sesion[0].equals(diaSemana)) {
						diaHora=sdf.format(dateChooser_1_1.getDate())+" "+ sesion[1];
						encontrado = true;
					}
				}
				if (encontrado == true){
					//diaHora=sdf.format(dateChooser_1_1.getDate())+" 20:00:00";
					int cliente = modeloReservas.comprobarDisponibilidadActividad(id, diaHora);
					if (cliente==-1) {
						JOptionPane.showMessageDialog(frmReservaParaActividad,
							    "Está ocupado por otra actividad.",
							    "Error al reservar",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if (cliente == 0){
						modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
						JOptionPane.showMessageDialog(frmReservaParaActividad, "Reservado.\n");
					}
					else {
						modeloReservas.eliminarReserva(Integer.parseInt(id), diaHora);
						modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
						JOptionPane.showMessageDialog(frmReservaParaActividad, "Estaba reservado por un cliente pero tienes prioridad.\n");
					}
				}
				else JOptionPane.showMessageDialog(frmReservaParaActividad, "La actividad elegida no tiene una sesión en el día especificado.\n");
				*/
			}
		});
		btnNewButton.setBounds(73, 75, 195, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(122, 127, 89, 23);
		panel.add(btnNewButton_1);
		
		
	}
	
	public static String getDayString(Date date, Locale locale) {
		DateFormat formatter = new SimpleDateFormat("EEEE", locale);
		return formatter.format(date);
	}
}
