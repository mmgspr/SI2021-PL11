package giis.proyecto.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaNuevaReserva {

	public JFrame frmNuevaReserva;
	public JTextField TFieldIDInstalacion;
	public JTextField TFieldIR;
	public JTextField TFieldFR;
	public JTextField TFieldIDSocio;
	public JComboBox<String> CBDesde;
	public JComboBox<String> CBHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaNuevaReserva window = new VistaNuevaReserva();
					window.frmNuevaReserva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaNuevaReserva() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaReserva = new JFrame();
		frmNuevaReserva.setResizable(false);
		frmNuevaReserva.setTitle("Nueva Reserva");
		frmNuevaReserva.setBounds(100, 100, 250, 420);
		frmNuevaReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNuevaReserva.getContentPane().setLayout(null);
		
		JLabel lblInstalacion = new JLabel("Instalacion:");
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInstalacion.setBounds(10, 11, 129, 20);
		frmNuevaReserva.getContentPane().add(lblInstalacion);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(20, 42, 46, 14);
		frmNuevaReserva.getContentPane().add(lblId);
		
		TFieldIDInstalacion = new JTextField();
		TFieldIDInstalacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				Toolkit tk=Toolkit.getDefaultToolkit();
				if(Character.isLetter(c) || (TFieldIDInstalacion.getText().length()==3)) {
					tk.beep();
					arg0.consume();
				}
			}
		});
		TFieldIDInstalacion.setBounds(53, 42, 86, 20);
		frmNuevaReserva.getContentPane().add(TFieldIDInstalacion);
		TFieldIDInstalacion.setColumns(10);
		
		JLabel lblFechaReserva = new JLabel("Fecha Inicio Reserva:");
		lblFechaReserva.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFechaReserva.setBounds(20, 104, 170, 14);
		frmNuevaReserva.getContentPane().add(lblFechaReserva);
		
		TFieldIR = new JTextField();
		TFieldIR.setBounds(80, 129, 86, 20);
		frmNuevaReserva.getContentPane().add(TFieldIR);
		TFieldIR.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fecha Fin Reserva:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 160, 170, 14);
		frmNuevaReserva.getContentPane().add(lblNewLabel);
		
		JLabel lblPeriodoDeReserva = new JLabel("Periodo de Reserva:");
		lblPeriodoDeReserva.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPeriodoDeReserva.setBounds(10, 73, 215, 20);
		frmNuevaReserva.getContentPane().add(lblPeriodoDeReserva);
		
		TFieldFR = new JTextField();
		TFieldFR.setBounds(80, 185, 86, 20);
		frmNuevaReserva.getContentPane().add(TFieldFR);
		TFieldFR.setColumns(10);
		
		JLabel lblHoraDeInicio = new JLabel("Desde:");
		lblHoraDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHoraDeInicio.setBounds(20, 220, 59, 14);
		frmNuevaReserva.getContentPane().add(lblHoraDeInicio);
		
		JComboBox CBDesde = new JComboBox();
		CBDesde.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		CBDesde.setBounds(80, 220, 59, 20);
		frmNuevaReserva.getContentPane().add(CBDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHasta.setBounds(20, 255, 59, 14);
		frmNuevaReserva.getContentPane().add(lblHasta);
		
		JComboBox CBHasta = new JComboBox();
		CBHasta.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		CBHasta.setBounds(80, 255, 59, 20);
		frmNuevaReserva.getContentPane().add(CBHasta);
		
		JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSocio.setBounds(10, 286, 70, 20);
		frmNuevaReserva.getContentPane().add(lblSocio);
		
		JLabel lblIdSocio = new JLabel("ID Socio:");
		lblIdSocio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdSocio.setBounds(20, 317, 70, 14);
		frmNuevaReserva.getContentPane().add(lblIdSocio);
		
		TFieldIDSocio = new JTextField();
		TFieldIDSocio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				Toolkit tk=Toolkit.getDefaultToolkit();
				if(Character.isLetter(c) || (TFieldIDSocio.getText().length()==3)) {
					tk.beep();
					arg0.consume();
				}
				
			}
		});
		TFieldIDSocio.setBounds(100, 317, 50, 20);
		frmNuevaReserva.getContentPane().add(TFieldIDSocio);
		TFieldIDSocio.setColumns(10);
		
		JButton JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFrameR().setVisible(false);
			}
		});
		JButtonCancelar.setBounds(136, 357, 89, 23);
		frmNuevaReserva.getContentPane().add(JButtonCancelar);
		
		JButton JButtonCrear = new JButton("Crear");
		JButtonCrear.setBounds(20, 357, 89, 23);
		frmNuevaReserva.getContentPane().add(JButtonCrear);
	}
	
	
	public JFrame getFrameR() { return this.frmNuevaReserva;}
	
	public String getIDInstalacion() {return this.TFieldIDInstalacion.getText();}
	
	public String getFInicio() { return this.TFieldIR.getText();}
	public void setFInicio(String d) { this.TFieldIR.setText(d);}
	
	public String getFFinal() {return this.TFieldFR.getText();}
	
	public String getCBDesde() {return (String)this.CBDesde.getSelectedItem();}
	public String getCBHasta() {return (String)this.CBHasta.getSelectedItem();}
	
	public String getIDSocio() { return this.TFieldIDSocio.getText();}

	
}
