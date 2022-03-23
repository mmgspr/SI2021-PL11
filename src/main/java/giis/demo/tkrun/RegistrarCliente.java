package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class RegistrarCliente {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField dniTxt;
	private JTextField nombreTxt;
	private JTextField nacimientoTxt;
	private JTextField direccionTxt;
	private JTextField tlfTxt;
	
	private ClientesModel modeloClientes = new ClientesModel();
	private Login principal;

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
	
	public RegistrarCliente(Login l) {
		principal = l;
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
		nombreLbl.setBounds(18, 102, 60, 14);
		panel.add(nombreLbl);
		
		JLabel tlfLbl = new JLabel("Tlf.:");
		tlfLbl.setBounds(175, 77, 49, 14);
		panel.add(tlfLbl);
		
		JLabel fechaLbl = new JLabel("Fecha de nacimiento:");
		fechaLbl.setBounds(18, 127, 146, 14);
		panel.add(fechaLbl);
		
		JLabel direccionLbl = new JLabel("Dirección:");
		direccionLbl.setBounds(18, 152, 111, 14);
		panel.add(direccionLbl);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(44, 74, 96, 20);
		panel.add(dniTxt);
		dniTxt.setColumns(10);
		
		nombreTxt = new JTextField();
		nombreTxt.setColumns(10);
		nombreTxt.setBounds(88, 99, 96, 20);
		panel.add(nombreTxt);
		
		nacimientoTxt = new JTextField();
		nacimientoTxt.setColumns(10);
		nacimientoTxt.setBounds(157, 124, 96, 20);
		panel.add(nacimientoTxt);
		
		direccionTxt = new JTextField();
		direccionTxt.setColumns(10);
		direccionTxt.setBounds(88, 149, 96, 20);
		panel.add(direccionTxt);
		
		tlfTxt = new JTextField();
		tlfTxt.setColumns(10);
		tlfTxt.setBounds(199, 74, 96, 20);
		panel.add(tlfTxt);
		
		noSocioRdBtn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(noSocioRdBtn.isSelected()) {
					direccionTxt.setBounds(1000,1000,1000,1000);
					nacimientoTxt.setBounds(1000,1000,1000,1000);
					direccionLbl.setBounds(1000,1000,1000,1000);
					fechaLbl.setBounds(1000,1000,1000,1000);
				}
				else {
					direccionTxt.setBounds(88, 149, 96, 20);
					nacimientoTxt.setBounds(157, 124, 96, 20);
					direccionLbl.setBounds(18, 152, 111, 14);
					fechaLbl.setBounds(18, 127, 146, 14);
				}
			}
		});
		
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(socioRdBtn.isSelected()) {
					modeloClientes.nuevoSocio(nombreTxt.getText(), nacimientoTxt.getText(), dniTxt.getText(), tlfTxt.getText(),direccionTxt.getText());
					JOptionPane.showMessageDialog(frame,
						    "Se ha añadido el socio");
				}
				else {
					modeloClientes.nuevoCliente(nombreTxt.getText(), dniTxt.getText(), tlfTxt.getText());
					JOptionPane.showMessageDialog(frame,
						    "Se ha añadido el cliente");
				}
			}
		});
		
		
	}
	public JFrame getFrmRegistrarCliente() {
		return this.frame;
	}
}
