package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;


import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;


public class VistaPagoSocios {

	public JFrame frmPago;
	public JLabel LInstalacion;
	public JLabel LPrecio;
	public JLabel LHoras;
	public JLabel LCosteTotal;
	public JRadioButton rdbtnEfectivo;
	public JRadioButton rdbtnTarjeta;
	public JTextField numeroTarjeta;
	public JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPagoAdministracion window = new VistaPagoAdministracion();
					window.frmPago.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaPagoSocios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPago = new JFrame();
		frmPago.setTitle("Pago");
		frmPago.setResizable(false);
		frmPago.setBounds(100, 100, 205, 310);
		frmPago.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPago.getContentPane().setLayout(null);
		
		JLabel lblReserva = new JLabel("Reserva:");
		lblReserva.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblReserva.setBounds(10, 11, 93, 20);
		frmPago.getContentPane().add(lblReserva);
		
		LInstalacion = new JLabel("");
		LInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LInstalacion.setBounds(20, 37, 165, 20);
		frmPago.getContentPane().add(LInstalacion);
		
		JLabel lblNewLabel = new JLabel("Importe:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 68, 93, 20);
		frmPago.getContentPane().add(lblNewLabel);
		
		JLabel lblPrecioPorHora = new JLabel("Precio por hora:");
		lblPrecioPorHora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecioPorHora.setBounds(20, 99, 108, 20);
		frmPago.getContentPane().add(lblPrecioPorHora);
		
		JLabel lblNumeroDeHoras = new JLabel("Numero de Horas:");
		lblNumeroDeHoras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumeroDeHoras.setBounds(20, 120, 121, 20);
		frmPago.getContentPane().add(lblNumeroDeHoras);
		
		LPrecio = new JLabel("");
		LPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		LPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LPrecio.setBounds(139, 99, 46, 20);
		frmPago.getContentPane().add(LPrecio);
		
		LHoras = new JLabel("");
		LHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		LHoras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LHoras.setBounds(160, 120, 25, 20);
		frmPago.getContentPane().add(LHoras);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(145, 140, 40, 10);
		frmPago.getContentPane().add(separator);
		
		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(147, 127, 11, 14);
		frmPago.getContentPane().add(label);
		
		JLabel lblCosteTotal = new JLabel("Coste Total:");
		lblCosteTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCosteTotal.setBounds(20, 147, 93, 20);
		frmPago.getContentPane().add(lblCosteTotal);
		
		LCosteTotal = new JLabel("");
		LCosteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		LCosteTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		LCosteTotal.setBounds(139, 148, 46, 20);
		frmPago.getContentPane().add(LCosteTotal);
		
		rdbtnEfectivo = new JRadioButton("Efectivo");
		rdbtnEfectivo.setSelected(true);
		rdbtnEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnEfectivo.setBounds(50, 175, 78, 23);
		frmPago.getContentPane().add(rdbtnEfectivo);
		
		rdbtnTarjeta = new JRadioButton("Tarjeta");
		
		rdbtnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnTarjeta.setBounds(50, 195, 78, 23);
		frmPago.getContentPane().add(rdbtnTarjeta);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEfectivo);
		group.add(rdbtnTarjeta);
		
		numeroTarjeta = new JTextField();
		
		
		
		numeroTarjeta.setEnabled(false);
		numeroTarjeta.setBounds(10, 220, 175, 20);
		frmPago.getContentPane().add(numeroTarjeta);
		numeroTarjeta.setColumns(10);
		
		btnNewButton = new JButton("Realizar pago");
		
		btnNewButton.setBounds(30, 248, 140, 25);
		frmPago.getContentPane().add(btnNewButton);
		
		
		
		
	}
}
