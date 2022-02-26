package giis.proyecto.Vista;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
//import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

//import giis.proyecto.Controlador.ControladorReservasAsociacion;
//import giis.proyecto.Controlador.ActividadesController;
//import giis.proyecto.Controlador.ControladorCreacionActividades;
//import giis.proyecto.Controlador.ControladorReservasAsociacion;
//import giis.proyecto.Controlador.ReservasController;
//import giis.proyecto.Modelo.ActividadesModel;
//import giis.proyecto.Modelo.ModeloCreacionActividades;
//import giis.proyecto.Modelo.ModeloReservaAsociacion;
//import giis.proyecto.Modelo.ReservasModel;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class VistaReservaAsociacion {
	
	public JFrame frmReservaAsociacion;
	public JDatePickerImpl datePickerFechaInicio;
	public JDatePickerImpl datePickerFechaFin;
	public JButton JButtonCrear;
	public JButton JButtonCancelar;
	public JComboBox<String> CBInstalaciones;
	public JComboBox<String> CBActividades;
	private JLabel label;
	private JLabel label_1;
	

	
	///
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaReservaAsociacion window = new VistaReservaAsociacion();
					window.frmReservaAsociacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public VistaReservaAsociacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaAsociacion = new JFrame();
		frmReservaAsociacion.setResizable(false);
		frmReservaAsociacion.setTitle("Nueva Reserva Asociacion");
		frmReservaAsociacion.setBounds(100, 100, 327, 341);
		frmReservaAsociacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmReservaAsociacion.getContentPane().setLayout(null);
		
		JLabel lblInstalacion = new JLabel("Instalacion:");
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstalacion.setBounds(16, 73, 125, 20);
		frmReservaAsociacion.getContentPane().add(lblInstalacion);
		
		JLabel lblActividad = new JLabel("Actividad:");
		lblActividad.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblActividad.setBounds(16, 11, 107, 20);
		frmReservaAsociacion.getContentPane().add(lblActividad);
		
		// ++Calendario desplegable1
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
				
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());
		
		datePickerFechaInicio.setBounds(20, 207, 125, 25);
		frmReservaAsociacion.getContentPane().add(datePickerFechaInicio);
		
		// ++Calendario desplegable 2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
		
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());

		datePickerFechaFin.setBounds(173, 207, 125, 25);
		frmReservaAsociacion.getContentPane().add(datePickerFechaFin);
		
		JLabel lblPeriodoDeReserva = new JLabel("Fecha de Reserva:");
		lblPeriodoDeReserva.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPeriodoDeReserva.setBounds(10, 147, 181, 20);
		frmReservaAsociacion.getContentPane().add(lblPeriodoDeReserva);
		
		
		
		JButtonCrear = new JButton("Crear");
		JButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		JButtonCrear.setEnabled(false);
		
		JButtonCrear.setBounds(23, 267, 89, 32);
		frmReservaAsociacion.getContentPane().add(JButtonCrear);
		
		CBInstalaciones = new JComboBox<String>();
		
		CBInstalaciones.setBounds(20, 104, 171, 20);
		frmReservaAsociacion.getContentPane().add(CBInstalaciones);
		
		CBActividades = new JComboBox<String>();
		
		CBActividades.setBounds(16, 42, 171, 20);
		frmReservaAsociacion.getContentPane().add(CBActividades);
		
		JButton JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFrameR().setVisible(false);
			}
		});
		JButtonCancelar.setBounds(173, 267, 89, 32);
		frmReservaAsociacion.getContentPane().add(JButtonCancelar);
		
		//Button group para escoger entre socios o administracion
		ButtonGroup group = new ButtonGroup();
		
		//@SuppressWarnings("unused")
		//ControladorReservasAsociacion VRA=new ControladorReservasAsociacion(this);
	}
	
	
	public JFrame getFrameR() { 
		return this.frmReservaAsociacion;
	}
	
}

