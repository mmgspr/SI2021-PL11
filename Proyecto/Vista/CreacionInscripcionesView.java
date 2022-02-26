package giis.proyecto.Vista;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import giis.proyecto.Controlador.ControladorCreacionActividades;
import giis.proyecto.Controlador.InscripcionController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreacionInscripcionesView {

	public JFrame frmNuevaInscripcion;
	public JDatePickerImpl datePickerFechaInicio;
	public JDatePickerImpl datePickerFechaFin;
	public JDatePickerImpl datePickerFechaFinNS;
	public JLabel lblFinDeInscripcion_1;
	public JTextField TFieldEtiqueta;
	public JButton JButtonCrear;
	public JButton JButtonCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreacionInscripcionesView window = new CreacionInscripcionesView();
					window.frmNuevaInscripcion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreacionInscripcionesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaInscripcion = new JFrame();
		frmNuevaInscripcion.setResizable(false);
		frmNuevaInscripcion.setTitle("Nuevo Periodo de Inscripcion");
		frmNuevaInscripcion.setBounds(100, 100, 420, 255);
		frmNuevaInscripcion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNuevaInscripcion.getContentPane().setLayout(null);

		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
		
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());

		datePickerFechaInicio.setBounds(270, 25, 125, 25);
		frmNuevaInscripcion.getContentPane().add(datePickerFechaInicio);
		// --Calendario desplegable

		// ++Calendario desplegable2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
		
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());

		datePickerFechaFin.setBounds(270, 61, 125, 25);
		frmNuevaInscripcion.getContentPane().add(datePickerFechaFin);
		// --Calendario desplegable2

		// ++Calendario desplegable3
		UtilDateModel modelFechaFinNS = new UtilDateModel();
		modelFechaFinNS.setSelected(false);

		JDatePanelImpl dpFechaFinNS = new JDatePanelImpl(modelFechaFinNS, modelProperties);
		datePickerFechaFinNS = new JDatePickerImpl(dpFechaFinNS, new DateLabelFormatter());

		datePickerFechaFinNS.setBounds(270, 97, 125, 25);
		frmNuevaInscripcion.getContentPane().add(datePickerFechaFinNS);
		// --Calendario desplegable3



		JLabel lblInicioDeInscripcion = new JLabel("Inicio de Inscripcion para Socios:");
		lblInicioDeInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInicioDeInscripcion.setBounds(10, 25, 256, 25);
		frmNuevaInscripcion.getContentPane().add(lblInicioDeInscripcion);

		JLabel lblFinDeInscripcion = new JLabel("Fin de Inscripcion para Socios:");
		lblFinDeInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFinDeInscripcion.setBounds(10, 61, 256, 25);
		frmNuevaInscripcion.getContentPane().add(lblFinDeInscripcion);

		lblFinDeInscripcion_1 = new JLabel("Fin de Inscripcion para No Socios:");
		lblFinDeInscripcion_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFinDeInscripcion_1.setBounds(10, 97, 256, 25);
		frmNuevaInscripcion.getContentPane().add(lblFinDeInscripcion_1);

		JButtonCrear = new JButton("Crear");
		JButtonCrear.setEnabled(false);
		JButtonCrear.setBounds(104, 182, 89, 25);
		frmNuevaInscripcion.getContentPane().add(JButtonCrear);

		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.setBounds(203, 182, 89, 25);
		frmNuevaInscripcion.getContentPane().add(JButtonCancelar);

		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEtiqueta.setBounds(10, 135, 76, 25);
		frmNuevaInscripcion.getContentPane().add(lblEtiqueta);

		TFieldEtiqueta = new JTextField();
		
		TFieldEtiqueta.setBounds(85, 137, 310, 25);
		frmNuevaInscripcion.getContentPane().add(TFieldEtiqueta);
		TFieldEtiqueta.setColumns(10);


		//Controllador
		InscripcionController IC= new InscripcionController(this);

	}

	public String getDatePickerFechaIniS() {
		int d = this.datePickerFechaInicio.getModel().getDay();
		int m = this.datePickerFechaInicio.getModel().getMonth()+1;
		int y = this.datePickerFechaInicio.getModel().getYear();
		String fecha = y + "-" + m + "-" + d;
		return fecha;
	}
	public void setDatePickerFechaInicio(String fecha) {
		String[] f = fecha.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaInicio.getModel().setDate(a, m, d);
	}

	public String getDatePickerFechaFinS() {
		int d = this.datePickerFechaFin.getModel().getDay();
		int m = this.datePickerFechaFin.getModel().getMonth()+1;
		int y = this.datePickerFechaFin.getModel().getYear();
		String fecha = y + "-" + m + "-" + d;
		return fecha;
	}

	public void setDatePickerFechaFin(String fecha) {
		String[] f = fecha.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaFin.getModel().setDate(a, m, d);
	}

	public String getDatePickerFechaFinNS() {
		int d = this.datePickerFechaFinNS.getModel().getDay();
		int m = this.datePickerFechaFinNS.getModel().getMonth()+1;
		int y = this.datePickerFechaFinNS.getModel().getYear();
		String fecha = y + "-" + m + "-" + d;
		return fecha;
	}

	public void setDatePickerFechaFinNS(String fecha) {
		String[] f = fecha.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaFinNS.getModel().setDate(a, m, d);
	}
}
