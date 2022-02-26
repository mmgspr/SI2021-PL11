package giis.proyecto.Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import giis.proyecto.Controlador.ControladorInscripcionSocio;
import giis.proyecto.Controlador.InscripcionController;

public class VistaInscripcionSocio {
	
	public JFrame frmInscripcionS;
	public JTextField TFieldDNI;
	public JComboBox<String> CBActividad;
	public JButton JButtonCrear;
	public JButton JButtonCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInscripcionSocio window = new VistaInscripcionSocio();
					window.frmInscripcionS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaInscripcionSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInscripcionS = new JFrame();
		frmInscripcionS.setResizable(false);
		frmInscripcionS.setTitle("Inscripcion de socio para una actividad");
		frmInscripcionS.setBounds(100, 100, 318, 255);
		frmInscripcionS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInscripcionS.getContentPane().setLayout(null);


		JButtonCrear = new JButton("Inscripcion");
		//JButtonCrear.setEnabled(true);
		JButtonCrear.setBounds(104, 182, 89, 25);
		frmInscripcionS.getContentPane().add(JButtonCrear);

		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.setBounds(203, 182, 89, 25);
		frmInscripcionS.getContentPane().add(JButtonCancelar);
		
		CBActividad = new JComboBox<String>();
		
		CBActividad.setBounds(104, 62, 188, 20);
		frmInscripcionS.getContentPane().add(CBActividad);

		JLabel lblEtiqueta = new JLabel("DNI:");
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEtiqueta.setBounds(8, 112, 76, 25);
		frmInscripcionS.getContentPane().add(lblEtiqueta);

		TFieldDNI = new JTextField();
		
		TFieldDNI.setBounds(94, 112, 198, 25);
		frmInscripcionS.getContentPane().add(TFieldDNI);
		TFieldDNI.setColumns(10);
		
		JLabel lblActividad = new JLabel("Actividad:");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblActividad.setBounds(10, 57, 76, 25);
		frmInscripcionS.getContentPane().add(lblActividad);


		//Controllador
		ControladorInscripcionSocio IC= new ControladorInscripcionSocio(this);

	}
}