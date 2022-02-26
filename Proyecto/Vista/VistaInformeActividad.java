package giis.proyecto.Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import giis.proyecto.Controlador.ControladorInformeActividad;
import giis.proyecto.Controlador.InscripcionController;

public class VistaInformeActividad {
	public JFrame frmInforme;
	public JDatePickerImpl datePickerFechaInicio;
	public JDatePickerImpl datePickerFechaFin;
	public JButton JButtonGen;
	public JButton JButtonCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInformeActividad window = new VistaInformeActividad();
					window.frmInforme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaInformeActividad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInforme = new JFrame();
		frmInforme.setResizable(false);
		frmInforme.setTitle("Nuevo Periodo de Inscripcion");
		frmInforme.setBounds(100, 100, 362, 203);
		frmInforme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInforme.getContentPane().setLayout(null);

		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
		
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());

		datePickerFechaInicio.setBounds(20, 61, 125, 25);
		frmInforme.getContentPane().add(datePickerFechaInicio);
		// --Calendario desplegable

		// ++Calendario desplegable2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
		
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());

		datePickerFechaFin.setBounds(215, 61, 125, 25);
		frmInforme.getContentPane().add(datePickerFechaFin);
		// --Calendario desplegable2

		JLabel lblInicio = new JLabel("Fecha de inicio:");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInicio.setBounds(20, 25, 125, 25);
		frmInforme.getContentPane().add(lblInicio);

		JLabel lblFin = new JLabel("Fecha de fin:");
		lblFin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFin.setBounds(214, 25, 103, 25);
		frmInforme.getContentPane().add(lblFin);

		JButtonGen = new JButton("Generar informe");
		JButtonGen.setEnabled(true);
		JButtonGen.setBounds(10, 126, 162, 25);
		frmInforme.getContentPane().add(JButtonGen);

		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.setBounds(248, 126, 89, 25);
		frmInforme.getContentPane().add(JButtonCancelar);


		//Controllador
		ControladorInformeActividad CIA= new ControladorInformeActividad(this);
	}
}
