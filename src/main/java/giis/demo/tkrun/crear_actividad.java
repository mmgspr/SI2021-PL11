package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
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
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import giis.demo.util.Database;
import giis.demo.util.SwingMain;

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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Pádel", "Tenis", "Fútbol"}));
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
		textField_1.setBounds(462, 48, 61, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
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
		
		List<Object[]> modPer=modeloPer.getPeriodosIns();
		
		String[] periodosIns=new String[modPer.size()];
		
		Iterator<Object[]> iteradorPer = modPer.iterator();
		
		int iPer=0;
		while(iteradorPer.hasNext()) {
			periodosIns[iPer]=iteradorPer.next()[0].toString();
			iPer++;
		}
		
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(periodosIns));
		comboBox_1_1.setBounds(382, 291, 267, 21);
		panel.add(comboBox_1_1);
		
		JButton btnNewButton = new JButton("Crear nuevo periodo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPeriodoIns.getFrmCrearPeriodoDe().setVisible(true);
			}
		});
		btnNewButton.setBounds(392, 322, 152, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crear");
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
					textField_3.setEnabled(true);
					textField_4.setEnabled(true);
					lblFechaInicial.setEnabled(true);
					lblFechaFinal.setEnabled(true);
				}
				else {
					textField_3.setEnabled(false);
					textField_4.setEnabled(false);
					lblFechaInicial.setEnabled(false);
					lblFechaFinal.setEnabled(false);
				}
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Verano", "Otoño", "Invierno", "Primavera", "Otro..."}));
		comboBox_2.setBounds(382, 149, 267, 21);
		panel.add(comboBox_2);
		
		
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(497, 181, 152, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(497, 216, 152, 19);
		panel.add(textField_4);
	}

	public Window getFrmCrearActividad() {
		// TODO Auto-generated method stub
		return this.frmCrearActividad;
	}
}

