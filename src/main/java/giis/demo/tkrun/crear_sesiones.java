package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;

public class crear_sesiones {

	private JFrame frmCrearSesiones;
	
	private crear_actividad ventanaCrearActividad;
	private JTextField textField;
	private JTextField textField_1;

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
		frmCrearSesiones.setBounds(100, 100, 700, 350);
		frmCrearSesiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearSesiones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDa = new JLabel("• Día:");
		lblDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDa.setBounds(10, 11, 42, 17);
		panel.add(lblDa);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<selecciona un día>", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"}));
		comboBox.setBounds(62, 10, 146, 22);
		panel.add(comboBox);
		
		JLabel lblHoraInicio = new JLabel("• Hora inicio:");
		lblHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoraInicio.setBounds(10, 54, 89, 17);
		panel.add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("• Hora fin:");
		lblHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoraFin.setBounds(10, 94, 67, 17);
		panel.add(lblHoraFin);
		
		JLabel lblSesiones = new JLabel("• Sesiones:");
		lblSesiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSesiones.setBounds(330, 14, 89, 17);
		panel.add(lblSesiones);
		
		textField = new JTextField();
		textField.setBounds(95, 54, 104, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(77, 94, 104, 20);
		panel.add(textField_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(340, 42, 334, 191);
		panel.add(textArea);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"No se ha podido añadir la sesión. \nSelecciona un día.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido añadir la sesión. \nIntroduce una hora de inicio.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido añadir la sesión. \nIntroduce una hora de fin.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					textArea.setText(textArea.getText()+comboBox.getModel().getElementAt(comboBox.getSelectedIndex())+" de "+textField.getText()+" a "+textField_1.getText()+"\n");
					//textField.getText();
					//JOptionPane.showMessageDialog(null,"La sesión se ha añadido correctamente","Añadido",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(10, 145, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 277, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Las sesiones se han guardado correctamento.","Guardado",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnGuardar.setBounds(585, 277, 89, 23);
		panel.add(btnGuardar);
		
		
		
		
	}
}
