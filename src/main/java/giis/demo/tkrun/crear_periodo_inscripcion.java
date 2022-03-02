package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import giis.demo.util.SwingMain;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

public class crear_periodo_inscripcion {

	private JFrame frmCrearPeriodoDe;
	
	private crear_actividad ventanaCrearActividad;
	private JTextField textField;
	private PeriodosInscripcionModel modeloPer = new PeriodosInscripcionModel();
	private SwingMain principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crear_periodo_inscripcion window = new crear_periodo_inscripcion();
					window.frmCrearPeriodoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public crear_periodo_inscripcion(crear_actividad vSM) {
		this.ventanaCrearActividad=vSM;
		initialize();
	}

	/**
	 * Create the application.
	 */
	public crear_periodo_inscripcion() {
		initialize();
	}
	
	public JFrame getFrmCrearPeriodoDe() {
		return this.frmCrearPeriodoDe;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearPeriodoDe = new JFrame();
		frmCrearPeriodoDe.setTitle("Crear Periodo de Inscripción");
		frmCrearPeriodoDe.setBounds(100, 100, 700, 300);
		frmCrearPeriodoDe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearPeriodoDe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("• Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 73, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(93, 10, 143, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("• Descripción:");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcin.setBounds(10, 50, 95, 14);
		panel.add(lblDescripcin);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 75, 226, 86);
		panel.add(textArea);
		
		JLabel lblSocios = new JLabel("• Socios:");
		lblSocios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSocios.setBounds(275, 11, 73, 14);
		panel.add(lblSocios);
		
		JLabel lblNoSocios = new JLabel("• No socios:");
		lblNoSocios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoSocios.setBounds(463, 11, 79, 14);
		panel.add(lblNoSocios);
		
		JLabel lblFechaInicio = new JLabel("- Fecha inicio:");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaInicio.setBounds(292, 50, 89, 14);
		panel.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("- Fecha fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaFin.setBounds(292, 111, 73, 14);
		panel.add(lblFechaFin);
		
		JLabel lblFechaFin_1 = new JLabel("- Fecha fin:");
		lblFechaFin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaFin_1.setBounds(479, 50, 73, 14);
		panel.add(lblFechaFin_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(302, 75, 135, 19);
		panel.add(dateChooser);
		
		
		
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(302, 136, 135, 19);
		panel.add(dateChooser_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(489, 75, 135, 19);
		panel.add(dateChooser_2);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCrearPeriodoDe.dispose();
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateIniSoc = dateChooser.getDate();
				Date dateFinSoc = dateChooser_1.getDate();
				Date dateFinNoSoc = dateChooser_2.getDate();
				//fecha de hoy
				Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nIntroduce un nombre.","Error",JOptionPane.ERROR_MESSAGE);
				}	
				else if(textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nIntroduce una descripción.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateIniSoc==null) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nIntroduce una fecha inicial de socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinSoc==null) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nIntroduce una fecha final de socios.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinNoSoc==null) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nIntroduce una fecha final de no socios","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateIniSoc.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nLa fecha inicial de socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinSoc.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nLa fecha final de socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinNoSoc.getTime()-dateHoy.getTime()<0) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nLa fecha final de no socios no puede ser anterior a la actual.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinSoc.getTime()-dateIniSoc.getTime()<0) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nLa fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateFinNoSoc.getTime()-dateIniSoc.getTime()<0) {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el periodo. \nLa fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String nombre=textField.getText();
					String descripcion=textArea.getText();
					String fecha_ini_soc=sdf.format(dateIniSoc);
					String fecha_fin_soc=sdf.format(dateFinSoc);
					String fecha_fin_no_soc=sdf.format(dateFinNoSoc);
					modeloPer.nuevoPeriodoIns(nombre, descripcion, fecha_ini_soc, fecha_fin_soc, fecha_fin_no_soc);
					JOptionPane.showMessageDialog(null,"El periodo se ha creado correctamente","Creado",JOptionPane.INFORMATION_MESSAGE);	
					if(ventanaCrearActividad!=null) {
						ventanaCrearActividad.getPeriodosIns();
						ventanaCrearActividad.comboBox_1_1.setSelectedIndex(ventanaCrearActividad.comboBox_1_1.getItemCount()-1);
					}
					frmCrearPeriodoDe.dispose();
				}
				
			}
		});
		btnNewButton_1.setBounds(585, 227, 89, 23);
		panel.add(btnNewButton_1);
		
		
	}

	public Window getFrmCrearActividad() {
		return this.getFrmCrearPeriodoDe();
	}
}
