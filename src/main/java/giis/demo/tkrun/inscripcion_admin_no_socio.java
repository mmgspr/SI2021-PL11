package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class inscripcion_admin_no_socio {

	private JFrame frmInscripcinAdministradorNo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private ReservasModel modeloReservas = new ReservasModel();
	private PagosModel modeloPagos = new PagosModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private InscripcionesModel modeloInscripciones = new InscripcionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private PeriodosInscripcionModel modeloPeriodosInscripcion = new PeriodosInscripcionModel();
	private EsperasModel modeloEsperas = new EsperasModel();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	private JTextField textField_3;
	String hoy = sdf.format(dateHoy);
	List<String> todasAct = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inscripcion_admin_no_socio window = new inscripcion_admin_no_socio();
					window.frmInscripcinAdministradorNo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inscripcion_admin_no_socio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInscripcinAdministradorNo = new JFrame();
		frmInscripcinAdministradorNo.setTitle("Inscripción administrador no socio");
		frmInscripcinAdministradorNo.setBounds(100, 100, 450, 300);
		frmInscripcinAdministradorNo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmInscripcinAdministradorNo.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//PERIODOS DE INSCRIPCION ABIERTOS
				List<Object[]> modPer=modeloPeriodosInscripcion.getFechasNoSocio(hoy);
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
		
		JLabel lblNewLabel = new JLabel("• Actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 77, 14);
		panel.add(lblNewLabel);
		
		
		
		JLabel lblDescripcin = new JLabel("- Descripción:");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcin.setBounds(20, 43, 89, 14);
		panel.add(lblDescripcin);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(101, 41, 321, 50);
		panel.add(textPane);
		
		JLabel lblPrecio = new JLabel("- Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(22, 103, 65, 14);
		panel.add(lblPrecio);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(80, 101, 58, 20);
		panel.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(148, 104, 18, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblFechaInicial = new JLabel("- Fecha inicial:");
		lblFechaInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaInicial.setBounds(189, 104, 89, 14);
		panel.add(lblFechaInicial);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(274, 101, 118, 20);
		panel.add(textField_1);
		
		JLabel lblFechaFinal = new JLabel("- Fecha final:");
		lblFechaFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaFinal.setBounds(189, 136, 89, 14);
		panel.add(lblFechaFinal);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(269, 134, 112, 20);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Inscribirse");
		btnNewButton_1.setBounds(333, 227, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblDni = new JLabel("• DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(10, 178, 77, 14);
		panel.add(lblDni);
		
		textField_3 = new JTextField();
		textField_3.setBounds(53, 176, 85, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Cambio");
			textPane.setText(modeloActividades.getDescripcionActividad(comboBox.getSelectedItem().toString()));
			textField.setText(modeloActividades.getPrecioActividadSocio(comboBox.getSelectedItem().toString()));
			textField_1.setText(modeloActividades.getFechaIniActividad(comboBox.getSelectedItem().toString()));
			textField_2.setText(modeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString()));
		}
		});
		comboBox.setModel(new DefaultComboBoxModel(actividades));
		comboBox.setBounds(91, 8, 129, 22);
		panel.add(comboBox);
		
		//System.out.println("Inicial");
		textPane.setText(modeloActividades.getDescripcionActividad(comboBox.getSelectedItem().toString()));
		textField.setText(modeloActividades.getPrecioActividadSocio(comboBox.getSelectedItem().toString()));
		textField_1.setText(modeloActividades.getFechaIniActividad(comboBox.getSelectedItem().toString()));
		textField_2.setText(modeloActividades.getFechaFinActividad(comboBox.getSelectedItem().toString()));
		
		
		
		
		
	}
	
	public Window getFrmInscripcinAdministradorNo() {
		return this.frmInscripcinAdministradorNo;
	}
}
