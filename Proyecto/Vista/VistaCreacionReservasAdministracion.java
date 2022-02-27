package giis.proyecto.Vista;



import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
//import giis.proyecto.Controlador.ReservasController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.util.Properties;



public class VistaCreacionReservasAdministracion {
	
	public JFrame frmReservaAsociacion;
	public JDatePickerImpl datePickerFechaInicio;
	public JComboBox<String> CBDesde;
	public JComboBox<String> CBHasta;
	public JButton JButtonCrear;
	public JComboBox<String> CBInstalaciones;
	public JLabel lblSolicitante;
	public JLabel LDni;
	public JTextField TFieldDni;
	public JLabel lblDesde;
	public JLabel lblHasta;
	public JLabel LPrecio;
	public JButton JButtonCancelar;
	

	
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
	public VistaCreacionReservasAdministracion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaAsociacion = new JFrame();
		frmReservaAsociacion.setResizable(false);
		frmReservaAsociacion.setTitle("Nueva Reserva");
		frmReservaAsociacion.setBounds(100, 100, 220, 380);
		frmReservaAsociacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmReservaAsociacion.getContentPane().setLayout(null);
		
		JLabel lblInstalacion = new JLabel("Instalacion:");
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstalacion.setBounds(10, 11, 129, 20);
		frmReservaAsociacion.getContentPane().add(lblInstalacion);
		
		// ++Calendario desplegable
		UtilDateModel modelFechaInicioS = new UtilDateModel();
		modelFechaInicioS.setSelected(true);
		Properties modelProperties = new Properties();
		modelProperties.put("text.today", "Today");
		modelProperties.put("text.month", "Month");
		modelProperties.put("text.year", "Year");
				
		JDatePanelImpl dpFechaIniS = new JDatePanelImpl(modelFechaInicioS, modelProperties);
		datePickerFechaInicio = new JDatePickerImpl(dpFechaIniS, new DateLabelFormatter());
		
		
		datePickerFechaInicio.setBounds(40, 106, 125, 30);
		frmReservaAsociacion.getContentPane().add(datePickerFechaInicio);
		
		JLabel lblPeriodoDeReserva = new JLabel("Fecha de Reserva:");
		lblPeriodoDeReserva.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPeriodoDeReserva.setBounds(10, 75, 181, 20);
		frmReservaAsociacion.getContentPane().add(lblPeriodoDeReserva);
		
		lblDesde = new JLabel("Desde:");
		lblDesde.setEnabled(false);
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDesde.setBounds(35, 142, 59, 14);
		frmReservaAsociacion.getContentPane().add(lblDesde);
		
		CBDesde = new JComboBox<String>();
		CBDesde.setEnabled(false);
		
		CBDesde.setModel(new DefaultComboBoxModel<String>(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		CBDesde.setBounds(106, 142, 59, 20);
		frmReservaAsociacion.getContentPane().add(CBDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setEnabled(false);
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHasta.setBounds(35, 176, 59, 14);
		frmReservaAsociacion.getContentPane().add(lblHasta);
		
		CBHasta = new JComboBox<String>();
		CBHasta.setEnabled(false);
		
		CBHasta.setModel(new DefaultComboBoxModel<String>(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		CBHasta.setBounds(106, 173, 59, 20);
		frmReservaAsociacion.getContentPane().add(CBHasta);
		
		
		
		JButtonCrear = new JButton("Crear");
		JButtonCrear.setEnabled(false);
		
		JButtonCrear.setBounds(10, 308, 89, 32);
		frmReservaAsociacion.getContentPane().add(JButtonCrear);
		
		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.setBounds(115, 308, 89, 32);
		frmReservaAsociacion.getContentPane().add(JButtonCancelar);
		
		CBInstalaciones = new JComboBox<String>();
		
		CBInstalaciones.setBounds(20, 42, 171, 20);
		frmReservaAsociacion.getContentPane().add(CBInstalaciones);
		
		
		
		lblSolicitante = new JLabel("Solicitante:");
		lblSolicitante.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSolicitante.setBounds(10, 235, 97, 20);
		frmReservaAsociacion.getContentPane().add(lblSolicitante);
		
		LDni = new JLabel("DNI:");
		LDni.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LDni.setBounds(10, 265, 46, 20);
		frmReservaAsociacion.getContentPane().add(LDni);
		
		TFieldDni = new JTextField();
		TFieldDni.setBounds(47, 266, 144, 20);
		frmReservaAsociacion.getContentPane().add(TFieldDni);
		TFieldDni.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrecio.setBounds(35, 210, 59, 20);
		frmReservaAsociacion.getContentPane().add(lblPrecio);
		
		LPrecio = new JLabel("");
		LPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LPrecio.setBounds(106, 210, 46, 20);
		frmReservaAsociacion.getContentPane().add(LPrecio);
		
		
	}
	
	
	public JFrame getFrameR() { return this.frmReservaAsociacion;}
	
	public String getCBDesde() {return (String)this.CBDesde.getSelectedItem();}
	public String getCBHasta() {return (String)this.CBHasta.getSelectedItem();}

	

	
	
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
}

