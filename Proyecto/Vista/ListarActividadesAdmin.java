package giis.proyecto.Vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import org.jdatepicker.impl.*;
import net.miginfocom.swing.MigLayout;


public class ListarActividadesAdmin {

	private JPanel frame;
	private JTable tablaActividades;
	private JButton btnTablaActividades;
	private JButton btnCrearActividad;
	public JDatePickerImpl datePickerFechaIni;
	public JDatePickerImpl datePickerFechaFin;
	private JLabel fecha_mal;
	
	//info actividad
	private JLabel instalacion;
	private JLabel cuota_socio;
	private JLabel cuota_no_socio;
	private JLabel periodo;
	private JPanel periodo_ins_act;
	private JLabel ini_ins_s;
	private JLabel fin_ins_s;
	private JLabel fin_ins_ns;


	/**
	 * Create the application.
	 */
	public ListarActividadesAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Creamos un JPanel en vez de JFrame para introducir esta vista en otra ventana
		frame = new JPanel();
		//frame.setTitle("Actividades");
		//frame.setBounds(50, 50, 950, 300);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Container borderLayout = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		
		// PANEL SUPERIOR
		JPanel top_layout = new JPanel(new BorderLayout());
		JPanel top_card_label = new JPanel(new CardLayout(11, 7));
		JPanel top_card = new JPanel(new CardLayout(10, 0));
		JPanel top_grid = new JPanel();
		top_grid.setLayout(new MigLayout("", "[100px][145px][5px][20px][169px]", "[25px][25px]"));
		
		JLabel lblSeMostrarnLas = new JLabel("Se mostrarán las actividades cuya fecha de inicio se encuentre entre las dos fechas introducidas");
		lblSeMostrarnLas.setToolTipText("Se mostrarán las actividades cuya fecha de inicio se encuentre entre las dos fechas introducidas");
		top_card_label.add(lblSeMostrarnLas);
		top_layout.add(top_card_label, BorderLayout.NORTH);
		
		JLabel lblFechaInicial = new JLabel("Fecha inicial");
		top_grid.add(lblFechaInicial, "cell 0 0, growx");
		
		// ++Calendario desplegable
		UtilDateModel modelFechaIni = new UtilDateModel();
		modelFechaIni.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
		
		JDatePanelImpl dpFechaIni = new JDatePanelImpl(modelFechaIni, modelProperties);
		datePickerFechaIni = new JDatePickerImpl(dpFechaIni, new DateLabelFormatter());
		 
		top_grid.add(datePickerFechaIni, "cell 1 0, grow");
		// --Calendario desplegable
		
		JLabel lblFechaFinal = new JLabel("Fecha final");
		top_grid.add(lblFechaFinal, "cell 0 1, growx");

		// ++Calendario desplegable2
		UtilDateModel modelFechaFin = new UtilDateModel();
		modelFechaFin.setSelected(true);
		JDatePanelImpl dpFechaFin = new JDatePanelImpl(modelFechaFin, modelProperties);
		datePickerFechaFin = new JDatePickerImpl(dpFechaFin, new DateLabelFormatter());
		 
		top_grid.add(datePickerFechaFin, "cell 1 1, grow");
		// --Calendario desplegable2
		
		fecha_mal = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
		fecha_mal.setToolTipText("AVISO: La fecha final del intervalo es anterior a la fecha inicial!");
		top_grid.add(fecha_mal, "cell 3 0, span 0 2");
		fecha_mal.setVisible(false);

		btnTablaActividades = new JButton("Mostrar actividades");
		btnTablaActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		top_grid.add(btnTablaActividades, "cell 4 0, span 0 2");
		
		top_card.add(top_grid);
		top_layout.add(top_card, BorderLayout.CENTER);
		frame.add(top_layout, BorderLayout.PAGE_START);
		
		
		// PANEL CENTRAL
		JPanel mid_card = new JPanel(new CardLayout(5, 0));
		
		tablaActividades = new JTable();
		tablaActividades.setName("tablaActividades");
		tablaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaActividades.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tablaActividades);
		
		mid_card.add(tablePanel);
		frame.add(mid_card, BorderLayout.CENTER);
		//tablaActividades.setRows(5);
		
		JPanel detail_form = new JPanel(new MigLayout("gapy 15"));
		
		JLabel lbl_instalacion = new JLabel("Instalacion: ");
		lbl_instalacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		instalacion = new JLabel("");
		instalacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_cuota_socio = new JLabel("Cuota para socios: ");
		lbl_cuota_socio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cuota_socio = new JLabel("");
		cuota_socio.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_cuota_no_socio = new JLabel("Cuota para NO socios: ");
		lbl_cuota_no_socio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cuota_no_socio = new JLabel("");
		cuota_no_socio.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_periodo = new JLabel("Periodo: ");
		lbl_periodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		periodo = new JLabel("");
		periodo.setFont(new Font("Tahoma", Font.BOLD, 12));

		detail_form.add(lbl_instalacion, "cell 0 1, growx");
		detail_form.add(instalacion, "cell 1 1, grow");
		detail_form.add(lbl_cuota_socio, "cell 0 2, growx");
		detail_form.add(cuota_socio, "cell 1 2, grow");
		detail_form.add(lbl_cuota_no_socio, "cell 0 3, growx");
		detail_form.add(cuota_no_socio, "cell 1 3, grow");
		detail_form.add(lbl_periodo, "cell 0 4, growx");
		detail_form.add(periodo, "cell 1 4, grow");
		
		periodo_ins_act = new JPanel(new MigLayout("gapy 8, insets 15 8 2 5"));
		
		JLabel lbl_inscripcion = new JLabel("Inscripcion: ");
		lbl_inscripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbl_ini_ins_s = new JLabel("Inicio (socios): ");
		lbl_ini_ins_s.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ini_ins_s = new JLabel("");
		ini_ins_s.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbl_fin_ins_s = new JLabel("Fin (s)/ Inicio (ns): ");
		lbl_fin_ins_s.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fin_ins_s = new JLabel("");
		fin_ins_s.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbl_fin_ins_ns = new JLabel("Fin (no-socios): ");
		lbl_fin_ins_ns.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fin_ins_ns = new JLabel("");
		fin_ins_ns.setFont(new Font("Tahoma", Font.BOLD, 11));

		periodo_ins_act.add(lbl_inscripcion, "cell 0 0, growx, center");
		periodo_ins_act.add(lbl_ini_ins_s, "cell 1 1, grow");
		periodo_ins_act.add(ini_ins_s, "cell 2 1, growx");
		periodo_ins_act.add(lbl_fin_ins_s, "cell 1 2, grow");
		periodo_ins_act.add(fin_ins_s, "cell 2 2, growx");
		periodo_ins_act.add(lbl_fin_ins_ns, "cell 1 3, grow");
		periodo_ins_act.add(fin_ins_ns, "cell 2 3, growx");
		
		periodo_ins_act.setVisible(false);
		detail_form.add(periodo_ins_act, "south");
		frame.add(detail_form, BorderLayout.LINE_END);
		
		
		// PANEL INFERIOR
		JPanel bottom_card = new JPanel(new CardLayout(10, 1));
		JPanel bottom_grid = new JPanel();
		bottom_grid.setLayout(new MigLayout("right", "", "[5px][33px]"));
		
		bottom_card.add(bottom_grid);
		
		btnCrearActividad = new JButton("Crear actividad");
		btnCrearActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaCreacionActividades VCA = new VistaCreacionActividades();
				VCA.frmCrearActividad.setVisible(true);
			}
		});
		bottom_grid.add(btnCrearActividad, "cell 0 1, grow");
		frame.add(bottom_card, BorderLayout.PAGE_END);
	}

	
	
	public JPanel getFrame() {
		return frame;
	}

	public String getDatePickerFechaIni() {
		int d = this.datePickerFechaIni.getModel().getDay();
		int m = this.datePickerFechaIni.getModel().getMonth()+1;
		int y = this.datePickerFechaIni.getModel().getYear();
		String fechaIni = y + "-" + m + "-" + d;
		return fechaIni;
	}
	public void setDatePickerFechaIni(String fechaIni) {
		String[] f = fechaIni.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaIni.getModel().setDate(a, m, d);
	}
	
	public String getDatePickerFechaFin() {
		int d = this.datePickerFechaFin.getModel().getDay();
		int m = this.datePickerFechaFin.getModel().getMonth()+1;
		int y = this.datePickerFechaFin.getModel().getYear();
		String fechaFin = y + "-" + m + "-" + d;
		return fechaFin;
	}
	public void setDatePickerFechaFin(String fechaFin) {
		String[] f = fechaFin.split("-");
		int a = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1])-1;
		int d = Integer.parseInt(f[2]);
		this.datePickerFechaFin.getModel().setDate(a, m, d);
	}
	
	public JTable getTablaActividades() {
		return tablaActividades;
	}

	public JButton getBtnTablaActividades() {
		return btnTablaActividades;
	}

	public JButton getBtnCrearActividad() {
		return btnCrearActividad;
	}
	public void setBtnCrearActividad(JButton btnCrearActividad) {
		this.btnCrearActividad = btnCrearActividad;
	}

	public JLabel getFecha_mal() {
		return fecha_mal;
	}
	
	
	// Para la info de las actividades
	public JLabel getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(String instalacion) {
		this.instalacion.setText(instalacion);
	}

	public JLabel getCuota_socio() {
		return cuota_socio;
	}
	public void setCuota_socio(String cuota_socio) {
		this.cuota_socio.setText(cuota_socio);
	}

	public JLabel getCuota_no_socio() {
		return cuota_no_socio;
	}
	public void setCuota_no_socio(String cuota_no_socio) {
		this.cuota_no_socio.setText(cuota_no_socio);
	}

	public JLabel getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo.setText(periodo);
	}
	
	public JPanel getPeriodo_ins_act() {
		return periodo_ins_act;
	}

	public JLabel getIni_ins_s() {
		return ini_ins_s;
	}
	public void setIni_ins_s(String ini_ins_s) {
		this.ini_ins_s.setText(ini_ins_s);
	}

	public JLabel getFin_ins_s() {
		return fin_ins_s;
	}
	public void setFin_ins_s(String fin_ins_s) {
		this.fin_ins_s.setText(fin_ins_s);
	}

	public JLabel getFin_ins_ns() {
		return fin_ins_ns;
	}
	public void setFin_ins_ns(String fin_ins_ns) {
		this.fin_ins_ns.setText(fin_ins_ns);
	}
	
	
}
