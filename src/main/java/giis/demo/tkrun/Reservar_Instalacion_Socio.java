package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JSeparator;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class Reservar_Instalacion_Socio {

	private InstalacionesModel modelo = new InstalacionesModel();
	private CosteModel modeloCoste = new CosteModel();
	private ReservasModel modeloReservas = new ReservasModel();
	private JFrame frmReservarInstalacin;
	private JTextField textFieldCoste;
	private SwingMain principal;

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
	public Reservar_Instalacion_Socio(SwingMain principal) {
		initialize();
		this.principal = principal;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarInstalacin = new JFrame();
		frmReservarInstalacin.setTitle("Reservar Instalación");
		frmReservarInstalacin.setBounds(100, 100, 450, 300);
		frmReservarInstalacin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//obtener todas las instalaciones
				List<Object[]> lista=modelo.getInstalaciones();
				
				String[] instalaciones=new String[lista.size()];
				
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					instalaciones[i]=iterador.next()[0].toString();
					i++;
				}
				
	 //Obetener coste de la instalacion seleccionada
				
		
		JPanel panel = new JPanel();
		frmReservarInstalacin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelFechadeReserva = new JLabel("Fecha de la Reserva:");
		LabelFechadeReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFechadeReserva.setBounds(10, 10, 141, 27);
		panel.add(LabelFechadeReserva);
		
		JLabel LabelCoste = new JLabel("Coste de la Reserva:");
		LabelCoste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelCoste.setBounds(10, 159, 141, 29);
		panel.add(LabelCoste);
		
		textFieldCoste = new JTextField();
		textFieldCoste.setEditable(false);
		//textFieldCoste.setModel(new DefaultComboBoxModel(instalaciones));
		textFieldCoste.setBounds(161, 166, 93, 19);
		panel.add(textFieldCoste);
		textFieldCoste.setColumns(10);
		
		JLabel LabelMetododePago = new JLabel("Método de Pago:");
		LabelMetododePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelMetododePago.setBounds(10, 192, 113, 29);
		panel.add(LabelMetododePago);
		
		JCheckBox CheckBoxEstaLibre = new JCheckBox("Está libre");
		CheckBoxEstaLibre.setEnabled(false);
		CheckBoxEstaLibre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxEstaLibre.setBounds(161, 108, 93, 21);
		panel.add(CheckBoxEstaLibre);
		
		JCheckBox CheckBoxPuedesReservar = new JCheckBox("Puedes Reservar");
		CheckBoxPuedesReservar.setEnabled(false);
		CheckBoxPuedesReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CheckBoxPuedesReservar.setBounds(264, 108, 152, 21);
		panel.add(CheckBoxPuedesReservar);
		
		JComboBox comboBoxMetodo = new JComboBox();
		comboBoxMetodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMetodo.setModel(new DefaultComboBoxModel(new String[] {"<selecciona uno>", "Pagar ahora", "Añadir a mi cuota"}));
		comboBoxMetodo.setBounds(146, 196, 170, 21);
		panel.add(comboBoxMetodo);
		
		JButton ButtonCancelar = new JButton("Cancelar\r\n");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReservarInstalacin.dispose();
			}
		});
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(182, 232, 103, 21);
		panel.add(ButtonCancelar);
		
		JButton ButtonGuardar = new JButton("Guardar");
		ButtonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ButtonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonGuardar.setBounds(316, 232, 85, 21);
		panel.add(ButtonGuardar);
		
		JLabel LabelInstalacion = new JLabel("Seleccione Instalación:");
		LabelInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelInstalacion.setBounds(10, 126, 152, 30);
		panel.add(LabelInstalacion);
		
		JComboBox comboBoxInstalaciones = new JComboBox();
		comboBoxInstalaciones.setModel(new DefaultComboBoxModel(instalaciones));
		comboBoxInstalaciones.setBounds(160, 135, 141, 21);
		panel.add(comboBoxInstalaciones);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(159, 18, 113, 19);
		panel.add(dateChooserInicio);
		
		JLabel LabelHoraIni = new JLabel("Hora de Inicio:\r\n");
		LabelHoraIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelHoraIni.setBounds(10, 47, 113, 19);
		panel.add(LabelHoraIni);
		
		JComboBox comboBoxHoraIni = new JComboBox();
		comboBoxHoraIni.setModel(new DefaultComboBoxModel(new String[] {"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"}));
		comboBoxHoraIni.setBounds(158, 48, 85, 21);
		panel.add(comboBoxHoraIni);
		
		JLabel LabelFin = new JLabel("Hora de Fin:");
		LabelFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelFin.setBounds(10, 76, 85, 17);
		panel.add(LabelFin);
		
		JComboBox comboBoxHoraFin = new JComboBox();
		comboBoxHoraFin.setModel(new DefaultComboBoxModel(new String[] {"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"}));
		comboBoxHoraFin.setBounds(159, 79, 85, 21);
		panel.add(comboBoxHoraFin);
		
		JButton ButtonComprobar = new JButton("Comprobar");
		ButtonComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//id_instalacion
				String id;
				
				List<Object[]> lista=modelo.getIdInstalacion((String)comboBoxInstalaciones.getSelectedItem());
				String[] nombre=new String[lista.size()];
				Iterator<Object[]> iterador = lista.iterator();
				
				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}
				
				id = nombre[0];
				System.out.println(id);
				
				//fecha
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooserInicio.getDate());
				String hora = (String)comboBoxHoraIni.getSelectedItem();
				String diaHora = date+" "+hora;
				System.out.println(diaHora);
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					CheckBoxEstaLibre.setSelected(true);
					
				}
				else System.out.println("Está ocupado");
				
				
				
				
			}
		});
		ButtonComprobar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonComprobar.setBounds(10, 108, 113, 21);
		panel.add(ButtonComprobar);
	}



	public Window getFrmReservarInstalacin() {
		// TODO Auto-generated method stub
		return this.frmReservarInstalacin;
	}
}
