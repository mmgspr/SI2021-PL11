package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class VistaReservaSocios {

	public JFrame frameReservasSocios;
	public JButton JBCrearReserva;
	public JDatePickerImpl datePickerFechaInicio;
	public JDatePickerImpl datePickerFechaFin;
	private JLabel lblNewLabel_1;
	private JLabel lblFechaDeFin;
	public JButton btnNewButton;
	public JTable tablaReservasSocios;
	private JScrollPane scrollPane;
	public JLabel LabelBienvenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaReservaSocios window = new VistaReservaSocios();
					window.frameReservasSocios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaReservaSocios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameReservasSocios = new JFrame();
		frameReservasSocios.setTitle("Visualizar Reservas Socio\r\n");
		frameReservasSocios.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frameReservasSocios.setBounds(100, 100, 710, 482);
		frameReservasSocios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameReservasSocios.getContentPane().setLayout(null);
		
		JBCrearReserva = new JButton("Crear Reserva");
		JBCrearReserva.setBounds(385, 398, 140, 23);
		frameReservasSocios.getContentPane().add(JBCrearReserva);
		
		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
						
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());
		datePickerFechaInicio.setBounds(196, 39, 125, 30);
		frameReservasSocios.getContentPane().add(datePickerFechaInicio);
		//--Calendario despegable
		
		// ++Calendario desplegable2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
		
				
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());
		datePickerFechaFin.setBounds(462, 39, 125, 30);
		frameReservasSocios.getContentPane().add(datePickerFechaFin);
		
		lblNewLabel_1 = new JLabel("Fecha de Inicio:");
		lblNewLabel_1.setBounds(75, 39, 125, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frameReservasSocios.getContentPane().add(lblNewLabel_1);
		
		lblFechaDeFin = new JLabel("Fecha de Fin:");
		lblFechaDeFin.setBounds(360, 39, 103, 20);
		lblFechaDeFin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frameReservasSocios.getContentPane().add(lblFechaDeFin);
		
		btnNewButton = new JButton("Ver Reservas");
		
		btnNewButton.setBounds(20, 398, 150, 23);
		frameReservasSocios.getContentPane().add(btnNewButton);
		
		tablaReservasSocios = new JTable();
		tablaReservasSocios.setBounds(0, 0, 622, 16);
		//frameReservasSocios.getContentPane().add(tablaReservasSocios);
		tablaReservasSocios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id_reserva", "Instalacion", "Fecha", "Hora de Inicio", "Hora de Fin"
			}
		));
		tablaReservasSocios.getColumnModel().getColumn(1).setPreferredWidth(85);
		tablaReservasSocios.getColumnModel().getColumn(4).setPreferredWidth(87);
		tablaReservasSocios.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaReservasSocios.setBackground(SystemColor.controlHighlight);
		
		scrollPane = new JScrollPane(tablaReservasSocios);
		scrollPane.setBounds(20, 70, 655, 317);
		
		frameReservasSocios.getContentPane().add(scrollPane);
		
		LabelBienvenido = new JLabel("\r\n");
		LabelBienvenido.setFont(new Font("Tahoma", Font.BOLD, 17));
		LabelBienvenido.setBounds(20, 11, 410, 20);
		frameReservasSocios.getContentPane().add(LabelBienvenido);
		
		JButton btnNewButton_1 = new JButton("Cancelar Reserva");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(535, 398, 140, 23);
		frameReservasSocios.getContentPane().add(btnNewButton_1);
	}
	
	public JTable getTablaReservas() {
		return tablaReservasSocios;
	}
	
	public void setDatePickerFechaFin(String fecha) {
		String[] f = fecha.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaFin.getModel().setDate(a, m, d);
	}
}