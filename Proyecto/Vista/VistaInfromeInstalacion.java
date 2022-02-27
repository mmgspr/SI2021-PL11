package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class VistaInfromeInstalacion {

	public JFrame frame;
	public JComboBox<String> CBInstalaciones;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInfromeInstalacion window = new VistaInfromeInstalacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaInfromeInstalacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 170);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CBInstalaciones = new JComboBox<String>();
		CBInstalaciones.setBounds(10, 49, 274, 20);
		frame.getContentPane().add(CBInstalaciones);
		
		btnNewButton = new JButton("Realizar Informe\r\n");
		
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(10, 97, 135, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cancelar\r\n");
		btnNewButton_1.setBounds(155, 97, 129, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Instalacion:\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 264, 20);
		frame.getContentPane().add(lblNewLabel);
	}

}
