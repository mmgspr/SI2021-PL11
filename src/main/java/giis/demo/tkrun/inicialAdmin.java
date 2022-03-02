package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class inicialAdmin {

	private JFrame frmIndex;
	private Login vLogin;
	private visualizarReservasA vVisualizarReservasA;
	private reserva_admin_cliente vReservaAdmin;
	private crear_actividad vCrearActividad;
	private Lista_Actividades_Administrador vListaActividadesA;
	private crear_periodo_inscripcion vCrearPeriodoInscripcion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicialAdmin window = new inicialAdmin();
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
	public inicialAdmin() {
		initialize();
	}

	
	public inicialAdmin(Login vLogin) {
		this.vLogin=vLogin;
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
		frmIndex.setTitle("Menú inicial");
		frmIndex.setBounds(100, 100, 531, 363);
		frmIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmIndex.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Crear Periodo de Inscripcion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCrearPeriodoInscripcion = new crear_periodo_inscripcion();
				vCrearPeriodoInscripcion.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 147, 207, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1_1 = new JButton("Reservar Instalación\r\n\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a reserva_admin_cliente
				vReservaAdmin = new reserva_admin_cliente();
				vReservaAdmin.getFrmReservaAdmin().setVisible(true);
			
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(10, 57, 207, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_4 = new JButton("Visualizar Reservas\r\n\r\n");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVisualizarReservasA = new visualizarReservasA();
				vVisualizarReservasA.getFrmVisualizarReservas().setVisible(true);
			}
		});
		btnNewButton_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_4.setBounds(305, 57, 181, 23);
		panel.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_5 = new JButton("Planificar Actividad\r\n");
		btnNewButton_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCrearActividad = new crear_actividad();
				vCrearActividad.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_5.setBounds(305, 147, 181, 23);
		panel.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("Reserva para Actividad\r\n\r\n");
		btnNewButton_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_6.setBounds(305, 238, 181, 23);
		panel.add(btnNewButton_1_6);
		
		JButton btnNewButton_1_7 = new JButton("Lista Actividades\r\n\r\n");
		btnNewButton_1_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a Lista_Actividades_Administrador
				vListaActividadesA = new Lista_Actividades_Administrador();
				vListaActividadesA.getFrmListaDeActividades().setVisible(true);
			
			}
		});
		btnNewButton_1_7.setBounds(10, 238, 207, 23);
		panel.add(btnNewButton_1_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 517, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Administración");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 114, 24);
		panel.add(lblNewLabel);
	}
}
