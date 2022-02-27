package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;


public class VistaLoginSocio {

	public JFrame frmInicioDeSesion;
	public JTextField TFieldUsuario;
	public JPasswordField PField;
	public JButton btnIniciarSesion;
	public JLabel LabelError;
	public JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLoginSocio window = new VistaLoginSocio();
					window.frmInicioDeSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaLoginSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInicioDeSesion = new JFrame();
		frmInicioDeSesion.setTitle("Inicio de Sesion\r\n");
		frmInicioDeSesion.setResizable(false);
		frmInicioDeSesion.setBounds(100, 100, 380, 240);
		frmInicioDeSesion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInicioDeSesion.getContentPane().setLayout(null);
		
		JLabel lblDniOCorreo = new JLabel("DNI o Correo:\r\n");
		lblDniOCorreo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDniOCorreo.setBounds(30, 30, 128, 20);
		frmInicioDeSesion.getContentPane().add(lblDniOCorreo);
		
		TFieldUsuario = new JTextField();
		TFieldUsuario.setBounds(30, 61, 300, 20);
		frmInicioDeSesion.getContentPane().add(TFieldUsuario);
		TFieldUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblContrasea.setBounds(30, 92, 101, 20);
		frmInicioDeSesion.getContentPane().add(lblContrasea);
		
		PField = new JPasswordField();
		PField.setBounds(30, 123, 300, 20);
		frmInicioDeSesion.getContentPane().add(PField);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setEnabled(false);
		
		btnIniciarSesion.setBounds(63, 168, 120, 23);
		frmInicioDeSesion.getContentPane().add(btnIniciarSesion);
		
		btnCancelar = new JButton("Cancelar");
		
		btnCancelar.setBounds(193, 168, 120, 23);
		frmInicioDeSesion.getContentPane().add(btnCancelar);
		
		LabelError = new JLabel("\r\n");
		LabelError.setForeground(Color.RED);
		LabelError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LabelError.setBounds(30, 11, 300, 20);
		frmInicioDeSesion.getContentPane().add(LabelError);
	}
}
