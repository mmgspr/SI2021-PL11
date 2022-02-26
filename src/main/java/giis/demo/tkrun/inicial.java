package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inicial {

	private JFrame frmIndex;
	private SwingMain ventanaMain;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicial window = new inicial();
					window.frmIndex.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inicial() {
		initialize();
	}

	public inicial(SwingMain vSM) {
		this.ventanaMain=vSM;
		initialize();
		
	}
	
	public JFrame getFrmIndex() {
		return this.frmIndex;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIndex = new JFrame();
		frmIndex.setTitle("index");
		frmIndex.setBounds(100, 100, 450, 300);
		frmIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmIndex.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Inscripcion actividad");
		btnNewButton.setBounds(10, 11, 181, 23);
		panel.add(btnNewButton);
		
		JButton btnDisponibilidadInstalaciones = new JButton("Disponibilidad instalaciones");
		btnDisponibilidadInstalaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDisponibilidadInstalaciones.setBounds(10, 56, 181, 23);
		panel.add(btnDisponibilidadInstalaciones);
		
		JButton btnNewButton_1_1 = new JButton("Admin reserva instalacion");
		btnNewButton_1_1.setBounds(10, 95, 181, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Reservar instalacion");
		btnNewButton_1_2.setBounds(10, 139, 181, 23);
		panel.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_4 = new JButton("Visualizar reservas");
		btnNewButton_1_4.setBounds(225, 11, 181, 23);
		panel.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_5 = new JButton("Planificar actividad");
		btnNewButton_1_5.setBounds(225, 56, 181, 23);
		panel.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("Reserva para actividad");
		btnNewButton_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_6.setBounds(225, 95, 181, 23);
		panel.add(btnNewButton_1_6);
		
		JButton btnNewButton_1_7 = new JButton("Lista actividades");
		btnNewButton_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_7.setBounds(225, 139, 181, 23);
		panel.add(btnNewButton_1_7);
		
		JButton btnCrearBase = new JButton("crear base");
		btnCrearBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Database db = new Database();
				
			}
		});
		btnCrearBase.setBounds(269, 211, 89, 23);
		panel.add(btnCrearBase);
	}
	
}
