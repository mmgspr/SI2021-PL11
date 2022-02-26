package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class crear_sesiones {

	private JFrame frmCrearSesiones;
	
	private crear_actividad ventanaCrearActividad;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crear_sesiones window = new crear_sesiones();
					window.frmCrearSesiones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public crear_sesiones(crear_actividad vSM) {
		this.ventanaCrearActividad=vSM;
		initialize();
		
	}

	/**
	 * Create the application.
	 */
	public crear_sesiones() {
		initialize();
	}
	
	public JFrame getFrmCrearSesiones() {
		return this.frmCrearSesiones;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearSesiones = new JFrame();
		frmCrearSesiones.setTitle("Crear Sesiones");
		frmCrearSesiones.setBounds(100, 100, 700, 500);
		frmCrearSesiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearSesiones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDa = new JLabel("• Día:");
		lblDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDa.setBounds(10, 11, 42, 17);
		panel.add(lblDa);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<selecciona un día>", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"}));
		comboBox.setBounds(62, 10, 146, 22);
		panel.add(comboBox);
		
		JLabel lblHoraInicio = new JLabel("• Hora inicio:");
		lblHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoraInicio.setBounds(10, 54, 89, 17);
		panel.add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("• Hora fin:");
		lblHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoraFin.setBounds(10, 94, 67, 17);
		panel.add(lblHoraFin);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.setBounds(10, 145, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 244, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(585, 244, 89, 23);
		panel.add(btnGuardar);
		
		JLabel lblSesiones = new JLabel("• Sesiones:");
		lblSesiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSesiones.setBounds(330, 14, 89, 17);
		panel.add(lblSesiones);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(340, 42, 334, 191);
		panel.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(95, 54, 104, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(77, 94, 104, 20);
		panel.add(textField_1);
	}
}
