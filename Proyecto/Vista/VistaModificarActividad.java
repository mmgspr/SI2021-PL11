package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import giis.proyecto.Controlador.ControladorCreacionActividades;
import giis.proyecto.Controlador.ControladorModificarActividad;
import giis.proyecto.Modelo.ModeloCreacionActividades;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class VistaModificarActividad {

	public JFrame frmCrearActividad;
	public JTextField TFieldNombre;
	public JTextField TFieldPrecioSocios;
	public JTextField TFieldPrecioNoSocios;
	public JButton JButtonCancelar;
	public JButton JButtonCrear;
	public JSpinner SpinnerPlazas;
	public JDatePickerImpl datePickerFechaInicio;
	public JDatePickerImpl datePickerFechaFin;
	public JCheckBox CBLunes;
	public JCheckBox CBMartes;
	public JCheckBox CBMiercoles;
	public JCheckBox CBJueves;
	public JCheckBox CBViernes;
	public JCheckBox CBSabado;
	public JCheckBox CBDomingo;
	public JLabel LDesdeLunes;
	public JLabel LHastaLunes;
	public JLabel LDesdeMartes;
	public JLabel LHastaMartes;
	public JLabel LDesdeMiercoles;
	public JLabel LHastaMiercoles;
	public JLabel LDesdeJueves;
	public JLabel LHastaJueves;
	public JLabel LDesdeViernes;
	public JLabel LHastaViernes;
	public JLabel LDesdeSabado;
	public JLabel LHastaSabado;
	public JLabel LDesdeDomingo;
	public JLabel LHastaDomingo;
	public JComboBox<String> CBDesdeLunes;
	public JComboBox<String> CBHastaLunes;
	public JComboBox<String> CBDesdeMartes;
	public JComboBox<String> CBHastaMartes;
	public JComboBox<String> CBDesdeMiercoles;
	public JComboBox<String> CBHastaMiercoles;
	public JComboBox<String> CBDesdeJueves;
	public JComboBox<String> CBHastaJueves;
	public JComboBox<String> CBDesdeViernes;
	public JComboBox<String> CBHastaViernes;
	public JComboBox<String> CBDesdeSabado;
	public JComboBox<String> CBHastaSabado;
	public JComboBox<String> CBDesdeDomingo;
	public JComboBox<String> CBHastaDomingo;
	public JComboBox<String> CBInstalacion;
	public JComboBox<String> CBInscripcion;
	public JComboBox<Integer> CBIDActividades;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaModificarActividad window = new VistaModificarActividad();
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
	public VistaModificarActividad() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearActividad = new JFrame();
		frmCrearActividad.setResizable(false);
		frmCrearActividad.setTitle("Nueva Actividad");
		frmCrearActividad.setBounds(100, 100, 1180, 400);
		frmCrearActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCrearActividad.getContentPane().setLayout(null);

		JLabel LabelNombre = new JLabel("Nombre:");
		LabelNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelNombre.setBounds(10, 44, 65, 14);
		frmCrearActividad.getContentPane().add(LabelNombre);

		JButtonCrear = new JButton("Modificar");
		JButtonCrear.setEnabled(false);
		
		JButtonCrear.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JButtonCrear.setBounds(620, 332, 105, 25);
		frmCrearActividad.getContentPane().add(JButtonCrear);

		JButtonCancelar = new JButton("Cancelar");
		
		JButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JButtonCancelar.setBounds(735, 332, 105, 25);
		frmCrearActividad.getContentPane().add(JButtonCancelar);

		TFieldNombre = new JTextField();
		TFieldNombre.setBounds(85, 44, 230, 20);
		frmCrearActividad.getContentPane().add(TFieldNombre);
		TFieldNombre.setColumns(10);

		JLabel LabelPlazas = new JLabel("Plazas:");
		LabelPlazas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelPlazas.setBounds(10, 83, 65, 14);
		frmCrearActividad.getContentPane().add(LabelPlazas);

		SpinnerPlazas = new JSpinner();
		SpinnerPlazas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		SpinnerPlazas.setBounds(87, 83, 48, 20);
		frmCrearActividad.getContentPane().add(SpinnerPlazas);
		
		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
				
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());

		datePickerFechaInicio.setBounds(595, 22, 125, 30);
		frmCrearActividad.getContentPane().add(datePickerFechaInicio);
		// --Calendario desplegable

		// ++Calendario desplegable2
		UtilDateModel modelFechaFinS = new UtilDateModel();
		modelFechaFinS.setSelected(false);
				
		JDatePanelImpl dpFechaFinS = new JDatePanelImpl(modelFechaFinS, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFinS, new DateLabelFormatter());

		datePickerFechaFin.setBounds(910, 22, 125, 30);
		frmCrearActividad.getContentPane().add(datePickerFechaFin);
		// --Calendario desplegable2

		JPanel PanelPrecios = new JPanel();
		PanelPrecios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		PanelPrecios.setBounds(10, 108, 315, 158);
		frmCrearActividad.getContentPane().add(PanelPrecios);
		PanelPrecios.setLayout(null);

		JLabel LabelPrecios = new JLabel("Precios:");
		LabelPrecios.setFont(new Font("Tahoma", Font.BOLD, 20));
		LabelPrecios.setBounds(10, 11, 90, 20);
		PanelPrecios.add(LabelPrecios);

		JLabel LabelPrecioSocios = new JLabel("Socios:");
		LabelPrecioSocios.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelPrecioSocios.setBounds(75, 54, 60, 15);
		PanelPrecios.add(LabelPrecioSocios);

		JLabel LabelPreciosNoSocios = new JLabel("No Socios:");
		LabelPreciosNoSocios.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelPreciosNoSocios.setBounds(75, 85, 80, 15);
		PanelPrecios.add(LabelPreciosNoSocios);

		TFieldPrecioSocios = new JTextField();
		TFieldPrecioSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				Toolkit tk=Toolkit.getDefaultToolkit();
				if(Character.isLetter(c) || (TFieldPrecioSocios.getText().length()==3)) {
					tk.beep();
					arg0.consume();
				}

			}
			
		});

		TFieldPrecioSocios.setBounds(165, 54, 30, 20);
		PanelPrecios.add(TFieldPrecioSocios);
		TFieldPrecioSocios.setColumns(10);

		TFieldPrecioNoSocios = new JTextField();
		TFieldPrecioNoSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				Toolkit tk=Toolkit.getDefaultToolkit();
				if(Character.isLetter(c) || (TFieldPrecioNoSocios.getText().length()==3)) {
					tk.beep();
					arg0.consume();
				}

			}
		});

		TFieldPrecioNoSocios.setBounds(165, 85, 30, 20);
		PanelPrecios.add(TFieldPrecioNoSocios);
		TFieldPrecioNoSocios.setColumns(10);

		JLabel LabelEuros1 = new JLabel("€");
		LabelEuros1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelEuros1.setBounds(205, 57, 20, 15);
		PanelPrecios.add(LabelEuros1);

		JLabel LabelEuros2 = new JLabel("€");
		LabelEuros2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelEuros2.setBounds(205, 87, 20, 15);
		PanelPrecios.add(LabelEuros2);

		JPanel PanelInstalacion = new JPanel();
		PanelInstalacion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		PanelInstalacion.setBounds(10, 277, 315, 82);
		frmCrearActividad.getContentPane().add(PanelInstalacion);
		PanelInstalacion.setLayout(null);

		JLabel LabelInstalacion = new JLabel("Instalación: ");
		LabelInstalacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		LabelInstalacion.setBounds(10, 11, 130, 20);
		PanelInstalacion.add(LabelInstalacion);
		
		CBInstalacion = new JComboBox();
		CBInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CBInstalacion.setBounds(20, 42, 270, 20);
		PanelInstalacion.add(CBInstalacion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(335, 108, 825, 213);
		frmCrearActividad.getContentPane().add(panel);
		panel.setLayout(null);
		
		CBLunes = new JCheckBox("Lunes");
		
		CBLunes.setBounds(10, 42, 99, 23);
		panel.add(CBLunes);
		CBLunes.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBMartes = new JCheckBox("Martes");
		
		CBMartes.setBounds(124, 42, 99, 23);
		panel.add(CBMartes);
		CBMartes.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBMiercoles = new JCheckBox("Miércoles");
		
		CBMiercoles.setBounds(243, 42, 102, 23);
		panel.add(CBMiercoles);
		CBMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBJueves = new JCheckBox("Jueves");
		
		CBJueves.setBounds(360, 42, 97, 23);
		panel.add(CBJueves);
		CBJueves.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBViernes = new JCheckBox("Viernes");
		CBViernes.setBounds(478, 42, 103, 23);
		panel.add(CBViernes);
		CBViernes.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBSabado = new JCheckBox("Sabado");
		CBSabado.setBounds(600, 42, 99, 23);
		panel.add(CBSabado);
		CBSabado.setHorizontalAlignment(SwingConstants.CENTER);
		
		CBDomingo = new JCheckBox("Domingo");
		CBDomingo.setBounds(714, 42, 101, 23);
		panel.add(CBDomingo);
		CBDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(115, 42, 3, 160);
		panel.add(separator_1);
		
				JSeparator separator = new JSeparator();
				separator.setBounds(233, 42, 3, 160);
				panel.add(separator);
				separator.setOrientation(SwingConstants.VERTICAL);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setOrientation(SwingConstants.VERTICAL);
				separator_2.setBounds(351, 42, 3, 160);
				panel.add(separator_2);
				
				JSeparator separator_3 = new JSeparator();
				separator_3.setOrientation(SwingConstants.VERTICAL);
				separator_3.setBounds(469, 42, 3, 160);
				panel.add(separator_3);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setOrientation(SwingConstants.VERTICAL);
				separator_4.setBounds(587, 42, 3, 160);
				panel.add(separator_4);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setOrientation(SwingConstants.VERTICAL);
				separator_5.setBounds(705, 42, 3, 160);
				panel.add(separator_5);
				
				CBDesdeLunes = new JComboBox();
				CBDesdeLunes.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeLunes.setEnabled(false);
				CBDesdeLunes.setBounds(10, 100, 95, 20);
				panel.add(CBDesdeLunes);
				
				CBHastaLunes = new JComboBox();
				CBHastaLunes.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaLunes.setEnabled(false);
				CBHastaLunes.setBounds(10, 162, 95, 20);
				panel.add(CBHastaLunes);
				
				LDesdeLunes = new JLabel("Desde:");
				LDesdeLunes.setEnabled(false);
				LDesdeLunes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeLunes.setBounds(10, 72, 46, 20);
				panel.add(LDesdeLunes);
				
				LHastaLunes = new JLabel("Hasta:");
				LHastaLunes.setEnabled(false);
				LHastaLunes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaLunes.setBounds(10, 131, 46, 20);
				panel.add(LHastaLunes);
				
				LDesdeMartes = new JLabel("Desde:");
				LDesdeMartes.setEnabled(false);
				LDesdeMartes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeMartes.setBounds(128, 72, 46, 20);
				panel.add(LDesdeMartes);
				
				CBDesdeMartes = new JComboBox();
				CBDesdeMartes.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeMartes.setEnabled(false);
				CBDesdeMartes.setBounds(128, 100, 95, 20);
				panel.add(CBDesdeMartes);
				
				LHastaMartes = new JLabel("Hasta:");
				LHastaMartes.setEnabled(false);
				LHastaMartes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaMartes.setBounds(128, 131, 46, 20);
				panel.add(LHastaMartes);
				
				CBHastaMartes = new JComboBox();
				CBHastaMartes.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaMartes.setEnabled(false);
				CBHastaMartes.setBounds(128, 162, 95, 20);
				panel.add(CBHastaMartes);
				
				LDesdeMiercoles = new JLabel("Desde:");
				LDesdeMiercoles.setEnabled(false);
				LDesdeMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeMiercoles.setBounds(242, 72, 46, 20);
				panel.add(LDesdeMiercoles);
				
				CBDesdeMiercoles = new JComboBox();
				CBDesdeMiercoles.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeMiercoles.setEnabled(false);
				CBDesdeMiercoles.setBounds(243, 100, 102, 20);
				panel.add(CBDesdeMiercoles);
				
				LHastaMiercoles = new JLabel("Hasta:");
				LHastaMiercoles.setEnabled(false);
				LHastaMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaMiercoles.setBounds(242, 131, 46, 20);
				panel.add(LHastaMiercoles);
				
				CBHastaMiercoles = new JComboBox();
				CBHastaMiercoles.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaMiercoles.setEnabled(false);
				CBHastaMiercoles.setBounds(243, 162, 102, 20);
				panel.add(CBHastaMiercoles);
				
				LDesdeJueves = new JLabel("Desde:");
				LDesdeJueves.setEnabled(false);
				LDesdeJueves.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeJueves.setBounds(360, 72, 46, 20);
				panel.add(LDesdeJueves);
				
				CBDesdeJueves = new JComboBox();
				CBDesdeJueves.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeJueves.setEnabled(false);
				CBDesdeJueves.setBounds(355, 100, 102, 20);
				panel.add(CBDesdeJueves);
				
				LDesdeViernes = new JLabel("Desde:");
				LDesdeViernes.setEnabled(false);
				LDesdeViernes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeViernes.setBounds(481, 72, 46, 20);
				panel.add(LDesdeViernes);
				
				LHastaJueves = new JLabel("Hasta:");
				LHastaJueves.setEnabled(false);
				LHastaJueves.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaJueves.setBounds(360, 131, 46, 20);
				panel.add(LHastaJueves);
				
				LDesdeSabado = new JLabel("Desde:");
				LDesdeSabado.setEnabled(false);
				LDesdeSabado.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeSabado.setBounds(600, 72, 46, 20);
				panel.add(LDesdeSabado);
				
				LDesdeDomingo = new JLabel("Desde:");
				LDesdeDomingo.setEnabled(false);
				LDesdeDomingo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LDesdeDomingo.setBounds(715, 72, 46, 20);
				panel.add(LDesdeDomingo);
				
				CBHastaJueves = new JComboBox();
				CBHastaJueves.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaJueves.setEnabled(false);
				CBHastaJueves.setBounds(355, 162, 102, 20);
				panel.add(CBHastaJueves);
				
				CBDesdeViernes = new JComboBox();
				CBDesdeViernes.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeViernes.setEnabled(false);
				CBDesdeViernes.setBounds(478, 100, 102, 20);
				panel.add(CBDesdeViernes);
				
				CBHastaViernes = new JComboBox();
				CBHastaViernes.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaViernes.setEnabled(false);
				CBHastaViernes.setBounds(478, 162, 102, 20);
				panel.add(CBHastaViernes);
				
				CBDesdeSabado = new JComboBox();
				CBDesdeSabado.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeSabado.setEnabled(false);
				CBDesdeSabado.setBounds(595, 100, 102, 20);
				panel.add(CBDesdeSabado);
				
				CBHastaSabado = new JComboBox();
				CBHastaSabado.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaSabado.setEnabled(false);
				CBHastaSabado.setBounds(596, 162, 102, 20);
				panel.add(CBHastaSabado);
				
				CBDesdeDomingo = new JComboBox();
				CBDesdeDomingo.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
				CBDesdeDomingo.setEnabled(false);
				CBDesdeDomingo.setBounds(713, 100, 102, 20);
				panel.add(CBDesdeDomingo);
				
				CBHastaDomingo = new JComboBox();
				CBHastaDomingo.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
				CBHastaDomingo.setEnabled(false);
				CBHastaDomingo.setBounds(713, 162, 102, 20);
				panel.add(CBHastaDomingo);
				
				LHastaViernes = new JLabel("Hasta:");
				LHastaViernes.setEnabled(false);
				LHastaViernes.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaViernes.setBounds(484, 131, 46, 20);
				panel.add(LHastaViernes);
				
				LHastaSabado = new JLabel("Hasta:");
				LHastaSabado.setEnabled(false);
				LHastaSabado.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaSabado.setBounds(598, 131, 46, 20);
				panel.add(LHastaSabado);
				
				LHastaDomingo = new JLabel("Hasta:");
				LHastaDomingo.setEnabled(false);
				LHastaDomingo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				LHastaDomingo.setBounds(713, 131, 46, 20);
				panel.add(LHastaDomingo);
				
				JLabel lblHorario = new JLabel("Horario:");
				lblHorario.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblHorario.setBounds(10, 11, 95, 25);
				panel.add(lblHorario);
				
				JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio Actividad:");
				lblFechaDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblFechaDeInicio.setBounds(420, 22, 170, 20);
				frmCrearActividad.getContentPane().add(lblFechaDeInicio);
				
				CBInscripcion = new JComboBox();
				CBInscripcion.setBounds(725, 65, 215, 20);
				frmCrearActividad.getContentPane().add(CBInscripcion);
				
				JLabel lblFechaFinActividad = new JLabel("Fecha Fin Actividad:");
				lblFechaFinActividad.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblFechaFinActividad.setBounds(770, 22, 170, 20);
				frmCrearActividad.getContentPane().add(lblFechaFinActividad);
				
				JLabel lblPeriodoDeInscripcion = new JLabel("Periodo de Inscripcion:");
				lblPeriodoDeInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblPeriodoDeInscripcion.setBounds(560, 63, 156, 20);
				frmCrearActividad.getContentPane().add(lblPeriodoDeInscripcion);
				
				JLabel LabelId = new JLabel("ID:");
				LabelId.setFont(new Font("Tahoma", Font.PLAIN, 17));
				LabelId.setBounds(10, 19, 65, 14);
				frmCrearActividad.getContentPane().add(LabelId);
				
				CBIDActividades = new JComboBox();
				CBIDActividades.setBounds(84, 13, 215, 20);
				frmCrearActividad.getContentPane().add(CBIDActividades);
	}
}