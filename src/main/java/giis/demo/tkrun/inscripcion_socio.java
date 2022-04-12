package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class inscripcion_socio {

	private JFrame frmInscripcinActividadSocio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	int id_socio;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(81, 7, 129, 22);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(10, 227, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Inscribirse");
		btnNewButton_1.setBounds(335, 227, 89, 23);
		panel.add(btnNewButton_1);
		
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
	}
}
