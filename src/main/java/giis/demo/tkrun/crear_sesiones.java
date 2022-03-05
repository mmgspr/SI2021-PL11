package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
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
	private List<String[]> sesionesLista;

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
		frmCrearSesiones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearSesiones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDa = new JLabel("• Día:");
		lblDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDa.setBounds(10, 11, 42, 17);
		panel.add(lblDa);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}));
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBox_1.setBounds(100, 53, 89, 22);
		panel.add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBox_1_1.setBounds(87, 93, 89, 22);
		panel.add(comboBox_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		//textArea.setBounds(340, 42, 334, 191);
		//panel.add(textArea);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(340, 42, 334, 191);
		panel.add(scroll);
		
		sesionesLista = new ArrayList<String[]>();
		
		 
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedIndex()>=comboBox_1_1.getSelectedIndex()+1) {
					JOptionPane.showMessageDialog(frmCrearSesiones,"No se ha podido añadir la sesión. \nLa hora final debe ser mayor que la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String vSesiones[] = new String[3]; 
					vSesiones[0]=(String) comboBox.getModel().getElementAt(comboBox.getSelectedIndex());
					vSesiones[1]=(String) comboBox_1.getModel().getElementAt(comboBox_1.getSelectedIndex());
					vSesiones[2]=(String) comboBox_1_1.getModel().getElementAt(comboBox_1_1.getSelectedIndex());
					sesionesLista.add(vSesiones);
					textArea.setText(textArea.getText()+comboBox.getModel().getElementAt(
							comboBox.getSelectedIndex())+
							" de "+comboBox_1.getModel().getElementAt(comboBox_1.getSelectedIndex())+
							" a "+comboBox_1_1.getModel().getElementAt(comboBox_1_1.getSelectedIndex())+"\n");
					Iterator<String[]> iter=sesionesLista.iterator();					
					/*
					while(iter.hasNext()) {
						String vector[]=iter.next();
						System.out.printf("%s - %s - %s\n", vector[0],vector[1],vector[2]);
					}
					*/
				}
				
			}
		});
		btnNewButton.setBounds(10, 145, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				sesionesLista.removeAll(sesionesLista);
				frmCrearSesiones.dispose();
			}
		});
		btnCancelar.setBounds(10, 277, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmCrearSesiones,"Las sesiones se han guardado correctamente.","Guardado",JOptionPane.INFORMATION_MESSAGE);
				frmCrearSesiones.dispose();
			}
		});
		btnGuardar.setBounds(585, 277, 89, 23);
		panel.add(btnGuardar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				sesionesLista.removeAll(sesionesLista);
			}
		});
		btnLimpiar.setBounds(350, 244, 89, 23);
		panel.add(btnLimpiar);
		
		
		
		
		
		
	}

	public List<String[]> getSesionesLista() {
		return sesionesLista;
	}

	public void setSesionesLista(List<String[]> sesionesLista) {
		this.sesionesLista = sesionesLista;
	}
}
