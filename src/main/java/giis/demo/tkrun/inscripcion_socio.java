package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inscripcion_socio {

	private JFrame frmInscripcinActividadSocio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ReservasModel modeloReservas = new ReservasModel();
	private PagosModel modeloPagos = new PagosModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private InscripcionesModel modeloInscripciones = new InscripcionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel modeloPeriodosInscripcion = new PeriodosInscripcionModel();
	private EsperasModel modeloEsperas = new EsperasModel();
	private Login vLogin;
	
	int id_socio;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	String hoy = sdf.format(dateHoy);
	List<String> todasAct = new ArrayList<String>();
	private JTextField textField_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inscripcion_socio window = new inscripcion_socio();
					window.frmInscripcinActividadSocio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inscripcion_socio() {
		initialize();
	}
	
	public inscripcion_socio(Login login) {
		initialize();
		this.vLogin = login;
		this.id_socio=this.vLogin.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInscripcinActividadSocio = new JFrame();
		frmInscripcinActividadSocio.setTitle("Inscripción actividad socio");
		frmInscripcinActividadSocio.setBounds(100, 100, 450, 300);
		frmInscripcinActividadSocio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmInscripcinActividadSocio.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("• Actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 71, 14);
		panel.add(lblNewLabel);
		
		
		
		//PERIODOS DE INSCRIPCION ABIERTOS
		List<Object[]> modPer=modeloPeriodosInscripcion.getFechasSocio(hoy);
		Iterator<Object[]> iteradorPer = modPer.iterator();
		while(iteradorPer.hasNext()) {
			//ACTIVIDADES DE CADA PERIODO
			List<Object[]> modAct=modeloActividades.getActividadesPeriodoIns(iteradorPer.next()[0].toString());
			Iterator<Object[]> iteradorAct = modAct.iterator();
			while(iteradorAct.hasNext()) {
				//Problema
				//System.out.println(iteradorAct.next()[0].toString());
				todasAct.add(iteradorAct.next()[0].toString());
			}
		}
		//METEMOS ACTIVIDADES EN VECTOR
		String [] actividades=new String[todasAct.size()];
		Iterator<String> iteradorTodas = todasAct.iterator();
		int iTodas=0;
		while(iteradorTodas.hasNext()) {
			//todasAct.add(iteradorTodas.next());
			actividades[iTodas]=iteradorTodas.next();	
			//System.out.println(actividades[iTodas]);
			iTodas++;
		}
		
		
		
		
		
		
		
		
		JLabel lblDescripcin = new JLabel("- Descripción:");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcin.setBounds(20, 40, 89, 14);
		panel.add(lblDescripcin);
		
		JLabel lblPrecio = new JLabel("- Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(20, 101, 65, 14);
		panel.add(lblPrecio);
		
		JLabel lblFechaInicial = new JLabel("- Fecha inicial:");
		lblFechaInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaInicial.setBounds(201, 101, 89, 14);
		panel.add(lblFechaInicial);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(103, 40, 321, 50);
		panel.add(textPane);
		
		JLabel lblFechaFinal = new JLabel("- Fecha final:");
		lblFechaFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaFinal.setBounds(201, 137, 89, 14);
		panel.add(lblFechaFinal);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(81, 99, 58, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(284, 99, 118, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(277, 135, 112, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(67, 125, 65, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(145, 102, 18, 14);
		panel.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pagar ahora");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(10, 156, 109, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnAadirAMi = new JRadioButton("Añadir a mi cuota");
		buttonGroup.add(rdbtnAadirAMi);
		rdbtnAadirAMi.setBounds(10, 182, 129, 23);
		panel.add(rdbtnAadirAMi);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Cambio");
				textPane.setText(modeloActividades.getDescripcionActividad(comboBox.getSelectedItem().toString()));
				textField.setText(modeloActividades.getPrecioActividadSocio(comboBox.getSelectedItem().toString()));
				textField_1.setText(modeloActividades.getFechaIniActividad(comboBox.getSelectedItem().toString()));
				textField_2.setText(modeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString()));
				textField_3.setText(modeloActividades.getPlazasActividad(comboBox.getSelectedItem().toString()));
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(actividades));
		comboBox.setBounds(81, 7, 129, 22);
		panel.add(comboBox);
		
		//System.out.println("Inicial");
		textPane.setText(modeloActividades.getDescripcionActividad(comboBox.getSelectedItem().toString()));
		textField.setText(modeloActividades.getPrecioActividadSocio(comboBox.getSelectedItem().toString()));
		textField_1.setText(modeloActividades.getFechaIniActividad(comboBox.getSelectedItem().toString()));
		textField_2.setText(modeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString()));
		textField_3.setText(modeloActividades.getPlazasActividad(comboBox.getSelectedItem().toString()));
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frmInscripcinActividadSocio.dispose();
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Inscribirse");
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Comprobar que no sea moroso
				if(esMoroso(id_socio)) {
					JOptionPane.showMessageDialog(frmInscripcinActividadSocio,"No puedes inscribirte a la actividad ya que tienes deudas.","Error",JOptionPane.ERROR_MESSAGE);				
				}
				else {
					//Comprobar que no este inscrito o en lista de espera
					if(estaInscrito(id_socio,comboBox.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(frmInscripcinActividadSocio,"Ya estás inscrito o en la lista de espera de esta actividad.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						if(hayPlazas(comboBox.getSelectedItem().toString())) {
							//Restar plaza
							modeloActividades.restarPlaza(comboBox.getSelectedItem().toString());
							//Crear inscripcion
							long id_ins=modeloInscripciones.nuevaInscripcionRetornaId(modeloClientes.getDNI(""+id_socio), ""+modeloActividades.getIdActividad(comboBox.getSelectedItem().toString()), hoy);
							if(rdbtnNewRadioButton.isSelected()) {
								//-Pagar ahora
								modeloPagos.anadirPago(hoy, modeloClientes.getDNI(""+id_socio), ""+id_ins, ""+0);
								JOptionPane.showMessageDialog(frmInscripcinActividadSocio,"Te has inscrito en esta actividad.\nRecibo:\n-Importe: "+textField.getText()+" €\n-Fecha: "+hoy,"Inscrito",JOptionPane.INFORMATION_MESSAGE);
								frmInscripcinActividadSocio.dispose();
							}
							else {
								//-Añadir a cuota
								double cuota = modeloReservas.nuevaCuota(id_socio);
		                        double precio = Double.parseDouble(modeloActividades.getPrecioActividadSocio(comboBox.getSelectedItem().toString()));
		                        modeloReservas.añadeCuotaAct(cuota+precio, id_socio);
								JOptionPane.showMessageDialog(frmInscripcinActividadSocio,"Te has inscrito en esta actividad.\nImporte: "+textField.getText()+" €\nSe añadirá el importe a tu próxima cuota.","Inscrito",JOptionPane.INFORMATION_MESSAGE);
								frmInscripcinActividadSocio.dispose();
							}
						}
						else {
							JOptionPane.showMessageDialog(frmInscripcinActividadSocio,"No puedes inscribirte a la actividad ya que no hay plazas disponibles.\nPasarás a lista de espera.","Error",JOptionPane.ERROR_MESSAGE);
							//Añadir a lista de espera de socios
							GestionColas.inicializa();
							modeloEsperas.nuevaEspera(""+modeloClientes.getDNI(""+id_socio), ""+modeloActividades.getIdActividad(comboBox.getSelectedItem().toString()), hoy);
							GestionColas.anadeSocio(""+id_socio,(int) modeloActividades.getIdActividad(comboBox.getSelectedItem().toString()));
							GestionColas.serializa();
							}
					}
				}
				
				
				
			}
		});
		btnNewButton_1.setBounds(295, 227, 129, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblPlazas = new JLabel("- Plazas:");
		lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlazas.setBounds(20, 128, 65, 14);
		panel.add(lblPlazas);
		
		
		
	}
	
	public Window getFrmInscripcinActividadSocio() {
		return this.frmInscripcinActividadSocio;
	}
	
	//Funcion para comprobar si es moroso
	public boolean esMoroso(int id_socio) {
		boolean b=false;
		if(modeloClientes.DebeDinero(""+id_socio)==1) {
			b=true;
		}
		return b;
	}
	
	//Funcion para comprobar si ya está inscrito o en lista
	public boolean estaInscrito(int id_socio, String nombre_actividad) {
		boolean b=false;
		b=modeloInscripciones.personaActividadInscripciones(modeloActividades.getIdActividad(nombre_actividad),modeloClientes.getDNI(""+id_socio));
		if(!b) {
			//Metodo Dani para comprobar si esta en lista
			b=modeloEsperas.personaActividadEsperas(modeloActividades.getIdActividad(nombre_actividad),modeloClientes.getDNI(""+id_socio));
			
		}
		return b;
	}
	
	//Funcion para comprobar si hay plazas en la actividad
	public boolean hayPlazas(String nombre_actividad) {
		boolean b=true;
		String plazas=modeloActividades.getPlazasActividad(nombre_actividad);
		if(plazas.equals("0")) {
			b=false;
		}
		return b;
	}
}
