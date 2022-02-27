package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.toedter.calendar.JCalendar;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;


public class reserva_admin_cliente {

	private JFrame frmReservaInstalacion;
	private JTextField textField;
	private InstalacionesModel modelo = new InstalacionesModel();
	private ReservasModel modeloReservas = new ReservasModel();
	private SwingMain principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserva_admin_cliente window = new reserva_admin_cliente();
					window.frmReservaInstalacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public reserva_admin_cliente() {
		initialize();
	}
	public reserva_admin_cliente(SwingMain principal) {
		initialize();
		this.principal = principal;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaInstalacion = new JFrame();
		frmReservaInstalacion.setTitle("RESERVA INSTALACION");
		frmReservaInstalacion.setBounds(100, 100, 700, 500);
		frmReservaInstalacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//obtener todas las instalaciones
		List<Object[]> lista=modelo.getInstalaciones();
		
		String[] instalaciones=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}
		
		
		JPanel panel = new JPanel();
		frmReservaInstalacion.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione instalaci\u00F3n:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 37, 178, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNDeSocio = new JLabel("N\u00BA de socio:");
		lblNDeSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNDeSocio.setBounds(42, 231, 96, 14);
		panel.add(lblNDeSocio);
		
		JButton btnNewButton = new JButton("Reservar");
		
		btnNewButton.setBounds(538, 398, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//borrar la ventana
				frmReservaInstalacion.dispose();
			}
		});
		btnCancelar.setBounds(42, 398, 89, 23);
		panel.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(205, 230, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		//array para introducir todos 
//		String[] instalaciones = new String[10];
//		for(int i=0; i<instalaciones.length; i++) {
//			instalaciones[i]="HOla"; //cambiar hola por el elemento de la lista del modelo que toque
//		}
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox.setBounds(205, 35, 169, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de la reserva:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 99, 157, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hora de la reserva:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(42, 168, 157, 14);
		panel.add(lblNewLabel_1_1);
		
		JComboBox comboBoxHora = new JComboBox();
		comboBoxHora.setModel(new DefaultComboBoxModel(new String[] {"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"}));
		comboBoxHora.setBounds(205, 166, 86, 22);
		panel.add(comboBoxHora);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(205, 99, 113, 19);
		panel.add(dateChooser);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//id_instalacion
				String id;
				
				List<Object[]> lista=modelo.getIdInstalacion((String)comboBox.getSelectedItem());
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
				String date = sdf.format(dateChooser.getDate());
				String hora = (String)comboBoxHora.getSelectedItem();
				String diaHora = date+" "+hora;
				System.out.println(diaHora);
				if (modeloReservas.comprobarDisponibilidad(id, diaHora)) {
					System.out.println("Correcto, has podido reservar");
					
				}
				else System.out.println("Está ocupado");
			}
		});

	}

	public Window getFrmReservaAdmin() {
		// TODO Auto-generated method stub
		return this.frmReservaInstalacion;
	}
	
	public int dateGetYear(){
	        Date date = new Date();
	        ZoneId timeZone = ZoneId.systemDefault();
	        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
	        return getLocalDate.getYear();
    
	}
}
