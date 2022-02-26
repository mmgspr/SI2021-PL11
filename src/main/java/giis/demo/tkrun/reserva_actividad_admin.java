package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;

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
		frmReservaParaActividad.setBounds(100, 100, 700, 510);
		frmReservaParaActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservaParaActividad.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(28, 439, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reservar");
		btnNewButton_1.setBounds(567, 439, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Seleccione actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(46, 79, 135, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSeleccionePeriodo = new JLabel("Seleccione instalación:");
		lblSeleccionePeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo.setBounds(46, 153, 163, 14);
		panel.add(lblSeleccionePeriodo);
		
		JLabel lblSeleccionePeriodo_1 = new JLabel("Seleccione fecha inicial:");
		lblSeleccionePeriodo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo_1.setBounds(46, 229, 177, 14);
		panel.add(lblSeleccionePeriodo_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(242, 79, 156, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(242, 151, 156, 22);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(242, 227, 156, 22);
		panel.add(comboBox_2);
		
		JLabel lblSeleccionePeriodo_1_1 = new JLabel("Seleccione fecha inicial:");
		lblSeleccionePeriodo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionePeriodo_1_1.setBounds(46, 298, 163, 14);
		panel.add(lblSeleccionePeriodo_1_1);
		
		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setBounds(242, 296, 156, 22);
		panel.add(comboBox_2_1);


	}
}
