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

public class inicialSocio {

	private JFrame frmIndex;
	private Login vLogin;
	private visualizarReservasS vVisualizarReservasS;
	private Reservar_Instalacion_Socio vReservarInstalacionS;
	private cancelarSocio vCancelarSocio;
	private Lista_Reservas vLista_Reservas;
	private JLabel lblBienvenido;
	private ver_pagos_socio vVerPagosSocio;
	private inscripcion_socio vInscripcionSocio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicialSocio window = new inicialSocio();
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
	public inicialSocio() {
		initialize();
	}

	
	public inicialSocio(Login vLogin) {
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
		
		JButton btnDisponibilidadInstalaciones = new JButton("Disponibilidad Instalaciones\r\n");
		btnDisponibilidadInstalaciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisponibilidadInstalaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVisualizarReservasS = new visualizarReservasS(vLogin);
				vVisualizarReservasS.getFrmVisualizarReservas().setVisible(true);
			}
		});
		btnDisponibilidadInstalaciones.setBounds(298, 182, 207, 23);
		panel.add(btnDisponibilidadInstalaciones);
		
		JButton btnNewButton_1_2 = new JButton("Reservar Instalacion\r\n");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a Reservar_Instalacion_Socio
				vReservarInstalacionS = new Reservar_Instalacion_Socio(vLogin);
				vReservarInstalacionS.getFrmReservarInstalacin().setVisible(true);
				
			}
		});
		btnNewButton_1_2.setBounds(25, 182, 207, 23);
		panel.add(btnNewButton_1_2);
		
		lblBienvenido = new JLabel("Bienvenido/a, ");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblBienvenido.setBounds(25, 39, 455, 84);
		panel.add(lblBienvenido);
		
		JButton eliminarReservasBtn = new JButton("Eliminar una reserva");
		eliminarReservasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a cancelarSocio
				vCancelarSocio = new cancelarSocio(vLogin);
				vCancelarSocio.getFrmCancelarSocio().setVisible(true);
			}
		});
		eliminarReservasBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eliminarReservasBtn.setBounds(25, 216, 207, 23);
		panel.add(eliminarReservasBtn);
		
		JButton btnNewButton = new JButton("Ver Lista de Reservas");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vLista_Reservas = new Lista_Reservas(vLogin);
				vLista_Reservas.getFrmListaReservas().setVisible(true);
			}
		});
		btnNewButton.setBounds(298, 219, 207, 20);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1_2_1 = new JButton("Ver Pagos");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVerPagosSocio = new ver_pagos_socio(vLogin);
				vVerPagosSocio.getFrmVerPagos().setVisible(true);
			}
		});
		btnNewButton_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_2_1.setBounds(25, 250, 207, 23);
		panel.add(btnNewButton_1_2_1);
		
		JButton btnNewButton_1_2_1_1 = new JButton("Inscribirse actividad");
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInscripcionSocio = new inscripcion_socio(vLogin);
				vInscripcionSocio.getFrmInscripcinActividadSocio().setVisible(true);
			}
		});
		btnNewButton_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_2_1_1.setBounds(298, 250, 207, 23);
		panel.add(btnNewButton_1_2_1_1);
		
	}
	
	public void generaLbl() {
		lblBienvenido.setText(lblBienvenido.getText() + vLogin.getModelClientes().getNombreDesdeId(vLogin.getId_socio()).get(0)[0].toString() );
	}
}
