package giis.proyecto.Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class VistaVerReservasS {

	public JFrame frmVerReservasS;
	public JComboBox<String> CBSocios;
	public JComboBox<String> CBInstalaciones;
	public JDatePickerImpl datePickerFechaIni;
	public JDatePickerImpl datePickerFechaFin;
	public JTable tablaReservas;
	public JButton JButtonCancelar;
	public JButton JButtonCrear;
	public JRadioButton rdbtnInstalacion;
	public JRadioButton rdbtnInstalacin;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVerReservasS window = new VistaVerReservasS();
					window.frmVerReservasS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public VistaVerReservasS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerReservasS = new JFrame();
		frmVerReservasS.setResizable(false);
		frmVerReservasS.setTitle("Ver reservas de un socio");
		frmVerReservasS.setBounds(100, 100, 938, 518);
		frmVerReservasS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVerReservasS.getContentPane().setLayout(null);
		
		
		JLabel lblInstalacion = new JLabel("Socio:");
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstalacion.setBounds(10, 42, 61, 20);
		frmVerReservasS.getContentPane().add(lblInstalacion);
	
		
		JLabel lblFechaReserva = new JLabel("Fecha Inicio:");
		lblFechaReserva.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFechaReserva.setBounds(249, 93, 95, 14);
		frmVerReservasS.getContentPane().add(lblFechaReserva);
		
		JLabel lblNewLabel = new JLabel("Fecha Fin:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(499, 93, 77, 14);
		frmVerReservasS.getContentPane().add(lblNewLabel);
		
		JLabel lblPeriodoDeReserva = new JLabel("Periodo de búsqueda:");
		lblPeriodoDeReserva.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPeriodoDeReserva.setBounds(10, 89, 229, 20);
		frmVerReservasS.getContentPane().add(lblPeriodoDeReserva);
		
		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
						
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaIni = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());

		datePickerFechaIni.setBounds(354, 93, 125, 30);
		frmVerReservasS.getContentPane().add(datePickerFechaIni);
		// --Calendario desplegable

		// ++Calendario desplegable2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
						
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());

		datePickerFechaFin.setBounds(586, 93, 125, 30);
		frmVerReservasS.getContentPane().add(datePickerFechaFin);
		// --Calendario desplegable2
		
		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmVerReservasS.setVisible(false);
			}
		});
		JButtonCancelar.setBounds(10, 343, 89, 23);
		frmVerReservasS.getContentPane().add(JButtonCancelar);
		
		JButtonCrear = new JButton("Consultar");
		JButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		JButtonCrear.setBounds(10, 309, 89, 23);
		JButtonCrear.setEnabled(false);
		frmVerReservasS.getContentPane().add(JButtonCrear);
		
		CBSocios = new JComboBox<String>();
		CBSocios.setBounds(92, 46, 110, 20);
		frmVerReservasS.getContentPane().add(CBSocios);
		
		JLabel lblInstalacin = new JLabel("Instalación:");
		lblInstalacin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstalacin.setBounds(226, 42, 125, 20);
		frmVerReservasS.getContentPane().add(lblInstalacin);
		
		CBInstalaciones = new JComboBox<String>();
		CBInstalaciones.setBounds(358, 46, 110, 20);
		frmVerReservasS.getContentPane().add(CBInstalaciones);
		
		
		rdbtnInstalacion = new JRadioButton("Todas las instalaciones");
		rdbtnInstalacion.setBounds(22, 174, 180, 23);
		frmVerReservasS.getContentPane().add(rdbtnInstalacion);
		
		rdbtnInstalacin = new JRadioButton("Instalación especificada");
		rdbtnInstalacin.setBounds(22, 200, 180, 23);
		frmVerReservasS.getContentPane().add(rdbtnInstalacin);
		
		tablaReservas = new JTable();
		tablaReservas.setBounds(212, 134, 501, 232);
		frmVerReservasS.getContentPane().add(tablaReservas);
		tablaReservas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id_reserva", "Instalacion", "Fecha", "Hora de Inicio", "Hora de Fin"
				}
			));
		tablaReservas.getColumnModel().getColumn(1).setPreferredWidth(85);
		tablaReservas.getColumnModel().getColumn(4).setPreferredWidth(87);
		tablaReservas.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaReservas.setBackground(SystemColor.controlHighlight);
		
		JLabel lblOrdenarPor = new JLabel("Mostrar");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOrdenarPor.setBounds(10, 147, 133, 20);
		frmVerReservasS.getContentPane().add(lblOrdenarPor);
		
		
		scrollPane = new JScrollPane(tablaReservas);
		scrollPane.setBounds(203, 134, 692, 344);
		
		frmVerReservasS.getContentPane().add(scrollPane);

	}
}
