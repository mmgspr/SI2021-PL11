package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class reserva_admin_cliente {

	private JFrame frmReservaInstalacion;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserva_admin_cliente window = new reserva_admin_cliente();
					window.frmReservaInstalacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reserva_admin_cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaInstalacion = new JFrame();
		frmReservaInstalacion.setTitle("RESERVA INSTALACION");
		frmReservaInstalacion.setBounds(100, 100, 700, 500);
		frmReservaInstalacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservaInstalacion.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione instalaci\u00F3n:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 37, 178, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSeleccioneFechaY = new JLabel("Introduzca fecha y hora:");
		lblSeleccioneFechaY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneFechaY.setBounds(42, 96, 178, 14);
		panel.add(lblSeleccioneFechaY);
		
		JLabel lblNDeSocio = new JLabel("N\u00BA de socio:");
		lblNDeSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNDeSocio.setBounds(42, 157, 178, 14);
		panel.add(lblNDeSocio);
		
		JButton btnNewButton = new JButton("Reservar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(538, 398, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(42, 398, 89, 23);
		panel.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(150, 156, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("(dd/mm/aa hh/mm)");
		lblNewLabel_1.setBounds(65, 111, 100, 14);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(230, 95, 96, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//array para introducir todos 
		String[] instalaciones = new String[10];
		for(int i=0; i<instalaciones.length; i++) {
			instalaciones[i]="HOla"; //cambiar hola por el elemento de la lista del modelo que toque
		}
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox.setBounds(205, 35, 107, 22);
		panel.add(comboBox);
	}
}
