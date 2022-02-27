package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reservar_Instalacion_Socio {

	private JFrame frmReservarInstalacin;
	private JTextField textFieldFecha;
	private JTextField textFieldHoraIni;
	private JTextField textFieldHoraFin;
	private JTextField textFieldCoste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservar_Instalacion_Socio window = new Reservar_Instalacion_Socio();
					window.frmReservarInstalacin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Reservar_Instalacion_Socio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarInstalacin = new JFrame();
		frmReservarInstalacin.setTitle("Reservar Instalación");
		frmReservarInstalacin.setBounds(100, 100, 450, 300);
		frmReservarInstalacin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservarInstalacin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelFechadeReserva = new JLabel("Fecha de Reserva:");
		LabelFechadeReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFechadeReserva.setBounds(10, 10, 126, 29);
		panel.add(LabelFechadeReserva);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(127, 17, 126, 19);
		panel.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel LabelHoraIni = new JLabel("Hora de Inicio:");
		LabelHoraIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelHoraIni.setBounds(10, 51, 113, 29);
		panel.add(LabelHoraIni);
		
		textFieldHoraIni = new JTextField();
		textFieldHoraIni.setBounds(127, 58, 126, 19);
		panel.add(textFieldHoraIni);
		textFieldHoraIni.setColumns(10);
		
		JLabel LabelHoraFin = new JLabel("Hora de Fin:");
		LabelHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelHoraFin.setBounds(10, 90, 91, 19);
		panel.add(LabelHoraFin);
		
		textFieldHoraFin = new JTextField();
		textFieldHoraFin.setBounds(127, 92, 126, 19);
		panel.add(textFieldHoraFin);
		textFieldHoraFin.setColumns(10);
		
		JLabel LabelCoste = new JLabel("Coste de la Reserva:");
		LabelCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelCoste.setBounds(10, 159, 141, 29);
		panel.add(LabelCoste);
		
		textFieldCoste = new JTextField();
		textFieldCoste.setBounds(146, 166, 126, 19);
		panel.add(textFieldCoste);
		textFieldCoste.setColumns(10);
		
		JLabel LabelMetododePago = new JLabel("Método de Pago:");
		LabelMetododePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelMetododePago.setBounds(47, 227, 113, 29);
		panel.add(LabelMetododePago);
		
		JCheckBox CheckBoxEstaLibre = new JCheckBox("Está libre");
		CheckBoxEstaLibre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxEstaLibre.setBounds(69, 130, 93, 21);
		panel.add(CheckBoxEstaLibre);
		
		JCheckBox CheckBoxPuedesReservar = new JCheckBox("Puedes Reservar");
		CheckBoxPuedesReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxPuedesReservar.setBounds(239, 130, 152, 21);
		panel.add(CheckBoxPuedesReservar);
		
		JComboBox comboBoxMetodo = new JComboBox();
		comboBoxMetodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMetodo.setModel(new DefaultComboBoxModel(new String[] {"<selecciona uno>", "Pagar ahora", "Añadir a mi cuota"}));
		comboBoxMetodo.setBounds(146, 196, 170, 21);
		panel.add(comboBoxMetodo);
		
		JButton ButtonCancelar = new JButton("Cancelar\r\n");
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(182, 232, 85, 21);
		panel.add(ButtonCancelar);
		
		JButton ButtonGuardar = new JButton("Guardar");
		ButtonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ButtonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonGuardar.setBounds(316, 232, 85, 21);
		panel.add(ButtonGuardar);
	}
}
