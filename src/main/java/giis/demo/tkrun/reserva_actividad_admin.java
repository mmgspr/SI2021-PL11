package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class reserva_actividad_admin {

	private JFrame frmReservaParaActividad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserva_actividad_admin window = new reserva_actividad_admin();
					window.frmReservaParaActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reserva_actividad_admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaParaActividad = new JFrame();
		frmReservaParaActividad.setTitle("Reserva para actividad");
		frmReservaParaActividad.setBounds(100, 100, 450, 300);
		frmReservaParaActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservaParaActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(28, 229, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reservar");
		btnNewButton_1.setBounds(319, 229, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Seleccione actividad:");
		lblNewLabel.setBounds(46, 49, 135, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSeleccionePeriodo = new JLabel("Seleccione periodo:");
		lblSeleccionePeriodo.setBounds(46, 100, 135, 14);
		panel.add(lblSeleccionePeriodo);
		
		JLabel lblSeleccionePeriodo_1 = new JLabel("Seleccione periodo:");
		lblSeleccionePeriodo_1.setBounds(46, 153, 135, 14);
		panel.add(lblSeleccionePeriodo_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(205, 45, 30, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(205, 96, 30, 22);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(205, 149, 30, 22);
		panel.add(comboBox_2);
	}
}
