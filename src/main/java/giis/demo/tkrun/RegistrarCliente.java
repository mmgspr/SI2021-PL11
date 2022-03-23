package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

public class RegistrarCliente {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarCliente window = new RegistrarCliente();
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
	public RegistrarCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton registrarBtn = new JButton("Registrar");
		registrarBtn.setBounds(313, 229, 89, 23);
		panel.add(registrarBtn);
		
		JRadioButton socioRdBtn = new JRadioButton("Nuevo socio");
		socioRdBtn.setSelected(true);
		buttonGroup.add(socioRdBtn);
		socioRdBtn.setBounds(18, 21, 111, 23);
		panel.add(socioRdBtn);
		
		JRadioButton noSocioRdBtn = new JRadioButton("Nuevo no socio");
		buttonGroup.add(noSocioRdBtn);
		noSocioRdBtn.setBounds(18, 47, 146, 23);
		panel.add(noSocioRdBtn);
		
		JLabel dniLbl = new JLabel("DNI:");
		dniLbl.setBounds(18, 77, 49, 14);
		panel.add(dniLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(18, 102, 49, 14);
		panel.add(nombreLbl);
		
		JLabel tlfLbl = new JLabel("Tlf.:");
		tlfLbl.setBounds(175, 77, 49, 14);
		panel.add(tlfLbl);
	}
}
