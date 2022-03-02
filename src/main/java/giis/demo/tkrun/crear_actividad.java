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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import giis.demo.util.Database;
import giis.demo.util.SwingMain;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class crear_actividad {

	private JFrame frmCrearActividad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private InstalacionesModel modeloIns = new InstalacionesModel();
	private PeriodosInscripcionModel modeloPer = new PeriodosInscripcionModel();
	
	private crear_sesiones vSesiones;
	private crear_periodo_inscripcion vPeriodoIns;
	private SwingMain principal;
	
	JComboBox comboBox_1_1;
	String[] periodosIns;

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
	
	public crear_actividad(SwingMain principal) {
		initialize();
		this.principal = principal;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearActividad = new JFrame();
		frmCrearActividad.setTitle("Planificar Actividad");
		frmCrearActividad.setBounds(100, 100, 700, 500);
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
		
		JLabel lblDeporte = new JLabel("\u2022 Deporte:");
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
		comboBox_1.setBounds(84, 264, 199, 21);
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
		
		comboBox_1_1 = new JComboBox();
		getPeriodosIns();
		
		
		comboBox_1_1.setBounds(382, 291, 267, 21);
		panel.add(comboBox_1_1);
		
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
				}
				else {
					dateChooser.setDate(null);
					dateChooser_1.setDate(null);
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
					lblFechaInicial.setEnabled(false);
					lblFechaFinal.setEnabled(false);
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
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce un nombre.","Error",JOptionPane.ERROR_MESSAGE);
				}	
				else if(textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce una descripción.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce un precio para socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce un precio para no socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!precioCorrecto(textField_1.getText())) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce un precio de socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!precioCorrecto(textField_2.getText())) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce un precio de no socio válido.","Error",JOptionPane.ERROR_MESSAGE);
				}		
				else if(comboBox_2.getSelectedIndex()==4) {
					if(dateIni==null) {
						JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce una fecha inicial de periodo.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin==null) {
						JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nIntroduce una fecha final de periodo.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateIni.getTime()-dateHoy.getTime()<0) {
						JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nLa fecha inicial no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin.getTime()-dateHoy.getTime()<0) {
						JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nLa fecha final no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(dateFin.getTime()-dateIni.getTime()<0) {
						JOptionPane.showMessageDialog(null,"No se ha podido crear la actividad. \nLa fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
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
						//System.out.printf("%s", instalacion);
						String per_ins_nombre=comboBox_1_1.getSelectedItem().toString();
						List<Object[]> per_ins_lista=modeloPer.getIdPeriodoIns(per_ins_nombre);
						String[] p_i=new String[per_ins_lista.size()];
						Iterator<Object[]> iteradorPerIns = per_ins_lista.iterator();
						int iPerIns=0;
						while(iteradorPerIns.hasNext()) {
							p_i[iPerIns]=iteradorPerIns.next()[0].toString();
							iIns++;
						}
						String per_ins=p_i[0];
						//System.out.printf("%s", per_ins);
						JOptionPane.showMessageDialog(null,"La actividad se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
						frmCrearActividad.dispose();
					}
					
				}		
				else {			
					String nombre=textField.getText();
					String descripcion=textArea.getText();
					if(comboBox_2.getSelectedIndex()==0) {
						String fecha_ini=year.format(dateHoy)+"-6-21";
						String fecha_fin=year.format(dateHoy)+"-9-23";
					}
					else if(comboBox_2.getSelectedIndex()==1) {
						String fecha_ini=year.format(dateHoy)+"-9-23";
						String fecha_fin=year.format(dateHoy)+"-12-21";
					}
					else if(comboBox_2.getSelectedIndex()==2) {
						String fecha_ini=year.format(dateHoy)+"-12-21";
						String fecha_fin=year.format(dateHoy)+"-3-20";
					}
					else if(comboBox_2.getSelectedIndex()==3) {
						String fecha_ini=year.format(dateHoy)+"-3-20";
						String fecha_fin=year.format(dateHoy)+"-6-21";
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
					//System.out.printf("%s", instalacion);
					String per_ins_nombre=comboBox_1_1.getSelectedItem().toString();
					List<Object[]> per_ins_lista=modeloPer.getIdPeriodoIns(per_ins_nombre);
					String[] p_i=new String[per_ins_lista.size()];
					Iterator<Object[]> iteradorPerIns = per_ins_lista.iterator();
					int iPerIns=0;
					while(iteradorPerIns.hasNext()) {
						p_i[iPerIns]=iteradorPerIns.next()[0].toString();
						iIns++;
					}
					String per_ins=p_i[0];
					JOptionPane.showMessageDialog(null,"La actividad se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
					frmCrearActividad.dispose();
				}
				
			}
		});
		btnNewButton_1.setBounds(591, 432, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancelar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCrearActividad.dispose();
			}
		});
		btnNewButton_1_1.setBounds(10, 432, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Crear sesiones");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vSesiones.getFrmCrearSesiones().setVisible(true);
			}
		});
		btnNewButton_1_1_1.setBounds(10, 346, 135, 21);
		panel.add(btnNewButton_1_1_1);
			
		
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

