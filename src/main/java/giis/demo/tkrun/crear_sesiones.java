package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;

public class crear_sesiones {

	private JFrame frmCrearSesiones;
	
	private crear_actividad ventanaCrearActividad;

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
	}

}
