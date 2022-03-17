package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import giis.demo.util.Database;
import giis.demo.util.SwingMain;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.Period;

public class crear_actividad {

	private JFrame frmCrearActividad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private InstalacionesModel modeloIns = new InstalacionesModel();
	private PeriodosInscripcionModel modeloPer = new PeriodosInscripcionModel();
	private ActividadesModel modeloAct = new ActividadesModel();
	private SesionesModel modeloSes = new SesionesModel();
	
	private crear_sesiones vSesiones;
	private crear_periodo_inscripcion vPeriodoIns;
	private List<String[]> sesionesLista;
	
	
	
	JComboBox comboBox_1_1;
	String[] periodosIns;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crear_actividad window = new crear_actividad();
					window.frmCrearActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public crear_actividad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearActividad = new JFrame();
		frmCrearActividad.setTitle("Planificar Actividad");
		frmCrearActividad.setBounds(100, 100, 700, 540);
		frmCrearActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		vSesiones= new crear_sesiones(this);
		vPeriodoIns= new crear_periodo_inscripcion(this);
		
		JPanel panel = new JPanel();
		frmCrearActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u2022 Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 67, 17);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(82, 11, 213, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("\u2022 Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcin.setBounds(10, 43, 87, 17);
		panel.add(lblDescripcin);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 70, 285, 116);
		panel.add(textArea);
		
		JLabel lblInstalacin = new JLabel("\u2022 Instalaci\u00F3n:");
		lblInstalacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstalacin.setBounds(10, 215, 87, 17);
		panel.add(lblInstalacin);
		
		List<Object[]> modIns=modeloIns.getInstalaciones();
		
		String[] instalaciones=new String[modIns.size()];
		
		Iterator<Object[]> iteradorIns = modIns.iterator();
		
		int iIns=0;
		while(iteradorIns.hasNext()) {
			instalaciones[iIns]=iteradorIns.next()[0].toString();
			iIns++;
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox.setBounds(96, 215, 199, 21);
		panel.add(comboBox);
		
		JLabel lblDeporte = new JLabel("• Tipo:");
		lblDeporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeporte.setBounds(10, 264, 73, 17);
		panel.add(lblDeporte);
		
		List<Object[]> modDep=modeloIns.getDeportes();
		
		String[] deportes=new String[modDep.size()];
		
		Iterator<Object[]> iteradorDep = modDep.iterator();
		
		int iDep=0;
		while(iteradorDep.hasNext()) {
			deportes[iDep]=iteradorDep.next()[0].toString();
			iDep++;
		}
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(deportes));
		comboBox_1.setBounds(61, 264, 199, 21);
		panel.add(comboBox_1);
		
		JLabel lblPlazas = new JLabel("\u2022 Plazas:");
		lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlazas.setBounds(10, 307, 61, 17);
		panel.add(lblPlazas);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(74, 308, 61, 20);
		panel.add(spinner);
		
		JLabel lblPrecio = new JLabel("\u2022 Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(372, 10, 61, 17);
		panel.add(lblPrecio);
		
		JLabel lblSocios = new JLabel("- Socios:");
		lblSocios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSocios.setBounds(400, 47, 61, 17);
		panel.add(lblSocios);
		
		JLabel lblNoSocios = new JLabel("- No socios:");
		lblNoSocios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoSocios.setBounds(400, 76, 76, 17);
		panel.add(lblNoSocios);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if((e.getKeyChar()>='0' && e.getKeyChar()<='9') || e.getKeyChar()==',' || e.getKeyChar()=='.') {
					if(e.getKeyChar()==',') {
						e.setKeyChar('.');
					}
				}
				else {
					e.setKeyChar((char)127);
				}
			}
		});
		textField_1.setBounds(462, 48, 61, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if((e.getKeyChar()>='0' && e.getKeyChar()<='9') || e.getKeyChar()==',' || e.getKeyChar()=='.') {
					if(e.getKeyChar()==',') {
						e.setKeyChar('.');
					}
				}
				else {
					e.setKeyChar((char)127);
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(483, 77, 61, 19);
		panel.add(textField_2);
		
		JLabel lblPrecio_1 = new JLabel("\u20AC");
		lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio_1.setBounds(533, 47, 14, 17);
		panel.add(lblPrecio_1);
		
		JLabel lblPrecio_1_1 = new JLabel("\u20AC");
		lblPrecio_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio_1_1.setBounds(554, 76, 14, 17);
		panel.add(lblPrecio_1_1);
		
		JLabel lblPeriodo = new JLabel("\u2022 Periodo:");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodo.setBounds(372, 122, 67, 17);
		panel.add(lblPeriodo);
		
		JLabel lblPeriodoDe = new JLabel("\u2022 Periodo de inscripci\u00F3n:");
		lblPeriodoDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodoDe.setBounds(372, 264, 172, 17);
		panel.add(lblPeriodoDe);
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(500, 180, 149, 19);
		panel.add(dateChooser);
		dateChooser.setEnabled(false);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(500, 213, 149, 19);
		panel.add(dateChooser_1);
		dateChooser_1.setEnabled(false);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(512, 353, 125, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(512, 381, 125, 20);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(512, 409, 125, 20);
		panel.add(textField_7);
		
		comboBox_1_1 = new JComboBox();
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String per_ins_selec=comboBox_1_1.getSelectedItem().toString();
				List<Object[]> per_ins_sel_lista=modeloPer.getFechas(per_ins_selec);
				String[][] p_i_s=new String[per_ins_sel_lista.size()][3];
				Iterator<Object[]> iteradorPerInsSel = per_ins_sel_lista.iterator();
				int iPerInsSel=0;
				while(iteradorPerInsSel.hasNext()) {
					//p_i_s[iPerInsSel]=iteradorPerInsSel.next()[0].toString();
					//iPerInsSel++;
					String[] vector = new String[3]; 
					Object[] r=iteradorPerInsSel.next();
					vector[0]=r[0].toString();
					vector[1]=r[1].toString();
					vector[2]=r[2].toString();
					for(int j=0;j<3;j++) {
						p_i_s[iPerInsSel][j]=vector[j];
					}
					iPerInsSel++;		
				}
				textField_5.setText(p_i_s[0][0]);
				textField_6.setText(p_i_s[0][1]);
				textField_7.setText(p_i_s[0][2]);
			}
		});
		getPeriodosIns();
		
		
		comboBox_1_1.setBounds(382, 291, 267, 21);
		panel.add(comboBox_1_1);
		
		String ini = "21-06-2022";
		Date inicial;
		try {
			inicial = new SimpleDateFormat("dd-MM-yyyy").parse(ini);
			dateChooser.setDate(inicial);
		} catch (ParseException e1) {
		}
		String fin = "23-09-2022";
		Date fina;
		try {
			fina = new SimpleDateFormat("dd-MM-yyyy").parse(fin);
			dateChooser_1.setDate(fina);
		} catch (ParseException e1) {
		}
		
		JLabel lblFechaInicial = new JLabel("- Fecha inicial:");
		lblFechaInicial.setEnabled(false);
		lblFechaInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaInicial.setBounds(400, 180, 90, 17);
		panel.add(lblFechaInicial);
		
		JLabel lblFechaFinal = new JLabel("- Fecha final:");
		lblFechaFinal.setEnabled(false);
		lblFechaFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaFinal.setBounds(400, 215, 87, 17);
		panel.add(lblFechaFinal);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_2.getSelectedIndex()==4) {
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
					lblFechaInicial.setEnabled(true);
					lblFechaFinal.setEnabled(true);
					dateChooser.setDate(null);
					dateChooser_1.setDate(null);
				}
				else {
					dateChooser.setDate(null);
					dateChooser_1.setDate(null);
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
					lblFechaInicial.setEnabled(false);
					lblFechaFinal.setEnabled(false);
					if(comboBox_2.getSelectedIndex()==0) {
						String vera = "21-06-2022";
						Date verano;
						try {
							verano = new SimpleDateFormat("dd-MM-yyyy").parse(vera);
							dateChooser.setDate(verano);
						} catch (ParseException e1) {
						}
						String oto = "23-09-2022";
						Date otono;
						try {
							otono = new SimpleDateFormat("dd-MM-yyyy").parse(oto);
							dateChooser_1.setDate(otono);
						} catch (ParseException e1) {
						}
					}
					if(comboBox_2.getSelectedIndex()==1) {
						String oto = "23-09-2022";
						Date otono;
						try {
							otono = new SimpleDateFormat("dd-MM-yyyy").parse(oto);
							dateChooser.setDate(otono);
						} catch (ParseException e1) {
						}
						String inv = "21-12-2022";
						Date invierno;
						try {
							invierno = new SimpleDateFormat("dd-MM-yyyy").parse(inv);
							dateChooser_1.setDate(invierno);
						} catch (ParseException e1) {
						}
					}
					if(comboBox_2.getSelectedIndex()==2) {
						String inv = "21-12-2022";
						Date invierno;
						try {
							invierno = new SimpleDateFormat("dd-MM-yyyy").parse(inv);
							dateChooser.setDate(invierno);
						} catch (ParseException e1) {
						}
						String prim = "20-03-2023";
						Date primavera;
						try {
							primavera = new SimpleDateFormat("dd-MM-yyyy").parse(prim);
							dateChooser_1.setDate(primavera);
						} catch (ParseException e1) {
						}
					}
					if(comboBox_2.getSelectedIndex()==3) {
						String prim = "20-03-2022";
						Date primavera;
						try {
							primavera = new SimpleDateFormat("dd-MM-yyyy").parse(prim);
							dateChooser.setDate(primavera);
						} catch (ParseException e1) {
						}
						String vera = "21-06-2022";
						Date verano;
						try {
							verano = new SimpleDateFormat("dd-MM-yyyy").parse(vera);
							dateChooser_1.setDate(verano);
						} catch (ParseException e1) {
						}
						
					}
					
				}
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Verano", "Otoño", "Invierno", "Primavera", "Otro..."}));
		comboBox_2.setBounds(382, 149, 267, 21);
		panel.add(comboBox_2);
		
		JButton btnNewButton = new JButton("Crear nuevo periodo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPeriodoIns.getFrmCrearPeriodoDe().setVisible(true);
			}
		});
		btnNewButton.setBounds(392, 322, 152, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat year = new SimpleDateFormat("yyyy");
				Date dateIni = dateChooser.getDate();
				Date dateFin = dateChooser_1.getDate();
				//fecha de hoy
				Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce un nombre.","Error",JOptionPane.ERROR_MESSAGE);
				}	
				else if(textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce una descripción.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce un precio para socios. ","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce un precio para no socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!precioCorrecto(textField_1.getText())) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce un precio de socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!precioCorrecto(textField_2.getText())) {
					JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce un precio de no socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}		
				else if(comboBox_2.getSelectedIndex()==4) {
					if(dateIni==null) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce una fecha inicial de periodo.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin==null) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nIntroduce una fecha final de periodo.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateIni.getTime()-dateHoy.getTime()<0) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nLa fecha inicial no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin.getTime()-dateHoy.getTime()<0) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nLa fecha final no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin.getTime()-dateIni.getTime()<0) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad. \nLa fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String nombre=textField.getText();
						String descripcion=textArea.getText();
						String fecha_ini=sdf.format(dateIni);
						String fecha_fin=sdf.format(dateFin);
						String aforo=spinner.getModel().getValue().toString();
						String pSoc=textField_1.getText();
						String pNoSoc=textField_2.getText();
						String deporte=comboBox_1.getSelectedItem().toString();
						String instalacion_nombre=comboBox.getSelectedItem().toString();
						List<Object[]> instalacion_lista=modeloIns.getIdInstalacion(instalacion_nombre);
						String[] instal=new String[instalacion_lista.size()];
						Iterator<Object[]> iteradorIns = instalacion_lista.iterator();
						int iIns=0;
						while(iteradorIns.hasNext()) {
							instal[iIns]=iteradorIns.next()[0].toString();
							iIns++;
						}
						String instalacion=instal[0];
						String per_ins_nombre=comboBox_1_1.getSelectedItem().toString();
						List<Object[]> per_ins_lista=modeloPer.getIdPeriodoIns(per_ins_nombre);
						String[] p_i=new String[per_ins_lista.size()];
						Iterator<Object[]> iteradorPerIns = per_ins_lista.iterator();
						int iPerIns=0;
						while(iteradorPerIns.hasNext()) {
							p_i[iPerIns]=iteradorPerIns.next()[0].toString();
							iPerIns++;
						}
						String per_ins=p_i[0];
						try {
							long id_act=modeloAct.nuevaActividadRetornaId(nombre, descripcion, aforo, pSoc, pNoSoc, fecha_ini, fecha_fin, deporte, instalacion, per_ins);
							sesionesLista=vSesiones.getSesionesLista();
							Iterator<String[]> iter=sesionesLista.iterator();
							while(iter.hasNext()) {
								String vector[]=iter.next();
								//System.out.printf("%s - %s - %s\n", vector[0],vector[1]+":00",vector[2]+":00");
								modeloSes.nuevaSesion(vector[0], vector[1]+":00", vector[2]+":00", id_act);
							}
							JOptionPane.showMessageDialog(frmCrearActividad,"La actividad se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
							frmCrearActividad.dispose();
						} catch (Exception eActividad) {
							JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad.\n","Error.",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}		
				else {			
					String nombre=textField.getText();
					String descripcion=textArea.getText();
					String fecha_ini=sdf.format(dateHoy);
					String fecha_fin=sdf.format(dateHoy);				
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateHoy);
					calendar.add(Calendar.YEAR, -1);
					Date año_ant=calendar.getTime();
					if(comboBox_2.getSelectedIndex()==0) {
						fecha_ini=year.format(dateHoy)+"-6-21";
						fecha_fin=year.format(dateHoy)+"-9-23";
					}
					else if(comboBox_2.getSelectedIndex()==1) {
						fecha_ini=year.format(dateHoy)+"-9-23";
						fecha_fin=year.format(dateHoy)+"-12-21";
					}
					else if(comboBox_2.getSelectedIndex()==2) {
						fecha_ini=year.format(año_ant)+"-12-21";
						fecha_fin=year.format(dateHoy)+"-3-20";
					}
					else if(comboBox_2.getSelectedIndex()==3) {
						fecha_ini=year.format(dateHoy)+"-3-20";
						fecha_fin=year.format(dateHoy)+"-6-21";
					}
					//int aforo=(int) spinner.getModel().getValue();
					String aforo=spinner.getModel().getValue().toString();
					String pSoc=textField_1.getText();
					String pNoSoc=textField_2.getText();
					String deporte=comboBox_1.getSelectedItem().toString();
					String instalacion_nombre=comboBox.getSelectedItem().toString();
					List<Object[]> instalacion_lista=modeloIns.getIdInstalacion(instalacion_nombre);
					String[] instal=new String[instalacion_lista.size()];
					Iterator<Object[]> iteradorIns = instalacion_lista.iterator();
					int iIns=0;
					while(iteradorIns.hasNext()) {
						instal[iIns]=iteradorIns.next()[0].toString();
						iIns++;
					}
					String instalacion=instal[0];
					String per_ins_nombre=comboBox_1_1.getSelectedItem().toString();
					List<Object[]> per_ins_lista=modeloPer.getIdPeriodoIns(per_ins_nombre);
					String[] p_i=new String[per_ins_lista.size()];
					Iterator<Object[]> iteradorPerIns = per_ins_lista.iterator();
					int iPerIns=0;
					while(iteradorPerIns.hasNext()) {
						p_i[iPerIns]=iteradorPerIns.next()[0].toString();
						iPerIns++;
					}
					String per_ins=p_i[0];
					try {
						long id_act=modeloAct.nuevaActividadRetornaId(nombre, descripcion, aforo, pSoc, pNoSoc, fecha_ini, fecha_fin, deporte, instalacion, per_ins);	
						sesionesLista=vSesiones.getSesionesLista();
						Iterator<String[]> iter=sesionesLista.iterator();
						while(iter.hasNext()) {
							String vector[]=iter.next();
							//System.out.printf("%s - %s - %s\n", vector[0],vector[1]+":00",vector[2]+":00");
							modeloSes.nuevaSesion(vector[0], vector[1]+":00", vector[2]+":00", id_act);
						}
						JOptionPane.showMessageDialog(frmCrearActividad,"La actividad se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);
						frmCrearActividad.dispose();
					} catch (Exception eActividad) {
						JOptionPane.showMessageDialog(frmCrearActividad,"No se ha podido crear la actividad.\n","Error.",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnNewButton_1.setBounds(589, 469, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancelar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCrearActividad.dispose();
			}
		});
		btnNewButton_1_1.setBounds(10, 469, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Crear sesiones");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vSesiones.getFrmCrearSesiones().setVisible(true);
			}
		});
		btnNewButton_1_1_1.setBounds(10, 346, 135, 21);
		panel.add(btnNewButton_1_1_1);
		
		JLabel lblfechaIniSocios = new JLabel("- Fecha ini. socios:");
		lblfechaIniSocios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfechaIniSocios.setBounds(382, 354, 108, 17);
		panel.add(lblfechaIniSocios);
		
		JLabel lblfechaFinSocios = new JLabel("- Fecha fin socios:");
		lblfechaFinSocios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfechaFinSocios.setBounds(382, 382, 108, 17);
		panel.add(lblfechaFinSocios);
		
		JLabel lblfechaFinNo = new JLabel("- Fecha fin no socios:");
		lblfechaFinNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfechaFinNo.setBounds(382, 410, 125, 17);
		panel.add(lblfechaFinNo);
		
		
			
		
	}

	public Window getFrmCrearActividad() {
		// TODO Auto-generated method stub
		return this.frmCrearActividad;
	}
	
	public void getPeriodosIns() {
		List<Object[]> modPer=modeloPer.getPeriodosIns();
		
		periodosIns=new String[modPer.size()];
		
		Iterator<Object[]> iteradorPer = modPer.iterator();
		
		int iPer=0;
		while(iteradorPer.hasNext()) {
			periodosIns[iPer]=iteradorPer.next()[0].toString();
			iPer++;
		}
		
		comboBox_1_1.setModel(new DefaultComboBoxModel(periodosIns));
	}
	
	public boolean precioCorrecto(String precio) {
		int puntos=0;
		if(precio.charAt(0)=='.') {
			return false;
		}
		for(int i=1;i<precio.length();i++) {
			if(precio.charAt(i)=='.') {
				puntos++;
			}		
		}
		if(puntos!=1) return false;
		if(precio.charAt(precio.length()-3)!='.') {
			return false;
		}
		return true;
	}
}

