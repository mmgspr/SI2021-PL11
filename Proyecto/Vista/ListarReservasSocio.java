package giis.proyecto.Vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

//import giis.proyecto.Controlador.ControladorCreacionReservas;
//import giis.proyecto.Modelo.ModeloCreacionReservas;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

//import giis.proyecto.Controlador.ControladorReservasAsociacion;

import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Modelo.ReservasModel;
import giis.proyecto.Modelo.Util;
import net.miginfocom.swing.MigLayout;

public class ListarReservasSocio {

	private JPanel frame;
	private JComboBox<String> CBInstalacion;
	private JTable tablaReservas;
	private JButton btnTablaReservas;
	private JButton btnCancelarReserva;
	private JButton btnCrearReserva;
	private JDatePickerImpl datePickerFecha;
	private JTable tablaReservasSocio;
	private JLabel lbl_reservas_socio;
	private JScrollPane tableSocioPanel;
	private int horas_reservadas;
	private int horas_totales;
	private JLabel lbl_horas_reservadas;
	
	//info reservas
	private JLabel instalacion;
	private JLabel actividad;
	private JLabel fecha;
	private JLabel hora;

	
	/**
	 * Create the application.
	 */
	public ListarReservasSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Creamos un JPanel en vez de JFrame para introducir esta vista en otra ventana
		frame = new JPanel();
		//frame.setBounds(0, 0, 650, 400);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Container borderLayout = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		
		// PANEL SUPERIOR
		JPanel top_layout = new JPanel(new BorderLayout());
		JPanel top_card_label = new JPanel(new CardLayout(11,7));
		JPanel top_card = new JPanel(new CardLayout(10, 0));
		JPanel top_grid = new JPanel(new MigLayout("", "[100px][145px][25px][150px]", "[25px][25px]"));
		
		JLabel lblSeMostrarnLas = new JLabel("Se mostrarán las reservas para la instalación seleccionada, para todas las horas de los próximos 30 días a la fecha introducida");
		lblSeMostrarnLas.setToolTipText("Se mostrarán las reservas para la instalación seleccionada, para todas las horas de los próximos 30 días a la fecha introducida");
		top_card_label.add(lblSeMostrarnLas);
		top_layout.add(top_card_label, BorderLayout.NORTH);
		
		JLabel lblInstalacion = new JLabel("Instalacion");
		top_grid.add(lblInstalacion, "cell 0 0, growx");
		
		CBInstalacion = new JComboBox<String>();
		top_grid.add(CBInstalacion, "cell 1 0, grow");
		CBInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CBInstalacion.getSelectedIndex()>0) { 
					btnTablaReservas.setEnabled(true);
					btnTablaReservas.setText("Mostrar reservas");
				}
				else {
					btnTablaReservas.setEnabled(false);
					btnTablaReservas.setText("Selecciona una instalacion");
				}
			}
		});
		
		JLabel lblFecha = new JLabel("Fecha");
		top_grid.add(lblFecha, "cell 0 1, growx");
		
		// ++Calendario desplegable
		UtilDateModel modelFecha = new UtilDateModel();
		modelFecha.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
		
		JDatePanelImpl dpFechaIni = new JDatePanelImpl(modelFecha, modelProperties);
		datePickerFecha = new JDatePickerImpl(dpFechaIni, new DateLabelFormatter());
		 
		top_grid.add(datePickerFecha, "cell 1 1, grow");
		// --Calendario desplegable
						
		btnTablaReservas = new JButton("Mostrar reservas");
		btnTablaReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		top_grid.add(btnTablaReservas, "flowx, cell 3 0 1 2");
		
		top_card.add(top_grid);
		top_layout.add(top_card, BorderLayout.CENTER);
		frame.add(top_layout, BorderLayout.PAGE_START);
		
		
		// PANEL CENTRAL
		JPanel table_card = new JPanel(new CardLayout(5, 0));
		
		tablaReservas = new JTable();
		tablaReservas.setName("tablaReservas");
		tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReservas.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tablaReservas);
		
		table_card.add(tablePanel);
		frame.add(table_card, BorderLayout.CENTER);

		JPanel detail_form = new JPanel(new MigLayout("gapy 15"));

		JLabel lbl_instalacion = new JLabel("Instalacion: ");
		lbl_instalacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instalacion = new JLabel("");
		instalacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_actividad = new JLabel("Actividad: ");
		lbl_actividad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actividad = new JLabel("");
		actividad.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_fecha = new JLabel("Fecha: ");
		lbl_fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fecha = new JLabel("");
		fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_hora = new JLabel("Hora: ");
		lbl_hora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		hora = new JLabel("");
		hora.setFont(new Font("Tahoma", Font.BOLD, 12));

		detail_form.add(lbl_instalacion, "cell 0 0, growx");
		detail_form.add(instalacion, "cell 1 0, grow");
		detail_form.add(lbl_actividad, "cell 0 1, growx");
		detail_form.add(actividad, "cell 1 1, grow");
		detail_form.add(lbl_fecha, "cell 0 2, growx");
		detail_form.add(fecha, "cell 1 2, grow");
		detail_form.add(lbl_hora, "cell 0 3, growx");
		detail_form.add(hora, "cell 1 3, grow");
		
		frame.add(detail_form, BorderLayout.LINE_END);
		
		
		// PANEL INFERIOR
		JPanel bottom_card = new JPanel(new CardLayout(10, 1));
		JPanel bottom_grid = new JPanel(new MigLayout("", "[][20px][][20px][]", "[10px][7px][10px][75px][45px]"));
		
		lbl_horas_reservadas = new JLabel();
		lbl_horas_reservadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bottom_grid.add(lbl_horas_reservadas, "cell 0 0, span, grow");
		lbl_horas_reservadas.setVisible(false);
		
		lbl_reservas_socio = new JLabel("Reservas propias");
		lbl_reservas_socio.setFont(new Font("Tahoma", Font.BOLD, 13));
		bottom_grid.add(lbl_reservas_socio, "cell 0 2, grow");
		
		tablaReservasSocio = new JTable();
		tablaReservasSocio.setName("tablaReservasSocio");
		tablaReservasSocio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReservasSocio.setDefaultEditor(Object.class, null); //readonly
		tableSocioPanel = new JScrollPane(tablaReservasSocio);
		bottom_grid.add(tableSocioPanel, "cell 0 3, span");
		
		btnCancelarReserva = new JButton("Cancelar reserva");
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
			}
		});
		bottom_grid.add(btnCancelarReserva, "cell 2 4");
		
		/*
		btnCrearReserva = new JButton("Hacer reserva");
		btnCrearReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//VistaReservaAsociacion VRA = new VistaReservaAsociacion();
				//VRA.frmReservaAsociacion.setVisible(true);
				ControladorReservasAsociacion CRA = new ControladorReservasAsociacion(new ModeloReservaAsociacion(), new VistaReservaAsociacion());
				CRA.initController();
			}
		});
		bottom_grid.add(btnCrearReserva, "cell 4 4");
		*/
		
		bottom_card.add(bottom_grid);
		frame.add(bottom_card, BorderLayout.PAGE_END);
	}



	public JPanel getFrame() {
		return this.frame;
	}

	public JComboBox<String> getCBInstalacion() {
		return this.CBInstalacion;
	}

	public String getDatePickerFecha() {
		int d = this.datePickerFecha.getModel().getDay();
		int m = this.datePickerFecha.getModel().getMonth()+1;
		int y = this.datePickerFecha.getModel().getYear();
		String fecha = y + "-" + m + "-" + d;
		return fecha;
	}
	public void setDatePickerFecha(String fecha) {
		String[] f = fecha.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFecha.getModel().setDate(a, m, d);
	}
	
	public JTable getTablaReservas() {
		return this.tablaReservas;
	}
	public JButton getBtnTablaReservas() {
		return this.btnTablaReservas;
	}

	public JButton getBtnCrearReserva() {
		return this.btnCrearReserva;
	}
	public void setBtnCrearReserva(JButton btnCrearReserva) {
		this.btnCrearReserva = btnCrearReserva;
	}
	
	public JTable getTablaReservasSocio() {
		return this.tablaReservasSocio;
	}
	

	public int getHoras_reservadas() {
		return horas_reservadas;
	}
	public void setHoras_reservadas(int horas_reservadas) {
		this.horas_reservadas = horas_reservadas;
	}

	public int getHoras_totales() {
		return horas_totales;
	}
	public void setHoras_totales(int horas_totales) {
		this.horas_totales = horas_totales;
	}
	
	public JLabel getLbl_horas_reservadas() {
		return lbl_horas_reservadas;
	}
	public void setLbl_horas_reservadas() {
		String fecha = this.getDatePickerFecha();
		this.lbl_horas_reservadas.setText(this.horas_reservadas + "/" + this.horas_totales + " horas están reservadas en el intervalo " + fecha + " - " );
	}

	
	// Para la info de las reservas
	public JLabel getInstalacion() {
		return this.instalacion;
	}
	public void setInstalacion(String instalacion) {
		this.instalacion.setText(instalacion);
	}

	public JLabel getActividad() {
		return this.actividad;
	}
	public void setActividad(String actividad) {
		this.actividad.setText(actividad);
	}

	public JLabel getFecha() {
		return this.fecha;
	}
	public void setFecha(String fecha) {
		this.fecha.setText(fecha);
	}

	public JLabel getHora() {
		return this.hora;
	}
	public void setHora(String hora) {
		this.hora.setText(hora);
	}

	public JLabel getLbl_reservas_socio() {
		return this.lbl_reservas_socio;
	}

	public JScrollPane getTableSocioPanel() {
		return this.tableSocioPanel;
	}

}
